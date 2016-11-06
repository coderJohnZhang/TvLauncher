package com.gotech.tv.launcher.vo;

public class UserInfoVo
{
	private String mUserName = "";
	private String mPassword = "";

	public UserInfoVo()
	{
	}

	public UserInfoVo(String mUserName, String mPassword)
	{
		setUserName(mUserName);
		setPassword(mPassword);
	}

	public String getUserName()
	{
		return mUserName;
	}

	public void setUserName(String mUserName)
	{
		this.mUserName = mUserName;
	}

	public String getPassword()
	{
		return mPassword;
	}

	public void setPassword(String mPassword)
	{
		this.mPassword = mPassword;
	}

	@Override
	public String toString()
	{
		return mUserName;
	}
}
