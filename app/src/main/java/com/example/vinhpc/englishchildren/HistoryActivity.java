package com.example.vinhpc.englishchildren;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity {

    private String jsonResult;

    TextView result;
    Button exit;
    ArrayList<Account> accountList;
    String username;
    int score;
    String time;
    String chuoi = "";
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        result = (TextView)findViewById(R.id.viewResult);
        result.setMovementMethod(new ScrollingMovementMethod());
        exit = (Button)findViewById(R.id.btnExit);

        accountList = new ArrayList<>();

        //
        //PostSession();
        loadHistory();

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void loadHistory(){
        sessionManager = new SessionManager(this);
        sessionManager.isLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        final String ssusername = user.get(sessionManager.USERNAME);

        String URL_HISTORY = "http://ecgame.000webhostapp.com/loadhistory.php?username="+ssusername;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_HISTORY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject history = array.getJSONObject(i);

                                username = history.getString("username");
                                score = history.getInt("score");
                                time = history.getString("time");
                                int count = i + 1;
                                chuoi += count + "." + " " + username + ": " + score + " " + time + "\n";
                                count++;
                            }
                            result.setText(chuoi);


                        } catch (JSONException e) {
                            Toast.makeText(HistoryActivity.this, "Error!" + e.toString(), Toast.LENGTH_SHORT).show();
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
