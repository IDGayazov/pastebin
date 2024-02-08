package dto;

import java.time.Duration;

public class CreatePasteDto {
    private String name;
    private String category;
    private String text;
    private Duration duration;

    private CreatePasteDto(){

    }

    public static Builder builder(){
        return new CreatePasteDto().new Builder();
    }

    public class Builder{

        private Builder(){

        }

        public Builder name(String name){
            CreatePasteDto.this.name = name;
            return this;
        }

        public Builder category(String cat){
            CreatePasteDto.this.category = cat;
            return this;
        }

        public Builder duration(Duration duration){
            CreatePasteDto.this.duration = duration;
            return this;
        }

        public Builder text(String text){
            CreatePasteDto.this.text = text;
            return this;
        }

        public CreatePasteDto build(){
            return CreatePasteDto.this;
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

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "PasteDto{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", text='" + text + '\'' +
                ", duration=" + duration +
                '}';
    }
}
