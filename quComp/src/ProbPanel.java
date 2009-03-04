import gates.Register;

import java.awt.*; 

import javax.swing.JPanel;
import maths.StateVector;


public class ProbPanel extends JPanel {

	StateVector vector;
	//size
	int X,Y;
	double[] amps;
	public ProbPanel(int x,int y){
		X=x;
		Y=y;
	}
	
	public void update(Register reg){
		this.vector=reg.getStateVector();
		repaint();
	}
	
	public void paintComponent(Graphics g){
		Dimension d = new Dimension(getSize());
		this.X = d.width;
		this.Y = d.height;
		int n = vector.getNumRows(); //Number of boxes
		int size = X/n;		
		amps = new double[n];
		double sum =0;
		
		double a = 0;
		double b = 1;
		
		for(int i=0;i<n;i++){
			double squared = vector.getAmp(i).getMagnitude()*vector.getAmp(i).getMagnitude();
			amps[i]=squared;
			sum+=squared;
		}
		for(int i=0;i<n;i++){
			amps[i]/=sum;
			g.setColor(Color.getHSBColor(0.5f,1f, new Double(amps[i]*(b-a)+a).floatValue()));
			g.fillRect(size*i,0,size*(i+1),Y);
		}
		for(int i=0;i<n-1;i++){
			g.setColor(Color.white);
			g.drawLine(size*(i+1),0,size*(i+1),Y);
		}
			
		}
	}

