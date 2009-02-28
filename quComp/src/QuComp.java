import maths.ComplexNum;

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
    	
    	int numQubits = 3;
    	
    	Register q = new Register(numQubits, false);
    	q.setGroundState();
    	Circuit test = new Circuit(q);
    	System.out.println(q);
    	test.addGate(new Not(1));
    	test.applyAll();
    	System.out.println(q);
    	
    }
}
