package ashttp.models.enumtypes;

/**
 * Enum to handle the request methods.
 * @author Matheus Amaro
 *
 */
public enum AsEnumMethod {
	GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE"), OPTION("OPTION");
	
	private String value;
	
	AsEnumMethod(String value) {
		this.value = value;
	}
	
	public String getNameByValue(String value) {
		for(AsEnumMethod objEnum : values()) {
			if(value.equals(objEnum.getValue())) {
				return objEnum.name();
			}
		}
        return null;
	}
	
	public AsEnumMethod getEnumByValue(String value) {
		for(AsEnumMethod objEnum : values()) {
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
