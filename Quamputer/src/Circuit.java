
public class Circuit {

	Gate nextGate;
	Register reg;
	
	public Circuit(Register reg){
		
		this.reg = reg;
		nextGate = null;
		
	}
	
	public void addGate(Gate gate){
		
		Gate first = gate; 
		if (gate == null){
			nextGate = gate;
		}
		else{
			//nextGate
			
		}
		
	}
	
	public void apply(){
		
		reg.apply(nextGate);
		
	}
	
}
