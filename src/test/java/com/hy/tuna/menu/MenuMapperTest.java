package com.hy.tuna.menu;

import com.hy.tuna.TestBase;
import com.hy.tuna.session.Session;
import com.hy.tuna.session.SessionFactory;
import org.junit.Test;


public class MenuMapperTest extends TestBase {

    @Test
    public void testQueryById(){
        SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        MenuMapper menuMapper = session.getTunaMapper(MenuMapper.class);
        Menu criteria = new Menu();
        criteria.setId("123");
        criteria.setName("menu1");
        Menu menu = menuMapper.queryById(criteria);
        assert menu.getId().equals("123");
    }
}
