package com.yelot.crm.entity;

/**
 * Created by kee on 17/5/26.
 */
public class Attribute {
    private Long id;
    /**
     * 熟悉名称,属性值，唯一
     */
    private String attributeName;

    private String selectionValues;
    /**
     * 属性类别：1：下拉选择，2，文本输入框
     */
    private int type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getSelectionValues() {
        return selectionValues;
    }

    public void setSelectionValues(String selectionValues) {
        this.selectionValues = selectionValues;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
