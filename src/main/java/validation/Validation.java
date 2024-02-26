package validation;

public interface Validation<T> {

	public ValidationResult isValid(T entitiy);
	
}