package gates;
import java.awt.Image;
import java.awt.Toolkit;

import maths.*;

/**
 * When this gate is applied to a register, each state is given a random probability 
 * amplitude. This gate is used for debugging purposes
 * 
 * @author Ewan Hemmingway<br>Ian Sullivan<br>James Vokes
 *
 */

public class Randomiser extends Gate {

	public Randomiser() {
		super(1,-1,(new int[] {-1}),0);
	}
	
	public Randomiser(int i) {
		super(1,i,(new int[] {-1}),0);
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

	public Image getImage(){
		return Toolkit.getDefaultToolkit().getImage("src/random.PNG");
	}
	
	public int getNumArguments() {
		// TODO Auto-generated method stub
		return 0;
	}

}
