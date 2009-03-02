import maths.*;

public abstract class DenseGate2 extends Gate{

	protected DenseMatrix m;
	
	
	public DenseGate2(int n,int targetBit, int[] ctrl, int searchedElem) {
		super(n,targetBit,ctrl,searchedElem);
		setM(new DenseMatrix(1));
	}
	
	public DenseMatrix getM(){
		return m;
	}
	
	public void setM(DenseMatrix m){
		this.m=m;
	}
	
	public void applyGate(Register a){
		a.updateStateVector(DenseMatrix.multiply(getM(), a.getStateVector()) );
	}
	
	public String toString(){
		return getM().toString();
	}
	
	
	
}
