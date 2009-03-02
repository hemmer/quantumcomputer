import maths.*;

/**
 * Functional Hadamard. Doesn't use matrix representation. Or anything yet for that matter...
 * 
 *  http://en.wikipedia.org/wiki/Walsh_function
 */

public class FuncHad extends Gate {

	public FuncHad(int targetBit) {
		super(1, targetBit, new int[]{0}, 0);
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return "functionalhadamard";
	}

	public void setNumQubits(int N){
		this.numQubits=N;
		//setM(numQubits,getTargetBit());
	}

	public void applyGate(Register QZ) {
		// TODO
	}

}
