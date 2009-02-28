import maths.*;

public class Had extends DenseGate2 {

	public Had(int target){
		super(1,target,(new int[] {0}),0);
	}
	
	public Had(int n,int target) {			
		super(n,target,(new int[] {0}),0);
		if ((target)<1||target>n){
			throw new IllegalArgumentException();
		}
		setM(n,target);
	}

	public String getName() {
		return "Hadamard";
	}
	
	public void setM(int n, int target){
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
	
	public void setNumQubits(int N){
		this.numQubits=N;
		setM(numQubits,targetBit);
	}
}
