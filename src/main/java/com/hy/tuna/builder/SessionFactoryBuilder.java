package com.hy.tuna.builder;

import com.hy.tuna.Configuration;
import com.hy.tuna.session.DefaultSessionFactory;
import com.hy.tuna.session.SessionFactory;
import com.hy.tuna.utils.StringUtils;

public class SessionFactoryBuilder{

    private SessionFactory sessionFactory;

    private ObjectBuilder objectBuilder;

    private String configLocation;

    public SessionFactoryBuilder(){

    }
    public SessionFactoryBuilder(String configLocation){
        this.configLocation = configLocation;
    }
    public static SessionFactoryBuilder create(){
        return new SessionFactoryBuilder();
    }

    public SessionFactory build() throws Exception {
        Configuration configuration = new Configuration();
        //xml config
        if(StringUtils.hasText(this.configLocation)){
            objectBuilder = new XmlConfigBuilder(configuration,this.configLocation);
        }else{
            objectBuilder = new AnnotationObjectBuilder(configuration,this.configLocation);
        }
        objectBuilder.build();
        sessionFactory = new DefaultSessionFactory(configuration);
        return sessionFactory;
    }
}
