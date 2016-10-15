package ashttp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.Proxy.Type;
import java.util.Base64;
import java.util.Vector;

import ashttp.models.AsHttpAuthorization;
import ashttp.models.AsHttpParameters;
import ashttp.models.AsHttpProxy;
import ashttp.models.enumtypes.AsEnumMethod;

/**
 * Class handling of HTTP requests.
 * @author Matheus Amaro
 *
 */
public class AsHttpRequest implements Serializable {
	private static final long serialVersionUID = -7853035488405752559L;
	
	private String url;
	private AsEnumMethod method;
	
	private int statusCode;
	private AsHttpResponse response;
	
	private AsHttpAuthorization authorization;
	
	private Proxy proxy;
	
	private Vector<AsHttpParameters> formData;
	private Vector<AsHttpParameters> headers;
	
	public AsHttpRequest() {
		this.url = "";
		this.method = AsEnumMethod.GET;
		
		this.statusCode = 0;
		
		this.authorization = new AsHttpAuthorization();
		
		this.proxy = null;
		
		this.formData = new Vector<AsHttpParameters>();
		this.headers = new Vector<AsHttpParameters>();
	}
	
	public AsHttpRequest(String url) {
		this.url = url;
		this.method = AsEnumMethod.GET;
		
		this.statusCode = 0;
		
		this.authorization = new AsHttpAuthorization();
				
		this.proxy = null;
		
		this.formData = new Vector<AsHttpParameters>();
		this.headers = new Vector<AsHttpParameters>();
	}
	
	public AsHttpRequest(String url, AsEnumMethod method) {
		this.url = url;
		this.method = method;
		
		this.statusCode = 0;
		
		this.authorization = new AsHttpAuthorization();
		
		this.proxy = null;
		
		this.formData = new Vector<AsHttpParameters>();
		this.headers = new Vector<AsHttpParameters>();
	}
	
	public void send() throws AsHttpParserException {		
		try {
			if(this.url.isEmpty()) {
				throw new AsHttpParserException("Url can't be empty.", "ASHTTPREQUEST8x10");
			}
			if(this.method == null) {
				throw new AsHttpParserException("Method can't be null.", "ASHTTPREQUEST8x11");
			}
			
			URL httpUrl = new URL(this.url);
			
			HttpURLConnection http = this.proxy != null ? (HttpURLConnection)httpUrl.openConnection(this.proxy) : (HttpURLConnection)httpUrl.openConnection();
			
			http.setRequestMethod(this.method.getValue());
			//http.setDoInput(true);
			http.setDoOutput(true);
			//http.setUseCaches(false);
			
			http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
			
			if(!this.headers.isEmpty()) {
				for(AsHttpParameters objHeader : this.headers) {
					http.setRequestProperty(objHeader.getKey(), objHeader.getValue().toString());
				}
			}
			
			if(!this.authorization.isEmpty()) {
				String auth = "Basic " + new String(Base64.getEncoder().encode(this.authorization.toString().getBytes()));
				http.setRequestProperty("Authorization", auth);
			}
			
			if(!this.formData.isEmpty()) {
				DataOutputStream data = new DataOutputStream(http.getOutputStream());
				for(int i = 0; i < this.formData.size(); i++) {
					data.writeBytes(this.formData.get(i).toString());
					if((i + 1) < this.formData.size()) {
						data.writeBytes("&");
					}
				}
			}
			
			this.response = new AsHttpResponse(http.getInputStream(), http);
			
			this.statusCode = http.getResponseCode();
			
		} catch (MalformedURLException ex) {
			throw new AsHttpParserException(ex.getMessage() + "Url informed this invalid.", "ASHTTPREQUEST8x12");
		} catch (IOException ex) {
			throw new AsHttpParserException(ex.getMessage() + "Error requesting access to the server, check your internet connection.", "ASHTTPREQUEST8x13");
		}
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public AsEnumMethod getMethod() {
		return method;
	}
	public void setMethod(AsEnumMethod method) {
		this.method = method;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public AsHttpResponse getResponse() throws AsHttpParserException {
		if(response != null) {
			return response;	
		}
		throw new AsHttpParserException("Request was not sent.", "ASHTTPREQUEST8x13");
	}
	
	public AsHttpAuthorization getAuthorization() {
		return authorization;
	}
	public void setAuthorization(AsHttpAuthorization authorization) {
		this.authorization = authorization;
	}
	
	public Proxy getProxy() {
		return proxy;
	}
	public void setProxy(AsHttpProxy proxy) {
		
		Type type = Type.HTTP;
		for(Type objType : Type.values()) {
			if(objType.name() == proxy.getProtocol()) {
				type = objType;
			}
		}
		
		this.proxy = new Proxy(type, new InetSocketAddress(proxy.getHost(), proxy.getPort()));
	}

	public Vector<AsHttpParameters> getFormData() {
		return formData;
	}
	public void addFormData(AsHttpParameters data) throws AsHttpParserException {
		if(data != null) {
			this.formData.add(data);
		} else {
			throw new AsHttpParserException("Parameter can't be null.", "ASHTTPREQUEST8x10");
		}
	}

	public Vector<AsHttpParameters> getHeaders() {
		return headers;
	}
	public void addHeader(AsHttpParameters header) throws AsHttpParserException {
		if(header != null) {
			this.headers.add(header);
		} else {
			throw new AsHttpParserException("Header can't be null.", "ASHTTPREQUEST8x11");
		}
	}
}
