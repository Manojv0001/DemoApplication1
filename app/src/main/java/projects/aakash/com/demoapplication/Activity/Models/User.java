package projects.aakash.com.demoapplication.Activity.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by NG on 20-Jul-2017.
 */

public class User {
    int id;
    String name;
    String email;
    String mobile;
    @SerializedName("result")
    @Expose
    String result;
    public User(int i, String string, String string1, String string2) {
        this.id  = i;
        this.name  = string;
        this.email  = string1;
        this.mobile  = string2;

    }

    public  User(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String isResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
