package game.controller;

import static java.util.stream.Collectors.toMap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.time.LocalTime;
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
    private Pane playerPane_player1Pane;

    @FXML
    private Label playerPane_player1Label;

    @FXML
    private Label playerPane_scoreLabel1;

    @FXML
    private Pane playerPane_player2Pane;

    @FXML
    private Label playerPane_player2Label;

    @FXML
    private Label playerPane_scoreLabel2;

    @FXML
    private Pane playerPane_player3Pane;

    @FXML
    private Label playerPane_player3Label;

    @FXML
    private Label playerPane_scoreLabel3;

    @FXML
    private Pane playerPane_player4Pane;

    @FXML
    private Label playerPane_player4Label;

    @FXML
    private Label playerPane_scoreLabel4;

    @FXML
    private Pane playerPane_player5Pane;

    @FXML
    private Label playerPane_player5Label;

    @FXML
    private Label playerPane_scoreLabel5;

    @FXML
    private Pane playerPane_player6Pane;

    @FXML
    private Label playerPane_player6Label;

    @FXML
    private Label playerPane_scoreLabel6;

    @FXML
    private Pane playerPane_player7Pane;

    @FXML
    private Label playerPane_player7Label;

    @FXML
    private Label playerPane_scoreLabel7;

    @FXML
    private Pane playerPane_player8Pane;

    @FXML
    private Label playerPane_player8Label;

    @FXML
    private Label playerPane_scoreLabel8;

    @FXML
    private Pane playerPane_player9Pane;

    @FXML
    private Label playerPane_player9Label;

    @FXML
    private Label playerPane_scoreLabel9;

    @FXML
    private Pane playerPane_player10Pane;

    @FXML
    private Label playerPane_player10Label;

    @FXML
    private Label playerPane_scoreLabel10;

    @FXML
    private Label bombLeftLabel;

    @FXML
    private Label bombLeft;

    @FXML
    private Button readyButton;

    // for score summary
    @FXML
    private Pane scoreSummary_player1Pane;

    @FXML
    private Label scoreSummary_playerNameLabel1;

    @FXML
    private Label scoreSummary_scoreLabel1;

    @FXML
    private Pane scoreSummary_player2Pane;

    @FXML
    private Label scoreSummary_playerNameLabel2;

    @FXML
    private Label scoreSummary_scoreLabel2;

    @FXML
    private Pane scoreSummary_player3Pane;

    @FXML
    private Label scoreSummary_playerNameLabel3;

    @FXML
    private Label scoreSummary_scoreLabel3;

    @FXML
    private Pane scoreSummary_player4Pane;

    @FXML
    private Label scoreSummary_playerNameLabel4;

    @FXML
    private Label scoreSummary_scoreLabel4;

    @FXML
    private Pane scoreSummary_player5Pane;

    @FXML
    private Label scoreSummary_playerNameLabel5;

    @FXML
    private Label scoreSummary_scoreLabel5;

    @FXML
    private Pane scoreSummary_player6Pane;

    @FXML
    private Label scoreSummary_playerNameLabel6;

    @FXML
    private Label scoreSummary_scoreLabel6;

    @FXML
    private Pane scoreSummary_player7Pane;

    @FXML
    private Label scoreSummary_playerNameLabel7;

    @FXML
    private Label scoreSummary_scoreLabel7;

    @FXML
    private Pane scoreSummary_player8Pane;

    @FXML
    private Label scoreSummary_playerNameLabel8;

    @FXML
    private Label scoreSummary_scoreLabel8;

    @FXML
    private Pane scoreSummary_player9Pane;

    @FXML
    private Label scoreSummary_playerNameLabel9;

    @FXML
    private Label scoreSummary_scoreLabel9;

    @FXML
    private Pane scoreSummary_player10Pane;

    @FXML
    private Label scoreSummary_playerNameLabel10;

    @FXML
    private Label scoreSummary_scoreLabel10;

    @FXML
    private AnchorPane leftPane;

    @FXML
    private AnchorPane rightPane;

    @FXML
    private Label title;

    @FXML
    private ImageView winnerImage;

    @FXML
    private AnchorPane scoreSummaryPane;

    @FXML
    private Button button_done;

    @FXML
    private DialogPane scoreBoard;

    @FXML
    private AnchorPane buttonPane;

    @FXML
    private ImageView backImage;

    @FXML
    private Label welcomeLabel;

    /*READ ME
     * left pane - whole left side of the page
     * player pane - middle box on the client game page
     * score summary - popup displayed only when game ends
     * */

    // game
    static int numOfPlayer; // how many player
    Button[][] setOfButton = new Button[6][6];
    private static ObservableMap<Integer, Integer> scoreOfPlayer;
    private ObservableValue<Integer> playerReady; // number of player ready
    Pane[] setOfPlayerPane_playerPane = new Pane[10]; // limit player :10
    Label[] setOfPlayerPane_scoreLabel = new Label[10]; //contains Label playerPane_scoreLabel1, ... ,playerPane_scoreLabel10
    Pane[] setOfScoreSummary_playerPane = new Pane[10];
    Label[] setOfScoreSummary_playerNameLabel = new Label[10];
    Label[] setOfScoreSummary_scoreLabel = new Label[10];
    int numBombLeft = 11;
    int score = 0; //score per 1 click
    private int playerplaying;

    //First player randomization
    String firstPlayerName;
    String nameToCompare;

    private String[] playerNames = new String[10];

    @FXML
    private TextArea txtArea;

    @FXML
    private ListView<String> listUsersConnected; //list on the right of the client game page

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
        System.out.println("initialize()");

        new ListenFromServer().start();

        //fill a space as all players' names
        Arrays.fill(playerNames, " ");

        // Poon
        // set podium image in scoreSummary
        Image image = new Image(getClass().getResourceAsStream("/podium.png"));
        winnerImage.setImage(image);
        Image image1 = new Image(getClass().getResourceAsStream("/bomb.png"));
        backImage.setImage(image1);
        welcomeLabel.setText("Welcome " + username.toUpperCase() + " !");

        txtArea.setEditable(false);
        buttonPane.setDisable(true);
        System.out.println("    (1)buttonPane disabled");
        bombLeft.setVisible(false);
        bombLeftLabel.setVisible(false);
        showTime.setVisible(false);
        showTimeLabel.setVisible(false);

        System.out.println("Hello, "+username+".\n");
        display("Hello, " + username + ".\n");
        display("When you are ready to play, press Ready button\n");
        // trigger this when server press start
        setUpLeftPane();
        //setUpPlayerPane();
        //setUpScoreSummaryPane();
        // setScore();
        // color change for the starting player
        // setOfPlayerPane_playerPane[player].setStyle("-fx-background-color: grey");
    }

    // Poon
    // method to reset all score to 0
    private void resetScore() {
        System.out.println("resetScore()");
        for (int i = 0; i < 10; i++) {
            scoreOfPlayer.put(i, 0);
            setOfPlayerPane_scoreLabel[i].setText("0");
        }
    }

    // Tram and Poon
    private void setUpLeftPane() {
        System.out.println("setUpLeftPane()");

        setOfPlayerPane_playerPane[0] = playerPane_player1Pane;
        setOfPlayerPane_playerPane[1] = playerPane_player2Pane;
        setOfPlayerPane_playerPane[2] = playerPane_player3Pane;
        setOfPlayerPane_playerPane[3] = playerPane_player4Pane;
        setOfPlayerPane_playerPane[4] = playerPane_player5Pane;
        setOfPlayerPane_playerPane[5] = playerPane_player6Pane;
        setOfPlayerPane_playerPane[6] = playerPane_player7Pane;
        setOfPlayerPane_playerPane[7] = playerPane_player8Pane;
        setOfPlayerPane_playerPane[8] = playerPane_player9Pane;
        setOfPlayerPane_playerPane[9] = playerPane_player10Pane;

        // hide players pane by default
        for (int i = 0; i < 10; i++) {
            setOfPlayerPane_playerPane[i].setVisible(false);
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

    }

    // Poon
    // call to change color to inform the next player that it's their turn
    // Inkz
    // add score
    void colorChange(int score) {

        int prev, newScore, next;

        if (playerplaying < numOfPlayer) {
            System.out.println(">>>colorChange(" + score + ") case 1 playerplaying("+playerplaying+") < numOfPlayer("+numOfPlayer+")");
            System.out.println("playerplaying=" + playerplaying);
            System.out.println("numOfPlayer=" + numOfPlayer);
            //if the player playing is not the last one
            //update the score and color of the player who played previously
            //then update the color of current player

            //get the previous player
            prev = --playerplaying;
            System.out.println("prev=" + prev);

            //original score + new score
            newScore = Integer.parseInt(setOfPlayerPane_scoreLabel[prev].getText()) + score;
            System.out.println("newScore=" + newScore);

            //remove the color of previous player and update their score both in the playerPane and scoreSummary
            setOfPlayerPane_playerPane[prev].setStyle("-fx-background-color: transparent");
            setOfPlayerPane_scoreLabel[prev].setText(newScore + "");

            //System.out.println("setOfScoreSummary_scoreLabel[prev].getText()="+setOfScoreSummary_scoreLabel[prev].getText());
            setOfScoreSummary_scoreLabel[prev].setText(newScore + "");
            //System.out.println("setOfScoreSummary_scoreLabel[prev].getText()="+setOfScoreSummary_scoreLabel[prev].getText());
            System.out.println(">score setting finished");

            //get the current player and update their color
            next = ++playerplaying;
            setOfPlayerPane_playerPane[next].setStyle("-fx-background-color: #484c4a");
            System.out.println("*"+playerNames[next]+"'s TURN*");
            display("*"+playerNames[next]+"'s TURN*");

            nameToCompare = playerNames[next];

            // Enable leftPane if username = nameToCompare
            if (nameToCompare.trim().equals(ClientStartPageController.userName)) {
                buttonPane.setDisable(false);
                System.out.println("    (1)buttonPane enabled");
            } else {
                buttonPane.setDisable(true);
                System.out.println("    (2)buttonPane disabled");
            }

            //move on to the next player
            playerplaying++;

        } else if (playerplaying == numOfPlayer) {
            System.out.println(">>>colorChange(" + score + ") case 2 playerplaying("+playerplaying+") == numOfPlayer("+numOfPlayer+")");
            System.out.println("playerplaying=" + playerplaying);
            System.out.println("numOfPlayer=" + numOfPlayer);
            //if the player playing is the last one or the only one

            //previous player is the one before last or this current player (if there is only 1 player)
            prev = numOfPlayer - 1;
            System.out.println("prev=" + prev);

            //original score + new score
            newScore = Integer.parseInt(setOfPlayerPane_scoreLabel[prev].getText()) + score;
            System.out.println("newScore=" + newScore);

            //remove the color of previous player and update their score both in the playerPane and scoreSummary
            setOfPlayerPane_playerPane[prev].setStyle("-fx-background-color: transparent");
            setOfPlayerPane_scoreLabel[prev].setText(newScore + "");
            System.out.println("setOfScoreSummary_scoreLabel[prev].getText()=" + setOfScoreSummary_scoreLabel[prev].getText());
            setOfScoreSummary_scoreLabel[prev].setText(newScore + "");
            System.out.println("setOfScoreSummary_scoreLabel[prev].getText()=" + setOfScoreSummary_scoreLabel[prev].getText());
            System.out.println(">score setting finished");

            //since the next player wraps around back to the 1st one, update their color
            setOfPlayerPane_playerPane[0].setStyle("-fx-background-color: #484c4a");
            System.out.println("*"+playerNames[0]+"'s TURN*");
            display("*"+playerNames[0]+"'s TURN*");

            nameToCompare = playerNames[0];

            // Enable leftPane if username = nameToCompare
            if (nameToCompare.trim().equals(ClientStartPageController.userName)) {
                buttonPane.setDisable(false);
                System.out.println("    (2)buttonPane enabled");
            } else {
                buttonPane.setDisable(true);
                System.out.println("    (3)buttonPane disabled");
            }

            //next player is wrapped around back to 1st player
            playerplaying = 1;
        } else {
            System.out.println("colorChange(" + score + ") case 3 playerplaying > numOfPlayer");
            //if player playing exceeds number of player then we wrap around back to 1st player
            playerplaying = 1;
        }


    }

    // Tram
    // playing (Button Clicked)
    @FXML
    void play(MouseEvent event) {
        System.out.println("play()");

        // send button position to server
        sendButtonPosition(event.toString().substring(32, 34).trim());

        //Update client game page
        //Reveal if free slot or bomb
        Button y = (Button) event.getTarget();
        System.out.println(">>>" + username + " clicked " + y.getId());

        if (y.getStyle() == "-fx-font-size: 0.0") {// free slot
            // ((Button) event.getTarget()).setStyle("-fx-font-size: 10");
            ((Button) event.getTarget()).setStyle("-fx-text-fill: #ffffff ; -fx-background-color:#2B2D42;");
            ((Button) event.getTarget()).setDisable(true);
            colorChange(0);
        } else if (y.getStyle() == "-fx-font-size: 0.1") {// bomb
            // might need to getStyle().removeAll() before do this to prevent bugs
            ((Button) event.getTarget())
                    .setStyle("-fx-background-color: #D90429; -fx-text-fill: #ffffff ; -fx-font-size: 10;");
            ((Button) event.getTarget()).setText("BOMB");
            ((Button) event.getTarget()).setDisable(true);
            numBombLeft--;
            bombLeft.setText(numBombLeft + "");
            //not needed if set score using colorChange()
            // int score = scoreOfPlayer.get(player);
            // scoreOfPlayer.put(player, score++);
            //
            // setOfPlayerPane_scoreLabel[player].setText(score + "");
            score++;
            colorChange(1);
        }

        // Tram
        else if (y.getStyle() == "-fx-font-size: 0.2") {// bomb (2 points)
            ((Button) event.getTarget())
                    .setStyle("-fx-font-size: 10;-fx-background-color:#D90429;-fx-text-fill: #edf2f4");
            ((Button) event.getTarget()).setDisable(true);
            ((Button) event.getTarget()).setText("BOMB \n x2");
            numBombLeft--;
            bombLeft.setText(numBombLeft + "");
            colorChange(2);
        } else if (y.getStyle() == "-fx-font-size: 0.3") {// bomb (3 points)
            ((Button) event.getTarget())
                    .setStyle("-fx-font-size: 10;-fx-background-color:#D90429;-fx-text-fill: #edf2f4");
            ((Button) event.getTarget()).setDisable(true);
            ((Button) event.getTarget()).setText("BOMB \n x3");
            numBombLeft--;
            bombLeft.setText(numBombLeft + "");
            colorChange(3);
        } else if (y.getStyle() == "-fx-font-size: 0.4") {// bomb (4 points)
            ((Button) event.getTarget())
                    .setStyle("-fx-font-size: 10;-fx-background-color:#D90429;-fx-text-fill: #edf2f4");
            ((Button) event.getTarget()).setDisable(true);
            ((Button) event.getTarget()).setText("BOMB \n x4");
            numBombLeft--;
            bombLeft.setText(numBombLeft + "");
            colorChange(4);
        }

        // currently using colorChange() to move to next player
//        player++;
//		if (player == numOfPlayer) {
//			player = 0;
//		}

        if (numBombLeft == 0)
            sendTriggerEnd();

        // timer of next player
        resetTimer();
        startTimer();
//		resetScore();

    }

    // receive button position clicked from other clients
    void playFromOthers(String cl) {
        System.out.println("playFromOthers()");
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
            y.setStyle("-fx-font-size: 10;-fx-background-color:#D90429;-fx-text-fill: #edf2f4");
            y.setDisable(true);
            y.setText("BOMB \n x2");
            numBombLeft--;
            bombLeft.setText(numBombLeft + "");
            colorChange(2);
        }

        if (y.getStyle() == "-fx-font-size: 0.3") {// bomb
            y.setStyle("-fx-font-size: 10;-fx-background-color:#D90429;-fx-text-fill: #edf2f4");
            y.setDisable(true);
            y.setText("BOMB \n x3");
            numBombLeft--;
            bombLeft.setText(numBombLeft + "");
            colorChange(3);
        }

        if (y.getStyle() == "-fx-font-size: 0.4") {// bomb
            y.setStyle("-fx-font-size: 10;-fx-background-color:#D90429;-fx-text-fill: #edf2f4");
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
        System.out.println("startTimer()");
        showTime.setStyle("-fx-text-fill: #edf2f4");

        timer = new Timer();

        task = new TimerTask() {

            @Override
            public void run() {
                if (maxTime > 0) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (time == 11)
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
                    System.out.println("Time's up");
                    resetTimer();
                    colorChange(0);
                    startTimer();
                }

            }

        };

        timer.schedule(task, 0, 1000);
    }

    // (Poon) pause timer at the current time
    private void stopTimer() {
        System.out.println("stopTimer()");
//        maxTime = 0;
//        showTime.setText(time + "");
        task.cancel();
        this.timer.cancel();
        System.out.println("Timer is stopped");
    }

    // (Poon) reset values to prepare for startTimer()
    private void resetTimer() {
        System.out.println("resetTimer()");
        maxTime = getTimerMode();
        time = getTimerMode();
        stopTimer();
        System.out.println("Timer is reset");
    }

    // Poon
    // default currentMode
    // please setGameMode() before server press start button
    String currentMode = "";

    // get the maxTime for startTimer method ( 5 or 10 )
    private int getTimerMode() {
        System.out.println("getTimerMode()");
        if (currentMode.equals("Quick Game")) {
            return 6;
        } else {
            return 11;
        }
    }

    // Poon
    private void setGameMode(String mode) {
        System.out.println("setGameMode("+mode+")");
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
        System.out.println("goBack()");
        scoreSummaryPane.setVisible(false);
    }

    boolean alreadyReady = false;

    @FXML
    void ready(ActionEvent event) throws IOException {
        System.out.println("ready()");

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
//    Integer[] nameOfPlayer = new Integer[10];
    private static Map<Integer, Integer> sorted = new Hashtable<Integer, Integer>();

    //
//    // Poon
//    // sort score
    public static Map<Integer, Integer> getSorted() {
        sorted = sort(scoreOfPlayer);
        System.out.print(sorted);
        return sorted;
    }

    //(Queenie) sort scores in the scoreSummary
    private void sortScoreSummary() {
        //using selection sort
        System.out.println("sortScoreSummary()");
        System.out.println("setOfScoreSummary_scoreLabel.length=" + setOfScoreSummary_scoreLabel.length);
        numOfPlayer = users.size();
        System.out.println("numOfPlayer=" + numOfPlayer);
        for (int i = 0; i < numOfPlayer - 1; i++) {
            System.out.println("i=" + i);
            int index = i; //variable index is for lowest index containing least score
            for (int j = i + 1; j < numOfPlayer; j++) {
                System.out.println("j=" + j);
                if (Integer.parseInt(setOfScoreSummary_scoreLabel[j].getText()) > Integer.parseInt(setOfScoreSummary_scoreLabel[index].getText())) {
                    index = j;//searching for lowest index
                    System.out.println("index = j (" + index + " = " + j + ")");
                }
            }
            int biggerNumber = Integer.parseInt(setOfScoreSummary_scoreLabel[index].getText());
            String winner = setOfScoreSummary_playerNameLabel[index].getText();
            System.out.println("biggerNumber=" + biggerNumber);
            System.out.println("winner=" + winner);

            //swapping winner and winner's scores
            setOfScoreSummary_scoreLabel[index].setText(setOfScoreSummary_scoreLabel[i].getText());
            setOfScoreSummary_playerNameLabel[index].setText(setOfScoreSummary_playerNameLabel[i].getText());

            setOfScoreSummary_scoreLabel[i].setText(biggerNumber + "");
            setOfScoreSummary_playerNameLabel[i].setText(winner);
        }
    }

    //
//    // Poon
//    // remove non-playing players from sorted arraylist
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
            // game initialize
            scoreOfPlayer = FXCollections.observableHashMap();
            playerReady = new SimpleIntegerProperty(0).asObject();

            // server
            users = FXCollections.observableArrayList();

            listUsersConnected.setItems(users);

            try {
                bombplacement = (int[][]) sInput.readObject();
                System.out.println("Received bombplacement");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try {
                sInput = new ObjectInputStream(socket.getInputStream());
                System.out.println("    renewing ObjectInputStream");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                bombaround = (int[][]) sInput.readObject();
                System.out.println("received bombaround");

            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                sInput = new ObjectInputStream(socket.getInputStream());
                System.out.println("    renewing ObjectInputStream");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            // Multi points bomb

            try {
                bombplacementMultiPoints = (int[][]) sInput.readObject();
                System.out.println("Received bombplacementMultiPoints");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try {
                sInput = new ObjectInputStream(socket.getInputStream());
                System.out.println("    renewing ObjectInputStream");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                bombaroundMultiPoints = (int[][]) sInput.readObject();
                System.out.println("Received bombaroundMultiPoints");

            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try {
                sInput = new ObjectInputStream(socket.getInputStream());
                System.out.println("    renewing ObjectInputStream");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            // for checking values
//            display(bombplacement[0][0] + " = bombplacement [0][0]");
//            display(bombplacement[1][4] + " = bombplacement [1][4]");
//            display(bombplacement[2][4] + " = bombplacement [2][4]");
//            display(bombplacement[3][4] + " = bombplacement [3][4]");
//            display(bombplacement[2][4] + " = bombplacement [2][4]");
//            display(bombplacement[2][3] + " = bombplacement [2][3]");
//            display(bombplacement[2][2] + " = bombplacement [2][2]");
//            display(bombplacement[3][2] + " = bombplacement [3][2]");
//            display(bombplacement[3][1] + " = bombplacement [3][1]");
//            display(bombplacement[3][3] + " = bombplacement [3][3]");
//            display(bombplacement[3][0] + " = bombplacement [3][0]");

            receiveMessages(true);

        }
    }

    private void receiveMessages(boolean run) {
        System.out.println("receiveMessages()");
        String msgt;

        while (run) {
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

                    } else if (split[1].equals("GAMESTART")) { //split = username!gamemode:GAMESTART
                        //split[0] = username!gamemode
                        //split2[0] = username
                        //split2[1] = gamemode
                        String [] split2 = split[0].split("!");
                        firstPlayerName = split2[0].trim();
                        System.out.println("(Split2) firstPlayerName = "+firstPlayerName);
                        switch (split2[1]) {
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
                            triggerClientScreen("ENDED", null);

                        });
                    } else if (split[1].equals("GAMERESET")) {
                        Platform.runLater(() -> {
                            // Game reset
                            // Revert things back to start
                            display("Server has reset the game" + "\n");
                            triggerClientScreen("WAITING", null);
                        });
                        reassignBombs();
                        break;

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
    //Inkz
    private void reassignBombs() {
        System.out.println(LocalTime.now()+" reassignBombs()");
        receiveMessages(false);
        this.bombaround = new int[6][6];
        this.bombaroundMultiPoints = new int[6][6];
        this.bombplacementMultiPoints = new int[6][6];
        this.bombplacement = new int[6][6];
        try {
            sInput = new ObjectInputStream(socket.getInputStream());

            this.bombplacement = (int[][]) sInput.readObject();
            sInput = new ObjectInputStream(socket.getInputStream());

            this.bombaround = (int[][]) sInput.readObject();
            sInput = new ObjectInputStream(socket.getInputStream());

            this.bombplacementMultiPoints = (int[][]) sInput.readObject();
            sInput = new ObjectInputStream(socket.getInputStream());

            this.bombaroundMultiPoints = (int[][]) sInput.readObject();
            sInput = new ObjectInputStream(socket.getInputStream());

        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // for checking values
//        display(bombplacement[0][0] + " = NEW bombplacement [0][0]");
//        display(bombplacement[1][4] + " = NEW bombplacement [1][4]");
//        display(bombplacement[2][4] + " = NEW bombplacement [2][4]");
//        display(bombplacement[3][4] + " = NEW bombplacement [3][4]");
//        display(bombplacement[2][4] + " = NEW bombplacement [2][4]");
//        display(bombplacement[2][3] + " = NEW bombplacement [2][3]");
//        display(bombplacement[2][2] + " = NEW bombplacement [2][2]");
//        display(bombplacement[3][2] + " = NEW bombplacement [3][2]");
//        display(bombplacement[3][1] + " = NEW bombplacement [3][1]");
//        display(bombplacement[3][3] + " = NEW bombplacement [3][3]");
//        display(bombplacement[3][0] + " = NEW bombplacement [3][0]");
//        display(bombaroundMultiPoints[3][0] + " = NEW bombaroundMultiPoints [3][0]");
//        display(bombaroundMultiPoints[3][1] + " = NEW bombaroundMultiPoints [3][1]");
//        display(bombaroundMultiPoints[3][2] + " = NEW bombaroundMultiPoints [3][2]");

        receiveMessages(true);
    }

    private void setUpBomb() {
        System.out.println("setUpBomb()");
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
        System.out.println("setUpBombMultiPoints()");
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
        String winner = setOfScoreSummary_playerNameLabel[0].getText();
        System.out.println(winner+" is leading in the game.");
        if (connected) {
            ButtonClick msg = new ButtonClick(ButtonClick.CLICK, pos, winner);
            try {
                System.out.println("sendButtonPosition(" + pos + ")");
                sOutput.writeObject(msg);
            } catch (IOException e) {
                display("Exception writing to server: " + e);
            }
        }
    }
    //(Queenie) Method to trigger the server to end the game
    private void sendTriggerEnd() {
        System.out.println("sendTriggerEnd()");
        ButtonClick msg;
        sortScoreSummary();
        String winner = setOfScoreSummary_playerNameLabel[0].getText();
        if (connected) {
            msg = new ButtonClick(ButtonClick.TRIGGER_END, winner);
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
                System.out.println("sendReady()");
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
                System.out.println("sendNotReady()");
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
                //resetScoreSummary();
                resetAllBombButtons();
                resetTimer();
                buttonPane.setDisable(true);
                System.out.println("    (4)buttonPane disabled");
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
                setUpPlayerPane();
                setUpScoreSummaryPane();
                // game starts, time and bombs remaining become visible
                bombLeft.setVisible(true);
                bombLeftLabel.setVisible(true);
                showTime.setVisible(true);
                showTimeLabel.setVisible(true);
                if (!playerNames[0].trim().equals(ClientStartPageController.userName))
                    buttonPane.setDisable(true);
                //buttonPane.Disable(); here
                //setPlayable(myturn); should be here
                startWithGameMode(game_mode);
                break;
            case "ENDED":
                // Server pressed "Stop", changing game state from "ONGOING" -> "ENDED"
                System.out.println("(triggerClientScreen()) Server has stopped the game");
                buttonPane.setDisable(true);
                System.out.println("    (5)buttonPane disabled");
                stopTimer();
                resetTimer();
                sortScoreSummary();
                showScoreSummary();
                //reassignBombs(); //(Q)probably should be somewhere else but right now I'll put it here
                //Clearing color from every player pane
                for (Pane pane :
                        setOfPlayerPane_playerPane) {
                    pane.setStyle("-fx-background-color: transparent");
                }
                break;

            default:
                // for when server shuts down
                buttonPane.setDisable(true);
                System.out.println("    (6)buttonPane disabled");
                readyButton.setDisable(true);
                bombLeft.setVisible(false);
                bombLeftLabel.setVisible(false);
                showTime.setVisible(false);
                showTimeLabel.setVisible(false);
                break;

        }
    }

    // (Queenie) method to control client screen for game_state = ONGOING with
    // specified game_mode
    private void startWithGameMode(String game_mode) {
        System.out.println("startWithGameMode(" + game_mode + ")");
        display(">>>"+firstPlayerName+" plays first<<<");
        System.out.println(">>>"+firstPlayerName+" plays first<<<");
        switch (game_mode) {
            case "DEFAULT":
                setGameMode("Default");
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
                setGameMode("Multipoints Bomb");
                // need receive bomb information from server first before setting up bombs on
                // client page
                setUpBombMultiPoints();
                break;
        }

        makeFirstPlayer(firstPlayerName.trim());
        setOfPlayerPane_playerPane[playerplaying-1].setStyle("-fx-background-color: #484c4a");
        System.out.println("*"+playerNames[playerplaying-1]+"'s TURN*");
        display("*"+playerNames[playerplaying-1]+"'s TURN*");
        if (firstPlayerName.equals(ClientStartPageController.userName.trim()))
            buttonPane.setDisable(false);
        System.out.println("    (3)buttonPane enabled");
        maxTime = getTimerMode();
        time = getTimerMode();
        startTimer();

        return;
    }

    private void showScoreSummary() {
        System.out.println("showScoreSummary() is called");
        scoreSummaryPane.setVisible(true);
    }

    // (Poon) setup scores and player names on scoreSummary
    private void setUpScoreSummaryPane() {
        numOfPlayer = users.size();
        System.out.println("setUpScoreSummaryPane(), numOfPlayer= " + numOfPlayer);

        //suggestion to replace above
        /* sorted = getSorted();
        int i = 0;
		for (Map.Entry<Integer, Integer> entry : sorted.entrySet()) {
		    if(i<numOfPlayer) {
		    		int v = entry.getKey();
		    		v++;
		    		setOfScoreSummary_playerNameLabel[i].setText(playerNames[v]);
		    		setOfScoreSummary_scoreLabel[i].setText(entry.getValue() +"");
		    		i++;
		    }
		}*/


        scoreSummary_playerNameLabel1.setText(playerNames[0]);
        scoreSummary_playerNameLabel2.setText(playerNames[1]);
        scoreSummary_playerNameLabel3.setText(playerNames[2]);
        scoreSummary_playerNameLabel4.setText(playerNames[3]);
        scoreSummary_playerNameLabel5.setText(playerNames[4]);
        scoreSummary_playerNameLabel6.setText(playerNames[5]);
        scoreSummary_playerNameLabel7.setText(playerNames[6]);
        scoreSummary_playerNameLabel8.setText(playerNames[7]);
        scoreSummary_playerNameLabel9.setText(playerNames[8]);
        scoreSummary_playerNameLabel10.setText(playerNames[9]);

        //(Queenie) moved these down from setUpPlayerPane() since these codes belong here
        setOfScoreSummary_playerNameLabel[0] = scoreSummary_playerNameLabel1;
        setOfScoreSummary_playerNameLabel[1] = scoreSummary_playerNameLabel2;
        setOfScoreSummary_playerNameLabel[2] = scoreSummary_playerNameLabel3;
        setOfScoreSummary_playerNameLabel[3] = scoreSummary_playerNameLabel4;
        setOfScoreSummary_playerNameLabel[4] = scoreSummary_playerNameLabel5;
        setOfScoreSummary_playerNameLabel[5] = scoreSummary_playerNameLabel6;
        setOfScoreSummary_playerNameLabel[6] = scoreSummary_playerNameLabel7;
        setOfScoreSummary_playerNameLabel[7] = scoreSummary_playerNameLabel8;
        setOfScoreSummary_playerNameLabel[8] = scoreSummary_playerNameLabel9;
        setOfScoreSummary_playerNameLabel[9] = scoreSummary_playerNameLabel10;


        setOfScoreSummary_scoreLabel[0] = scoreSummary_scoreLabel1;
        setOfScoreSummary_scoreLabel[1] = scoreSummary_scoreLabel2;
        setOfScoreSummary_scoreLabel[2] = scoreSummary_scoreLabel3;
        setOfScoreSummary_scoreLabel[3] = scoreSummary_scoreLabel4;
        setOfScoreSummary_scoreLabel[4] = scoreSummary_scoreLabel5;
        setOfScoreSummary_scoreLabel[5] = scoreSummary_scoreLabel6;
        setOfScoreSummary_scoreLabel[6] = scoreSummary_scoreLabel7;
        setOfScoreSummary_scoreLabel[7] = scoreSummary_scoreLabel8;
        setOfScoreSummary_scoreLabel[8] = scoreSummary_scoreLabel9;
        setOfScoreSummary_scoreLabel[9] = scoreSummary_scoreLabel10;

        // display user names
        setOfScoreSummary_playerPane[0] = scoreSummary_player1Pane;
        setOfScoreSummary_playerPane[1] = scoreSummary_player2Pane;
        setOfScoreSummary_playerPane[2] = scoreSummary_player3Pane;
        setOfScoreSummary_playerPane[3] = scoreSummary_player4Pane;
        setOfScoreSummary_playerPane[4] = scoreSummary_player5Pane;
        setOfScoreSummary_playerPane[5] = scoreSummary_player6Pane;
        setOfScoreSummary_playerPane[6] = scoreSummary_player7Pane;
        setOfScoreSummary_playerPane[7] = scoreSummary_player8Pane;
        setOfScoreSummary_playerPane[8] = scoreSummary_player9Pane;
        setOfScoreSummary_playerPane[9] = scoreSummary_player10Pane;

        //set player nickname in order of pressing ready
        for (int i = 0; i < numOfPlayer; i++) { // this loop could be
            setOfScoreSummary_playerNameLabel[i].setText(playerNames[i]); //playerNames[] collect player nickname in order of pressing ready
        }

        //display only players in game
        for (int i = 0; i < numOfPlayer; i++) {
            setOfScoreSummary_playerPane[i].setVisible(true);
        }

        // Setup scores on scoreSummary
        // currently not working
//        for (int i = 0; i < numOfPlayer; i++) {
//            setOfScoreSummary_scoreLabel[i].setText(sorted.get(i) + "");
//        }

    }

    //(Owner unknown - If this is your method put your name here)
    //Method to set up the player pane (middle of client game page) with current players name
    //put score labels into an array of Label
    private void setUpPlayerPane() {

        numOfPlayer = users.size();
        System.out.println("setUpPlayerPane(), numOfPlayer= " + numOfPlayer);
        for (int i = 0; i < numOfPlayer; i++) {
            playerNames[i] = users.get(i).substring(0, users.get(i).indexOf("("));
        }

        playerPane_player1Label.setText(playerNames[0]);
        playerPane_player2Label.setText(playerNames[1]);
        playerPane_player3Label.setText(playerNames[2]);
        playerPane_player4Label.setText(playerNames[3]);
        playerPane_player5Label.setText(playerNames[4]);
        playerPane_player6Label.setText(playerNames[5]);
        playerPane_player7Label.setText(playerNames[6]);
        playerPane_player8Label.setText(playerNames[7]);
        playerPane_player9Label.setText(playerNames[8]);
        playerPane_player10Label.setText(playerNames[9]);

        // display users name
        for (int i = 0; i < numOfPlayer; i++) {
            setOfPlayerPane_playerPane[i].setVisible(true);
        }
        //setOfPlayerPane_scoreLabel is an array of Labels
        //playerPane_scoreLabel1-10 is the label for displaying score
        setOfPlayerPane_scoreLabel[0] = playerPane_scoreLabel1;
        setOfPlayerPane_scoreLabel[1] = playerPane_scoreLabel2;
        setOfPlayerPane_scoreLabel[2] = playerPane_scoreLabel3;
        setOfPlayerPane_scoreLabel[3] = playerPane_scoreLabel4;
        setOfPlayerPane_scoreLabel[4] = playerPane_scoreLabel5;
        setOfPlayerPane_scoreLabel[5] = playerPane_scoreLabel6;
        setOfPlayerPane_scoreLabel[6] = playerPane_scoreLabel7;
        setOfPlayerPane_scoreLabel[7] = playerPane_scoreLabel8;
        setOfPlayerPane_scoreLabel[8] = playerPane_scoreLabel9;
        setOfPlayerPane_scoreLabel[9] = playerPane_scoreLabel10;

    }

    // (Queenie) Method to reset all ready status from players, reset ready button and disable it
    private void resetReadyButton() {
        System.out.println("resetReadyButton()");
        readyButton.setText("READY");
        alreadyReady = false;
        sendNotReady();
        readyButton.setDisable(true);
    }

    //(Queenie) Method to reset all bomb buttons to default for next game
    private void resetAllBombButtons() {
        System.out.println("resetAllBombButtons()");
        //Set all bomb buttons back default (enabled and visible)
        //Set number of bombs back to default
        numBombLeft = 11;
        bombLeft.setText("11");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                Button y = setOfButton[i][j];
                y.setDisable(false);
                y.setStyle(null);
            }
        }
    }

    //(Poon) should set if it's their turn to play, they can play
    //before call this method should set buttonPane default as Disable(true)
    //prepare for tram's gameturn system
    private void setPlayable(int myturn) {
        System.out.println("setPlayabel()");
        if (myturn == playerplaying) {
            buttonPane.setDisable(false);
            System.out.println("    (4)buttonPane enabled");
        }
    }

    //tram
    //if there is no bomb left - return false
    //we should call this method in some while loop ,to check every clicking button that is it a bomb?
    //if it a bomb, is it the last bomb??
    //if it is the last bomb (numbombleft==0), we change the game state to ended
    boolean isThereBombLeft() {
        System.out.println("isThereBombLeft()");
        if (numBombLeft == 0) {
            return false;
        }
        return true;

    }

    //(Queenie) Method to print out contents within a label array
    private String toString(Label[] setOfLabels) {
        String s = "";
        for (Label label : setOfLabels) {
            s += label.getText() + ", ";
        }
        return s;
    }
    //(Queenie) Method to make the given username the first player of the game
    private void makeFirstPlayer(String name){
        System.out.println("makeFirstPlayer("+name+")");
        nameToCompare = name;
        for (int i=0; i<users.size();i++) {
            System.out.println("i="+i+" playerNames[i]="+playerNames[i]);
            if(name.equals(playerNames[i].trim())){
                playerplaying = i+1;
                System.out.println(name+" is playerplaying= "+playerplaying);
                break;
            }
        }
    }

}