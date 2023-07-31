package com.htvm.awal.info;

public class Clubmodel {
    private String namainfo;
    private int iconinfo;

    public Clubmodel(String namainfo, int icon) {
        this.namainfo = namainfo;
        this.iconinfo = icon;
    }

    public String getNamainfo() {
        return namainfo;
    }

    public void setNamainfo(String namainfo) {
        this.namainfo = namainfo;
    }

    public int getIconInfo() {
        return iconinfo;
    }

    public void setIcon(int icon) {
        this.iconinfo = icon;
    }
}
