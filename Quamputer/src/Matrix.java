
abstract public class Matrix {

	protected double e[][];
	protected int n;
	protected int m;

	abstract public void add(Matrix a);	

	abstract public void multiply(Matrix a);
	
	abstract public void multiply(double a);

	abstract public void tensor(Matrix a);

	abstract public double[][] getE();
	
	abstract public void setE(double[][] i);
	
	abstract public double getElem(int a,int b);

	public int getN() {
		return n;
	}

	public int getM() {
		return m;
	}
	
}
