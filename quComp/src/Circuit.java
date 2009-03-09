import gates.CustomGate;
import gates.DenseGate;
import gates.Gate;
import gates.Register;
import maths.Matrix;

/**
 * The circuit class is an object that stores a register and points to a linked list of gates.
 * 
 * @author Ewan Hemmingway, Ian Sullivan, James Vokes
 * 
 *
 */
public class Circuit implements CircuitInterface {

	/**
	 * Holds the next gate to be applied in the circuit.
	 */
	private Gate nextGate;
	/**
	 * Holds the first gate in the circuit.
	 */
	private Gate firstGate;
	/**
	 * Holds the circuit register.
	 */
	private Register reg;
	/**
	 * Holds the total number of gates in the circuit.
	 */
	private int total;
	/**
	 * Holds the position of the current gate in the list. Numbering starts from 1.
	 */
	private int current;
	/**
	 * Holds a gate that represents the whole circuit
	 */
	private Gate overallGate;
	private boolean display;
	Window frame;
	
	void setTotal(int total) {
		this.total = total;
	}

	void setReg(Register reg) {
		this.reg = reg;
	}

	Register getReg() {
		return reg;
	}

	void setNextGate(Gate nextGate) {
		this.nextGate = nextGate;
	}

	void setFirstGate(Gate firstGate) {
		this.firstGate = firstGate;
	}

	Gate getFirstGate() {
		return firstGate;
	}
	
	void setCurrent(int current) {
		this.current = current;
	}
	
	
	public Gate getNextGate(){
		return nextGate;
	}
	
	public int getTotal(){
		
		return total;
	}
	public Register getRegister(){
		return getReg();
	}
	public int getCurrent(){
		
		return current;
		
	}
	/**
	 * Creates a circuit object which stores a given register passed into it.
	 * 
	 * @param reg the circuit takes in a register as an argument and stores it in the object.
	 */
	public Circuit(Register reg, boolean display){		
		reg.setGroundState();
		this.setReg(reg);
		setNextGate(null);
		setCurrent(0);
		this.display = display;
		if (display)frame=new Window(reg);
	}

	//add a gate to end of the list
	/**
	 * Adds a gate to the end of the linked list of gates within the circuit.
	 * This method causes the gate to generate itself a matrix of the correct size to be applied to the stored register.
	 * The circuit will only let gates with valid parameters be added to it
	 */
		public void addGate(Gate gate){
		
		setTotal(getTotal() + 1);
		gate.setNumQubits(getReg().getNumQubits());
		if (gate.checkParams()){
			if (getNextGate()==null){
				setCurrent(1);
				setFirstGate(gate);
				setNextGate(gate);
			}
			else{
				getNextGate().addToEnd(gate);
			}
		}
		else{System.out.println("Gate number " + (current+1)+ " has invalid arguments");}
	}
	
	//apply the gate and go to the next one in the list
	/**
	 * Applies the next gate in the linked list to the register.
	 */
	public void apply(){
		if (getNextGate()==null){
			System.out.println("No more gates in cirucit");
		}
		else{
			String msg = "Applying " + getNextGate().getName();
			if(getNextGate().getTargetBit() == -1){
				msg += " to whole register.";
			}else{
				msg += " to qubit " + getNextGate().getTargetBit() + ".";
			}
			System.out.println(msg);
			getNextGate().applyGate(getReg());
			setNextGate(getNextGate().getNextGate());
			setCurrent(getCurrent() + 1);
			
			updatePanel();
		}
	}
	
	/**
	 * Applies all the gates in the circuit sequentially to the register.
	 */
	public void applyAll(){
		
		while (getNextGate() != null){
			apply();
		}
		
		
	}
	
	/**
	 * Sets the register to the ground state, and sets the current gate to the first gate.
	 */
	public void reset(){
		current=1;
		setNextGate(getFirstGate());
		if (display)frame.update(reg);
		getReg().setGroundState();
	}

	/**
	 * 
	 * @param n position of the gate in the list.
	 * @return returns a gate at a given position in the circuit.
	 */
	public Gate getGate(int n){
		
		Gate requiredGate;

			requiredGate = getFirstGate();
			for (int i=1;i<n;i++){
				requiredGate = requiredGate.getNextGate();
			}
			return requiredGate;
	}
	//apply all the gates with one matrix
	/**
	 * Applies the matrix that represents the whole circuit to the register
	 */
	public void runOverallMatrix(){
	
		if (overallGate==null){
			System.out.println("Please run setOverallGate() to create a matrix to represent the circuit");
		}
		else{
			overallGate.applyGate(getReg());
			updatePanel();
			current = total;
		}
	}
	/**
	 * Calculates the matrix that represents the whole circuit by multiplying all of the gates together in reverse order.
	 * This method will only work if all gates are represented by matrices (i.e. no functional gates), otherwise it will catch a ClassCastException and report to the user that the matrix could not be created.
	 * 
	 */
	public void setOverallMatrix(){
		
		try{
			Matrix overallMatrix = ((DenseGate)(getGate(getTotal()))).getM();
			for (int i=getTotal()-1;i>=1;i--){
				overallMatrix = Matrix.multiply(overallMatrix,((DenseGate)getGate(i)).getM());
			}
			overallGate=new CustomGate(overallMatrix);
		}
		catch (ClassCastException e) {
	        System.out.println("Overall matrix only works with dense matrices");
	    }

	}
/**
 * Updates the graphical probability panel
 */
	public void updatePanel(){
		if (display)frame.update(reg);
		/*try {
			Thread.currentThread().sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}


}
