package entity;

public class User {

    private int id;
    private String login;
    private String email;
    private String password;

    private User(){
    }

    public static Builder builder(){
        return new User().new Builder();
    }
    public class Builder{
        private Builder(){}

        public Builder id(int id){
            User.this.id = id;
            return this;
        }

        public Builder email(String email){
            User.this.email = email;
            return this;
        }

        public Builder login(String login){
            User.this.login = login;
            return this;
        }

        public Builder password(String password){
            User.this.password = password;
            return this;
        }

        public User build(){
            return User.this;
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
