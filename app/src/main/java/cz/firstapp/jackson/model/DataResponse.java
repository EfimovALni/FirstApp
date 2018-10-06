package cz.firstapp.jackson.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataResponse {
    public ArrayList<Initial_screen> getData;

    /*{
    "status": "OK",
    "actual_version": 0.01,
    "configuartion": {
        "initial_screen": [
        {
            "color": "#0000ff",
                "text": "software",
                "icon": "data:image/png;base64,iVBORw0KGgoAAAA */


    @SerializedName("status")
    @Expose
    private String status;


    @SerializedName("actual_version")
    @Expose
    private float actual_version;

    @SerializedName("configuartion")
    @Expose
    private Configuartion configuartion;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getActual_version() {
        return actual_version;
    }

    public void setActual_version(float actual_version) {
        this.actual_version = actual_version;
    }

    public Configuartion getConfiguartion() {
        return configuartion;
    }

    public void setConfiguartion(Configuartion configuartion) {
        this.configuartion = configuartion;
    }

    @Override
    public String toString() {
        return "DataResponse{" +
                "status='" + status + '\'' +
                ", actual_version=" + actual_version +
                ", configuartion=" + configuartion +
                '}';
    }
}
