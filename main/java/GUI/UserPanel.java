package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

public class UserPanel extends JPanel {
	Service s;
	
	String[] tableHead = {"ID", "Manufacturer", "Model", "Price"};
	
	JTable stockTable;
	TableColumn tableColumnID;
	TableColumn tableColumnManufacturer;
	TableColumn tableColumnModel;
	TableColumn tableColumnQuantity;
	TableColumn tableColumnPrice;
	
	JTextField searchTextField;
	JLabel searchLabel;

	JTextField idProdusTextField, adresaTextField, cantitateTextField;
	JLabel idProdusLabel, adresaLabel, cantitateLabel;

	JButton backButton;
	JButton exitButton;
	JButton comandaButton;

	JMenuBar menuBar;
	JMenu salesMenu, optionsMenu;
	JMenuItem cautare, comanda;
	JMenuItem back, exit;
	
	public UserPanel(Service s) {
		super();
		this.s = s;
		super.setLayout(null);
		super.setVisible(true);
		super.setBackground(Color.DARK_GRAY);
		initJMenu();
		revalidare();
		System.out.println(getCurrentDate());
		System.out.println(getDeliveryDate());
	}

	private void removePanel() {
		this.removeAll();
		initJMenu();
	}

	private void loadCautare() {
		initCautareTextField();
		initCautareMenu();
		revalidare();
	}

	private void loadComenzi() {
		initCautareTextField();
		initComandaTextField();
		initCautareMenu();
		revalidare();
	}

	private void initJMenu() {
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.DARK_GRAY);

		salesMenu = new JMenu("Comenzi");
		salesMenu.setForeground(Color.white);
		salesMenu.setBackground(Color.black);

		optionsMenu = new JMenu("Optiuni");
		optionsMenu.setForeground(Color.white);
		optionsMenu.setBackground(Color.black);

		cautare = new JMenuItem("Cautare Produse");
		comanda = new JMenuItem("Comanda Produse");
		back = new JMenuItem("Inapoi La Login");
		exit = new JMenuItem("Inchidere");

		salesMenu.add(cautare);
		salesMenu.add(comanda);
		optionsMenu.add(back);
		optionsMenu.add(exit);

		menuBar.add(salesMenu);
		menuBar.add(optionsMenu);

		menuBar.setBounds(0, 0, 900, 30);
		this.add(menuBar);

		cautare.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel();
				loadCautare();
			}
		});

		comanda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel();
				loadComenzi();
			}
		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
	}

	private void initCautareTextField() {
		searchLabel = new JLabel("Search");
		searchLabel.setForeground(Color.white);

		searchTextField = new JTextField();
		searchTextField.setForeground(Color.white);
		searchTextField.setBackground(Color.DARK_GRAY);

		searchLabel.setBounds(20, 60, 100, 20);
		searchTextField.setBounds(150, 60, 100, 20);

		searchTextField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadAllSearchedDataInTable();
			}
			
		});

		this.add(searchLabel);
		this.add(searchTextField);

	}

	private void initComandaTextField() {
		idProdusTextField = new JTextField();
		idProdusTextField.setForeground(Color.white);
		idProdusTextField.setBackground(Color.DARK_GRAY);
		adresaTextField = new JTextField();
		adresaTextField.setForeground(Color.white);
		adresaTextField.setBackground(Color.DARK_GRAY);
		cantitateTextField = new JTextField();
		cantitateTextField.setForeground(Color.white);
		cantitateTextField.setBackground(Color.DARK_GRAY);

		idProdusLabel = new JLabel("ID Produs");
		idProdusLabel.setForeground(Color.white);
		adresaLabel = new JLabel("Adresa");
		adresaLabel.setForeground(Color.white);
		cantitateLabel = new JLabel("Cantitate");
		cantitateLabel.setForeground(Color.white);

		comandaButton = new JButton("Comanda");
		comandaButton.setForeground(Color.white);
		comandaButton.setBackground(Color.DARK_GRAY);
		comandaButton.setFocusPainted(false);
		comandaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				plasareComanda();
				JOptionPane.showMessageDialog(null, "Comanda a fost plasata cu succes!");
			}
			
		});

		idProdusLabel.setBounds(20, 90, 100, 20);
		adresaLabel.setBounds(20, 120, 100, 20);
		cantitateLabel.setBounds(20, 150, 100, 20);

		idProdusTextField.setBounds(150, 90, 100, 20);
		adresaTextField.setBounds(150, 120, 100, 20);
		cantitateTextField.setBounds(150, 150, 100, 20);
		comandaButton.setBounds(150, 180, 100, 20);

		this.add(idProdusTextField);
		this.add(idProdusLabel);
		this.add(adresaTextField);
		this.add(adresaLabel);
		this.add(cantitateTextField);
		this.add(cantitateLabel);
		this.add(comandaButton);
	}

	private void plasareComanda() {
		String idPrd = idProdusTextField.getText();
		String usr = LoginPanel.whoIsLoggedIn;
		String adr = adresaTextField.getText();
		String dataC = getCurrentDate();
		String dataL = getDeliveryDate();
		int status = 1;
		int cant = Integer.parseInt(cantitateTextField.getText());
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

	private void initCautareMenu() {
		stockTable = new JTable();
		tableColumnID = new TableColumn();
		tableColumnManufacturer = new TableColumn();
		tableColumnModel = new TableColumn();
		tableColumnPrice = new TableColumn();

		tableColumnID.setHeaderValue("ID");
		stockTable.addColumn(tableColumnID);
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
		sp.setBounds(280, 60, 580, 480);
		this.add(sp);
	}
	
	private void loadAllSearchedDataInTable() {
		String s = searchTextField.getText();
		DefaultTableModel d = new DefaultTableModel();
		d.addColumn(tableHead[0]);
		d.addColumn(tableHead[1]);
		d.addColumn(tableHead[2]);
		d.addColumn(tableHead[3]);
		int i = 0;
		for(String[] s1: this.s.stonkSearchUserData(s) ) {
			d.insertRow(i, s1);
			i ++;
		}
		stockTable.setModel(d);
		stockTable.revalidate();
		stockTable.repaint();
	}

	private void revalidare() {
		this.revalidate();
		this.repaint();
	}
	
	private void close() {
		Container c = super.getParent();
		c.remove(MainForm.p);
		MainForm.p = new LoginPanel(s);
		c.add(MainForm.p);
		MainForm.p.revalidate();
	}
}
