package gates;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * A gate object is a member of a linked list of gates. Each gate has a field that points to the next gate
 *  in a linked list.
 * 
 * @author Ewan Hemmingway<br>Ian Sullivan<br>James Vokes
 *
 */

public abstract class Gate implements GateInterface{	


	/**
	 * number of qubits the gate applies to*/
	protected int numQubits;
	/**
	 * the bit that the gate applies to*/
	private int targetBit;
	/**
	 * array of control bits*/
	private int[] ctrl;
	/**
	 * the search element that Grover's algorithm searches for*/
	private int searchedElem;
	/**
	 * the next gate in a linked list of gates*/
	private Gate nextGate;

	/**
	 * 
	 * @param n number of qubits that the gate applies to
	 * @param target the bit that the gate applies to*
	 * @param ctrl array of control bits
	 * @param searchedElem the search element that Grover's algorithm searches for
	 */
	public Gate(int n, int target, int[] ctrl, int searchedElem){
		numQubits =n;
		targetBit = target;
		this.searchedElem = searchedElem;
		this.ctrl=ctrl;
		setNextGate(null);
	}
			
	/**
	 * applies the gate to a register	 */
	public abstract void applyGate(Register q);
	/**
	 * sets the number of qubits that the gate applies to
	 * @param n the number of qubits that the gate applies to
	 */
	public abstract void setNumQubits(int n);
	/**
	 * returns the name of the gate	 */
	public abstract String getName();
	
	// checks input is within range
	/**
	 * returns true if the gate's parameters are valid */
	public boolean checkParams(){
		boolean okay = true;
		if(targetBit > numQubits - 1 || targetBit < -1) okay = false;
		for(int i : ctrl){
			if(i > numQubits - 1 || i < -1) okay = false;
		}
		if(searchedElem < 0 || searchedElem > (int) Math.pow(2, numQubits) - 1) okay = false;
		return okay;
	}

	public int[] getCtrl() {
		return ctrl;
	}
	
	public int getCtrl(int n){
		return ctrl[n];
	}
	
	public int getSearchedElem() {
		return searchedElem;
	}

	public Gate getNextGate() {
		return nextGate;
	}

	public int getTargetBit() {
		return targetBit;
	}
	
	public void setTarget(int newTargetBit){
		this.setTargetBit(newTargetBit);
	}

	public void setNextGate(Gate a) {
		this.nextGate = a;
	}

	/**
	 * adds a gate to the end of the linked list
	 * @param a gate to add to the end of the list
	 */
	public void addToEnd(Gate a){
		   
        if (getNextGate()==null){
                setNextGate(a);
        }
        else{
                getNextGate().addToEnd(a);
        }
	}
	/**
	 * The method returns an image to represent the gate in the circuit GUI. If this method is not overridden, it will return a default image.
	 * 
	 * @return the image that the gate represents
	 */
	public Image getImage(){
		return Toolkit.getDefaultToolkit().getImage("src/default.GIF");
	}
	
	
	void setTargetBit(int targetBit) {
		this.targetBit = targetBit;
	}


	void setSearchedElem(int searchedElem) {
		this.searchedElem = searchedElem;
	}


	int getNumQubits() {
		return numQubits;
	}


	void setCtrl(int[] ctrl) {
		this.ctrl = ctrl;
	}
	
	
}
