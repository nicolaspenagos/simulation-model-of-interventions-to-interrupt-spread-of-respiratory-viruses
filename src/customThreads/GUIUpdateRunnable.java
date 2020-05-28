/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package customThreads;

import controller.GraphicUserInterfaceController;

public class GUIUpdateRunnable implements Runnable{

	// -------------------------------------
	// Atributtes and relationships
	// -------------------------------------
	private GraphicUserInterfaceController graphicUserInterfaceController;
	
	// -------------------------------------
	// Constructor
	// -------------------------------------
	public GUIUpdateRunnable(GraphicUserInterfaceController graphicUserInterfaceController){
		this.graphicUserInterfaceController = graphicUserInterfaceController;
	}

	// -------------------------------------
	// Methods
	// -------------------------------------
	@Override
	public void run() {
		graphicUserInterfaceController.update();
	}

}
