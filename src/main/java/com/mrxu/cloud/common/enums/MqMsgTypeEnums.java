package com.mrxu.cloud.common.enums;

/**
 * @author ifocusing-xujia
 * @since 2017/12/26
 */
public enum MqMsgTypeEnums {
    MRVIDEO("MrVideo"),//cn.marsar.zhongtie.entity.mq.MrVideo
    ZHONGTIE_DG_DATA_LOG("DgDataLog"),//cn.marsar.zhongtie.entity.DgDataLog
    ZHONGTIE_DG_DATA_TAG_LOG("DgDataTagLog"),//cn.marsar.zhongtie.entity.DgDataTagLog
    ;

    private String itemLabel;
    private String itemValue;

    MqMsgTypeEnums(String itemValue){
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
