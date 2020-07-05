/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package controller;

import controller.GraphicUserInterfaceController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

	public AlertBox() {

	}

	public void display(String title, String message, GraphicUserInterfaceController gui) {

		if (!gui.isWindowLaunched()) {
			gui.setWindowLaunched(true);
			
			// -------------------------------------
			// Stage
			// -------------------------------------
			Stage window = new Stage();

			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle(title);
			window.setMinWidth(530);
			window.setMinHeight(340);
			window.getIcons().add(new Image("/images/icon.png"));
			window.setResizable(false);

			// -------------------------------------
			// Labels
			// -------------------------------------
			Label label = new Label();
			label.setText(message);
			String style = "-fx-font-size: 20pt; -fx-font-weight: bold;";
			label.setStyle(style);

			Label label1 = new Label();
			label1.setText("What would you like to do ?  ");

			Label space = new Label();
			space.setText("  ");
			Label space1 = new Label();
			space1.setText("  ");

			// -------------------------------------
			// Images
			// -------------------------------------
			Image goBackImage = new Image("/images/goBack.png");
			Image saveImage = new Image("/images/save.png");
			Image bckgImage = new Image("/images/alertBox.png");

			// -------------------------------------
			// Buttons
			// -------------------------------------
			Button closeButton = new Button();
			Button saveButton = new Button();

			closeButton.setGraphic(new ImageView(goBackImage));
			saveButton.setGraphic(new ImageView(saveImage));

			closeButton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					gui.programmaticallyStopButton();
					window.close();
				}
			});

			saveButton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					gui.saveCVS();
					window.close();
				}
			});


			// -------------------------------------
			// HBox
			// -------------------------------------
			HBox horizontalLayout = new HBox(5);

			horizontalLayout.getChildren().addAll(closeButton, space, saveButton, space1);
			horizontalLayout.setAlignment(Pos.CENTER);

			// -------------------------------------
			// VBox
			// -------------------------------------
			VBox layout = new VBox(10);

			layout.getChildren().addAll(label, label1, horizontalLayout);
			layout.setAlignment(Pos.CENTER);

			BackgroundSize backgroundSize = new BackgroundSize(350, 200, true, true, true, false);
			BackgroundImage backgroundImage = new BackgroundImage(bckgImage, BackgroundRepeat.REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
			layout.setBackground(new Background(backgroundImage));

			// -------------------------------------
			// Scene
			// -------------------------------------
			Scene scene = new Scene(layout);

			window.setScene(scene);
			window.showAndWait();

		}
	}

}