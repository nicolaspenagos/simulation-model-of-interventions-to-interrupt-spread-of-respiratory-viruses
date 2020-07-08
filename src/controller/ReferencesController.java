/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.Main;

public class ReferencesController {

    @FXML
    private MenuBar menuBar;
    
    @FXML
    private Button goBack;
    
    private Main main;
    
    public void initialize() {
    	
    	String style = "-fx-font-weight: bold; -fx-background-color: SNOW;";
		menuBar.setStyle(style);
		
		goBack.setGraphic(new ImageView(new Image("/images/goBack.png")));
		
    }
    
    @FXML
    void goToMainView(ActionEvent event) {
    	System.out.println("hOLA");
    	main.changeScene("graphicalUserInterface.fxml");
    }
    
    public void setupMain(Main main) {
    	
		this.main = main;

	}

}
