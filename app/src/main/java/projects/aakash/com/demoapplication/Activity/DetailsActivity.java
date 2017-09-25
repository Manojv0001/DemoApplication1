package projects.aakash.com.demoapplication.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import projects.aakash.com.demoapplication.Activity.Adapter.EmailColumnAdapter;
import projects.aakash.com.demoapplication.Activity.Adapter.MobileColumnAdapter;
import projects.aakash.com.demoapplication.Activity.Adapter.NameColumnAdapter;
import projects.aakash.com.demoapplication.Activity.Models.User;
import projects.aakash.com.demoapplication.Activity.Sqlite.DatabaseHandler;
import projects.aakash.com.demoapplication.R;

/**
 * Created by NG on 20-Jul-2017.
 */

public class DetailsActivity extends AppCompatActivity {
    private DatabaseHandler databaseHandler;
    private EditText etSearch;
    private Button btSearch;
    private RecyclerView rvName;
    private RecyclerView rvEmail;
    private RecyclerView rvMobile;
    private NameColumnAdapter nameColumnAdapter;
    private EmailColumnAdapter emailColumnAdapter;
    private MobileColumnAdapter mobileColumnAdapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        databaseHandler = new DatabaseHandler(this);
        initView();
        initData();
    }

    private void initData() {
         userList = databaseHandler.getAllContacts();
        nameColumnAdapter = new NameColumnAdapter(this, userList);
        rvName.setAdapter(nameColumnAdapter);
       /* emailColumnAdapter = new EmailColumnAdapter(this, userList);
        rvEmail.setAdapter(emailColumnAdapter);
        mobileColumnAdapter = new MobileColumnAdapter(this, userList);
        rvMobile.setAdapter(mobileColumnAdapter);*/
        etSearch.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();

                final List<User> filteredList = new ArrayList<>();

                for (int i = 0; i < userList.size(); i++) {

                    final String text = userList.get(i).getName().toLowerCase();
                    if (text.contains(query)) {
                        filteredList.add(userList.get(i));
                    }
                }

                rvName.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));
                nameColumnAdapter = new NameColumnAdapter(DetailsActivity.this,filteredList );
                rvName.setAdapter(nameColumnAdapter);
                nameColumnAdapter.notifyDataSetChanged();  // data set changed
            }
        });


    }


    private void initView() {
        etSearch = (EditText) findViewById(R.id.etSearch);
//        btSearch = (Button)findViewById(R.id.btSearch);
        rvName = (RecyclerView) findViewById(R.id.rvName);
        rvName.setHasFixedSize(true);
        rvName.setLayoutManager(new LinearLayoutManager(this));

    }
}
