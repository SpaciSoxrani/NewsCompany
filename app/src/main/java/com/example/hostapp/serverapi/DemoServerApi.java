package com.example.hostapp.serverapi;

import com.example.hostapp.mainMenu.MenuItem;
import com.example.hostapp.preSale.NewMailing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DemoServerApi {

    public static final Map<String, String> CORRECT_LOGIN_PAIRS = new HashMap<String, String>() {
        {
            put("логин", "пароль");
            put("настя", "смирнова");
            put("1", "1");
        }
    };

    public static boolean checkSignIn(String login, String password) {
        return CORRECT_LOGIN_PAIRS.containsKey(login) && Objects.equals(CORRECT_LOGIN_PAIRS.get(login), password);
    }

    public static final List<MenuItem> ITEMS = new ArrayList<MenuItem>() {{

        add(new MenuItem(0, "Контакты", 0, 0));
        add(new MenuItem(0, "Организации", 1, 1));
        add(new MenuItem(0, "Сделки", 2, 2));
        add(new MenuItem(0, "Отчетность", 3, 3));
        add(new MenuItem(0, "Заявки", 4, 4));
        add(new MenuItem(0, "Интересы", 5, 5));
        add(new MenuItem(0, "Маркетинг", 6, 6));

        add(new MenuItem(1, "Pre-sale", 7, 7));
        add(new MenuItem(1, "Лиды", 8, 8));
    }};

    public static final List<NewMailing> NEW_MAILINGS = new ArrayList<NewMailing>(){{
        add(new NewMailing(0, "Test 1", "В работе", "ДВС"));
        add(new NewMailing(1, "Test 2", "Закрыта", "ДВС"));
        add(new NewMailing(3, "Test 3", "Закрыта", "ДВС"));
    }};

}
