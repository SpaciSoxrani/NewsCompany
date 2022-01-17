package com.example.hostapp.preSale;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hostapp.mainMenu.MenuItem;
import com.example.hostapp.preSale.NewMailing;

import java.util.ArrayList;
import java.util.List;

public class PreSaleViewModel extends ViewModel {
    private MutableLiveData<List<NewMailing>> newMailings;
    private MutableLiveData<NewMailing> addedNewRow;

    public PreSaleViewModel() {
        newMailings = new MutableLiveData<>();
        newMailings.setValue(new ArrayList<NewMailing>());

        addedNewRow = new MutableLiveData<>();
        addedNewRow.setValue(null);
    }

    public MutableLiveData<List<NewMailing>> getNewMailings() {
        return newMailings;
    }

    public void setNewMailings(List<NewMailing> newMailings) {
        this.newMailings.setValue(newMailings);
    }

    public MutableLiveData<NewMailing> getNewRow() {
        return addedNewRow;
    }

    public void setNewRow(NewMailing newMailing) {
        this.addedNewRow.setValue(newMailing);
    }

}
