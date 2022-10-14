package com.sharpszhang.tog.Bean;

public class UserContent {
    private String imgPath;
    private String userName;
    private String nickName;
    private String signature;
    private String gender;
    private String city;

    public UserContent() {
    }

    public UserContent(String imgPath, String userName, String nickName, String signature, String gender, String city) {
        this.imgPath = imgPath;
        this.userName = userName;
        this.nickName = nickName;
        this.signature = signature;
        this.gender = gender;
        this.city = city;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "imgPath='" + imgPath + '\'' +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", signature='" + signature + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public String getImgPath() {
        return imgPath;
    }

    public String getUserName() {
        return userName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getSignature() {
        return signature;
    }

    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }
}
