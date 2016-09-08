package ashttp;

import java.io.Serializable;
import java.util.Vector;

import ashttp.models.AsHttpParameters;
import ashttp.models.enumtypes.AsHttpMethod;

/**
 * Class handling of HTTP requests.
 * @author Matheus Amaro
 *
 */
public class AsHttpRequest implements Serializable {
	private static final long serialVersionUID = -7853035488405752559L;
	
	private String url;
	private AsHttpMethod method;
	
	private Vector<AsHttpParameters> formData;
	private Vector<AsHttpParameters> headers;
	
	public AsHttpRequest() {
		this.url = "";
		this.method = AsHttpMethod.GET;
		
		this.formData = new Vector<AsHttpParameters>();
		this.headers = new Vector<AsHttpParameters>();
	}
	
	public AsHttpRequest(String url) {
		this.url = url;
		this.method = AsHttpMethod.GET;
		
		this.formData = new Vector<AsHttpParameters>();
		this.headers = new Vector<AsHttpParameters>();
	}
	
	public AsHttpRequest(String url, AsHttpMethod method) {
		this.url = url;
		this.method = method;
		
		this.formData = new Vector<AsHttpParameters>();
		this.headers = new Vector<AsHttpParameters>();
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public AsHttpMethod getMethod() {
		return method;
	}
	public void setMethod(AsHttpMethod method) {
		this.method = method;
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
