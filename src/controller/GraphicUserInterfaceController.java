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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	
    @FXML
    private ImageView playImageView;
    
    @FXML
    private ImageView stopImageView;

	// -------------------------------------
	// Atributtes
	// -------------------------------------
	private Logic logic;
	private boolean onSimulation;
	private boolean playButtonAble;
	private boolean removeAllCircles;
	private Image playButtonOn;
	private Image playButtonOff;
	private Image stopButtonOn;
	private Image stopButtonOff;
	
	private ArrayList<Circle> circles;
	private ArrayList<ModelCircle> people;

	// -------------------------------------
	// Constructor
	// -------------------------------------
	public GraphicUserInterfaceController() {

		logic = new Logic();
		onSimulation = false;
		playButtonAble = false;
		removeAllCircles = false;
		
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
		
		playButtonOn = new Image("/images/playButtonOn.png");
		playButtonOff = new Image("/images/playButtonOff.png");
		stopButtonOn = new Image("/images/stopButtonOn.png");
		stopButtonOff = new Image("/images/stopButtonOff.png");
		
	}

	@FXML
	void startSimulation(ActionEvent event) {

		
		startButton.setVisible(false);

	}


    @FXML
    void playButtonClicked(MouseEvent event) {
    
    	if(playButtonAble) {
    		
    		playImageView.setImage(playButtonOff);
    		stopImageView.setImage(stopButtonOn);
    		onSimulation = true;
    		logic.startMovementThread();
    		ableConfigButtons();
    		logic.loadPeople();
    		loadCircles();
    		
    	}else {
    		playImageView.setImage(playButtonOn);
    	}
    	
    	playButtonAble = (playButtonAble)?false:true;
    	
    }
    
    @FXML
    void stopButtonClicked(MouseEvent event) {
    	
    	if(onSimulation) {
    		
    		onSimulation = false;
    		playButtonAble = true;
    		playImageView.setImage(playButtonOn);
    		stopImageView.setImage(stopButtonOff);
    		logic.setPeople(new ArrayList<ModelCircle>());
    		removeAllCircles = true;
    		onSimulation = false;
    		logic.killMovThread();
    		
    	}
    	
    }
    
	public void update() {
		
		draw();
		
	}

	public void loadCircles() {

		people = (ArrayList<ModelCircle>) logic.getPeople();

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
		
	}

	public void draw() {
		
		for (int i = 0; i < circles.size(); i++) {
			
			Circle current  = circles.get(i);
			double currentX = (double)people.get(i).getPosX();
			double currentY = (double)people.get(i).getPosY();
			
			checkColor(current, people.get(i));
			current.setCenterX(currentX);
			current.setCenterY(currentY);
			//System.out.println("cX "+currentX+" cY: "+currentY);
		}
		
	//	System.out.println("");
		
		if(removeAllCircles) {
		
			for (int i = 0; i < circles.size(); i++) {
				
				Circle current  = circles.get(i);
				pane.getChildren().remove(current);
				
			}
			
			circles = new ArrayList<Circle>();
			removeAllCircles = false;
	
		}
		
		totalPeopleLabel.toFront();
		totalPeopleLabel1.toFront();
		

	}
	
	public void checkColor(Circle circle, ModelCircle modelCircle) {
		
		if(modelCircle.getHealthCondition() == Person.INFECTED) {
			circle.setFill(Color.SALMON);
		}else if(modelCircle.getHealthCondition() == Person.HEALTHY) {
			circle.setFill(Color.MEDIUMSEAGREEN);
		}else {
			circle.setFill(Color.STEELBLUE);
		}
		
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

		iPtxtField.setEditable(true);
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

		if (logic.getTotalPeople() >= 1) {
			
			playImageView.setImage(playButtonOn);
			playButtonAble = true;
			
		}else {
			
			playImageView.setImage(playButtonOff);
			playButtonAble = false;
			
		}	

	}

}
