package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Service.Service;

public class AdminPanel extends JPanel{
	Service s;
	
	String[] tableHead = {"ID", "Manufacturer", "Model", "Quantity", "Price"};
	String[] tableHeadUsers = {"ID", "Username", "Password", "Admin Privileges"};
	String[] tableHeadSales = {"ID", "ID Produs", "User", "Adresa", "Data Comandare", "Data Livrare", "Status Comanda", "Cantitate", "Pret"};

	JTable stockTable;
	TableColumn tableColumnManufacturer;
	TableColumn tableColumnModel;
	TableColumn tableColumnQuantity;
	TableColumn tableColumnPrice;

	JTable userTable;
	TableColumn tableColumnUser;
	TableColumn tableColumnPass;
	TableColumn tableColumnAdmPriv;

	JTable salesTable;
	TableColumn tableColumnID;
	TableColumn tableColumnIdPrd;
	TableColumn tableColumnCustommer;
	TableColumn tableColumnAdresa;
	TableColumn tableColumnDataC;
	TableColumn tableColumnDataL;
	TableColumn tableColumnStatus;
	TableColumn tableColumnCant;
	TableColumn tableColumnPret;
	
	JTextField searchTextField;
	JTextField searchUserTextField;
	JTextField searchSalesTextField;
	JTextField removeTextField;
	JTextField removeUserTextField;

	JTextField addStockManufacturerTextField;
	JTextField addStockModelTextField;
	JTextField addStockQuantityTextField;
	JTextField addStockPriceTextField;
	
    JTextField modifyStockIDTextField;
    JTextField modifyStockManufacturerTextField;
	JTextField modifyStockModelTextField;
	JTextField modifyStockQuantityTextField;
	JTextField modifyStockPriceTextField;

	JTextField modifySalesIDTextField;
	JTextField modifySalesIdPrdTextField;
	JTextField modifySalesUserTextField;
	JTextField modifySalesAdresaTextField;
	JTextField modifySalesDataCTextField;
	JTextField modifySalesDataLTextField;
	JTextField modifySalesStatusTextField;
	JTextField modifySalesCantitateTextField;
	JTextField modifySalesPretTextField;

	JTextField deleteSalesTextField;

	JTextField modifyUserIDTextField;
	JTextField modifyUserUsernameTextField;
	JTextField modifyUserPasswordTextField;
	JTextField modifyUserAdmPrvTextField;
	
    JLabel searchLabel;
	JLabel searchUserLabel;
	JLabel searchSalesLabel;
	JLabel removeLabel;
	JLabel removeUserLabel;
	
    JLabel addStockManufacturerLabel;
	JLabel addStockModelLabel;
	JLabel addStockQuantityLabel;
	JLabel addStockPriceLabel;
	
    JLabel modifyStockIDLabel;
    JLabel modifyStockManufacturerLabel;
	JLabel modifyStockModelLabel;
	JLabel modifyStockQuantityLabel;
	JLabel modifyStockPriceLabel;
	
	JLabel modifyUserIDLabel;
	JLabel modifyUserUsernameLabel;
	JLabel modifyUserPasswordLabel;
	JLabel modifyUserAdmPrvLabel;

	JLabel modifySalesIDLabel;
	JLabel modifySalesIdPrdLabel;
	JLabel modifySalesUserLabel;
	JLabel modifySalesAdresaLabel;
	JLabel modifySalesDataCLabel;
	JLabel modifySalesDataLLabel;
	JLabel modifySalesStatusLabel;
	JLabel modifySalesCantitateLabel;
	JLabel modifySalesPretLabel;
	JLabel deleteSalesLabel;

	JButton backButton;
	JButton exitButton;
	JButton stockAddButton;
    JButton stockModifyButton;
	JButton userModifyButton;
	JButton repairAIIndex; //repair auto increment index
	JButton salesAddButton;
	JButton salesModifyButton;
	JButton salesDeleteButton;
	
	JMenuBar menuBar;
	JMenu stockMenu;
	JMenu userMenu;
	JMenu optionMenu;
	JMenu orderMenu;
	JMenuItem stockAdd, stockModify, stockSearch, stockDelete;
	JMenuItem userModify, userSearch, userDelete;
	JMenuItem optionBack, optionExit;
	JMenuItem orderAdd, orderModify, orderSearch, orderDelete;

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

		revalidare();
	}
	
	private void loadStockDeletePanel() {
		initSearchTextField();
		initRemoveTextField();
		initStockTable();
		loadAllDataInStockTable();
		
		revalidare();
	}

	private void loadUserModifyPanel() {
		initModifyUserTextField();
		initUserSearchTextField();
		initUserTable();
		loadAllDataInUserTable();

	}

	private void loadUserSearchPanel() {
		initUserSearchTextField();
		initUserTable();
		loadAllDataInUserTable();
	}

	private void loadUserDeletePanel() {
		initRemoveUserTextField();
		initUserSearchTextField();
		initUserTable();
		loadAllDataInUserTable();
	}

	private void loadOrderAddPanel() {
		initSalesSearchTextField();
		initModifySalesTextField("add");
		initSalesAddButton();
		initSalesTable();
		loadAllSalesDataInSalesTable();
	}

	private void loadOrderModifyPanel() {
		initSalesSearchTextField();
		initModifySalesTextField("modify");
		initSalesModifyButton();
		initSalesTable();
		loadAllSalesDataInSalesTable();
	}

	private void loadOrderSearchPanel() {
		initSalesSearchTextField();
		initSalesTable();
		loadAllSalesDataInSalesTable();
	}

	private void loadOrderDeletePanel() {
		initSalesSearchTextField();
		initSalesTable();
		initSalesDeleteButtonAndTextField();
		loadAllSalesDataInSalesTable();
	}
	
	private void removePanel() {
		this.removeAll();
		initJMenu();
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

		orderMenu = new JMenu("Comenzi");
		orderMenu.setForeground(Color.white);
		orderMenu.setBackground(Color.black);

		orderAdd = new JMenuItem("Adaugare Comenzia");
		orderModify = new JMenuItem("Modificare Comenzi");
		orderSearch = new JMenuItem("Cautare Comenzi");
		orderDelete = new JMenuItem("Stergere Comenzi");

		orderMenu.add(orderAdd);
		orderMenu.add(orderModify);
		orderMenu.add(orderSearch);
		orderMenu.add(orderDelete);

		menuBar.add(orderMenu);

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

		orderAdd.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel();
				loadOrderAddPanel();
			}
		});

		orderSearch.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel();
				loadOrderSearchPanel();
			}
		});

		orderModify.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel();
				loadOrderModifyPanel();
			}
		});

		orderDelete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel();
				loadOrderDeletePanel();
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

	private void initModifySalesTextField(String tag) {
		if(tag.equals("modify")) {
			modifySalesIDLabel = new JLabel("ID");
			modifySalesUserLabel = new JLabel("Username");
			modifySalesDataCLabel = new JLabel("DataC");
			modifySalesDataLLabel = new JLabel("DataL");
			modifySalesStatusLabel = new JLabel("Status");
			modifySalesPretLabel = new JLabel("Pret");

			modifySalesIDTextField = new JTextField();
			modifySalesUserTextField = new JTextField();
			modifySalesDataCTextField = new JTextField();
			modifySalesDataLTextField = new JTextField();
			modifySalesStatusTextField = new JTextField("1");
			modifySalesPretTextField = new JTextField();

			modifySalesIDLabel.setForeground(Color.white);
			modifySalesUserLabel.setForeground(Color.white);
			modifySalesDataCLabel.setForeground(Color.white);
			modifySalesDataLLabel.setForeground(Color.white);
			modifySalesStatusLabel.setForeground(Color.white);
			modifySalesPretLabel.setForeground(Color.white);

			modifySalesIDTextField.setForeground(Color.white);
			modifySalesUserTextField.setForeground(Color.white);
			modifySalesDataCTextField.setForeground(Color.white);
			modifySalesDataLTextField.setForeground(Color.white);
			modifySalesStatusTextField.setForeground(Color.white);
			modifySalesPretTextField.setForeground(Color.white);

			modifySalesIDTextField.setBackground(Color.DARK_GRAY);
			modifySalesUserTextField.setBackground(Color.DARK_GRAY);
			modifySalesDataCTextField.setBackground(Color.DARK_GRAY);
			modifySalesDataLTextField.setBackground(Color.DARK_GRAY);
			modifySalesStatusTextField.setBackground(Color.DARK_GRAY);
			modifySalesPretTextField.setBackground(Color.DARK_GRAY);
	
		}
		modifySalesIdPrdLabel = new JLabel("ID Produs");
		modifySalesAdresaLabel = new JLabel("Adresa");
		modifySalesCantitateLabel = new JLabel("Cantitate");
		
		modifySalesIdPrdLabel.setForeground(Color.white);
		modifySalesAdresaLabel.setForeground(Color.white);
		modifySalesCantitateLabel.setForeground(Color.white);

		modifySalesIdPrdTextField = new JTextField();
		modifySalesAdresaTextField = new JTextField();
		modifySalesCantitateTextField = new JTextField();

		
		modifySalesIdPrdTextField.setForeground(Color.white);
		modifySalesAdresaTextField.setForeground(Color.white);
		modifySalesCantitateTextField.setForeground(Color.white);

		modifySalesIdPrdTextField.setBackground(Color.DARK_GRAY);
		modifySalesAdresaTextField.setBackground(Color.DARK_GRAY);
		modifySalesCantitateTextField.setBackground(Color.DARK_GRAY);
		if(tag.equals("modify")) {
			modifySalesIDLabel.setBounds(20, 90, 100, 20);
			modifySalesIdPrdLabel.setBounds(20, 120, 100, 20);
			modifySalesUserLabel.setBounds(20, 150, 100, 20);
			modifySalesAdresaLabel.setBounds(20, 180, 100, 20);
			modifySalesDataCLabel.setBounds(20, 210, 100, 20);
			modifySalesDataLLabel.setBounds(20, 240, 100, 20);
			modifySalesStatusLabel.setBounds(20, 270, 100, 20);
			modifySalesCantitateLabel.setBounds(20, 300, 100, 20);
			modifySalesPretLabel.setBounds(20, 330, 100, 20);

			modifySalesIDTextField.setBounds(150, 90, 100, 20);
			modifySalesIdPrdTextField.setBounds(150, 120, 100, 20);
			modifySalesUserTextField.setBounds(150, 150, 100, 20);
			modifySalesAdresaTextField.setBounds(150, 180, 100, 20);
			modifySalesDataCTextField.setBounds(150, 210, 100, 20);
			modifySalesDataLTextField.setBounds(150, 240, 100, 20);
			modifySalesStatusTextField.setBounds(150, 270, 100, 20);
			modifySalesCantitateTextField.setBounds(150, 300, 100, 20);
			modifySalesPretTextField.setBounds(150, 330, 100, 20);
		
			this.add(modifySalesIDLabel);
			this.add(modifySalesIdPrdLabel);
			this.add(modifySalesUserLabel);
			this.add(modifySalesAdresaLabel);
			this.add(modifySalesDataCLabel);
			this.add(modifySalesDataLLabel);
			this.add(modifySalesStatusLabel);
			this.add(modifySalesCantitateLabel);
			this.add(modifySalesPretLabel);
			
			this.add(modifySalesIDTextField);
			this.add(modifySalesIdPrdTextField);
			this.add(modifySalesUserTextField);
			this.add(modifySalesAdresaTextField);
			this.add(modifySalesDataCTextField);
			this.add(modifySalesDataLTextField);
			this.add(modifySalesStatusTextField);
			this.add(modifySalesCantitateTextField);
			this.add(modifySalesPretTextField);
		} else {
			modifySalesIdPrdLabel.setBounds(20, 90, 100, 20);
			modifySalesAdresaLabel.setBounds(20, 120, 100, 20);
			modifySalesCantitateLabel.setBounds(20, 150, 100, 20);

			modifySalesIdPrdTextField.setBounds(150, 90, 100, 20);
			modifySalesAdresaTextField.setBounds(150, 120, 100, 20);
			modifySalesCantitateTextField.setBounds(150, 150, 100, 20);
			
		
			this.add(modifySalesIdPrdLabel);
			this.add(modifySalesAdresaLabel);
			this.add(modifySalesCantitateLabel);
			
			this.add(modifySalesIdPrdTextField);
			this.add(modifySalesAdresaTextField);
			this.add(modifySalesCantitateTextField);
		}
		
	
	}
	
	private void initModifyUserTextField() {
		modifyUserIDLabel = new JLabel("Id to modify");
		modifyUserIDLabel.setForeground(Color.white);
		modifyUserIDTextField = new JTextField();
		modifyUserIDTextField.setForeground(Color.white);
		modifyUserIDTextField.setBackground(Color.DARK_GRAY);

		modifyUserUsernameLabel = new JLabel("Username");
		modifyUserUsernameLabel.setForeground(Color.white);
		modifyUserUsernameTextField = new JTextField();
		modifyUserUsernameTextField.setForeground(Color.white);
		modifyUserUsernameTextField.setBackground(Color.DARK_GRAY);

		modifyUserPasswordLabel = new JLabel("Password");
		modifyUserPasswordLabel.setForeground(Color.white);
		modifyUserPasswordTextField = new JTextField();
		modifyUserPasswordTextField.setForeground(Color.white);
		modifyUserPasswordTextField.setBackground(Color.DARK_GRAY);

		modifyUserAdmPrvLabel = new JLabel("Adm Priv");
		modifyUserAdmPrvLabel.setForeground(Color.white);
		modifyUserAdmPrvTextField = new JTextField();
		modifyUserAdmPrvTextField.setForeground(Color.white);
		modifyUserAdmPrvTextField.setBackground(Color.DARK_GRAY);

		userModifyButton = new JButton("Apply");
        userModifyButton.setVisible(true);
        userModifyButton.setBackground(Color.DARK_GRAY);
        userModifyButton.setForeground(Color.white);

		userModifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyUserData();
				revalidareUserTable();
            }
        });

		modifyUserIDLabel.setBounds(20, 90, 100, 20);
		modifyUserUsernameLabel.setBounds(20, 120, 100, 20);
		modifyUserPasswordLabel.setBounds(20, 150, 100, 20);
		modifyUserAdmPrvLabel.setBounds(20, 180, 100, 20);

		modifyUserIDTextField.setBounds(150, 90, 100, 20);
		modifyUserUsernameTextField.setBounds(150, 120, 100, 20);
		modifyUserPasswordTextField.setBounds(150, 150, 100, 20);
		modifyUserAdmPrvTextField.setBounds(150, 180, 100, 20);
		userModifyButton.setBounds(150, 210, 100, 20);

		this.add(modifyUserIDLabel);
		this.add(modifyUserUsernameLabel);
		this.add(modifyUserPasswordLabel);
		this.add(modifyUserAdmPrvLabel);

		this.add(modifyUserIDTextField);
		this.add(modifyUserUsernameTextField);
		this.add(modifyUserPasswordTextField);
		this.add(modifyUserAdmPrvTextField);
		this.add(userModifyButton);
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

        stockModifyButton = new JButton("Apply");
        stockModifyButton.setVisible(true);
        stockModifyButton.setBackground(Color.DARK_GRAY);
        stockModifyButton.setForeground(Color.white);

        stockModifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyStockData();
				revalidareTabel();
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
				removeDataFromID();
				revalidareTabel();
				JOptionPane.showMessageDialog(null, "Produsul cu id-ul "+id+" a fost sters cu succes!");
			}
		});
		//removeTextField.setPreferredSize(new Dimension(100, 20));
		removeLabel.setBounds(20, 90, 100, 20);
		removeTextField.setBounds(150, 90, 100, 20);
		this.add(removeLabel);
		this.add(removeTextField);
	}
	
	private void initRemoveUserTextField() {
		removeUserLabel = new JLabel("Id to remove");
		removeUserTextField = new JTextField();
		removeUserLabel.setForeground(Color.white);
		removeUserTextField.setForeground(Color.white);
		removeUserTextField.setBackground(Color.DARK_GRAY);
		String id = removeUserTextField.getText();
		removeUserTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeUserDataFromID();
				revalidareUserTable();
				JOptionPane.showMessageDialog(null, "Utilizatorul cu id-ul "+id+" a fost sters cu succes!");
			}
		});
		//removeTextField.setPreferredSize(new Dimension(100, 20));
		removeUserLabel.setBounds(20, 90, 100, 20);
		removeUserTextField.setBounds(150, 90, 100, 20);
		this.add(removeUserLabel);
		this.add(removeUserTextField);
	}
	
	private void initUserSearchTextField() {
		searchUserLabel = new JLabel("Search");
		searchUserTextField = new JTextField();
		searchUserLabel.setForeground(Color.white);
		searchUserTextField.setForeground(Color.white);
		searchUserTextField.setBackground(Color.DARK_GRAY);
		searchUserTextField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadAllSearchedUsersInTable();
			}
			
		});
		searchUserLabel.setBounds(20, 60, 100, 20);
		searchUserTextField.setBounds(150, 60, 100, 20);
		this.add(searchUserLabel);
		this.add(searchUserTextField);
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
		searchLabel.setBounds(20, 60, 100, 20);
		searchTextField.setBounds(150, 60, 100, 20);
		this.add(searchLabel, BorderLayout.PAGE_START);
		this.add(searchTextField, BorderLayout.PAGE_START);
	}

	private void initSalesSearchTextField() {
		searchSalesLabel = new JLabel("Search");
		searchSalesTextField = new JTextField();
		searchSalesLabel.setForeground(Color.white);
		searchSalesTextField.setForeground(Color.white);
		searchSalesTextField.setBackground(Color.DARK_GRAY);
		searchSalesTextField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadAllSearchedDataInSalesTable();
			}
			
		});
		searchSalesLabel.setBounds(20, 60, 100, 20);
		searchSalesTextField.setBounds(150, 60, 100, 20);
		this.add(searchSalesLabel, BorderLayout.PAGE_START);
		this.add(searchSalesTextField, BorderLayout.PAGE_START);
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
				revalidareTabel();
				JOptionPane.showMessageDialog(null, "Produsul a fost adaugat in baza de date!");
			}
		});
		this.add(stockAddButton);
	}

	private void initSalesAddButton() {
		salesAddButton = new JButton("Comanda");
		salesAddButton.setForeground(Color.white);
		salesAddButton.setBackground(Color.DARK_GRAY);
		salesAddButton.setBounds(150, 180, 100, 20);
		salesAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addSalesData();
				revalidareSalesTable();
				JOptionPane.showMessageDialog(null, "Comanda a fost plasata cu succes!");
			}
		});
		this.add(salesAddButton);
	}

	private void addSalesData() {
		String idPrd = modifySalesIdPrdTextField.getText();
		String usr = LoginPanel.whoIsLoggedIn;
		String adr = modifySalesAdresaTextField.getText();
		String dataC = getCurrentDate();
		String dataL = getDeliveryDate();
		int status = Integer.parseInt(modifySalesStatusTextField.getText());
		int cant = Integer.parseInt(modifySalesCantitateTextField.getText());
		int idPrdInt = Integer.parseInt(idPrd);
		int pret = getPret(idPrd);
		this.s.tryOrder(idPrdInt, usr, adr, dataC, dataL, status, cant, pret);
	}

	private int getPret(String id) {
		return this.s.getPrice(id);
	}

	String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();  
		return sdf.format(date);
	}

	private String getDeliveryDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5);
        return sdf.format(cal.getTime());
	}

	private void initSalesModifyButton() {
		salesModifyButton = new JButton("Modifica");
		salesModifyButton.setForeground(Color.white);
		salesModifyButton.setBackground(Color.DARK_GRAY);
		salesModifyButton.setBounds(150, 360, 100, 20);
		salesModifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//modifySalesData();
				revalidareSalesTable();
				JOptionPane.showMessageDialog(null, "Comanda a fost modificata cu succecs!");
			}
		});
		this.add(salesModifyButton);
	}

	private void initSalesDeleteButtonAndTextField() {
		deleteSalesLabel = new JLabel("ID de sters");
		deleteSalesLabel.setForeground(Color.white);

		deleteSalesTextField = new JTextField();
		deleteSalesTextField.setForeground(Color.white);
		deleteSalesTextField.setBackground(Color.DARK_GRAY);

		salesDeleteButton = new JButton("Stergere");
		salesDeleteButton.setForeground(Color.white);
		salesDeleteButton.setBackground(Color.DARK_GRAY);
		salesDeleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteSalesData();
				revalidareSalesTable();
				JOptionPane.showMessageDialog(null, "Comanda a fost stearsa cu succecs!");
			}
		});

		deleteSalesLabel.setBounds(20, 90, 100, 20);
		deleteSalesTextField.setBounds(150, 90, 100, 20);
		salesDeleteButton.setBounds(150, 120, 100, 20);
		this.add(deleteSalesLabel);
		this.add(deleteSalesTextField);
		this.add(salesDeleteButton);
	}

	private void deleteSalesData() {
		this.s.deleteSalesData(Integer.parseInt(deleteSalesTextField.getText()));
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

	private void initSalesTable() {
		salesTable = new JTable();
		salesTable.setForeground(Color.white);
		salesTable.setBackground(Color.DARK_GRAY);

		tableColumnID = new TableColumn();
		tableColumnIdPrd = new TableColumn();
		tableColumnCustommer = new TableColumn();
		tableColumnAdresa = new TableColumn();
		tableColumnDataC = new TableColumn();
		tableColumnDataL = new TableColumn();
		tableColumnStatus = new TableColumn();
		tableColumnCant = new TableColumn();
		tableColumnPret = new TableColumn();

		tableColumnID.setHeaderValue("ID");
		tableColumnIdPrd.setHeaderValue("ID-Prd");
		tableColumnCustommer.setHeaderValue("User");
		tableColumnAdresa.setHeaderValue("Adresa");
		tableColumnDataC.setHeaderValue("DataC");
		tableColumnDataL.setHeaderValue("DataL");
		tableColumnStatus.setHeaderValue("Status");
		tableColumnCant.setHeaderValue("Cantitate");
		tableColumnPret.setHeaderValue("Pret");

		salesTable.addColumn(tableColumnID);
		salesTable.addColumn(tableColumnIdPrd);
		salesTable.addColumn(tableColumnCustommer);
		salesTable.addColumn(tableColumnAdresa);
		salesTable.addColumn(tableColumnDataC);
		salesTable.addColumn(tableColumnDataL);
		salesTable.addColumn(tableColumnStatus);
		salesTable.addColumn(tableColumnCant);
		salesTable.addColumn(tableColumnPret);

		salesTable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		JScrollPane sp = new JScrollPane(salesTable);
		sp.setBounds(280, 60, 580, 480);
		this.add(sp);
		this.revalidate();
		this.repaint();
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
	
	private void loadAllSearchedUsersInTable() {
		String s = searchUserTextField.getText();
		DefaultTableModel d = new DefaultTableModel();
		d.addColumn(tableHeadUsers[0]);
		d.addColumn(tableHeadUsers[1]);
		d.addColumn(tableHeadUsers[2]);
		d.addColumn(tableHeadUsers[3]);
		int i = 0;
		for(String[] s1: this.s.userSearchData(s) ) {
			d.insertRow(i, s1);
			i ++;
		}
		userTable.setModel(d);
		userTable.setBackground(Color.DARK_GRAY);
		userTable.revalidate();
		userTable.repaint();
	}

	private void loadAllSalesDataInSalesTable() {
		DefaultTableModel d = new DefaultTableModel();
		d.addColumn(tableHeadSales[0]);
		d.addColumn(tableHeadSales[1]);
		d.addColumn(tableHeadSales[2]);
		d.addColumn(tableHeadSales[3]);
		d.addColumn(tableHeadSales[4]);
		d.addColumn(tableHeadSales[5]);
		d.addColumn(tableHeadSales[6]);
		d.addColumn(tableHeadSales[7]);
		d.addColumn(tableHeadSales[8]);
		int i = 0;
		for(String[] s: this.s.salesSearchAllData() ) {
			d.insertRow(i, s);
			i ++;
		}
		salesTable.setModel(d);
		salesTable.setBackground(Color.DARK_GRAY);
		salesTable.revalidate();
		salesTable.repaint();
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

	private void loadAllSearchedDataInSalesTable() {
		String s = searchSalesTextField.getText();
		DefaultTableModel d = new DefaultTableModel();
		d.addColumn(tableHeadSales[0]);
		d.addColumn(tableHeadSales[1]);
		d.addColumn(tableHeadSales[2]);
		d.addColumn(tableHeadSales[3]);
		d.addColumn(tableHeadSales[4]);
		d.addColumn(tableHeadSales[5]);
		d.addColumn(tableHeadSales[6]);
		d.addColumn(tableHeadSales[7]);
		d.addColumn(tableHeadSales[8]);
		int i = 0;
		for(String[] s1: this.s.salesSearchData(s) ) {
			d.insertRow(i, s1);
			i ++;
		}
		salesTable.setModel(d);
		salesTable.setBackground(Color.DARK_GRAY);
		salesTable.revalidate();
		salesTable.repaint();
	}
	
	private void removeDataFromID() {
		String removeString = removeTextField.getText();
		this.s.removeDataWithID(removeString);
	}

	private void removeUserDataFromID() {
		String removeString = removeUserTextField.getText();
		this.s.removeUserDataWithID(removeString);
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

	private void modifyUserData() {
		String modifyStringID = modifyUserIDTextField.getText();
		int modifyID = Integer.parseInt(modifyStringID);
		String modifyUsername = modifyUserUsernameTextField.getText();
		String modifyPassword = modifyUserPasswordTextField.getText();
		String modifyAdmPriv = modifyUserAdmPrvTextField.getText();
		System.out.println("this is the adm priv text*" + modifyAdmPriv + "*");

		this.s.tryModifyUsers(modifyID, modifyUsername, modifyPassword, modifyAdmPriv);
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

	private void revalidareTabel() {
		emptyTableData();
		loadAllDataInStockTable();
		this.revalidate();
		this.repaint();
	}
		
	private void emptyTableData() {
		DefaultTableModel d = new DefaultTableModel();
		stockTable.setModel(d);
	}

	private void revalidareUserTable() {
		emptyUserTableData();
		loadAllDataInUserTable();
		this.revalidate();
		this.repaint();
	}
		
	private void emptyUserTableData() {
		DefaultTableModel d = new DefaultTableModel();
		userTable.setModel(d);
	}

	private void revalidareSalesTable() {
		emptySalesTableData();
		loadAllSalesDataInSalesTable();
		this.revalidate();
		this.repaint();
	}
		
	private void emptySalesTableData() {
		DefaultTableModel d = new DefaultTableModel();
		salesTable.setModel(d);
	}
	
	private void close() {
		Container c = super.getParent();
		c.remove(MainForm.p);
		MainForm.p = new LoginPanel(s);
		c.add(MainForm.p);
		MainForm.p.revalidate();
	}
}
