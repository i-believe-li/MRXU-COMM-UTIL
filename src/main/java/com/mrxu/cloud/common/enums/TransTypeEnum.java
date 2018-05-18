package com.mrxu.cloud.common.enums;

/**
 * @author ifocusing-xuzhiwei
 * @since 2017/11/21
 */
public enum TransTypeEnum {

    thumbnail("thumbnail"),
    m3u8("m3u8"),
    ts("ts"),
    flv("flv"),
    mp4("mp4"),
    picAlbum("picAlbum");
    ;

    private String itemLabel;
    private String itemValue;

    TransTypeEnum(String itemValue){
        this.itemLabel = this.name();
        this.itemValue = itemValue;
    }

    public String getItemValue() {
        return itemValue;
    }

    public String getItemLabel() {
        return itemLabel;
    }

    @Override
    public String toString() {
        return this.itemValue;
    }

    public static boolean contains(String value){
        for(TransTypeEnum transcodeTypeEnum : TransTypeEnum.values()){
            if(transcodeTypeEnum.itemValue.equals(value)){
                return true;
            }
        }
        return false;
    }

    /**
     * 根据类型获取枚举
     * @param type
     * @return
     */
    public static TransTypeEnum findTransType(String type){
        for(TransTypeEnum transTypeEnum : TransTypeEnum.values()){
            if(transTypeEnum.itemValue.toLowerCase().equals(type.toLowerCase())){
                return transTypeEnum;
            }
        }
        return null;
    }
}
