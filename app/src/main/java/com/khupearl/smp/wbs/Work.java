package com.khupearl.smp.wbs;

public class Work {
    @Expose
    private String team; //
    private String title; //
    private String content; //
    private String date;
    private String field; //
    private String state;

    private int id; //

    public Work(String title, String field, int id) {
        this.title = title;
        this.field = field;
        this.id = id;
    }

    public Work(String team, String title, String content, String date, String field) {
        this.team = team;
        this.title = title;
        this.content = content;
        this.date = date;
        this.field = field;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
