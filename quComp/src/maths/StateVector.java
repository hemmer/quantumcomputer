package maths;

public class StateVector extends Matrix {
	
	boolean decimal; 
		
	public StateVector(int size, boolean decimal){
		super(size,1);     // create 1D matrix
		this.decimal = decimal;
		matrix = new ComplexNum[size][1];
	}
	
	/**
	 * put the register in the ground state (1,0,0....0)
	 */
	public void setGroundState(){
		
		 // initialise all states to 0.0 amplitude
		for(int i = 0; i < this.getNumRows(); i++) matrix[i][0] = new ComplexNum();
		// except the first element
		matrix[0][0] = new ComplexNum(1.0, 0.0);
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
	
	/**
	 * Returns the probability amplitude of a given state
	 * @param the state
	 * @return a complex number represented the probability amplitude
	 */
	public ComplexNum getAmp(int index){
		ComplexNum c = matrix[index][0];
		return new ComplexNum(c.getReal(), c.getImag());
	}
	
	
	public void setAmp(ComplexNum amp, int index){
		matrix[index][0].setReal(amp.getReal());
		matrix[index][0].setImag(amp.getImag());
	}

	// used to properly format binary numbers
	/**
	 * Formats the register to be displayed in binary instead of decimal
	 */
    public static String toBinary(int numQubits, int number){
    	
    	String binString = Integer.toBinaryString(number);

    	// pack string with zeros
    	while(binString.length() < numQubits){
    		binString = '0' + binString;
    	}
    	return binString;
    }
	
}
