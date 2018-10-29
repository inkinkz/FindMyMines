
package game;

import javafx.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;

public class ServerMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("view/StartPage.fxml"));
			Scene scene = new Scene(root, 1000, 650);
			scene.getStylesheets().add(getClass().getResource("view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.show();
			primaryStage.setMinWidth(700);
	        primaryStage.setMinHeight(505);
			primaryStage.setResizable(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	int numBomb = 0;

	public static void main(String[] args) {

		launch(args);

	}
}
