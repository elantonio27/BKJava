package newP;

import javax.mail.Address;
import java.util.Arrays;
import java.util.Date;

public class Mailobject {


    private String subject;
    private Address[] senderA;
    private String sender;
    private Date date;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Address[] getSenderA() {
        return senderA;
    }

    public void setSenderA(Address[] senderA) {
        this.senderA = senderA;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Mailobject() {
    }

    @Override
    public String toString() {
        return "Mailobject{" +
                "subject='" + subject + '\'' +
                ", senderA=" + Arrays.toString(senderA) +
                ", sender='" + sender + '\'' +
                ", date=" + date +
                '}';
    }
}
