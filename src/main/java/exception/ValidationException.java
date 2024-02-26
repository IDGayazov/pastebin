package exception;

import java.util.List;
import validation.Error;

public class ValidationException extends RuntimeException{

	private final List<Error> errors;
	
	public ValidationException(List<Error> errors) {
		this.errors = errors;
	}
	
	public List<Error> getErrors(){
		return errors;
	}
}
