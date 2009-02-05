
public class Matrix implements MatrixRep {

	double[][] values;
	int n;
	int m;
	
	public Matrix(double[][] values){
		
		this.values=values;
		n = values.length;
		m = values[0].length;
		
	}
	
	public static Matrix tensorProduct(Matrix A, Matrix B){
		
		int an = A.getN();
		int am = A.getM();
		int bn = B.getN();
		int bm = B.getM();
			
		double[][] values = new double[an*bn][(am)*(bm)];	
			for(int z = 0; z<an; z++){
				for(int i=0; i<am; i++){
					for(int j=0; j<bn; j++){
						for(int k=0; k<bm; k++){
							values[j+(z*bn)][k+(i*bm)] = A.getArray()[z][i]*B.getArray()[j][k]; 			
						}	
					}	
				}	
			}

		return new Matrix(values);	
		
		
	}
	
	public void tensorProduct( Matrix B){
		
		int an = n;
		int am = m;
		int bn = B.getN();
		int bm = B.getM();
		double[][] original = values;
		n = an*bn;
		m = am*bm;
			
		values = new double[n][m];	
			for(int z = 0; z<an; z++){
				for(int i=0; i<am; i++){
					for(int j=0; j<bn; j++){
						for(int k=0; k<bm; k++){
							values[j+(z*bn)][k+(i*bm)] = original[z][i]*B.getArray()[j][k]; 			
						}	
					}	
				}	
			}

		
	}
	
	private double[][] getArray(){
		return values;
	}
	
	private int getN(){
		return n;
	}
	private int getM(){
		return m;
	}
	
	public String toString(){
		
		String s = "[";
		for (int i=0;i<n;i++){
			for (int j=0;j<m;j++){
				s = s+values[i][j]+",";
			}
			s = s.substring(0,s.length()-1);
			s=s+"\n";
		}
		s = s.substring(0,s.length()-1);
		s = s+"]";
		return s;
	}
	
	public Matrix returnMatrix(){ return this;}
	
	
}
