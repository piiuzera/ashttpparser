package ashttp;

/**
 * Logic of exception handling class produced in this library.
 * @author Matheus Amaro
 *
 */
public class AsHttpParserException extends Exception {
	private static final long serialVersionUID = 7651925637512205633L;
	
	private String code;
	private String details;
	
	public AsHttpParserException(String message) {
		super(message);
		this.code = "";
		this.details = "";
	}
	
	public AsHttpParserException(String message, String code) {
		super(message);
		this.code = code;
		this.details = "";
	}

	public AsHttpParserException(String message, String code, String details) {
		super(message);
		this.code = code;
		this.details = details;
	}

	public String getCode() {
		return code;
	}

	public String getDetails() {
		return details;
	}
}
