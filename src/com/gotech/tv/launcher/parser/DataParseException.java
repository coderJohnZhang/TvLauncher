package com.gotech.tv.launcher.parser;

public class DataParseException extends Exception {

    private static final long serialVersionUID = 1L;
    private int mErrorCode;
    private String mErrorMsg = "";

    public DataParseException(int errorCode) {
        super();
        mErrorCode = errorCode;
    }

    public DataParseException(int errorCode, String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
        mErrorCode = errorCode;
        mErrorMsg = detailMessage;
    }

    public DataParseException(int errorCode, String detailMessage) {
        super(detailMessage);
        mErrorCode = errorCode;
        mErrorMsg = detailMessage;
    }

    public DataParseException(int errorCode, Throwable throwable) {
        super(throwable);
        mErrorCode = errorCode;
        mErrorMsg = throwable.getMessage();
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public String getErrorMsg() {
        return mErrorMsg;
    }
}
