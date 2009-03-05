
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import gates.*;


	class CircuitPanel extends JPanel implements MouseListener{

		Circuit circuit;
		int width;
		int height;
		int xoffset = 10;
		int yoffset = 40;
		int betweenlines = 40;
		int betweengates = 40;
		int imagesize = 32;
		int halfimagesize = imagesize/2;
		int ctrlsize = 20;
		int halfctrlsize = 10;
		int sizediff = imagesize -ctrlsize;
		Image ctrlImage;
		
		
		public CircuitPanel(Circuit circuit,int width,int height){

			this.circuit = circuit;
			setBackground(Color.DARK_GRAY);
			this.width = width;
			this.height = height;
			ctrlImage = Toolkit.getDefaultToolkit().getImage("src/ctrl.PNG");
			addMouseListener(this);
		}

		public void paintComponent(Graphics g){

			super.paintComponent(g);
			g.setColor(Color.red);
			g.drawLine(xoffset + circuit.getCurrent()*betweengates-betweengates+halfimagesize, 0, xoffset + circuit.getCurrent()*betweengates-betweengates+halfimagesize, height);
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
						g.drawImage(ctrlImage, xoffset + i*betweengates-betweengates+sizediff/2, yoffset+betweenlines*next.getCtrl(j)-halfctrlsize, this);
				
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
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		} 


	}
