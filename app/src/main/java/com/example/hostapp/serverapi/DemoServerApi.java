package com.example.hostapp.serverapi;

import com.example.hostapp.mainMenu.MenuItem;
import com.example.hostapp.preSale.NewMailing;
import com.example.hostapp.preSale.PreSaleEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DemoServerApi {

    public static final Map<String, String> CORRECT_LOGIN_PAIRS = new HashMap<String, String>() {
        {
            put("A.Smirnova@hostco.ru", "AShost");
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
        add(new NewMailing(0, "Test 1", "В работе", "ДВС", PRE_SALE_ENTRIES));
        add(new NewMailing(1, "Test 2", "Закрыта", "ДВС", null));
        add(new NewMailing(3, "Test 3", "Закрыта", "ДВС", null));
    }};

    public  static  final  List<PreSaleEntry> PRE_SALE_ENTRIES = new ArrayList<PreSaleEntry>(){{
        add(new PreSaleEntry(0, "Свердловская область", "(ЕКБ+0)", "Областной диспансер №2",
                "Матушкин Матвей Федорович", "Главный врач", "89901234567", "test1@gmail.com", "http://test.com",
                "Иск №21109 от 19.12.21", "№480019"));
        add(new PreSaleEntry(1, "Оренбурская область", "(ЕКБ+0)", "Городская больница №1",
                "Иванов Иван Андреевич", "Главный врач", "89901230888", "test2@gmail.com", "http://test2.com",
                "", ""));
        add(new PreSaleEntry(2, "Республика Бурятия", "(ЕКБ+3)", "Минздрав",
                "Сергеев Григорий Антонович", "Министр", "89956777767", "test3@gmail.com", "http://test3.com",
                "", ""));
    }};
}
