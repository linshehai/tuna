# tuna
a mybatis-like project.after read mybatis source code,
I decide writing my own project.

# how to use
- just like mybatis,there is a mapper file
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<mapper namespace="com.hy.tuna.menu.MenuMapper">
    <resultMap id="baseMap" type="com.hy.tuna.menu.Menu">
        <result column="id" property="id" javaType="String"/>
        <result column="name" property="name" javaType="String"/>
    </resultMap>
    <resultMap id="baseMap1" type="com.hy.tuna.menu.Menu">
        <result column="id" property="id" javaType="String"/>
    </resultMap>
    <select id="queryById" resultMap="baseMap1">
        select * from menu where 
        <if test="name!=null">
            and name=#{name}
        </if>
        <if test="id!=null">
            and id=#{id}
        </if>
        order by id
    </select>
</mapper>
```
- there is the config file
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<config>
    <dataSource
    username="root" password="root"
    url="jdbc:mysql://localhost:3306/test?serverTimezone=UTC&amp;useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false"
    driverClass="com.mysql.cj.jdbc.Driver"
    dataSourceType="com.alibaba.druid.pool.DruidDataSource"
    />

    <resource location="/MenuMapper.xml"/>
</config>
```
- you can use in the code like this
```java
    SessionFactoryBuilder sessionFactoryBuilder = new SessionFactoryBuilder("/tuna-config.xml");
    SessionFactory sessionFactory = sessionFactoryBuilder.build()
    Session session = sessionFactory.openSession();
    MenuMapper menuMapper = session.getTunaMapper(MenuMapper.class);
    Menu criteria = new Menu();
    criteria.setId("123");
    Menu menu = menuMapper.queryById(criteria);
    assert menu.getId().equals("123");
```
