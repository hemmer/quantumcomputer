package main;

	import java.awt.*;
import javax.swing.*;

/**
 * 
 * Displays the quantum circuit
 * 
 * @author Ewan Hemmingway<br>Ian Sullivan<br>James Vokes
 *
 */

	class CircuitGui extends JFrame {
		
		CircuitPanel panel;
		int width = 1000;

		/**
		 * Creates a window that displays a circuit object.
		 * @param circuit the circuit that is to be displayed
		 */
		public CircuitGui(Circuit circuit) {
		
				setTitle("Circuit");
				pack();
				//add to the content pane
				int height = circuit.getRegister().getNumQubits()*40+70;
				setBounds(0,300,width,height);
				panel = new CircuitPanel(circuit,width,height);
				
				getContentPane().add(panel, BorderLayout.CENTER);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//setSize(width,height);//
				setVisible(true);
				

	       }
				

				/**
				 * Updates the panel
				 */
				public void update(){
					
					panel.update();
					
				}
		   
		   
		   
	}
