
public class Not extends DenseGate2 {

	public Not(int n,int target,int a,int b) {
			

		super(n,new Matrix (new double[][] {{1}}),target,0,0);
		if ((target)<1||target>n){
			throw new IllegalArgumentException();
		}
		
		name = "Not";
		Matrix ident = Matrix.getIdentity(2);
		Matrix not = new Matrix(new double[][] {{0, 1}, {1, 0}});
		for (int i=1;i<=n;i++){
			if (i==target){
				m.tensor(not);
			}
			else{
				m.tensor(ident);
			}
		}
	}

		
	
	
	

}
