package application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class popupError {
	static boolean answer;

	public static void display(String message) {
		Stage errorWindow = new Stage();
		errorWindow.initModality(Modality.APPLICATION_MODAL);
		errorWindow.setTitle("Problem Detected");
		errorWindow.setMinWidth(250);
		
		Label label = new Label();
		label.setText(message);
		
		Button okBtn = new Button("OK");
		
		okBtn.setOnAction(e -> {
			errorWindow.close();
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, okBtn);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		errorWindow.setScene(scene);
		errorWindow.showAndWait();
	}
}
