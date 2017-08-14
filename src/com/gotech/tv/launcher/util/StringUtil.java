package com.gotech.tv.launcher.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class StringUtil {

    /**
     * 获取源关键字
     *
     * @return
     */
    public static String getSourceKeyName(String sourceName) {
        if (sourceName.contains("华数")) {
            return "华数";
        } else if (sourceName.contains("优酷") || sourceName.contains("youku")) {
            return "优酷";
        } else if (sourceName.contains("风行") || sourceName.contains("funshion")) {
            return "风行";
        } else if (sourceName.contains("凤凰") || sourceName.contains("ifeng")) {
            return "凤凰";
        } else if (sourceName.contains("CNTV")) {
            return "CNTV";
        } else if (sourceName.contains("乐视") || sourceName.contains("letv")) {
            return "乐视";
        } else if (sourceName.contains("电影网") || sourceName.contains("dianying") || sourceName.contains("M1905")) {
            return "M1905";
        } else if (sourceName.contains("PPS") || sourceName.contains("pps")) {
            return "PPS";
        } else if (sourceName.contains("PPTV") || sourceName.contains("pptv")) {
            return "PPTV";
        } else if (sourceName.contains("奇艺") || sourceName.contains("奇异") || sourceName.contains("qiyi")) {
            return "奇艺";
        } else if (sourceName.contains("搜狐") || sourceName.contains("sohu")) {
            return "搜狐";
        } else if (sourceName.contains("上海文广") || sourceName.contains("SMG") || sourceName.contains("smg")) {
            return "上海文广";
        } else if (sourceName.contains("腾讯") || sourceName.contains("QQ") || sourceName.contains("qq")) {
            return "腾讯";
        } else if (sourceName.contains("酷米") || sourceName.contains("kumi")) {
            return "酷米";
        } else if (sourceName.contains("TV189") || sourceName.contains("天翼") || sourceName.contains("tv189")) {
            return "天翼";
        } else {
            return "其它";
        }
    }

    /**
     * 获取源关键字排序号
     *
     * @return
     */
    public static int getSourceKeyNameIndex(String sourceName, String chooseName) {

        int index = 0;
        if (!chooseName.equals("") && sourceName.equals(chooseName)) {
            index = 0;
        } else if (sourceName.equals("搜狐")) {
            index = 1;
        } else if (sourceName.equals("PPTV")) {
            index = 2;
        } else if (sourceName.equals("风行")) {
            index = 3;
        } else if (sourceName.equals("优酷")) {
            index = 4;
        } else if (sourceName.equals("奇艺")) {
            index = 5;
        } else if (sourceName.equals("乐视")) {
            index = 6;
        } else if (sourceName.equals("CNTV")) {
            index = 7;
        } else if (sourceName.equals("腾讯")) {
            index = 8;
        } else if (sourceName.equals("PPS")) {
            index = 9;
        } else if (sourceName.equals("华数")) {
            index = 10;
        } else if (sourceName.equals("酷米")) {
            index = 11;
        } else if (sourceName.equals("凤凰")) {
            index = 12;
        } else if (sourceName.equals("M1905")) {
            index = 13;
        } else if (sourceName.equals("上海文广")) {
            index = 14;
        } else if (sourceName.equals("天翼")) {
            index = 15;
        } else {
            index = 16;
        }
        return index;
    }

    /***
     * 获取总时长
     *
     * @param url
     * @return
     */
    public static int getTotalDuration(String url) {

        try {
            String startTimePrefix = "startTime=";
            String endTimePrefix = "endTime=";
            int startTimeIdx = url.indexOf(startTimePrefix);
            int endTimeIdx = url.indexOf(endTimePrefix);

            long mTVodStartTime = getLongNum(url.substring(startTimeIdx + startTimePrefix.length()));
            long mTVodEndTime = getLongNum(url.substring(endTimeIdx + endTimePrefix.length()));
            int re = (int) (mTVodEndTime - mTVodStartTime);
            if (re > 0) {
                return re;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static long getLongNum(final String str) {
        if (str == null)
            return 0;

        String numStr = "";
        for (int i = 0; i < str.length(); i++) {
            char j = str.charAt(i);
            if (j > 47 && j < 58) {
                numStr = numStr + j;
            } else {
                break;
            }
        }

        if (numStr.length() > 0) {
            return Long.parseLong(numStr);
        }
        else {
            return 0;
        }
    }

    public static int getNum(final String str) {
        if (str == null) {
            return 0;
        }

        String numStr = "";
        for (int i = 0; i < str.length(); i++) {
            char j = str.charAt(i);
            if (j > 47 && j < 58) {
                numStr = numStr + j;
            } else {
                break;
            }
        }

        if (numStr.length() > 0) {
            return Integer.parseInt(str);
        }
        else {
            return 0;
        }
    }

    public static String updateUrlParam(String url, final String name, final String value) {

        try {
            if (url == null) {
                return "";
            }
            String nameValue = name + "=" + value;
            int startIdx = url.indexOf("?" + name + "=");
            if (startIdx >= 0) {

                String subSequence;
                int endIdx = url.indexOf("&", startIdx + name.length() + 2);
                if (endIdx >= 0)
                    subSequence = url.substring(startIdx, endIdx);
                else
                    subSequence = url.substring(startIdx);

                url = url.replaceFirst(subSequence, "?" + nameValue);

            } else {
                startIdx = url.indexOf("&" + name + "=");
                if (startIdx >= 0) {
                    String subSequence;
                    int endIdx = url.indexOf("&", startIdx + name.length() + 2);
                    if (endIdx >= 0) {
                        subSequence = url.substring(startIdx, endIdx);
                    }
                    else {
                        subSequence = url.substring(startIdx);
                    }

                    url = url.replaceFirst(subSequence, "&" + nameValue);

                } else if (url.contains("?")) {
                    url = url + "&" + nameValue;
                } else {
                    url = url + "?" + nameValue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }

    public static int length(String value, int chineseLength, int englishLength) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
			/* 获取一个字符 */
            String temp = value.substring(i, i + 1);
			/* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
				/* 中文字符长度为2 */
                // valueLength += 2;
                valueLength += chineseLength;
            } else {
				/* 其他字符长度为1 */
                // valueLength += 1;
                valueLength += englishLength;
            }
        }
        return valueLength;
    }

    /**
     * 获取密码加码
     *
     * @param msg
     * @return
     */
    public static String generatorPassword(String msg) {
        return String.valueOf((msg.hashCode() + "tvlauncher").hashCode());
    }

    /**
     * 获取指定范围的随机数（字符串格式）
     *
     * @return 的随机数
     */
    public static long getRadomNumber(long _min, long _max) {

        Random rand = new Random();
        long seed = Math.abs(rand.nextLong()) % (_max - _min) + _min;

        return seed;
    }

    /**
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }

        return weekDays[w];
    }

    /**
     * 获取时间
     *
     * @return
     */
    public static String getDayStr() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        String str;
        if (hour >= 6 && hour < 8) {
            str = "早上";
        } else if (hour >= 8 && hour < 11) {
            str = "上午";
        } else if (hour >= 11 && hour < 13) {
            str = "中午";
        } else if (hour >= 13 && hour < 18) {
            str = "下午";
        } else {
            str = "晚上";
        }
        SimpleDateFormat format_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("HH:mm", Locale.CHINA);
        str = str + " " + format_YYYY_MM_DD_HH_MM_SS.format(new Date());
        return str;

    }

    /**
     * 获取日期 格式 2011年11年11年
     *
     * @return
     */
    public static String getDateStr() {
        String str = "";
        SimpleDateFormat format_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        str = str + " " + format_YYYY_MM_DD_HH_MM_SS.format(new Date());
        return str;

    }

    /**
     * 获取时间
     *
     * @return
     */
    public static String getTimeStr() {
        String str;
        SimpleDateFormat format_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("HH:mm", Locale.CHINA);
        str = format_YYYY_MM_DD_HH_MM_SS.format(new Date());
        return str;

    }

    /**
     * 获取到下载文件的后缀名
     *
     * @param reqUrl
     * @return
     */
    private static String getFileSuffix(String reqUrl) {
        if (reqUrl.lastIndexOf("/") != -1) {
            reqUrl = reqUrl.substring(reqUrl.lastIndexOf("/"));
            if (reqUrl.lastIndexOf(".") != -1) {
                reqUrl = reqUrl.substring(reqUrl.lastIndexOf("."));
                return reqUrl;
            }
        }
        return "";
    }

    public final static String generatePwdByUser(String userName) {
        return Math.abs(MD5("tvlauncher_" + userName).hashCode()) + "";
    }

    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取时间格
     *
     * @return
     */
    public static String getNowTime(Date now) {
        SimpleDateFormat format_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
        String str = format_YYYY_MM_DD_HH_MM_SS.format(now);
        return str;
    }

    /**
     * 计算两个日期的时间差 大于30秒返回true 否 返回false
     *
     * @param before
     * @param now
     * @return
     */
    public static boolean getTimeDifference(Date now, Date before) {

        long l = 0;
        if (before != null && now != null) {
            l = now.getTime() - before.getTime();
        }
        if (l > 0 && l / 1000 > 30) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 计算两个日期的时间差
     *
     * @param now
     * @param before
     * @return
     */
    public static int getTimeDifferenceByInt(Date now, Date before) {
        if (before != null && now != null) {
            long l = now.getTime() - before.getTime();
            return (int) (l / 1000);
        }
        return 0;
    }

    /**
     * 显示Toast
     *
     * @param c
     */
    public static void showToast(Context c, String message, int showType) {
        Toast toast = Toast.makeText(c, message, showType);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }

}
