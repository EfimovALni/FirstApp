package cz.firstapp.jackson;

import com.google.gson.JsonObject;

import java.util.Map;

import cz.firstapp.jackson.model.DataResponse;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    //    Method for get info by POST ("@FormUrlEncoded" + "@POST") + body like a "@File" - use OBLIGATORY!
    @FormUrlEncoded
    @POST("DAN/ajaxData.php")
    Call<JsonObject> loginAccount(@FieldMap Map<String, String> fields);

//    Брать данные
//    @POST
//    Call<DataResponse> getData();
}
