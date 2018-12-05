package td.fransiska.ti3a_17_19_tugasbesar.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultTiket {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Tiket> result = new ArrayList<Tiket>();
    @SerializedName("message")
    private String message;
    public  ResultTiket(){}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Tiket> getResult() {
        return result;
    }

    public void setResult(List<Tiket> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
