
public class Graphics {

	public Graphics(CircuitInterface circuit){
		
		circuit.applyAll();
		System.out.println();
		System.out.println(circuit.getRegister());
		
	}
	
}
