
public class Circuit implements CircuitInterface {

	Gate nextGate;
	Gate firstGate;
	Register reg;
	int total;
	int current;
	Matrix overallMatrix;
	
	public Circuit(Register reg){
		
		this.reg = reg;
		nextGate = null;
		current = 0;
		overallMatrix = null;
		
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
			System.out.print("Applying " + nextGate.getName() + " to quibt "+nextGate.getTarget());
			if (nextGate.getCtrl1()!=0){
				System.out.println(" with " + nextGate.getCtrl1()+ " as the control bit");
			}
			else{
				System.out.println("");
			}
			nextGate.apply(reg);
			nextGate = nextGate.getNextGate();
			current++;
		}
	}
	
	//apply all the gates in order
	//overwrite overAll matrix if true
	public void applyAll(){
	

		
	}
	
	public void setOverallMatrix(){
	
		DenseGate2 current;
		current= ((DenseGate2)getGate(total));
		overallMatrix = current.getM();
		for (int i=total-1;i>=1;i--){
			current = (DenseGate2)getGate(i);
			overallMatrix.multiply(current.getM());
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
	public Gate getGate(int n){
		
		Gate requiredGate;

			requiredGate = firstGate;
			for (int i=1;i<n;i++){
				requiredGate = requiredGate.getNextGate();
			}
			return requiredGate;
	}
	
	public int getCurrent(){
		
		return current;
	}

}
