
	import java.awt.*;
	import javax.swing.*;

	class BlochGui extends JFrame {

		BlochPanel graphics;
			
				public BlochGui(int size) {
		
					//set window title and stop the user from being able to resize the window
					//setResizable(false);
					setTitle("Bloch Sphere/Circle");
					//create the table
					graphics = new BlochPanel(size);
					//set the size of the table
					graphics.setPreferredSize(new Dimension(size,size));
					//fit the panel to the GUI
					pack();
					//add to the content pane
					getContentPane().add(graphics, BorderLayout.CENTER);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					setSize(size+30,size+35);//
					setVisible(true);
				

	       }
				
				public void update(double angle){
					
					graphics.update(angle);
					
				}
		   
		   
		   
	}
