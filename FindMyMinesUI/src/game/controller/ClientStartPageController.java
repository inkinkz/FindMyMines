package game.controller;

import javafx.scene.control.*;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class ClientStartPageController implements Initializable {


    @FXML
    private Button connect;

    @FXML
    private Label Title;

    @FXML
    private Label clientNamebox;

    @FXML
    private ImageView backImage;

    @FXML
    private AnchorPane colorPane;
    @FXML
    private Button connectButton;

    // this will be assign to each button in the GamePage 0=free 1=bomb
    public static int[][] valueOfSpace = new int[6][6];

    // create array to keep number of surrounding bomb
    public static int[][] bombAround = new int[6][6];
    int numBomb = 0;

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtServer;

    static String userName;
    static String server;
    static int port = 1500;
    static Socket socket;
    public static boolean connected;
    public static ObjectInputStream sInput; // to read from the socket
    public static ObjectOutputStream sOutput; // to write on the socket

    @FXML
    private Label warnIP;

    @FXML
    private Label warnName;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Image image = new Image(getClass().getResourceAsStream("/bomb.png"));
        backImage.setImage(image);

    }

    @FXML
    void connect(ActionEvent event) throws IOException {

        server = txtServer.getText().trim();
        userName = txtUser.getText().trim();

        if (server.isEmpty() && userName.isEmpty()) {
            warnIP.setVisible(true);
            warnName.setVisible(true);
            return;
        } else if (server.isEmpty()) {
            warnIP.setVisible(true);
            warnName.setVisible(false);
            return;
        } else if (userName.isEmpty()) {
            warnName.setVisible(true);
            warnIP.setVisible(false);
            return;
        } else if (!startConnection()) {
            showErrorPopup();
            return;
        }


        AnchorPane gamePage = (AnchorPane) FXMLLoader.load(getClass().getResource("/game/view/ClientGamePage.fxml"));
        Scene scene = new Scene(gamePage);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setMinWidth(1000);
        stage.setMinHeight(520);
        stage.setScene(scene);
        //when user closed the window
        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("Disconnected from the server.");
                disconnect();
                System.exit(0);
            }
        });
        stage.show();

    }

    public boolean startConnection() {
        // try to connect to the server

        //if there is a game playing, allow client to start connection but not join the game
        //method getGameStateBeforeConnection() right now is blank and needs implementation
        if (getGameStateBeforeConnection() != null && !getGameStateBeforeConnection().equals("WAITING")) {
            //do something
        }

        try {
            socket = new Socket(server, port);
            connected = true;
        }
        // if it failed
        catch (Exception ec) {
            System.out.println("Error connecting to server:" + ec);
            return false;
        }

        String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
        System.out.println(msg);

        /* Creating both Data Stream */
        try {
            sInput = new ObjectInputStream(socket.getInputStream());
            sOutput = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException eIO) {
            System.out.println("Exception creating new Input/output Streams: " + eIO);
            return false;
        }

        // Send our username to the server this is the only message that we
        // will send as a String. All other messages will be ChatMessage objects
        try {
            sOutput.writeObject(userName);
        } catch (IOException eIO) {
            System.out.println(eIO);
            disconnect();
            return false;
        }
        // success we inform the caller that it worked
        return true;
    }

    private void disconnect() {
        try {
            if (sInput != null)
                sInput.close();
        } catch (Exception e) {
        } // not much else I can do
        try {
            if (sOutput != null)
                sOutput.close();
        } catch (Exception e) {
        } // not much else I can do
        try {
            if (socket != null)
                socket.close();
        } catch (Exception e) {
        } // not much else I can do

        // inform the GUI
        connectionFailed();

    }

    public void connectionFailed() {
        // don't react to a <CR> after the username
        connected = false;
    }

    @FXML
    void clickedButton(MouseEvent event) {
        //connectButton.setStyle("-fx-background-color: #8D99AE");
    }

    @FXML
    void releasedButton(MouseEvent event) {
        //	connectButton.setStyle("-fx-background-color: #EDF2F4");
    }

    private String getGameStateBeforeConnection() {
        //to be implemented to ask for game state from server
        return "";
    }

    private void showErrorPopup() {
        String content = "Cannot connect to server. Please make sure that the IP address is correct and the server is online.";
        String header = "Connection Error";

        //error if client tries to join during game
        if (getGameStateBeforeConnection() != null && !getGameStateBeforeConnection().equals("WAITING")) {
            System.out.println("Result from getGameStateBeforeConnection() = " + FindMyMinesServer.getGameState());
            content = "The current game is playing right now. Please wait until the current game ends and try again.";
            header = "Server is busy";
        }
        Alert alert = new Alert(Alert.AlertType.ERROR, content, ButtonType.OK);
        alert.setHeaderText(header);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }
}