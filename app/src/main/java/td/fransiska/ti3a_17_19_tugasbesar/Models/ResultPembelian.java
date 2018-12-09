package td.fransiska.ti3a_17_19_tugasbesar.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultPembelian {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Pembelian> result = new ArrayList<Pembelian>();
    @SerializedName("message")
    private String message;


    public  ResultPembelian(){}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Pembelian> getResult() {
        return result;
    }

    public void setResult(List<Pembelian> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
