package gates;

import maths.*;


public class Register {
	
	//public ComplexNum[] amplitudes;
	private StateVector stateVector;
	private int numQubits;
	private int size;
	public boolean decimal;   // print out in decimal or binary
		

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
	
	public void setGroundState(){
		System.out.println("Initialising Register to ground state...");
		getStateVector().setGroundState();
	}
	
	public void updateStateVector(Matrix m){
		
		StateVector updated = new StateVector(m.getNumRows(), decimal);
		for(int i = 0; i < m.getNumRows(); i++){
			updated.setElem(i, 0, m.getElem(i,0));
		}
		this.setStateVector(updated);
	}
	

}
