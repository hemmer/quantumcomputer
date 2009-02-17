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
    	
    	System.out.println("   _                      _                     ");
		System.out.println("  / /   _  _ _/_   _ _   / `_  _ _  _    _/__  _");
		System.out.println(" /_\\/_//_|/ // /_// / / /_,/_// / //_//_// /_'/");                                          
		System.out.println("----------------------------------/-------------");  
	    System.out.println();
    	
    	int numQubits = 4;
    	
    	Register q = new Register(numQubits, false);
    	q.setGroundState();
    	
    	System.out.println(q);
    	
    	Gate hadamard = new DenseGate("hadamard",2, null, 0, numQubits);
    	//Gate cnot = new DenseGate("cnot", 0, new int[]{2}, 0, numQubits);
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

    	
    	/*
    	// applying Hadamard (tensor p'ed with itself n times)
    	// should reset to ground state 
    	//Gate prepare = new DenseGate("prepare",numQubits);
    	//prepare.applyGate(q);
    
    	//System.out.println("\n" + q);
 		
    	
    	Circuit test = new Circuit(q); //create a new circuit
    	//add three gates to the circuit
    	test.addGate(new DenseGate("hadamard",0, null, 0, numQubits));
    	test.addGate(new DenseGate("cnot",2, new int[] {0}, 0, numQubits));
    	test.addGate(new DenseGate("hadamard",1, null, 0, numQubits));
    	//generate a matrix to represent the circuit
    	test.setOverallMatrix();
    	//apply the matrix to the register
    	test.applyAll();
    	//print reg
    	System.out.println("\n" + q);
    	*/
    }
}
