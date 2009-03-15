package gates;
import java.awt.Image;
import java.awt.Toolkit;

import maths.ComplexNum;
import maths.Matrix;

/**
 * This generates the matrix representation of the Toffoli gate or CCNot. It works by first calculating which
 * bits are unchanged operator by taking the appropriate combinations of tensor product of the inner product of
 * both the basis vectors (1, 0)^T and (0,1)^T. The additional control bit means that a correction must be made
 * (step 1B). Finally, the swapped bits are calculated (easy part!).
 * 
 * Ex. Toffoli with target 3, control bits 0 & 1 in a 5 qubit circuit (Remembering zero-index notation):
 * <br>
 * First find majority of changed bits:
 * 
 * | 1 0 | (x) | 1 0 | (x) | 1 0 | (x) | 1 0 | (x) | 1 0 |   Outer Product |0><0| in Pos 1 (2nd Ctrl)
 * | 0 1 |     | 0 0 |     | 0 1 |     | 0 1 |     | 0 1 |   Identity elsewhere  
 * 
 * Then find the remaining diagonals
 * 
 * | 1 0 | (x) | 0 0 | (x) | 1 0 | (x) | 1 0 | (x) | 1 0 | Outer Product |0><0| in Pos 0 (1st Ctrl)
 * | 0 0 |     | 0 1 |     | 0 1 |     | 0 1 |     | 0 1 | Outer Product |1><1| in Pos 1 (2nd Ctrl)
 *                                                         Identity elsewhere
 *                                               
 * Finally find the swapped bits                                              
 *
 * | 0 0 | (x) | 0 0 | (x) | 1 0 | (x) | 0 1 | (x) | 1 0 |  Outer Product |0><0| in Pos 0 (1st Ctrl)
 * | 0 1 |     | 0 1 |     | 0 1 |     | 1 0 |     | 0 1 |  Outer Product |0><0| in Pos 1 (2nd Ctrl)
 *                                               			Not matrix in Pos 3 (Target Bit)
 *                                               			Identity elsewhere
 *                                               
 * Sum these 3 matrices and you will have a Toffoli Gate (Target Bit 3, Control Bits 0, 1)
 * for a 5 qubit system!
 */

public class Toffoli extends DenseGate {

	public Toffoli(int targetBit, int[] ctrl) {
		super(1,targetBit,ctrl,0);
	}

	
	public void setM(int n, int targetBit, int[] ctrl){
		System.out.println(targetBit + " " + ctrl[0] + " " + ctrl[1]);

		Matrix[] controlElements1 = new Matrix[n];
		Matrix[] controlElements2 = new Matrix[n];

    	Matrix b0 = new Matrix(2,1);     // create the basis vector for |0>
    	b0.setElem(0, 0, new ComplexNum(1.0,0.0));  // i.e. (1, 0)^T
	   	Matrix outProdB0 = Matrix.outerProduct(b0);  // find outer product of |0> basis vector
	   	
	   	Matrix b1 = new Matrix(2,1);    // create the basis vector for |1>
    	b1.setElem(1, 0, new ComplexNum(1.0,0.0));  // i.e. (0, 1)^T
	   	Matrix outProdB1 = Matrix.outerProduct(b1); // find outer product of |1> basis vector
	   	
	   	// Step 1A
	   	// Fix initial control bits
	   	for(int i = 0; i < controlElements1.length; i++){
			if(i == this.getCtrl(1)){        
				controlElements1[i] = outProdB0;
			}else{
				controlElements1[i] = new Matrix(2,"identity");
			}
		}
	   	
	   	// Step 1B
	   	// Patch remaining control bits
		for(int i = 0; i < controlElements2.length; i++){
			if(i == this.getCtrl(0)){        
				controlElements2[i] = outProdB0;
			}else if(i == this.getCtrl(1)){
				controlElements2[i] = outProdB1;
			}else{
				controlElements2[i] = new Matrix(2,"identity");
			}
		}
				
		// Step 2. Calculate correct bit shift entries for matrix
		Matrix[] shiftElements = new Matrix[n];
	   	
		for(int i = 0; i < shiftElements.length; i++){
			if(i == this.getTargetBit()){        
				shiftElements[i] = new Matrix(2,"not");
			}else if(i == this.getCtrl(0) || i == this.getCtrl(1)){
				shiftElements[i] = outProdB1;
			}else{
				shiftElements[i] = new Matrix(2,"identity");
			}
		}
		
		// tensor product our two arrays
		Matrix gate1 = Matrix.tensorProductArray(controlElements1);
		Matrix gate2 = Matrix.tensorProductArray(controlElements2);
		Matrix gate3 = Matrix.tensorProductArray(shiftElements);
		
		// and sum to find total contribution
		this.setM(Matrix.add(gate3, Matrix.add(gate1, gate2)));
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
	
	public Image getImage(){
		return Toolkit.getDefaultToolkit().getImage("src/tof.GIF");
	}



	@Override
	public int getNumArguments() {
		// can take array, min of 2 inputs
		return -1;
	}


}
