package com.mrxu.cloud.common.enums;

/**
 * @author ifocusing-xuzhiwei
 * @since 2017/8/3
 */
public enum ClientInfoEnum {

    clientType("clientType"),
    clientOS("clientOS"),
    clientVersion("clientVersion"),
    clientResolution("clientResolution");

    private String itemLabel;
    private String itemValue;

    ClientInfoEnum(String itemValue) {
        this.itemLabel = this.name();
        this.itemValue = itemValue;
    }

    public String getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public static boolean contains(String value) {
        for (ClientInfoEnum clientInfoEnum : ClientInfoEnum.values()) {
            if (clientInfoEnum.itemValue.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static ClientInfoEnum getEnum(String value) {
        for (ClientInfoEnum clientInfoEnum : ClientInfoEnum.values()) {
            if (clientInfoEnum.itemValue.equals(value)) {
                return clientInfoEnum;
            }
        }
        return null;
    }
}
