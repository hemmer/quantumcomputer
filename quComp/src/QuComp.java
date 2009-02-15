/**
 * <h1>QuComp</h1>
 * 
 * Quantum Computer Simulation
 *
 * @author Ewan Hemingway
 * @version 1.00 2009/1/31
 */
 
public class QuComp {
    
    public static void main(String[] args) {
    	
    	int numQubits = 3;
    	
    	Register q = new Register(numQubits, false);
    	q.setGroundState();
    	System.out.println(q);
    	
    	Gate hadamard = new DenseGate("hadamard",2,0,0, numQubits);
    	Gate cnot = new DenseGate("cnot", 2, 0, 0, numQubits);
    	
    	// create hadamard gate object
    	// and apply to each register separately
    	for(int i = 0; i < q.getNumQubits(); i++){
    		hadamard.setTarget(i);
    		hadamard.applyGate(q);
        	System.out.println("\n" + q);
        	
        	cnot.applyGate(q);
        	System.out.println("\n" + q);
    	}
    	 	 
    	// applying Hadamard (tensor p'ed with itself n times)
    	// should reset to ground state 
    	Gate prepare = new DenseGate("prepare",0,0,0,numQubits);
    	prepare.applyGate(q);
    	
    	System.out.println("\n" + q);
    	
    }
}
