/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

package model;

import java.util.ArrayList;
import java.util.List;

public class Logic{
	
	// -------------------------------------
	// Constants
	// -------------------------------------
	public final static double LEFT_LIMIT = 390.0;
	public final static double RIGHT_LIMIT = 790.0;
	public final static double UP_LIMIT = 138.0;
	public final static double DOWN_LIMIT = 555.0;
	public final static double MAX_VEL = 4.0;
	public final static double RADIUS = 3;
	
	
	// -------------------------------------
	// Atributtes
	// -------------------------------------
	private int infectedPeople;
	private int recoveredPeople;
	private int healthyPeople;
	private int totalPeople;
	private List<ModelCircle> people;
	
	// -------------------------------------
	// Constructor
	// -------------------------------------
	public Logic() {
		
		
	}

	// -------------------------------------
	// Methods
	// -------------------------------------
	public int getTotalPeople() {
		return infectedPeople + recoveredPeople + healthyPeople;
	}
	
	public void loadPeople() {
		
		people = new ArrayList<ModelCircle>();
		totalPeople = infectedPeople + recoveredPeople + healthyPeople;
		
		for (int i = 0; i < totalPeople; i++) {
			
			int ramdonX = (int) ( (Math.random() * RIGHT_LIMIT) + LEFT_LIMIT);
			int ramdonY = (int) ( (Math.random() * DOWN_LIMIT) + UP_LIMIT);
			int velX    = (int) ( (Math.random() * MAX_VEL) + 1);
			int velY    = (int) ( (Math.random() * MAX_VEL) + 1);
			char condition;
			
			if(i < infectedPeople) {
				condition = Person.INFECTED;	
			}else if(i >= infectedPeople && i < infectedPeople + recoveredPeople) {
				condition = Person.RECOVERED;
			}else {
				condition = Person.HEALTHY;	
			}
			
			people.add(new ModelCircle(condition, ramdonX, ramdonY, velX, velY, (int)RADIUS));
			
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

}
