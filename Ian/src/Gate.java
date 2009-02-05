
public abstract class Gate implements MatrixRep {

	Matrix m;
	
	public String toString(){
		return m.toString();
	}
	
	public Matrix returnMatrix(){
		return m;
	}
	
}
