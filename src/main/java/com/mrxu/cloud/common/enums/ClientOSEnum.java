package com.mrxu.cloud.common.enums;

/**
 * @author ifocusing-xujia
 * @since 2017/4/24
 */
public enum ClientOSEnum {
    holographic("holographic"),
    pc("windows"),
    ios("ios"),
    android("android");

    private String itemLabel;
    private String itemValue;

    ClientOSEnum(String itemValue) {
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

    public static boolean contains(String value) {
        String val = value.toLowerCase();
        for (ClientOSEnum deviceTypeEnum : ClientOSEnum.values()) {
            if (val.contains(deviceTypeEnum.itemValue)) {
                return true;
            }
        }
        return false;
    }

    public static ClientOSEnum getEnum(String value) {
        String val = value.toLowerCase();
        for (ClientOSEnum deviceTypeEnum : ClientOSEnum.values()) {
            if (val.contains(deviceTypeEnum.itemValue)) {
                return deviceTypeEnum;
            }
        }
        return null;
    }
}
