import gates.CustomGate;
import gates.DenseGate;
import gates.Gate;
import gates.Register;
import maths.Matrix;


public class Circuit implements CircuitInterface {

	private Gate nextGate;
	private Gate firstGate;
	private Register reg;
	private int total;
	private int current;
	private Gate overallGate;
	private boolean display;
	Window frame;
	
	void setTotal(int total) {
		this.total = total;
	}

	void setReg(Register reg) {
		this.reg = reg;
	}

	Register getReg() {
		return reg;
	}

	void setOverallGate(Gate overallGate) {
		this.overallGate = overallGate;
	}

	Gate getOverallGate() {
		return overallGate;
	}

	void setNextGate(Gate nextGate) {
		this.nextGate = nextGate;
	}

	void setFirstGate(Gate firstGate) {
		this.firstGate = firstGate;
	}

	Gate getFirstGate() {
		return firstGate;
	}
	
	void setCurrent(int current) {
		this.current = current;
	}

	public Circuit(Register reg, boolean display){		
		reg.setGroundState();
		this.setReg(reg);
		setNextGate(null);
		setCurrent(0);
		this.display = display;
		if (display)frame=new Window(reg);
	}
	
	//add a gate to end of the list
	public void addGate(Gate gate){
		
		setTotal(getTotal() + 1);
		gate.setNumQubits(getReg().getNumQubits());
		if (gate.checkParams()){
			if (getNextGate()==null){
				setCurrent(1);
				setFirstGate(gate);
				setNextGate(gate);
			}
			else{
				getNextGate().addToEnd(gate);
			}
		}
		else{System.out.println("Gate number " + (current+1)+ " has invalid arguments");}
	}
	
	//apply the gate and go to the next one in the list
	public void apply(){
		if (getNextGate()==null){
			System.out.println("No more gates in cirucit");
		}
		else{
			String msg = "Applying " + getNextGate().getName();
			if(getNextGate().getTargetBit() == -1){
				msg += " to whole register.";
			}else{
				msg += " to qubit " + getNextGate().getTargetBit() + ".";
			}
			System.out.println(msg);
			getNextGate().applyGate(getReg());
			setNextGate(getNextGate().getNextGate());
			setCurrent(getCurrent() + 1);
			
			updatePanel();
		}
	}
	
	public void applyAll(){
		
		while (getNextGate() != null){
			apply();
		}
		
		
	}
	
	
	public Gate getNextGate(){
		return nextGate;
	}
	
	public int getTotal(){
		
		return total;
	}
	
	public void reset(){
		current=1;
		setNextGate(getFirstGate());
		if (display)frame.update(reg);
		getReg().setGroundState();
	}
	public Register getRegister(){
		return getReg();
	}
	public int getCurrent(){
		
		return current;
		
	}
	
	public Gate getGate(int n){
		
		Gate requiredGate;

			requiredGate = getFirstGate();
			for (int i=1;i<n;i++){
				requiredGate = requiredGate.getNextGate();
			}
			return requiredGate;
	}
	//apply all the gates with one matrix
	public void runOverallMatrix(){
	
		if (getOverallGate()==null){
			System.out.println("Please run setOverallGate() to create a matrix to represent the circuit");
		}
		else{
			getOverallGate().applyGate(getReg());
			updatePanel();
		}
	}
	
	public void setOverallMatrix(){
		
		try{
			Matrix overallMatrix = ((DenseGate)(getGate(getTotal()))).getM();
			for (int i=getTotal()-1;i>=1;i--){
				overallMatrix = Matrix.multiply(overallMatrix,((DenseGate)getGate(i)).getM());
			}
			setOverallGate(new CustomGate(overallMatrix));
		}
		catch (ClassCastException e) {
	        System.out.println("Overall matrix only works with dense matrices");
	    }

	}

	public void updatePanel(){
		if (display)frame.update(reg);
		/*try {
			Thread.currentThread().sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}


}
