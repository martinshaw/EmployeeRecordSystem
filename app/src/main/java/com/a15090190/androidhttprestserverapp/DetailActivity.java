package com.a15090190.androidhttprestserverapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.a15090190.androidhttprestserverapp.RESTAPIFunctions.FunctionsDAO;

/**
 * DetailActivity.java
 * ----------------
 * Layout view which displays further information to the user on request.
 *
 * Renders Layout view for manipulation by this code base,
 * then requests further in-depth details of an Employee as
 * specified by the ID number stored in the Intent used to
 * trigger this class.
 *
 * @author martin
 */

public class DetailActivity extends AppCompatActivity {
    FunctionsDAO dao = new FunctionsDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Get stored ID number for use when getting
        // Employee data from the database.
        Intent intent = getIntent();
        int intent_id = intent.getIntExtra(MainActivity.EMPLOYEE_ID, -1);

        // Store references to visual elements in the Layout
        // View for displaying text values.
        TextView id = (TextView) findViewById(R.id.id);
        TextView name = (TextView) findViewById(R.id.name);
        TextView gender = (TextView) findViewById(R.id.gender);
        TextView natInscNo = (TextView) findViewById(R.id.natInscNo);
        TextView dob = (TextView) findViewById(R.id.dob);
        TextView address = (TextView) findViewById(R.id.address);
        TextView postcode = (TextView) findViewById(R.id.postcode);
        TextView salary = (TextView) findViewById(R.id.salary);
        TextView startDate = (TextView) findViewById(R.id.startDate);
        TextView title = (TextView) findViewById(R.id.title);
        TextView email = (TextView) findViewById(R.id.email);

        // Check if an ID number has been successfully passed to the Detail Activity
        if(intent_id == -1){
            Snackbar.make(findViewById(R.id.coordinatorLayout), "There are no Employee records with that ID number!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }else{

            Employee emp = dao.getEmployeeByID(intent_id);

            toolbar.setTitle(emp.getName());

            id.setText(Html.fromHtml("ID Number: <b>"+ Integer.toString(emp.getId())+"</b>"));
            gender.setText(Html.fromHtml("Gender: <b>"+ (((char) emp.getGender() == 'm')||((char) emp.getGender() == 'M')? "Male" : "Female")+"</b>"));
            natInscNo.setText(Html.fromHtml("Nat. Insurance Number: <b>"+ emp.getNatInscNo()+"</b>"));
            dob.setText(Html.fromHtml("Date of Birth: <b>"+ emp.getDob()+"</b>"));
            address.setText(Html.fromHtml("Address: <b>"+ emp.getAddress()+"</b>"));
            postcode.setText(Html.fromHtml("Postcode: <b>"+ emp.getPostcode()+"</b>"));
            salary.setText(Html.fromHtml("Salary: <b>"+ emp.getSalary()+"</b>"));
            startDate.setText(Html.fromHtml("Start Date: <b>"+ emp.getStartDate()+"</b>"));
            title.setText(Html.fromHtml("Title: <b>"+ emp.getTitle()+"</b>"));
            email.setText(Html.fromHtml("E-Mail Address: <b>"+ emp.getEmail()+"</b>"));

        }


    }
}
