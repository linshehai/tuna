package com.hy.tuna.xml.elements;

public class ResultMappingNode extends AbstractNode{

    private String column;

    private String property;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
