package game.controller;

import static java.util.stream.Collectors.toMap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.*;

import game.model.ButtonClick;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
    private Label showTimeLabel;

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
    private Label bombLeftLabel;

    @FXML
    private Label bombLeft;

    @FXML
    private Button readyButton;

    // for score board
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
    private ImageView winnerImage;

    @FXML
    private AnchorPane scoreboardPane;

    @FXML
    private Button button_done;

    @FXML
    private DialogPane scoreBoard;

    // game
    static int numOfPlayer; // how many player
    Button[][] setOfButton = new Button[6][6];
    Pane[] setOfPlayerPane = new Pane[10]; // limit player :10
    private static ObservableMap<Integer, Integer> scoreOfPlayer;
    private ObservableValue<Integer> playerReady; // number of player ready
    Label[] setOfScore = new Label[10];
    Label[] setOfNameBoard = new Label[10];
    Label[] setOfScoreBoard = new Label[10];
    int numBombLeft = 11;
    int score = 0;

    private String[] playerNames = new String[10];

    @FXML
    private TextArea txtArea;

    @FXML
    private ListView<String> listUsersConnected;

    private ObservableList<String> users;

    int[][] bombplacement = new int[6][6];
    int[][] bombaround = new int[6][6];

    int[][] bombplacementMultiPoints = new int[6][6];
    int[][] bombaroundMultiPoints = new int[6][6];

    // Server Configuration
    private boolean connected = ClientStartPageController.connected;
    private String username = ClientStartPageController.userName;

    // for I/O
    private ObjectInputStream sInput = ClientStartPageController.sInput; // to read from the socket
    private ObjectOutputStream sOutput = ClientStartPageController.sOutput; // to write on the socket
    private Socket socket = ClientStartPageController.socket;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        new ListenFromServer().start();

        Arrays.fill(playerNames, " ");

        // Poon
        // set podium image in scoreboard
        Image image = new Image(getClass().getResourceAsStream("/podium.png"));
        winnerImage.setImage(image);

        txtArea.setEditable(false);
        leftPane.setDisable(true);
        bombLeft.setVisible(false);
        bombLeftLabel.setVisible(false);
        showTime.setVisible(false);
        showTimeLabel.setVisible(false);

        display("Hello, " + username + ".\n");
        display("When you are ready to play, press Ready button\n");
        // trigger this when server press start
        setUpLeftPane();
        // setScore();
        // color change for the starting player
        // setOfPlayerPane[player].setStyle("-fx-background-color: grey");
    }

    // Poon
    // method to reset all score to 0
    private void resetScore() {
        for (int i = 0; i < 10; i++) {
            scoreOfPlayer.put(i, 0);
            setOfScore[i].setText("0");
        }
    }

    // Tram and Poon
    private void setUpLeftPane() {

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

        // hide players by default
        for (int i = 0; i < 10; i++) {
            setOfPlayerPane[i].setVisible(false);
        }

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

    // Poon
    // call to change color to inform the next player that it's their turn
    // Inkz
    // add score
    void colorChange(int score) {

        int prev, newScore, next;
        String nameToCompare;

        if (playerplaying < numOfPlayer) {
            prev = --playerplaying;
            newScore = Integer.parseInt(setOfScore[prev].getText()) + score;
            setOfPlayerPane[prev].setStyle("-fx-background-color: transparent");
            setOfScore[prev].setText(newScore + "");
            next = ++playerplaying;
            setOfPlayerPane[next].setStyle("-fx-background-color: #484c4a");
//			nameToCompare = setOfPlayerPane[prev].getChildren().get(0).toString().substring(setOfPlayerPane[prev].getChildren().get(0).toString().indexOf("'")+1, setOfPlayerPane[prev].getChildren().get(0).toString().lastIndexOf("'"));
//          nameToCompare = setOfPlayerPane[prev].getChildren().get(prev).toString().substring(setOfPlayerPane[prev].getChildren().get(prev).toString().indexOf("'")+1, setOfPlayerPane[prev].getChildren().get(prev).toString().lastIndexOf("'"));

            //TODO --- get name that is currently have background color to nameToCompare plz
            nameToCompare = "";


            // Enable leftPane if username = nameToCompare
//			if(nameToCompare.equals(username))
//				leftPane.setDisable(false);
//			else
//				leftPane.setDisable(true);

            playerplaying++;
        } else if (playerplaying == numOfPlayer) {
            prev = numOfPlayer - 1;
            newScore = Integer.parseInt(setOfScore[prev].getText()) + score;
            setOfPlayerPane[prev].setStyle("-fx-background-color: transparent");
            setOfScore[prev].setText(newScore + "");
            setOfPlayerPane[0].setStyle("-fx-background-color: #484c4a");
//			nameToCompare = setOfPlayerPane[0].getChildren().get(0).toString().substring(setOfPlayerPane[0].getChildren().get(0).toString().indexOf("'")+1, setOfPlayerPane[prev].getChildren().get(0).toString().lastIndexOf("'"));

            //TODO --- get name that is currently have background color to nameToCompare plz
            nameToCompare = "get name that is currently have background color plz";

            // Enable leftPane if username = nameToCompare
//			if(nameToCompare.equals(username))
//				leftPane.setDisable(false);
//			else
//				leftPane.setDisable(true);

            playerplaying = 1;
        } else {
            playerplaying = 1;
        }


    }

    // Tram
    // playing (Button Clicked)
    @FXML
    void play(MouseEvent event) {

        // send button position to server
        sendButtonPosition(event.toString().substring(32, 34).trim());

        //Update client game page
        //Reveal if free slot or bomb
        Button y = (Button) event.getTarget();

        if (y.getStyle() == "-fx-font-size: 0.0") {// free slot
            // ((Button) event.getTarget()).setStyle("-fx-font-size: 10");
            ((Button) event.getTarget()).setStyle("-fx-text-fill: #ffffff ; -fx-background-color:#2B2D42;");
            ((Button) event.getTarget()).setDisable(true);
            player++;
            colorChange(0);
        }

        if (y.getStyle() == "-fx-font-size: 0.1") {// bomb
            // might need to getStyle().removeAll() before do this to prevent bugs
            ((Button) event.getTarget())
                    .setStyle("-fx-background-color: #D90429; -fx-text-fill: #ffffff ; -fx-font-size: 10;");
            ((Button) event.getTarget()).setText("BOMB");
            ((Button) event.getTarget()).setDisable(true);
            numBombLeft--;
            bombLeft.setText(numBombLeft + "");
            //
            // int score = scoreOfPlayer.get(player);
            // scoreOfPlayer.put(player, score++);
            //
            // setOfScore[player].setText(score + "");
            score++;
            player++;
            colorChange(1);
        }

        // Tram
        if (y.getStyle() == "-fx-font-size: 0.2") {// bomb (2 points)
            ((Button) event.getTarget())
                    .setStyle("-fx-font-size: 5;-fx-background-color:#D90429;-fx-text-fill: #edf2f4");
            ((Button) event.getTarget()).setDisable(true);
            ((Button) event.getTarget()).setText("BOMB \n x2");
            numBombLeft--;
            bombLeft.setText(numBombLeft + "");
            colorChange(2);
        }

        if (y.getStyle() == "-fx-font-size: 0.3") {// bomb (3 points)
            ((Button) event.getTarget())
                    .setStyle("-fx-font-size: 5;-fx-background-color:#D90429;-fx-text-fill: #edf2f4");
            ((Button) event.getTarget()).setDisable(true);
            ((Button) event.getTarget()).setText("BOMB \n x3");
            numBombLeft--;
            bombLeft.setText(numBombLeft + "");
            colorChange(3);
        }

        if (y.getStyle() == "-fx-font-size: 0.4") {// bomb (4 points)
            ((Button) event.getTarget())
                    .setStyle("-fx-font-size: 5;-fx-background-color:#D90429;-fx-text-fill: #edf2f4");
            ((Button) event.getTarget()).setDisable(true);
            ((Button) event.getTarget()).setText("BOMB \n x4");
            numBombLeft--;
            bombLeft.setText(numBombLeft + "");
            colorChange(4);
        }

        // timer of next player
        resetTimer();
        startTimer();
    }

    // receive button position clicked from other clients
    void playFromOthers(String cl) {

        // Inkz
        String s = cl.trim();
        int j = 0;
        int i = (Integer.parseInt(s.charAt(0) + "")) - 1;
        if (s.length() >= 2) {
            if (!(s.substring(1).equals(","))) {
                j = Integer.parseInt(s.charAt(1) + "");
            }
        }

        // Tram
        // To check button position
        // display("i = " + i + " j = " + j);
        Button y = setOfButton[i][j];

        if (y.getStyle() == "-fx-font-size: 0.0") {// free slot
            y.setStyle("-fx-font-size: 10;-fx-background-color:#2B2D42; -fx-text-fill: #edf2f4");
            y.setDisable(true);
            colorChange(0);
        }

        if (y.getStyle() == "-fx-font-size: 0.1") {// bomb
            y.setStyle("-fx-font-size: 10;-fx-background-color:#D90429;-fx-text-fill: #edf2f4");
            y.setDisable(true);
            y.setText("BOMB");
            numBombLeft--;
            bombLeft.setText(numBombLeft + "");
            colorChange(1);
        }

        if (y.getStyle() == "-fx-font-size: 0.2") {// bomb
            y.setStyle("-fx-font-size: 5;-fx-background-color:#D90429;-fx-text-fill: #edf2f4");
            y.setDisable(true);
            y.setText("BOMB \n x2");
            numBombLeft--;
            bombLeft.setText(numBombLeft + "");
            colorChange(2);
        }

        if (y.getStyle() == "-fx-font-size: 0.3") {// bomb
            y.setStyle("-fx-font-size: 5;-fx-background-color:#D90429;-fx-text-fill: #edf2f4");
            y.setDisable(true);
            y.setText("BOMB \n x3");
            numBombLeft--;
            bombLeft.setText(numBombLeft + "");
            colorChange(3);
        }

        if (y.getStyle() == "-fx-font-size: 0.4") {// bomb
            y.setStyle("-fx-font-size: 5;-fx-background-color:#D90429;-fx-text-fill: #edf2f4");
            y.setDisable(true);
            y.setText("BOMB \n x4");
            numBombLeft--;
            bombLeft.setText(numBombLeft + "");
            colorChange(4);
        }

        resetTimer();
        startTimer();
//		resetScore();

    }

    // Tram
    Timer timer;
    //= new Timer();// tram
    int time;
    int maxTime;
    TimerTask task;


    // (Poon) fix bugs to run timer, please call resetTimer() before this method or declare maxTime = getTimerMode(); time = getTimerMode();
    void startTimer() {
        showTime.setStyle("-fx-text-fill: #edf2f4");

        timer = new Timer();

        task = new TimerTask() {

            @Override
            public void run() {
                if (maxTime > 0) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(time == 11)
                                showTime.setText("10");
                            else
                            showTime.setText(time + "");
                            if (time < 4) {
                                showTime.setStyle("-fx-text-fill: #EF233C");
                            }
                        }
                    });
//					System.out.println("Seconds = " + time);
                    time--;
                    maxTime--;
                } else {
                    resetTimer();
                    colorChange(0);
                    startTimer();
                }

            }

        };

        timer.schedule(task, 0,1000);
    }

    // (Poon) pause timer at the current time
    private void stopTimer() {
//        maxTime = 0;
//        showTime.setText(time + "");
        task.cancel();
        this.timer.cancel();
    }

    // (Poon) reset values to prepare for startTimer()
    private void resetTimer() {
        maxTime = getTimerMode();
        time = getTimerMode();
        stopTimer();
    }

    // Poon
    // default currentMode
    // please setGameMode() before server press start button
    String currentMode = "";

    // get the maxTime for startTimer method ( 5 or 10 )
    private int getTimerMode() {
        if (currentMode.equals("Quick Game")) {
            return 6;
        } else {
            return 11;
        }
    }

    // Poon
    private void setGameMode(String mode) {
        currentMode = mode;
    }

    /*
     * // to display count down game timer void startTimer() { // timer run
     * Task<Void> task = new Task<Void>() {
     *
     * @Override public Void call() throws InterruptedException { for (int i = 10; i
     * >= 0; i--) { // exit timer if (false) { // to stop timer return null; }
     * updateMessage(i + ""); Thread.sleep(1000); } return null; } }; // exit
     * timer(); if (false) { // to stop timer return; }
     * showTime.textProperty().bind(task.messageProperty()); task.setOnSucceeded(e
     * -> { showTime.textProperty().unbind(); showTime.setText("0"); // skip this
     * player when timeout player++; if (player == numOfPlayer) { player = 0; }
     * colorChange(); // set condition back to default
     *
     * // startTimer after timeout startTimer(); });
     *
     * Thread thread = new Thread(task); thread.setDaemon(true); thread.start(); }
     */

    @FXML
    private Button buttonDone;

    // Poon
    @FXML
    void goBack(ActionEvent event) throws IOException {
        scoreboardPane.setVisible(false);
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
            readyButton.setText("NOT READY");

        } else {
            /* if (alreadyReady) { */
            // set ready button to disable after being pressed
            sendNotReady();
            readyButton.setText("READY");
        }

        alreadyReady = !alreadyReady;

    }

    // Poon
    Integer[] nameOfPlayer = new Integer[10];
    private static Map<Integer, Integer> sorted = new Hashtable<Integer, Integer>();

    // Poon
    // sort score
    public static Map<Integer, Integer> getSorted() {
        sorted = sort(scoreOfPlayer);
        System.out.print(sorted);
        return sorted;
    }

    // Poon
    // remove non-playing players from sorted arraylist
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

    /*
     * public void login() { // test if we can start the connection to the Server //
     * if it failed nothing we can do if (!startConnection()) return; connected =
     * true; }
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
        triggerClientScreen("", "");
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

            // server
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

            // Multi points bomb

            try {
                bombplacementMultiPoints = (int[][]) sInput.readObject();
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
                bombaroundMultiPoints = (int[][]) sInput.readObject();

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

            while (true) {
                try {
                    msgt = (String) sInput.readObject();
                    String msg = msgt.trim();
                    if (msg.contains("WHOISIN") || msg.contains("REMOVE") || msg.contains("READDY")
                            || msg.contains("NOTREADY") || msg.contains("GAMESTART") || msg.contains("GAMESTOP")
                            || msg.contains("GAMERESET")) {
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
                                users.add(split[0] + " (READY)");
                            });
                        } else if (split[1].equals("NOTREADY")) {
                            Platform.runLater(() -> {
                                users.remove(split[0] + " (READY)");
                                users.add(split[0]);
                            });

                        } else if (split[1].equals("GAMESTART")) {
                            switch (split[0]) {
                                case "DEFAULT":
                                    Platform.runLater(() -> {
                                        // Game started
                                        // Do things for default mode
                                        display("Welcome to Find My Mines");
                                        display("Server has started the game (Mode: Default)" + "\n");
                                        triggerClientScreen("ONGOING", "DEFAULT");
                                    });
                                    break;
                                case "QUICK_GAME":
                                    Platform.runLater(() -> {
                                        // Game started
                                        // Do things for quick game mode
                                        display("Welcome to Find My Mines");
                                        display("Server has started the game (Mode: Quick Game)" + "\n");
                                        triggerClientScreen("ONGOING", "QUICK_GAME");
                                    });
                                    break;
                                case "MULTIPOINTS_BOMB":
                                    Platform.runLater(() -> {
                                        // Game started
                                        // Do things for multipoints bomb mode
                                        display("Welcome to Find My Mines");
                                        display("Server has started the game (Mode: Multipoints Bomb)" + "\n");
                                        triggerClientScreen("ONGOING", "MULTIPOINTS_BOMB");
                                    });
                                    break;
                            }
                        } else if (split[1].equals("GAMESTOP")) {
                            Platform.runLater(() -> {
                                // Game ended
                                // Do things when game ends
                                display("Game Over!");
                                display("Server has stopped the game" + "\n");
                                triggerClientScreen("ENDED", null);

                            });
                        } else if (split[1].equals("GAMERESET")) {
                            Platform.runLater(() -> {
                                // Game reset
                                // Revert things back to start
                                display("Server has reset the game" + "\n");
                                triggerClientScreen("WAITING", null);
                            });
                        }
                    } // Button Clicked
                    else if (msg.length() == 2) {
                        Platform.runLater(() -> {
                            playFromOthers(msg);
                        });
                    }
                } catch (IOException e) {
                    display("Server has closed the connection");
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
        // numOfPlayer = users.size(); // how many clients are there

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

    private void setUpBombMultiPoints() {
        // numOfPlayer = users.size(); // how many clients are there

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int result = bombplacementMultiPoints[i][j];
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
                int numOfBombAround = bombaroundMultiPoints[i][j];
                if (numOfBombAround > 0) {
                    setOfButton[i][j].setText("" + numOfBombAround);

                }
            }
        }

    }

    private void sendButtonPosition(String pos) {
        if (connected) {
            ButtonClick msg = new ButtonClick(ButtonClick.CLICK, pos);
            try {
                sOutput.writeObject(msg);
            } catch (IOException e) {
                display("Exception writing to server: " + e);
            }
        }
    }

    private void sendReady() {
        if (connected) {
            ButtonClick msg = new ButtonClick(ButtonClick.READDY, username);
            try {
                sOutput.writeObject(msg);
            } catch (IOException e) {
                display("Exception writing to server: " + e);
            }
        }
    }

    private void sendNotReady() {
        if (connected) {
            ButtonClick msg = new ButtonClick(ButtonClick.NOTREADY, username);
            try {
                sOutput.writeObject(msg);
            } catch (IOException e) {
                display("Exception writing to server: " + e);
            }
        }
    }

    // (Queenie) method to control how client screen behavior should be when a game
    // state changes
    private void triggerClientScreen(String game_state, String game_mode) {
        switch (game_state) {
            case "WAITING":
                // Server pressed "Reset", changing game state from "ENDED" -> "WAITING"
                // reset everything on client screen
                System.out.println("(triggerClientScreen()) Server has reset the game");
                resetScore();
                setUpLeftPane();
                resetAllBombButtons();
                resetTimer();
                leftPane.setDisable(true);
                readyButton.setDisable(false);
                // hiding bomb and time information until game starts
                bombLeft.setVisible(false);
                bombLeftLabel.setVisible(false);
                showTime.setVisible(false);
                showTimeLabel.setVisible(false);
                // To be implemented: request server to call assignBomb() and
                // assignBombMultiPoints()
                break;
            case "ONGOING":
                // Server pressed "Start", changing game state from "WAITING" -> "ONGOING"
                System.out.println("(triggerClientScreen()) Server has started the game");
                resetReadyButton();
                setPlayerPane();
                leftPane.setDisable(false);
                // game starts, time and bombs remaining become visible
                bombLeft.setVisible(true);
                bombLeftLabel.setVisible(true);
                showTime.setVisible(true);
                showTimeLabel.setVisible(true);

                startWithGameMode(game_mode);
                break;
            case "ENDED":
                // Server pressed "Stop", changing game state from "ONGOING" -> "ENDED"
                System.out.println("(triggerClientScreen()) Server has stopped the game");
                leftPane.setDisable(true);
                stopTimer();
                resetTimer();
                setScoreboardPane(); // set panes in scoreboard
                showScoreSummary();
                break;
            default:
                // for when server shuts down
                leftPane.setDisable(true);
                readyButton.setDisable(true);
                bombLeft.setVisible(false);
                bombLeftLabel.setVisible(false);
                showTime.setVisible(false);
                showTimeLabel.setVisible(false);
                break;

        }
        return;
    }

    // (Queenie) method to control client screen for game_state = ONGOING with
    // specified game_mode
    private void startWithGameMode(String game_mode) {
        switch (game_mode) {
            case "DEFAULT":
                // need receive bomb information from server first before setting up bombs on
                // client page
                setUpBomb();
                break;
            case "QUICK_GAME":
                setGameMode("Quick Game");
                // need receive bomb information from server first before setting up bombs on
                // client page
                setUpBomb();
                break;
            case "MULTIPOINTS_BOMB":
                // need receive bomb information from server first before setting up bombs on
                // client page
                setUpBombMultiPoints();
                break;
        }

        maxTime = getTimerMode();
        time = getTimerMode();
        startTimer();
        setOfPlayerPane[0].setStyle("-fx-background-color: #484c4a");

        return;
    }

    private void showScoreSummary() {
        System.out.println("showScoreSummary() is called");
        scoreboardPane.setVisible(true);
    }

    // (Poon) setup scores and player names on scoreboard
    private void setScoreboardPane() {
        numOfPlayer = users.size();

        player11.setText(playerNames[0]);
        player21.setText(playerNames[1]);
        player31.setText(playerNames[2]);
        player41.setText(playerNames[3]);
        player51.setText(playerNames[4]);
        player61.setText(playerNames[5]);
        player71.setText(playerNames[6]);
        player81.setText(playerNames[7]);
        player91.setText(playerNames[8]);
        player101.setText(playerNames[9]);

        // display user names
        Pane[] setOfScoreboardPane = new Pane[10];
        setOfScoreboardPane[0] = player1Pane1;
        setOfScoreboardPane[1] = player2Pane1;
        setOfScoreboardPane[2] = player3Pane1;
        setOfScoreboardPane[3] = player4Pane1;
        setOfScoreboardPane[4] = player5Pane1;
        setOfScoreboardPane[5] = player6Pane1;
        setOfScoreboardPane[6] = player7Pane1;
        setOfScoreboardPane[7] = player8Pane1;
        setOfScoreboardPane[8] = player9Pane1;
        setOfScoreboardPane[9] = player10Pane1;
        
        for (int i = 0; i < numOfPlayer; i++) {
            setOfScoreboardPane[i].setVisible(true);
        }

        Label[] setOfScoreboardScore = new Label[10];
        setOfScoreboardScore[0] = score11;
        setOfScoreboardScore[1] = score21;
        setOfScoreboardScore[2] = score31;
        setOfScoreboardScore[3] = score41;
        setOfScoreboardScore[4] = score51;
        setOfScoreboardScore[5] = score61;
        setOfScoreboardScore[6] = score71;
        setOfScoreboardScore[7] = score81;
        setOfScoreboardScore[8] = score91;
        setOfScoreboardScore[9] = score101;

        // Setup scores on scoreboard
        // currently not working
        for (int i = 0; i < numOfPlayer; i++) {
            setOfScoreboardScore[i].setText(sorted.get(i) + "");
        }

        // setText for score board
        /*
         * int i = 0; for (Map.Entry<Integer, Integer> entry : sorted.entrySet()) { if
         * (i < numOfPlayer) { setOfScore[i].setText(entry.getValue() + ""); i++; } }
         */

    }
    //(Owner unknown - If this is your method put your name here)
    //Method to set up the player pane (middle of client game page) with current players name
    //put score labels into an array of Label
    private void setPlayerPane() {

        numOfPlayer = users.size();

        for (int i = 0; i < numOfPlayer; i++) {
            playerNames[i] = users.get(i).substring(0, users.get(i).indexOf("("));
        }

        player1.setText(playerNames[0]);
        player2.setText(playerNames[1]);
        player3.setText(playerNames[2]);
        player4.setText(playerNames[3]);
        player5.setText(playerNames[4]);
        player6.setText(playerNames[5]);
        player7.setText(playerNames[6]);
        player8.setText(playerNames[7]);
        player9.setText(playerNames[8]);
        player10.setText(playerNames[9]);

        // display users name
        for (int i = 0; i < numOfPlayer; i++) {
            setOfPlayerPane[i].setVisible(true);
        }
        //setOfScore is an array of Labels
        //score1-10 is the label for displaying score
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

    }

    // (Queenie) Method to reset all ready status from players, reset ready button and disable it
    private void resetReadyButton() {
        readyButton.setText("READY");
        alreadyReady = false;
        sendNotReady();
        readyButton.setDisable(true);
    }
    //(Queenie) Method to reset all bomb buttons to default for next game
    private void resetAllBombButtons(){
        //Set all bomb buttons back default (enabled and visible)
        //Set number of bombs back to default
        numBombLeft=11;
        bombLeft.setText("11");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                Button y = setOfButton[i][j];
                y.setDisable(false);
                y.setStyle(null);
            }
        }
    }

}