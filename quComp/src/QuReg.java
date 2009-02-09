import maths.*;


public class QuReg {
	
	//public ComplexNum[] amplitudes;
	public StateVector v;
	int numQubits;
	public int size;
	boolean decimal;   // print out in decimal or binary
		
	public QuReg(int inNumQubits, boolean dec){
		
		numQubits = inNumQubits;
		size = (int) Math.pow(2, numQubits);
		decimal = dec;
		v = new StateVector(size, decimal);  // set array to 2^numQubits
		v.setGroundState();
	}
	
	
	public String toString(){

		return v.toString();
	}
	
	public void setGroundState(){
		System.out.println("Initialising Register to ground state...");
		v.setGroundState();
	}
	
	public void updateStateVector(DenseMatrix m){
		
		StateVector updated = new StateVector(m.getNumRows(), decimal);
		for(int i = 0; i < m.getNumRows(); i++){
			updated.setElem(i, 0, m.getElem(i,0));
		}
		this.v = updated;
	}
	
	public StateVector getStateVector(){
		return v;
	}
	
	public int getNumQubits(){
		return numQubits;
	}
}
