package application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class closeConfirmation {
	static boolean answer;

	public static boolean display() {
		Stage closeConfirmWindow = new Stage();
		closeConfirmWindow.initModality(Modality.APPLICATION_MODAL);
		closeConfirmWindow.setTitle("Confirm Termination");
		closeConfirmWindow.setMinWidth(250);
		
		Label label = new Label();
		label.setText("Are you sure you want to close the program?");
		
		Button yesBtn = new Button("Yes");
		Button noBtn = new Button("No");
		
		yesBtn.setOnAction(e -> {
			answer = true;
			closeConfirmWindow.close();
		});
		
		noBtn.setOnAction(e -> {
			answer = false;
			closeConfirmWindow.close();
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, yesBtn, noBtn);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		closeConfirmWindow.setScene(scene);
		closeConfirmWindow.showAndWait();

		return answer;
	}
}
