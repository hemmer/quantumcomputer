package maths;

import maths.Matrix;

public class DenseMatrix extends Matrix {
	
	int numRows;
	int numCols;
    ComplexNum[][] matrix;
	
	// construct rxc matrix of type specify by string
	public DenseMatrix(int r, int c, String type){
		numRows = r;
		numCols = c;

		if(type.toLowerCase() == "hadamard"){
			initHadamard();
		}else if(type.toLowerCase() == "zero"){
			initZero();	
		}else if(type.toLowerCase() == "identity"){
			initIdentity();	
		}else{
			initZero();	
		}
		
	}
	
	// if no type specified, create zero filled matrix
	public DenseMatrix(int r, int c){
		
		numRows = r;
		numCols = c;

		initZero();	
	}
	
	// creates 2x2 hadamard gate
	public void initHadamard(){
		if(numCols != 2 || numRows != 2){
			System.out.println("hadamard must be 2x2 in size");
		}else{
			matrix = new ComplexNum[numRows][numCols];
			ComplexNum entry = new ComplexNum(1/Math.sqrt(2));
		
			// initialise to hadamard gate
			for(int i = 0; i < numRows; i++){
				for(int j = 0; j < numCols; j++){
					matrix[i][j] = entry;
				}
			}
			matrix[1][1] = matrix[1][1].multiply(new ComplexNum(-1.0));  // negate last entry (defn of hadamard)
		}
	}
	
	// creates zero filled matrix
	public void initZero(){
		
		matrix = new ComplexNum[numRows][numCols];

		// initialise every entry to 0
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numCols; j++){
				matrix[i][j] = new ComplexNum(0.0);
			}
		}
	}
	
	// initialises an indentity matrix
	public void initIdentity(){
		
		initZero();   // create zero-filled matrix
		
		for(int i = 0; i < numRows; i++){
			matrix[i][i] = new ComplexNum(1.0);   // set diagonal equal to 1
		}
	}
	
	public static DenseMatrix multiply(DenseMatrix matrix1, DenseMatrix matrix2){
		
		// if matrix1 is axb, and matrix2 is cxd
		// resulting matrix is axd
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
	Finds the tensor product of two matrices
	
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
	Find the tensor product of an array of matrices
	
	*/
	public static DenseMatrix tensorProductArray(DenseMatrix[] matrixArray){
		
		// start with first entry
		DenseMatrix tempMatrix = (DenseMatrix) matrixArray[0];
		
		// for each matrix in array, tensor product onto the previous ones
		for(int i = 1; i < matrixArray.length; i++){
			tempMatrix = DenseMatrix.tensorProduct(tempMatrix, matrixArray[i]);
		}
		
		return tempMatrix;
	} 

	/**
	Scales every element in a matrix by a factor of type Num

	*/
	public static DenseMatrix scale(ComplexNum factor, DenseMatrix m){
		
		DenseMatrix tempMatrix = new DenseMatrix(m.getNumRows(), m.getNumCols());
		
		for(int i = 0; i < m.getNumRows(); i++){
			for(int j = 0; j < m.getNumCols(); j++){
				ComplexNum value = m.getElem(i,j).multiply(factor);
				tempMatrix.setElem(i, j, value);
			}
		}
		return tempMatrix;
	}
	
	// getter/setters
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
	
	// display matrix in text format
	public String toString(){
		String string = "";
		
        for(int i = 0; i < numRows ; i++){
        	string += "[";
        	for(int j = 0; j < numCols; j++){
        		string += matrix[i][j] + " ";
        	}
        	string += "]\n";
        }
        return string;

	}
	
}
