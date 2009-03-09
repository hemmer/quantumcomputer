import gates.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;



public class DataIO {
	// this is very incomplete, but will read in Grovers, Had, CNot, Measurement and Toffoli gates.
	
	private File input = null;
	private Circuit circuit;
	private Register register = null;
	boolean display = false;
	int numQBits;		
	
	public DataIO(String input){this.input = new File(input);}
	public DataIO(){this.input = new File("input.properties");}
	
	public Circuit readFromPropertiesFile(){
		
		 try {	
			 	ArrayList<Gate> gates = new ArrayList<Gate>(0);
			 	Gate newGate = null; 
			 	
			 	// loads properties file
			 	Properties properties = new Properties();
		        properties.load(new FileInputStream(input));
		        int numberOfGates = Integer.parseInt(properties.getProperty("numberOfGates"));
		        int numberOfQbits = Integer.parseInt(properties.getProperty("numberOfQbits"));
		        //System.out.println(numberOfGates);
		        
		        // parses gates to gate array
		       for(int i = 0; i < numberOfGates; i++){
		     
		        	String currentGate = null;
		        	currentGate =  properties.getProperty(Integer.toString(i));
		        	newGate = gateType(currentGate,numberOfQbits);
		        	//System.out.println(newGate.getName());
		        	gates.add(newGate);
		       	}
		       //creates circuit
		       this.register = new Register(numberOfQbits,true);
		       this.circuit = new Circuit(register,display);
		        for(int i=0;i < gates.size();i++){
		        	this.circuit.addGate(gates.get(i));
		        }
		        return circuit;
		    } catch (IOException e) {System.out.println("null circuit"); return this.circuit;}

	}
		
    //handles parsing data for gates. 
	//assumes string format of gateName,targetBit,controlBit1,ControlBit2,etc...
    private Gate gateType(String currentGate, int n){
    	Gate gate = null;
    	//System.out.println(currentGate);
    	String[] name = currentGate.split(",");
    	
    	for(int i =0; i < name.length ; i++){
    	
    	//System.out.println("entry no. " + i + name[i]);
    	
    	 
    	
    	}
    	if(name[0].equals("Had")){
    		//if(name.length > 2){System.out.println("Hadamard arguments out of range");return gate;}
    		gate = new Had(n,Integer.parseInt(name[1])); return gate;}
    	if(name[0].equals("CNot")){
    		gate = new CNot(Integer.parseInt(name[1]),Integer.parseInt(name[2])); 
    		gate.setNumQubits(n); 
    		return gate;
    		}
    	if(name[0].equals("Toffoli")){
			int[] controls = new int[name.length - 2];

    		for(int i  : controls){
    			controls[i] = Integer.parseInt(name[i+2]);
    		}
    		gate = new Toffoli(Integer.parseInt(name[1]),controls);
    		return gate;
    		}
    	if(name[0].equals("Measurement")){
    		gate = new Measurement();
    		gate.setNumQubits(n);
    		return gate;}
    	if (name[0].equals("Grovers")){
    		gate = new Grovers(Integer.parseInt(name[1]));
    		gate.setNumQubits(n);
    		return gate;
    	}
		else{System.out.println("null gate");return gate;}

    		
    	
    	}
}