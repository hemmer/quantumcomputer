package gates;

import maths.*;

/**
 * This object represents a quantum register
 * 
 * @author Ewan Hemmingway<br>Ian Sullivan<br>James Vokes
 *
 */

public class Register {
	
	//public ComplexNum[] amplitudes;
	/**
	 * stores a vector that represents the register	 */
	private StateVector stateVector;
	/**
	 * number of qubits in the register	 */
	private int numQubits;
	/**
	 * number of states in the register	 */
	private int size;
	/**
	 * if this is true the states will be represented by decimal number and if it is false
	 * it will be represented by binary	 */
	public boolean decimal;   // print out in decimal or binary
		

/**
 * Creates a quantum register
 * 
 * @param inNumQubits the number of qubits in the register
 * @param dec if this is true the states will be represented by decimal number and if it is false it will be represented by binary
 */
	public Register(int inNumQubits, boolean dec){
		
		numQubits = inNumQubits;
		setSize((int) Math.pow(2, getNumQubits()));
		decimal = dec;
		setStateVector(new StateVector(getSize(), decimal));  // set array to 2^numQubits
		getStateVector().setGroundState();
	}
	


	public String toString(){

		return getStateVector().toString();
	}
	
	/**
	 * Initialises the register to the ground state	 */
	public void setGroundState(){
		System.out.println("Initialising Register to ground state...");
		getStateVector().setGroundState();
	}
	
	/**
	 * Sets the register with the given values
	 * 
	 * @param m an (nx1) matrix that represents the register
	 */
	public void updateStateVector(Matrix m){
		
		StateVector updated = new StateVector(m.getNumRows(), decimal);
		for(int i = 0; i < m.getNumRows(); i++){
			updated.setElem(i, 0, m.getElem(i,0));
		}
		this.setStateVector(updated);
	}
	

	public void setStateVector(StateVector x) {
		stateVector = x;
	}
	
	public StateVector getStateVector(){
		return stateVector;
	}
	
	public int getNumQubits(){
		return numQubits;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public int getSize() {
		return size;
	}
	

}
