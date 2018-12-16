package td.fransiska.ti3a_17_19_tugasbesar.Libraries;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import td.fransiska.ti3a_17_19_tugasbesar.Responses.ResponseRoute;

public interface ApiServices {
    //https://maps.googleapis.com/maps/api/directions/
    // json?origin=Cirebon,ID&destination=Jakarta,ID&api_key=YOUR_API_KEY
    @GET("json")
    Call<ResponseRoute> request_route(
            @Query("origin") String origin,
            @Query("destination") String destination,
            @Query("api_key") String api_key
    );
}

