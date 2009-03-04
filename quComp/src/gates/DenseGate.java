package gates;
import maths.*;

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
	
	public void applyGate(Register a){
		a.updateStateVector(Matrix.multiply(getM(), a.getStateVector()) );
	}
	
	public String toString(){
		return getM().toString();
	}
	
	
	
}
