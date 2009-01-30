
public class Quibit implements MatrixRep {

	Matrix m;
	
	
	public Quibit(Matrix m){
		
		this.m=m;
		
	}
	
	public Matrix returnMatrix(){
		return m;
	}
	
	public String toString(){
		return "("+m.toString()+")";
	}
}
