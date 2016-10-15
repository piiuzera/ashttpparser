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
	
	public AsHttpProxy(String host, int port) {
		this.host = host;
		this.port = port;
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
}
