package package1;

import java.io.Serializable;

class Email implements Serializable {
    private String email;
    private String subject;
    private String body;
    public Email(String email, String subject, String body) {
        this.email = email;
        this.subject = subject;
        this.body = body;
    }
    public String getEmail() {
        return email;
    }
    public String getSubject() {
        return subject;
    }
    public String getBody() {
        return body;
    }
    public String getMailDetails() {
        return "Subject: " + getSubject() + " | " + "Recipient: " + getEmail();
    }
}