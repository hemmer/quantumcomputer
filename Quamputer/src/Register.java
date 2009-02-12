
public class Register{

	double[] values;
	int size;
	int cubits;
	


	public double[] getValues() {
		return values;
	}

	public void setValues(double[] values) {
		this.values = values;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCubits() {
		return cubits;
	}

	public void setCubits(int cubits) {
		this.cubits = cubits;
	}

	public Register(int n) {
		
		cubits=n;
		size=1;
		for (int i=0;i<n;i++){
			
			size=size*2;
			
		}
		values = new double[size];
		values[0]=1;
		for (int i=1;i<n;i++){
				values[i]=0;
		}
	}
	
	public String toString(){
		
			String s = "";
			for (int i=0;i<size;i++){
					s = s+"|"+i+"> = "+values[i]+"\n";
			}
			s = s.substring(0,s.length()-1);
			return (s);
		}

	public void normalise(){
		
		double sum = 0;
		for (int i=0;i<size;i++){
			sum=sum+(values[i]*values[i]);
		}
		sum = Math.sqrt(sum);
		for (int i=0;i<size;i++){
			values[i]=values[i]*sum;
		}
		
		
		
		
	}


}
