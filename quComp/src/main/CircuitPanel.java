package main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import gates.*;

/**
 * 
 * Displays the quantum circuit
 * 
 * @author Ewan Hemmingway<br>Ian Sullivan<br>James Vokes
 *
 */


	class CircuitPanel extends JPanel implements MouseListener{

		Circuit circuit;
		private int width;
		private int height;
		private final int xoffset = 10;
		private final int yoffset = 40;
		private final int betweenlines = 40;
		private final int betweengates = 50;
		private final int imagesize = 32;
		private final int halfimagesize = imagesize/2;
		private final int ctrlsize = 8;
		private final int halfctrlsize = ctrlsize/2;
		Image ctrlImage;
		
		
		public CircuitPanel(Circuit circuit,int width,int height){

			this.circuit = circuit;
			setBackground(Color.LIGHT_GRAY);
			this.width = width;
			this.height = height;
			ctrlImage = Toolkit.getDefaultToolkit().getImage("src/ctrl.PNG");
			addMouseListener(this);
		}

		public void paintComponent(Graphics g){

			super.paintComponent(g);
			g.setColor(Color.red);
			g.drawLine(xoffset + circuit.getCurrent()*betweengates-betweengates+halfimagesize-betweengates/2, 0, xoffset + circuit.getCurrent()*betweengates-betweengates+halfimagesize-betweengates/2, height);
			g.setColor(Color.black);
			//draw the lines
			for (int i=0;i<circuit.getRegister().getNumQubits();i++){
				g.drawLine(0, yoffset+betweenlines*i, width, yoffset+betweenlines*i);
			}
			//draw the gates
			int ctrlBit;
			for (int i=1;i<=circuit.getTotal();i++){
				
				
				Gate next = circuit.getGate(i);
				Image gateimg = next.getImage();
				//draw ctrl bit
				ctrlBit = next.getCtrl(0);
				if (ctrlBit>=0){
					for (int j=0;j<next.getCtrl().length;j++){
						g.drawLine(xoffset + i*betweengates-betweengates+halfimagesize, yoffset+betweenlines*next.getTargetBit()-halfimagesize
							,xoffset + i*betweengates-betweengates+halfimagesize, yoffset+betweenlines*next.getCtrl(j));
						//g.drawImage(ctrlImage, xoffset + i*betweengates-betweengates+sizediff/2, yoffset+betweenlines*next.getCtrl(j)-halfctrlsize, this);
						g.fillOval(xoffset + i*betweengates-betweengates+-halfctrlsize+halfimagesize, yoffset+betweenlines*next.getCtrl(j)-halfctrlsize, ctrlsize, ctrlsize);
						
					}
				}
				//draw gate
				if (next.getTargetBit()>=0){
					g.drawImage(gateimg, xoffset + i*betweengates-betweengates, yoffset+betweenlines*next.getTargetBit()-halfimagesize, this);
				}
				else{
					for (int j=0;j<circuit.getRegister().getNumQubits();j++){
						g.drawImage(gateimg, xoffset + i*betweengates-betweengates, yoffset+betweenlines*j-halfimagesize, this);
					}
				}
			}
			
			
			
			
			
			
		}

		public void update(){
			repaint();
		}
		
		public int round(double x){
			return (int)Math.rint(x);
		}
		/**
		 * Left click applies the next gate
		 * Right click resets the circuit
		 * Middle mouse button applies the rest of the gates
		 */
		public void mouseClicked(MouseEvent e){
		    
			if (e.getButton() == MouseEvent.BUTTON1)// left mouse click
		    {
		        circuit.apply();
		        repaint();
		    }
		    if (e.getButton() == MouseEvent.BUTTON3){
		        
		    	circuit.reset();
		    	repaint();
			}
		    if (e.getButton() == MouseEvent.BUTTON2){
		        
		    	circuit.applyAll();
		    	repaint();
			}
		    
		}

		
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		} 


	}
