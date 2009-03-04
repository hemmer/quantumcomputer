package gates;
import maths.*;



public class Randomiser extends Gate {

	public Randomiser() {
		super(1,-1,(new int[] {0}),0);
	}
	
	public Randomiser(int i) {
		super(1,i,(new int[] {0}),0);
	}
	
	public void applyGate(Register q) {
		if(getTargetBit()==-1){
			StateVector random = new StateVector(2<<q.getNumQubits()-1,false);
			random.setGroundState();
			for(int i=0;i<random.getNumRows();i++){
				random.setAmp(new ComplexNum(Math.random()), i);
			}
			q.setStateVector(random);
		}
		else{
			q.getStateVector().setAmp(new ComplexNum(Math.random()), getTargetBit());
		}
	}

	public String getName() {
		return "Randomiser";
	}

	public void setNumQubits(int n) {

	}

}
