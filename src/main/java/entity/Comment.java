package entity;

import java.time.LocalDateTime;

public class Comment {

    private int id;
    private String text;

    private int userId;
    private int pasteId;
    private LocalDateTime createDate;

    public Comment(String text, int user_id, int paste_id, LocalDateTime create_date) {
        this.text = text;
        this.userId = user_id;
        this.pasteId = paste_id;
        this.createDate = create_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
    }

    public int getPasteId() {
        return pasteId;
    }

    public void setPasteId(int paste_id) {
        this.pasteId = paste_id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", user_id=" + userId +
                ", paste_id=" + pasteId +
                ", create_date=" + createDate +
                '}';
    }
}
