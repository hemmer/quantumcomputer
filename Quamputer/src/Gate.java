
public abstract class Gate extends DenseMatrix implements GateInterface{

	protected Gate nextGate;
	protected String name;
	protected int targetBit;
	protected int ctrl1;
	protected int ctrl2;
	
	
	public Gate(double[][] i,int targetBit, int ctrl1, int ctrl2) {
		super(i);
		this.targetBit = targetBit;
		this.ctrl1 = ctrl1;
		this.ctrl2 = ctrl2;
		nextGate = null;
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

	public void setNextGate(Gate a) {
		this.nextGate = a;
		
	}
	
	//add a gate to the end of the list
	public void addToEnd(Gate a){
	
		if (nextGate==null){
			setNextGate(a);
		}
		else{
			nextGate.addToEnd(a);
		}
	
	}

	
	


	
	
	
	
}
