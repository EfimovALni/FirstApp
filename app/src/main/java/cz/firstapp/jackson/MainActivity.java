package cz.firstapp.jackson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.firstapp.jackson.model.DataResponse;
import cz.firstapp.jackson.model.InitialScreen;


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



//        1.---- РАБОТАТЕТ ---------------------------------------------------------------------
//        String connect = "{\"firstName\":\"Иван\",\"lastName\":\"Иванов\",\"address\":{\"street\":\"Невскии 11, кв.11\",\"city\":\"СПб\",\"postalCode\":101021},\"phoneNumber\":[\"812 123-1234\",\"916 123-9090\"]}";
//
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            Person person = mapper.readValue(connect, Person.class);
//            tv_showDecriptJSON.setText("First name: " + person.getFirstName() +
//                    "\nPhone number main: " + person.getPhoneNumber().get(0) +
//                    "\nPhone number home: " + person.getPhoneNumber().get(1) +
//                    "\nStreet: " + person.getAddress().getStreet());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        1.--------------------------------------------------------------------------------------------



//        2.--------------------------------------------------------------------------------------------
        String connect1 = "{\"status\":\"OK\",\"actual_version\":0.01,\"configuartion\":{\"initial_screen\":[{\"color\":\"#0000ff\",\"text\":\"software\",\"icon\":\"data:image\\/png;base64,iVB=\"},{\"color\":\"#0000ff\",\"text\":\"citrix\",\"icon\":\"data:image\\/png;base64,iVBO+A=\"},{\"color\":\"#0000ff\",\"text\":\"equipment\",\"icon\":\"data:image\\/png;base64,iVB=\"}]}}";

        ObjectMapper mapper1 = new ObjectMapper();

        try {
            DataResponse dataResponse = mapper1.readValue(connect1, DataResponse.class);
//            tv_showJSON.setText("Status: " + dataResponse.getStatus() +
//                    "\nActual version: " + dataResponse.getActual_version() +
//                    "\nInitial screen 1 : " + dataResponse.getConfiguartion().getInitial_screen().get(0)
////                    "\nInitial screen 2 : " + dataResponse.getConfiguartion().getInitial_screen().get(1) +
////                    "\nInitial screen 3 : " + dataResponse.getConfiguartion().getInitial_screen().get(2)
//            );

            tv_showJSON.setText("");
//        Работат и выдает резульатт на экран,
            for (int i = 0; i < dataResponse.getConfiguartion().getInitial_screen().size(); i++) {
                String info = "";
                String color = "", text = "", icon = "";
                info += dataResponse.getConfiguartion().getInitial_screen().get(i);
                tv_showJSON.append("Initial screen "+ i + ": "  +info + " \n");

                for (int j = 0; j < 1; j++) {
                    color += dataResponse.getConfiguartion().getInitial_screen().get(i).getColor();
                    text += dataResponse.getConfiguartion().getInitial_screen().get(i).getText();
                    icon += dataResponse.getConfiguartion().getInitial_screen().get(i).getIcon();
                }
                tv_showJSON.append(color + "\n " + text +"\n " + icon + "\n\n ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


//        2.--------------------------------------------------------------------------------------------















//        Clicing on Button VERSION
        final View.OnClickListener onClickListener_1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_showDecriptJSON.setText("Was pressed button: ---> 'b_version'");
            }
        };
        b_version.setOnClickListener(onClickListener_1);

    }
    //..Method for filling users information, for POST ........Start.........
    public Map<String, String> usedDataForPOST() {
        final Map<String, String> parametersFromUser = new HashMap<>();

        parametersFromUser.put(action, value);

        return parametersFromUser;
    }
//..Method for filling users information, for POST ........End .........
}


class Person {
    private String firstName;
    private String lastName;
    private Address address;
    private List<String> phoneNumber = null;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(List<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}


class Address {
    private String street;
    private String city;
    private Integer postalCode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
}

