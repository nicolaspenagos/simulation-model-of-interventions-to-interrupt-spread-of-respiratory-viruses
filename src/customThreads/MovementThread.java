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
	
	// -------------------------------------
	// Constructor
	// -------------------------------------
	public MovementThread(Logic logic) {
		this.logic = logic;
	}
	
	// -------------------------------------
	// Methods
	// -------------------------------------
	public void run() {
		
		while(true) {
			
			try {
				
				sleep(30);
				logic.move();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
