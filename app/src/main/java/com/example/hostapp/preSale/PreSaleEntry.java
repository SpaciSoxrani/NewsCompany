package com.example.hostapp.preSale;

public class PreSaleEntry {
    public String district;
    public String timeZone;
    public String organization;
    public String name;
    public String position;
    public String phone;
    public String mail;
    public String site;
    public String requestSend;
    public String number;

    public long getId() {
        return id;
    }

    private final long id;

    public PreSaleEntry(long id, String district, String timeZone, String organization, String name, String position,
                        String phone, String mail, String site, String requestSend, String number) {
        this.id = id;
        this.district = district;
        this.timeZone = timeZone;
        this.organization = organization;
        this.name = name;
        this.position = position;
        this.phone = phone;
        this.mail = mail;
        this.site = site;
        this.requestSend = requestSend;
        this.number = number;
    }
}
