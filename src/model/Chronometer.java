/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package model;

public class Chronometer {
	
	//-------------------------------------
    // Atributtes 
    //-------------------------------------
    private String time;
    private int min;
    private int sec;
    private int centiSec;
    private boolean pause;

    //-------------------------------------
    // Constructor
    //-------------------------------------
    public Chronometer() {
        time="0:00:00";
    }
    
    public Chronometer(int min, int sec, int centiSec, boolean pause) {
    	
        this.min = min;
        this.sec = sec;
        this.centiSec = centiSec;
        this.pause = pause;
        
    }

    //-------------------------------------
    // Methods
    //-------------------------------------
    public void changeTime(String time) {
        this.time=time;
    }
    
    public void reStart() {
    	
    	time = "0:00:00";
    	sec = 0;
    	centiSec = 0;
    	min = 0;
    	
    	if(sec<10)
    		time = min + ":0" + sec + ":" + centiSec;
    	else
    		time = min + ":" + sec + ":" + centiSec;
    	
    }
    
    //-------------------------------------
    // Getters and Setters
    //-------------------------------------
    public String getTime() {
        return time;
    }

	public int getCentiSec() {
		return centiSec;
	}

	public void setCentiSec(int centiSec) {
		this.centiSec = centiSec;
	}

	public int getSec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}
	
}
