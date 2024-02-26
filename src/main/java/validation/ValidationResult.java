package validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

	private final List<Error> errors = new ArrayList<>();
	
	public void add(Error error) {
		this.errors.add(error);
	}
	
	public void add(ValidationResult other){
		this.errors.addAll(other.getErrors());
	}
	
	public boolean hasErrors() {
		return !errors.isEmpty();
	}
	
	public List<Error> getErrors() {
		return errors;
	}
}
