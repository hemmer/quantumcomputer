
	import java.awt.*;
	import java.awt.event.*;
import javax.swing.*;

	class BlochPanel extends JPanel{

		int size;
		double angle;
		int innerRadius = 25;
		
		
		public BlochPanel(int size){

			this.size = size;
			angle = 0;
			setBackground(Color.white);
		}
			



		public void paintComponent(Graphics g){

			super.paintComponent(g);
			g.setColor(Color.cyan);
			g.fillOval(0, 0, size, size);
			g.setColor(Color.black);
			g.drawOval(0, 0, size, size);
			g.drawLine(size/2, size/2,size/2+round((size/2)*Math.cos(angle)),size/2-round((size/2)*Math.sin(angle)));
			g.drawArc(size/2-innerRadius, size/2-innerRadius, innerRadius*2, round(innerRadius*2), 0, round(Math.toDegrees(angle)));
			g.drawLine(size/2, size/2,size/2+innerRadius,size/2);
			
		}
		
		public void update(double angle){
			
			
			this.angle = angle;
			repaint();

		}
		
		public int round(double x){
			
			return (int)Math.rint(x);

		}


	}
