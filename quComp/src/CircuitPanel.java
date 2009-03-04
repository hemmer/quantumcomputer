
import java.awt.*;

import javax.swing.*;
import gates.*;


	class CircuitPanel extends JPanel{

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
			ctrlImage = Toolkit.getDefaultToolkit().getImage("src/ctrl.GIF");
		}

		public void paintComponent(Graphics g){

			super.paintComponent(g);
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
					g.drawLine(xoffset + i*betweengates-betweengates+halfimagesize, yoffset+betweenlines*next.getTargetBit()-halfimagesize
							,xoffset + i*betweengates-betweengates+halfimagesize, yoffset+betweenlines*next.getCtrl(0));
					g.drawImage(ctrlImage, xoffset + i*betweengates-betweengates+sizediff/2, yoffset+betweenlines*ctrlBit-halfctrlsize, this);
				}

				g.drawImage(gateimg, xoffset + i*betweengates-betweengates, yoffset+betweenlines*next.getTargetBit()-halfimagesize, this);
			}
			
			
			
			
			
			
		}

		public void update(){
			repaint();
		}
		
		public int round(double x){
			return (int)Math.rint(x);
		}


	}
