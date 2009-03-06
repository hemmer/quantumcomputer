package gates;
import maths.Matrix;


public class Not extends DenseGate {

	public Not(int targetBit) {
		super(1,targetBit,(new int[] {-1}),0);
	}

	public void setM(int n, int target){
		
		Matrix not = new Matrix(new double[][] {{0, 1}, {1, 0}});
		
		for(int i = 0; i < n; i++){
			if(i == target){        
				m= Matrix.tensorProduct(m,not);
			}else{
				m= Matrix.tensorProduct(m,new Matrix(2));
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

	@Override
	public int getNumArguments() {
		// TODO Auto-generated method stub
		return 1;
	}

}
