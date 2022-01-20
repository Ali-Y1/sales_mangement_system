package Employees;

public class MainEmployeebuilder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DirecteurEmployee directeuremployee=new DirecteurEmployee();
		EmplyeeBuilder MangerOne=new MangerEmployee("khodor","Khodor@gmil.com","123456");
		EmplyeeBuilder cashierOne=new cashierEmployee("Mohammad Ali","MohammadAli@gmil.com","12345678");
		directeuremployee.setEmployeebuilder(MangerOne);
		directeuremployee.ConstructEmployee();
		Employe em=directeuremployee.getEmploye();
		System.out.println(em);
		
		
		directeuremployee.setEmployeebuilder(cashierOne);
		directeuremployee.ConstructEmployee();
		em=directeuremployee.getEmploye();
		System.out.println(em);
	}
	
}
