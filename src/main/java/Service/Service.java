package Service;

import java.util.ArrayList;
import java.util.List;

import Repo.DatabaseRepo;
import Domain.Stock;
import Domain.Users;

public class Service {
	DatabaseRepo repo;
	
	public Service(DatabaseRepo repo) {
		this.repo = repo;
	}
	
	public int tryLogin(String username, String password) throws Exception {
		//validation
		if((username.length()<=3 && username.length()>0)||
			(password.length()<=3 && password.length()>0)) {
			throw new Exception("Credentials too short!");
		}
		
		if(username.length()==0 || password.length()==0) {
			throw new Exception("Fields cannot be empty!");
		}
		if(username.length()>49 || password.length()>49) {
			throw new Exception("Credentials too long!");
		}
		//logic
		if(repo.checkLogin(username, password)==1) {
			return 1;
		}
		else if(repo.checkLogin(username, password)==2) {
			return 2;
		}
		else {
			throw new Exception("Invalid Data!");
		}
	}

	public void repairTableID(String table) {
		//repo.repairID(table);
	}
    
    public void tryRegister(String username, String password) throws Exception {
    	//validation
    	if((username.length()<=3 && username.length()>0)||
    			(password.length()<=3 && password.length()>0)) {
    			throw new Exception("Credentials too short!");
    		}
    		
    		if(username.length()==0 || password.length()==0) {
    			throw new Exception("Fields cannot be empty!");
    		}
    		if(username.length()>49 || password.length()>49) {
    			throw new Exception("Credentials too long!");
    		}
    	//logic
    	repo.addUser(username, password);
    }

	public void tryModifyStonks(int id, String man, String mod, String qnt, String price) {
		//validation
		int qntInt, priceInt;
		if(qnt.equals("")) qntInt=0;
		else qntInt = Integer.parseInt(qnt);
		
		if(price.equals("")) priceInt=0;
		else priceInt = Integer.parseInt(price);

		//logic
		repo.modifyStonkWithID(id, man, mod, qntInt, priceInt);
	}

	public void tryModifyUsers(int id, String usr, String pass, String adm_prv) {
		//validation
		int adm_prvInt;
		if(adm_prv.equals("")) adm_prvInt = 0;
		else adm_prvInt = Integer.parseInt(adm_prv);
		//logic
		repo.modifyUserWithID(id, usr, pass, adm_prvInt);
	}
    
    public void addStonksData(String manufacturer, String model, int quantity, int price) {
    	repo.addData(manufacturer, model, quantity, price);
    }
    
    public void removeDataWithID(String query) {
    	repo.removeStonkWithID(query);
    }

	public void removeUserDataWithID(String query) {
    	repo.removeUserWithID(query);
    }
	
	public List<String[]> stonkGetAllData() {
		List<String[]> l = new ArrayList<>();
		
		for(Stock stock: repo.getAllStocks()) {
			l.add(new String[] {
					Integer.toString(stock.getID()),
					stock.getManufacturer(),
					stock.getModel(),
					Integer.toString(stock.getQuantity()),
					Integer.toString(stock.getPrice())
			});
		}
		
		return l;
	}
	
	public List<String[]> stonkSearchData(String query) {
		List<String[]> l = new ArrayList<>();
		
		for(Stock stock: repo.searchStocksProgressive(query)) {
			l.add(new String[] {
					Integer.toString(stock.getID()),
					stock.getManufacturer(),
					stock.getModel(),
					Integer.toString(stock.getQuantity()),
					Integer.toString(stock.getPrice())
			});
		}
		
		return l;
	}

	public List<String[]> userSearchData(String query) {
		List<String[]> l = new ArrayList<>();
		
		for(Users user: repo.searchUsersProgressive(query)) {
			l.add(new String[] {
				Integer.toString(user.getID()),
				user.getUsername(),
				user.getPassword(),
				Integer.toString(user.getAdminPriviliges())
			});
		}
		
		return l;
	}

	public List<String[]> userGetAllData() {
		List<String[]> l = new ArrayList<>();
		
		for(Users user: repo.getAllUsers()) {
			l.add(new String[] {
					Integer.toString(user.getID()),
					user.getUsername(),
					user.getPassword(),
					Integer.toString(user.getAdminPriviliges())
			});
		}
		
		return l;
	}
	
	
	public List<String[]> stonkSearchUserData(String query) {
		List<String[]> l = new ArrayList<>();
		
		for(Stock stock: repo.searchStocksProgressive(query)) {
			l.add(new String[] {
					stock.getManufacturer(),
					stock.getModel(),
					Integer.toString(stock.getPrice())
			});
		}
		
		return l;
	}
	
}