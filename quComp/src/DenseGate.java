import maths.*;

public class DenseGate extends Gate{
	
	
	
	public DenseGate(String name, int target, int ctrl1, int ctrl2) {
		super(name, target, ctrl1, ctrl2);
	}

	// apply hadamard gate to a specific bit
	public static void apply(QuReg q, int targetBit){
		
		System.out.println("Applying Hadamard Gate to bit " + targetBit + "...");
		
		// store individual gates in array
		DenseMatrix[] gates = new DenseMatrix[q.getNumQubits()];
		
		for(int i = 0; i < q.getNumQubits(); i++){
			if(i+1 == targetBit){        // 1 is first bit
				gates[i] = new DenseMatrix(2,2,"hadamard");
			}else{
				gates[i] = new DenseMatrix(2,2,"identity");
			}
		}

		// tensor product gates together to create a gate the size of the register
		DenseMatrix gate = DenseMatrix.tensorProductArray(gates);
		q.updateStateVector(DenseMatrix.multiply(gate, q.getStateVector()) );
	}
	
	// apply hadamard to all bits i.e normalise
	public static void applyHadamard(QuReg q){
		
		System.out.println("Applying Hadamard Gate to all bits...");
		
		DenseMatrix[] gates = new DenseMatrix[q.getNumQubits()];
		
		for(int i = 0; i < q.getNumQubits(); i++){
			gates[i] = new DenseMatrix(2,2,"hadamard");
		}
		
		// create gate of Hadamard tensor producted with itself n times (n number of bits)
		DenseMatrix gate = DenseMatrix.tensorProductArray(gates);
		q.updateStateVector(DenseMatrix.multiply(gate, q.getStateVector()) );

	}
}
