package cz.firstapp.jackson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASED_URL = "https://www.videotechnik.cz/";
    private static final String LOGIN_URL = "https://www.videotechnik.cz/"; // Now the same with "BASED_URL", but it can be different


    public static Retrofit getClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASED_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }
}
