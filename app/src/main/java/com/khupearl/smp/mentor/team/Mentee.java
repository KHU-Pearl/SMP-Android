package com.khupearl.smp.mentor.team;

public class Mentee {
    private String name;
    private String subject;

    public Mentee(String name, String subject) {
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
