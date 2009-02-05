
public class HGate extends Gate {

	
	public HGate(int n){

		Matrix one= new Matrix(new double[][] {{1, 1}, {1, -1}});
		m = new Matrix(new double[][] {{1}});
		for (int i=1;i<=n;i++){
			m.tensorProduct(one);
		}
	}
	
}
