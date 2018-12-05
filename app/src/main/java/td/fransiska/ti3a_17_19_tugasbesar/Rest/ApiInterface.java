package td.fransiska.ti3a_17_19_tugasbesar.Rest;

import retrofit2.Call;
import retrofit2.http.GET;
import td.fransiska.ti3a_17_19_tugasbesar.Models.ResultTiket;

public interface ApiInterface {
    @GET("tiket/tiket")
    Call<ResultTiket> getTiket();

}
