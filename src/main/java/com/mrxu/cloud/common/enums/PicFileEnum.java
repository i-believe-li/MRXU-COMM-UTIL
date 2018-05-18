package com.mrxu.cloud.common.enums;

/**
 * 图片类型枚举
 * @author ifocusing-xujia
 * @since 2017/5/4
 */
public enum PicFileEnum {
    //bmp,jpg,png,tiff,gif,pcx,tga,exif,fpx,svg,psd,cdr,pcd,dxf,ufo,eps,ai,raw,WMF
    bmp("bmp"),
    jpg("jpg"),
    jpeg("jpeg"),
    png("png"),
    tiff("tiff"),
    gif("gif"),
    pcx("pcx"),
    tga("tga"),
    exif("exif"),
    fpx("fpx"),
    svg("svg"),
    psd("psd"),
    cdr("cdr"),
    pcd("pcd"),
    dxf("dxf"),
    ufo("ufo"),
    eps("eps"),
    ai("ai"),
    raw("raw"),
    wmf("wmf")
    ;

    private String itemLabel;
    private String itemValue;

    PicFileEnum(String itemValue){
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
        for(PicFileEnum fileEnum : PicFileEnum.values()){
            if(fileEnum.itemValue.equalsIgnoreCase(value)){
                return true;
            }
        }
        return false;
    }
}
