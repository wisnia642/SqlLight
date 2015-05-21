package com.example.michal.sql;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.*;


public class MainActivity extends ActionBarActivity {

    TextView responseTextView;
    static ResultSet rs;
    static Statement st;
    String napis;
    Connection connection = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseTextView = (TextView) findViewById(R.id.View);
        EditText edit =(EditText) findViewById(R.id.Text);
        Button but = (Button) findViewById(R.id.button);
        napis = edit.getText().toString();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName("com.mysql.jd,kn.njbc.Driver");
        } catch (ClassNotFoundException e) {
            responseTextView.setText(""+e);
            return;
        }


        try {
            connection = DriverManager.getConnection("jdbc:mysql://10.1.1.110/a4442254_rest","a4442254_rest", "kaseta12");
            responseTextView.setText("open");
        } catch (SQLException e) {
            responseTextView.setText(""+e);
            return;
        }

        if (connection != null) {
            responseTextView.setText("Connected to database!");
        } else {
            responseTextView.setText("Connection Failed!");
        }

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    st = connection.createStatement();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                EditText edit =(EditText) findViewById(R.id.Text);
                napis = edit.getText().toString();
                String sql = "CREATE TABLE "+napis+"" +
                        "(id INTEGER not NULL, " +
                        " last VARCHAR(255), " +
                        " age INTEGER, " +
                        " PRIMARY KEY ( id ))";
                responseTextView.setText(napis);

                try {
                    st.executeUpdate(sql);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try{
                    if(connection!=null)
                        connection.close();
                }catch(SQLException se){
                    se.printStackTrace();}
               // responseTextView.setText("Created table in given database...");


            }
        });






    }


    }




