package com.mrxu.cloud.common.enums;

/**
 * 操作类型枚举
 * @author ifocusing-xuzhiwei
 * @since 2017/9/12
 */
public enum OperationTypeEnum {

    View("view"),//看
    Create("create"),//创建
    Edit("edit"),//编辑
    Delete("delete");//删除

    private String itemLabel;
    private String itemValue;

    OperationTypeEnum(String itemValue){
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
        for(OperationTypeEnum operationTypeEnum : OperationTypeEnum.values()){
            if(operationTypeEnum.itemValue.equals(value)){
                return true;
            }
        }
        return false;
    }
}
