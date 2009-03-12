package gates;
import java.awt.Image;
import java.awt.Toolkit;

import maths.ComplexNum;
import maths.Matrix;


public class Grovers extends DenseGate {

	boolean single;
	
	public Grovers(int SearchedElem) {
		super(1,-1,(new int[] {-1}),SearchedElem);
		single = false;
	}
	
	public Grovers(int SearchedElem, boolean single) {
		super(1,-1,(new int[] {-1}),SearchedElem);
		this.single = single;
	}
	
	public void setM(int n){
		
		int matrixSize = (int) Math.pow(2, n);

		Matrix invert = new Matrix(matrixSize);
		for(int i = 0; i < invert.getNumCols(); i++) invert.setElem(i, getSearchedElem(), new ComplexNum(-1.0,0.0));

		Matrix gate = new Matrix(matrixSize, matrixSize);
		ComplexNum entry = new ComplexNum((2.0/matrixSize), 0.0);
		
		// first generate the inversion about average operator
		// for i != j, gate[i][j] = 2/N
		//     i == j, gate[i][i] = 2/N - 1
		for(int i = 0; i < matrixSize; i++){
			for(int j = 0; j < matrixSize; j++){
				gate.setElem(i, j, entry);
				if(i == j) gate.setElem(i, j, entry.add(new ComplexNum(-1.0,0.0)));
			}
		}

		// invert searched element column, performed second as matrix multiplication
		// must be performed in reverse order
		for(int i = 0; i < invert.getNumCols(); i++){
			gate.setElem(i, getSearchedElem(), gate.getElem(i, getSearchedElem()).multiply(new ComplexNum(-1.0,0.0)));	
		}
		
		int optimalNumIter = (int) (Math.sqrt(matrixSize) * Math.PI)/ 4;
	
		for(int i = 0; i < optimalNumIter -1 && !single; i++) gate = Matrix.multiply(gate, gate);
		
		this.setM(gate);
		
	}
	
	public void setNumQubits(int N){
		this.numQubits=N;
		setM(numQubits);
	}

	public String getName() {
		return "Grovers";
	}

	@Override
	public int getNumArguments() {
		// searched element only
		return 1;
	}
	
	public Image getImage(){
		return Toolkit.getDefaultToolkit().getImage("src/grovers.png");
	}
}

