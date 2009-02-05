
public class Register extends DenseMatrix{

	
	public Register(int n) {
		
		super(new double[][] {{1},{0}});
		DenseMatrix one = new DenseMatrix(new double[][]{{1},{0}});
		for (int i=1;i<n;i++){
			tensor(one);	
		}
	}

	

}
