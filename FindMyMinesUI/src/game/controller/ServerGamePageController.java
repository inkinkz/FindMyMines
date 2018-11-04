package game.controller;

import static java.util.stream.Collectors.toMap;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class ServerGamePageController implements Initializable {

    @FXML
    private AnchorPane parentPane;

    @FXML
    private AnchorPane leftPane;

    @FXML
    private AnchorPane rightPane;

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
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private Button resetButton;

    @FXML
    private TextArea textArea;

    @FXML
    private ListView<String> listUsersConnected;

    @FXML
    private ChoiceBox<String> modebox;

    @FXML
    private Label warnReady;

    // game

    static int numOfPlayer; // how many player
    static Button[][] setOfButton = new Button[6][6];
    Pane[] setOfPlayerPane = new Pane[10]; // limit player :10
    private static ObservableMap<Integer, Integer> scoreOfPlayer;
    private ObservableValue<Integer> playerReady; // number of player ready
    Label[] setOfScore = new Label[10];
    Label[] setOfPlayer = new Label[10];
    Label[] setOfNameBoard = new Label[10];
    Label[] setOfScoreBoard = new Label[10];
    int numBombLeft = 11;
    private String gameMode = "DEFAULT";

    // SERVER

    public FindMyMinesServer server;

    private ObservableList<String> users;

    public void startServer() {
        // game initialize
        scoreOfPlayer = FXCollections.observableHashMap();
        playerReady = new SimpleIntegerProperty(0).asObject();
        // create a new Server
        server = new FindMyMinesServer(1500, this);
        users = FXCollections.observableArrayList();
        listUsersConnected.setItems(users);
        assignBombDefault();
        new ServerRunning().start();
    }

    /*
     * A thread to run the Server
     */
    class ServerRunning extends Thread {
        public void run() {
            server.start(); // should execute until if fails

            // the server failed
            appendEvent("Server Stopped \n");
            server = null;
            users = null;
        }
    }

    public void addUser(String user) {
        Platform.runLater(() -> {
            users.add(user);
            numOfPlayer++;
        });
    }

    public void appendEvent(String string) {
        textArea.appendText(string);
    }

    public void appendRoom(String messageLf) {
        textArea.appendText(messageLf);
    }

    //remove user
    public void remove(String username) {
        Platform.runLater(() -> {
            users.remove(username);
        });
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        textArea.setEditable(false);
        warnReady.setVisible(false);
        leftPane.setDisable(true);

        // set up mode selection
        ObservableList<String> availableChoices = FXCollections.observableArrayList("Default Mode", "Quick Game", "Multipoints Bomb");
        modebox.setItems(availableChoices);
        modebox.setValue("Default Mode");


        startServer();
    }

    private void setScore() {
        for (int i = 0; i < 10; i++) {
            scoreOfPlayer.put(i, 0);
        }
    }

    private void setUpBomb() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int result = getValueOfSpace(i, j);
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
                int numOfBombAround = getNumBombAround(i, j);
                if (numOfBombAround > 0) {
                    setOfButton[i][j].setText("" + numOfBombAround);
                }
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                Button y = setOfButton[i][j];
                y.setDisable(true);
            }
        }
    }

    private void setupPane() {
        // put each pane into setOfPlayer
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

        // TODO Auto-generated method stub
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

    //starting of implementation from former StartPageController
    //this will be assign to each button in the GamePage 0=free 1=bomb
    public static int[][] valueOfSpace = new int[6][6];

    // create array to keep number of surrounding bomb
    public static int[][] bombAround = new int[6][6];
    int numBomb = 0;

    private void assignBombDefault() {
        // assign bomb to the slot
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {

                int result = (int) Math.ceil(Math.random() * 2); //
                // float result = (float) Math.random();
                /*
                 * if (result < 0.5) { result = 0; } if (result >= 0.5) { result = 1; }
                 */
                if (numBomb >= 11) {
                    result = 1;
                }
                if (result == 1) {
                    valueOfSpace[i][j] = 0;// free space
                }
                if (result == 2) {
                    valueOfSpace[i][j] = 1;// bomb
                    numBomb++;
                }

            }
        }

        // fix number of bomb to 11
        while (numBomb != 11) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {

                    if (valueOfSpace[i][j] == 0) {
                        int result = (int) Math.ceil(Math.random() * 2);
                        // float result = (float) Math.random();
                        /*
                         * if (result < 0.5) { result = 0; } if (result >= 0.5) { result = 1; }
                         */
                        if (result == 1) {
                            valueOfSpace[i][j] = 0;// free space
                        }
                        if (result == 2) {
                            valueOfSpace[i][j] = 1;// bomb
                            numBomb++;
                        }
                    }
                }

            }
        }

        // set number in free slot

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {

                int countBombAround = 0;
                if (valueOfSpace[i][j] == 0) { // if this is free slot
                    if (i - 1 >= 0 && j - 1 >= 0) { // if there is a slot
                        if (valueOfSpace[i - 1][j - 1] == 1) { // if the upperleft is bomb
                            countBombAround++;
                        }
                    }
                    if (i >= 0 && j - 1 >= 0) {
                        if (valueOfSpace[i][j - 1] == 1) {
                            countBombAround++;
                        }
                    }
                    if (i + 1 <= 5 && j - 1 >= 0) {
                        if (valueOfSpace[i + 1][j - 1] == 1) {
                            countBombAround++;
                        }
                    }
                    if (i - 1 >= 0 && j >= 0) {
                        if (valueOfSpace[i - 1][j] == 1) {
                            countBombAround++;
                        }
                    }
                    if (i + 1 <= 5 && j >= 0) {
                        if (valueOfSpace[i + 1][j] == 1) {
                            countBombAround++;
                        }
                    }
                    if (i - 1 >= 0 && j + 1 <= 5) {
                        if (valueOfSpace[i - 1][j + 1] == 1) {
                            countBombAround++;
                        }
                    }
                    if (i >= 0 && j + 1 <= 5) {
                        if (valueOfSpace[i][j + 1] == 1) {
                            countBombAround++;
                        }
                    }
                    if (i + 1 <= 5 && j + 1 <= 5) {
                        if (valueOfSpace[i + 1][j + 1] == 1) {
                            countBombAround++;
                        }
                    }
                    bombAround[i][j] = countBombAround;

                }
            }
        }

    }

    private void assignBombMultipleScore() {
        // assign bomb to the slot
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {

                int result = (int) Math.ceil(Math.random() * 9); //
                // float result = (float) Math.random();

                /*
                 * if (result < 0.5) { result = 0; } if (result >= 0.5) { result = 1; }
                 */
                if (numBomb >= 11) {
                    result = 1;
                }
                if (result == 1) {
                    valueOfSpace[i][j] = 0;// free space
                }
                if (result == 6) {
                    valueOfSpace[i][j] = 0;// free space
                }
                if (result == 7) {
                    valueOfSpace[i][j] = 0;// free space
                }
                if (result == 8) {
                    valueOfSpace[i][j] = 0;// free space
                }

                if (result == 2) {
                    valueOfSpace[i][j] = 1;// bomb
                    numBomb++;
                }
                if (result == 3) {
                    valueOfSpace[i][j] = 2;// bomb
                    numBomb++;
                }
                if (result == 4) {
                    valueOfSpace[i][j] = 3;// bomb
                    numBomb++;
                }
                if (result == 5) {
                    valueOfSpace[i][j] = 4;// bomb
                    numBomb++;
                }

            }
        }

        // fix number of bomb to 11
        while (numBomb != 11) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {

                    if (valueOfSpace[i][j] == 0) {
                        int result = (int) Math.ceil(Math.random() * 6);
                        // float result = (float) Math.random();
                        /*
                         * if (result < 0.5) { result = 0; } if (result >= 0.5) { result = 1; }
                         */
                        if (result == 1) {
                            valueOfSpace[i][j] = 0;// free space
                        }

                        if (result == 6) {
                            valueOfSpace[i][j] = 0;// free space
                        }
                        if (result == 7) {
                            valueOfSpace[i][j] = 0;// free space
                        }
                        if (result == 8) {
                            valueOfSpace[i][j] = 0;// free space
                        }

                        if (result == 2) {
                            valueOfSpace[i][j] = 1;// bomb
                            numBomb++;
                        }
                        if (result == 3) {
                            valueOfSpace[i][j] = 2;// bomb
                            numBomb++;
                        }
                        if (result == 4) {
                            valueOfSpace[i][j] = 3;// bomb
                            numBomb++;
                        }
                        if (result == 5) {
                            valueOfSpace[i][j] = 4;// bomb
                            numBomb++;
                        }
                    }
                }

            }
        }

        // set number in free slot

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {

                int countBombAround = 0;
                if (valueOfSpace[i][j] == 0) { // if this is free slot
                    if (i - 1 >= 0 && j - 1 >= 0) { // if there is a slot
                        if (valueOfSpace[i - 1][j - 1] == 1) { // if the upperleft is bomb
                            countBombAround++;
                        }
                        if (valueOfSpace[i - 1][j - 1] == 2) { // if the upperleft is bomb
                            countBombAround++;
                        }
                        if (valueOfSpace[i - 1][j - 1] == 3) { // if the upperleft is bomb
                            countBombAround++;
                        }
                        if (valueOfSpace[i - 1][j - 1] == 4) { // if the upperleft is bomb
                            countBombAround++;
                        }
                    }
                    if (i >= 0 && j - 1 >= 0) {
                        if (valueOfSpace[i][j - 1] == 1) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i][j - 1] == 2) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i][j - 1] == 3) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i][j - 1] == 4) {
                            countBombAround++;
                        }
                    }
                    if (i + 1 <= 5 && j - 1 >= 0) {
                        if (valueOfSpace[i + 1][j - 1] == 1) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i + 1][j - 1] == 2) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i + 1][j - 1] == 3) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i + 1][j - 1] == 4) {
                            countBombAround++;
                        }
                    }
                    if (i - 1 >= 0 && j >= 0) {
                        if (valueOfSpace[i - 1][j] == 1) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i - 1][j] == 2) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i - 1][j] == 3) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i - 1][j] == 4) {
                            countBombAround++;
                        }
                    }
                    if (i + 1 <= 5 && j >= 0) {
                        if (valueOfSpace[i + 1][j] == 1) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i + 1][j] == 2) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i + 1][j] == 3) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i + 1][j] == 4) {
                            countBombAround++;
                        }
                    }
                    if (i - 1 >= 0 && j + 1 <= 5) {
                        if (valueOfSpace[i - 1][j + 1] == 1) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i - 1][j + 1] == 2) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i - 1][j + 1] == 3) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i - 1][j + 1] == 4) {
                            countBombAround++;
                        }
                    }
                    if (i >= 0 && j + 1 <= 5) {
                        if (valueOfSpace[i][j + 1] == 1) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i][j + 1] == 2) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i][j + 1] == 3) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i][j + 1] == 4) {
                            countBombAround++;
                        }
                    }
                    if (i + 1 <= 5 && j + 1 <= 5) {
                        if (valueOfSpace[i + 1][j + 1] == 1) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i + 1][j + 1] == 2) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i + 1][j + 1] == 3) {
                            countBombAround++;
                        }
                        if (valueOfSpace[i + 1][j + 1] == 4) {
                            countBombAround++;
                        }
                    }
                    bombAround[i][j] = countBombAround;

                }
            }
        }

    }

    public static int getValueOfSpace(int i, int j) {
        int valueofspace = valueOfSpace[i][j];
        return valueofspace;

    }

    public static int getNumBombAround(int i, int j) {
        int numbombaround = bombAround[i][j];
        return numbombaround;
    }
    //end of implementation from former StartPageController

    //to keep track of score for the score board next page

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


    // playing
    @FXML
    void showBomb() throws InterruptedException {
        // set exit startTimer();
        // condition = true;
        // set color of player to know whose turn is next
        // colorChange();
        // timer
        // startTimer();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                Button y = setOfButton[i][j];
                if (y.getStyle() == "-fx-font-size: 0.0") {// free slot
                    y.setStyle("-fx-font-size: 10");
                    y.setStyle("-fx-background-color:#cccccc");
                }

                if (y.getStyle() == "-fx-font-size: 0.1") {// bomb
                    y.setStyle("-fx-font-size: 10");
                    y.setText("BOMB");
                }

                if (y.getStyle() == "-fx-font-size: 0.2") {// bomb
                    y.setStyle("-fx-font-size: 10");
                    y.setText("BOMB \n x2");
                }

                if (y.getStyle() == "-fx-font-size: 0.3") {// bomb
                    y.setStyle("-fx-font-size: 10");
                    y.setText("BOMB \n x3");
                }

                if (y.getStyle() == "-fx-font-size: 0.4") {// bomb
                    y.setStyle("-fx-font-size: 10");
                    y.setText("BOMB \n x4");
                }

            }
        }
    }

    int time = 10;

    //to display count down from 10 to 0
    void startTimer() {
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                for (int i = time; i >= 0; i--) {
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
        if (false) {
            // to stop timer
            return;
        }
        showTime.textProperty().bind(task.messageProperty());
        task.setOnSucceeded(e -> {
            showTime.textProperty().unbind();
            showTime.setText("0");
            //skip this player when timeout
            player++;
            if (player == numOfPlayer) {
                player = 0;
            }
            colorChange();
            //startTimer after timeout
            startTimer();
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    //check all the player already click ready
//    boolean readyAll = false;

    @FXML
    void nextGameState(ActionEvent event) {
        //called when "Start" / "Stop" / "Reset" button is pressed
        String GAME_STATE = server.getGameState();

        if (GAME_STATE.equals("WAITING")) {
            //GAME_STATE = WAITING -> ONGOING
            if(!readyAll()) {
                warnReady.setVisible(true);
                return;
            } else {
                //All players are ready, proceed to game
                warnReady.setVisible(false);
                setMode();
                modebox.setDisable(true);
                // complete game template
                numOfPlayer = users.size(); // get from how many client
                setupPane();
                setScore();
                // color change for the starting player
                setOfPlayerPane[player].setStyle("-fx-background-color: grey");
                assignBombDefault();
                setUpBomb();
                FindMyMinesServer.broadcast("GAMESTARTED:GAMESTART");

            try {
                showBomb();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Start button clicked");
            server.changeGameState();
            startButton.setText("Stop");
            return;
            }
        }
        else if (GAME_STATE.equals("ONGOING")) {
            //GAME_STATE = ONGOING -> ENDED
            modebox.setDisable(true);
            System.out.println("Stop button clicked");
            startButton.setText("Reset");
            server.changeGameState();
            FindMyMinesServer.broadcast("GAMESTOPPED:GAMESTOP");
            return;
        }
        else {
            //GAME_STATE = ENDED -> WAITING
            modebox.setDisable(false);
            System.out.println("Reset button clicked");
            startButton.setText("Start");
            server.changeGameState();
            return;
        }

    }

    //check if all users are ready
    boolean readyAll() {

        Boolean check = true;

        for (String user : users) {
            if(!user.contains("(READY)")){
                check = false;
                break;
            }
        }

        return check;
    }

    //boolean firstTime =true;
    // GAME_STATE = "WAITING";

 /*   private void stateCheck() {
        String GAME_STATE = FindMyMinesServer.getGameState();
        if (GAME_STATE.equals("WAITING")) {
            // complete game template
            numOfPlayer = users.size(); // get from how many client
            setupPane();
            setScore();
            // color change for the starting player
            setOfPlayerPane[player].setStyle("-fx-background-color: grey");

            assignBombDefault();
            setUpBomb();
            try {
                showBomb();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            FindMyMinesServer.setGameState("ONGOING");
            FindMyMinesServer.display("");
            System.out.println("GAME_STATE set to:" +FindMyMinesServer.getGameState()+" and button should be Stop");
            startButton.setText("Stop");
            System.out.println("done if 1");
            return;
        }
        else if (GAME_STATE.equals("ONGOING")) {
            startButton.setText("Reset");
            FindMyMinesServer.setGameState("ENDED");
            System.out.println("GAME_STATE set to:" +FindMyMinesServer.getGameState()+" and button should be Reset");
            System.out.println("done if 2");
            return;
        }
        else {
            startButton.setText("Start");
            FindMyMinesServer.setGameState("ONGOING");
            System.out.println("GAME_STATE set to:" +FindMyMinesServer.getGameState()+" and button should be Start");
            System.out.println("done if 3");
            //firstTime =true;
            return;
        }
    }*/

    @FXML
    void stop(ActionEvent event) {
        //	if (GAME_STATE == "ONGOING") {
        // setText for score board -- for stop button
        stopButton.setVisible(false);
        stopButton.setDisable(true);
        resetButton.setVisible(true);
        resetButton.setDisable(false);

        //	}
    }


    @FXML
    void resetState(ActionEvent event) {
        resetButton.setVisible(false);
        resetButton.setDisable(true);
        startButton.setVisible(true);
        startButton.setDisable(false);

        new ServerGamePageController();
    }

    @FXML
    private Button buttonDone;

    //delete all the value assign & disble the dialogpane
    @FXML
    void goBack(ActionEvent event) throws IOException {
        //new ServerGamePageController();
    }

    //getter of sorted for other class to use
    public static Map<Integer, Integer> getSorted() {
        sorted = sort(scoreOfPlayer);
        System.out.print(sorted);
        return sorted;
    }

    //sort player score from highest to lowest
    private static Map<Integer, Integer> sorted = new Hashtable<Integer, Integer>();

    private static Map<Integer, Integer> sort(Map<Integer, Integer> map) {
        Map<Integer, Integer> sorted = map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        Iterator<Integer> iterators = sorted.keySet().iterator();
        while (iterators.hasNext()) {
            int key = iterators.next();
            if (key >= numOfPlayer) {
                iterators.remove();
            }
        }
        //System.out.print(sorted);
        return sorted;

    }

    @FXML
    public void setMode() {
        String selectedChoice = modebox.getValue();
        if (selectedChoice.equals("Default Mode")) {
            setGameMode("DEFAULT");
            assignBombDefault();
            System.out.println("Game mode set to DEFAULT");
            return;
        }
        if (selectedChoice.equals("Quick Game")) {
            setGameMode("QUICK_GAME");
            assignBombDefault();
            System.out.println("Game mode set to QUICK_GAME");
            time = 5;
            return;
        }
        if (selectedChoice.equals("Multipoints Bomb")) {
            setGameMode("MULTIPOINTS_BOMB");
            assignBombMultipleScore();
            System.out.println("Game mode set to MULTIPOINTS_BOMB");
            return;
        }
    }

    public String getGameMode() {
        if(this.gameMode.equals("QUICK_GAME"))
            return "Quick Game";
        else if(this.gameMode.equals("MULTIPOINTS_BOMB"))
            return "Multipoints Bomb";
        else
            return "Default Mode";
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    // receive button position clicked from other clients
    void playFromOthers(String cl) {

        String s = cl.trim();
        int j = 0;
        int i = (Integer.parseInt(s.charAt(0) + "")) - 1;
        if (s.length() >= 2) {
            if (!(s.substring(1).equals(","))) {
                j = Integer.parseInt(s.charAt(1) + "");
            }
        }

        // To check button position
//        display("i = " + i + " j = " + j);
        Button y = setOfButton[i][j];

        if (y.getStyle() == "-fx-font-size: 0.0") {// free slot
            y.setStyle("-fx-font-size: 10");
            y.setStyle("-fx-background-color:#2B2D42");
        }

        if (y.getStyle() == "-fx-font-size: 0.1") {// bomb
            y.setStyle("-fx-font-size: 5");
            y.setStyle("-fx-background-color:#D90429");
            y.setText("BOMB");
        }

        if (y.getStyle() == "-fx-font-size: 0.2") {// bomb
            y.setStyle("-fx-font-size: 5");
            y.setText("BOMB \n x2");
        }

        if (y.getStyle() == "-fx-font-size: 0.3") {// bomb
            y.setStyle("-fx-font-size: 5");
            y.setText("BOMB \n x3");
        }

        if (y.getStyle() == "-fx-font-size: 0.4") {// bomb
            y.setStyle("-fx-font-size: 5");
            y.setText("BOMB \n x4");
        }
    }


}