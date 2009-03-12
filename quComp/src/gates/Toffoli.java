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
			if(i == this.getCtrl(0)){        
				controlElements1[i] = outProdB0;
			}else{
				controlElements1[i] = new Matrix(2,"identity");
			}
		}
		
		for(int i = 0; i < controlElements2.length; i++){
			if(i == this.getTargetBit()){        
				controlElements2[i] = new Matrix(2,"identity");
			}else if(i == this.getCtrl(0)){
				controlElements2[i] = outProdB1;
			}else{
				controlElements2[i] = outProdB0;
			}
			System.out.println(controlElements2[i]);
		}
		
		
		
		// Step 2. Calculate correct bit shift entries for matrix
		Matrix[] shiftElements = new Matrix[n];
		

	   	
		for(int i = 0; i < shiftElements.length; i++){
			if(i == this.getTargetBit()){        
				shiftElements[i] = new Matrix(2,"not");
			}else{
				shiftElements[i] = outProdB1;
			}
		}
		
		// tensor product our two arrays
		Matrix gate1 = Matrix.tensorProductArray(controlElements1);
		Matrix gate2 = Matrix.tensorProductArray(controlElements2);
		Matrix gate3 = Matrix.tensorProductArray(shiftElements);
		
		// and sum to find total contribution
		m = Matrix.add(gate3, Matrix.add(gate1, gate2));
		
		System.out.println(gate1 + "1\n " + gate2 + "2\n " + "3\n" + gate3 + "\n" + m);

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
