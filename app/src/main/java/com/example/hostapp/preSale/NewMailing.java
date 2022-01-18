package com.example.hostapp.preSale;

import com.example.hostapp.R;

import java.util.List;

public class NewMailing {
    public static final int[] icons = {
            R.drawable.ic_baseline_edit_24,
            R.drawable.ic_baseline_delete_24,
    };

    public String name;
    public String depart;
    public String status;

    public List<PreSaleEntry> details;

    public NewMailing(long id, String name, String status, String depart, List<PreSaleEntry> details) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.depart = depart;
        this.details = details;
    }

    public long getId() {
        return id;
    }

    private final long id;
}
