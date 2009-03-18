package main;
import gates.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;



public class DataIO {
	
	public DataIO(){}
	
	public Circuit readFromPropertiesFile(String inputName){
		// method parameters
		Circuit circuit = null;
		Register register = null;
	 	File input = new File(inputName);
	 	boolean display = false;
		
		
		 try {	
			 	
			 	ArrayList<Gate> gates = new ArrayList<Gate>(0);
			 	Gate newGate = null; 
			 	
			 	// loads properties file
			 	Properties properties = new Properties();
		        properties.load(new FileInputStream(input));
		        int numberOfGates = Integer.parseInt(properties.getProperty("numberOfGates"));
		        int numberOfQbits = Integer.parseInt(properties.getProperty("numberOfQbits"));
		        //System.out.println(numberOfGates);
		       display = Boolean.parseBoolean(properties.getProperty("display"));
		        // parses gates to gate array
		       for(int i = 0; i < numberOfGates; i++){
		     
		        	String currentGate = null;
		        	currentGate =  properties.getProperty(Integer.toString(i));
		        	newGate = gateType(currentGate,numberOfQbits);
		        	//System.out.println(newGate.getName());
		        	gates.add(newGate);
		       	}
		       //creates circuit
		       register = new Register(numberOfQbits,true);
		       circuit = new Circuit(register,display);
		        for(int i=0;i < gates.size();i++){
		        	circuit.addGate(gates.get(i));
		        }
		        return circuit;
		    } catch (IOException e) {System.out.println("null circuit"); return circuit;}

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
    	
    	
    	if(name[0].equals("CNot")){
			if(name.length != 3){System.out.println("CNot arguments out of range");return gate;}
			gate = new CNot(Integer.parseInt(name[1]),Integer.parseInt(name[2])); 
			gate.setNumQubits(n); 
			return gate;
			}
    	
    	if(name[0].equals("FuncHad")){
    		if(name.length != 2){System.out.println("Hadamard arguments out of range");return gate;}
    		gate = new FuncHad(Integer.parseInt(name[1])); 
    		gate.setNumQubits(n);
    		return gate;
    		}
    	
    	if (name[0].equals("Grovers")){
    		if(name.length != 2){System.out.println("Grovers arguments out of range");return gate;}
    		gate = new Grovers(Integer.parseInt(name[1]));
    		gate.setNumQubits(n);
    		return gate;
    		}
    	
    	if(name[0].equals("Had")){
    		if(name.length > 3){System.out.println("Hadamard arguments out of range");return gate;}
    			if(name.length == 3){gate = new Had(n,Integer.parseInt(name[2]));}
    			if(name.length == 2){
    				gate = new Had(Integer.parseInt(name[1]));
    				gate.setNumQubits(n);
    				}
    			else{gate = new Had(); gate.setNumQubits(n);}
    		return gate;
    		}
    		
    	if(name[0].equals("Measurement")){
    		if(name.length != 1){System.out.println("Measurement arguments out of range");return gate;}
    		gate = new Measurement();
    		gate.setNumQubits(n);
    		return gate;}	
    	
    	if(name[0].equals("Not")){
    		if(name.length != 2){System.out.println("Not arguments out of range");}
    		gate = new Not(Integer.parseInt(name[1])); 
    		gate.setNumQubits(n); 
    		return gate;
    		}
    	
    	if(name[0].equals("Randomiser")){
    		if(name.length > 2){System.out.println("Randomiser arguments out of range");}
    		if(name.length == 2){
    			gate = new Randomiser(Integer.parseInt(name[1]));
    			gate.setNumQubits(n);
    			return gate;
    			}
    		else{
    			gate = new Randomiser(); gate.setNumQubits(n);}
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
    	
    	
    	
    	
		
    	else{System.out.println("null gate" + name[0]);return gate;}
	
    	}
    
 // Write results to properties file.
    public void writeRegisterToFile(Register register, String output){
    	Properties properties = new Properties();
        try {
            properties.store(new FileOutputStream(output), register.getStateVector().toString());
        } catch (IOException e) {
        }

    }
    
    }