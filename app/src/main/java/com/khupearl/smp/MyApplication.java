package com.khupearl.smp;

import android.app.Application;

public class MyApplication extends Application {
    private String login_type; //(mentor, mentee)
    private String email;

    public String getLogin_type() {
        return login_type;
    }

    public void setLogin_type(String login_type) {
        this.login_type = login_type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
