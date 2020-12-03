package com.khupearl.smp.wbs;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Work {
    @Expose
    @SerializedName("fk_team") private String team;

    @Expose
    @SerializedName("title") private String title;

    @Expose
    @SerializedName("content") private String content;

    @Expose
    @SerializedName("date") private String date;

    @Expose
    @SerializedName("field") private String field;

    @Expose
    @SerializedName("state") private String state;

    @Expose
    @SerializedName("id") private int id;

    @Expose
    @SerializedName("success") private boolean success;

    public Work(String title, String field, int id) {
        this.title = title;
        this.field = field;
        this.id = id;
    }

    public Work(String team, String title, String field, int id) {
        this.team = team;
        this.title = title;
        this.field = field;
        this.id = id;
    }

    public Work(String team, String title, String date, String field, int id) {
        this.team = team;
        this.title = title;
        this.date = date;
        this.field = field;
        this.id = id;
    }

    public Work(String team, String title, String content, String date, String field, int id) {
        this.team = team;
        this.title = title;
        this.content = content;
        this.date = date;
        this.field = field;
        this.id = id;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
