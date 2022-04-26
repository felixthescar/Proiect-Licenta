package Domain;
public class Users {
	int id;
	String username;
	String password;
	int admin_priviliges;
	
	public Users() {
		
	}

	public Users(int id, String username, String password, int admin_privileges) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.admin_priviliges = admin_privileges;
	}

	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getAdminPriviliges() {
		return admin_priviliges;
	}
	
	public void setAdminPriviliges(int admin_priviliges) {
		this.admin_priviliges = admin_priviliges;
	}
	
	@Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", "
        		+ "password=" + password + ", admin_priviliges=" + admin_priviliges + '}';
    }
}
