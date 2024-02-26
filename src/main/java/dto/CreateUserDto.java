package dto;

public class CreateUserDto {
	private String login;
	private String email;
	private String password;
	
	public CreateUserDto() {
		
	}

	public static Builder builder() {
		return new CreateUserDto().new Builder();
	}
	
	
	public class Builder {
		
		public Builder() {
			
		}
		
		public Builder login(String login) {
			CreateUserDto.this.login = login;
			return this;
		}
		
		public Builder email(String email) {
			CreateUserDto.this.email = email;
			return this;
		}
		
		public Builder password(String password) {
			CreateUserDto.this.password = password;
			return this;
		}
		
		public CreateUserDto build() {
			return CreateUserDto.this;
		}
		
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
}
