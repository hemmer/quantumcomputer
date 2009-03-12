package gates;
import maths.*;

/**
 * A dense gate is a gate which can be represented by a matrix
 * 
 * @author Ewan Hemmingway<br>Ian Sullivan<br>James Vokes
 *
 */

public abstract class DenseGate extends Gate{

	protected Matrix m;

	public DenseGate(int n,int targetBit, int[] ctrl, int searchedElem) {
		super(n,targetBit,ctrl,searchedElem);
		setM(new Matrix(1));
	}
	
	public Matrix getM(){
		return m;
	}
	
	public void setM(Matrix m){
		this.m=m;
	}
	
	/**
	 * applies the gate to a register. This is matrix multiplication of
	 * of the matrix that represents the gate and the vector that represents the register
	 * 
	 */
	public void applyGate(Register a){
		a.updateStateVector(Matrix.multiply(getM(), a.getStateVector()) );
	}
	
	public String toString(){
		return getM().toString();
	}
	
	
	
}
