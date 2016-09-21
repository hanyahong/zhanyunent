package cc.zhanyun.model;

import cc.zhanyun.model.location.Images;

import java.util.ArrayList;
import java.util.List;

public class Email {
    private String from;
    private String to;
    private String subject;
    private String text;
    private List<Images> houses = new ArrayList();

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Images> getHouses() {
        return this.houses;
    }

    public void setHouses(List<Images> houses) {
        this.houses = houses;
    }
}


