
public class Had extends Gate {

	public Had(int n,int target,int ctrl1,int ctrl2) {
			

		super(new double[][] {{1}},target,ctrl1,ctrl2);
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
