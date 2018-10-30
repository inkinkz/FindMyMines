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

import game.model.ButtonClick;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

	/*
	 * @FXML private Button button_done;
	 */
	@FXML
	private DialogPane scoreBoard;

	static int numOfPlayer; // how many player

	Button[][] setOfButton = new Button[6][6];
	Pane[] setOfPlayer = new Pane[10]; // limit player :10
	int[] scoreOfPlayer = new int[10];
	Label[] setOfScore = new Label[10];
	Label[] setOfNameBoard = new Label[10];
	Label[] setOfScoreBoard = new Label[10];
	int numBombLeft = 11;

	@FXML
	private TextArea txtArea;

	@FXML
	private ListView<String> listUsersConnected;

	private ObservableList<String> users;

	int[][] bombplacement = new int[6][6];
	int[][] bombaround = new int[6][6];

	// Server Configuration
	private boolean connected;
	private String server = ClientStartPageController.server;
	private String username = ClientStartPageController.userName;
	private int port = 1500;

	// for I/O
	private ObjectInputStream sInput; // to read from the socket
	private ObjectOutputStream sOutput; // to write on the socket
	private Socket socket;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		txtArea.setEditable(false);
		display("Hello, " + username + "\n");
		startConnection();
		startTimer();
		numOfPlayer = 4; // get from how many client that ready

		// put each pane into setOfPlayer
		setOfPlayer[0] = player1Pane;
		setOfPlayer[1] = player2Pane;
		setOfPlayer[2] = player3Pane;
		setOfPlayer[3] = player4Pane;
		setOfPlayer[4] = player5Pane;
		setOfPlayer[5] = player6Pane;
		setOfPlayer[6] = player7Pane;
		setOfPlayer[7] = player8Pane;
		setOfPlayer[8] = player9Pane;
		setOfPlayer[9] = player10Pane;

		// to hide who does not play
		for (int i = numOfPlayer; i < 10; i++) {
			setOfPlayer[i].setVisible(false);
		}

		scoreOfPlayer[0] = 0;
		scoreOfPlayer[1] = 0;
		scoreOfPlayer[2] = 0;
		scoreOfPlayer[3] = 0;
		scoreOfPlayer[4] = 0;
		scoreOfPlayer[5] = 0;
		scoreOfPlayer[6] = 0;
		scoreOfPlayer[7] = 0;
		scoreOfPlayer[8] = 0;
		scoreOfPlayer[9] = 0;

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
		setOfButton[0][1] = b2;
		setOfButton[0][2] = b3;
		setOfButton[0][3] = b4;
		setOfButton[0][4] = b5;
		setOfButton[0][5] = b6;
		setOfButton[1][0] = b11;
		setOfButton[1][1] = b21;
		setOfButton[1][2] = b31;
		setOfButton[1][3] = b41;
		setOfButton[1][4] = b51;
		setOfButton[1][5] = b61;
		setOfButton[2][0] = b12;
		setOfButton[2][1] = b22;
		setOfButton[2][2] = b32;
		setOfButton[2][3] = b42;
		setOfButton[2][4] = b52;
		setOfButton[2][5] = b62;
		setOfButton[3][0] = b13;
		setOfButton[3][1] = b23;
		setOfButton[3][2] = b33;
		setOfButton[3][3] = b43;
		setOfButton[3][4] = b53;
		setOfButton[3][5] = b63;
		setOfButton[4][0] = b14;
		setOfButton[4][1] = b24;
		setOfButton[4][2] = b34;
		setOfButton[4][3] = b44;
		setOfButton[4][4] = b54;
		setOfButton[4][5] = b64;
		setOfButton[5][0] = b15;
		setOfButton[5][1] = b25;
		setOfButton[5][2] = b35;
		setOfButton[5][3] = b45;
		setOfButton[5][4] = b55;
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

		keeptrack.put(0, 0);
		keeptrack.put(1, 0);
		keeptrack.put(2, 0);
		keeptrack.put(3, 0);
		keeptrack.put(4, 0);
		keeptrack.put(5, 0);
		keeptrack.put(6, 0);
		keeptrack.put(7, 0);
		keeptrack.put(8, 0);
		keeptrack.put(9, 0);
		keeptrack.put(10, 0);

		// color change for the starting player
		setOfPlayer[player].setStyle("-fx-background-color: grey");

	}

	// to keep track of score for the score board next page
	private static Map<Integer, Integer> keeptrack = new Hashtable<Integer, Integer>();

	private int player = 0;
	private int playerplaying = 1;

	void colorChange() {
		if (playerplaying < numOfPlayer) {
			setOfPlayer[--playerplaying].setStyle("-fx-background-color: white");
			setOfPlayer[++playerplaying].setStyle("-fx-background-color: grey");
			playerplaying++;
		} else if (playerplaying == numOfPlayer) {
			setOfPlayer[numOfPlayer - 1].setStyle("-fx-background-color: white");
			setOfPlayer[0].setStyle("-fx-background-color: grey");
			playerplaying = 1;
		} else {
			playerplaying = 1;
		}

	}

	// playing
	@FXML
	void play(MouseEvent event) throws InterruptedException {
		// set exit startTimer();
		sendClick(event);
		condition = true;
		// set color of player to know whose turn is next
		colorChange();
		// timer
		startTimer();
		Button y = (Button) event.getTarget();

		if (y.getStyle() == "-fx-font-size: 0.3") {// free slot
			((Button) event.getTarget()).setStyle("-fx-font-size: 10");
			((Button) event.getTarget()).setStyle("-fx-background-color:#cccccc");
			((Button) event.getTarget()).setDisable(true);
			player++;
		}

		if (y.getStyle() == "-fx-font-size: 0.1") {// bomb
			((Button) event.getTarget()).setStyle("-fx-font-size: 10");
			((Button) event.getTarget()).setText("bomb");
			((Button) event.getTarget()).setDisable(true);
			numBombLeft--;
			bombLeft.setText(numBombLeft + "");
			scoreOfPlayer[player]++;
			int score = scoreOfPlayer[player];
			setOfScore[player].setText(score + "");
			keeptrack.put(player, score);
			player++;
		}

		if (player == numOfPlayer) {
			player = 0;
		}
	}

	boolean condition = false;

	// to display count down from 10 to 0
	void startTimer() {
		// timer run
		Task<Void> task = new Task<Void>() {
			@Override
			public Void call() throws InterruptedException {
				for (int i = 10; i >= 0; i--) {
					// exit startTimer();
					if (condition == true) {
						return null;
					}
					updateMessage(i + "");
					Thread.sleep(1000);
				}
				return null;
			}
		};
		// exit startTimer();
		if (condition == true) {
			condition = false;
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
			condition = false;
			// startTimer after timeout
			startTimer();
		});

		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}

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

	// need to change to ready - disable when pressed
	@FXML
	void ready(ActionEvent event) throws IOException {

	}

	// sort score
	public static Map<Integer, Integer> getSorted() {
		sorted = sort(keeptrack);
		// System.out.print(sorted);
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

	public void login() {
		// test if we can start the connection to the Server
		// if it failed nothing we can do
		if (!startConnection())
			return;
		connected = true;
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

	public void sendClick(MouseEvent event) {

		Platform.runLater(() -> {
			// Update UI here.
			if (connected) {
				ButtonClick bc = new ButtonClick(event);
				try {
					sOutput.writeObject(bc);
					// txtUserMsg.setText("");
				} catch (IOException e) {
					display("Exception writing to server: " + e);
				}
			}
		});

	}

	/*
	 * To send a message to the GUI
	 */
	private void display(String msg) {
		txtArea.appendText(msg + "\n");
	}

	public boolean startConnection() {
		// try to connect to the server
		try {
			socket = new Socket(server, port);
		}
		// if it failed
		catch (Exception ec) {
			display("Error connecting to server:" + ec);
			return false;
		}

		String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
		display(msg);

		/* Creating both Data Stream */
		try {
			sInput = new ObjectInputStream(socket.getInputStream());
			sOutput = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException eIO) {
			display("Exception creating new Input/output Streams: " + eIO);
			return false;
		}

		// creates the Thread to listen from the server
		new ListenFromServer().start();

		// Send our username to the server this is the only message that we
		// will send as a String. All other messages will be ChatMessage objects
		try {
			sOutput.writeObject(username);
		} catch (IOException eIO) {
			display("Exception doing login : " + eIO);
			disconnect();
			return false;
		}
		// success we inform the caller that it worked
		return true;
	}

	class ListenFromServer extends Thread {

		public void run() {
			users = FXCollections.observableArrayList();
			listUsersConnected.setItems(users);
			try {
				bombplacement = (int[][]) sInput.readObject();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				sInput = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				bombaround = (int[][]) sInput.readObject();

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				sInput = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Platform.runLater(() -> {
				// Update UI here.
				setUpBomb();

			});

			while (true) {
				try {
					String msg = (String) sInput.readObject();
//					display(msg);
					String[] split = msg.split(":");
					if (split[1].equals("WHOISIN")) {
						Platform.runLater(() -> {
							users.add(split[0]);
						});
						;
					} else if (split[1].equals("REMOVE")) {
						Platform.runLater(() -> {
							users.remove(split[0]);
						});
					} else {
						txtArea.appendText(msg);
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
				catch (ClassNotFoundException e2) {

				}
			}
		}
	}

	private void setUpBomb() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				int result = bombplacement[i][j];
				Button y = setOfButton[i][j];
				if (result == 0) {
					y.setStyle("-fx-font-size: 0.3"); // blank
				}
				if (result == 1) {
					y.setStyle("-fx-font-size: 0.1"); // bomb
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
}