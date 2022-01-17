package com.example.hostapp.preSale;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class PreSaleViewModel extends ViewModel {
    private MutableLiveData<List<NewMailing>> newMailings;
    private MutableLiveData<NewMailing> selectedEditRow;

    public PreSaleViewModel() {
        newMailings = new MutableLiveData<>();
        newMailings.setValue(new ArrayList<NewMailing>());

        selectedEditRow = new MutableLiveData<>();
        selectedEditRow.setValue(null);
    }

    public MutableLiveData<List<NewMailing>> getNewMailings() {
        return newMailings;
    }

    public void setNewMailings(List<NewMailing> newMailings) {
        this.newMailings.setValue(newMailings);
    }

    public MutableLiveData<NewMailing> getSelectedEditRow() {
        return selectedEditRow;
    }

    public void setSelectedEditRow(NewMailing newMailing) {
        this.selectedEditRow.setValue(newMailing);
    }

}
