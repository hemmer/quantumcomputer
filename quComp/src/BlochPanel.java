
	import java.awt.*;
import javax.swing.*;
import maths.ComplexNum;

	class BlochPanel extends JPanel{

		int yoffset = 20;
		int size;
		int innerRadius = 25;
		ComplexNum z;
		
		
		public BlochPanel(int size){

			this.size = size;
			setBackground(Color.white);
		}
			


		/*
		public void paintComponent(Graphics g){

			super.paintComponent(g);
			
			
			
			g.setColor(Color.cyan);
			g.fillOval(0, 0, size, size);
			g.setColor(Color.black);
			g.drawOval(0, 0, size, size);
			g.drawLine(size/2, size/2,size/2+round((size/2)*Math.cos(angle)),size/2-round((size/2)*Math.sin(angle)));
			g.drawArc(size/2-innerRadius, size/2-innerRadius, innerRadius*2, round(innerRadius*2), 0, round(Math.toDegrees(angle)));
			g.drawLine(size/2, size/2,size/2+innerRadius,size/2);
			
		}*/
		
		public void paintComponent(Graphics g){

			super.paintComponent(g);
			
			
			int half = size/2;
			g.setColor(Color.cyan);
			g.fillOval(0, 0+yoffset, size, size);
			g.setColor(Color.red);
			//re
			g.drawLine(0, half+yoffset,size,half+yoffset);
			g.drawString("Re",size+5, half+yoffset);
			//img
			g.drawLine(half, 0+yoffset,half,size+yoffset);
			g.drawString("Img",half, 0+yoffset-5);
			//outline
			g.setColor(Color.black);
			g.drawOval(0, 0+yoffset, size, size);
			//vector
			g.drawLine(half, half+yoffset,half+round(half*z.getReal()),half-round(half*z.getImag())+yoffset);
			
			
		}
		/*
		public void update(double angle){
			
			mag = 1;
			this.angle = angle;
			repaint();

		}*/
		
		public void update(ComplexNum z){
			
			this.z = z;
			repaint();

		}
		
		public int round(double x){
			
			return (int)Math.rint(x);

		}


	}
