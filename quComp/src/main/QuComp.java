package main;
import maths.ComplexNum;
import gates.*;

/**
 * <h1>QuComp</h1>
 * 
 * Quantum Computer Simulation
 *
 */
 
public class QuComp {
    
    public static void main(String[] args) {
    	
    	System.out.println("   _                      _                     ");
		System.out.println("  / /   _  _ _/_   _ _   / `_  _ _  _    _/__  _");
		System.out.println(" /_\\/_//_|/ // /_// / / /_,/_// / //_//_// /_'/");                                          
		System.out.println("----------------------------------/-------------");  
	    System.out.println();
	    
    	int numQubits = 5;
    	boolean displayGui = true;
    	
    	Register q = new Register(numQubits, false);
    	Circuit test = new Circuit(q,displayGui);

    	test.addGate(new Had(0));
    	test.addGate(new Had(1));
    	test.addGate(new Had(2));
    	test.addGate(new Had(3));
    	test.addGate(new Had(4));

    	test.addGate(new Grovers(3,true));
    	test.addGate(new Grovers(3,true));
    	test.addGate(new Grovers(3,true));
    	test.addGate(new Grovers(3,true));

    	test.addGate(new Measurement());
    	
    	if (displayGui){
    		new CircuitGui(test);
    	}
    	else{
    		test.applyAll();
    		System.out.println();
    		System.out.println(q);
    	}
    	
    }
}
