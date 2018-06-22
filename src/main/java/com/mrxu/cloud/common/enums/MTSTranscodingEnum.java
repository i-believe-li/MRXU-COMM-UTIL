package com.mrxu.cloud.common.enums;

/**
 * @author ifocusing-xujia
 * @since 2017/5/11
 */
public enum MTSTranscodingEnum {
    flv("flv"),
    m3u8("m3u8"),
    mp4("mp4"),
    mp3("mp3")
    ;

    private String itemLabel;
    private String itemValue;

    MTSTranscodingEnum(String itemValue){
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
}
