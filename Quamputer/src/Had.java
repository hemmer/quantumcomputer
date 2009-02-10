
public class Had extends Gate {

	public Had(int n,int target,int a,int b) {
			

		super(new double[][] {{1}},target,0,0);
		if ((target)<1||target>n){
			throw new IllegalArgumentException();
		}
		
		name = "Hadamard";
		Matrix ident = returnIdentity(2);
		Matrix one = new DenseMatrix(new double[][] {{1, 1}, {1, -1}});
		for (int i=1;i<=n;i++){
			if (i==target){
				tensor(one);
			}
			else{
				tensor(ident);
			}
		}
	}

		
	
	
	

}
