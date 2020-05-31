/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package controller;

import java.util.ArrayList;

import customThreads.GUIUpdateControlThread;
import javafx.fxml.FXML;
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

	@FXML
	private ImageView pauseImageView;

	// -------------------------------------
	// Atributtes
	// -------------------------------------
	private Logic logic;
	private boolean playButtonAble;
	private boolean pauseButtonAble;
	private boolean stopButtonAble;
	private boolean removeAllCircles;
	private boolean pause;
	private Image playButtonOn;
	private Image playButtonOff;
	private Image stopButtonOn;
	private Image stopButtonOff;
	private Image pauseButtonOn;
	private Image pauseButtonOff;
	private Image pausePlayButton;

	private ArrayList<Circle> circles;
	private ArrayList<ModelCircle> people;

	// -------------------------------------
	// Constructor
	// -------------------------------------
	public GraphicUserInterfaceController() {

		logic = new Logic();
		playButtonAble = false;
		pauseButtonAble = false;
		stopButtonAble = false;
		removeAllCircles = false;
		setPause(false);
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

		playButtonOn = new Image("/images/playButtonOn.png");
		playButtonOff = new Image("/images/playButtonOff.png");
		stopButtonOn = new Image("/images/stopButtonOn.png");
		stopButtonOff = new Image("/images/stopButtonOff.png");
		pauseButtonOn = new Image("/images/pauseButtonOn.png");
		pauseButtonOff = new Image("/images/pauseButtonOff.png");
		pausePlayButton = new Image("/images/pausePlayButton.png");

	}

	@FXML
	void pauseButtonClicked(MouseEvent event) {

		if (pauseButtonAble) {
			
			playButtonAble = false;
			stopButtonAble = true;
			
			if(!isPause()) {
				
				pauseImageView.setImage(pausePlayButton);
				pause = true;
				logic.setPause(true);
				
			}else {
				
				pauseImageView.setImage(pauseButtonOn);
				pause = false;
				logic.setPause(false);
				
			}
			
		}else {
			pauseImageView.setImage(pauseButtonOff);
		}

		/*
		 * if(pauseButtonAble) { pauseImageView.setImage(pausePlayButton); }else {
		 * pauseImageView.setImage(pauseButtonOn); }
		 * 
		 * if(onSimulation) {
		 * 
		 * }
		 */
	}

	@FXML
	void playButtonClicked(MouseEvent event) {

		if (playButtonAble) {
			System.out.println("playButtonClick");

			// When the simulation is running, you can: STOP and Pause
			stopButtonAble = true;
			pauseButtonAble = true;

			if (playButtonAble) {
				ableConfigButtons(false);
				pause=false;
				logic.setPause(false);
				playImageView.setImage(playButtonOff);
				stopImageView.setImage(stopButtonOn);
				pauseImageView.setImage(pauseButtonOn);
				logic.startMovementThread();
				ableConfigButtons(false);
				logic.loadPeople();
				loadCircles();
				playButtonAble = false;
				

			} else {

				playImageView.setImage(playButtonOn);
				pauseImageView.setImage(pauseButtonOff);

			}
		}

	}

	/*
	 * 
	 * if(playButtonAble) {
	 * 
	 * playImageView.setImage(playButtonOff); stopImageView.setImage(stopButtonOn);
	 * pauseImageView.setImage(pauseButtonOn); onSimulation = true; pauseButtonAble
	 * = true; logic.startMovementThread(); ableConfigButtons(); logic.loadPeople();
	 * loadCircles();
	 * 
	 * }else {
	 * 
	 * playImageView.setImage(playButtonOn);
	 * pauseImageView.setImage(pauseButtonOff);
	 * 
	 * }
	 */

	// playButtonAble = (playButtonAble)?false:true;

	@FXML
	void stopButtonClicked(MouseEvent event) {

		if (stopButtonAble) {
			System.out.println("stopButtonClick");

			// The simulation has ended, you can: NOTHING (People has to be more than 1)
			playButtonAble = true;
			stopButtonAble = false;
			pauseButtonAble = false;

			playImageView.setImage(playButtonOn);
			stopImageView.setImage(stopButtonOff);
			pauseImageView.setImage(pauseButtonOff);
			logic.setPeople(new ArrayList<ModelCircle>());
			removeAllCircles = true;
			logic.killMovThread();
			ableConfigButtons(true);
			
			sliderHealthyPeople.setValue(0);
			sliderInfectedPeople.setValue(0);
			sliderRecoveredPeople.setValue(0);
			
			logic.setHealthyPeople(0);
			logic.setInfectedPeople(0);
			logic.setRecoveredPeople(0);
			iPtxtField.setText("");
			rPtxtField.setText("");
			hPtxtField.setText("");
			updateTotalPeople();

		}
		/*
		 * if(onSimulation) {
		 * 
		 * onSimulation = false; playButtonAble = true;
		 * playImageView.setImage(playButtonOn); stopImageView.setImage(stopButtonOff);
		 * pauseImageView.setImage(pauseButtonOff); logic.setPeople(new
		 * ArrayList<ModelCircle>()); removeAllCircles = true; onSimulation = false;
		 * logic.killMovThread();
		 * 
		 * }
		 */

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

			if (current.getHealthCondition() == Person.INFECTED) {
				color = Color.SALMON;
			} else if (current.getHealthCondition() == Person.RECOVERED) {
				color = Color.STEELBLUE;
			}

			Circle circleJfx = new Circle(centerX, centerY, radius, color);
			circles.add(circleJfx);
			pane.getChildren().add(circleJfx);

		}

	}

	public void draw() {

		for (int i = 0; i < circles.size(); i++) {

			Circle current = circles.get(i);
			double currentX = (double) people.get(i).getPosX();
			double currentY = (double) people.get(i).getPosY();

			checkColor(current, people.get(i));
			current.setCenterX(currentX);
			current.setCenterY(currentY);
			// System.out.println("cX "+currentX+" cY: "+currentY);
		}

		// System.out.println("");

		if (removeAllCircles) {

			for (int i = 0; i < circles.size(); i++) {

				Circle current = circles.get(i);
				pane.getChildren().remove(current);

			}

			circles = new ArrayList<Circle>();
			removeAllCircles = false;

		}

		totalPeopleLabel.toFront();
		totalPeopleLabel1.toFront();

	}

	public void checkColor(Circle circle, ModelCircle modelCircle) {

		if (modelCircle.getHealthCondition() == Person.INFECTED) {
			circle.setFill(Color.SALMON);
		} else if (modelCircle.getHealthCondition() == Person.HEALTHY) {
			circle.setFill(Color.MEDIUMSEAGREEN);
		} else {
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

	public void ableConfigButtons(boolean able) {

		if (!able) {

			sliderInfectedPeople.setDisable(true);
			sliderHealthyPeople.setDisable(true);
			sliderRecoveredPeople.setDisable(true);
			handWashingChB.setDisable(true);
			maskChB.setDisable(true);
			n95MaskChB.setDisable(true);
			glovesChB.setDisable(true);
			gownChB.setDisable(true);

		} else {

			sliderInfectedPeople.setDisable(false);
			sliderHealthyPeople.setDisable(false);
			sliderRecoveredPeople.setDisable(false);
			handWashingChB.setDisable(false);
			maskChB.setDisable(false);
			n95MaskChB.setDisable(false);
			glovesChB.setDisable(false);
			gownChB.setDisable(false);

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
			//ableConfigButtons(true);

		} else {

			playImageView.setImage(playButtonOff);
			playButtonAble = false;
			//ableConfigButtons(false);

		}

	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

}
