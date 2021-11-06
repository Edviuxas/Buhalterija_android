package com.example.lab6v2;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.lab6v2.REST.RESTControl;

import static com.example.lab6v2.Constants.address;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void validateUser(View v) {
        EditText username = findViewById(R.id.textLogin);
        EditText password = findViewById(R.id.textPassword);
        String dataToSend = "{\"username\":\"" + username.getText().toString() + "\", \"password\":\"" + password.getText().toString() + "\"}";
        Login login = new Login();
        login.execute(dataToSend);
    }

    private final class Login extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(LoginActivity.this, "Validating login data", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = address + "/Laboratorinis3_war_exploded/users/login";
            String postDataParameters = params[0];
            System.out.println("SENT: " + postDataParameters);
            try {
                return RESTControl.sendPost(url, postDataParameters);
            } catch (Exception e) {
                e.printStackTrace();
                return "Error while getting data from web";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("RECEIVED: " + result);
            if (result != null && !result.equals("Validation error")) {
                try {
                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_LONG).show();
                    Intent categoriesWindow = new Intent(LoginActivity.this, CategoriesActivityV1.class);
                    int userID = Integer.parseInt(result.split(" ")[0]);
                    String username = result.split(" ")[1];
                    String password = result.split(" ")[2];
                    categoriesWindow.putExtra("userID", userID);
                    categoriesWindow.putExtra("username", username);
                    categoriesWindow.putExtra("password", password);
                    startActivity(categoriesWindow);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "Wrong data", Toast.LENGTH_LONG).show();
            }
        }
    }
}