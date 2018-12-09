package td.fransiska.ti3a_17_19_tugasbesar.Models;

import com.google.gson.annotations.SerializedName;

public class Pembelian {
    @SerializedName("id")
            private int id;
    @SerializedName("id_tiket")
            private int id_tiket;
    @SerializedName("tanggal")
            private String tanggal;
    @SerializedName("nama")
            private String nama;
    @SerializedName("alamat_jemput")
            private String alamat_jemput;
    @SerializedName("alamat_antar")
            private String alamat_antar;

    private String action;
    public Pembelian(){}

    public Pembelian(int id, int id_tiket, String tanggal, String nama, String alamat_jemput, String alamat_antar){
        this.id = id;
        this.id_tiket = id_tiket;
        this.tanggal = tanggal;
        this.nama = nama;
        this.alamat_jemput = alamat_jemput;
        this.alamat_antar = alamat_antar;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_tiket() {
        return id_tiket;
    }

    public void setId_tiket(int id_tiket) {
        this.id_tiket = id_tiket;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat_jemput() {
        return alamat_jemput;
    }

    public void setAlamat_jemput(String alamat_jemput) {
        this.alamat_jemput = alamat_jemput;
    }

    public String getAlamat_antar() {
        return alamat_antar;
    }

    public void setAlamat_antar(String alamat_antar) {
        this.alamat_antar = alamat_antar;
    }
}
