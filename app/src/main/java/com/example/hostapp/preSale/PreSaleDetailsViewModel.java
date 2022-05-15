package com.example.hostapp.preSale;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hostapp.serverapi.APIPreSaleDetailsModels.PreSaleDetailsModel;

import java.util.ArrayList;
import java.util.List;

public class PreSaleDetailsViewModel extends ViewModel {
    private final MutableLiveData<List<PreSaleDetailsModel>> preSaleEntries;
    private final MutableLiveData<PreSaleDetailsModel> selectedPreSaleEntry;

    public PreSaleDetailsViewModel() {
        preSaleEntries = new MutableLiveData<>();
        preSaleEntries.setValue(new ArrayList<>());
        selectedPreSaleEntry = new MutableLiveData<>();
        selectedPreSaleEntry.setValue(null);
    }

    public MutableLiveData<List<PreSaleDetailsModel>> getMenuItems() {
        return preSaleEntries;
    }

    public void setMenuItems(List<PreSaleDetailsModel> preSaleEntries) {
        this.preSaleEntries.setValue(preSaleEntries);
    }

    public MutableLiveData<PreSaleDetailsModel> getSelectedMenuItem() {
        return selectedPreSaleEntry;
    }

    public void setSelectedMenuItem(PreSaleDetailsModel preSaleEntry) {
        this.selectedPreSaleEntry.setValue(preSaleEntry);
    }
}
