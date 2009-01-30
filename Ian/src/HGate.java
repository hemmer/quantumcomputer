
public class HGate extends Gate {

	public HGate(){

	}
	
	public Matrix returnMatrix(){
		return new Matrix(new double[][] {{1, 1}, {1, -1},});
	}
	
	public String toString(){
		return new Matrix(new double[][] {{1, 1}, {1, -1},}).toString();
	}
}
