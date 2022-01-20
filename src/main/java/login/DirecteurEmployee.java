package Employees;

public class DirecteurEmployee {

	private EmplyeeBuilder employeebuilder;

	public void setEmployeebuilder(EmplyeeBuilder employeebuilder) {
		this.employeebuilder = employeebuilder;
	}
	
	public Employe getEmploye() {
		return employeebuilder.getEmploye();
	}

	
	public void ConstructEmployee() {
		employeebuilder.CreateNewEmployee();
		employeebuilder.buildName();
		employeebuilder.buildEmail();
		employeebuilder.buildpassword();
		employeebuilder.buildHisjob();
	}
}
