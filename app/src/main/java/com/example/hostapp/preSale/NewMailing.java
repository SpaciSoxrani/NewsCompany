package com.example.hostapp.preSale;

import com.example.hostapp.R;

public class NewMailing {
    public static final int[] icons = {
            R.drawable.ic_baseline_edit_24,
            R.drawable.ic_baseline_delete_24,
    };

    public String name;
    public String depart;
    public String status;
    public int iconEdit;
    public int iconDelete;

    public NewMailing(long id, String name, String status, String depart) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.depart = depart;
    }

    public long getId() {
        return id;
    }

    private final long id;
}
