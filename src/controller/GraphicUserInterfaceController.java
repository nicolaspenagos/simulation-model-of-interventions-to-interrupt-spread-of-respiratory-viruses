/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package controller;

import java.util.ArrayList;

import customThreads.GUIUpdateControlThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import model.Logic;

public class GraphicUserInterfaceController {

	// -------------------------------------
	// FXML
	// -------------------------------------
	@FXML
	private Slider sliderInfectedPeople;

	@FXML
	private Slider sliderHealthyPeople;

	@FXML
	private Slider sliderRecoveredPeople;

	@FXML
	private TextField iPtxtField;

	@FXML
	private TextField hPtxtField;

	@FXML
	private TextField rPtxtField;

	@FXML
	private Label totalPeopleLabel;
	
    @FXML
    private Button startButton;
    
    @FXML
    void startSimulation(ActionEvent event) {
    	logic.loadPeople();
    }
    
	// -------------------------------------
	// Atributtes
	// -------------------------------------
	private Logic logic;

	// -------------------------------------
	// Constructor
	// -------------------------------------
	public GraphicUserInterfaceController() {

		logic = new Logic();

	}

	@FXML
	public void initialize() {

		disableTextFields();
		addSlidersEventHandlers();
		
		startButton.setStyle("-fx-base: coral;");
		startButton.setStyle(
		            "-fx-background-radius: 50em; "
		            + "-fx-min-width: 100px; "
		            + "-fx-min-height: 100px; "
		            + "-fx-max-width: 100px; "
		            + "-fx-max-height: 100px;"
		            + "-fx-background-color:LIMEGREEN"
		    );
		startButton.setText("START");
		startButton.setDisable(true);
		
		GUIUpdateControlThread guiThread = new GUIUpdateControlThread(this); 
    	guiThread.setDaemon(true);
    	guiThread.start();

	}

	// -------------------------------------
	// Methods
	// -------------------------------------
	public void update() {
		
	}

	private void changeTextF1Handler(MouseEvent e) {

		int infectedPeople = (int) sliderInfectedPeople.getValue();
		iPtxtField.setText("" + infectedPeople);
		logic.setInfectedPeople(infectedPeople);
		updateTotalPeople();
		disableButton();
			
	}

	private void changeTextF2Handler(MouseEvent e) {

		int healthyPeople = (int) sliderHealthyPeople.getValue();
		hPtxtField.setText("" + healthyPeople);
		logic.setHealthyPeople(healthyPeople);
		updateTotalPeople();
		disableButton();

	}

	private void changeTextF3Handler(MouseEvent e) {

		int recoveredPeople = (int) sliderRecoveredPeople.getValue();
		rPtxtField.setText("" + recoveredPeople);
		logic.setRecoveredPeople(recoveredPeople);
		updateTotalPeople();
		disableButton();
		
	}

	public void disableTextFields() {

		iPtxtField.setEditable(false);
		hPtxtField.setEditable(false);
		rPtxtField.setEditable(false);

	}

	public void addSlidersEventHandlers() {

		sliderInfectedPeople.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::changeTextF1Handler);
		sliderHealthyPeople.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::changeTextF2Handler);
		sliderRecoveredPeople.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::changeTextF3Handler);

	}

	public void updateTotalPeople() {	
		totalPeopleLabel.setText(""+logic.getTotalPeople());
	}

	public void disableButton() {
		
		if(logic.getTotalPeople() > 1) 
			startButton.setDisable(false);
		else 
			startButton.setDisable(true);
		
	}
	
	
}
