package com.example.hostapp.preSale;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class PreSaleDetailsViewModel extends ViewModel {
    private final MutableLiveData<List<PreSaleEntry>> preSaleEntries;
    private final MutableLiveData<PreSaleEntry> selectedPreSaleEntry;

    public PreSaleDetailsViewModel() {
        preSaleEntries = new MutableLiveData<>();
        preSaleEntries.setValue(new ArrayList<>());
        selectedPreSaleEntry = new MutableLiveData<>();
        selectedPreSaleEntry.setValue(null);
    }

    public MutableLiveData<List<PreSaleEntry>> getMenuItems() {
        return preSaleEntries;
    }

    public void setMenuItems(List<PreSaleEntry> preSaleEntries) {
        this.preSaleEntries.setValue(preSaleEntries);
    }

    public MutableLiveData<PreSaleEntry> getSelectedMenuItem() {
        return selectedPreSaleEntry;
    }

    public void setSelectedMenuItem(PreSaleEntry preSaleEntry) {
        this.selectedPreSaleEntry.setValue(preSaleEntry);
    }
}
