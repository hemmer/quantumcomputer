import maths.ComplexNum;
import maths.DenseMatrix;


public class Toffoli extends DenseGate2 {

	public Toffoli(int targetBit, int[] ctrl) {
		super(1,targetBit,ctrl,0);
		// TODO Auto-generated constructor stub
	}

	
	
	public void setM(int n, int targetBit, int[] ctrl){
		// Step 1. Fix control elements
		DenseMatrix[] controlElements = new DenseMatrix[n];
		
    	DenseMatrix b0 = new DenseMatrix(2,1);     // create the basis vector for |0>
    	b0.setElem(0, 0, new ComplexNum(1.0,0.0));  // i.e. (1, 0)^T
    	
	   	DenseMatrix outProdB0 = DenseMatrix.outerProduct(b0);  // find outer product of |0> basis vector
	   	
		for(int i = 0; i < controlElements.length; i++){
			if(i == this.getTargetBit()){        
				controlElements[i] = new DenseMatrix(2,"identity");
			}else{
				controlElements[i] = outProdB0;
			}
		}
		
		// Step 2. Calculate correct bit shift entries for matrix
		DenseMatrix[] shiftElements = new DenseMatrix[n];
		
    	DenseMatrix b1 = new DenseMatrix(2,1);    // create the basis vector for |1>
    	b1.setElem(1, 0, new ComplexNum(1.0,0.0));  // i.e. (0, 1)^T
    	
	   	DenseMatrix outProdB1 = DenseMatrix.outerProduct(b1); // find outer product of |1> basis vector
	   	
		for(int i = 0; i < shiftElements.length; i++){
			if(i == this.getTargetBit()){        
				shiftElements[i] = new DenseMatrix(2,"not");
			}else if(isCtrlBit(i)){
				System.out.println(i);
				shiftElements[i] = outProdB1;
			}else{
				shiftElements[i] = new DenseMatrix(2,"identity");
			}
		}
		
		// tensor product our two arrays
		DenseMatrix gate1 = DenseMatrix.tensorProductArray(controlElements);
		DenseMatrix gate2 = DenseMatrix.tensorProductArray(shiftElements);
		// and sum to find total contribution
		m = DenseMatrix.add(gate1, gate2);
		
		System.out.println("\n" + m);
	}
	
	
	public String getName() {
		return "Toffoli";
	}

	public void setNumQubits(int n) {
		numQubits=n;
		setM(numQubits,getTargetBit(),getCtrl());
	}
	
	//searches the array of ctrl bits
	public boolean isCtrlBit(int n){

		for (int i=0;i<getCtrl().length;i++){
			if (getCtrl()[i]==n){return true;}
		}
		return false;
	}

}
