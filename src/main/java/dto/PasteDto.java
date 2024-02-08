package dto;

import entity.Paste;

import java.time.LocalDateTime;

public class PasteDto {
    private String name;
    private String category;
    private String text;
    private LocalDateTime createDate;
    private LocalDateTime expiredDate;
    private Integer fileSize;
    private String username;

    public PasteDto(){

    }

    public static Builder builder(){
        return new PasteDto().new Builder();
    }

    public class Builder{

        private Builder(){

        }
        public Builder name(String name){
            PasteDto.this.name = name;
            return this;
        }

        public Builder category(String cat){
            PasteDto.this.category = cat;
            return this;
        }

        public Builder text(String text){
            PasteDto.this.text = text;
            return this;
        }

        public Builder createDate(LocalDateTime createDate){
            PasteDto.this.createDate = createDate;
            return this;
        }

        public Builder fileSize(int size){
            PasteDto.this.fileSize = size;
            return this;
        }

        public Builder expiredDate(LocalDateTime expiredDate){
            PasteDto.this.expiredDate = expiredDate;
            return this;
        }
        public Builder username(String username){
            PasteDto.this.username = username;
            return this;
        }

        public PasteDto build(){
            return PasteDto.this;
        }
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "PasteDto{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", text='" + text + '\'' +
                ", createDate=" + createDate +
                ", expiredDate=" + expiredDate +
                ", fileSize=" + fileSize +
                ", username='" + username + '\'' +
                '}';
    }
}
