package com.dvb.service;

interface IDvbService 
{
	void startInitDvbModule();
	int doSystemCmd(String cmd);
	int getCurstomerID();
	boolean isFactorySW();
	String getSerialNumber();
	byte[] getFullSN();
	String getEncryptSN();
	void setPowerAction(int action);
	String[] getTimeZones();
	int getCurZoneIndex();
	void saveTimeZone(int index);
	Rect getOutRange();
	void setOSDArea(int l, int t, int r, int b);
	int getHdmiIndex();
	void setHdmiIndex(int index);
	void setFpanelDisplayName(String name);
	void setFpanelDisplayTimeAuto(boolean enable);
}