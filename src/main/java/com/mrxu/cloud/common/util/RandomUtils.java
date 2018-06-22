package com.mrxu.cloud.common.util;

import java.util.Random;

/**
 * @author ifcousing-linfeng
 * @since 2017/4/18
 */
public class RandomUtils {

    /**
     * 生成指定长度
     *
     * @param length 指定长度
     * @return 随机字符串
     */
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getRandNum() {
        Random rad = new Random();
        return String.valueOf(rad.nextInt(1000000));
    }

}
