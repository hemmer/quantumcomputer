import javax.swing.JFrame;

import maths.DenseMatrix;


public class Circuit implements CircuitInterface {

	private Gate nextGate;
	private Gate firstGate;
	private Register reg;
	private int total;
	private int current;
	private Gate overallGate;
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

	public Circuit(Register reg){		
		this.setReg(reg);
		setNextGate(null);
		setCurrent(0);
		frame=new Window();
	}
	
	//add a gate to end of the list
	public void addGate(Gate gate){
		
		setTotal(getTotal() + 1);
		gate.setNumQubits(getReg().getNumQubits());
		if (getNextGate()==null){
			setCurrent(1);
			setFirstGate(gate);
			setNextGate(gate);
		}
		else{
			getNextGate().addToEnd(gate);
		}
	}
	
	//apply the gate and go to the next one in the list
	public void apply(){
		if (getNextGate()==null){
			System.out.println("No more gates in cirucit");
		}
		else{
			System.out.println("Applying " + getNextGate().getName() + " to qubit "+getNextGate().getTargetBit());
			getNextGate().applyGate(getReg());
			setNextGate(getNextGate().getNextGate());
			setCurrent(getCurrent() + 1);
			
			frame.update(reg);
			try {
				Thread.currentThread().sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		setNextGate(getFirstGate());
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
		}
	}
	
	public void setOverallMatrix(){
		
		try{
			DenseMatrix overallMatrix = ((DenseGate)getGate(getTotal())).gate;
			for (int i=getTotal()-1;i>=1;i--){
				overallMatrix = DenseMatrix.multiply(overallMatrix,((DenseGate)getGate(i)).gate);
			}
			setOverallGate(new DenseGate("Overall Matrix",overallMatrix,getReg().getSize()));
		}
		catch (ClassCastException e) {
	        System.out.println("Overall matrix only works with dense matrices");
	    }

	}



}
