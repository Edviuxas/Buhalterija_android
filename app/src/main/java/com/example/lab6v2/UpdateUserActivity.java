package com.example.lab6v2;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.lab6v2.REST.RESTControl;

import static com.example.lab6v2.Constants.address;

public class UpdateUserActivity extends AppCompatActivity {

    int userID;
    String username;
    String password;
    EditText textUsername;
    EditText textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userID = getIntent().getIntExtra("userID", 0);
        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        setContentView(R.layout.activity_update_user);
        textUsername = findViewById(R.id.textUsername);
        textPassword = findViewById(R.id.textPassword);
        textUsername.setText(username);
        textPassword.setText(password);
    }

    private final class UpdateUser extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(LoginActivity.this, "Validating login data", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = address + "/Laboratorinis3_war_exploded/users/update";
            String postDataParameters = params[0];
            System.out.println("SENT: " + postDataParameters);
            try {
                return RESTControl.sendPut(url, postDataParameters);
            } catch (Exception e) {
                e.printStackTrace();
                return "Error while getting data from web";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("RECEIVED: " + result);
            UpdateUserActivity.this.finish();
//            CategoriesActivityV1.GetCategories getCategories = new CategoriesActivityV1.GetCategories();
//            String dataToSend = "{\"userID\":\"" + userId + "\"}";
//            getCategories.execute(dataToSend);
        }
    }

    public void updateInformation(View view) {
        String dataToSend = "{\"userID\":\"" + userID + "\",\"username\":\"" + textUsername.getText() + "\",\"password\":\"" + textPassword.getText() + "\"}";
        UpdateUser updateUser = new UpdateUser();
        updateUser.execute(dataToSend);
    }
}