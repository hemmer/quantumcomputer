import gates.Gate;
import gates.Register;


public interface CircuitInterface {

    //return number of gates in circuit
    public int getTotal();

	//add a gate to end of the list
	public void addGate(Gate gate);
	
	//apply the gate and set the next gate as the next one in the list
	public void apply();
	
	//apply all the gates in order
	public void runOverallMatrix();
	
	//return the nextGate
	public Gate getNextGate();

    //set nextGate to firstGate
    public void reset();
    
    //return the register
    public Register getRegister();
    
    //return the position of 'nextGate' in the list
    public int getCurrent();
		
}
