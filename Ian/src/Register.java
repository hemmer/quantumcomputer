
public class Register implements MatrixRep{

	Matrix m;
	
	public Matrix returnMatrix(){
		return m;
	}
	
	public Register(Quibit[] q){
		
		m=q[0].returnMatrix();
		for (int i=1;i<q.length;i++){
		
			m.tensorProduct(q[i].returnMatrix());
			
		}
		
	}
	
	public String toString(){
		return m.toString();
	}
	
}
