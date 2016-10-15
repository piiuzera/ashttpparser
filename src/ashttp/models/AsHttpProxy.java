package ashttp.models;

import java.io.Serializable;
/**
 * Class model to request using proxy.
 * @author Matheus Amaro
 *
 */
public class AsHttpProxy implements Serializable {
	private static final long serialVersionUID = 6009111591090680944L;
	
	private String protocol;
	private String host;
	private int port;
	
	public AsHttpProxy(String protocol, String host, int port) {
		this.protocol = protocol;
		this.host = host;
		this.port = port;
	}
	
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
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
