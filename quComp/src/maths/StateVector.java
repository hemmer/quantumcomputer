package maths;

public class StateVector extends DenseMatrix {
	
	boolean decimal; 
		
	public StateVector(int size, boolean decimal){
		super(size,1);     // create 1D matrix
		this.decimal = decimal;
		matrix = new ComplexNum[size][1];
	}
	
	public void setGroundState(){
		
		ComplexNum zero = new ComplexNum();
		ComplexNum one = new ComplexNum(1.0, 0.0);
		
		for(int i = 0; i < this.getNumRows(); i++){
			matrix[i][0] = zero; // initialise all states to 0.0 amplitude
		}
		
		matrix[0][0] = one;
	}
	

	
	public String toString(){
		
		String s = ""; 
		
		if(decimal){
			for(int i = 0; i < this.getNumRows(); i++){
				s += "|" + i + "> = " + matrix[i][0] + "\n";
			}
		}else{
			for(int i = 0; i < this.getNumRows(); i++){
				s += "|" + Integer.toBinaryString(i) + "> = " + matrix[i][0] + "\n";
			}
		}

		return s;
	}
	
	public ComplexNum getAmp(int index){
		ComplexNum c = (ComplexNum) matrix[index][0];
		return new ComplexNum(c.getReal(), c.getImag());
	}
	
}
