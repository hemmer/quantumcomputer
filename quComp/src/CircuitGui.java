
	import java.awt.*;
import javax.swing.*;

import maths.ComplexNum;

	class CircuitGui extends JFrame {
		
		CircuitPanel panel;
		int width = 1000;
		int height = 300;

		public CircuitGui(Circuit circuit) {
		
				//set window title and stop the user from being able to resize the window
				//setResizable(false);
				setTitle("Circuit");
				//create the table
				//fit the panel to the GUI
				pack();
				//add to the content pane

				panel = new CircuitPanel(circuit,width,height);
				getContentPane().add(panel, BorderLayout.CENTER);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setSize(width,height);//
				setVisible(true);
				

	       }
				

				
				public void update(){
					
					panel.update();
					
				}
		   
		   
		   
	}
