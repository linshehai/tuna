package com.hy.tuna.xml.elements;

public class SelectStatement extends SqlStatement implements ExecutableStatement {


    private String resultMap;


    public String getResultMap() {
        return resultMap;
    }

    public void setResultMap(String resultMap) {
        this.resultMap = resultMap;
    }

}
