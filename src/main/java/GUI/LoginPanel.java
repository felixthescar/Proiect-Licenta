			package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Service.Service;

public class LoginPanel extends JPanel{
	
	public static String username;
	public static String password;
	private Service s;
	
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JTextField usernameText;
	private JPasswordField passwordText;
	private JButton loginButton;
	private JButton registerButton;
	
	public LoginPanel(Service s) {
		super();
		setLayout(null);
		initComponents();
		this.s = s;
		initLoginButton();
		initRegisterButton();
	}
	
	private void initComponents() {
		
		this.setBackground(Color.DARK_GRAY);
		
		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(900/2-150, 600/2-65, 80, 25);
		usernameLabel.setForeground(Color.white);
		usernameLabel.setVisible(true);
		super.add(usernameLabel);
		
		usernameText = new JTextField(50);
		usernameText.setBounds(900/2-70, 600/2-65, 165, 25);
		usernameText.setBackground(Color.DARK_GRAY);
		usernameText.setForeground(Color.white);
		super.add(usernameText);
	
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(900/2-150, 600/2-25, 80, 25);
		passwordLabel.setForeground(Color.white);
		super.add(passwordLabel);
		
		passwordText = new JPasswordField(50);
		passwordText.setBounds(900/2-70, 600/2-25, 165, 25);
		passwordText.setBackground(Color.DARK_GRAY);
		passwordText.setForeground(Color.white);
		super.add(passwordText);
		
		passwordText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	String username = usernameText.getText();
	        	String password = new String(passwordText.getPassword());
	        	
	        	try {
					int privileges = s.tryLogin(username, password);
					LoginPanel.username = username;
					LoginPanel.password = password;
					if(privileges == 1) closeAndOpenAdminForm();
					else if(privileges == 2) closeAndOpenUserForm();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		
	}
	
	private void initRegisterButton() {
		registerButton  = new JButton("Register");
		//loginButton.setBounds(10, 80, 160, 25);
		registerButton.setBounds(900/2-70+85, 600/2+15, 80, 25);
		registerButton.setForeground(Color.white);
		registerButton.setBackground(Color.DARK_GRAY);
		super.add(registerButton);
		
		//when clicked
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	String username = usernameText.getText();
	        	String password = new String(passwordText.getPassword());
	        	
	        	try {
					s.tryRegister(username, password);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
	}
	
	private void initLoginButton() {
		loginButton  = new JButton("Login");
		//loginButton.setBounds(10, 80, 160, 25);
		loginButton.setBounds(900/2-70, 600/2+15, 80, 25);
		loginButton.setForeground(Color.white);
		loginButton.setBackground(Color.DARK_GRAY);
		super.add(loginButton);
		
		//when clicked
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	String username = usernameText.getText();
	        	String password = new String(passwordText.getPassword());
	        	
	        	try {
					int privileges = s.tryLogin(username, password);
					LoginPanel.username = username;
					LoginPanel.password = password;
					if(privileges == 1) closeAndOpenAdminForm();
					else if(privileges == 2) closeAndOpenUserForm();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
	}
	
	private void closeAndOpenAdminForm() {
		Container c = super.getParent();
		c.remove(MainForm.p);
		MainForm.p = new AdminPanel(s);
		c.add(MainForm.p);
		MainForm.p.revalidate();
	}
	
	private void closeAndOpenUserForm() {
		Container c = super.getParent();
		c.remove(MainForm.p);
		MainForm.p = new UserPanel(s);
		c.add(MainForm.p);
		MainForm.p.revalidate();
	}
}

