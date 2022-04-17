package Service;

import java.util.ArrayList;
import java.util.List;

import Repo.DatabaseRepo;
import Domain.Stock;

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
    
    public void addStonksData(String manufacturer, String model, int quantity, int price) {
    	repo.addData(manufacturer, model, quantity, price);
    }
    
    public void removeDataWithID(String query) {
    	repo.removeStonkWithID(query);
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