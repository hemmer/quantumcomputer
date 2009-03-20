package main;
import gates.Register;

import java.awt.Dimension;
import javax.swing.JFrame;


public class ProbGui extends JFrame {

	
	ProbPanel panel;
	
	public ProbGui(Register reg){
		panel = new ProbPanel(650, 250);
		panel.update(reg);
		this.setSize(new Dimension(650,250));
		setTitle("Probabilities");
		setVisible(true);
		this.add(panel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void update(Register reg){
		panel.update(reg);
	}
}
