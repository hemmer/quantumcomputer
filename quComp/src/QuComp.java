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
    	
    	int numQubits = 5;
    	
    	Register q = new Register(numQubits, false);
    	q.setGroundState();
    	System.out.println(q);
    	
    	Gate hadamard = new DenseGate("hadamard",2, null, 0, numQubits);
    	//Gate cnot = new DenseGate("cnot", 2, new int[]{0}, 0, numQubits);
    	Gate grovers = new DenseGate("grovers", 4, numQubits);
    	
    	// create hadamard gate object
    	// and apply to each register separately
    	for(int i = 0; i < q.getNumQubits(); i++){
    		hadamard.setTarget(i);
    		hadamard.applyGate(q);
        	System.out.println("\n" + q);
        	
        	//cnot.applyGate(q);
        	//System.out.println("\n" + q);
    	}
    	
    	grovers.applyGate(q);
    	System.out.println("\n" + q);
    	
    	grovers.applyGate(q);
    	System.out.println("\n" + q);

    	grovers.applyGate(q);
    	System.out.println("\n" + q);

    	grovers.applyGate(q);
    	System.out.println("\n" + q);
    	
    	
    	// applying Hadamard (tensor p'ed with itself n times)
    	// should reset to ground state 
    	//Gate prepare = new DenseGate("prepare",numQubits);
    	//prepare.applyGate(q);
    	
    	//System.out.println("\n" + q);
    	
    }
}
