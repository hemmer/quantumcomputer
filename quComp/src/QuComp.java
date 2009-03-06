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
	    
    	int numQubits = 4;
    	boolean displayGui = true;
    	
    	/*
    	BlochGui thing = new BlochGui(500);
    	thing.update(new ComplexNum(0.707,0.707));
    	*/
    	Register q = new Register(numQubits, false);
    	Circuit test = new Circuit(q,displayGui);
    	test.addGate(new Had(-1));
    	test.addGate(new Toffoli(3,new int[]{0,2}));
    	test.addGate(new CNot(2,1));
    	test.addGate(new CNot(3,1));
    	test.addGate(new Measurement()); 
    	if (displayGui){
    		new CircuitGui(test);
    	}
    	else{
    		test.applyAll();
    		System.out.println();
    		System.out.println(q);
    	}
//    	test.setOverallMatrix();
//    	test.runOverallMatrix();
//    	System.out.println(q);
/*
    	test.addGate(new Grovers(5));
    	test.addGate(new Grovers(5));
    	test.addGate(new Grovers(5));
    	test.addGate(new Grovers(5));
    	test.addGate(new Grovers(5));
    	test.addGate(new Grovers(5));
    	test.addGate(new Measurement());
    	test.applyAll();
    	System.out.println(q);
    	*/
    	/*q.setGroundState();
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
 */
    }
}
