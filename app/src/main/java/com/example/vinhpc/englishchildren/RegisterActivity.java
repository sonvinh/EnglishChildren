package com.example.vinhpc.englishchildren;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernames, passwords;
    private Button btnRegist, btnCancle;
    private ProgressBar loading2;
    private static String URL_REGIST = "http://ecgame.000webhostapp.com/register.php";
    String username = "";
    String password = "";
    Intent intentLogin;
    Music music = new Music();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernames = (EditText)findViewById(R.id.textName);
        passwords = (EditText)findViewById(R.id.pass);
        btnRegist = (Button)findViewById(R.id.login);
        btnCancle = (Button)findViewById(R.id.button20);
        loading2 = (ProgressBar)findViewById(R.id.progressBar2);
        intentLogin = new Intent(this, LoginActivity.class);

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.ClickSound(RegisterActivity.this);
                btnRegist.setVisibility(View.INVISIBLE);
                btnCancle.setVisibility(View.INVISIBLE);
                loading2.setVisibility(View.VISIBLE);
                Register();
            }
        });

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentLogin);
                finish();
            }
        });
    }

    private void Register(){
        username = this.usernames.getText().toString().trim();
        password = this.passwords.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success =  jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                startActivity(intentLogin);
                                finish();
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Đăng ký thất bại!" + e.toString(), Toast.LENGTH_SHORT).show();
                            btnRegist.setVisibility(View.VISIBLE);
                            loading2.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Đăng ký thất bại!" + error.toString(), Toast.LENGTH_SHORT).show();
                        btnRegist.setVisibility(View.VISIBLE);
                        loading2.setVisibility(View.GONE);
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
