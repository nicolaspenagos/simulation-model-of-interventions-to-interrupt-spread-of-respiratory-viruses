/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

package model;

import java.util.ArrayList;
import java.util.List;

public class Logic {
	
	// -------------------------------------
	// Atributtes
	// -------------------------------------
	private int infectedPeople;
	private int recoveredPeople;
	private int healthyPeople;
	private int totalPeople;
	private List<Person> people;
	
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
		
		people = new ArrayList<Person>();
		totalPeople = infectedPeople + recoveredPeople + healthyPeople;
		Circle c;
		
		for (int i = 0; i < totalPeople; i++) {
			
			if(i < infectedPeople) {
				
				c = new Circle(Person.INFECTED);
				people.add(c);
				
			}else if(i >= infectedPeople && i < infectedPeople + recoveredPeople) {
				
				c = new Circle(Person.RECOVERED);
				people.add(c);
			
			}else {
				
				c = new Circle(Person.HEALTHY);
				people.add(c);
				
			}
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

}
