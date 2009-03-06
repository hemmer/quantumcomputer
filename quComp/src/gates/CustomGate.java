package gates;
import maths.*;

public class CustomGate extends DenseGate {

	public CustomGate(Matrix m){
		super(m.getNumCols(),0,(new int[] {0}),0);
		setM(m);
	}
	

	public String getName() {
		return "Custom Gate";
	}


	public void setNumQubits(int N){
		this.numQubits=N;
	}


	@Override
	public int getNumArguments() {
		// should never use this
		return -1;
	}

	
}
