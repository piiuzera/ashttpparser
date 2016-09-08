package ashttp.models.enumtypes;

/**
 * Enum to handle the request methods.
 * @author Matheus Amaro
 *
 */
public enum AsHttpMethod {
	GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE"), OPTION("OPTION");
	
	private String value;
	
	AsHttpMethod(String value) {
		this.value = value;
	}
	
	public String getNameByValue(String value) {
		for(AsHttpMethod objEnum : values()) {
			if(value.equals(objEnum.getValue())) {
				return objEnum.name();
			}
		}
        return null;
	}
	
	public AsHttpMethod getEnumByValue(String value) {
		for(AsHttpMethod objEnum : values()) {
			if(value.equals(objEnum.getValue())) {
				return objEnum;
			}
		}
        return null;
	}

	public String getValue() {
		return value;
	}
}
