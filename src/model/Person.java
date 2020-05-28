/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
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
	
	// -------------------------------------
	// Constructor
	// -------------------------------------
	public Person(char healthCondition) {
		this.healthCondition = healthCondition;
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
	
	
	
	
	

}
