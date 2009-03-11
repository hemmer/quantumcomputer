package main;
import gates.Gate;
import gates.Register;

/**
 * 
 * @author Ewan Hemmingway<br>Ian Sullivan<br>James Vokes
 *
 */

public interface CircuitInterface {

    //return number of gates in circuit
	/**
	 * Returns the number of gates in a circuit*/
    public int getTotal();

	//add a gate to end of the list
    /**
     * Adds a gate to the end of a circuit*/
	public void addGate(Gate gate);
	
	//apply the gate and set the next gate as the next one in the list
	/**
	 * Applies the next gate in the list to the register*/
	public void apply();
	
	//apply all the gates in order
	/**
	 * Applies the matrix that represents the circuit to the register*/
	public void runOverallMatrix();
	
	//return the nextGate
	/**
	 * Returns the next gate in the list*/
	public Gate getNextGate();

    //set nextGate to firstGate
	/**
	 * Sets the register to the ground state, and sets the current gate to the first gate. */
    public void reset();
    
    //return the register
    /**
     * Returns the register*/
    public Register getRegister();
    
    //return the position of 'nextGate' in the list
    /**
     * Return the position of 'nextGate' in the list*/
    public int getCurrent();
		
}
