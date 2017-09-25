package projects.aakash.com.demoapplication.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import projects.aakash.com.demoapplication.Activity.Models.User;
import projects.aakash.com.demoapplication.Activity.Network.ApiClientMain;
import projects.aakash.com.demoapplication.Activity.Sqlite.DatabaseHandler;
import projects.aakash.com.demoapplication.Activity.Utils.Validation;
import projects.aakash.com.demoapplication.R;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName;
    private EditText etEmail;
    private EditText etMobile;
    private Button btAdd;
    private Button btDetails;
    private DatabaseHandler databaseHandler;
    private User user = new User();
    private Button btPush;
    private List<User> userList;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = new ProgressDialog(MainActivity.this);
        databaseHandler = new DatabaseHandler(this);
        initView();
        initData();
    }

    private void initData() {
            /*showProgressDialog("Please wait...",false);
            String fullName=fullname.getText().toString();
            String emailID=txtemailid.getText().toString();
            String userName=username.getText().toString();
            String pwd=password.getText().toString();
            androidkey = preferenceManager.getPreferenceValues(InstanceIdHelper.GCM_KEY);
            Call<FinalRegister> call= ApiClientMain.getApiClient().registerUser(fullName,emailID,pwd,userName,androidkey);
            call.enqueue(new retrofit2.Callback<FinalRegister>() {
                @Override
                public void onResponse(Call<FinalRegister> call, Response<FinalRegister> response) {
                    cancelProgressDialog();
                    FinalRegister resp = response.body();
                    if (resp != null) {
                        if(resp.getUserData()!=null) {
                            String registerStatus = resp.getStatus().toString();
                            String header= response.headers().get("accesstoken");
                            preferenceManager.putPreferenceValues(PreferenceManager.HEADER,header);
                            preferenceManager.putPreferenceValues(PreferenceManager.STATUS,registerStatus);
                            preferenceManager.putPreferenceValues(PreferenceManager.USERID,resp.getUserData().getUserID());
                            preferenceManager.putPreferenceValues(PreferenceManager.FULLNAME,resp.getUserData().getFullName());
                            preferenceManager.putPreferenceValues(PreferenceManager.USERNAME,resp.getUserData().getUserName());
                            preferenceManager.putPreferenceValues(PreferenceManager.EMAILID,resp.getUserData().getEmail());
                            preferenceManager.putPreferenceValues(PreferenceManager.OCCUPATION,resp.getUserData().getOccupation());
                            preferenceManager.putPreferenceValues(PreferenceManager.PhoneNo,resp.getUserData().getPhoneNo());
                            preferenceManager.putPreferenceValues(PreferenceManager.ABOUTME,resp.getUserData().getAboutMe());
                            preferenceManager.putPreferenceValues(PreferenceManager.GENDER,resp.getUserData().getGender());
                            preferenceManager.putPreferenceValues(PreferenceManager.PROFILEPIC,resp.getUserData().getProfilePic());
                            preferenceManager.putPreferenceValues(PreferenceManager.ANDROIDKEY,resp.getUserData().getAndroidKey());
                            preferenceManager.putPreferenceValues(PreferenceManager.DOB,resp.getUserData().getDob());
                            preferenceManager.putPreferenceValues(PreferenceManager.COUNTRY,resp.getUserData().getCountry());
                            preferenceManager.putPreferenceValues(PreferenceManager.STATE,resp.getUserData().getState());
                            if(resp.getInterest().size()!=0){
                                List<Interest> list=resp.getInterest();
                                Gson gson = new Gson();
                                String json = gson.toJson(list);
                                preferenceManager.putPreferenceValues(PreferenceManager.INTEREST,json);
                            }
                            Intent i = new Intent(SignUpActivity.this, OnBoardingActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            Bundle bundle = new Bundle();
                            bundle.putString(applicationStrings.skip, "");
                            i.putExtras(bundle);
                            startActivity(i);
                            finish();
                            Toast.makeText(SignUpActivity.this, resp.getMessage(), Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(SignUpActivity.this,resp.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<FinalRegister> call, Throwable t) {
                    cancelProgressDialog();
                    Toast.makeText(SignUpActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                }
            });*/


    }

    private void initView() {
        etName = (EditText)findViewById(R.id.etName);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etMobile = (EditText)findViewById(R.id.etMobile);
        btAdd = (Button)findViewById(R.id.btAdd);
        btDetails = (Button)findViewById(R.id.btDetails);
        btPush = (Button)findViewById(R.id.btPush);
        btAdd.setOnClickListener(this);
        btDetails.setOnClickListener(this);
        btPush.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btAdd:
                if (checkValidation()) {
                    user.setName(etName.getText().toString());
                    user.setEmail(etEmail.getText().toString());
                    user.setMobile(etMobile.getText().toString());
                    databaseHandler.addUserInfo(user);
                    Toast.makeText(MainActivity.this,"Data added successfully",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btDetails:
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                startActivity(intent);
                break;
            case R.id.btPush:
                userList = databaseHandler.getAllContacts();
//                showProgressDialog("Please wait...",false);
                progress.setIndeterminate(true);
                progress.setMessage("Please wait...");
                progress.show();
                for(int i =0;i<userList.size();i++){
                    Call<User> call= ApiClientMain.getApiClient().pushData(userList.get(i).getName(),userList.get(i).getEmail(),userList.get(i).getMobile());
                    call.enqueue(new retrofit2.Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (progress.isShowing())
                                progress.dismiss();
                            User resp = response.body();
                            if (resp != null) {
                                if(resp.isResult().equals("true")){
                                    Toast.makeText(MainActivity.this,"Data uploaded successfully", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(MainActivity.this,"Data is not uploaded please try again", Toast.LENGTH_SHORT).show();

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            if (progress.isShowing())
                                progress.dismiss();
                            Toast.makeText(MainActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
        }
    }

    private boolean checkValidation() {
        boolean ret = true;
        if(!Validation.hasText(etName))ret = false;
        if(!Validation.isEmailAddress(etEmail,true))ret = false;
        if (!Validation.isPhoneNumber(etMobile,true)) ret = false;
        return ret;
    }
}
