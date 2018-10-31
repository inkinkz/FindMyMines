package game.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ClientStartPageController {

	@FXML
	private Button start;

	@FXML
	private Label Title;

	@FXML
	private Label clientNamebox;

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

	@FXML
	private Label warnIP;

	@FXML
	private Label warnName;

	@FXML
	void start(ActionEvent event) throws IOException {
		
		server = txtServer.getText().trim();
		userName = txtUser.getText().trim();
		
		if (server.isEmpty() == true && userName.isEmpty() == true) {
			warnIP.setVisible(true);
			warnName.setVisible(true);
			return;
		}
		else if (server.isEmpty() == true) {
			warnIP.setVisible(true);
			warnName.setVisible(false);
			return;
		}
		else if (userName.isEmpty() == true) {
			warnName.setVisible(true);
			warnIP.setVisible(false);
			return;
		}		

		AnchorPane gamePage = (AnchorPane) FXMLLoader.load(getClass().getResource("/game/view/ClientGamePage.fxml"));
		Scene scene = new Scene(gamePage);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setMinWidth(1000);
		stage.setMinHeight(520);
		stage.setScene(scene);
		stage.show();

	}

}