import maths.*;

public class Had extends DenseGate2 {

	public Had(int n,int target) {
			
		super(n,new DenseMatrix(1),target,(new int[] {0}),0);
		if ((target)<1||target>n){
			throw new IllegalArgumentException();
		}
		
		
		DenseMatrix ident = new DenseMatrix(2);
		DenseMatrix one = new DenseMatrix(new double[][] {{1, 1}, {1, -1}});
		for (int i=1;i<=n;i++){
			if (i==target){
				m=DenseMatrix.tensorProduct(m,one);
			}
			else{
				m=DenseMatrix.tensorProduct(m,ident);
			}
		}
	}

	/*public void applyGate(Register q) {
		System.out.println("Hopefully, it should never get this far down");
		
	}*/

	public String getName() {
		return "Hadamard";
	}

		
	
	
	

}
