/**
 * @(#)qComp.java
 *
 * qComp application
 *
 * @author 
 * @version 1.00 2009/1/31
 */
 
 
public class QuComp {
    
    public static void main(String[] args) {
    	
    	QuReg q = new QuReg(3, false);
    	q.setGroundState();
    	System.out.println(q);
    	
		DenseGate.applyHadamard(q,1);
    	System.out.println("\n" + q);
    	
    	DenseGate.applyHadamard(q,2);
    	System.out.println("\n" + q);
    	
    	DenseGate.applyHadamard(q,3);
    	System.out.println("\n" + q);
    	
    	
    	// applying Hadamard resets to ground state :)
    	DenseGate.applyHadamard(q);
    	System.out.println("\n" + q);
    }
}
