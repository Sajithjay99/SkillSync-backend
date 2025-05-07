package com.springbootacademy.skill_sync.dto;

import java.util.Date;

public class ProgressUpdateDTO {

    private String skillTitle;
    private String skillUrl;
    private String description;
    private String field;
    private Date startDate;
    private Date endDate;
    private int progress;


  
   
    public ProgressUpdateDTO(String skillTitle, String skillUrl, String description, String field, Date startDate, Date endDate, int progress) {
        this.skillTitle = skillTitle;
        this.skillUrl = skillUrl;
        this.description = description;
        this.field = field;
        this.startDate = startDate;
        this.endDate = endDate;
        this.progress = progress;
    }


    // Getters and setters
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
