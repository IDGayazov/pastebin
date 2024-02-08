package entity;

import java.time.LocalDateTime;

public class Paste {
    private int id;
    private String name;
    private String category;
    private String hash;
    private LocalDateTime createDate;
    private LocalDateTime expiredDate;
    private int userId;

    public Paste(){

    }

    public Paste(String name, String category, String hash,
                 LocalDateTime create_date, LocalDateTime expired_date, int user_id) {
        this.name = name;
        this.category = category;
        this.hash = hash;
        this.createDate = create_date;
        this.expiredDate = expired_date;
        this.userId = user_id;
    }

    public static Builder builder(){
        return new Paste().new Builder();
    }

    public class Builder{

        private Builder(){

        }

        public Builder id(int id){
            Paste.this.id = id;
            return this;
        }

        public Builder name(String name){
            Paste.this.name = name;
            return this;
        }

        public Builder category(String cat){
            Paste.this.category = cat;
            return this;
        }

        public Builder hash(String hash){
            Paste.this.hash = hash;
            return this;
        }

        public Builder createDate(LocalDateTime createDate){
            Paste.this.createDate = createDate;
            return this;
        }

        public Builder expiredDate(LocalDateTime expiredDate){
            Paste.this.expiredDate = expiredDate;
            return this;
        }

        public Builder userId(int userId){
            Paste.this.userId = userId;
            return this;
        }

        public Paste build(){
            return Paste.this;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Paste{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", hash='" + hash + '\'' +
                ", create_date=" + createDate +
                ", expired_date=" + expiredDate +
                ", user_id=" + userId +
                '}';
    }
}
