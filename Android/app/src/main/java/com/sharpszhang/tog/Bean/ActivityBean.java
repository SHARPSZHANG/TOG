package com.sharpszhang.tog.Bean;

import java.util.Date;

public class ActivityBean {
    public int activityId;
    public String activityTitle;
    public String activityContent;
    public String orgName;
    public int OrgIcon;
    public Date activityStartTime;
    public Date activityEndTime;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getOrgIcon() {
        return OrgIcon;
    }

    public void setOrgIcon(int orgIcon) {
        OrgIcon = orgIcon;
    }

    public Date getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Date activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public Date getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Date activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public ActivityBean(int activityId, String activityTitle, String activityContent, String orgName, int orgIcon, Date activityStartTime, Date activityEndTime) {
        this.activityId = activityId;
        this.activityTitle = activityTitle;
        this.activityContent = activityContent;
        this.orgName = orgName;
        OrgIcon = orgIcon;
        this.activityStartTime = activityStartTime;
        this.activityEndTime = activityEndTime;
    }

    public ActivityBean() {
    }


}
