package com.yelot.crm.Util;

/**
 * @author kee
 */
public class NumberUtils {
    /**
     * 生成 num 位随机数
     *
     * @param num 位数
     * @return
     */
    public static String generateRandom(int num) {
        Integer x = (int) (Math.random() * Math.pow(10, num));
        return String.format("%04d", x);
    }

}
