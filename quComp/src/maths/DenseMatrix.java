package maths;

/**
 * Dense representation of a matrix, includes methods for basic matrix algebra
 * @author Ewan Hemingway
 *
 */
public class DenseMatrix extends Matrix {
	
	int numRows;
	int numCols;
    ComplexNum[][] matrix;
	
	/**
	 * Create a matrix of dimensions r x c
	 * @param r number of rows
	 * @param c number of columns
	 * @param type type of matrix to be generated, possibles are 'hadamard', 'identity', 'zero', 'not'. 
	 * Default case is zero-filled matrix
	 */
	public DenseMatrix(int r, int c, String type){
		numRows = r;
		numCols = c;

		initMatrixType(type);	
	}
	
	/**
	 * Creates an n x n square matrix of specified type.
	 * @param n
	 * @param type
	 */
	public DenseMatrix(int n, String type){
		numRows = n;
		numCols = n;
		initMatrixType(type);
	}
	
	/**
	 * Creates an n x n identity matrix.
	 * @param n
	 */
	public DenseMatrix(int n){
		numRows = n;
		numCols = n;
		initIdentity();
	}
	
	/**
	 * Creates a matrix with the specified values.
	 * @param numQubits
	 */
	public DenseMatrix(double[][] values){
		
		this.numRows=values.length;
		this.numCols=values[0].length;
		
		ComplexNum[][] complex = new ComplexNum[numRows][numCols];
		for(int a=0;a<numCols;a++){
			for(int b=0;b<numRows;b++){
				complex[a][b]=new ComplexNum(values[a][b]);
			}
		}
		this.matrix=complex;
		
	}
	
	/**
	 * Initialises the matrix to the specified type
	 * @param type which matrix to generate, default is zero-filled
	 */
	public void initMatrixType(String type){
		if(type.toLowerCase() == "hadamard"){
			initHadamard();
		}else if(type.toLowerCase() == "zero"){
			initZero();	
		}else if(type.toLowerCase() == "identity"){
			initIdentity();
		}else if(type.toLowerCase() == "not"){
			initNot();
		}else{
			initZero();	
		}
	}
	
	/**
	 * Creates r x c zero-filled matrix  
	 * @param r number of rows
	 * @param c number of columns
	 */
	public DenseMatrix(int r, int c){	
		numRows = r;
		numCols = c;
		initZero();	
	}
		
	/**
	 * Initialises a 2x2 hadamard matrix
	 */
	public void initHadamard() throws IllegalArgumentException {
		if(numCols != 2 || numRows != 2) throw new IllegalArgumentException("Hadamard can only be generated for a 2x2 matrix.");

		matrix = new ComplexNum[numRows][numCols];
		ComplexNum entry = new ComplexNum(1/Math.sqrt(2));
	
		for(int i = 0; i < numRows; i++)
			for(int j = 0; j < numCols; j++)
				matrix[i][j] = entry;
			
		
		matrix[1][1] = matrix[1][1].multiply(new ComplexNum(-1.0));  // negate last entry (defn of hadamard)
		
	}
	
	/**
	 * Initialises a zero-filled matrix 
	 */
	public void initZero(){
		
		matrix = new ComplexNum[numRows][numCols];

		for(int i = 0; i < numRows; i++)
			for(int j = 0; j < numCols; j++)
				matrix[i][j] = new ComplexNum(0.0);	
	}
	
	public void initZero(int n){
		
		matrix = new ComplexNum[n][n];

		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				matrix[i][j] = new ComplexNum(0.0);	
	}
	
	/**
	 * Creates identity matrix. Must be a square matrix.
	 * 
	 */
	public void initIdentity() throws IllegalArgumentException{
		if(numCols != numRows) throw new IllegalArgumentException("Identity can only be generated for a square matrix.");
		initZero();   // create zero-filled matrix
		
		for(int i = 0; i < numRows; i++) matrix[i][i] = new ComplexNum(1.0);   // set diagonal equal to 1
	}
	
	public void initNot(){
		// must be square!!! 
		initZero();
		for(int i = 0; i < numRows; i++){
			matrix[i][numRows-i-1] = new ComplexNum(1.0);   // set diagonal equal to 1
		}

	}
	
	public void initNot(int n){
		// must be square!!! 
		initZero(n);
		for(int i = 0; i < numRows; i++){
			matrix[i][numRows-i-1] = new ComplexNum(1.0);   // set diagonal equal to 1
		}

	}
	
	/**
	 * Multiplies two matrices together.
	 * @param matrix1
	 * @param matrix2
	 * @return product of two matrices	  
	 */
	public static DenseMatrix multiply(DenseMatrix matrix1, DenseMatrix matrix2) throws IllegalArgumentException {
		
		if(matrix1.getNumCols() != matrix2.getNumRows()) throw new IllegalArgumentException("Matrices can't be multiplied, wrong dimensions.");
		
		// if matrix1 is a x b, and matrix2 is c x d
		// resulting matrix is a x d only if b=c
		DenseMatrix tempMatrix = new DenseMatrix(matrix1.getNumRows(), matrix2.getNumCols());
		
		for (int i=0; i<matrix1.getNumRows(); i++){
			for (int j=0; j<matrix2.getNumCols(); j++){
				for (int k=0; k<matrix1.getNumCols(); k++){
					ComplexNum tIJ = tempMatrix.getElem(i,j);
					ComplexNum aIK = matrix1.getElem(i,k);
					ComplexNum bKJ = matrix2.getElem(k,j);
					
					// sum aIK*bKJ, tIJ tracks sum
					ComplexNum value = tIJ.add(aIK.multiply(bKJ));
        			tempMatrix.setElem(i, j, value);
				}
			}
		}
		return tempMatrix;
	}
	
	/**
	 * Finds the tensor product of two matrices.	
	 * @param matrix1
	 * @param matrix2
	 * @return tensor product of two matrices
	 */
	public static DenseMatrix tensorProduct(DenseMatrix matrix1, DenseMatrix matrix2){

		int newRowSize = matrix1.getNumRows()*matrix2.getNumRows();   // find new dimensions
		int newColSize = matrix1.getNumCols()*matrix2.getNumCols();
		DenseMatrix tempMatrix = new DenseMatrix(newRowSize, newColSize);   // initialise temporary matrix
			
		for(int z = 0; z<matrix1.getNumRows(); z++){
			for(int i=0; i<matrix1.getNumCols(); i++){
				for(int j=0; j<matrix2.getNumRows(); j++){
					for(int k=0; k<matrix2.getNumCols(); k++){
						// multiply matrix2 by every entry in matrix1
						ComplexNum value = matrix1.getElem(z,i).multiply(matrix2.getElem(j,k));
						tempMatrix.setElem(j + (z*matrix2.getNumRows()), k + (i*matrix2.getNumCols()), value); 			
					}	
				}	
			}	
		}
		return tempMatrix;
	}
	
	/**
	 * Transposes a matrix, in particular used for finding outer product of basis states
	 * @param matrix1 
	 * @return transposed matrix 
	 */
	public static DenseMatrix tranpose(DenseMatrix matrix){
		DenseMatrix tempMatrix = new DenseMatrix(matrix.getNumCols(), matrix.getNumRows()); // swap rows/cols
		for(int i = 0; i < tempMatrix.getNumRows(); i++)
			for(int j = 0; j < tempMatrix.getNumCols(); j++)
				tempMatrix.setElem(i, j, matrix.getElem(j, i));
		return tempMatrix;
	}
	
	/**
	 * Finds the outer product of two vectors, only really useful for 1D matrices
	 * @param matrix
	 * @return Outer product of two vectors
	 */
	public static DenseMatrix outerProduct(DenseMatrix matrix){
		return DenseMatrix.tensorProduct(matrix, DenseMatrix.tranpose(matrix));		
	}
	
	/**
	 * Tensor products each element in an array of matrices with the next.
	 * In particular used to generate matrix for n-qubit register.  
	 * @param matrixArray
	 * @return tensor product of the array
	 */
	public static DenseMatrix tensorProductArray(DenseMatrix[] matrixArray){
		
		// start with first entry
		DenseMatrix tempMatrix = (DenseMatrix) matrixArray[0];
		
		// for each matrix in array, tensor product into the previous ones
		for(int i = 1; i < matrixArray.length; i++){
			tempMatrix = DenseMatrix.tensorProduct(tempMatrix, matrixArray[i]);
		}
		
		return tempMatrix;
	}
	
	/**
	 * Adds two matrices
	 * @param matrix1
	 * @param matrix2
	 * @return Sum of the matrices
	 */
	public static DenseMatrix add(DenseMatrix matrix1, DenseMatrix matrix2){
		//catch exception
		DenseMatrix tempMatrix = new DenseMatrix(matrix1.getNumRows(), matrix1.getNumCols());
		for(int r = 0; r < tempMatrix.getNumRows(); r++)
			for(int c = 0; c < tempMatrix.getNumCols(); c++)
				tempMatrix.setElem(r, c, matrix1.getElem(r, c).add(matrix2.getElem(r, c)));
		return tempMatrix;
	}

	/**
	 * Scales a matrix by a constant factor.
	 * @param factor
	 * @param matrix
	 * @return scaled matrix
	 */
	public static DenseMatrix scale(ComplexNum factor, DenseMatrix matrix){
		
		DenseMatrix tempMatrix = new DenseMatrix(matrix.getNumRows(), matrix.getNumCols());
		
		for(int i = 0; i < matrix.getNumRows(); i++){
			for(int j = 0; j < matrix.getNumCols(); j++){
				ComplexNum value = matrix.getElem(i,j).multiply(factor);
				tempMatrix.setElem(i, j, value);
			}
		}
		return tempMatrix;
	}
	
	/**
	 * Swaps two rows of the matrix
	 * @param rowA
	 * @param rowB
	 * @throws IllegalArgumentException must be within range of matrix
	 */
	public void swapRows(int rowA, int rowB) throws IllegalArgumentException{
		if(rowA > matrix.length || rowA < 0 || rowB > matrix[0].length || rowB < 0)
			throw new IllegalArgumentException("Invalid row choice, must be in range of matrix.");
		
		int rowLength = matrix[0].length;
		
		// manually copy matrix  into temporary array to avoid usual pointer issues
		ComplexNum[] tempA = new ComplexNum[rowLength];
		for(int i = 0; i < rowLength; i++) tempA[i] = matrix[rowA][i];
				
		for(int i = 0; i < rowLength; i++){
			matrix[rowA][i] = matrix[rowB][i];
			matrix[rowB][i] = tempA[i];
		}
	}
	
	public int getNumCols(){
		return numCols;
	}
	
	public int getNumRows(){
		return numRows;
	}
	
	public void setElem(int rowIndex, int colIndex, ComplexNum value){
		matrix[rowIndex][colIndex] = value;
	}
	
	public ComplexNum getElem(int rowIndex, int colIndex){
		return matrix[rowIndex][colIndex];
	}
	
	/**
	 * Overrides toString method to display matrix as string
	 */
	public String toString(){
		String string = "";
		
        for(int i = 0; i < numRows ; i++){
        	string += "[";
        	for(int j = 0; j < numCols; j++) string += matrix[i][j] + " ";
        	string += "]\n";
        }
        return string;
	}
}
