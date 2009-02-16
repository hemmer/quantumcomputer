

public class MainTest {
	
	
	public static void main(String args[])
    {
		try {
			
			System.out.println("   _                      _                     ");
			System.out.println("  / /   _  _ _/_   _ _   / `_  _ _  _    _/__  _");
			System.out.println(" /_\\/_//_|/ // /_// / / /_,/_// / //_//_// /_'/");                                          
			System.out.println("----------------------------------/-------------");  
		    System.out.println();
		    int n = 3;
		    
			Register reg = new Register(n);
			Circuit circuit = new Circuit(reg);
			/*
			for (int i = 1;i<=n;i++){
				circuit.addGate(new Had(n,i,0,0));
			}
			new Graphics(circuit);
		    */
			
			circuit.addGate(new Had(n,1,0,0));
		    circuit.addGate(new CNot(n,2,1,0));
		    circuit.applyAll();
			System.out.println();
			System.out.println(circuit.getRegister());
			
			
			
			
		}
		catch(IllegalArgumentException e) {
		    System.out.println("Must select appropriate arguments");
		}




       
       
    }

}
