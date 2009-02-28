import maths.DenseMatrix;


public class Not extends DenseGate2 {

	public Not(int targetBit) {
		super(1,targetBit,(new int[] {0}),0);
	}

	public void setM(int n, int target){
		
		DenseMatrix not = new DenseMatrix(new double[][] {{0, 1}, {1, 0}});
		
		for(int i = 0; i < n; i++){
			if(i == target){        
				m= DenseMatrix.tensorProduct(m,not);
			}else{
				m= DenseMatrix.tensorProduct(m,new DenseMatrix(2));
			}
		}
	}
	
	public void setNumQubits(int N){
		this.numQubits=N;
		setM(numQubits,getTargetBit());
	}

	public String getName() {
		return "Not";
	}

}
