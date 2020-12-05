package com.khupearl.smp.mentee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Progress {
    @Expose
    @SerializedName("fk_team") private String fk_team;

    @Expose
    @SerializedName("success") private boolean success;

    @Expose
    @SerializedName("count_todo") private String count_todo;

    @Expose
    @SerializedName("count_inprogress") private String count_inprogress;

    @Expose
    @SerializedName("count_done") private String count_done;

    @Expose
    @SerializedName("progress") private String progress;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCount_todo() {
        return count_todo;
    }

    public void setCount_todo(String count_todo) {
        this.count_todo = count_todo;
    }

    public String getCount_inprogress() {
        return count_inprogress;
    }

    public void setCount_inprogress(String count_inprogress) {
        this.count_inprogress = count_inprogress;
    }

    public String getCount_done() {
        return count_done;
    }

    public void setCount_done(String count_done) {
        this.count_done = count_done;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getFk_team() {
        return fk_team;
    }

    public void setFk_team(String fk_team) {
        this.fk_team = fk_team;
    }
}
