
public class Circuit {

	Gate gate;
	Register reg;
	
	
	void apply(){
		
		reg.apply(gate);
		gate = gate.nextGate();
		
	}
	
}
