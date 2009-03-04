package gates;


public interface GateInterface{
	
	
	public void applyGate(Register q);
	// select name
	
	public Gate getNextGate();
	public void setNextGate(Gate a);
	
	public int[] getCtrl();
	public int getSearchedElem();

	public String getName();
	public int getTargetBit();

}
