
public interface GateInterface{
	
/*	String name;
	int targetBit;
	int ctrl1;
	int ctrl2;
	
	Gate nextGate;*/

	
	public void applyGate(QuReg q);
	// select name
	
	public Gate getNextGate();
	public void setNextGate();
	
	public int getCtrl1();
	public int getCtrl2();

	public String getName();
	public int getTarget();

}
