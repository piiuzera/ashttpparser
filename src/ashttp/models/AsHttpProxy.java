package ashttp.models;

import java.io.Serializable;
/**
 * Class model to request using proxy.
 * @author Matheus Amaro
 *
 */
public class AsHttpProxy implements Serializable {
	private static final long serialVersionUID = 6009111591090680944L;
	
	private String host;
	private int port;
	private String user;
	private String password;
	
	public AsHttpProxy(String host, int port) {
		this.host = host;
		this.port = port;
		this.user = "";
		this.password = "";
	}
	public AsHttpProxy(String host, int port, String user, String password) {
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
