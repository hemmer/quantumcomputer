
public interface GateInterface{
	
/*	String name;
	int targetBit;
	int ctrl1;
	int ctrl2;
	
	Gate nextGate;*/
	
	//public abstract QuReg applyHadamard(QuReg q, int targetBit);
	
	//public Gate(String name, int target, int ctrl1, int ctrl2);
	
	// select name
	
	public Gate getNextGate();
	public void setNextGate(Gate a);
	
	public int getCtrl1();
	public int getCtrl2();

	public String getName();
	public int getTarget();

}
