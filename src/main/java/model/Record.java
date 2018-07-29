package model;

import java.sql.Timestamp;


public class Record {

    private int recordId;
    private Timestamp postDate;
    private String postText;

    public Record(int recordId, Timestamp postDate, String postText) {
        this.recordId = recordId;
        this.postDate = postDate;
        this.postText = postText;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    @Override
    public String toString() {
        return "Record{" +
                "recordId=" + recordId +
                ", postDate=" + postDate +
                ", postText='" + postText + '\'' +
                '}';
    }
}
