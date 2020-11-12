package com.khupearl.smp.mentor.wbs;

import java.util.ArrayList;

public class Work {
    private String teamName;
    private String subject;
    private String content;
    private String time;
    private String field;
    ArrayList<String> member = new ArrayList<>();

    public Work(String subject, String field) {
        this.subject = subject;
        this.field = field;
    }

    public Work(String teamName, String subject, String content, String time, String field, ArrayList<String> member) {
        this.teamName = teamName;
        this.subject = subject;
        this.content = content;
        this.time = time;
        this.field = field;
        this.member = member;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public ArrayList<String> getMember() {
        return member;
    }

    public void setMember(ArrayList<String> member) {
        this.member = member;
    }
}
