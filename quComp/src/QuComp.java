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
    	
    	int numQubits = 4;
    	
    	Register q = new Register(numQubits, false);
    	q.setGroundState();
    	Circuit test = new Circuit(q);
    	DenseGate2 test2 = new Had(1);
    	DenseGate2 test3 = new Had(2);
    	test.addGate(test2);
    	test.addGate(test3);
    	test.addGate(new Had(3));
    	test.addGate(new Had(4));
    	test.addGate(new Measurement());
    	//BlochGui tester = new BlochGui(500);
    	//tester.update(new ComplexNum(0.707,0.707));
    	test.applyAll();
    	System.out.println(q);
    	
    }
}
