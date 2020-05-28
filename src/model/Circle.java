/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

package model;

public class Circle extends Person{
	
	
	// -------------------------------------
	// Atributtes
	// -------------------------------------
	private int posX;
	private int posY;
	private int velX;
	private int velY;
	
	
	// -------------------------------------
	// Constructor
	// -------------------------------------
	public Circle(char healthCondition) {
		super(healthCondition);
		// TODO Auto-generated constructor stub
	}

	
	// -------------------------------------
	// Getters and Setters
	// -------------------------------------
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

}
