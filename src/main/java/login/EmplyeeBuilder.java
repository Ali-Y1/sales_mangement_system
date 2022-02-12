package login;

public abstract class EmplyeeBuilder {
protected Employe employe;

public Employe getEmploye() {
	return employe;
}
 public void CreateNewEmployee() {
	 employe = new Employe();
 }
 
 public abstract void buildName();
 public abstract void buildEmail();
 public abstract void buildpassword();
 public abstract void buildHisjob();
}
