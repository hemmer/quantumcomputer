import maths.*;

/**
 * The DenseGate class is a extension of the abstract gate class, with the 
 * aim of providing a verbose method of apply gates to a register. The gate
 * is represented by a matrix of size 2^N x 2^N
 * 
 * @author Ewan Hemingway
 *  
 */
public class DenseGate extends Gate{
	
	DenseMatrix gate;
	int regSize;
	
	/**
	 * 
	 * @param name Name of the gate (used to generate correct matrix)
	 * @param target The qubit to which the gate is applied to
	 * @param ctrl1 First control bit (used for CNOT)
	 * @param ctrl2 Second control bit (used for Toffoli)
	 * @param regSize Number of bits that gate is to be applied to 
	 */
	public DenseGate(String name, int target, int ctrl1, int ctrl2, int regSize) {
		super(name, target, ctrl1, ctrl2);
		this.regSize = regSize;
				
		if(targetBit > regSize){
			System.out.println("Invalid gate operation: target bit (" + targetBit + ") larger than register size (" + regSize + ").");
			return;
		}
		
		initGate();  // create correct gate object
	}

	/**
	 * Initialises the correct gate based on name
	 */
	public void initGate(){
		if(this.name == "hadamard"){
			initHadamard(); // call hadamard 
		}else if(this.name == "not"){
			initNot();
		}else if(this.name == "prepare"){
			initPrepare();
		}else if(this.name == "cnot"){
			initCNot();
		}
	}
	
	/**
	 * Applies the appropriate gate to the specified register
	 * @param q Input register to be acted upon 
	 */
	public void applyGate(Register q){
		
		System.out.println("Applying " + this.name + " gate to bit " + targetBit + "...");
		q.updateStateVector(DenseMatrix.multiply(gate, q.getStateVector()) );
	}
	
	/**
	 * Generate a matrix representation of the Hadamard gate
	 * @param targetBit Bit to which gate is to be applied
	 */
	public void initHadamard(){
				
		// store individual 2x2 elements in array
		DenseMatrix[] elements = new DenseMatrix[regSize];
		
		for(int i = 0; i < elements.length; i++){
			if(i == this.targetBit){        // 1 is first bit
				elements[i] = new DenseMatrix(2,2,"hadamard");
			}else{
				elements[i] = new DenseMatrix(2,2,"identity");
			}
		}

		// tensor product elements together to create a gate the size of the register
		gate = DenseMatrix.tensorProductArray(elements);	
	}
	
	/**
	 * Generate a matrix representation of the Not gate
	 * @param targetBit 
	 */
	public void initNot(){
		
		DenseMatrix[] elements = new DenseMatrix[regSize];
		
		for(int i = 0; i < elements.length; i++){
			if(i == this.targetBit){        // 1 is first bit
				elements[i] = new DenseMatrix(2,2,"not");
			}else{
				elements[i] = new DenseMatrix(2,2,"identity");
			}
		}
		
		// create gate of Hadamard tensor producted with itself n times (n number of bits)
		gate = DenseMatrix.tensorProductArray(elements);
	}
	
	/**
	 * This generates the matrix representation of the CNOT gate. It works by first calculating which
	 * bits are unchanged by cnot operator by taking the tensor product of the inner product |0><0|
	 * of the state basis vector with the identity matrix, in the appropriate order. 
	 * Then we find the bit swap elements of the matrix by taking the tensor product of a list of matrices, 
	 * with the outer product of state |1> (i.e. |1><1|) in the position of target bit, NOT gate in the 
	 * control bit position, and identity matrices elsewhere. 
	 * <br/>
	 * This is best explained with an example. Take a 3 qubit system, with control bit 0, and target bit 2 (assuming
	 * zero-index notation). First we fixed the control bits using |0><0| (x) I (x) I, where (x) is the tensor product.
	 * Then we sum onto this, the tensor product: NOT (x) I (x) |1><1|. This generates:
	 *  
	 */
	public void initCNot(){
		
		// Step 1. Fix control elements
		DenseMatrix[] controlElements = new DenseMatrix[regSize];
		
    	DenseMatrix b0 = new DenseMatrix(2,1);     // create the basis vector for |0>
    	b0.setElem(0, 0, new ComplexNum(1.0,0.0));  // i.e. (1, 0)^T
    	
	   	DenseMatrix outProdB0 = DenseMatrix.outerProduct(b0);  // find outer product of |0> basis vector
	   	
		for(int i = 0; i < controlElements.length; i++){
			if(i == this.targetBit){        
				controlElements[i] = outProdB0;
			}else{
				controlElements[i] = new DenseMatrix(2,2,"identity");
			}
		}
		
		// Step 2. Calculate correct bit shift entries for matrix
		DenseMatrix[] shiftElements = new DenseMatrix[regSize];
		
    	DenseMatrix b1 = new DenseMatrix(2,1);    // create the basis vector for |1>
    	b1.setElem(1, 0, new ComplexNum(1.0,0.0));  // i.e. (0, 1)^T
    	
	   	DenseMatrix outProdB1 = DenseMatrix.outerProduct(b1); // find outer product of |1> basis vector
	   	
		for(int i = 0; i < shiftElements.length; i++){
			if(i == this.targetBit){        
				shiftElements[i] = outProdB1;
			}else if(i == this.ctrl1){
				shiftElements[i] = new DenseMatrix(2,2,"not");
			}else{
				shiftElements[i] = new DenseMatrix(2,2,"identity");
			}
		}
		
		// tensor product our two arrays
		DenseMatrix gate1 = DenseMatrix.tensorProductArray(controlElements);
		DenseMatrix gate2 = DenseMatrix.tensorProductArray(shiftElements);
		// and sum to find total contribution
		gate = DenseMatrix.add(gate1, gate2);
		
		System.out.println("\n" + gate);
	}
	
	/**
	 * Creates a gate of the Hadamard matrix tensor producted with itself n times 
	 */
	public void initPrepare(){
		
		DenseMatrix[] elements = new DenseMatrix[regSize];
		
		for(int i = 0; i < elements.length; i++){
			elements[i] = new DenseMatrix(2,2,"hadamard");
		}
		
		// create gate of Hadamard tensor producted with itself n times (n number of bits)
		gate = DenseMatrix.tensorProductArray(elements);
	}
	
	/**
	 * Overrides abstract Gate class as we need to recalculate matrix for new bit
	 * 
	 * @param newTargetBit The new bit to which the gate should be applied
	 */
	public void setTarget(int newTargetBit){
		this.targetBit = newTargetBit;
		initGate();
	}
	
	/**
	 * @return The DenseMatrix used to apply gate
	 */
	public DenseMatrix getGate() {
		return gate;
	}

	/**
	 * Passes a custom matrix to the gate object
	 * @param gate The matrix to update with
	 */
	public void setGate(DenseMatrix gate) {
		this.gate = gate;
	}

	/**
	 * @return The size of the register (in bits)
	 */
	public int getRegSize() {
		return regSize;
	}

	/**
	 * @param regSize The new size of the register
	 */
	public void setRegSize(int regSize) {
		this.regSize = regSize;
	}
}
