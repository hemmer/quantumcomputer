public abstract class Gate implements GateInterface{	

	String name;
	int numQubits;
	int targetBit;
	int[] ctrl;
	int searchedElem;
	
	Gate nextGate;
	
	/**
	 * @param name Name of Gate
	 * @param n Size of register
	 * @param target
	 * @param ctrl1
	 * @param ctrl2
	 */
	public Gate(int n, int target, int[] ctrl, int searchedElem){
		this.numQubits = n;
		this.targetBit = target;
		this.searchedElem = searchedElem;
		this.ctrl = ctrl;
		nextGate = null;
	}
	
	
	public abstract void applyGate(Register q);
	
	public abstract void setNumQubits(int n);

	public int[] getCtrl() {
		return ctrl;
	}
	public int getSearchedElem() {
		return searchedElem;
	}
	
	public String getName() {
		return name;
	}

	public Gate getNextGate() {
		return nextGate;
	}

	public int getTargetBit() {
		return targetBit;
	}
	
	public void setTarget(int newTargetBit){
		this.targetBit = newTargetBit;
	}

	public void setNextGate(Gate a) {
		this.nextGate = a;
	}

	public void addToEnd(Gate a){
		   
        if (nextGate==null){
                setNextGate(a);
        }
        else{
                nextGate.addToEnd(a);
        }
	}
	
	
	
	
}
