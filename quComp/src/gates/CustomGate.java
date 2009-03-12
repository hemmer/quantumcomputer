package gates;
import maths.*;

/**
 * Creates a gate object from a given matrix
 * 
 * 
 * @author Ewan Hemmingway<br>Ian Sullivan<br>James Vokes
 * 
 *
 */

public class CustomGate extends DenseGate {

	/**
	 * 
	 * @param m the matrix that represents the gate
	 */
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
