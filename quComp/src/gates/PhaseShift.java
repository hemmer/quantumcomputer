package gates;

import maths.ComplexNum;
import maths.Matrix;

public class PhaseShift extends DenseGate {
	
	

	protected Matrix m;

	public PhaseShift(int n, int target, int[] ctrl, int searchedElem,double phi) {
		super(n, target, ctrl, searchedElem);
		setM(n, target, phi);
	}
	
	public PhaseShift(int target, double phi){
			super(1,target,(new int[] {-1}),0);
			setM(-1,target,phi);
	}
	
	public Matrix getM(){
		return m;
	}
	
	public void setM(int n, int target, double phi){
		
		//to get e^(i*Phi) = cos(Phi) + i.sin(Phi)
		
		ComplexNum j = new ComplexNum(0,1);
		ComplexNum eIPhi = null;
		double cosPhi = Math.cos(phi);
		ComplexNum iSinPhi = j.multiply(Math.sin(phi));
		
		eIPhi = iSinPhi.add(cosPhi);
		ComplexNum one = new ComplexNum(1,0);
		ComplexNum zero = new ComplexNum(0,0);

		Matrix[] elements = new Matrix[n];
		
		Matrix phaseShift = new Matrix(new ComplexNum[][]{{one, zero}, {zero,eIPhi}});
		
		
		for(int i = 0; i < elements.length; i++){
				if(i == target){        
					elements[i] = phaseShift;
				}else{
					elements[i] = new Matrix(2,"identity");
				}
			}
		
		}
	
	
	public void applyGate(Register a){
		a.updateStateVector(Matrix.multiply(getM(), a.getStateVector()) );
	}
	
	public String toString(){
		return getM().toString();
	}

	
	public String getName() {
		return "Phase Shift";
	}

	
	public void setNumQubits(int n) {
		this.numQubits = n;		
	}

	
	public int getNumArguments() {
		return 1;
	}
}
