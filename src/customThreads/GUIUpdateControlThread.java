/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicol�s Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package customThreads;

import controller.GraphicUserInterfaceController;
import javafx.application.Platform;

public class GUIUpdateControlThread extends Thread{

	// -------------------------------------
	// Constants
	// -------------------------------------
	private final static long UPDATE_SLEEP_TIME = 5;
	
	// -------------------------------------
	// Atributtes and relationships
	// -------------------------------------
	private GraphicUserInterfaceController graphicUserInterfaceController;
	
	// -------------------------------------
	// Constructor
	// -------------------------------------
	public GUIUpdateControlThread(GraphicUserInterfaceController graphicUserInterfaceController) {
		this.graphicUserInterfaceController = graphicUserInterfaceController;
	}

	// -------------------------------------
	// Methods
	// -------------------------------------
	public void run() {
		
		while(true) {
			
			GUIUpdateRunnable gUR = new GUIUpdateRunnable(graphicUserInterfaceController);
			Platform.runLater(gUR);
			
			try {
				sleep(UPDATE_SLEEP_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
}
