package validation;

import dto.CreateUserDto;

public class CreateUserValidation implements Validation<CreateUserDto>{
	
	private static final CreateUserValidation INSTANCE = new CreateUserValidation();
	
	private static final String VALID_EMAIL_PATTERN = "([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+)";
	private static final String VALID_PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
	
	private CreateUserValidation() {}
	
	public ValidationResult isValid(CreateUserDto user) {
		
		ValidationResult validationResult = new ValidationResult();
		validationResult.add(isValidEmail(user.getEmail()));
		validationResult.add(isValidPassword(user.getPassword()));
		return validationResult;
	}
	
	private ValidationResult isValidEmail(String email) {
		
		ValidationResult validationResult = new ValidationResult();
		if(!email.matches(VALID_EMAIL_PATTERN)) {
			validationResult.add(Error.of("invalid.email", "Email is invalid"));
		}
		return validationResult;
	}
	
	private ValidationResult isValidPassword(String password) {
		
		ValidationResult validationResult = new ValidationResult();
		if(!password.matches(VALID_PASSWORD_PATTERN)) {
			validationResult.add(Error.of("invalid.password", "Password is invalid"));
		}
		return validationResult;
	}
	
	public static CreateUserValidation getInstance() {
		return INSTANCE;
	}
	
}
