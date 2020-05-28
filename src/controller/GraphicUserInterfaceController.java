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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Logic;
import model.ModelCircle;
import model.Person;

public class GraphicUserInterfaceController {

	// -------------------------------------
	// FXML
	// -------------------------------------
	@FXML
	private Pane pane;
	
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
    private Label totalPeopleLabel1;

	@FXML
	private Button startButton;

	@FXML
	private CheckBox maskChB;

	@FXML
	private CheckBox n95MaskChB;

	@FXML
	private CheckBox glovesChB;

	@FXML
	private CheckBox gownChB;

	@FXML
	private CheckBox handWashingChB;

	// -------------------------------------
	// Atributtes
	// -------------------------------------
	private Logic logic;
	private boolean onSimulation;
	private ArrayList<Circle> circles;
	private ArrayList<ModelCircle> people;

	// -------------------------------------
	// Constructor
	// -------------------------------------
	public GraphicUserInterfaceController() {

		logic = new Logic();
		onSimulation = false;

		GUIUpdateControlThread guiThread = new GUIUpdateControlThread(this);
		guiThread.setDaemon(true);
		guiThread.start();
		circles = new ArrayList<Circle>();
		people = (ArrayList<ModelCircle>) logic.getPeople();

	}

	// -------------------------------------
	// Methods
	// -------------------------------------
	@FXML
	public void initialize() {
		
		disableTextFields();
		addSlidersEventHandlers();

		startButton.setStyle("-fx-base: coral;");
		startButton.setStyle("-fx-background-radius: 50em; " + "-fx-min-width: 100px; " + "-fx-min-height: 100px; "
				+ "-fx-max-width: 100px; " + "-fx-max-height: 100px;" + "-fx-background-color:LIMEGREEN");
		startButton.setText("START");
		startButton.setDisable(true);
		
	}

	@FXML
	void startSimulation(ActionEvent event) {

		onSimulation = true;
		ableConfigButtons();
		logic.loadPeople();
		loadCircles();
		startButton.setVisible(false);

	}

	public void update() {
		
		draw();
		
	}

	public void loadCircles() {


		for (int i = 0; i < people.size(); i++) {

			ModelCircle current = people.get(i);

			double centerX = (double) current.getPosX();
			double centerY = (double) current.getPosY();
			double radius = (double) current.getRadius();
			
			Color color = Color.MEDIUMSEAGREEN;
			
			if(current.getHealthCondition() == Person.INFECTED) {
				color = Color.SALMON;
			}else if(current.getHealthCondition() == Person.RECOVERED) {
				color = Color.STEELBLUE;
			}
			
			Circle circleJfx = new Circle(centerX, centerY, radius, color);
			circles.add(circleJfx);
			pane.getChildren().add(circleJfx);

		}
		
		logic.startMovementThread();
		
	}

	public void draw() {
		
		for (int i = 0; i < circles.size(); i++) {
			
			Circle current  = circles.get(i);
			double currentX = (double)people.get(i).getPosX();
			double currentY = (double)people.get(i).getPosY();
			
			current.setCenterX(currentX);
			current.setCenterY(currentY);
		
		}
		
		totalPeopleLabel.toFront();
		totalPeopleLabel1.toFront();
		

	}

	public void changeTextF1Handler(MouseEvent e) {

		int infectedPeople = (int) sliderInfectedPeople.getValue();
		iPtxtField.setText("" + infectedPeople);
		logic.setInfectedPeople(infectedPeople);
		updateTotalPeople();
		disableButton();

	}

	public void changeTextF2Handler(MouseEvent e) {

		int healthyPeople = (int) sliderHealthyPeople.getValue();
		hPtxtField.setText("" + healthyPeople);
		logic.setHealthyPeople(healthyPeople);
		updateTotalPeople();
		disableButton();

	}

	public void ableConfigButtons() {

		if (onSimulation) {

			sliderInfectedPeople.setDisable(true);
			sliderHealthyPeople.setDisable(true);
			sliderRecoveredPeople.setDisable(true);
			handWashingChB.setDisable(true);
			maskChB.setDisable(true);
			n95MaskChB.setDisable(true);
			glovesChB.setDisable(true);
			gownChB.setDisable(true);
			startButton.setDisable(true);

		} else {

			sliderInfectedPeople.setDisable(false);
			sliderHealthyPeople.setDisable(false);
			sliderRecoveredPeople.setDisable(false);
			handWashingChB.setDisable(false);
			maskChB.setDisable(false);
			n95MaskChB.setDisable(false);
			glovesChB.setDisable(false);
			gownChB.setDisable(false);
			startButton.setDisable(false);

		}

	}

	public void changeTextF3Handler(MouseEvent e) {

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
		totalPeopleLabel.setText("" + logic.getTotalPeople());
	}

	public void disableButton() {

		if (logic.getTotalPeople() > 1)
			startButton.setDisable(false);
		else
			startButton.setDisable(true);

	}

}
