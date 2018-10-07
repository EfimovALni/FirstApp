package cz.firstapp.jackson;

import cz.firstapp.jackson.model.Configuartion;

public class DataResponse_Test {
    private String status;
    private float actualVersion;
    private Configuartion_Test configuartion_test;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getActualVersion() {
        return actualVersion;
    }

    public void setActualVersion(float actualVersion) {
        this.actualVersion = actualVersion;
    }

    public Configuartion_Test getConfiguartion_test() {
        return configuartion_test;
    }

    public void setConfiguartion_test(Configuartion_Test configuartion_test) {
        this.configuartion_test = configuartion_test;
    }


    @Override
    public String toString() {
        return "DataResponse_Test{" +
                "status='" + status + '\'' +
                ", actualVersion=" + actualVersion +
                ", configuartion_test=" + configuartion_test +
                '}';
    }
}
