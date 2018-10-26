package game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ScoreboardController implements Initializable {

	@FXML
	private Label label_scoreboard;

	@FXML
	private Button button_done;

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

	// event going back to the starting page
	@FXML
	void backtohome(ActionEvent event) throws IOException {
		AnchorPane gamePage = (AnchorPane) FXMLLoader.load(getClass().getResource("StartPage.fxml"));
		Scene scene = new Scene(gamePage);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	int numOfPlayer;
	Pane[] setOfPlayer = new Pane[10];
	Label[] setOfName = new Label[10];
	Label[] setOfScore = new Label[10];
	Map<Integer, Integer> sorted = ServerGamePageController.getSorted();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		numOfPlayer = 4; // get from how many clients that ready

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

		setOfName[0] = player1;
		setOfName[1] = player2;
		setOfName[2] = player3;
		setOfName[3] = player4;
		setOfName[4] = player5;
		setOfName[5] = player6;
		setOfName[6] = player7;
		setOfName[7] = player8;
		setOfName[8] = player9;
		setOfName[9] = player10;

		//setText for score board
		int i = 0;
		for (Map.Entry<Integer, Integer> entry : sorted.entrySet()) {
		    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		    if(i<numOfPlayer) {
		    		int v = entry.getKey();
		    		v++;
		    		setOfName[i].setText("Player "+ v);
		    		setOfScore[i].setText(entry.getValue() +"");
		    		i++;
		    }   
		}
	}
}
