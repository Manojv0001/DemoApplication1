package projects.aakash.com.demoapplication.Activity.Network;

import java.util.concurrent.TimeUnit;

import projects.aakash.com.demoapplication.Activity.Application.Constants;

/**
 * Created by NG on 21-07-2017.
 */

import android.util.Log;
import android.widget.EditText;


import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import projects.aakash.com.demoapplication.Activity.Models.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
public class ApiClientMain{
    private static DemoApiInterface DemoApiInterface;  // interface for Retrofit api
    public static final String prefixUrl = "bcil/";
    private static StringBuilder builder = new StringBuilder();
    public static final String URL = builder.append(Constants.URL).append(prefixUrl).toString(); //"http://faarbetterfilms.com/rockabyte/admin/index.php/api/";
    public static final String MEDIA_TYPE_STRING = "text/plain";
    public static final String MEDIA_TYPE_IMAGE = "image/*";

    public static DemoApiInterface getApiClient() {

        if (DemoApiInterface == null) {
            OkHttpClient client = new OkHttpClient();
            client.setConnectTimeout(20, TimeUnit.SECONDS);
            client.setReadTimeout(15, TimeUnit.SECONDS);
            client.setWriteTimeout(15, TimeUnit.SECONDS);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            DemoApiInterface = retrofit.create(DemoApiInterface.class);
        }
        return DemoApiInterface;
    }

    public interface DemoApiInterface {
        @FormUrlEncoded
        @POST("Servelet")
        Call<User> pushData(@Field("Name")String name,
                            @Field("Email") String email,
                            @Field("Address")String mobile);
    }

    public static RequestBody getStringRequestBody(String s) {
        return RequestBody.create(MediaType.parse(MEDIA_TYPE_STRING), s);
    }
}
