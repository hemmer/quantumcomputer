
public class DenseMatrix extends Matrix {

	public DenseMatrix(double[][] i){
		this.e=i;
		this.n=i.length;
		this.m=i[0].length;

	}


	public void add(Matrix a) {
		for(int b=0;b<n;b++){
			for(int c=0;c<m;c++){
				e[b][c] += a.getElem(b,c);
			}
		}
	}

	public void multiply(Matrix a) {
		if(this.m==a.getM()){
			double[][] C = new double[m][m];
			for (int i = 0; i < m; i++){
				for (int j = 0; j < m; j++){
					for (int k = 0; k < m; k++){
						C[i][k] += e[i][j] * a.getElem(j,k);
					}
				}
			}
			setE(C);
		}
		else{
			System.out.println("Matrices are not compatable");
		}
	}

	public void tensor(Matrix z) {
		int an = getN();
		int am = getM();
		int bn = z.getN();
		int bm = z.getM();

		double[][] values = new double[an*bn][(am)*(bm)];	
		for(int a = 0; a<an; a++){
			for(int b=0; b<am; b++){
				for(int c=0; c<bn; c++){
					for(int d=0; d<bm; d++){
						values[c+(a*bn)][d+(b*bm)] = getE()[a][b]*z.getE()[c][d]; 			
					}	
				}	
			}	
		}

		setE(values);	

	}



	public double getElem(int a, int b) {
		return e[a][b];
	}



	public double[][] getE() {
		return e;
	}



	public void setE(double[][] i) {
		this.e=i;
		this.n=i.length;
		this.m=i[0].length;
		
	}

	public String toString(){
		
		String s = "";
		for (int i=0;i<n;i++){
			for (int j=0;j<m;j++){
				s = s+e[i][j]+",";
			}
			s = s.substring(0,s.length()-1);
			s=s+"\n";
		}
		s = s.substring(0,s.length()-1);
		return s;
	}
}
