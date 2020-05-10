package com.hy.tuna.builder.scanner;

import com.hy.tuna.Configuration;

import java.io.IOException;

public class AnnotationObjectScanner implements ObjectScanner {


    private String basePackage;

    public AnnotationObjectScanner(String basePackage) {
        this.basePackage=basePackage;
    }

    @Override
    public void scan() throws IOException {

    }
}
