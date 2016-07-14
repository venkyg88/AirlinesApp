package venkat.com.kayakapp.api;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import venkat.com.kayakapp.model.Airlines;

/**
 * Created by venkatgonuguntala on 3/19/16.
 */
public interface KayakApi {

    @GET("/h/mobileapis/directory/airlines")
    void getAirlinesList(Callback<List<Airlines>> callback);
}
