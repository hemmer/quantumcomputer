import maths.*;

public class DenseGate extends Gate{
	
	public DenseGate(String name, int target, int ctrl1, int ctrl2) {
		super(name, target, ctrl1, ctrl2);
	}

	public void applyGate(Register q){
		
		if(targetBit > q.getNumQubits()){
			System.out.println("Invalid gate operation: target bit (" + targetBit + ") larger than register size (" + q.getNumQubits() + ").");
			return;
		}
		
		if(this.name == "hadamard"){
			applyHadamard(q, this.targetBit); // call hadamard 
		}else if(this.name == "not"){
			applyNot(q, this.targetBit);
		}else if(this.name == "prepare"){
			applyPrepare(q);
		}else if(this.name == "cnot"){
			applyCNot(q, this.targetBit);
		}
	}
	
	// apply hadamard gate to a specific bit
	public static void applyHadamard(Register q, int targetBit){
		
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
	
	// apply not gate
	public static void applyNot(Register q, int targetBit){
		
		System.out.println("Applying Not Gate to bit " + targetBit + "...");
		
		DenseMatrix[] gates = new DenseMatrix[q.getNumQubits()];
		
		for(int i = 0; i < q.getNumQubits(); i++){
			if(i+1 == targetBit){        // 1 is first bit
				gates[i] = new DenseMatrix(2,2,"not");
			}else{
				gates[i] = new DenseMatrix(2,2,"identity");
			}
		}
		
		// create gate of Hadamard tensor producted with itself n times (n number of bits)
		DenseMatrix gate = DenseMatrix.tensorProductArray(gates);
		q.updateStateVector(DenseMatrix.multiply(gate, q.getStateVector()) );
	}
	
	public static void applyCNot(Register q, int targetBit){
		
		System.out.println("Applying CNot Gate to bit " + targetBit + "...");
		
		DenseMatrix[] gates = new DenseMatrix[q.getNumQubits()-1];
		
		for(int i = 0; i < q.getNumQubits()-1; i++){
			if(i+1 == targetBit){        // 1 is first bit
				gates[i] = new DenseMatrix(4,4,"cnot");
			}else{
				gates[i] = new DenseMatrix(2,2,"identity");
			}
		}
		
		// create gate of Hadamard tensor producted with itself n times (n number of bits)
		DenseMatrix gate = DenseMatrix.tensorProductArray(gates);  //new DenseMatrix(4,4,"cnot");
		//System.out.println(" test" + gate);
		q.updateStateVector(DenseMatrix.multiply(gate, q.getStateVector()) );
	}
	
	// apply hadamard to all bits i.e normalise
	public static void applyPrepare(Register q){
		
		System.out.println("Applying Hadamard Gate to all bits...");
		
		DenseMatrix[] gates = new DenseMatrix[q.getNumQubits()];
		
		for(int i = 0; i < q.getNumQubits(); i++){
			gates[i] = new DenseMatrix(2,2,"hadamard");
		}
		
		// create gate of Hadamard tensor producted with itself n times (n number of bits)
		DenseMatrix gate = DenseMatrix.tensorProductArray(gates);
		q.updateStateVector(DenseMatrix.multiply(gate, q.getStateVector()) );

	}
	
	public void setBit(int i){
		this.targetBit = i;
	}
}
