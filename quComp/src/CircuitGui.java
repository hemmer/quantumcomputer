
	import java.awt.*;
import javax.swing.*;

import maths.ComplexNum;

	class CircuitGui extends JFrame {
		
		CircuitPanel panel;
		int width = 1000;

		public CircuitGui(Circuit circuit) {
		
				setTitle("Circuit");
				pack();
				//add to the content pane
				int height = circuit.getRegister().getNumQubits()*40+70;
				setBounds(0,125,width,height);
				panel = new CircuitPanel(circuit,width,height);
				
				getContentPane().add(panel, BorderLayout.CENTER);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//setSize(width,height);//
				setVisible(true);
				

	       }
				

				
				public void update(){
					
					panel.update();
					
				}
		   
		   
		   
	}
