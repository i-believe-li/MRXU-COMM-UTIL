package com.mrxu.cloud.common.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/3/28
 */
public class MathUtil {
    /**
     * 整数相除 保留一位小数
     * @param a
     * @param b
     * @return
     */
    public static Double division(int a ,int b){
        float num =(float)a/b;
        DecimalFormat df = new DecimalFormat("0.0");
        return Double.valueOf(df.format(num));
    }

    /**
     * 获取图片缩放比例
     * @param filePath
     * @return
     */
    public static Double getImageScale(String filePath){
        File file = new File(filePath);
        FileInputStream fis = null;
        BufferedImage bufferedImg = null;
        try {
            fis = new FileInputStream(file);
            bufferedImg = ImageIO.read(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != fis){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        int imgWidth = bufferedImg.getWidth();
        int imgHeight = bufferedImg.getHeight();
        int maxEdge = imgWidth > imgHeight ? imgWidth : imgHeight;
        return maxEdge > 200 ? MathUtil.division(200, maxEdge) : 1D;
    }

}
