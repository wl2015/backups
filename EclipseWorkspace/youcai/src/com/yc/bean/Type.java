package com.yc.bean;
/**
 * 菜品/套餐类别实体类
 * @author Administrator
 *
 */
public class Type {
    
    private int typeId;
    private String typeName;//类别名称
    private int target;//所属类型：1代表菜品 2代表套餐
    public int getTypeId() {
        return typeId;
    }
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public int getTarget() {
        return target;
    }
    public void setTarget(int target) {
        this.target = target;
    }
    
}
