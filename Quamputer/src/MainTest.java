
public class MainTest {
	
	
	public static void main(String args[])
    {
       System.out.println("Quantum Computer");
       System.out.println();
       
       //size of register
       int n = 3;
       Gate[] gates = new Gate[n];
       
       //create a hadamard gate to apply to a register of n cubits
       System.out.println("Hadamard Gate = ");
       Matrix had = new Had(n,2,0,0);
       System.out.println(had);
       System.out.println();
       
       /*for (int i=0;i<n;i++){
    	   gates[i] = new Had(n,i+1,0,0);
    	   //System.out.println(gates[i]);
    	   System.out.println();
       }*/
       
       //create register of n quibits in 1 state
       Register reg = new Register(n);
       System.out.println(reg);
       System.out.println();
       

       reg.apply(had);
       //reg.apply(new Had(n,2,0,0));
       reg.normalise();
       System.out.println(reg);
       
       
       
    }

}
