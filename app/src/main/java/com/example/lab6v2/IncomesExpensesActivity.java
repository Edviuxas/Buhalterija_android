package com.example.lab6v2;

import android.os.AsyncTask;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.lab6v2.Objects.Expense;
import com.example.lab6v2.Objects.Income;
import com.example.lab6v2.REST.RESTControl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.lab6v2.Constants.address;

public class IncomesExpensesActivity extends AppCompatActivity {
    String moneyType;
    String selectedCategory;
    String selectedItem;
    ListView listView;
    List<Income> incomes;
    List<Expense> expenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moneyType = getIntent().getStringExtra("moneyType");
        selectedCategory = getIntent().getStringExtra("selectedCategory");
        setContentView(R.layout.activity_incomes_expenses);
        listView = findViewById(R.id.listIncomesExpenses);
        String dataToSend = "{\"belongsTo\":\"" + selectedCategory + "\"}";
        if (moneyType.equals("Expense")) {
            GetExpenses getExpenses = new GetExpenses();
            getExpenses.execute(dataToSend);
        } else if (moneyType.equals("Income")) {
            GetIncomes getIncomes = new GetIncomes();
            getIncomes.execute(dataToSend);
        }
    }

    private final class GetIncomes extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(LoginActivity.this, "Validating login data", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = address + "/Laboratorinis3_war_exploded/incomes/getIncomes";
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
            if (result != null && !result.equals("No data")) {
                try {
                    final Type listType = new TypeToken<ArrayList<Income>>() {
                    }.getType();
                    incomes = new Gson().fromJson(result, listType);
                    ArrayAdapter<Income> arrayAdapter = new ArrayAdapter<Income>(IncomesExpensesActivity.this, android.R.layout.simple_list_item_1, incomes);
                    listView.setAdapter(arrayAdapter);
                    registerForContextMenu(listView);
                    double sum = 0.0;
                    for (Income income : incomes) {
                        sum += income.getAmount();
                    }
                    TextView textView = findViewById(R.id.labelAmount);
                    TextView textView1 = findViewById(R.id.labelMoneyType);
                    textView1.setText("Total incomes:");
                    textView.setText(Double.toString(sum));
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(IncomesExpensesActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            } else {
                TextView textView1 = findViewById(R.id.labelMoneyType);
                textView1.setText("Total incomes:");
                TextView textView = findViewById(R.id.labelAmount);
                textView.setText("0.0");
                ArrayAdapter<Income> arrayAdapter = new ArrayAdapter<Income>(IncomesExpensesActivity.this, android.R.layout.simple_list_item_1, new ArrayList<Income>());
                listView.setAdapter(arrayAdapter);
                //Toast.makeText(LoginActivity.this, "Wrong data", Toast.LENGTH_LONG).show();
            }
        }
    }

    private final class GetExpenses extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(LoginActivity.this, "Validating login data", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = address + "/Laboratorinis3_war_exploded/expenses/getExpenses";
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
            if (result != null && !result.equals("No data")) {
                try {
                    final Type listType = new TypeToken<ArrayList<Expense>>() {
                    }.getType();
                    expenses = new Gson().fromJson(result, listType);
                    ArrayAdapter<Expense> arrayAdapter = new ArrayAdapter<Expense>(IncomesExpensesActivity.this, android.R.layout.simple_list_item_1, expenses);
                    listView.setAdapter(arrayAdapter);
                    registerForContextMenu(listView);
                    double sum = 0.0;
                    for (Expense expense : expenses) {
                        sum += expense.getAmount();
                    }
                    TextView textView = findViewById(R.id.labelAmount);
                    TextView textView1 = findViewById(R.id.labelMoneyType);
                    textView1.setText("Total expenses:");
                    textView.setText(Double.toString(sum));
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(IncomesExpensesActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            } else {
                TextView textView1 = findViewById(R.id.labelMoneyType);
                textView1.setText("Total expenses:");
                TextView textView = findViewById(R.id.labelAmount);
                textView.setText("0.0");
                ArrayAdapter<Expense> arrayAdapter = new ArrayAdapter<Expense>(IncomesExpensesActivity.this, android.R.layout.simple_list_item_1, new ArrayList<Expense>());
                listView.setAdapter(arrayAdapter);
                //Toast.makeText(LoginActivity.this, "Wrong data", Toast.LENGTH_LONG).show();
            }
        }
    }

    private final class DeleteExpense extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(LoginActivity.this, "Validating login data", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = address + "/Laboratorinis3_war_exploded/expenses/deleteExpense";
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
            if (result != null) {
                try {
                    final Type listType = new TypeToken<ArrayList<Expense>>() {
                    }.getType();

                    String dataToSend = "{\"belongsTo\":\"" + selectedCategory + "\"}";
                    GetExpenses getExpenses = new GetExpenses();
                    getExpenses.execute(dataToSend);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(IncomesExpensesActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            } else {
                //Toast.makeText(LoginActivity.this, "Wrong data", Toast.LENGTH_LONG).show();
            }
        }
    }

    private final class DeleteIncome extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(LoginActivity.this, "Validating login data", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = address + "/Laboratorinis3_war_exploded/incomes/deleteIncome";
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
            if (result != null) {
                try {
                    final Type listType = new TypeToken<ArrayList<Expense>>() {
                    }.getType();

                    String dataToSend = "{\"belongsTo\":\"" + selectedCategory + "\"}";
                    GetIncomes getIncomes = new GetIncomes();
                    getIncomes.execute(dataToSend);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(IncomesExpensesActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            } else {
                //Toast.makeText(LoginActivity.this, "Wrong data", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_incomes_content, menu);
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) menuInfo;
        String selectedWord = ((TextView) info.targetView).getText().toString();
        //long selectedWordId = info.id;
        selectedItem = selectedWord.split(" ")[1].split(";")[0];

        menu.setHeaderTitle("Choose your action");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete) {
//            System.out.println("askjdhasd");
//            String dataToSend = "{\"belongsTo\":\"" + selectedCategory + "\"}";
            String dataToSend = "";
            if (moneyType.equals("Expense")) {
//                System.out.println(moneyType);
                for (Expense expense : expenses) {
                    if (expense.getName().equals(selectedItem)) {
                        dataToSend = "{\"belongsTo\":\"" + selectedCategory + "\",\"name\":\"" + expense.getName() + "\",\"description\":\"" + expense.getDescription() + "\",\"amount\":\"" + expense.getAmount() + "\"}";
                        break;
                    }
                }
                DeleteExpense deleteExpense = new DeleteExpense();
                deleteExpense.execute(dataToSend);
            } else if (moneyType.equals("Income")) {
                for (Income income : incomes) {
                    if (income.getName().equals(selectedItem)) {
                        dataToSend = "{\"belongsTo\":\"" + selectedCategory + "\",\"name\":\"" + income.getName() + "\",\"description\":\"" + income.getDescription() + "\",\"amount\":\"" + income.getAmount() + "\"}";
                        break;
                    }
                }
                DeleteIncome deleteIncome = new DeleteIncome();
                deleteIncome.execute(dataToSend);
            }
        } else {
            return false;
        }
        return true;
    }

    public void filter(View v) {
        String dateFrom = ((EditText) findViewById(R.id.dateFrom)).getText().toString();
        String dateUntil = ((EditText) findViewById(R.id.dateUntil)).getText().toString();
        String dataToSend = "{\"belongsTo\":\"" + selectedCategory + "\",\"dateFrom\":\"" + dateFrom + "\",\"dateUntil\":\"" + dateUntil + "\"}";

        if (moneyType.equals("Expense")) {
            GetExpenses getExpenses = new GetExpenses();
            getExpenses.execute(dataToSend);
        } else if (moneyType.equals("Income")) {
            GetIncomes getIncomes = new GetIncomes();
            getIncomes.execute(dataToSend);
        }
    }
}