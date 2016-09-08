package ashttp.models;

import java.io.Serializable;
import java.util.List;

import ashttp.AsHttpParserException;
import ashttp.models.enumtypes.AsHttpMethod;

public class AsHttpRequest implements Serializable {
	private static final long serialVersionUID = -7853035488405752559L;
	
	private String url;
	private AsHttpMethod method;
	
	private List<AsHttpParameters> formData;
	private List<AsHttpParameters> headers;
	
	public AsHttpRequest() {
		
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

	public List<AsHttpParameters> getFormData() {
		return formData;
	}
	
	public void addFormData(AsHttpParameters data) throws AsHttpParserException {
		if(data != null) {
			this.formData.add(data);
		} else {
			throw new AsHttpParserException("Parameter can't be null.", "ASHTTPREQUEST8x10");
		}
	}

	public List<AsHttpParameters> getHeaders() {
		return headers;
	}
	
	public void addHeader(AsHttpParameters header) throws AsHttpParserException {
		if(header != null) {
			this.headers.add(header);
		} else {
			throw new AsHttpParserException("Header can't be null.", "ASHTTPREQUEST8x10");
		}
	}
}
