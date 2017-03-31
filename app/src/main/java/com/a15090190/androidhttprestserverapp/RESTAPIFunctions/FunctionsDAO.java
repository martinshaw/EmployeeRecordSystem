package com.a15090190.androidhttprestserverapp.RESTAPIFunctions;

import android.util.Log;

import com.a15090190.androidhttprestserverapp.Employee;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutionException;


/**
 * Created by martin on 06/02/2017.
 */

public class FunctionsDAO {
    public Gson gson = new Gson();

    public static String api_host = "http://10.0.2.2:8005";

    public ArrayList<Employee> getAllEmployees(){
        ArrayList<Employee> _result = null;
        String _returnedJson = "";
        HttpGetRequest _getRequest = new HttpGetRequest();

        try {
            _returnedJson = _getRequest.execute(this.api_host+"/employees").get();
            Employee[] actualEmps = gson.fromJson(_returnedJson, Employee[].class);
            _result = new ArrayList<Employee>(Arrays.asList(actualEmps));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return _result;
    }

    public Employee getEmployeeByID(int intent_id) {
        Employee _result = null;
        String _returnedJson;
        HttpGetRequest _getRequest = new HttpGetRequest();

        try {
            _returnedJson = _getRequest.execute(this.api_host+"/employees/"+(Integer.toString(intent_id))).get();

            _result = gson.fromJson(_returnedJson, Employee.class);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return _result;
    }
}
