package com.example.hostapp.mainMenu;
import com.example.hostapp.R;

public class MenuItem {
    public static final int[] backgrounds = {
            R.drawable.menu_item_blue,
            R.drawable.menu_item_orange,
    };

    public static final int[] icons = {
            R.drawable.ic_baseline_contacts_24,
            R.drawable.ic_baseline_psychology_24,
            R.drawable.ic_baseline_deal_24,
            R.drawable.ic_baseline_star_24,
            R.drawable.ic_baseline_flag_24,
            R.drawable.ic_baseline_favorite_24,
            R.drawable.ic_baseline_account_tree_24,
            R.drawable.ic_baseline_folder_special_24,
            R.drawable.ic_baseline_offline_pin_24

    };

    // !!! NAME IS UNIQUE AND IS USED AS ID
    public String name;
    public int icon;
    public int image;

    public long getId() {
        return id;
    }

    private final long id;

    public MenuItem(int image, String name, long id, int icon) {
        this.name = name;
        this.image = image;
        this.icon = icon;
        this.id=id;
    }
}
