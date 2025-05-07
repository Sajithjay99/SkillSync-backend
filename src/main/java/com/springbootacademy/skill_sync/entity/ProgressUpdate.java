package com.springbootacademy.skill_sync.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "progress_updates")
public class ProgressUpdate {

    @Id
    private String id;

    private String skillTitle;
    private String skillUrl;
    private String description;
    private String field;
    private Date startDate;
    private Date endDate;
    private int progress;

    // Constructors, getters, and setters
    public ProgressUpdate() {}

    public ProgressUpdate(String id, String skillTitle, String skillUrl, String description,
                          String field, Date startDate, Date endDate, int progress) {
        this.id = id;
        this.skillTitle = skillTitle;
        this.skillUrl = skillUrl;
        this.description = description;
        this.field = field;
        this.startDate = startDate;
        this.endDate = endDate;
        this.progress = progress;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkillTitle() {
        return skillTitle;
    }

    public void setSkillTitle(String skillTitle) {
        this.skillTitle = skillTitle;
    }

    public String getSkillUrl() {
        return skillUrl;
    }

    public void setSkillUrl(String skillUrl) {
        this.skillUrl = skillUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
