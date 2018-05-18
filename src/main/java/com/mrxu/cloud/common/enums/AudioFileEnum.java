package com.mrxu.cloud.common.enums;

/**
 * 音频文件类型枚举
 * @author ifocusing-xujia
 * @since 2017/5/4
 */
public enum AudioFileEnum {
    wav("wav"),
    mp3("mp3"),
    wma("wma"),
    ogc("ogc"),
    ape("ape"),
    acc("acc")
            ;

    private String itemLabel;
    private String itemValue;

    AudioFileEnum(String itemValue){
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
        for(AudioFileEnum fileEnum : AudioFileEnum.values()){
            if(fileEnum.itemValue.equals(value)){
                return true;
            }
        }
        return false;
    }
}
