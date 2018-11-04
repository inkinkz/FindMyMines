
package game;

import javafx.scene.text.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class ClientMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Font.loadFont(getClass().getResourceAsStream("/ISOCP___.TTF"), 14);
			Font.loadFont(getClass().getResourceAsStream("/ISOCPEUR.TTF"), 14);
			Font.loadFont(getClass().getResourceAsStream("/ISOCTEUR.TTF"), 14);
			
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("view/ClientStartPage.fxml"));
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

	public static void main(String[] args) {

		launch(args);
		
		
		
		  

	}
}
