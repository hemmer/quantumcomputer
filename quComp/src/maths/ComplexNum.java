package maths;


public class ComplexNum{
	
	private double real, imag;   // real and imaginary parts
	
	public ComplexNum(double r, double i){
		real = r;
		imag = i;
	}
	
	// construct real number
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
}
