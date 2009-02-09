
public class Had extends Gate {

	public Had(int n,int a,int b,int c) {

		super(new double[][] {{1}},a,b,c);
		name = "Hadamard";
		Matrix one= new DenseMatrix(new double[][] {{1, 1}, {1, -1}});
		for (int i=1;i<=n;i++){
			tensor(one);
		}
	}
	
	

}
