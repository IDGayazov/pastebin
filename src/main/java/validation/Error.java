package validation;

public class Error {
	
	final String code;
	final String message;
	
	private Error(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public static Error of(String code, String message) {
		return new Error(code, message);
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getMessage() {
		return this.message;
	}
	
}
