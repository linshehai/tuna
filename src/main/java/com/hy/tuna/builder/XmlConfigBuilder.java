package com.hy.tuna.builder;

import com.alibaba.druid.pool.DruidDataSource;
import com.hy.tuna.Configuration;
import com.hy.tuna.exceptions.TunaParseException;
import com.hy.tuna.utils.ReflectUtil;
import com.hy.tuna.utils.StringUtils;
import com.hy.tuna.xml.TunaXmlParser;
import com.hy.tuna.xml.elements.Node;
import com.hy.tuna.xml.elements.config.Config;
import com.hy.tuna.xml.elements.config.DataSourceNode;
import com.hy.tuna.xml.elements.config.ResourceNode;
import com.hy.tuna.xml.elements.mapper.MapperNode;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class XmlConfigBuilder implements ObjectBuilder {

    private String configLocation;

    private Configuration configuration;

    public XmlConfigBuilder(Configuration configuration, String configLocation) {

        this.configuration = configuration;
        this.configLocation = configLocation;
    }

    @Override
    public Map build() throws Exception {
        if(StringUtils.isEmpty(configLocation)){
            throw new TunaParseException("configuration location is required");
        }
        TunaXmlParser tunaXmlParser = new TunaXmlParser();
        try(InputStream inputStream = this.getClass().getResourceAsStream(this.configLocation)){
            tunaXmlParser.parse(inputStream);
        }
        Config config = (Config) tunaXmlParser.getRoot();
        List<Node> nodeList = config.getNodeList();

        for(Node node:nodeList){

            if(node instanceof ResourceNode){
                ResourceNode resourceNode = (ResourceNode) node;
                String location = resourceNode.getLocation();
                //解析mapper文件
                try(InputStream inputStream = this.getClass().getResourceAsStream(location)) {
                    tunaXmlParser.parse(inputStream);
                }
                MapperNode mapperNode = (MapperNode) tunaXmlParser.getRoot();
                List<Node> statementList = mapperNode.getNodeList();
                statementList.forEach(elem-> configuration.addMapping(mapperNode.getNamespace(), elem));
                //将mapper添加到配置中心
                this.configuration.addMapper(mapperNode.getNamespace());
            }else if(node instanceof DataSourceNode){
                DataSourceNode dataSourceNode = (DataSourceNode) node;
                Class<DataSource> dataSourceClass = ReflectUtil.resolve(dataSourceNode.getDataSourceType());
                DataSource dataSource = dataSourceClass.newInstance();
                ReflectUtil.invoke("setUsername",dataSource,new Class[]{String.class},dataSourceNode.getUsername());
                ReflectUtil.invoke("setPassword",dataSource,new Class[]{String.class},dataSourceNode.getPassword());
                ReflectUtil.invoke("setUrl",dataSource,new Class[]{String.class},dataSourceNode.getUrl());
                ReflectUtil.invoke("setDriverClassName",dataSource,new Class[]{String.class},dataSourceNode.getDriverClass());
                configuration.setDataSource(dataSource);

            }
        }
        return null;
    }
}
