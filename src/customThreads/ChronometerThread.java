/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package customThreads;

import model.Chronometer;

public class ChronometerThread extends Thread {
	
	// -------------------------------------
	// Atributtes and relationships
	// -------------------------------------
	private Chronometer chronometer;
	private boolean running;

	// -------------------------------------
	// Constructor
	// -------------------------------------
	public ChronometerThread(Chronometer chronometer) {
		
		this.chronometer = chronometer;
		running = true;
		
	}

	// -------------------------------------
	//  Methods 
	// -------------------------------------
	@Override
	public void run() {
		
		for (int i = 0; i < 60 && running; i++) {
			for (int j = 0; j < 60 && running; j++) {
				for (int k = 0; k < 100 && running; k++) {
					
					chronometer.setCentiSec(k);
					chronometer.setSec(j);
					chronometer.setMin(i);
					 
					if (j < 10) 
						chronometer.changeTime(i + ":0" + j + ":" + k);
					else
						chronometer.changeTime(i + ":" + j + ":" + k);
					
					try {
						
						sleep(10);
						while(chronometer.isPause()) {
							sleep(10);
						}	
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}
		
	}
	
	public void kill() {
		running = false;
	}
	
}