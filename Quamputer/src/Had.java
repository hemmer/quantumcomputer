
public class Had extends Gate {

	public Had(int n) {

		super(new double[][] {{1}});
		Matrix one= new DenseMatrix(new double[][] {{1, 1}, {1, -1}});
		for (int i=1;i<=n;i++){
			tensor(one);
		}
	}
	
	

}
