/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;

public class ReferencesController {

    @FXML
    private MenuBar menuBar;
    
    public void initialize() {
    	
    	String style = "-fx-font-weight: bold; -fx-background-color: SNOW;";
		menuBar.setStyle(style);
		
    }
}
