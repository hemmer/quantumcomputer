import maths.*;

public class Had extends DenseGate2 {

	public Had(int target){
		super(1,target,(new int[] {0}),0);
	}
	
	public Had(){
		super(1,-1,(new int[] {0}),0);
	}
	
	public Had(int n,int target) {			
		super(n,target,(new int[] {0}),0);
		if ((target)<1||target>n){
			throw new IllegalArgumentException();
		}
		setM(n,target);
	}

	public String getName() {
		return "hadamard";
	}
	
	public void setM(int n, int target){
		
		DenseMatrix had = new DenseMatrix(2,"hadamard");

		// if target bit is -1, apply to whole register
		if(target == -1){
			for (int i=1;i<=n;i++) m=DenseMatrix.tensorProduct(m,had);
		}else{
			DenseMatrix ident = new DenseMatrix(2);
			for (int i=0;i<n;i++){
				if (i==target) m = DenseMatrix.tensorProduct(m,had);
				else m = DenseMatrix.tensorProduct(m,ident);
			}
		}
	}
	
	public void setNumQubits(int N){
		this.numQubits=N;
		setM(numQubits,getTargetBit());
	}
}
