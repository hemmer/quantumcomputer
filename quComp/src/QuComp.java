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
    	test.addGate(new Randomiser());
    	test.apply();
    	System.out.println(q);   	

    	test.addGate(new Had(0));
    	test.apply();
    	System.out.println(q);   	

    	test.addGate(new Had(1));
    	test.apply();
    	System.out.println(q);   	

    	test.addGate(new Had(2));
    	test.apply();
    	System.out.println(q);
    	

    	
    	q.setGroundState();
    	Circuit test2 = new Circuit(q);
    	test2.addGate(new FuncHad(0));
    	test2.apply();
    	System.out.println(q);   	

    	test2.addGate(new FuncHad(1));
    	test2.apply();
    	System.out.println(q);   	

    	test2.addGate(new FuncHad(2));
    	test2.apply();
    	System.out.println(q);   
 
    }
}
