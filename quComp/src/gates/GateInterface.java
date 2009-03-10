package gates;

/**
 * 
 * @author Ewan Hemmingway<br>Ian Sullivan<br>James Vokes
 *
 */
public interface GateInterface{
	
	/**
	 * applies a gate to a register
	 * @param q register that the gate is applied to
	 */
	public void applyGate(Register q);
	
	/**
	 * @return the next gate in the linked list */
	public Gate getNextGate();
	/**
	 * set the next gate in the linked list
	 */
	public void setNextGate(Gate a);
	
	public int[] getCtrl();
	public int getSearchedElem();

	public String getName();
	public int getTargetBit();
	/**
	 * 
	 * @return the number of arguments a gate needs to be constructed (this is for input purposes)
	 */
	public int getNumArguments();

}
