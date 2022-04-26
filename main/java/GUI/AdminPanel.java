package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout ;
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
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Service.Service;

public class AdminPanel extends JPanel{
	Service s;
	
	String[] tableHead = {"ID", "Manufacturer", "Model", "Quantity", "Price"};
	String[] tableHeadUsers = {"ID", "Username", "Password", "Admin Privileges"};
	
	JTable stockTable;
	TableColumn tableColumnManufacturer;
	TableColumn tableColumnModel;
	TableColumn tableColumnQuantity;
	TableColumn tableColumnPrice;

	JTable userTable;
	TableColumn tableColumnUser;
	TableColumn tableColumnPass;
	TableColumn tableColumnAdmPriv;
	
	JTextField searchTextField;
	JTextField removeTextField;

	JTextField addStockManufacturerTextField;
	JTextField addStockModelTextField;
	JTextField addStockQuantityTextField;
	JTextField addStockPriceTextField;
	
    JTextField modifyStockIDTextField;
    JTextField modifyStockManufacturerTextField;
	JTextField modifyStockModelTextField;
	JTextField modifyStockQuantityTextField;
	JTextField modifyStockPriceTextField;
	
    JLabel searchLabel;
	JLabel removeLabel;
	
    JLabel addStockManufacturerLabel;
	JLabel addStockModelLabel;
	JLabel addStockQuantityLabel;
	JLabel addStockPriceLabel;
	
    JLabel modifyStockIDLabel;
    JLabel modifyStockManufacturerLabel;
	JLabel modifyStockModelLabel;
	JLabel modifyStockQuantityLabel;
	JLabel modifyStockPriceLabel;
	
	
	JButton backButton;
	JButton exitButton;
	JButton stockAddButton;
    JButton stockModifyButton;
	JButton repairAIIndex; //repair auto increment index
	
	JMenuBar menuBar;
	JMenu stockMenu;
	JMenu userMenu;
	JMenu optionMenu;
	JMenuItem stockAdd, stockModify, stockSearch, stockDelete;
	JMenuItem userModify, userSearch, userDelete;
	JMenuItem optionBack, optionExit;

	GroupLayout layout;
	
	public AdminPanel(Service s) {
		super();
		this.s = s;
		super.setLayout(null);
		super.setVisible(true);
		super.setBackground(Color.DARK_GRAY);
		initJMenu();
		
		revalidare();
		
	}
	
	private void loadStockSearchPanel() {
		initSearchTextField();
		initStockTable();
		loadAllDataInStockTable();
		repairIDs();

		revalidare();
	}
	
	private void loadStockAddPanel() {
		initStockAddTextField();
		initStockAddButton();
		initStockTable();
		loadAllDataInStockTable();

		revalidare();
	}
	
	private void loadStockModifyPanel() {
		initSearchTextField();
		initStockTable();
		loadAllDataInStockTable();
		initModifyTextField();
		repairIDs();

		revalidare();
	}
	
	private void loadStockDeletePanel() {
		initRemoveTextField();
		initStockTable();
		loadAllDataInStockTable();
		
		revalidare();
	}

	private void loadUserModifyPanel() {
		
		initUserTable();
		loadAllDataInUserTable();
	}

	private void loadUserSearchPanel() {
		
		initUserTable();
		loadAllDataInUserTable();
	}

	private void loadUserDeletePanel() {
		
		initUserTable();
		loadAllDataInUserTable();
	}
	
	private void removePanel() {
		this.removeAll();
		initJMenu();
	}

	private void repairIDs() {
		repairAIIndex = new JButton("Repair ids numbers");
		repairAIIndex.setBackground(Color.DARK_GRAY);
		repairAIIndex.setForeground(Color.white);
		repairAIIndex.setSize(new DimensionUIResource(100, 20));

		repairAIIndex.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				repairIDIndex("stonks");
			}
		});

		this.add(repairAIIndex);
	}
	
	private void initJMenu() {
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.DARK_GRAY);
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

		optionMenu = new JMenu("Options");
		
		optionMenu.setForeground(Color.white);
		optionMenu.setBackground(Color.black);

		optionBack = new JMenuItem("Back");
		optionExit = new JMenuItem("Exit");

		optionMenu.add(optionBack);
		optionMenu.add(optionExit);

		menuBar.add(optionMenu);


		menuBar.setBounds(0, 0, 900, 30);
		this.add(menuBar);	
		
		optionBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});

		optionExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

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

		userModify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel();
				loadUserModifyPanel();
			}
		});

		userDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel();
				loadUserDeletePanel();
			}
		});

		userSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel();
				loadUserSearchPanel();
			}
		});
	}
	
	private void initModifyTextField() {
		modifyStockIDLabel = new JLabel("Id to modify");
		modifyStockIDLabel.setForeground(Color.white);
        modifyStockIDTextField = new JTextField();
        modifyStockIDTextField.setForeground(Color.white);
        modifyStockIDTextField.setBackground(Color.DARK_GRAY);

        modifyStockManufacturerLabel = new JLabel("Manuf to modify");
		modifyStockManufacturerLabel.setForeground(Color.white);
        modifyStockManufacturerTextField = new JTextField();
        modifyStockManufacturerTextField.setForeground(Color.white);
        modifyStockManufacturerTextField.setBackground(Color.DARK_GRAY);

        modifyStockModelLabel = new JLabel("Model to modify");
		modifyStockModelLabel.setForeground(Color.white);
        modifyStockModelTextField = new JTextField();
        modifyStockModelTextField.setForeground(Color.white);
        modifyStockModelTextField.setBackground(Color.DARK_GRAY);

        modifyStockQuantityLabel = new JLabel("Quantity to modify");
		modifyStockQuantityLabel.setForeground(Color.white);
        modifyStockQuantityTextField = new JTextField();
        modifyStockQuantityTextField.setForeground(Color.white);
        modifyStockQuantityTextField.setBackground(Color.DARK_GRAY);

        modifyStockPriceLabel = new JLabel("Price to modify");
		modifyStockPriceLabel.setForeground(Color.white);
        modifyStockPriceTextField = new JTextField();
        modifyStockPriceTextField.setForeground(Color.white);
        modifyStockPriceTextField.setBackground(Color.DARK_GRAY);

        stockModifyButton = new JButton("Dew it");
        stockModifyButton.setVisible(true);
        stockModifyButton.setBackground(Color.DARK_GRAY);
        stockModifyButton.setForeground(Color.white);

        stockModifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyStockData();
				repairIDIndex("stonks");
            }
        });

		modifyStockIDLabel.setBounds(20, 90, 100, 20);
		modifyStockManufacturerLabel.setBounds(20,120, 100, 20);
		modifyStockModelLabel.setBounds(20, 150, 100, 20);
		modifyStockQuantityLabel.setBounds(20, 180, 100, 20);
		modifyStockPriceLabel.setBounds(20, 210, 100, 20);

		modifyStockIDTextField.setBounds(150, 90, 100, 20);
		modifyStockManufacturerTextField.setBounds(150,120, 100, 20);
		modifyStockModelTextField.setBounds(150, 150, 100, 20);
		modifyStockQuantityTextField.setBounds(150, 180, 100, 20);
		modifyStockPriceTextField.setBounds(150, 210, 100, 20);
		stockModifyButton.setBounds(150, 240, 100, 20);

        this.add(modifyStockIDLabel);
        this.add(modifyStockIDTextField);
        this.add(modifyStockManufacturerLabel);
        this.add(modifyStockManufacturerTextField);
        this.add(modifyStockModelLabel);
        this.add(modifyStockModelTextField);
        this.add(modifyStockQuantityLabel);
        this.add(modifyStockQuantityTextField);
        this.add(modifyStockPriceLabel);
        this.add(modifyStockPriceTextField);

        this.add(stockModifyButton);
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
				String stonks = "stonks";
				removeDataFromID();
				repairIDIndex(stonks);
				JOptionPane.showMessageDialog(null, "Produsul cu id-ul "+id+" a fost sters cu succes!");
				revalidare();
			}
		});
		//removeTextField.setPreferredSize(new Dimension(100, 20));
		removeLabel.setBounds(20, 60, 100, 20);
		removeTextField.setBounds(150, 60, 100, 20);
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
		//searchTextField.setPreferredSize(new Dimension(100, 20));
		searchLabel.setBounds(20, 60, 100, 20);
		searchTextField.setBounds(150, 60, 100, 20);
		this.add(searchLabel, BorderLayout.PAGE_START);
		this.add(searchTextField, BorderLayout.PAGE_START);
	}
	
	private void initStockAddTextField() {
		addStockManufacturerTextField = new JTextField();
		addStockManufacturerTextField.setForeground(Color.white);
		addStockManufacturerTextField.setBackground(Color.DARK_GRAY);

		addStockModelTextField = new JTextField();
		addStockModelTextField.setForeground(Color.white);
		addStockModelTextField.setBackground(Color.DARK_GRAY);
		
		addStockQuantityTextField = new JTextField();
		addStockQuantityTextField.setForeground(Color.white);
		addStockQuantityTextField.setBackground(Color.DARK_GRAY);
		
		addStockPriceTextField = new JTextField();
		addStockPriceTextField.setForeground(Color.white);
		addStockPriceTextField.setBackground(Color.DARK_GRAY);
		
		addStockManufacturerLabel = new JLabel("Producator");
		addStockManufacturerLabel.setForeground(Color.white);
		
		addStockModelLabel = new JLabel("Model");
		addStockModelLabel.setForeground(Color.white);
		
		addStockQuantityLabel = new JLabel("Cantitate");
		addStockQuantityLabel.setForeground(Color.white);
		
		addStockPriceLabel = new JLabel("Pret");
		addStockPriceLabel.setForeground(Color.white);

		addStockManufacturerLabel.setBounds(20, 60, 100, 20);
		addStockModelLabel.setBounds(20, 90, 100, 20);
		addStockQuantityLabel.setBounds(20, 120, 100, 20);
		addStockPriceLabel.setBounds(20, 150, 100, 20);

		addStockManufacturerTextField.setBounds(150, 60, 100, 20);
		addStockModelTextField.setBounds(150, 90, 100, 20);
		addStockQuantityTextField.setBounds(150, 120, 100, 20);
		addStockPriceTextField.setBounds(150, 150, 100, 20);

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
		stockAddButton.setBounds(150, 180, 100, 20);
		stockAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStockData();
				JOptionPane.showMessageDialog(null, "Produsul a fost adaugat in baza de date!");
			}
		});
		this.add(stockAddButton);
	}

	private void initUserTable() {
		userTable = new JTable();
		userTable.setForeground(Color.white);
		userTable.setBackground(Color.DARK_GRAY);
		tableColumnUser = new TableColumn();
		tableColumnPass = new TableColumn();
		tableColumnAdmPriv = new TableColumn();

		tableColumnUser.setHeaderValue("User");
		userTable.addColumn(tableColumnUser);
		tableColumnPass.setHeaderValue("Pass");
		userTable.addColumn(tableColumnPass);
		tableColumnAdmPriv.setHeaderValue("Admin Privileges");
		userTable.addColumn(tableColumnAdmPriv);
		
		//stockTable.setPreferredSize(new Dimension(600, 480));
		userTable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		
		JScrollPane sp = new JScrollPane(userTable);
		//sp.setPreferredSize(new Dimension(600, 300));
		sp.setBounds(280, 60, 580, 480);
		this.add(sp);
		this.repaint();
		this.revalidate();
	}

	private void loadAllDataInUserTable() {
		//emptyTableData();
		//DefaultTableModel d = (DefaultTableModel) stockTable.getModel();
		DefaultTableModel d = new DefaultTableModel();
		d.addColumn(tableHeadUsers[0]);
		d.addColumn(tableHeadUsers[1]);
		d.addColumn(tableHeadUsers[2]);
		d.addColumn(tableHeadUsers[3]);
		int i = 0;
		for(String[] s: this.s.userGetAllData() ) {
			d.insertRow(i, s);
			i ++;
		}
		userTable.setModel(d);
		userTable.revalidate();
		userTable.repaint();
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
		
		//stockTable.setPreferredSize(new Dimension(600, 480));
		stockTable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		
		JScrollPane sp = new JScrollPane(stockTable);
		//sp.setPreferredSize(new Dimension(600, 300));
		sp.setBounds(280, 60, 580, 480);
		this.add(sp);
		this.repaint();
		this.revalidate();
		
	}
	
	private void loadAllDataInStockTable() {
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

	private void repairIDIndex(String table) {
		this.s.repairTableID(table);
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

	private void modifyStockData() {
		String modifyStringID = modifyStockIDTextField.getText();
		int modifyID = Integer.parseInt(modifyStringID);	
		String modifyMan = modifyStockManufacturerTextField.getText();
		String modifyMod = modifyStockModelTextField.getText();
		String modifyQnt = modifyStockQuantityTextField.getText();
		String modifyPrice = modifyStockPriceTextField.getText();

		this.s.tryModifyStonks(modifyID, modifyMan, modifyMod, modifyQnt, modifyPrice);
	}
	
	private void revalidare() {
		this.revalidate();
		this.repaint();
	}
		
	//private void emptyTableData() {
		//DefaultTableModel d = new DefaultTableModel();
		//stockTable.setModel(d);
	//}
	
	private void close() {
		Container c = super.getParent();
		c.remove(MainForm.p);
		MainForm.p = new LoginPanel(s);
		c.add(MainForm.p);
		MainForm.p.revalidate();
	}
}
