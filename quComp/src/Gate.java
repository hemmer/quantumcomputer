
public class Gate implements GateInterface{

	String name;
	int targetBit;
	int ctrl1;
	int ctrl2;
	
	Gate nextGate;
	
	
	public Gate(String name, int target, int ctrl1, int ctrl2){
		this.name = name;
		this.targetBit = target;
		this.ctrl1 = ctrl1;
		this.ctrl2 = ctrl2;
	}
	
	
	public void applyGate(QuReg q) {
				
	}

	public int getCtrl1() {
		return ctrl1;
	}

	public int getCtrl2() {
		return ctrl2;
	}

	public String getName() {
		return name;
	}

	public Gate getNextGate() {
		return nextGate;
	}

	public int getTarget() {
		return targetBit;
	}

	public void setNextGate() {
		// TODO Auto-generated method stub
		
	}

	
}
