
	import java.awt.*;
import javax.swing.*;

import maths.ComplexNum;

	class BlochGui extends JFrame {

		private BlochPanel graphics;;
			
		void setGraphics(BlochPanel graphics) {
			this.graphics = graphics;
		}

		BlochPanel returnGraphics() {
			return graphics;
		}



				public BlochGui(int size) {
		
					//set window title and stop the user from being able to resize the window
					//setResizable(false);
					setTitle("Bloch Sphere/Circle");
					//create the table
					setGraphics(new BlochPanel(size));
					//set the size of the table
					returnGraphics().setPreferredSize(new Dimension(size,size));
					//fit the panel to the GUI
					pack();
					//add to the content pane
					getContentPane().add(returnGraphics(), BorderLayout.CENTER);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					setSize(size+30,size+65);//
					setVisible(true);
				

	       }
				

				
				public void update(ComplexNum z){
					
					returnGraphics().update(z);
					
				}
		   
		   
		   
	}
