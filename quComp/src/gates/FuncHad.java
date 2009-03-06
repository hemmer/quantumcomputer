package gates;
import maths.*;

/**
 * Functional Hadamard. Doesn't use matrix representation. Or anything yet for that matter...
 * 
 *  http://en.wikipedia.org/wiki/Walsh_function
 */

public class FuncHad extends Gate {

	public FuncHad(int targetBit) {
		super(1, targetBit, new int[]{0}, 0);
	}

	public String getName() {
		return "functionalhadamard";
	}

	public void setNumQubits(int N){
		this.numQubits=N;
	}

	/**
	 * Method applies a Hadamard Gate to the register using a functional method.
	 * It basically works by adding or subtracting the amplitudes of the
	 * pair of |xx0xx> and |xx1xx>.
	 *  
	 * i.e. |xx0xx> = |xx0xx> + |xx1xx> 
	 * and  |xx1xx> = |xx0xx> - |xx1xx>
	 * 
	 * The register is currently not normalised after operation.
	 * 
	 */
	public void applyGate(Register reg) {
		int targetShift = (int) Math.pow(2, this.getTargetBit());
		int totalShift = (int) Math.pow(2, reg.getNumQubits() - this.getTargetBit() - 1);
		int diff = reg.getNumQubits() - this.getTargetBit();
		
		// for each |xx0xx> and each |xx1xx> where x can be 1 or 0,
		// take sum and difference of both
		for(int i = 0; i < targetShift; i++){
			for(int j = 0; j < totalShift; j++){
				
				int bit1 = (i << diff) + j;   // find the integer index of |xx0xx> for the current iteration
				int bit2 = bit1 + totalShift;    // and also for |xx1xx>
				
				// find the two amplitudes of the swapped bits
				ComplexNum amp1 = reg.getStateVector().getAmp(bit1);
				ComplexNum amp2 = reg.getStateVector().getAmp(bit2);
		
				// find the sum and difference of the amplitudes
				ComplexNum sum = amp1.add(amp2);
				ComplexNum difference = amp1.add(amp2.multiply(new ComplexNum(-1.0, 0.0)));
		
				// update the state vector
				reg.getStateVector().setAmp(sum, bit1);
				reg.getStateVector().setAmp(difference, bit2);
			}
		}

	}

	@Override
	public int getNumArguments() {
		// TODO Auto-generated method stub
		return 1;
	}

}