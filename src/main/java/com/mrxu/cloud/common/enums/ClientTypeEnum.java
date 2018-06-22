package com.mrxu.cloud.common.enums;

/**
 * @author ifocusing-xujia
 * @since 2017/4/24
 */
public enum ClientTypeEnum {
    pc("pc"),
    glass("glass"),
    phone("phone"),
    tablet("tablet");

    private String itemLabel;
    private String itemValue;

    ClientTypeEnum(String itemValue) {
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
        for (ClientTypeEnum deviceTypeEnum : ClientTypeEnum.values()) {
            if (deviceTypeEnum.itemValue.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static ClientTypeEnum getEnum(String value) {
        for (ClientTypeEnum deviceTypeEnum : ClientTypeEnum.values()) {
            if (deviceTypeEnum.itemValue.equals(value)) {
                return deviceTypeEnum;
            }
        }
        return null;
    }
}
