package com.mrxu.cloud.common.enums;

/**
 * 视频文件类型枚举
 * @author ifocusing-xujia
 * @since 2017/5/4
 */
public enum VideoFileEnum {
    //微软视频 ：wmv、asf、asx
    wmv("wmv"),
    asf("asf"),
    asx("asx"),
    //Real Player ：rm、 rmvb
    rm("rm"),
    rmvb("rmvb"),
    //MPEG视频 ：mpg、mpeg、mpe
    mpg("mpg"),
    mpeg("mpeg"),
    mpe("mpe"),
    //手机视频 ：3gp
    gp("3gp"),
    //Apple视频 ：mov
    mov("mov"),
    //Sony视频 ：mp4、m4v
    mp4("mp4"),
    m4v("m4v"),
    //其他常见视频：avi、dat、mkv、flv、vob
    avi("avi"),
    dat("dat"),
    mkv("mkv"),
    flv("flv"),
    vob("vob"),
    m3u8("m3u8")
    ;

    private String itemLabel;
    private String itemValue;

    VideoFileEnum(String itemValue){
        this.itemLabel = this.name();
        this.itemValue = itemValue;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public String getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }


    @Override
    public String toString() {
        return this.itemValue;
    }

    public static boolean contains(String value){
        for(VideoFileEnum fileEnum : VideoFileEnum.values()){
            if(fileEnum.itemValue.equalsIgnoreCase(value)){
                return true;
            }
        }
        return false;
    }
}
