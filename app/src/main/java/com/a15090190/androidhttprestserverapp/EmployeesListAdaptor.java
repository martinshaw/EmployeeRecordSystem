package com.a15090190.androidhttprestserverapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by martin on 30/01/2017.
 */

public class EmployeesListAdaptor extends ArrayAdapter<Employee> {

    public EmployeesListAdaptor(Context context, ArrayList<Employee> emps) {
        super(context, 0, emps);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Employee e = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_employee_basicdetail, parent, false);
        }
        // Lookup view for data population
        TextView eName = (TextView) convertView.findViewById(R.id.name);
        TextView eEmail = (TextView) convertView.findViewById(R.id.email);
        // Populate the data into the template view using the data object
        eName.setText(e.getName());
        eEmail.setText(e.getEmail());

        LinearLayout ll = (LinearLayout) convertView.findViewById(R.id.employee_linearlayout);
        ll.setTag(e);

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Employee e = (Employee) view.getTag();
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(MainActivity.EMPLOYEE_ID, e.getId());
                getContext().startActivity(intent);
            }
        });


        // Return the completed view to render on screen
        return convertView;
    }
}