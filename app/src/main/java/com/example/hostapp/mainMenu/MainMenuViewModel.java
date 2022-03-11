package com.example.hostapp.mainMenu;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainMenuViewModel extends ViewModel {

    private final MutableLiveData<List<MenuItem>> menuItems;
    private final MutableLiveData<MenuItem> selectedMenuItem;

    public MainMenuViewModel() {
        menuItems = new MutableLiveData<>();
        menuItems.setValue(new ArrayList<>());
        selectedMenuItem = new MutableLiveData<>();
        selectedMenuItem.setValue(null);
    }

    public MutableLiveData<List<MenuItem>> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems.setValue(menuItems);
    }

    public MutableLiveData<MenuItem> getSelectedMenuItem() {
        return selectedMenuItem;
    }

    public void setSelectedMenuItem(MenuItem menuItem) {
        this.selectedMenuItem.setValue(menuItem);
    }

}
