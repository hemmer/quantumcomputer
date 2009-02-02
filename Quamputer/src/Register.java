
public class Register{

	DenseMatrix reg;
	
	public Register(int n) {
		this.reg=generator(n);
	}

	public static DenseMatrix generator(int n){
		DenseMatrix result =new DenseMatrix(new double[1][1]);
		for(int i=0;i<n;i++){
			result.tensor(new DenseMatrix(new double[][] {{1.0/Math.sqrt(2.0)},{0}}));
		}
		return result;
	}
	
	public String toString(){
		return reg.toString();
	}
	
	public static void main(String[] args){
		System.out.println(new Register(3));
	}
}
