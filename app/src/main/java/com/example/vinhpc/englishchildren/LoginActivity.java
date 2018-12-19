package com.example.vinhpc.englishchildren;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText usernames, passwords;
    Button login;
    TextView link_register;
    private static String URL_LOGIN = "http://ecgame.000webhostapp.com/login.php";
    String mName = "";
    String mPass = "";

    SessionManager sessionManager;

    Intent intentRegister, intentMainScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);

        usernames = (EditText)findViewById(R.id.name);
        passwords = (EditText)findViewById(R.id.pass);
        login = (Button)findViewById(R.id.login);
        link_register = (TextView)findViewById(R.id.register);

        intentRegister = new Intent(this, RegisterActivity.class);
        intentMainScreen = new Intent(this, MainScreeenActivity.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mName = usernames.getText().toString().trim();
                mPass = passwords.getText().toString().trim();

                if (!mName.isEmpty() || !mPass.isEmpty()) {
                    Login(mName, mPass);
                }else {
                    usernames.setError("Vui lòng nhập Tên");
                    passwords.setError("Vui lòng nhập Mật khẩu");
                }
            }
        });

        link_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentRegister);
                finish();
            }
        });

    }

    private void Login(final String username, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String username = object.getString("username").trim();

                                    sessionManager.createSession(username);

                                    intentMainScreen.putExtra("username", username);
                                    startActivity(intentMainScreen);
                                    finish();

                                }

                            } if (success.equals("0")){
                                Toast.makeText(LoginActivity.this, "Success Fail. \nYour Name: "+username, Toast.LENGTH_SHORT).show();
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
