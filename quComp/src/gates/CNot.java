package gates;
import java.awt.Image;
import java.awt.Toolkit;

import maths.ComplexNum;
import maths.Matrix;

/**
 * This generates the matrix representation of the CNOT gate. It works by first calculating which
 * bits are unchanged by CNOT operator by taking the tensor product of the inner product |0><0|
 * of the state basis vector with the identity matrix, in the appropriate order. 
 * Then we find the bit swap elements of the matrix by taking the tensor product of a list of matrices, 
 * with the outer product of state |1> (i.e. |1><1|) in the position of target bit, NOT gate in the 
 * control bit position, and identity matrices elsewhere. 
 * <br/>
 * This is best explained with an example. Take a 3 qubit system, with control bit 0, and target bit 2 (assuming
 * zero-index notation). First we fixed the control bits using |0><0| (x) I (x) I, where (x) is the tensor product.
 * Then we sum onto this, the tensor product: NOT (x) I (x) |1><1|. This generates:
 *  
 */
public class CNot extends DenseGate {

	public CNot(int targetBit, int ctrl) {
		super(1,targetBit,(new int[] {ctrl}),0);
	}

	
	
	public void setM(int n, int targetBit, int ctrl){
		// Step 1. Fix control elements
		Matrix[] controlElements = new Matrix[n];
		
    	Matrix b0 = new Matrix(2,1);     // create the basis vector for |0>
    	b0.setElem(0, 0, new ComplexNum(1.0,0.0));  // i.e. (1, 0)^T
    	
	   	Matrix outProdB0 = Matrix.outerProduct(b0);  // find outer product of |0> basis vector
	   	
		for(int i = 0; i < controlElements.length; i++){
			if(i == this.getCtrl(0)){        
				controlElements[i] = outProdB0;
			}else{
				controlElements[i] = new Matrix(2,"identity");
			}
		}
		
		// Step 2. Calculate correct bit shift entries for matrix
		Matrix[] shiftElements = new Matrix[n];
		
    	Matrix b1 = new Matrix(2,1);    // create the basis vector for |1>
    	b1.setElem(1, 0, new ComplexNum(1.0,0.0));  // i.e. (0, 1)^T
    	
	   	Matrix outProdB1 = Matrix.outerProduct(b1); // find outer product of |1> basis vector
	   	
		for(int i = 0; i < shiftElements.length; i++){
			if(i == this.getTargetBit()){        
				shiftElements[i] = new Matrix(2,"not");
			}else if(i == this.getCtrl()[0]){
				shiftElements[i] = outProdB1;
			}else{
				shiftElements[i] = new Matrix(2,"identity");
			}
		}
		
		// tensor product our two arrays
		Matrix gate1 = Matrix.tensorProductArray(controlElements);
		Matrix gate2 = Matrix.tensorProductArray(shiftElements);
		// and sum to find total contribution
		m = Matrix.add(gate1, gate2);
		
		//System.out.println(gate1 + "\n " + gate2 + "\n " + m);
	}
	
	
	public String getName() {
		return "CNot";
	}

	public void setNumQubits(int n) {
		this.numQubits=n;
		setM(numQubits,getTargetBit(),getCtrl(0));
	}

	public Image getImage(){
		return Toolkit.getDefaultToolkit().getImage("src/cnot.GIF");
	}



	@Override
	public int getNumArguments() {
		// TODO Auto-generated method stub
		return 2;
	}


}
