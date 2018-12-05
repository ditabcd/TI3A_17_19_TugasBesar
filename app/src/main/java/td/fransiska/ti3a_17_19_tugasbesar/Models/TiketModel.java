package td.fransiska.ti3a_17_19_tugasbesar.Models;

import java.io.Serializable;


public class TiketModel implements Serializable{
    String judul;
    String waktuBerangkat;
    String harga;
    int foto;

    public TiketModel(String judul, String waktuBerangkat, String harga, int foto) {
        this.judul = judul;
        this.waktuBerangkat = waktuBerangkat;
        this.harga = harga;
        this.foto = foto;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getWaktuBerangkat() {
        return waktuBerangkat;
    }

    public void setWaktuBerangkat(String waktuBerangkat) {
        this.waktuBerangkat = waktuBerangkat;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
