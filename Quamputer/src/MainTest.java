
public class MainTest {

	public static void main(String args[])
    {
       System.out.println("Quantum Computer");
       System.out.println();
       
       //size of register
       int n = 2;
       
       //create a hadamard gate to apply to a register of n cubits
       System.out.println("Hadamard Gate = ");
       Matrix had = new Had(n);
       System.out.println(had);
       System.out.println();
       
       //create register of n quibits in 1 state
       Register reg = new Register(n);
       System.out.println(reg);
       System.out.println();
       
       //Once the hadamard gate is applied, each state has equal probability
       reg.apply(had);
       System.out.println(reg);
       
       
       
    }

}
