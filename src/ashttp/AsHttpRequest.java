package ashttp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import ashttp.models.AsHttpAuthorization;
import ashttp.models.AsHttpParameters;
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
	
	private Vector<AsHttpParameters> formData;
	private Vector<AsHttpParameters> headers;
	private Vector<AsHttpParameters> cookies;
	
	public AsHttpRequest() {
		this.url = "";
		this.method = AsEnumMethod.GET;
		
		this.statusCode = 0;
		
		this.authorization = new AsHttpAuthorization();
		
		this.formData = new Vector<AsHttpParameters>();
		this.headers = new Vector<AsHttpParameters>();
		this.cookies = new Vector<AsHttpParameters>();
	}
	
	public AsHttpRequest(String url) {
		this.url = url;
		this.method = AsEnumMethod.GET;
		
		this.statusCode = 0;
		
		this.authorization = new AsHttpAuthorization();
		
		this.formData = new Vector<AsHttpParameters>();
		this.headers = new Vector<AsHttpParameters>();
		this.cookies = new Vector<AsHttpParameters>();
	}
	
	public AsHttpRequest(String url, AsEnumMethod method) {
		this.url = url;
		this.method = method;
		
		this.statusCode = 0;
		
		this.authorization = new AsHttpAuthorization();
		
		this.formData = new Vector<AsHttpParameters>();
		this.headers = new Vector<AsHttpParameters>();
		this.cookies = new Vector<AsHttpParameters>();
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
			HttpURLConnection http = (HttpURLConnection)httpUrl.openConnection();
			
			http.setRequestMethod(this.method.getValue());
			http.setDoInput(true);
			http.setDoOutput(true);
			http.setUseCaches(false);
			
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
			
			this.cookies = getCookie(http.getHeaderFields());
			
			this.response = new AsHttpResponse(http.getInputStream(), this.headers);
			
		} catch (MalformedURLException ex) {
			throw new AsHttpParserException("Url informed this invalid.", "ASHTTPREQUEST8x12");
		} catch (IOException ex) {
			throw new AsHttpParserException("Error requesting access to the server, check your internet connection.", "ASHTTPREQUEST8x13");
		}
	}
	
	private Vector<AsHttpParameters> getCookie(Map<String, List<String>> headers) {
		Vector<AsHttpParameters> cookies = new Vector<AsHttpParameters>();

		Set<String> headerFieldsSet = headers.keySet();
		Iterator<String> hearerFieldsIter = headerFieldsSet.iterator();

		while (hearerFieldsIter.hasNext()) {
			String headerFieldKey = hearerFieldsIter.next();
			if ("Set-Cookie".equalsIgnoreCase(headerFieldKey)) {
				List<String> headerFieldValue = headers.get(headerFieldKey);
				for (String headerValue : headerFieldValue) {

					String[] fields = headerValue.split(";\\s*");

					String cookieValue = fields[0];
					String expires = null;
					String path = null;
					String domain = null;
					boolean secure = false;

					for (int j = 1; j < fields.length; j++) {
						if ("secure".equalsIgnoreCase(fields[j])) {
							secure = true;
						}
						else if (fields[j].indexOf('=') > 0) {
							String[] f = fields[j].split("=");
							if ("expires".equalsIgnoreCase(f[0])) {
								expires = f[1];
							}
							else if ("domain".equalsIgnoreCase(f[0])) {
								domain = f[1];
							}
							else if ("path".equalsIgnoreCase(f[0])) {
								path = f[1];
							}
						}

					}
					cookies.add(new AsHttpParameters("cookieValue", cookieValue));
					cookies.add(new AsHttpParameters("expires", expires));
					cookies.add(new AsHttpParameters("path", path));
					cookies.add(new AsHttpParameters("domain", domain));
					cookies.add(new AsHttpParameters("secure", secure));
				}
			}
		}
		return cookies;
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

	public Vector<AsHttpParameters> getCookies() {
		return cookies;
	}
}
