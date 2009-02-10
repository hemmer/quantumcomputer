
public interface CircuitInterface {

    //return number of gates in circuit
    public int getTotal();

	//add a gate to end of the list
	public void addGate(Gate gate);
	
	//apply the gate and set the next gate as the next one in the list
	public void apply();
	
	//apply all the gates in order
	public void applyAll();
	
	//return the nextGate
	public Gate getNextGate();

    //return the first gate in the circuit
    public Gate getFirst();
    
    //return the register
    public Register getRegister();
		
}
