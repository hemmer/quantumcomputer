
public class Circuit {

	Gate nextGate;
	Register reg;
	
	public Circuit(Register reg){
		
		this.reg = reg;
		nextGate = null;
		
	}
	
	//add a gate to end of the list
	public void addGate(Gate gate){
		
		if (nextGate==null){
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
			System.out.println("Applying " + nextGate.getName() + " to quibt "+nextGate.getTarget());
			reg.apply(nextGate);
			nextGate = nextGate.getNextGate();
		}
	}
	
	//apply all the gates in order
	public void applyAll(){
	
		while(nextGate!=null){
			reg.apply(nextGate);
		}
	}
}
