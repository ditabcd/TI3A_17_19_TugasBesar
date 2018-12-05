package td.fransiska.ti3a_17_19_tugasbesar.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Tiket {
    @SerializedName("id_tiket")
            private int idTiket;
    @SerializedName("kota")
            private String kota;
    @SerializedName("waktu")
            private  String waktu;
    @SerializedName("harga")
            private  int harga;
    @SerializedName("photo_id")
            private int photoId;
    private String action;
    public Tiket(){}

    public Tiket(int idTiket, String kota, String waktu, int harga, int photoId, String action) {
        this.idTiket = idTiket;
        this.kota = kota;
        this.waktu = waktu;
        this.harga = harga;
        this.photoId = photoId;
        this.action = action;
    }

    public int getIdTiket() {
        return idTiket;
    }

    public void setIdTiket(int idTiket) {
        this.idTiket = idTiket;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
