package com.ruoyi.system.params;

public class ClubParams {
    private Long userId;
    private String position;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public ClubParams() {
    }

    public ClubParams(Long userId, String position) {
        this.userId = userId;
        this.position = position;
    }
}
