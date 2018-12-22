package com.example.vinhpc.englishchildren;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HighestScoreActivity extends AppCompatActivity {

    private String jsonResult;
    public String URL_HIGHSCORE = "http://ecgame.000webhostapp.com/readaccount.php";
    TextView result;
    Button exit;
    ArrayList<Account> accountList;
    String username;
    int score;
    String chuoi = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highest_score);

        result = (TextView)findViewById(R.id.textView10);
        exit = (Button)findViewById(R.id.btnExit);

        accountList = new ArrayList<>();

        //
        loadAccount();

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void loadAccount(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_HIGHSCORE,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject account = array.getJSONObject(i);

                        username = account.getString("username");
                        score = account.getInt("score");

                        int count = i + 1;
                        chuoi += count + "." + " " + username + ": " + score + "\n";
                        count++;
                    }
                    result.setText(chuoi);


                } catch (JSONException e) {
                    Toast.makeText(HighestScoreActivity.this, "Error!" + e.toString(), Toast.LENGTH_SHORT).show();
//                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null) {
                            Toast.makeText(getApplicationContext(), "Lỗi" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
