package login;

public class Employe {
	private String Name ="";
    private String Email ="";
    private String password ="";
    private String HisJob="";
	public void setName(String name) {
		Name = name;
	}
	public String getName() {
		return Name;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getEmail() {
		return Email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setHisJob(String hisJob) {
		HisJob = hisJob;
	}
	public String getHisJob() {
		return HisJob;
	}
	
	public String toString() {
		return "I am a "+ HisJob +" my name is: "+Name+" my Email : "+ Email+" my password: "+password;
	}

	
	
    
    
}
