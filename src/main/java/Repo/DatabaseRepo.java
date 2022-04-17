package Repo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Domain.Users;
import Domain.Stock;

public class DatabaseRepo {
	Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int st;//status
    
    public DatabaseRepo() {
    	
    }
    
    public void removeStonkWithID(String id) {
    	con = getConnection();
    	try {
    		String query =  "DELETE FROM stonks WHERE id=?;";
    		ps = con.prepareStatement(query);
    		ps.setString(1, id);
    		ps.execute();
    		System.out.println(id + " deleted");
    	} catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
 
    public int checkLogin(String username, String password) {
        con = getConnection();
        try {
            String query = "select * from accounts where username=? and password=?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if(rs.next()) {
            	if(rs.getInt("admin_privileges")==1) return 1;
            	else if(rs.getInt("admin_privileges")==0) return 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return 0;
    }
    
    public void addUser(String username, String password) {
    	con = getConnection();
    	try {
    		String query =  "INSERT INTO accounts (username, password) VALUES (?, ?)";
    		ps = con.prepareStatement(query);
    		ps.setString(1, username);
    		ps.setString(2, password);
    		ps.execute();
    		System.out.println(username + ", " + password + " worked");
    	} catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void addData(String manufacturer, String model, int quantity, int price) {
    	con = getConnection();
    	try {
    		String query =  "INSERT INTO stonks (manufacturer, model, quantity, price) VALUES (?, ?, ?, ?)";
    		ps = con.prepareStatement(query);
    		ps.setString(1, manufacturer);
    		ps.setString(2, model);
    		ps.setInt(3, quantity);
    		ps.setInt(4, price);
    		ps.execute();
    		System.out.println(manufacturer + ", " + model + ", " +quantity + ", " +price + ", " +" worked");
    	} catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public Connection getConnection() {
        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/licenta", "root", "4kgjuzuw");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException " + e);
        } catch (SQLException e) {
            System.out.println("SQLException " + e);
        }
        return c;
    }
    
    public List<Stock> getAllStocks() {
    	List<Stock> l = new ArrayList<>();
    	con = getConnection();
    	String query = "select * from stonks";
    	try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				l.add(new Stock(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return l;
    }
    
    public List<Stock> searchStocksProgressive(String search) {
    	List<Stock> l = new ArrayList<>();
    	con = getConnection();
    	String q1 = "(manufacturer like ? or model like ? or quantity like ? or price like ?) ";
    	try {
    		String query = "select * from stonks where 1=1 ";
    		for(String s: search.split(" ")) {
        		query+="and ";
        		query+=q1;
        	}
			ps = con.prepareStatement(query);
			int i = 0;
			for(String s: search.split(" ")) {
				ps.setString(1+i*4, "%"+s+"%");
				ps.setString(2+i*4, "%"+s+"%");
	            ps.setString(3+i*4, "%"+s+"%");
	            ps.setString(4+i*4, "%"+s+"%");
	            i++;
        	}
			rs = ps.executeQuery();
			while(rs.next()) {
				l.add(new Stock(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return l;
    }
}