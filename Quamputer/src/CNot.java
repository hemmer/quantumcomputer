
public class CNot extends DenseGate{


	
	public CNot(int n,int targetBit, int ctrl1, int ctrl2) {
		super(n,new Matrix (new double[][] {{1}}), targetBit,ctrl1,0);
		name = "CNot";
		int size=1;
		for (int i=0;i<n;i++){
			size=size*2;
		}

		Matrix cnot = new Matrix(new double[][]{{1,0,0,0},{0,1,0,0},{0,0,0,1},{0,0,1,0}});
		Matrix ident = Matrix.returnIdentity(2);
		for (int i=2;i<n;i++){
			cnot.tensor(ident);			
		}
		setM(cnot);
		
		
		
		
	}
	
	


	
	
	
	
}
