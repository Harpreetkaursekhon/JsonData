package com.app.rwjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);
        ed3 = (EditText) findViewById(R.id.ed3);
        ed4 = (EditText) findViewById(R.id.ed4);
        b1 = (Button) findViewById(R.id.btn1);
        b2 = (Button) findViewById(R.id.btn2);
    }

    public void writeJson(View view) {
        try {
            JSONObject object = new JSONObject();
            JSONArray jsonArray = new JSONArray();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", ed1.getText().toString());
            jsonObject.put("name", ed2.getText().toString());
            jsonObject.put("designation", ed3.getText().toString());
            jsonObject.put("department", ed4.getText().toString());
            jsonArray.put(jsonObject);
            object.put("employees", jsonArray);
            //give any path where you want to store data
            FileWriter fileWriter = new FileWriter("/Users/harpr/Downloads/empdata.json");
            fileWriter.write(object.toString());
            //if any buffer data then flush it
            fileWriter.flush();
            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void readJson(View view) {
        try {

            //to read the file &  where is file located
            //data is in filereader internally i.e present in byte array format
            FileReader reader = new FileReader("/Users/harpr/Downloads/empdata.json");
            //convert the data into string format
            String msg = "";
            //read method will read the single character & return in form of integer
            int i = reader.read();
            // return -1 if file ends
            while (i != -1) {
                msg = msg + (char) i;
                i = reader.read();
            }
            //json data is avaialble in string msg
            JSONObject jsonObject = new JSONObject(msg);
            //json object contains an array
            JSONArray jsonArray = jsonObject.getJSONArray("employees");
            //now array contains individual several objects
            for (int j = 0; j <= jsonArray.length(); j++) {
                JSONObject individual_obj = jsonArray.getJSONObject(j);
                Toast.makeText(getApplicationContext(),
                        individual_obj.getString("id") + "\n" +
                                individual_obj.getString("name") + "\n" +
                                individual_obj.getString("designation") + "\n" +
                                individual_obj.getString("department") + "\n",
                        Toast.LENGTH_LONG
                ).show();
            }

        } catch (Exception e) {

        }
    }
}