package com.hy.tuna.builder.scanner;

import com.hy.tuna.utils.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAnnotationScanner implements ObjectScanner{

    private String basePackage;

    @Override
    public void scan() throws IOException {
        URL url = this.getClass().getResource("/");
        List<StringBuilder> paths = new ArrayList<>();
        StringBuilder currentPath = new StringBuilder();
        boolean allPackage = false;
        //com.hy.*.entity.*,即com.hy下的子包下的entity下的所有类
        if(StringUtils.hasText(basePackage)){
            String[] subPacks = basePackage.split("\\.");
            for(String sub:subPacks){
                if(!sub.equals("**")&&!sub.equals("*")){
                    currentPath.append("/").append(sub);
                }else{
                    //currentPath目录下的所有包以及子包
                    if(sub.equals("**")){
                        allPackage = true;
                        break;
                    }else if(sub.equals("*")){
                        //当前目录下的子包
                        if(paths.size()>0){
                            paths.stream().forEach(p->{
                                try {
                                    Files.list(Paths.get(currentPath.toString())).forEach(e->{
                                        p.append("/").append(e.getFileName());
                                    });
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            });
                        }else{
                            Files.list(Paths.get(currentPath.toString())).forEach(e->{
                               paths.add(new StringBuilder(e.toString()));
                            });
                        }

                    }
                }
            }
            doScan();
        }
    }

    protected abstract void doScan();
}
