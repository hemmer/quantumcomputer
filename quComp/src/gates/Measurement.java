package gates;
import java.awt.Image;
import java.awt.Toolkit;

import maths.*;

/**
 * The measurement gate forces a register into a single state.
 * The probabilities (amplitude squared) are normalised. A random number between 0 and 1 is chosen.
 * The probabilities of each state are  added together till the sum is greater than the chosen random number.
 * The last state to be added is the chosen state
 * 
 * @author Ewan Hemmingway<br>Ian Sullivan<br>James Vokes
 *
 */

public class Measurement extends Gate {

	/**
	 * Creates a measurement gate
	 */
	public Measurement() {
		super(0,-1,(new int[] {-1}),0);
	}

	public void applyGate(Register q) {
		
		//generate a random number between 0 and 1
		double rand = Math.random();
		double sum = 0;
		double[] mag = new double[q.getSize()];
		StateVector v = q.getStateVector();
		for (int i=0;i<q.getSize();i++){
			//get the magnitude^2 of each element of the register
			mag[i] = v.getElem(i, 0).getMagnitude();
			mag[i] = mag[i]*mag[i];
			//sum the total probability
			sum=sum+mag[i];
		}
		//scale the random number
		rand = rand*sum;
		
		sum=0;
		int i=-1;
		//select the band which rand falls within
		while (sum<=rand){
			i++;
			sum=sum+mag[i];
		}
		//force the register into that state
		q.getStateVector().initZero();
		q.getStateVector().setElem(i, 0, new ComplexNum(1,0));
		
		
		
	}

	public void setNumQubits(int n) {		
	}

	public String getName() {
		return "Measurement";
	}

	public Image getImage(){
		return Toolkit.getDefaultToolkit().getImage("src/measurement.png");
	}

	@Override
	public int getNumArguments() {
		// doesnt need anything
		return 0;
	}
	



}
