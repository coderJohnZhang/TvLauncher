/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\Administrator\\Desktop\\TvLauncher\\src\\com\\dvb\\service\\IDvbService.aidl
 */
package com.dvb.service;
public interface IDvbService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.dvb.service.IDvbService
{
private static final java.lang.String DESCRIPTOR = "com.dvb.service.IDvbService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.dvb.service.IDvbService interface,
 * generating a proxy if needed.
 */
public static com.dvb.service.IDvbService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.dvb.service.IDvbService))) {
return ((com.dvb.service.IDvbService)iin);
}
return new com.dvb.service.IDvbService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_startInitDvbModule:
{
data.enforceInterface(DESCRIPTOR);
this.startInitDvbModule();
reply.writeNoException();
return true;
}
case TRANSACTION_doSystemCmd:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
int _result = this.doSystemCmd(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getCurstomerID:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getCurstomerID();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_isFactorySW:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.isFactorySW();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getSerialNumber:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getSerialNumber();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getFullSN:
{
data.enforceInterface(DESCRIPTOR);
byte[] _result = this.getFullSN();
reply.writeNoException();
reply.writeByteArray(_result);
return true;
}
case TRANSACTION_getEncryptSN:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getEncryptSN();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_setPowerAction:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.setPowerAction(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getTimeZones:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String[] _result = this.getTimeZones();
reply.writeNoException();
reply.writeStringArray(_result);
return true;
}
case TRANSACTION_getCurZoneIndex:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getCurZoneIndex();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_saveTimeZone:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.saveTimeZone(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getOutRange:
{
data.enforceInterface(DESCRIPTOR);
android.graphics.Rect _result = this.getOutRange();
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_setOSDArea:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
int _arg3;
_arg3 = data.readInt();
this.setOSDArea(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_getHdmiIndex:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getHdmiIndex();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_setHdmiIndex:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.setHdmiIndex(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_setFpanelDisplayName:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.setFpanelDisplayName(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_setFpanelDisplayTimeAuto:
{
data.enforceInterface(DESCRIPTOR);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.setFpanelDisplayTimeAuto(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.dvb.service.IDvbService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void startInitDvbModule() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_startInitDvbModule, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public int doSystemCmd(java.lang.String cmd) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(cmd);
mRemote.transact(Stub.TRANSACTION_doSystemCmd, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getCurstomerID() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getCurstomerID, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean isFactorySW() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isFactorySW, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getSerialNumber() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getSerialNumber, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public byte[] getFullSN() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
byte[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getFullSN, _data, _reply, 0);
_reply.readException();
_result = _reply.createByteArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getEncryptSN() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getEncryptSN, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setPowerAction(int action) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(action);
mRemote.transact(Stub.TRANSACTION_setPowerAction, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public java.lang.String[] getTimeZones() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getTimeZones, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getCurZoneIndex() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getCurZoneIndex, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void saveTimeZone(int index) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(index);
mRemote.transact(Stub.TRANSACTION_saveTimeZone, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public android.graphics.Rect getOutRange() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.graphics.Rect _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getOutRange, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = android.graphics.Rect.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setOSDArea(int l, int t, int r, int b) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(l);
_data.writeInt(t);
_data.writeInt(r);
_data.writeInt(b);
mRemote.transact(Stub.TRANSACTION_setOSDArea, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public int getHdmiIndex() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getHdmiIndex, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setHdmiIndex(int index) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(index);
mRemote.transact(Stub.TRANSACTION_setHdmiIndex, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setFpanelDisplayName(java.lang.String name) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(name);
mRemote.transact(Stub.TRANSACTION_setFpanelDisplayName, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setFpanelDisplayTimeAuto(boolean enable) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((enable)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setFpanelDisplayTimeAuto, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_startInitDvbModule = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_doSystemCmd = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getCurstomerID = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_isFactorySW = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_getSerialNumber = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_getFullSN = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_getEncryptSN = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_setPowerAction = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_getTimeZones = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_getCurZoneIndex = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_saveTimeZone = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_getOutRange = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_setOSDArea = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_getHdmiIndex = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_setHdmiIndex = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_setFpanelDisplayName = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
static final int TRANSACTION_setFpanelDisplayTimeAuto = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
}
public void startInitDvbModule() throws android.os.RemoteException;
public int doSystemCmd(java.lang.String cmd) throws android.os.RemoteException;
public int getCurstomerID() throws android.os.RemoteException;
public boolean isFactorySW() throws android.os.RemoteException;
public java.lang.String getSerialNumber() throws android.os.RemoteException;
public byte[] getFullSN() throws android.os.RemoteException;
public java.lang.String getEncryptSN() throws android.os.RemoteException;
public void setPowerAction(int action) throws android.os.RemoteException;
public java.lang.String[] getTimeZones() throws android.os.RemoteException;
public int getCurZoneIndex() throws android.os.RemoteException;
public void saveTimeZone(int index) throws android.os.RemoteException;
public android.graphics.Rect getOutRange() throws android.os.RemoteException;
public void setOSDArea(int l, int t, int r, int b) throws android.os.RemoteException;
public int getHdmiIndex() throws android.os.RemoteException;
public void setHdmiIndex(int index) throws android.os.RemoteException;
public void setFpanelDisplayName(java.lang.String name) throws android.os.RemoteException;
public void setFpanelDisplayTimeAuto(boolean enable) throws android.os.RemoteException;
}
