/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package customThreads;

import controller.GraphicUserInterfaceController;

public class GUIUpdateRunnable implements Runnable{

	private GraphicUserInterfaceController graphicUserInterfaceController;
	
	public GUIUpdateRunnable(GraphicUserInterfaceController graphicUserInterfaceController){
		this.graphicUserInterfaceController = graphicUserInterfaceController;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		graphicUserInterfaceController.update();
	}

}
