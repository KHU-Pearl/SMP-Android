package com.khupearl.smp.mentee;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Mentee {
    @Expose
    @SerializedName("email") private String email;

    @Expose
    @SerializedName("fk_team") private String fk_team;

    @Expose
    @SerializedName("name") private String name;

    @Expose
    @SerializedName("password") private String password;

    @Expose
    @SerializedName("major") private String major;

    @Expose
    @SerializedName("student_id") private int student_id;

    @Expose
    @SerializedName("success") private Boolean success;

    public Boolean getEmpty() {
        return empty;
    }

    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }

    @Expose
    @SerializedName("empty") private Boolean empty;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFk_team() {
        return fk_team;
    }

    public void setFk_team(String fk_team) {
        this.fk_team = fk_team;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }
}
