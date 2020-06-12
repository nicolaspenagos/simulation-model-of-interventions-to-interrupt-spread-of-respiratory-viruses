/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package view;

import java.io.IOException;

import controller.GraphicUserInterfaceController;
import controller.DataController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	// -------------------------------------
	// Attributes 
	// -------------------------------------
	private Stage currentStage;
	private GraphicUserInterfaceController gUIC;
	private DataController dataController;
	
	// -------------------------------------
	// Main Method
	// -------------------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	// -------------------------------------
	// JavaFX Methods
	// -------------------------------------
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub

	
		
		currentStage = stage;
		FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("graphicalUserInterface.fxml"));
		Parent root = fxmlL.load();
		Scene scene = new Scene(root);
		
		gUIC = fxmlL.getController();
		gUIC.setupMain(this);

		
		
		
		stage.setScene(scene);
		stage.getIcons().add(new Image("/images/icon.png"));
		stage.setTitle("");
		stage.show();
		stage.setResizable(false);

	}

	// -------------------------------------
	// Methods
	// -------------------------------------
	public void changeScene(String fxml) {
		
		FXMLLoader fxmlL = new FXMLLoader(getClass().getResource(fxml));
		try {
			
			Parent pane = fxmlL.load();
			
			if(fxmlL.getController() instanceof DataController) {
				
				dataController = fxmlL.getController();
				dataController.setupMain(this); 
				
			}else if(fxmlL.getController() instanceof GraphicUserInterfaceController) {
				
				gUIC = fxmlL.getController();
				gUIC.setupMain(this); 
				
			}
			
			
			currentStage.getScene().setRoot(pane);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
