
package game;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.fxml.FXMLLoader;

public class ServerMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Font.loadFont(getClass().getResourceAsStream("/ISOCP___.TTF"), 14);
			Font.loadFont(getClass().getResourceAsStream("/ISOCPEUR.TTF"), 14);
			Font.loadFont(getClass().getResourceAsStream("/ISOCTEUR.TTF"), 14);
			
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("view/GamePage.fxml"));
			Scene scene = new Scene(root, 1000, 650);
			scene.getStylesheets().add(getClass().getResource("view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.show();
			primaryStage.setMinWidth(1000);
	        primaryStage.setMinHeight(650);
			primaryStage.setResizable(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		launch(args);

	}
}
