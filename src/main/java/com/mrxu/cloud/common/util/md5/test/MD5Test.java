package com.mrxu.cloud.common.util.md5.test;


import com.mrxu.cloud.common.util.md5.MD5;

import java.io.File;
import java.io.IOException;

/**
 * @author ifocusing-xuzhiwei
 * @since 2017/11/21
 */
public class MD5Test {
    public static void main(String[] args) {
        String filename = "E:\\tmp\\help_video\\1.mp4";
        try {
            String hash = MD5.asHex(MD5.getHash(new File(filename)));
            System.out.println("hash: " + hash);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
