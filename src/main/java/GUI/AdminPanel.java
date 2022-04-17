package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Service.Service;

public class AdminPanel extends JPanel{
	Service s;
	
	String[] tableHead = {"ID", "Manufacturer", "Model", "Quantity", "Price"};
	
	JTable stockTable;
	TableColumn tableColumnManufacturer;
	TableColumn tableColumnModel;
	TableColumn tableColumnQuantity;
	TableColumn tableColumnPrice;
	
	JTextField searchTextField;
	JTextField removeTextField;
	JTextField addStockManufacturerTextField;
	JTextField addStockModelTextField;
	JTextField addStockQuantityTextField;
	JTextField addStockPriceTextField;
	JLabel searchLabel;
	JLabel removeLabel;
	JLabel addStockManufacturerLabel;
	JLabel addStockModelLabel;
	JLabel addStockQuantityLabel;
	JLabel addStockPriceLabel;
	
	
	JButton backButton;
	JButton exitButton;
	JButton stockAddButton;
	
	JMenuBar menuBar;
	JMenu stockMenu;
	JMenu userMenu;
	JMenuItem stockAdd, stockModify, stockSearch, stockDelete;
	JMenuItem userModify, userSearch, userDelete;
	
	public AdminPanel(Service s) {
		super();
		this.s = s;
		super.setLayout(new FlowLayout());
		super.setBackground(Color.DARK_GRAY);
		initJMenu();
		
		revalidare();
		
	}
	
	private void loadStockSearchPanel() {
		initSearchTextField();
		initStockTable();
		loadAllDataInTable();
		
		initBackButton();
		initExitButton();

		revalidare();
	}
	
	private void loadStockAddPanel() {
		initStockAddTextField();
		initStockAddButton();

		revalidare();
	}
	
	private void loadStockModifyPanel() {
		revalidare();
	}
	
	private void loadStockDeletePanel() {
		initRemoveTextField();
		initStockTable();
		loadAllDataInTable();
		
		initBackButton();
		initExitButton();
		revalidare();
	}
	
	private void removePanel() {
		this.removeAll();
		initJMenu();
	}
	
	private void initJMenu() {
		menuBar = new JMenuBar();
		stockMenu = new JMenu("StockActions");
		stockMenu.setForeground(Color.white);
		stockMenu.setBackground(Color.black);
		
		stockAdd = new JMenuItem("Adaugare Stock");
		stockModify = new JMenuItem("Modificare Stock");
		stockSearch = new JMenuItem("Cautare Stock");
		stockDelete = new JMenuItem("Stergere Stock");
		
		stockMenu.add(stockAdd);
		stockMenu.add(stockModify);
		stockMenu.add(stockSearch);
		stockMenu.add(stockDelete);
		
		menuBar.add(stockMenu);
		
		userMenu = new JMenu("UserActions");
		userMenu.setForeground(Color.white);
		userMenu.setBackground(Color.black);
		
		userModify = new JMenuItem("Modificare User");
		userSearch = new JMenuItem("Cautare User");
		userDelete = new JMenuItem("Stergere User");
		
		userMenu.add(userModify);
		userMenu.add(userSearch);
		userMenu.add(userDelete);
		
		menuBar.add(userMenu);
		
		this.add(menuBar);	
		
		stockAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel();
				loadStockAddPanel();
			}
		});
		
		stockSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel();
				loadStockSearchPanel();
			}
		});
		
		stockModify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel();
				loadStockModifyPanel();
			}
		});
		
		stockDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel();
				loadStockDeletePanel();
			}
		});
	}
	
	private void initRemoveTextField() {
		removeLabel = new JLabel("Id to remove");
		removeTextField = new JTextField();
		removeLabel.setForeground(Color.white);
		removeTextField.setForeground(Color.white);
		removeTextField.setBackground(Color.DARK_GRAY);
		String id = removeTextField.getText();
		removeTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeDataFromID();
				JOptionPane.showMessageDialog(null, "Produsul cu id-ul "+id+" a fost sters cu succes!");
				revalidare();
			}
		});
		removeTextField.setPreferredSize(new Dimension(100, 20));
		this.add(removeLabel);
		this.add(removeTextField);
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
	
	private void initStockAddTextField() {
		addStockManufacturerTextField = new JTextField();
		addStockManufacturerTextField.setForeground(Color.white);
		addStockManufacturerTextField.setBackground(Color.DARK_GRAY);
		addStockManufacturerTextField.setPreferredSize(new Dimension(100, 20));
		
		addStockModelTextField = new JTextField();
		addStockModelTextField.setForeground(Color.white);
		addStockModelTextField.setBackground(Color.DARK_GRAY);
		addStockModelTextField.setPreferredSize(new Dimension(100, 20));
		
		addStockQuantityTextField = new JTextField();
		addStockQuantityTextField.setForeground(Color.white);
		addStockQuantityTextField.setBackground(Color.DARK_GRAY);
		addStockQuantityTextField.setPreferredSize(new Dimension(100, 20));
		
		addStockPriceTextField = new JTextField();
		addStockPriceTextField.setForeground(Color.white);
		addStockPriceTextField.setBackground(Color.DARK_GRAY);
		addStockPriceTextField.setPreferredSize(new Dimension(100, 20));
		
		addStockManufacturerLabel = new JLabel("Producator");
		addStockManufacturerLabel.setForeground(Color.white);
		
		addStockModelLabel = new JLabel("Model");
		addStockModelLabel.setForeground(Color.white);
		
		addStockQuantityLabel = new JLabel("Cantitate");
		addStockQuantityLabel.setForeground(Color.white);
		
		addStockPriceLabel = new JLabel("Pret");
		addStockPriceLabel.setForeground(Color.white);
		
		this.add(addStockManufacturerLabel);
		this.add(addStockManufacturerTextField);
		this.add(addStockModelLabel);
		this.add(addStockModelTextField);
		this.add(addStockQuantityLabel);
		this.add(addStockQuantityTextField);
		this.add(addStockPriceLabel);
		this.add(addStockPriceTextField);
	}
	
	private void initStockAddButton() {
		stockAddButton = new JButton("Adaugare");
		stockAddButton.setForeground(Color.white);
		stockAddButton.setBackground(Color.DARK_GRAY);
		stockAddButton.setPreferredSize(new Dimension(80, 20));
		stockAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStockData();
				JOptionPane.showMessageDialog(null, "Produsul a fost adaugat in baza de date!");
			}
		});
		this.add(stockAddButton);
		
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
		stockTable.setForeground(Color.white);
		stockTable.setBackground(Color.DARK_GRAY);
		tableColumnManufacturer = new TableColumn();
		tableColumnModel = new TableColumn();
		tableColumnQuantity = new TableColumn();
		tableColumnPrice = new TableColumn();

		tableColumnManufacturer.setHeaderValue("Manufacturer");
		stockTable.addColumn(tableColumnManufacturer);
		tableColumnModel.setHeaderValue("Model");
		stockTable.addColumn(tableColumnModel);
		tableColumnQuantity.setHeaderValue("Quantity");
		stockTable.addColumn(tableColumnQuantity);
		tableColumnPrice.setHeaderValue("Price");
		stockTable.addColumn(tableColumnPrice);
		
		stockTable.setPreferredSize(new Dimension(600, 300));
		stockTable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		
		JScrollPane sp = new JScrollPane(stockTable);
		sp.setPreferredSize(new Dimension(600, 300));
		this.add(sp);
		this.repaint();
		this.revalidate();
		
	}
	
	private void loadAllDataInTable() {
		//emptyTableData();
		//DefaultTableModel d = (DefaultTableModel) stockTable.getModel();
		DefaultTableModel d = new DefaultTableModel();
		d.addColumn(tableHead[0]);
		d.addColumn(tableHead[1]);
		d.addColumn(tableHead[2]);
		d.addColumn(tableHead[3]);
		d.addColumn(tableHead[4]);
		int i = 0;
		for(String[] s: this.s.stonkGetAllData() ) {
			//(DefaultTableModel)stockTable.getModel();
			d.insertRow(i, s);
			i ++;
			//System.out.println(s[0] +", "+ s[1]+", "+ s[2]+", "+ s[3]);
		}
		//System.out.println(d.);
		stockTable.setModel(d);
		stockTable.revalidate();
		stockTable.repaint();
	}
	
	private void loadAllSearchedDataInTable() {
		String s = searchTextField.getText();
		DefaultTableModel d = new DefaultTableModel();
		d.addColumn(tableHead[0]);
		d.addColumn(tableHead[1]);
		d.addColumn(tableHead[2]);
		d.addColumn(tableHead[3]);
		d.addColumn(tableHead[4]);
		int i = 0;
		for(String[] s1: this.s.stonkSearchData(s) ) {
			d.insertRow(i, s1);
			i ++;
		}
		stockTable.setModel(d);
		stockTable.setBackground(Color.DARK_GRAY);
		stockTable.revalidate();
		stockTable.repaint();
	}
	
	private void removeDataFromID() {
		String removeString = removeTextField.getText();
		this.s.removeDataWithID(removeString);
	}
	
	private void addStockData() {
		String manufacturer = addStockManufacturerTextField.getText();
		String model = addStockModelTextField.getText();
		String quantity = addStockQuantityTextField.getText();
		String price = addStockPriceTextField.getText();
    	int quantityInt = Integer.parseInt(quantity);
    	int priceInt = Integer.parseInt(price);
		this.s.addStonksData(manufacturer, model, quantityInt, priceInt);
	}
	
	private void revalidare() {
		this.revalidate();
		this.repaint();
	}
		
	private void emptyTableData() {
		DefaultTableModel d = new DefaultTableModel();
		stockTable.setModel(d);
	}
	
	private void close() {
		Container c = super.getParent();
		c.remove(MainForm.p);
		MainForm.p = new LoginPanel(s);
		c.add(MainForm.p);
		MainForm.p.revalidate();
	}
}
