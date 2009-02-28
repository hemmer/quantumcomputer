import maths.*;

public abstract class DenseGate2 extends Gate{

	DenseMatrix m;
	
	
	public DenseGate2(int n,int targetBit, int[] ctrl, int searchedElem) {
		super(n,targetBit,ctrl,searchedElem);
		m=new DenseMatrix(1);
	}
	public DenseMatrix getM(){
		return m;
	}
	
	public void setM(DenseMatrix m){
		this.m=m;
	}
	
	public void applyGate(Register a){
		a.updateStateVector(DenseMatrix.multiply(m, a.getStateVector()) );
	}
	
	public String toString(){
		return m.toString();
	}
	
	
	
}
