/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicol�s Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package model;

public abstract class Person {
	
	// -------------------------------------
	// Constants
	// -------------------------------------
	public final static char HEALTHY = 'H';
	public final static char INFECTED = 'I';
	public final static char RECOVERED = 'R';
	
	// -------------------------------------
	// Atributtes
	// -------------------------------------
	private char healthCondition;
	private Chronometer infectionTime;
	private boolean quarantine;
	
	// -------------------------------------
	// Constructor
	// -------------------------------------
	public Person(char healthCondition, boolean quarantine) {
		
		this.healthCondition = healthCondition;
		this.quarantine = quarantine;
		infectionTime = new Chronometer(0,Integer.MIN_VALUE, 0, false);
		
	}

	// -------------------------------------
	// Methods
	// -------------------------------------
	public void move() {
	}
	
	// -------------------------------------
	// Getters and Setters
	// -------------------------------------
	public char getHealthCondition() {
		return healthCondition;
	}

	public void setHealthCondition(char healthCondition) {
		this.healthCondition = healthCondition;
	}

	public Chronometer getInfectionTime() {
		return infectionTime;
	}

	public void setInfectionTime(Chronometer infectionTime) {
		this.infectionTime = infectionTime;
	}

	public boolean isQuarantine() {
		return quarantine;
	}

	public void setQuarantine(boolean quarantine) {
		this.quarantine = quarantine;
	}
	
	
	
	
	

}
