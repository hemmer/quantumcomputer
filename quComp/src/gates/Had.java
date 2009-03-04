package gates;
import maths.*;

public class Had extends DenseGate {

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
		
		Matrix had = new Matrix(new double[][]{{1.0, 1.0}, {1.0,-1.0}});
		had = Matrix.scale(new ComplexNum(1.0/Math.sqrt(2.0), 0.0), had);
		
		// if target bit is -1, apply to whole register
		if(target == -1){
			for (int i=1;i<=n;i++) m=Matrix.tensorProduct(m,had);
		}else{
			Matrix ident = new Matrix(2);
			for (int i=0;i<n;i++){
				if (i==target) m = Matrix.tensorProduct(m,had);
				else m = Matrix.tensorProduct(m,ident);
			}
		}
	}
	
	public void setNumQubits(int N){
		this.numQubits=N;
		setM(numQubits,getTargetBit());
	}
}
