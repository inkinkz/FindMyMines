package game.controller;

import static java.util.stream.Collectors.toMap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import game.model.ButtonClick;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ClientGamePageController implements Initializable {
	@FXML
	private Button b1;

	@FXML
	private Button b2;

	@FXML
	private Button b3;

	@FXML
	private Button b4;

	@FXML
	private Button b5;

	@FXML
	private Button b6;

	@FXML
	private Button b11;

	@FXML
	private Button b21;

	@FXML
	private Button b31;

	@FXML
	private Button b41;

	@FXML
	private Button b51;

	@FXML
	private Button b61;

	@FXML
	private Button b12;

	@FXML
	private Button b22;

	@FXML
	private Button b32;

	@FXML
	private Button b42;

	@FXML
	private Button b52;

	@FXML
	private Button b62;

	@FXML
	private Button b13;

	@FXML
	private Button b23;

	@FXML
	private Button b33;

	@FXML
	private Button b43;

	@FXML
	private Button b53;

	@FXML
	private Button b63;

	@FXML
	private Button b14;

	@FXML
	private Button b24;

	@FXML
	private Button b34;

	@FXML
	private Button b44;

	@FXML
	private Button b54;

	@FXML
	private Button b64;

	@FXML
	private Button b15;

	@FXML
	private Button b25;

	@FXML
	private Button b35;

	@FXML
	private Button b45;

	@FXML
	private Button b55;

	@FXML
	private Button b65;

	@FXML
	private Label showTime;

	@FXML
	private Pane player1Pane;

	@FXML
	private Label player1;

	@FXML
	private Label score1;

	@FXML
	private Pane player2Pane;

	@FXML
	private Label player2;

	@FXML
	private Label score2;

	@FXML
	private Pane player3Pane;

	@FXML
	private Label player3;

	@FXML
	private Label score3;

	@FXML
	private Pane player4Pane;

	@FXML
	private Label player4;

	@FXML
	private Label score4;

	@FXML
	private Pane player5Pane;

	@FXML
	private Label player5;

	@FXML
	private Label score5;

	@FXML
	private Pane player6Pane;

	@FXML
	private Label player6;

	@FXML
	private Label score6;

	@FXML
	private Pane player7Pane;

	@FXML
	private Label player7;

	@FXML
	private Label score7;

	@FXML
	private Pane player8Pane;

	@FXML
	private Label player8;

	@FXML
	private Label score8;

	@FXML
	private Pane player9Pane;

	@FXML
	private Label player9;

	@FXML
	private Label score9;

	@FXML
	private Pane player10Pane;

	@FXML
	private Label player10;

	@FXML
	private Label score10;

	@FXML
	private Label bombLeft;

	@FXML
	private Button readyButton;

//for score board
	@FXML
	private Pane player1Pane1;

	@FXML
	private Label player11;

	@FXML
	private Label score11;

	@FXML
	private Pane player2Pane1;

	@FXML
	private Label player21;

	@FXML
	private Label score21;

	@FXML
	private Pane player3Pane1;

	@FXML
	private Label player31;

	@FXML
	private Label score31;

	@FXML
	private Pane player4Pane1;

	@FXML
	private Label player41;

	@FXML
	private Label score41;

	@FXML
	private Pane player5Pane1;

	@FXML
	private Label player51;

	@FXML
	private Label score51;

	@FXML
	private Pane player6Pane1;

	@FXML
	private Label player61;

	@FXML
	private Label score61;

	@FXML
	private Pane player7Pane1;

	@FXML
	private Label player71;

	@FXML
	private Label score71;

	@FXML
	private Pane player8Pane1;

	@FXML
	private Label player81;

	@FXML
	private Label score81;

	@FXML
	private Pane player9Pane1;

	@FXML
	private Label player91;

	@FXML
	private Label score91;

	@FXML
	private Pane player10Pane1;

	@FXML
	private Label player101;

	@FXML
	private Label score101;
	
	@FXML
    private AnchorPane leftPane;
	
	@FXML
    private AnchorPane rightPane;
	
	@FXML
	private Label title;

	
	@FXML
	private Button button_done;
	
	@FXML
	private DialogPane scoreBoard;

    //game
    static int numOfPlayer; // how many player
    Button[][] setOfButton = new Button[6][6];
    Pane[] setOfPlayerPane = new Pane[10]; // limit player :10
    private static ObservableMap<Integer, Integer> scoreOfPlayer;
    private ObservableValue<Integer> playerReady; // number of player ready
    Label[] setOfScore = new Label[10];
    Label[] setOfNameBoard = new Label[10];
    Label[] setOfScoreBoard = new Label[10];
    int numBombLeft = 11;
    private static String GAME_STATE; //to be implemented to receive from server

    @FXML
    private TextArea txtArea;

    @FXML
    private ListView<String> listUsersConnected;

    private ObservableList<String> users;
    
    

    int[][] bombplacement = new int[6][6];
    int[][] bombaround = new int[6][6];

    // Server Configuration
    private boolean connected = ClientStartPageController.connected;
    private String server = ClientStartPageController.server;
    private String username = ClientStartPageController.userName;
    private int port = ClientStartPageController.port;

    // for I/O
    private ObjectInputStream sInput = ClientStartPageController.sInput; // to read from the socket
    private ObjectOutputStream sOutput = ClientStartPageController.sOutput; // to write on the socket
    private Socket socket = ClientStartPageController.socket;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        new ListenFromServer().start();
        txtArea.setEditable(false);
		leftPane.setDisable(true);
        display("Hello, " + username + ".\n");
        display("When you are ready to play, press Ready button");
        // trigger this when server press start
        setUpPane();
        setUpBomb();
        
        startTimer();  //need to start when the game start
        //setScore();
        // color change for the starting player
        //setOfPlayerPane[player].setStyle("-fx-background-color: grey");
    }

    private void setScore() {
        for (int i = 0; i < 10; i++) {
            scoreOfPlayer.put(i, 0);
        }
    }

    private void setUpPane() {
        // put each pane into setOfPlayerPane
        setOfPlayerPane[0] = player1Pane;
        setOfPlayerPane[1] = player2Pane;
        setOfPlayerPane[2] = player3Pane;
        setOfPlayerPane[3] = player4Pane;
        setOfPlayerPane[4] = player5Pane;
        setOfPlayerPane[5] = player6Pane;
        setOfPlayerPane[6] = player7Pane;
        setOfPlayerPane[7] = player8Pane;
        setOfPlayerPane[8] = player9Pane;
        setOfPlayerPane[9] = player10Pane;

        // to hide who does not play
        for (int i = numOfPlayer; i < 10; i++) {
            setOfPlayerPane[i].setVisible(false);
        }

        setOfScore[0] = score1;
        setOfScore[1] = score2;
        setOfScore[2] = score3;
        setOfScore[3] = score4;
        setOfScore[4] = score5;
        setOfScore[5] = score6;
        setOfScore[6] = score7;
        setOfScore[7] = score8;
        setOfScore[8] = score9;
        setOfScore[9] = score10;

        setOfButton[0][0] = b1;
        setOfButton[1][0] = b2;
        setOfButton[2][0] = b3;
        setOfButton[3][0] = b4;
        setOfButton[4][0] = b5;
        setOfButton[5][0] = b6;
        setOfButton[0][1] = b11;
        setOfButton[1][1] = b21;
        setOfButton[2][1] = b31;
        setOfButton[3][1] = b41;
        setOfButton[4][1] = b51;
        setOfButton[5][1] = b61;
        setOfButton[0][2] = b12;
        setOfButton[1][2] = b22;
        setOfButton[2][2] = b32;
        setOfButton[3][2] = b42;
        setOfButton[4][2] = b52;
        setOfButton[5][2] = b62;
        setOfButton[0][3] = b13;
        setOfButton[1][3] = b23;
        setOfButton[2][3] = b33;
        setOfButton[3][3] = b43;
        setOfButton[4][3] = b53;
        setOfButton[5][3] = b63;
        setOfButton[0][4] = b14;
        setOfButton[1][4] = b24;
        setOfButton[2][4] = b34;
        setOfButton[3][4] = b44;
        setOfButton[4][4] = b54;
        setOfButton[5][4] = b64;
        setOfButton[0][5] = b15;
        setOfButton[1][5] = b25;
        setOfButton[2][5] = b35;
        setOfButton[3][5] = b45;
        setOfButton[4][5] = b55;
        setOfButton[5][5] = b65;

        setOfNameBoard[0] = player11;
        setOfNameBoard[1] = player21;
        setOfNameBoard[2] = player31;
        setOfNameBoard[3] = player41;
        setOfNameBoard[4] = player51;
        setOfNameBoard[5] = player61;
        setOfNameBoard[6] = player71;
        setOfNameBoard[7] = player81;
        setOfNameBoard[8] = player91;
        setOfNameBoard[9] = player101;

        setOfScoreBoard[0] = score11;
        setOfScoreBoard[1] = score21;
        setOfScoreBoard[2] = score31;
        setOfScoreBoard[3] = score41;
        setOfScoreBoard[4] = score51;
        setOfScoreBoard[5] = score61;
        setOfScoreBoard[6] = score71;
        setOfScoreBoard[7] = score81;
        setOfScoreBoard[8] = score91;
        setOfScoreBoard[9] = score101;
    }

    private int player = 0;
    private int playerplaying = 1;

    void colorChange() {
        if (playerplaying < numOfPlayer) {
            setOfPlayerPane[--playerplaying].setStyle("-fx-background-color: white");
            setOfPlayerPane[++playerplaying].setStyle("-fx-background-color: grey");
            playerplaying++;
        } else if (playerplaying == numOfPlayer) {
            setOfPlayerPane[numOfPlayer - 1].setStyle("-fx-background-color: white");
            setOfPlayerPane[0].setStyle("-fx-background-color: grey");
            playerplaying = 1;
        } else {
            playerplaying = 1;
        }

    }

    // playing (Button Clicked)
    @FXML
    void play(MouseEvent event) {

        //send buttion position to server
        sendButtonPosition(event.toString().substring(32, 34).trim());


        // set color of player to know whose turn is next
        colorChange();
        // timer of next player
        maxTime = 0;
        startTimer();
        Button y = (Button) event.getTarget();


        if (y.getStyle() == "-fx-font-size: 0.0") {// free slot
            // ((Button) event.getTarget()).setStyle("-fx-font-size: 10");
            ((Button) event.getTarget()).setStyle("-fx-text-fill: #ffffff ; -fx-background-color:#2B2D42");
            ((Button) event.getTarget()).setDisable(true);
            player++;
        }

        if (y.getStyle() == "-fx-font-size: 0.1") {// bomb
            // might need to getStyle().removeAll() before do this to prevent bugs
            ((Button) event.getTarget()).setStyle("-fx-background-color: #D90429; -fx-text-fill: #ffffff ; -fx-font-size: 10;");
            ((Button) event.getTarget()).setText("BOMB");
            ((Button) event.getTarget()).setDisable(true);
            numBombLeft--;
            bombLeft.setText(numBombLeft + "");
//
//            int score = scoreOfPlayer.get(player);
//            scoreOfPlayer.put(player, score++);
//
//            setOfScore[player].setText(score + "");
            player++;
        }

        if (player == numOfPlayer) {
            player = 0;
        }
    }

    // receive button position clicked from other clients
    void playFromOthers(String cl) {

        String s = cl.trim();
        int j = 0;
        int i = (Integer.parseInt(s.charAt(0)+"")) - 1;
        if(s.length() >= 2) {
            if (!(s.substring(1).equals(","))) {
                j = Integer.parseInt(s.charAt(1) + "");
            }
        }

        // To check button position
//        display("i = " + i + " j = " + j);
        Button y = setOfButton[i][j];

        if (y.getStyle() == "-fx-font-size: 0.0") {// free slot
           y.setStyle("-fx-font-size: 10;-fx-background-color:#2B2D42; -fx-text-fill: #edf2f4");
           y.setDisable(true);
        }

        if (y.getStyle() == "-fx-font-size: 0.1") {// bomb
        	y.setStyle("-fx-font-size: 10;-fx-background-color:#D90429;-fx-text-fill: #edf2f4");
        	y.setDisable(true);
            y.setText("BOMB");
        }

        if (y.getStyle() == "-fx-font-size: 0.2") {// bomb
        	y.setStyle("-fx-font-size: 5;-fx-background-color:#D90429;-fx-text-fill: #edf2f4");
        	y.setDisable(true);
            y.setText("BOMB \n x2");
        }

        if (y.getStyle() == "-fx-font-size: 0.3") {// bomb
        	y.setStyle("-fx-font-size: 5;-fx-background-color:#D90429;-fx-text-fill: #edf2f4");
        	y.setDisable(true);
            y.setText("BOMB \n x3");
        }

        if (y.getStyle() == "-fx-font-size: 0.4") {// bomb
        	y.setStyle("-fx-font-size: 5;-fx-background-color:#D90429;-fx-text-fill: #edf2f4");
        	y.setDisable(true);
            y.setText("BOMB \n x4");
        }
    }
    
    //tram
    static Timer timer = new Timer();//tram
    int time=10;
    int maxTime=10;
    
    void startTimer() {

    	TimerTask task;
    	task = new TimerTask() {
         
            @Override
			public void run() {
				if (maxTime > 0) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							showTime.setText(time + "");
						}
					});
					System.out.println("Seconds = " + time);
					time--;
					maxTime--;
                } else {
                    // stop the timer
             
                	/*player++;
                	if (player == numOfPlayer) {
                        player = 0;
                    }
                    */colorChange();
                    startTimer();
                    cancel();
                }
            }
        };
        timer.schedule(task, 0, 1000);
    }


    /*// to display count down game timer
    void startTimer() {
        // timer run
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                for (int i = 10; i >= 0; i--) {
                    // exit timer
                    if (false) {
                        // to stop timer
                        return null;
                    }
                    updateMessage(i + "");
                    Thread.sleep(1000);
                }
                return null;
            }
        };
        // exit timer();
        if (false) {
            // to stop timer
            return;
        }
        showTime.textProperty().bind(task.messageProperty());
        task.setOnSucceeded(e -> {
            showTime.textProperty().unbind();
            showTime.setText("0");
            // skip this player when timeout
            player++;
            if (player == numOfPlayer) {
                player = 0;
            }
            colorChange();
            // set condition back to default

            // startTimer after timeout
            startTimer();
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }*/

    Integer[] nameOfPlayer = new Integer[10];
    private static Map<Integer, Integer> sorted = new Hashtable<Integer, Integer>();

    @FXML
    private Button buttonDone;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        AnchorPane gamePage = (AnchorPane) FXMLLoader
                .load(getClass().getResource("/FindMyMinesUI/src/game/view/ClientStartPage.fxml"));
        Scene scene = new Scene(gamePage);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setMinWidth(1000);
        stage.setMinHeight(520);
        stage.setScene(scene);
        stage.show();
    }

	boolean alreadyReady = false;

	@FXML
	void ready(ActionEvent event) throws IOException {

		if (!alreadyReady) {
		    sendReady();
			// number of ready player increase every time a client click ready
			int ready = playerReady.getValue();
			playerReady = new SimpleIntegerProperty(ready++).asObject();
			// set ready button to disable after being pressed
			readyButton.setText("Not Ready");

		} else {
			/* if (alreadyReady) { */
			// set ready button to disable after being pressed
            sendNotReady();
			readyButton.setText("Ready");
		}

		alreadyReady = !alreadyReady;

	}

    // sort score
    public static Map<Integer, Integer> getSorted() {
        sorted = sort(scoreOfPlayer);
        System.out.print(sorted);
        return sorted;
    }

    // remove non=playing players
    private static Map<Integer, Integer> sort(Map<Integer, Integer> map) {
        Map<Integer, Integer> sorted = map.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        Iterator<Integer> iterators = sorted.keySet().iterator();
        while (iterators.hasNext()) {
            int key = iterators.next();
            if (key >= numOfPlayer) {
                iterators.remove();
            }
        }
        // System.out.print(sorted);
        return sorted;

    }
    // event going back to the starting page
    /*
     * @FXML void backtohome(ActionEvent event) throws IOException { AnchorPane
     * gamePage = (AnchorPane)
     * FXMLLoader.load(getClass().getResource("ClientStartPage.fxml")); Scene scene
     * = new Scene(gamePage); Stage stage = (Stage) ((Node)
     * event.getSource()).getScene().getWindow(); stage.setScene(scene);
     * stage.show(); }
     */

    // Login

    /*	public void login() {
            // test if we can start the connection to the Server
            // if it failed nothing we can do
            if (!startConnection())
                return;
            connected = true;
        }
    */

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
        display("Disconnected.");
    }

    /*
     * To send a message to the GUI
     */
    private void display(String msg) {
        txtArea.appendText(msg + "\n");
    }

    class ListenFromServer extends Thread {

        public void run() {
            String msgt;
            // game initialize
            scoreOfPlayer = FXCollections.observableHashMap();
            playerReady = new SimpleIntegerProperty(0).asObject();

            //server
            users = FXCollections.observableArrayList();
         
            listUsersConnected.setItems(users);
            
            
          

            try {
                bombplacement = (int[][]) sInput.readObject();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try {
                sInput = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                bombaround = (int[][]) sInput.readObject();

            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                sInput = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            Platform.runLater(() -> {
                setUpBomb();
            });
//
//            //listen for game_state messages
//            try {
//                msgt = (String) sInput.readObject();
//                msgt.trim();
//                if(msgt.equals("WAITING")){
//                    //This means the server has pressed "Reset" button
//                    //ENDED -> WAITING
//                    System.out.println("Received server msg (GAME_STATE): "+msgt); //just for debugging
//                    GAME_STATE = msgt;
//                    //do something
//
//                } else if(msgt.equals("ONGOING")){
//                    //This means the server has pressed "Start" button
//                    //WAITING -> ONGOING
//                    System.out.println("Received server msg (GAME_STATE): "+msgt); //just for debugging
//                    GAME_STATE = msgt;
//                    //do something
//
//                } else if(msgt.equals("ENDED")){
//                    //This means the server has pressed "Stop" button
//                    //ONGOING -> ENDED
//                    System.out.println("Received server msg (GAME_STATE): "+msgt); //just for debugging
//                    GAME_STATE = msgt;
//                    //do something
//                }
//
//            } catch (ClassNotFoundException e1) {
//                e1.printStackTrace();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//
//            try {
//                sInput = new ObjectInputStream(socket.getInputStream());
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }

            while (true) {
                try {
                    msgt = (String) sInput.readObject();
                    String msg = msgt.trim();
                    if (msg.length() >= 5) {
                        String[] split = msg.split(":");
                        if (split[1].equals("WHOISIN")) {
                            Platform.runLater(() -> {
                                users.add(split[0]);
                            });
                        } else if (split[1].equals("REMOVE")) {
                            Platform.runLater(() -> {
                                users.remove(split[0]);
                            });
                        } else if (split[1].equals("READDY")) {
                            Platform.runLater(() -> {
                                users.remove(split[0]);
                                users.add(split[0]+" (READY)");
                            });
                        } else if (split[1].equals("NOTREADY")) {
                            Platform.runLater(() -> {
                                users.remove(split[0] + " (READY)");
                                users.add(split[0]);
                            });
                        } else if (split[1].equals("GAMESTART")) {
                            Platform.runLater(() -> {
                                display("Server started the game!");
                            });
                        } else if (split[1].equals("GAMESTOP")) {
                            Platform.runLater(() -> {
                                display("Server stopped the game!");
                            });
                        }
                    }
                } catch (IOException e) {
                    display("Server has close the connection");
                    connectionFailed();
                    Platform.runLater(() -> {
                        listUsersConnected.setItems(null);
                    });
                    break;
                }
                // can't happen with a String object but need the catch anyhow
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setUpBomb() {
//		numOfPlayer = users.size(); // how many clients are there

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int result = bombplacement[i][j];
                Button y = setOfButton[i][j];
                if (result == 0) {
					y.setStyle("-fx-font-size: 0.0"); // blank
				}
				if (result == 1) {
					y.setStyle("-fx-font-size: 0.1"); // bomb
				}
				if (result == 2) {
					y.setStyle("-fx-font-size: 0.2"); // bomb
				}
				if (result == 3) {
					y.setStyle("-fx-font-size: 0.3"); // bomb
				}
				if (result == 4) {
					y.setStyle("-fx-font-size: 0.4"); // bomb
				}
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int numOfBombAround = bombaround[i][j];
                if (numOfBombAround > 0) {
                    setOfButton[i][j].setText("" + numOfBombAround);

                }
            }
        }

    }

    public void sendButtonPosition(String pos) {
        if (connected) {
            ButtonClick msg = new ButtonClick(ButtonClick.CLICK, pos);
            try {
                sOutput.writeObject(msg);
            } catch (IOException e) {
                display("Exception writing to server: " + e);
            }
        }
    }

    public void sendReady(){
        if (connected) {
            ButtonClick msg = new ButtonClick(ButtonClick.READDY, username);
            try {
                sOutput.writeObject(msg);
            } catch (IOException e) {
                display("Exception writing to server: " + e);
            }
        }
    }

    public void sendNotReady(){
        if (connected) {
            ButtonClick msg = new ButtonClick(ButtonClick.NOTREADY, username);
            try {
                sOutput.writeObject(msg);
            } catch (IOException e) {
                display("Exception writing to server: " + e);
            }
        }
    }

}