package cz.firstapp.jackson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cz.firstapp.jackson.model.DataResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASED_URL = "https://www.videotechnik.cz/";

    public static Retrofit getClient() {
        Gson gson = new GsonBuilder()
//                .registerTypeAdapterFactory(DataResponse.class,)
                .setLenient()
                .create();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASED_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }
}
