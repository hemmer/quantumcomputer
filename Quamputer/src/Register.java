
public class Register extends DenseMatrix{

	
	public Register(int n) {
		
		super(new double[][] {{1},{0}});
		DenseMatrix one = new DenseMatrix(new double[][]{{1},{0}});
		for (int i=1;i<n;i++){
			tensor(one);	
		}
		normalise();
	}
	
	public String toString(){
		
			String s = "";
			for (int i=0;i<n;i++){
					s = s+"|"+i+"> = "+e[i][0]+"\n";
			}
			s = s.substring(0,s.length()-1);
			return (s);
		}

	public void normalise(){
		
		double sum = 0;
		for (int i=0;i<getN();i++){
			sum=sum+(e[i][0]*e[i][0]);
		}
		sum = Math.sqrt(sum);
		multiply(1.0/sum);
		
		
		
		
	}

}
