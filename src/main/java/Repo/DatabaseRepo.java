package Repo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Domain.Stock;
import Domain.Users;

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

	public void removeUserWithID(String id) {
    	con = getConnection();
    	try {
    		String query =  "DELETE FROM accounts WHERE id=?;";
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
            	if(rs.getInt("admin_privileges")==2) return 1;
            	else if(rs.getInt("admin_privileges")==1) return 2;
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
    		String query =  "INSERT INTO accounts (username, password, admin_privileges) VALUES (?, ?, 1)";
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
			e.printStackTrace();
		}
    	return l;
    }

	public List<Users> getAllUsers() {
    	List<Users> l = new ArrayList<>();
    	con = getConnection();
    	String query = "select * from accounts";
    	try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				l.add(new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return l;
    }

	public void modifyUserWithID (int id, String usr, String pass, int adm_prv) {
    	con = getConnection();
    	try {
			String query = "UPDATE accounts SET ";//manufacturer=?, model=?, quantity=?, price=? WHERE id=?;";
			boolean firstNull = true;
			boolean isUsr = true;
			boolean isPass = true;
			boolean isAdm = true;
			if(!usr.equals("")) {
				query+="username=?";
				firstNull = false;
			}else {
				isUsr = false;
			}
			if(!pass.equals("") && !firstNull) {
				query+=", password=?";
			} else if (!pass.equals("") && firstNull){
				query+="password=?";
				firstNull = false;
			} else {
				isPass = false;
			}
			if(adm_prv!=0 && !firstNull) {
				query+=", admin_privileges=?";
			} else if(adm_prv!=0 && firstNull){
				query+="admin_privileges=?";
			} else {
				isAdm = false;
			}
			query+=" WHERE id=?;";
			System.out.println(query);
			ps = con.prepareStatement(query);
			int cnt = 1;
			if(isUsr) {
				ps.setString(cnt, usr);
				cnt++;
			}
			if(isPass) {
				ps.setString(cnt, pass);
				cnt++;
			}
			if(isAdm) {
				ps.setInt(cnt, adm_prv);
				cnt++;
			}
			ps.setInt(cnt, id);
			ps.execute();
			System.out.println("modificat ficat ficat");
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

	public void modifyStonkWithID (int id, String man, String mod, int qnt, int price) {
    	con = getConnection();
    	try {
			System.out.println(man + " " + mod + " " + qnt + " " + price);
			String query = "UPDATE stonks SET ";//manufacturer=?, model=?, quantity=?, price=? WHERE id=?;";
			boolean firstNull = true;
			boolean isMan = true;
			boolean isMod = true;
			boolean isQnt = true;
			boolean isPrice = true;
			if(!man.equals("")) {
				query+="manufacturer=?";
				firstNull = false;
			}else {
				isMan = false;
			}
			if(!mod.equals("") && !firstNull) {
				query+=", model=?";
			} else if (!mod.equals("") && firstNull){
				query+="model=?";
				firstNull = false;
			} else {
				isMod = false;
			}
			if(qnt!=0 && !firstNull) {
				query+=", quantity=?";
			} else if(qnt!=0 && firstNull){
				query+="quantity=?";
				firstNull = false;
			} else {
				isQnt = false;
			}
			if(price!=0 && !firstNull) {
				query+=", price=?";
			} else if(price!=0 && firstNull){
				query+="price=?";
			} else {
				isPrice = false;
			}
			query+=" WHERE id=?;";
			System.out.println(query);
			ps = con.prepareStatement(query);
			int cnt = 1;
			if(isMan) {
				ps.setString(cnt, man);
				cnt++;
			}
			if(isMod) {
				ps.setString(cnt, mod);
				cnt++;
			}
			if(isQnt) {
				ps.setInt(cnt, qnt);
				cnt++;
			}
			if(isPrice) {
				ps.setInt(cnt, price);
				cnt++;
			}
			ps.setInt(cnt, id);
			ps.execute();
			System.out.println("modificat ficat ficat");
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
    
	public List<Users> searchUsersProgressive(String search) {
    	List<Users> l = new ArrayList<>();
    	con = getConnection();
    	String q1 = "(username like ? or password like ? or admin_privileges like ?) ";
    	try {
    		String query = "select * from accounts where 1=1 ";
    		for(String s: search.split(" ")) {
				System.out.println(s);
        		query+="and ";
        		query+=q1;
        	}
			ps = con.prepareStatement(query);
			int i = 0;
			for(String s: search.split(" ")) {
				ps.setString(1+i*4, "%"+s+"%");
				ps.setString(2+i*4, "%"+s+"%");
	            ps.setString(3+i*4, "%"+s+"%");
	            i++;
        	}
			rs = ps.executeQuery();
			while(rs.next()) {
				l.add(new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
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
				System.out.println(s);
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
			e.printStackTrace();
		}
    	
    	return l;
    }
}