package dto;

public class ReadUserDto {
	
	private int id;
	private String login;
	private String password;
	
	public ReadUserDto() {
		
	}
	
	public static Builder builder() {
		return new ReadUserDto().new Builder();
	}
	
	public class Builder{
		
		private Builder() {
			
		}
		
		public Builder id(int id) {
			ReadUserDto.this.id = id;
			return this;
		}
		
		public Builder login(String login) {
			ReadUserDto.this.login = login;
			return this;
		}
		
		public Builder password(String password) {
			ReadUserDto.this.password = password;
			return this;
		}
		
		public ReadUserDto build() {
			return ReadUserDto.this;
		}
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String toString() {
		return login;
	}
	
}
