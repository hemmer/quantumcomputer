package maths;

/**
 * 
 * This represents a complex number
 *
 */

public class ComplexNum{
	
	/**
	 * Real part of the complex number
	 */
	private double real;
	/**
	 * Imaginary part of the complex number
	 */
	private double imag;   // real and imaginary parts
	
	/**
	 * 
	 * @param r real part
	 * @param i imaginary part
	 */
	public ComplexNum(double r, double i){
		real = r;
		imag = i;
	}
	
	// construct real number
	/**
	 * Create a real number
	 * @param real real part 
	 */
	public ComplexNum(double real){
		this.real = real;
		imag = 0.0;
	}
	
	// construct empty complex number
	public ComplexNum(){
		real = 0.0;
		imag = 0.0;
	}
	
	public ComplexNum add(ComplexNum c){
		return new ComplexNum(real + c.getReal(), imag + c.getImag() );
	}

	/**
	 * multiply this complex number by another
	 * @param c complex number to be multiplied
	 * @return the product
	 */
	public ComplexNum multiply(ComplexNum c){
			
		double realTemp = real*c.getReal()+imag*c.getImag();
		double imagTemp = imag*c.getReal()+real*c.getImag();
			
		return new ComplexNum(realTemp, imagTemp);
	}
	
	public String toString(){
		String output = "" + real;
		if(Math.abs(imag) != 0.0) output +=  " + " + imag + "i";  // ignore zero complex entrys
			
		return output;
	}
	
	// get set methods
	public double getReal(){
		return real;
	}
	public void setReal(double r){
		real = r;
	}
	
	public double getImag(){
		return imag;
	}
	public void setImag(double i){
		imag = i;
	}

	/**
	 * 
	 * @return magnitude of a complex number
	 */
	public double getMagnitude() {
		return Math.sqrt(Math.pow(real, 2)+Math.pow(imag, 2));
	}
}
