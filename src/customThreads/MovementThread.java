/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package customThreads;

import model.Logic;

public class MovementThread extends Thread{
	
	// -------------------------------------
	// Atributtes and relationships
	// -------------------------------------
	private Logic logic;
	boolean running;
	
	// -------------------------------------
	// Constructor
	// -------------------------------------
	public MovementThread(Logic logic) {
		this.logic = logic;
		running = true;
	}
	
	// -------------------------------------
	// Methods
	// -------------------------------------
	public void run() {
		
		while(running) {
			
			try {
				
				sleep(30);
				logic.move();
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void kill() {
		running = false;
	}

}
