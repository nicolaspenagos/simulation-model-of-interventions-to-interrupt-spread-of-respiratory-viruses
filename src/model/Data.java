/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// -------------------------------------
	// Atributtes and relationships
	// -------------------------------------
	private ArrayList<SimulationData> data;
	private int counter;

	// -------------------------------------
	// Constructor
	// -------------------------------------
	public Data() {
		
		loadData();
		counter = data.size();
		
	}

	// -------------------------------------
	// Methods
	// -------------------------------------
	@SuppressWarnings("unchecked")
	public void loadData() {
		
		File file = new File("data/data.dat");
		
		if(file.exists()) {
			
			try {
				
				ObjectInputStream io = new ObjectInputStream(new FileInputStream(file));
				data = (ArrayList<SimulationData>) io.readObject();
				io.close();
				System.out.println("Cargo");
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			data = new ArrayList<SimulationData>();
		}
		
		System.out.println(data.size());
	}
	
	public void saveData() {
		
		try {
			
			ObjectOutputStream io = new ObjectOutputStream(new FileOutputStream(new File("data/data.dat")));
			io.writeObject(data);
			io.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// -------------------------------------
	// Getters and Setters
	// -------------------------------------
	public ArrayList<SimulationData> getData() {
		return data;
	}

	public void setData(ArrayList<SimulationData> data) {
		this.data = data;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

}
