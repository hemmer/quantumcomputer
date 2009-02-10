
public class MainTest {
	
	
	public static void main(String args[])
    {
		try {
			
			
			System.out.println("******************");
		    System.out.println("*Quantum Computer*");
		    System.out.println("******************");
		    System.out.println();
			int n = 3;
			Register reg = new Register(n);
			Circuit circuit = new Circuit(reg);
			circuit.addGate(new Had(n,1,0,0));
			circuit.addGate(new Had(n,2,0,0));
			circuit.addGate(new Had(n,3,0,0));
			System.out.println(reg);
			System.out.println();
			circuit.apply();
			System.out.println(reg);
			System.out.println();
			circuit.apply();
			System.out.println(reg);
			System.out.println();
			circuit.apply();
			System.out.println(reg);
			
			
		}
		catch(IllegalArgumentException e) {
		    System.out.println("Must select appropriate arguments");
		}




       
       
    }

}
