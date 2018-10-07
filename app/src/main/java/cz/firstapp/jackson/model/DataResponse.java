package cz.firstapp.jackson.model;

public class DataResponse {
    private String status;
    private float actual_version;
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
