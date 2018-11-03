package game.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import game.model.ButtonClick;

public class FindMyMinesServer {
    // a unique ID for each connection
    private static int uniqueId;
    // an ArrayList to keep the list of the Client
    private ArrayList<ClientThread> clientsConnected;
    // if I am in a GUI
    private ServerGamePageController serverController;
    // the port number to listen for connection
    private int port;
    // the boolean that will be turned of to stop the server
    private boolean keepGoing;

    int[][] bombplacement = ServerGamePageController.valueOfSpace;
    static int[][] bombaround = ServerGamePageController.bombAround;

    /*
     * server constructor that receive the port to listen to for connection as
     * parameter
     */
    public FindMyMinesServer(int port) {
        this(port, null);
    }

    public FindMyMinesServer(int port, ServerGamePageController serverController) {
        // GUI or not
        this.serverController = serverController;
        // the port
        this.port = port;
        // ArrayList for the Client list
        clientsConnected = new ArrayList<ClientThread>();
    }

    public void start() {
        keepGoing = true;
        /* create socket server and wait for connection requests */
        try {
            // the socket used by the server
            ServerSocket serverSocket = new ServerSocket(port);

            display("Server waiting for Clients on port " + port + "." + "\n");

            // infinite loop to wait for connections
            while (keepGoing) {
                // format message saying we are waiting

                Socket socket = serverSocket.accept(); // accept connection

                // if I was asked to stop
                if (!keepGoing)
                    break;
                ClientThread t = new ClientThread(socket); // make a thread of it
                clientsConnected.add(t); // save it in the ArrayList
                t.start();
            }
            // I was asked to stop
            try {
                serverSocket.close();
                for (int i = 0; i < clientsConnected.size(); ++i) {
                    ClientThread tc = clientsConnected.get(i);
                    try {
                        tc.sInput.close();
                        tc.sOutput.close();
                        tc.socket.close();
                    } catch (IOException ioE) {
                        // not much I can do
                    }
                }
            } catch (Exception e) {
                display("Exception closing the server and clients: " + e);
            }
        }
        // something went bad
        catch (IOException e) {
            display(" Exception on new ServerSocket: " + e + "\n");
        }
    }

    /*
     * For the GUI to stop the server
     */
    public void stop() {
        keepGoing = false;
        // connect to myself as Client to exit statement
        // Socket socket = serverSocket.accept();
        try {
            new Socket("localhost", port);
        } catch (Exception e) {
            // nothing I can really do
        }
    }

    /*
     * Display an event (not a message) to the GUI
     */
    private void display(String msg) {
        serverController.appendEvent(msg + "\n");
    }

    /*
     * to broadcast a message to all Clients
     */
    private synchronized void broadcast(String message) {
        String messageLf;
        if (message.contains("WHOISIN") || message.contains("REMOVE")) {
            messageLf = message;
        } else {
            messageLf = " " + message + "\n";
            serverController.appendRoom(messageLf); // append in the room window
        }

//		 we loop in reverse order in case we would have to remove a Client
//		 because it has disconnected
        for (int i = clientsConnected.size(); --i >= 0; ) {
            ClientThread ct = clientsConnected.get(i);
            // try to write to the Client if it fails remove it from the list
            if (!ct.writeMsg(messageLf)) {
                clientsConnected.remove(i);
                serverController.remove(ct.username);
                display("Disconnected Client " + ct.username + " removed from list.");
            }
        }
    }

    // for a client who logoff
    synchronized void remove(int id) {
        // scan the array list until we found the Id
        for (int i = 0; i < clientsConnected.size(); ++i) {
            ClientThread ct = clientsConnected.get(i);
            // found it
            if (ct.id == id) {
                serverController.remove(ct.username);
                ct.writeMsg(ct.username + ":REMOVE");
                clientsConnected.remove(i);
                return;
            }
        }
    }

    /**
     * One instance of this thread will run for each client
     */
    class ClientThread extends Thread {
        // the socket where to listen/talk
        Socket socket;
        ObjectInputStream sInput;
        ObjectOutputStream sOutput;
        // my unique id (easier for deconnection)
        int id;
        // the Username of the Client
        String username;
        // the only type of message a will receive
        ButtonClick cm;

        // Constructor
        ClientThread(Socket socket) {
            // a unique id
            id = ++uniqueId;
            this.socket = socket;
            /* Creating both Data Stream */
            System.out.println("Thread trying to create Object Input/Output Streams");
            try {
                // create output first
                sOutput = new ObjectOutputStream(socket.getOutputStream());
                sInput = new ObjectInputStream(socket.getInputStream());
                sOutput.writeObject(bombplacement);
                sOutput = new ObjectOutputStream(socket.getOutputStream());
                sOutput.writeObject(bombaround);
                sOutput = new ObjectOutputStream(socket.getOutputStream());

                // read the username
                username = (String) sInput.readObject();
                serverController.addUser(username);
                broadcast(username + ":WHOISIN"); // Broadcast user who logged in
                writeMsg(username + ":WHOISIN");
                for (ClientThread client : clientsConnected) {
                    writeMsg(client.username + ":WHOISIN");
                }
                display(username + " just connected.");
            } catch (IOException e) {
                display("Exception creating new Input/output Streams: " + e);
                return;
            }
            // have to catch ClassNotFoundException
            // but I read a String, I am sure it will work
            catch (ClassNotFoundException e) {
            }
        }

        // what will run forever
        public void run() {
            // to loop until LOGOUT
            boolean keepGoing = true;
            while (keepGoing) {
                // read a String (which is an object)
                try {
                    cm = (ButtonClick) sInput.readObject();
                } catch (IOException e) {
                    display(username + " Exception reading Streams: " + e);
                    break;
                } catch (ClassNotFoundException e2) {
                    break;
                }
                // the message part of the ButtonClick
                String msg = cm.getMessage();

                // Switch on the type of message receive
                switch (cm.getType()) {
                    case ButtonClick.LOGOUT:
                        display(username + " disconnected from the server.");
                        broadcast(username + ":REMOVE");
                        keepGoing = false;
                        break;
                    case ButtonClick.CLICK:
                        broadcast(msg);
                        playOnServer(msg);
//                      display(message);
                        break;
                    case ButtonClick.READY:
                        broadcast(username + ":READY");
                        break;
                    case ButtonClick.NOTREADY:
                        broadcast(username + ":NOTREADY");
                        break;
                }

                // remove myself from the arrayList containing the list of the
                // connected Clients
            }
            remove(id);
            close();
        }

        public  void playOnServer(String s){


            ServerGamePageController.playFromOthers(s);
        }

        // try to close everything
        private void close() {
            // try to close the connection
            try {
                if (sOutput != null)
                    sOutput.close();
            } catch (Exception e) {
            }
            try {
                if (sInput != null)
                    sInput.close();
            } catch (Exception e) {
            }
            ;
            try {
                if (socket != null)
                    socket.close();
            } catch (Exception e) {
            }
        }

        /*
         * Write a String to the Client output stream
         */
        private boolean writeMsg(String msg) {
            // if Client is still connected send the message to it
            if (!socket.isConnected()) {
                close();
                return false;
            }
            // write the message to the stream
            try {
                sOutput.writeObject(msg);
            }
            // if an error occurs, do not abort just inform the user
            catch (IOException e) {
                display("Error sending message to " + username);
                display(e.toString());
            }
            return true;
        }

    }
}
