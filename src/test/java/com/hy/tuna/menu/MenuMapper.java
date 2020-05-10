package com.hy.tuna.menu;

import java.util.List;

public interface MenuMapper {

    Menu queryById(Menu criteria);
    Menu queryById(String menuId);

    List<Menu> getMenu(Menu criteria);
}
