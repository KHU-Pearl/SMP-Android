package com.khupearl.smp.mentor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.khupearl.smp.team.Team;

import java.util.ArrayList;
import java.util.List;

public class Mentor {
    @Expose
    @SerializedName("email") private String email;

    @Expose
    @SerializedName("name") private String name;

    @Expose
    @SerializedName("password") private String password;

    @Expose
    @SerializedName("teams") private List<Team> teams = new ArrayList<Team>();

    @Expose
    @SerializedName("success") private Boolean success;

    @Expose
    @SerializedName("empty") private Boolean empty;

    public Boolean getEmpty() {
        return empty;
    }

    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
