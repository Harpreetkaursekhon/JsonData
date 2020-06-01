package com.app.rwjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4;
Button b1, b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);
        ed3=(EditText)findViewById(R.id.ed3);
        ed4=(EditText)findViewById(R.id.ed4);
        b1=(Button) findViewById(R.id.btn1);
        b2=(Button) findViewById(R.id.btn2);
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
            FileWriter fileWriter=new FileWriter("//empdata.json");
            fileWriter.write(object.toString());
            fileWriter.flush();
            fileWriter.close();

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

    }
    public void readJson(View view) {
    }
}
