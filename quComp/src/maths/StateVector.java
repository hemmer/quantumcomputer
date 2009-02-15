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
			
			// infer number of qubits, then complete string
			int numQubits = (int)( Math.log(this.getNumRows())/Math.log(2));  
			for(int i = 0; i < this.getNumRows(); i++){
				s += "|" + toBinary(numQubits, i) + "> = " + matrix[i][0] + "\n";
			}
		}

		return s;
	}
	
	public ComplexNum getAmp(int index){
		ComplexNum c = (ComplexNum) matrix[index][0];
		return new ComplexNum(c.getReal(), c.getImag());
	}
	

	// used to properly format binary numbers
    public static String toBinary(int numQubits, int number){
    	
    	String binString = Integer.toBinaryString(number);

    	// pack string with zeros
    	while(binString.length() < numQubits){
    		binString = '0' + binString;
    	}
    	return binString;
    }
	
}
