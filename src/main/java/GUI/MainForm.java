package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Service.Service;

public class MainForm extends JFrame {
	Service s;
	
	public static JPanel p;
	
	public MainForm(Service s) {
		super();
		this.s = s;
		super.setTitle("AplicatieLicenta");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setVisible(true);
		super.setSize(900, 600);
		super.setLocationRelativeTo(null);
		
		p = new LoginPanel(s);
		this.add(p);
		this.getContentPane().revalidate();
	}
}