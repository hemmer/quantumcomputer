/**
 * <strong>qComp.java</strong>
 * <br />
 * qComp application
 *
 * @author Ewan Hemingway
 * @version 1.00 2009/1/31
 */
 
 
public class QuComp {
    
    public static void main(String[] args) {
    	
    	Register q = new Register(2, false);
    	q.setGroundState();
    	System.out.println(q);
    	
    	DenseGate hadamard = new DenseGate("hadamard",1,0,0);
    	//DenseGate not = new DenseGate("not",1,0,0);
    	DenseGate cnot = new DenseGate("cnot", 1, 0, 0);
    	//cnot.applyGate(q);
        //System.out.println("\n" + q);
    	
    	// create hadamard gate object
    	// and apply to each register separately
    	for(int i = 1; i <= q.getNumQubits(); i++){
    		hadamard.setBit(i);
    		hadamard.applyGate(q);
        	System.out.println("\n" + q);
        	
        	cnot.applyGate(q);
        	System.out.println("\n" + q);
    	}
    	 	 	
    	// applying Hadamard (tensor p'ed with itself n times)
    	// should reset to ground state 
    	DenseGate.applyPrepare(q);
    	System.out.println("\n" + q);
    }
}
