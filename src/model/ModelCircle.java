/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

package model;

public class ModelCircle extends Person {

	// -------------------------------------
	// Atributtes
	// -------------------------------------
	private int posX;
	private int posY;
	private int velX;
	private int velY;
	private int radius;

	// -------------------------------------
	// Constructor
	// -------------------------------------
	public ModelCircle(char healthCondition, int posX, int posY, int velX, int velY, int radius) {
		
		super(healthCondition);
		
		this.posX   = posX;
		this.posY   = posY;
		this.velX   = velX;
		this.velY   = velY;
		this.radius = radius;
		
	}

	public void move() {
		
		//System.out.println("X:"+posX+" Y: "+ posY);
		
		posY += velY;	
		
		if(posY<Logic.UP_LIMIT || posY>Logic.DOWN_LIMIT) {
			velY *= -1;
		
		}
		
		posX += velX;
		
		if(posX<Logic.LEFT_LIMIT|| posX>Logic.RIGHT_LIMIT) {
			velX *= -1;
			//System.out.println("rebote en X");
		}
		
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

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

}
