package com.htvm.awal;

public class Jadwal_minggu {

    private String id;
    private String nama;
    private String jam;
    private String kategori;

    public Jadwal_minggu(String id, String nama, String jam, String kategori) {
        this.id = id;
        this.jam = jam;
        this.nama = nama;
        this.kategori = kategori;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
