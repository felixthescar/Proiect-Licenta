import GUI.MainForm;
import Repo.DatabaseRepo;
import Service.Service;

public class Main {
	
	public static void main(String args[]) {
		DatabaseRepo repo = new DatabaseRepo();
		Service s = new Service(repo);
		MainForm f = new MainForm(s);
	}
}