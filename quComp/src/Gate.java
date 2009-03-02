public abstract class Gate implements GateInterface{	

	/**
	 * @param n Size of register
	 * @param target target bit
	 * @param ctrl array for control bits
	 * @param searchedElem the searched element
	 */
		
	protected int numQubits;
	private int targetBit;
	private int[] ctrl;
	private int searchedElem;
	
	private Gate nextGate;

	void setTargetBit(int targetBit) {
		this.targetBit = targetBit;
	}


	void setSearchedElem(int searchedElem) {
		this.searchedElem = searchedElem;
	}


	int getNumQubits() {
		return numQubits;
	}


	void setCtrl(int[] ctrl) {
		this.ctrl = ctrl;
	}

	public Gate(int n, int target, int[] ctrl, int searchedElem){
		numQubits =n;
		targetBit = target;
		this.searchedElem = searchedElem;
		this.ctrl=ctrl;
		setNextGate(null);
	}
	
	
	public abstract void applyGate(Register q);
	
	public abstract void setNumQubits(int n);
	
	public abstract String getName();

	public int[] getCtrl() {
		return ctrl;
	}
	
	public int getCtrl(int n){
		return ctrl[n];
	}
	
	public int getSearchedElem() {
		return searchedElem;
	}

	public Gate getNextGate() {
		return nextGate;
	}

	public int getTargetBit() {
		return targetBit;
	}
	
	public void setTarget(int newTargetBit){
		this.setTargetBit(newTargetBit);
	}

	public void setNextGate(Gate a) {
		this.nextGate = a;
	}

	public void addToEnd(Gate a){
		   
        if (getNextGate()==null){
                setNextGate(a);
        }
        else{
                getNextGate().addToEnd(a);
        }
	}
	
	
	
	
}
