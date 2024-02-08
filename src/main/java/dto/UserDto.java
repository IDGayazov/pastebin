package dto;

public class UserDto {
    private String login;
    private String email;

    private UserDto(){

    }

    public static Builder builder(){
        return new UserDto().new Builder();
    }

    public class Builder{

        private Builder(){

        }

        public Builder login(String login){
            UserDto.this.login = login;
            return this;
        }

        public Builder email(String email){
            UserDto.this.email = email;
            return this;
        }

        public UserDto build(){
            return UserDto.this;
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
