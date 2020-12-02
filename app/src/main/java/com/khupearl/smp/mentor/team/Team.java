package com.khupearl.smp.mentor.team;

public class Team {
    private String name;
    private String subject;

    public Team(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }
}
