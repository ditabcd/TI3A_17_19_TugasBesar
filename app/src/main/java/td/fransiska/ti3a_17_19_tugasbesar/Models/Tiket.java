package td.fransiska.ti3a_17_19_tugasbesar.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Tiket implements Serializable{
    @SerializedName("id_tiket")
            private int idTiket;
    @SerializedName("kota")
            private String kota;
    @SerializedName("waktu")
            private  String waktu;
    @SerializedName("harga")
            private  int harga;
    @SerializedName("id_photo")
            private String photoId;
    private String action;
    public Tiket(){}

    public Tiket(int idTiket, String kota, String waktu, int harga, String photoId, String action) {
        this.idTiket = idTiket;
        this.kota = kota;
        this.waktu = waktu;
        this.harga = harga;
        this.photoId = photoId;
        this.action = action;


    }

    public void setIdTiket(int idTiket) {
        this.idTiket = idTiket;
    }

    public int getIdTiket() {
        return idTiket;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKota() {
        return kota;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getHarga() {
        return harga;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
