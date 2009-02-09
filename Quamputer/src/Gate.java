
public abstract class Gate extends DenseMatrix {

	String name;
	int targetBit;
	int ctrl1;
	int ctrl2;
	
	
	public Gate(double[][] i,int targetBit, int ctrl1, int ctrl2) {
		super(i);
		this.targetBit = targetBit;
		this.ctrl1 = ctrl1;
		this.ctrl2 = ctrl2;
	}


	
	
	
	
}
