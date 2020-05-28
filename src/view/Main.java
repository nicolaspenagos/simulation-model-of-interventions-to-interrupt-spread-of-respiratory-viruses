/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{

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
		
		FXMLLoader fxmlL= new FXMLLoader(getClass().getResource("graphicalUserInterface.fxml"));
		Parent root = fxmlL.load(); 
		Scene scene = new Scene(root); 
		
		stage.setScene(scene);
		stage.getIcons().add(new Image("/images/icon-01.png")); 
		stage.setTitle("MOTSORV");
		stage.show();
		//stage.setResizable(false);
		
	}

}
