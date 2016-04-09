package edu.dtcc.emailman.cis282mysqldemo;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import java.sql.Connection;
import java.sql.DriverManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String url =  "jdbc:mysql://phpmyadmin.cdgwdgkn5fuv.us-west-2.rds.amazonaws.com:3306/eric_db";
        String user = "db_eric";
        String password = "Way2Go";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded :)");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not loaded :(");
        }

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conection Succeeded :)");
        }
        catch (Exception e) {
            System.out.println("Conection Failed :(");
            e.printStackTrace();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
