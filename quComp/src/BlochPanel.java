
	import java.awt.*;
import javax.swing.*;
import maths.ComplexNum;

	class BlochPanel extends JPanel{

		private int yoffset = 20;
		private int size;
		private int innerRadius = 25;
		private ComplexNum z;
		
		
		void setZ(ComplexNum z) {
			this.z = z;
		}



		ComplexNum getZ() {
			return z;
		}



		void setYoffset(int yoffset) {
			this.yoffset = yoffset;
		}



		int getYoffset() {
			return yoffset;
		}



		void setInnerRadius(int innerRadius) {
			this.innerRadius = innerRadius;
		}



		int getInnerRadius() {
			return innerRadius;
		}



		public BlochPanel(int size){

			this.setSize(size);
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
			
			
			int half = returnSize()/2;
			g.setColor(Color.cyan);
			g.fillOval(0, 0+getYoffset(), returnSize(), returnSize());
			g.setColor(Color.red);
			//re
			g.drawLine(0, half+getYoffset(),returnSize(),half+getYoffset());
			g.drawString("Re",returnSize()+5, half+getYoffset());
			//img
			g.drawLine(half, 0+getYoffset(),half,returnSize()+getYoffset());
			g.drawString("Img",half, 0+getYoffset()-5);
			//outline
			g.setColor(Color.black);
			g.drawOval(0, 0+getYoffset(), returnSize(), returnSize());
			//vector
			g.drawLine(half, half+getYoffset(),half+round(half*getZ().getReal()),half-round(half*getZ().getImag())+getYoffset());
			
			
		}
		/*
		public void update(double angle){
			
			mag = 1;
			this.angle = angle;
			repaint();

		}*/
		
		public void update(ComplexNum z){
			
			this.setZ(z);
			repaint();

		}
		
		public int round(double x){
			
			return (int)Math.rint(x);

		}



		void setSize(int size) {
			this.size = size;
		}



		int returnSize() {
			return size;
		}


	}
