package ashttp.models;

import java.io.Serializable;

/**
 * Class that authenticates the HTTP request.
 * @author Matheus Amaro
 *
 */
public class AsHttpAuthorization implements Serializable {
	private static final long serialVersionUID = 55516442876232698L;
	
	private String username;
	private String password;
	
	public AsHttpAuthorization() {
		this.username = "";
		this.password = "";
	}
	public AsHttpAuthorization(String username) {
		this.username = username;
		this.password = "";		
	}
	public AsHttpAuthorization(String username, String password) {
		this.username = username;
		this.password = password;
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
}
