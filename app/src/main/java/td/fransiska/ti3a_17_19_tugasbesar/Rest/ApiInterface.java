package td.fransiska.ti3a_17_19_tugasbesar.Rest;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import td.fransiska.ti3a_17_19_tugasbesar.Models.ResultPembelian;
import td.fransiska.ti3a_17_19_tugasbesar.Models.ResultTiket;

public interface ApiInterface {
    @GET("tiket/tiket")
    Call<ResultTiket> getTiket();

    @GET("tiket/tiket/{kata}")
    Call<ResultTiket> getTiketbyKota(@Path("kata") String kata);

    @Multipart
    @POST("Pembelian/pembelian")
    Call<ResultPembelian> postPembelian(
            @Part("id_tiket") RequestBody id_tiket,
            @Part("tanggal") RequestBody tanggal,
            @Part("nama") RequestBody nama,
            @Part("alamat_jemput") RequestBody alamat_jemput,
            @Part("alamat_antar") RequestBody alamat_antar);

    @Multipart
    @POST("tiket/tiket")
    Call<ResultTiket> postTiket(
            @Part MultipartBody.Part file,
            @Part("kota") RequestBody kota,
            @Part("waktu") RequestBody waktu,
            @Part("harga") RequestBody harga
    );
}
