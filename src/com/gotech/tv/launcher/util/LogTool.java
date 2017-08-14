package com.gotech.tv.launcher.util;

import android.util.Log;

/**
 * Log tools.<br>
 * CN:日志工具，便于集中管理日志的打印。
 */
public class LogTool {
    /**
     * Message separator.<br>
     * CN:分割打印的消息.
     */
    public static final String MSG_SEPARATOR = " -- ";

    /**
     * Head of TAG.<br>
     * CN:日志打印的标签头.
     */
    public static final String TAG_PREFIX = "StarViewLauncher";

    /**
     * Output message when message is null.<br>
     * CN:日志的打印输入字符串为空时的替代字符串.
     */
    public static final String MSG_EMPTY = "Empty Msg";

    /**
     * Stack level.<br>
     * CN:线程堆栈等级.
     */
    public static final int STACK_LEVEL = 5;

    /**
     * Whether to print VERBOSE or not.<br>
     * CN:是否打印VERBOSE等级的日志信息.
     */
    public static boolean mVFlag = true;

    /**
     * Whether to print DEBUG or not.<br>
     * CN:是否打印DEBUG等级的日志信息.
     */
    public static boolean mDFlag = true;

    /**
     * Whether to print INFO or not.<br>
     * CN:是否打印INFO等级的日志信息.
     */
    public static boolean mIFlag = true;

    /**
     * Whether to print WARN or not.<br>
     * CN:是否打印WARN等级的日志信息.
     */
    public static boolean mWFlag = true;

    /**
     * Whether to print ERROR or not.<br>
     * CN:是否打印ERROR等级的日志信息.
     */
    public static boolean mEFlag = true;

    /**
     * Print VERBOSE.<br>
     * CN:打印VERBOSE等级的日志信息.
     *
     * @param pMsg - message to be printed.<br>
     *             CN:要打印的消息.
     */
    public static void v(String pMsg) {
        if (mVFlag) {
            Log.v(getFinalTag(), getFinalMsg(pMsg));
        }
    }

    /**
     * Print VERBOSE.<br>
     * CN:打印VERBOSE等级的日志信息.
     *
     * @param pMsg - message to be printed.<br>
     *             CN:要打印的消息.
     */
    public static void v(String TAG, String pMsg) {
        if (mVFlag) {
            Log.v(TAG, getFinalMsg(pMsg));
        }
    }

    /**
     * Print DEBUG.<br>
     * CN:打印DEBUG等级的日志信息.
     *
     * @param pMsg - message to be printed.<br>
     *             CN:要打印的消息.
     */
    public static void d(String pMsg) {
        if (mDFlag) {
            Log.d(getFinalTag(), getFinalMsg(pMsg));
        }
    }

    /**
     * Print DEBUG.<br>
     * CN:打印DEBUG等级的日志信息.
     *
     * @param pMsg - message to be printed.<br>
     *             CN:要打印的消息.
     */
    public static void d(String TAG, String pMsg) {
        if (mDFlag) {
            Log.d(TAG, getFinalMsg(pMsg));
        }
    }

    /**
     * Print INFO.<br>
     * CN:打印INFO等级的日志信息.
     *
     * @param pMsg - message to be printed.<br>
     *             CN:要打印的消息.
     */
    public static void i(String pMsg) {
        if (mIFlag) {
            Log.i(getFinalTag(), getFinalMsg(pMsg));
        }
    }

    /**
     * Print INFO.<br>
     * CN:打印INFO等级的日志信息.
     *
     * @param pMsg - message to be printed.<br>
     *             CN:要打印的消息.
     */
    public static void i(String TAG, String pMsg) {
        if (mIFlag) {
            Log.i(TAG, getFinalMsg(pMsg));
        }
    }

    /**
     * Print WARN.<br>
     * CN:打印WARN等级的日志信息.
     *
     * @param pMsg - message to be printed.<br>
     *             CN:要打印的消息.
     */
    public static void w(String pMsg) {
        if (mEFlag) {
            Log.w(getFinalTag(), getFinalMsg(pMsg));
        }
    }

    /**
     * Print WARN.<br>
     * CN:打印WARN等级的日志信息.
     *
     * @param pMsg - message to be printed.<br>
     *             CN:要打印的消息.
     */
    public static void w(String TAG, String pMsg) {
        if (mEFlag) {
            Log.w(TAG, getFinalMsg(pMsg));
        }
    }

    /**
     * Print WARN.<br>
     * CN:打印WARN等级的日志信息.
     *
     * @param pMsg - message to be printed.<br>
     *             CN:要打印的消息.
     */
    public static void w(String TAG, String pMsg, Throwable t) {
        if (mEFlag) {
            Log.w(TAG, getFinalMsg(pMsg), t);
        }
    }

    /**
     * Print ERROR.<br>
     * CN:打印ERROR等级的日志信息.
     *
     * @param pMsg - message to be printed.<br>
     *             CN:要打印的消息.
     */
    public static void e(String pMsg) {
        if (mWFlag) {
            Log.e(getFinalTag(), getFinalMsg(pMsg));
        }
    }

    /**
     * Print ERROR.<br>
     * CN:打印ERROR等级的日志信息.
     *
     * @param pMsg - message to be printed.<br>
     *             CN:要打印的消息.
     */
    public static void e(String TAG, String pMsg) {
        if (mWFlag) {
            Log.e(TAG, getFinalMsg(pMsg));
        }
    }

    /**
     * Print ERROR.<br>
     * CN:打印ERROR等级的日志信息.
     *
     * @param pMsg - message to be printed.<br>
     *             CN:要打印的消息.
     */
    public static void e(String pMsg, Throwable t) {
        if (mWFlag) {
            Log.e(getFinalTag(), getFinalMsg(pMsg), t);
        }
    }

    /**
     * Print ERROR.<br>
     * CN:打印ERROR等级的日志信息.
     *
     * @param pMsg - message to be printed.<br>
     *             CN:要打印的消息.
     */
    public static void e(String TAG, String pMsg, Throwable t) {
        if (mWFlag) {
            Log.e(TAG, getFinalMsg(pMsg), t);
        }
    }

    private static String getFinalMsg(String pMsg) {
        if (pMsg == null || pMsg == "") {
            pMsg = MSG_EMPTY;
        }

        return getMethodName() + "(" + getLineNumber() + ")" + MSG_SEPARATOR + pMsg;
    }

    private static String getFinalTag() {

        return TAG_PREFIX;
    }

    private static String getLineNumber() {
        return "L" + Thread.currentThread().getStackTrace()[STACK_LEVEL].getLineNumber();
    }

    private static String getMethodName() {
        return Thread.currentThread().getStackTrace()[STACK_LEVEL].getMethodName();
    }

    private static String getClassName() {
        String fullName = Thread.currentThread().getStackTrace()[STACK_LEVEL].getClassName();
        return fullName.substring(fullName.lastIndexOf(".") + 1);
    }
}