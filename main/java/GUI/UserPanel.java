package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Service.Service;

public class UserPanel extends JPanel {
	Service s;
	
	String[] tableHead = {"Manufacturer", "Model", "Price"};
	
	JTable stockTable;
	TableColumn tableColumnManufacturer;
	TableColumn tableColumnModel;
	TableColumn tableColumnQuantity;
	TableColumn tableColumnPrice;
	
	JTextField searchTextField;
	JLabel searchLabel;
	
	JButton backButton;
	JButton exitButton;
	
	public UserPanel(Service s) {
		super();
		this.s = s;
		super.setLayout(new FlowLayout());
		super.setBackground(Color.DARK_GRAY);
		
		initSearchTextField();
		initStockTable();
		initBackButton();
		initExitButton();
		this.revalidate();
		this.repaint();
	}
	
	private void initSearchTextField() {
		searchLabel = new JLabel("Search");
		searchTextField = new JTextField();
		searchLabel.setForeground(Color.white);
		searchTextField.setForeground(Color.white);
		searchTextField.setBackground(Color.DARK_GRAY);
		searchTextField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadAllSearchedDataInTable();
			}
			
		});
		searchTextField.setPreferredSize(new Dimension(100, 20));
		this.add(searchLabel);
		this.add(searchTextField);
	}
	
	private void loadAllSearchedDataInTable() {
		String s = searchTextField.getText();
		DefaultTableModel d = new DefaultTableModel();
		d.addColumn(tableHead[0]);
		d.addColumn(tableHead[1]);
		d.addColumn(tableHead[2]);
		int i = 0;
		for(String[] s1: this.s.stonkSearchUserData(s) ) {
			d.insertRow(i, s1);
			i ++;
		}
		stockTable.setModel(d);
		stockTable.revalidate();
		stockTable.repaint();
	}
	
	private void initBackButton() {
		backButton = new JButton("<");
		backButton.setPreferredSize(new Dimension(50, 50));
		backButton.setLocation(10, 10);
		backButton.setVisible(true);
		backButton.setBackground(Color.DARK_GRAY);
		backButton.setForeground(Color.white);
		
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
			
		});
		
		
		this.add(backButton);	
	}

	private void initExitButton() {
		exitButton = new JButton("X");
		exitButton.setPreferredSize(new Dimension(50, 50));
		exitButton.setLocation(10, 10);
		exitButton.setVisible(true);
		exitButton.setBackground(Color.DARK_GRAY);
		exitButton.setForeground(Color.white);
		
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});

		this.add(exitButton);
	}
	
	private void initStockTable() {
		
		stockTable = new JTable();
		tableColumnManufacturer = new TableColumn();
		tableColumnModel = new TableColumn();
		tableColumnPrice = new TableColumn();

		tableColumnManufacturer.setHeaderValue("Manufacturer");
		stockTable.addColumn(tableColumnManufacturer);
		tableColumnModel.setHeaderValue("Model");
		stockTable.addColumn(tableColumnModel);
		tableColumnPrice.setHeaderValue("Price");
		stockTable.addColumn(tableColumnPrice);
		
		stockTable.setPreferredSize(new Dimension(600, 300));
		stockTable.setBackground(Color.DARK_GRAY);
		stockTable.setForeground(Color.white);
		stockTable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		
		JScrollPane sp = new JScrollPane(stockTable);
		sp.setPreferredSize(new Dimension(600, 300));
		this.add(sp);
		
	}
	
	private void close() {
		Container c = super.getParent();
		c.remove(MainForm.p);
		MainForm.p = new LoginPanel(s);
		c.add(MainForm.p);
		MainForm.p.revalidate();
	}
}
