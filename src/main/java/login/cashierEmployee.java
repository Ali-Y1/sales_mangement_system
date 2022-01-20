package Employees;

public class cashierEmployee extends EmplyeeBuilder{
	private String Name;
	private String Email;
	private String password;

	public cashierEmployee(String Name,String Email,String password) {
		this.Name=Name;
		this.Email=Email;
		this.password=password;
	}

	@Override
	public void buildName() {
		// TODO Auto-generated method stub
		employe.setName(Name);
	}

	@Override
	public void buildEmail() {
		// TODO Auto-generated method stub
		employe.setEmail(Email);
	}

	@Override
	public void buildpassword() {
		// TODO Auto-generated method stub
		employe.setPassword(password);
	}

	@Override
	public void buildHisjob() {
		// TODO Auto-generated method stub
		employe.setHisJob("Cashier");
		
	}
}
