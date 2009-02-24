
public class CNot extends DenseGate2{


	
	public CNot(int n,int targetBit, int ctrl1, int ctrl2) {
		super(n,new Matrix (new double[][] {{1}}), targetBit,ctrl1,0);
		name = "CNot";
		
		
		//int size=1;
		//for (int i=0;i<n;i++){
		//	size=size*2;
		//}

	
			// Step 1. Fix control elements
			Matrix[] controlElements = new Matrix[n];
			
	    	Matrix b0 = new Matrix(new double[][] {{1},{0}});     // create the basis vector for |0>
	    	
	    	b0.outerProduct(b0);
		   	Matrix outProdB0 = b0; // find outer product of |0> basis vector
		   	
			for(int i = 0; i < controlElements.length; i++){
				if(i == this.targetBit){        
					controlElements[i] = outProdB0;
				}else{
					controlElements[i] = Matrix.getIdentity(2);
				}
			}
			
			// Step 2. Calculate correct bit shift entries for matrix
			Matrix[] shiftElements = new Matrix[n];
			
	    	Matrix b1 = new Matrix(new double[][] {{1},{0}});    // create the basis vector for |1>
	    	b1.outerProduct(b1); // find outer product of |1> basis vector
		   	Matrix outProdB1 = b1;
		   	//System.out.println(b1);
			for(int i = 0; i < shiftElements.length; i++){
				if(i == this.targetBit){        
					shiftElements[i] = outProdB1;
				}else if(i == this.ctrl1){
					shiftElements[i] = new Matrix(new double[][] {{0, 1}, {1, 0}});
				}else{
					shiftElements[i] = Matrix.getIdentity(2);
				}
			}
			
			// tensor product our two arrays
			Matrix gate1 = Matrix.tensorArray(controlElements);
			Matrix gate2 = Matrix.tensorArray(shiftElements);
			// and sum to find total contribution
			//System.out.println(Matrix.add(gate1, gate2));
			setM(Matrix.add(gate1, gate2));
		
		
		
		
	}
	
	


	
	
	
	
}
