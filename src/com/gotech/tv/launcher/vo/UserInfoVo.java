package com.gotech.tv.launcher.vo;

public class UserInfoVo {
    public String mUserName;
    public String mPassword;

    public UserInfoVo() {

    }

    public UserInfoVo(String mUserName, String mPassword) {
        this.mUserName = mUserName;
        this.mPassword = mPassword;
    }

    @Override
    public String toString() {
        return mUserName;
    }
}
