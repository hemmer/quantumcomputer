
public class Had extends DenseGate {

	public Had(int n,int target,int a,int b) {
			

		super(n,new Matrix (new double[][] {{1}}),target,0,0);
		if ((target)<1||target>n){
			throw new IllegalArgumentException();
		}
		
		name = "Hadamard";
		Matrix ident = Matrix.returnIdentity(2);
		Matrix one = new Matrix(new double[][] {{1, 1}, {1, -1}});
		for (int i=1;i<=n;i++){
			if (i==target){
				m.tensor(one);
			}
			else{
				m.tensor(ident);
			}
		}
	}

		
	
	
	

}
