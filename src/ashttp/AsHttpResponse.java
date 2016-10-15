package ashttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.jsoup.nodes.Document;

import ashttp.models.AsHttpParameters;

public class AsHttpResponse implements Serializable {
	private static final long serialVersionUID = 8943293685945666320L;
	
	private Document document;
	
	private int statusCode;
	
	private Vector<AsHttpParameters> headers;
	
	private Vector<AsHttpParameters> cookie;
	
	public AsHttpResponse(InputStream inputStream, Vector<AsHttpParameters> headers, HttpURLConnection http) throws AsHttpParserException {
		String lineResponse = "";
		StringBuffer responseStream = new StringBuffer();
		
		this.headers = headers;
		
		try {
			BufferedReader buff = new BufferedReader(new InputStreamReader(inputStream));
			while((lineResponse = buff.readLine()) != null) {
				responseStream.append(lineResponse);
			}
			buff.close();
			
			this.document = new Document(responseStream.toString());
			
			this.cookie = getCookie(http.getHeaderFields());
			
		} catch (IOException ex) {
			throw new AsHttpParserException("Url Informada Está Inválida!",
					"AS8X101", ex.getMessage());
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

	public Vector<AsHttpParameters> getHeaders() {
		return headers;
	}
	
	public int getStatusCode() {
		return statusCode;
	}

	public Document getDocument() {
		return document;
	}

	public Vector<AsHttpParameters> getCookie() {
		return cookie;
	}
}
