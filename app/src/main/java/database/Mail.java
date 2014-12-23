package database;

public class Mail {

    private String draftsID;
    private String filePath;
    private String to;
    private String subject;
    private String message;

    public Mail(String draftsID, String filePath, String to, String subject, String message) {
        this.draftsID = draftsID;
        this.filePath = filePath;
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    public String getDraftsID() {
        return draftsID;
    }

    public void setDraftsID(String draftsID) {
        this.draftsID = draftsID;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}