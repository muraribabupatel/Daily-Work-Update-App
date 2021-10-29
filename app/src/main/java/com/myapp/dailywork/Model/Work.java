package com.myapp.dailywork.Model;

public class Work {
    private String date;
    private String name;
    private String activityName;
    private String dayOutput;
    private String link;
    private String comment;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDayOutput() {
        return dayOutput;
    }

    public void setDayOutput(String dayOutput) {
        this.dayOutput = dayOutput;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
