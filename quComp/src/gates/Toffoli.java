package gates;
import java.awt.Image;
import java.awt.Toolkit;

import maths.ComplexNum;
import maths.Matrix;


public class Toffoli extends DenseGate {

	public Toffoli(int targetBit, int[] ctrl) {
		super(1,targetBit,ctrl,0);
	}

	
	public void setM(int n, int targetBit, int[] ctrl){
		// Step 1. Fix control elements
		Matrix[] controlElements = new Matrix[n];
		
    	Matrix b0 = new Matrix(2,1);     // create the basis vector for |0>
    	b0.setElem(0, 0, new ComplexNum(1.0,0.0));  // i.e. (1, 0)^T
    	
	   	Matrix outProdB0 = Matrix.outerProduct(b0);  // find outer product of |0> basis vector
	   	
		for(int i = 0; i < controlElements.length; i++){
			if(i == this.getTargetBit()){        
				controlElements[i] = new Matrix(2,"identity");
			}else{
				controlElements[i] = outProdB0;
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
			}else if(isCtrlBit(i)){
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
