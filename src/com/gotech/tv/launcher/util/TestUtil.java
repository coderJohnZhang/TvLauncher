package com.gotech.tv.launcher.util;

import java.util.Random;

public class TestUtil {

    public static String getRandamName() {
        long i = getRandomNumber(1, 10);
        if (i == 1) {
            return "功夫熊猫";
        } else if (i == 2) {
            return "盗梦空间";
        } else if (i == 3) {
            return "记忆裂痕";
        } else if (i == 4) {
            return "生死停留";
        } else if (i == 2) {
            return "死亡幻觉";
        } else if (i == 5) {
            return "禁闭岛";
        } else if (i == 6) {
            return "穆赫兰道";
        } else if (i == 7) {
            return "蝴蝶效应";
        } else if (i == 8) {
            return "恐怖游轮";
        } else {
            return "恐怖游轮";
        }

    }

    /**
     * 获取指定范围的随机数（字符串格式）
     *
     * @return 的随机数
     */
    public static long getRandomNumber(long _min, long _max) {

        Random rand = new Random();
        long seed = Math.abs(rand.nextLong()) % (_max - _min) + _min;

        return seed;
    }

    public static String getRandamImage() {
        long i = getRandomNumber(1, 5);
        return "http://192.168.1.160:8180/demo/demo" + i + ".jpg";

    }

    public static String getRandamImage2() {
        long i = getRandomNumber(1, 5);
        return "http://192.168.1.160:8180/demo2/demo" + i + ".jpg";

    }
}
