package edu.dtcc.emailman.cis282mysqldemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toast.makeText(getBaseContext(), "App Started", Toast.LENGTH_SHORT).show();

        // Run the database task in the background
        new MyTask().execute();
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

    private class MyTask extends AsyncTask<Void, Void, Void> {

        String txt1 = "";
        String txt2 = "";


        @Override
        protected Void doInBackground(Void... voids) {

            String url =  "jdbc:mysql://phpmyadmin.cdgwdgkn5fuv.us-west-2.rds.amazonaws.com:3306/eric_db";
            String user = "db_eric";
            String password = "Way2Go";
            Connection connection = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                txt1 = "Driver Loaded";
                System.out.println("Driver loaded :)");

            } catch (ClassNotFoundException e) {
                txt1 = "Driver Not Loaded";
                System.out.println("Driver not loaded :(");
                // e.printStackTrace();
            }

            try {
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Connection Succeeded :)");
                txt2 = "Connection OK";
            } catch (SQLException e) {
                System.out.println("Connection Failed :(");
                txt2 = "Connection Failed";
                // e.printStackTrace();
            }

            try {
                Statement st = null;
                if (connection != null) {
                    st = connection.createStatement();
                }
                String sql = "SELECT * FROM address";
                final ResultSet rs;
                if (st != null) {
                    rs = st.executeQuery(sql);

                    while (rs.next()) {
                        System.out.println(rs.getString(2) + " lives in unit " + rs.getString(3));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            String message = txt1 + " / " + txt2;
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

        }
    }
}


