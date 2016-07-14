package venkat.com.kayakapp.Controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.client.OkClient;
import retrofit.converter.JacksonConverter;
import venkat.com.kayakapp.Util.Constant;
import venkat.com.kayakapp.api.KayakApi;

/**
 * Created by venkatgonuguntala on 3/19/16.
 */
public class Access {
    private static final String TAG = "Access";
    private static final int TIMEOUT = 15;

    private static Access instance;

    private OkClient okClient;
    private JacksonConverter converter;

    private KayakApi kayakApi;

    public static Access getInstance() {
        if (instance == null) {
            synchronized (Access.class) {
                if (instance == null) {
                    instance = new Access();
                }
            }
        }
        return (instance);
    }

    private Access() {
        // Make HTTP client
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.setFollowRedirects(false);
        okClient = new OkClient(okHttpClient);

        // Make JSON converter
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        converter = new JacksonConverter(mapper);
    }

    public KayakApi getKayakAirlinesAPi() {
        if (kayakApi !=null) return kayakApi;

        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setClient(okClient);
        builder.setEndpoint(Constant.BASE_URL);
        builder.setConverter(converter);
        builder.setLog(new AndroidLog(TAG));
        RestAdapter adapter = builder.build();

        kayakApi = adapter.create(KayakApi.class);
        return kayakApi;
    }
}
