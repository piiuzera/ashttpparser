package ashttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Vector;

import org.jsoup.nodes.Document;

import ashttp.models.AsHttpParameters;

public class AsHttpResponse implements Serializable {
	private static final long serialVersionUID = 8943293685945666320L;
	
	private Document document;
	
	private Vector<AsHttpParameters> headers;
	
	public AsHttpResponse(InputStream inputStream, Vector<AsHttpParameters> headers) throws AsHttpParserException {
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
			
		} catch (IOException ex) {
			throw new AsHttpParserException("Url Informada Está Inválida!",
					"AS8X101", ex.getMessage());
		}
	}

	public Vector<AsHttpParameters> getHeaders() {
		return headers;
	}

	public Object getDocument() {
		return document;
	}
}
