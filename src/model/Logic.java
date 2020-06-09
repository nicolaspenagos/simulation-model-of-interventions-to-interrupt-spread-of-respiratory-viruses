/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

package model;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import controller.GraphicUserInterfaceController;

import java.util.List;
import java.util.Random;

import customThreads.ChronometerThread;
import customThreads.MovementThread;

public class Logic {

	// -------------------------------------
	// Constants
	// -------------------------------------
	public final static double LEFT_LIMIT = 398.0;
	public final static double RIGHT_LIMIT = 1185.0;
	public final static double UP_LIMIT = 166.0;
	public final static double DOWN_LIMIT = 690.0;

	public final static double MAX_VEL = 4.0;
	public final static double TOLERANCE = 4;

	public final static double IE_FREQUENT_HANDWAHING = 0.55;
	public final static double IE_WEARING_MASK = 0.68;
	public final static double IE_WEARING_N95_MASK = 0.91;
	public final static double IE_WEARING_GLOVES = 0.57;
	public final static double IE_WEARING_GOWN = 0.77;
	public final static double IE_WEARING_ALL = 0.91;

	public final static int OPTION_FHW = 0;
	public final static int OPTION_M = 1;
	public final static int OPTION_N95M = 2;
	public final static int OPTION_GLVS = 3;
	public final static int OPTION_GWN = 4;
	public final static int OPTION_WALL = 5;

	// -------------------------------------
	// Atributtes and relationships
	// -------------------------------------
	private int infectedPeople;
	private int recoveredPeople;
	private int healthyPeople;
	private int totalPeople;
	private int radius;
	private boolean pause;
	private boolean deleteAllPeople;
	private int option;
	private double probability;
	private boolean simulationEnded;

	private List<ModelCircle> people;
	private MovementThread movementThread;
	private Chronometer chronometer;
	private ChronometerThread chronometerThread;
	private Data data;

	// -------------------------------------
	// Constructor
	// -------------------------------------
	public Logic() {

		people = new ArrayList<ModelCircle>();
		deleteAllPeople = false;
		option = -1;
		chronometer = new Chronometer();
		setSimulationEnded(false);
		data = new Data();

	}

	// -------------------------------------
	// Methods
	// -------------------------------------
	public int getTotalPeople() {
		return infectedPeople + recoveredPeople + healthyPeople;
	}
	
	public void startChronometerThread(GraphicUserInterfaceController gui) {
		
		chronometerThread = new ChronometerThread(getChronometer(), gui);
		chronometerThread.setDaemon(true);
		chronometerThread.start();
		
	}

	public void loadPeople() {

		people = new ArrayList<ModelCircle>();
		totalPeople = infectedPeople + recoveredPeople + healthyPeople;
		Random r = new Random();

		if (totalPeople < 500) {
			radius = 3;
		} else if (totalPeople < 1500) {
			radius = 2;
		} else {
			radius = 1;
		}

		for (int i = 0; i < totalPeople; i++) {

			int ramdonX = ThreadLocalRandom.current().nextInt((int) LEFT_LIMIT, (int) RIGHT_LIMIT + 1);
			int ramdonY = ThreadLocalRandom.current().nextInt((int) UP_LIMIT, (int) DOWN_LIMIT + 1);
			int velX = ThreadLocalRandom.current().nextInt(1, (int) MAX_VEL + 1);
			int velY = ThreadLocalRandom.current().nextInt(1, (int) MAX_VEL + 1);
			char condition;

			if (r.nextBoolean()) {
				velX *= -1;
			}

			if (r.nextBoolean()) {
				velY *= -1;
			}

			boolean setChronometer = false;
			
			if (i < infectedPeople) {
				
				condition = Person.INFECTED;
				setChronometer = true;
				
			} else if (i >= infectedPeople && i < infectedPeople + recoveredPeople) {
				condition = Person.RECOVERED;
			} else {
				condition = Person.HEALTHY;
			}

			people.add(new ModelCircle(condition, ramdonX, ramdonY, velX, velY, radius));
			
			if(setChronometer)
				people.get(i).setInfectionTime(new Chronometer());
		
		}
		
	}

	public void move() {
		
		endSimulation();
		
		if (!pause) {

			for (int i = 0; i < people.size(); i++) {

				ModelCircle current = people.get(i);
				current.move();
				contact(current, i);
				recoveryCheck(current);
				
			}
			
			if (deleteAllPeople) {

				people = new ArrayList<ModelCircle>();
				deleteAllPeople = false;

			}

		}
		
	}

	public void recoveryCheck(ModelCircle circle) {
		
		if(circle.getHealthCondition()==Person.INFECTED){
			
			int time = ((chronometer.getMin()*60)+chronometer.getSec());
			int timeCircle = ((circle.getInfectionTime().getMin()*60)+circle.getInfectionTime().getSec());
			
			if(14<time-timeCircle) {
				
				circle.setHealthCondition(Person.RECOVERED);
				recoveredPeople++;
				infectedPeople--;
				
			}	
		}
			
	}
	
	public void killMovThread() {
		movementThread.kill();
	}

	public void endSimulation() {
		
		if(infectedPeople == 0) {
			pause = true;
			chronometer.setPause(true);
			setSimulationEnded(true);
		}
		if(chronometer.getMin()>10) {
			pause = true;
			chronometer.setPause(true);
			setSimulationEnded(true);
		}
		
	}
	
	public void contact(ModelCircle current, int index) {

		for (int i = 0; i < people.size(); i++) {

			if (people.size() != 0) {
				if (i != index) {

					ModelCircle other = people.get(i);
					double dist = Math.sqrt((current.getPosX() - other.getPosX()) * (current.getPosX() - other.getPosX())
									+ (current.getPosY() - other.getPosY()) * (current.getPosY() - other.getPosY()));

					if (dist <= current.getRadius() + other.getRadius()) {

						other.bounce();
						current.bounce();
						
						double contagion = Math.random();
					
						if((probability - contagion<0)) {
						
							if (other.getHealthCondition() == Person.INFECTED
									&& current.getHealthCondition() == Person.HEALTHY) {

								current.setHealthCondition(Person.INFECTED);
								current.setInfectionTime(new Chronometer(chronometer.getMin(), chronometer.getSec(), chronometer.getCentiSec(), true));
								infectedPeople++;
								healthyPeople--;

							} else if (current.getHealthCondition() == Person.INFECTED
									&& other.getHealthCondition() == Person.HEALTHY) {

								other.setHealthCondition(Person.INFECTED);
								other.setInfectionTime(new Chronometer(chronometer.getMin(), chronometer.getSec(), chronometer.getCentiSec(), true));
								infectedPeople++;
								healthyPeople--;

							}
							
						}

						
					}
				}
			}
		}
	}

	public void startMovementThread() {

		movementThread = new MovementThread(this);
		movementThread.setDaemon(true);
		movementThread.start();
	
	}

	public void killChronometerThread() {
		chronometerThread.kill();
	}
	
	public void setProbability() {
		
		switch(option) {
		
			case OPTION_FHW:
				probability = IE_FREQUENT_HANDWAHING;
				break;
			case OPTION_GLVS:
				probability = IE_WEARING_GLOVES;
				break;
			case OPTION_GWN:
				probability = IE_WEARING_GOWN;
				break;
			case OPTION_M:
				probability = IE_WEARING_MASK;
				break;
			case OPTION_N95M:
				probability = IE_WEARING_N95_MASK;
				break;
			case OPTION_WALL:
				probability = IE_WEARING_ALL;
				break;
			case -1:
				probability = 0;
				break;
		
		}
		
	}

	// -------------------------------------
	// Getters and Setters
	// -------------------------------------
	public int getInfectedPeople() {
		return infectedPeople;
	}

	public void setInfectedPeople(int infectedPeople) {
		this.infectedPeople = infectedPeople;
	}

	public int getRecoveredPeople() {
		return recoveredPeople;
	}

	public void setRecoveredPeople(int recoveredPeople) {
		this.recoveredPeople = recoveredPeople;
	}

	public int getHealthyPeople() {
		return healthyPeople;
	}

	public void setHealthyPeople(int healthyPeople) {
		this.healthyPeople = healthyPeople;
	}

	public List<ModelCircle> getPeople() {
		return people;
	}

	public void setPeople(List<ModelCircle> people) {
		this.people = people;
	}

	public boolean isDeleteAllPeople() {
		return deleteAllPeople;
	}

	public void setDeleteAllPeople(boolean deleteAllPeople) {
		this.deleteAllPeople = deleteAllPeople;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}

	public Chronometer getChronometer() {
		return chronometer;
	}

	public void setChronometer(Chronometer chronometer) {
		this.chronometer = chronometer;
	}

	public boolean isSimulationEnded() {
		return simulationEnded;
	}

	public void setSimulationEnded(boolean simulationEnded) {
		this.simulationEnded = simulationEnded;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}
