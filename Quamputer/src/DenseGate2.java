
public abstract class DenseGate2 extends Gate{

	Matrix m;
	
	
	public DenseGate2(int n,Matrix m,int targetBit, int ctrl1, int ctrl2) {
		super(n,targetBit,ctrl1,ctrl2);
		this.m = m;
	}
	public Matrix getM(){
		return m;
	}
	
	public void setM(Matrix m){
		this.m=m;
	}
	
	public void apply(Register a){
		
	
		double sum;
		double[] newValues = new double[a.getSize()];
		for (int i=0;i<a.getSize();i++){
			sum=0;
			for (int j=0;j<m.getM();j++){
				sum=sum+(a.getValues()[j]*m.getElem(i,j));
			}
			newValues[i] = sum;
		}
		a.setValues(newValues);
	}
	
	public String toString(){
		return m.toString();
	}
	
	


	
	
	
	
}
