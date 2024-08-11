/*
 * @author Kayode Ojo
 */
package smaApi;

public class studentModel {
	private int userID;
	private String password;
	private String email;
	private String role;
	public studentModel(String email, String password) {
		super();
		this.password = password;
		this.email = email;
	}
	@Override
	public String toString() {
		return "studentModel [userID=" + userID + ", email=" + email + "], password="+ password ;
	}

	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String username) {
		this.email = username;
	}
	public String getRole() {
		// TODO Auto-generated method stub
		return role;
	}
	public void setRole() {
		this.role=role;
	}

}
