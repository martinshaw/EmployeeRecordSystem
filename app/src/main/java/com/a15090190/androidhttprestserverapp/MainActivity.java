package com.a15090190.androidhttprestserverapp;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.a15090190.androidhttprestserverapp.RESTAPIFunctions.FunctionsDAO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import junit.framework.Assert;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * MainActivity.java
 * ----------------
 * Starting point of the Android Java Application.
 *
 * Renders Layout view for manipulation by this code base,
 * then requests a list of basic Employee data from the server,
 * then passes this data into a ListViewAdaptor which displays
 * the records in a pressable list, then sets an event listener to
 * inflate the DetailActivity for displaying further in-depth Employee
 * information, on press of button.
 *
 * @author martin
 */

public class MainActivity extends AppCompatActivity {

    public final static String EMPLOYEE_ID = "EMPLOYEE_ID"; // for use with MainActivity to DetailActivity Intent
    public ArrayList<Employee> demoEmps;
    public FunctionsDAO dao = new FunctionsDAO();
    public Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Check Connection
        if (isConnected() == false){
            Snackbar.make(findViewById(R.id.coordinatorLayout), "You are not connected to the internet! Enable WiFi or Mobile Data & restart!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }else{

            // Get full list of Employees from server
            ArrayList<Employee> employees_list = dao.getAllEmployees();

            // Connect data set of Employees into adaptor for display in ListView
            EmployeesListAdaptor adapter = new EmployeesListAdaptor(this, employees_list);
            ListView listView = (ListView) findViewById(R.id.employee_list);
            listView.setAdapter(adapter);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Basic test for whether the device is connected to ther internet
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

}
