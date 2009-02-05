
public class QuantumComputer {

	public static void main(String args[])
    {
       System.out.println("Quantum Computer");
       System.out.println();
       Matrix m1 = new Matrix(new double[][] {{1, 2, 3}, {4, 5, 6}});
       Matrix m2 = new Matrix(new double[][] {{2, 2}, {2, 2}});
       //System.out.println(tensorProduct(m1,m2));
       
       Quibit quibit1 = new Quibit(new Matrix(new double[][] {{0.5},{0.5}}));
       Quibit quibit2 = new Quibit(new Matrix(new double[][] {{0.5},{0.5}}));
       Quibit quibit3 = new Quibit(new Matrix(new double[][] {{0.5},{0.5}}));
       Register reg = new Register(new Quibit[]{quibit1,quibit2,quibit3});
       m1.tensorProduct(m2);
      
       System.out.println();
       System.out.println(reg);
       System.out.println();
       
       System.out.println("Hadamard Gate = ");
       System.out.println(new HGate(2));
       
    
    
    
    
    }
	
	public static Matrix tensorProduct(MatrixRep A,MatrixRep B){
		
		return Matrix.tensorProduct(A.returnMatrix(), B.returnMatrix());
		
	}
	


	
}
