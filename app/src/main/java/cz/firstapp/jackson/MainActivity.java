package cz.firstapp.jackson;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONValue;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cz.firstapp.jackson.model.Configuartion;
import cz.firstapp.jackson.model.DataResponse;
import cz.firstapp.jackson.model.InitialScreen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    Api api;
    ApiClient ApiClient;

    private Button b_getJSON;
    private Button b_decriptJSON;
    private Button b_version;

    private TextView tv_showJSON;
    private TextView tv_showDecriptJSON;

    String action = "action";
    String value = "downloadConfiguration";
    String jsonStr = "{\"status\":\"OK\",\"actual_version\":0.01,\"configuartion\":{\"initial_screen\":[{\"color\":\"#0000ff\",\"text\":\"software\",\"icon\":\"data:image\\/png;base64,iVB=\"},{\"color\":\"#0000ff\",\"text\":\"citrix\",\"icon\":\"data:image\\/png;base64,iVBO+A=\"},{\"color\":\"#0000ff\",\"text\":\"equipment\",\"icon\":\"data:image\\/png;base64,iVB=\"}]}}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_getJSON = (Button) findViewById(R.id.b_getJSON);
        b_decriptJSON = (Button) findViewById(R.id.b_decriptJSON);
        b_version = (Button) findViewById(R.id.b_version);

        tv_showJSON = (TextView) findViewById(R.id.tv_showJSON);
        tv_showDecriptJSON = (TextView) findViewById(R.id.tv_showDecriptJSON);


        api = ApiClient.getClient().create(Api.class);


        //import org.json.simple.JSONObject;
//        Create JSON object
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", new Integer(12342));
            jsonObject.put("firstName", "Ivan");
            jsonObject.put("lastNname", "Polin");
            jsonObject.put("sex", new Boolean(true));
            jsonObject.put("phoneNumb", new Integer(923411231));
            jsonObject.put("nuckname", null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        tv_showDecriptJSON.setText(jsonObject.toString());


//        Create JSON jsonObject more difficult
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject resuliJson = new JSONObject();

        jsonArray.put("first");
        jsonArray.put(new Integer(100));

        try {
            jsonObject1.put("one", "two");
            jsonObject1.put("three", "four");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            resuliJson.put("parametrArray", jsonArray);
            resuliJson.put("parametrObject", jsonObject1);
            resuliJson.put("parametr", "somestring");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("^^^^^^^^^^^^^^^^^^^^^^", resuliJson.toString());
//        tv_showJSON.setText(resuliJson.toString());


//        Example 1-1 - Encode a JSON object
        JSONObject obj1=new JSONObject();
        try {
            obj1.put("name","foo");
            obj1.put("num",new Integer(100));
            obj1.put("balance",new Double(1000.21));
            obj1.put("is_vip",new Boolean(true));
            obj1.put("nickname",null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("1-1 ^^^^^^^^^^", obj1.toString());


//        Example 1-2 - Encode a JSON object - Streaming
        JSONObject obj2=new JSONObject();
        try {
            obj2.put("name","foo");
            obj2.put("num",new Integer(100));
            obj2.put("balance",new Double(1000.21));
            obj2.put("is_vip",new Boolean(true));
            obj2.put("nickname",null);
            StringWriter out = new StringWriter();
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        obj.writeJSONString(out);
//        String jsonText = out.toString();
//        System.out.print(jsonText);
        Log .e("1-2 ^^^^^^^^^^\t", obj2.toString());

//        Example 1-3 - Encode a JSON object - Using Map
        Map obj3=new LinkedHashMap();
        obj3.put("name","foo");
        obj3.put("num",new Integer(100));
        obj3.put("balance",new Double(1000.21));
        obj3.put("is_vip",new Boolean(true));
        obj3.put("nickname",null);

        String jsonText = JSONValue.toJSONString(obj3);
//        System.out.print(jsonText);
        Log .e("1-3 ^^^^^^^^^^\t", jsonText);











    }
    //..Method for filling users information, for POST ........Start.........
    public Map<String, String> usedDataForPOST() {
        final Map<String, String> parametersFromUser = new HashMap<>();

        parametersFromUser.put(action, value);

        return parametersFromUser;
    }
//..Method for filling users information, for POST ........End .........
}


//class Person {
//    private String firstName;
//    private String lastName;
//    private Address address;
//    private List<String> phoneNumber = null;
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }
//
//    public List<String> getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(List<String> phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    @Override
//    public String toString() {
//        return "Person{" +
//                "firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", address=" + address +
//                ", phoneNumber=" + phoneNumber +
//                '}';
//    }
//}
//
//
//class Address {
//    private String street;
//    private String city;
//    private Integer postalCode;
//
//    public String getStreet() {
//        return street;
//    }
//
//    public void setStreet(String street) {
//        this.street = street;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public Integer getPostalCode() {
//        return postalCode;
//    }
//
//    public void setPostalCode(Integer postalCode) {
//        this.postalCode = postalCode;
//    }
//
//    @Override
//    public String toString() {
//        return "Address{" +
//                "street='" + street + '\'' +
//                ", city='" + city + '\'' +
//                ", postalCode=" + postalCode +
//                '}';
//    }
//}
//
