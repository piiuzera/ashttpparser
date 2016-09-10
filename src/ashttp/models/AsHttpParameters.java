package ashttp.models;

import java.io.Serializable;

/**
 * Class model parameters using the keys and values.
 * @author Matheus Amaro
 *
 */
public class AsHttpParameters implements Serializable {
	private static final long serialVersionUID = 5561313429438187237L;
	
	private String key;
	private Object value;
	
	public AsHttpParameters() {
		this.key = "";
		this.value = "";
	}
	public AsHttpParameters(String key) {
		this.key = key;
		this.value = "";		
	}
	public AsHttpParameters(String key, Object value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.key + "=" + this.value;
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
