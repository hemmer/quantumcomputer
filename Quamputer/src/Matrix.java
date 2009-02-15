
public class Matrix{
	

		protected double e[][];
		protected int n;
		protected int m;


		public int getN() {
			return n;
		}

		public int getM() {
			return m;
		}
		


	public Matrix(double[][] i){
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

		if(this.n==a.getM()){
			double[][] C = new double[n][m];
			for (int i = 0; i < n; i++){
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
			System.out.println("A.m is "+getM()+ " B.n is "+a.getN());
		}
	}
	
	public void multiply(double a) {
		
		
		for (int i=0;i<getN();i++){
			for (int j=0;j<getM();j++){
				e[i][j] = e[i][j]*a;
			}
		}
		
		
		
	}

	
	//apply a matrix to the this register
	
	

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
			s = s+"|";
			for (int j=0;j<m;j++){
				s = s+e[i][j]+",";
			}
			s = s.substring(0,s.length()-1);
			s=s+"|\n";
		}
		s = s.substring(0,s.length()-1);
		return (s);
	}
	
	public static Matrix returnIdentity(int size){
		
		double[][] values = new double[size][size];
		for (int i=0;i<size;i++){
			for (int j=0;j<size;j++){
				if (i==j){values[i][j]=1;}
				else {values[i][j]=0;}
			}
			
			
		}
		return new Matrix(values);
		
		
	}
	
	
}
