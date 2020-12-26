package com.khupearl.smp.notice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notice {
    @Expose
    @SerializedName("fk_email") private String fk_email;

    @Expose
    @SerializedName("fk_team") private String fk_team;

    @Expose
    @SerializedName("id") private String id;

    @Expose
    @SerializedName("title") private String title;

    @Expose
    @SerializedName("content") private String content;

    @Expose
    @SerializedName("created") private String created;

    @Expose
    @SerializedName("success") private Boolean success;

    public String getFk_email() {
        return fk_email;
    }

    public void setFk_email(String fk_email) {
        this.fk_email = fk_email;
    }

    public String getFk_team() {
        return fk_team;
    }

    public void setFk_team(String fk_team) {
        this.fk_team = fk_team;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
