package tek.api.utility;

public enum EndPoints2 {
	
	TOKEN_GENERATION("/api/token"),
	TOKEN_VERIFY("/api/token/verify");
	
	private String value;
	
	EndPoints2 (String value) {
		this.value = value;
	}
	
	public String getValue () {
		return this.value;
	}

}
