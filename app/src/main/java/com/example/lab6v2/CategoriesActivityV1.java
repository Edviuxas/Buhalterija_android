package com.example.lab6v2;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.lab6v2.REST.RESTControl;
import com.example.lab6v2.Objects.Category;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.lab6v2.Constants.address;

public class CategoriesActivityV1 extends AppCompatActivity {
    int userId = 0;
    String username;
    String password;
    ListView listView;
    String selectedItem = "";
    ArrayList<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = getIntent().getIntExtra("userID", 0);
        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        String dataToSend = "{\"userID\":\"" + userId + "\"}";
        GetCategories getCategories = new GetCategories();
        getCategories.execute(dataToSend);
        setContentView(R.layout.activity_categories);
        listView = findViewById(R.id.listCategories);
    }

    private final class GetCategories extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(LoginActivity.this, "Validating login data", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = address + "/Laboratorinis3_war_exploded/category/getAllCategories";
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
            if (result != null) {
                try {
                    final Type listType = new TypeToken<ArrayList<Category>>() {
                    }.getType();
                    categories = new Gson().fromJson(result, listType);
                    ArrayAdapter<Category> arrayAdapter = new ArrayAdapter<Category>(CategoriesActivityV1.this, android.R.layout.simple_list_item_1, categories);
                    listView.setAdapter(arrayAdapter);
                    registerForContextMenu(listView);

                    String dataToSend = "{\"userID\":\"" + userId + "\"}";
                    GetTotalIncomes getTotalIncomes = new GetTotalIncomes();
                    getTotalIncomes.execute(dataToSend);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(CategoriesActivityV1.this, "Error", Toast.LENGTH_LONG).show();
                }
            } else {
                categories = new ArrayList<>();
                ArrayAdapter<Category> arrayAdapter = new ArrayAdapter<Category>(CategoriesActivityV1.this, android.R.layout.simple_list_item_1, categories);
                listView.setAdapter(arrayAdapter);
                registerForContextMenu(listView);
                ((TextView)findViewById(R.id.labelIncomes)).setText("0.0");
                ((TextView)findViewById(R.id.labelExpenses)).setText("0.0");
                //Toast.makeText(LoginActivity.this, "Wrong data", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        String dataToSend = "{\"userID\":\"" + userId + "\"}";
        GetCategories getCategories = new GetCategories();
        getCategories.execute(dataToSend);
    }

    private final class GetTotalIncomes extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(LoginActivity.this, "Validating login data", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = address + "/Laboratorinis3_war_exploded/incomes/getTotalIncomes";
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
            TextView incomeView = findViewById(R.id.labelIncomes);
            if (result != null) {
                try {
                    incomeView.setText(result);

                    String dataToSend = "{\"userID\":\"" + userId + "\"}";
                    GetTotalExpenses getTotalExpenses = new GetTotalExpenses();
                    getTotalExpenses.execute(dataToSend);

//                    final Type listType = new TypeToken<ArrayList<Category>>() {
//                    }.getType();
//                    categories = new Gson().fromJson(result, listType);
//                    ArrayAdapter<Category> arrayAdapter = new ArrayAdapter<Category>(CategoriesActivityV1.this, android.R.layout.simple_list_item_1, categories);
//                    listView.setAdapter(arrayAdapter);
//                    registerForContextMenu(listView);


                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(CategoriesActivityV1.this, "Error", Toast.LENGTH_LONG).show();
                }
            } else {
                incomeView.setText("0.0");
                //Toast.makeText(LoginActivity.this, "Wrong data", Toast.LENGTH_LONG).show();
            }
        }
    }

    private final class GetTotalExpenses extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(LoginActivity.this, "Validating login data", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = address + "/Laboratorinis3_war_exploded/expenses/getTotalExpenses";
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
            TextView expenseView = findViewById(R.id.labelExpenses);
            if (result != null) {
                try {
//                    final Type listType = new TypeToken<ArrayList<Category>>() {
//                    }.getType();
//                    categories = new Gson().fromJson(result, listType);
//                    ArrayAdapter<Category> arrayAdapter = new ArrayAdapter<Category>(CategoriesActivityV1.this, android.R.layout.simple_list_item_1, categories);
//                    listView.setAdapter(arrayAdapter);
//                    registerForContextMenu(listView);

                    expenseView.setText(result);


                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(CategoriesActivityV1.this, "Error", Toast.LENGTH_LONG).show();
                }
            } else {
                expenseView.setText("0.0");
                //Toast.makeText(LoginActivity.this, "Wrong data", Toast.LENGTH_LONG).show();
            }
        }
    }

    private final class DeleteCategory extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(LoginActivity.this, "Validating login data", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = address + "/Laboratorinis3_war_exploded/category/delete";
            String postDataParameters = params[0];
            System.out.println("SENT: " + postDataParameters);
            try {
                return RESTControl.sendDelete(url, postDataParameters);
            } catch (Exception e) {
                e.printStackTrace();
                return "Error while getting data from web";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("RECEIVED: " + result);
            GetCategories getCategories = new GetCategories();
            String dataToSend = "{\"userID\":\"" + userId + "\"}";
            getCategories.execute(dataToSend);
        }
    }

    private final class GetUsernamePassword extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(LoginActivity.this, "Validating login data", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = address + "/Laboratorinis3_war_exploded/users/getUsernamePassword";
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

            if (result != null && !result.equals("Error")) {
                Intent updateUserWindow = new Intent(CategoriesActivityV1.this, UpdateUserActivity.class);
                updateUserWindow.putExtra("userID", userId);
                updateUserWindow.putExtra("username", result.split(" ")[0]);
                updateUserWindow.putExtra("password", result.split(" ")[1]);
                startActivity(updateUserWindow);
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) menuInfo;
        String selectedWord = ((TextView) info.targetView).getText().toString();
        //long selectedWordId = info.id;
        selectedItem = selectedWord.split("\\(")[0];

        menu.setHeaderTitle("Choose your action");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete) {

            String sendData = "{\"categoryName\":\"" + selectedItem + "\"}";
            DeleteCategory deleteCategory = new DeleteCategory();
            deleteCategory.execute(sendData);
            for (Category category : categories) {
                if (category.getPath().contains(selectedItem)) {
                    sendData = "{\"categoryName\":\"" + category.getName() + "\"}";
                    deleteCategory = new DeleteCategory();
                    deleteCategory.execute(sendData);
                }
            }
        } else if (item.getItemId() == R.id.manageIncomes) {
            Intent incomesExpensesWindow = new Intent(CategoriesActivityV1.this, IncomesExpensesActivity.class);
            incomesExpensesWindow.putExtra("selectedCategory", selectedItem);
            incomesExpensesWindow.putExtra("moneyType", "Income");
            startActivity(incomesExpensesWindow);
        } else if (item.getItemId() == R.id.manageExpenses) {
            Intent incomesExpensesWindow = new Intent(CategoriesActivityV1.this, IncomesExpensesActivity.class);
            incomesExpensesWindow.putExtra("selectedCategory", selectedItem);
            incomesExpensesWindow.putExtra("moneyType", "Expense");
            startActivity(incomesExpensesWindow);
        } else {
            return false;
        }
        return true;
    }

    public void loadUserUpdateWindow(View view) {
        String dataToSend = "{\"userID\":\"" + userId + "\"}";
        GetUsernamePassword getUsernamePassword = new GetUsernamePassword();
        getUsernamePassword.execute(dataToSend);
    }
}