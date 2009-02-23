import maths.DenseMatrix;


public class Circuit implements CircuitInterface {

	Gate nextGate;
	Gate firstGate;
	Register reg;
	int total;
	int current;
	Gate overallGate;
	
	public Circuit(Register reg){
		
		this.reg = reg;
		nextGate = null;
		current = 0;
		
	}
	
	//add a gate to end of the list
	public void addGate(Gate gate){
		
		total++;
		if (nextGate==null){
			current = 1;
			firstGate = gate;
			nextGate = gate;
		}
		else{
			nextGate.addToEnd(gate);
		}
	}
	
	//apply the gate and go to the next one in the list
	public void apply(){
		
		if (nextGate==null){
			System.out.println("No more gates in cirucit");
		}
		else{
			System.out.println("Applying " + nextGate.getName() + " to qubit "+nextGate.getTargetBit());
			nextGate.applyGate(reg);
			nextGate = nextGate.getNextGate();
			current++;
		}
	}
	
	public void applyAll(){
		
		while (nextGate != null){
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
		nextGate = firstGate;
	}
	public Register getRegister(){
		return reg;
	}
	public int getCurrent(){
		
		return current;
		
	}
	
	public Gate getGate(int n){
		
		Gate requiredGate;

			requiredGate = firstGate;
			for (int i=1;i<n;i++){
				requiredGate = requiredGate.getNextGate();
			}
			return requiredGate;
	}
	//apply all the gates with one matrix
	public void runOverallMatrix(){
	
		if (overallGate==null){
			System.out.println("Please run setOverallGate() to create a matrix to represent the circuit");
		}
		else{
			overallGate.applyGate(reg);
		}
	}
	
	public void setOverallMatrix(){
		
		try{
			DenseMatrix overallMatrix = ((DenseGate)getGate(total)).gate;
			for (int i=total-1;i>=1;i--){
				overallMatrix = DenseMatrix.multiply(overallMatrix,((DenseGate)getGate(i)).gate);
			}
			overallGate = new DenseGate("Overall Matrix",overallMatrix,reg.size);
		}
		catch (ClassCastException e) {
	        System.out.println("Overall matrix only works with dense matrices");
	    }

	}


}
