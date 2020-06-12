/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SimulationData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//-------------------------------------
    // Atributtes 
    //-------------------------------------
	private int infectedPeopleAtDay0;
	private int healthyPeopleAtDay0;
	private int recoveredPeopleAtDay0;
	private int infectedPeopleAtDayN;
	private int healthyPeopleAtDayN;
	private int recoveredPeopleAtDayN;
	private int interventionOption;
	private double interventionEffectiveness;
	private String time;
	private int days;
	private ArrayList<Integer[]> graph;
	private Calendar cDate;
	private String date;
	private int id;
	
	//-------------------------------------
    // Constructor
    //-------------------------------------
	public SimulationData(int infectedPeopleAtDay0, int healthyPeopleAtDay0, int recoveredPeopleAtDay0, int infectedPeopleAtDayN, int healthyPeopleAtDayN, int recoveredPeopleAtDayN, int interventionOption, double interventionEffectiveness, String time, int days, ArrayList<Integer[]> graph, GregorianCalendar cDate, int id) {
		
		this.infectedPeopleAtDay0 = infectedPeopleAtDay0;
		this.healthyPeopleAtDay0 = healthyPeopleAtDay0;
		this.recoveredPeopleAtDay0 = recoveredPeopleAtDay0;
		this.infectedPeopleAtDayN = infectedPeopleAtDayN;
		this.healthyPeopleAtDayN = healthyPeopleAtDayN;
		this.recoveredPeopleAtDayN = recoveredPeopleAtDayN;
		this.interventionOption = interventionOption;
		this.interventionEffectiveness = interventionEffectiveness;
		this.time = time;
		this.days = days;
		this.graph = graph;
		this.id = id;
		this.cDate = cDate;
		Calendar d = (Calendar) cDate;
		this.setDate("" + d.getTime());
		
	}

    //-------------------------------------
    // Getters and Setters
    //-------------------------------------
	public int getInfectedPeopleAtDay0() {
		return infectedPeopleAtDay0;
	}

	public void setInfectedPeopleAtDay0(int infectedPeopleAtDay0) {
		this.infectedPeopleAtDay0 = infectedPeopleAtDay0;
	}

	public int getHealthyPeopleAtDay0() {
		return healthyPeopleAtDay0;
	}

	public void setHealthyPeopleAtDay0(int healthyPeopleAtDay0) {
		this.healthyPeopleAtDay0 = healthyPeopleAtDay0;
	}

	public int getRecoveredPeopleAtDay0() {
		return recoveredPeopleAtDay0;
	}

	public void setRecoveredPeopleAtDay0(int recoveredPeopleAtDay0) {
		this.recoveredPeopleAtDay0 = recoveredPeopleAtDay0;
	}

	public int getHealthyPeopleAtDayN() {
		return healthyPeopleAtDayN;
	}
	
	public void setHealthyPeopleAtDayN(int healthyPeopleAtDayN) {
		this.healthyPeopleAtDayN = healthyPeopleAtDayN;
	}

	public int getInfectedPeopleAtDayN() {
		return infectedPeopleAtDayN;
	}

	public void setInfectedPeopleAtDayN(int infectedPeopleAtDayN) {
		this.infectedPeopleAtDayN = infectedPeopleAtDayN;
	}

	public int getRecoveredPeopleAtDayN() {
		return recoveredPeopleAtDayN;
	}

	public void setRecoveredPeopleAtDayN(int recoveredPeopleAtDayN) {
		this.recoveredPeopleAtDayN = recoveredPeopleAtDayN;
	}

	public int getInterventionOption() {
		return interventionOption;
	}

	public void setInterventionOption(int interventionOption) {
		this.interventionOption = interventionOption;
	}

	public double getInterventionEffectiveness() {
		return interventionEffectiveness;
	}

	public void setInterventionEffectiveness(double interventionEffectiveness) {
		this.interventionEffectiveness = interventionEffectiveness;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public ArrayList<Integer[]> getGraph() {
		return graph;
	}

	public void setGraph(ArrayList<Integer[]> graph) {
		this.graph = graph;
	}

	public Calendar getCDate() {
		return cDate;
	}

	public void setDate(Calendar cDate) {
		this.cDate = cDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getcDate() {
		return cDate;
	}

	public void setcDate(Calendar cDate) {
		this.cDate = cDate;
	}

	public void setDate(String date) {
		this.date = date;
	} 
	
	public String getDate() {
		return date;
	}

}
