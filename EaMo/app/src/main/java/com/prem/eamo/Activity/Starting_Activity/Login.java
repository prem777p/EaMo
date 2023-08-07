package com.prem.eamo.Activity.Starting_Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.prem.eamo.Activity.Home.Home_Activity;
import com.prem.eamo.Models.Url;
import com.prem.eamo.Utilities.SessionManager;
import com.prem.eamo.databinding.ActivityLoginBinding;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;
    int PASSWORD_LENGTH = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.skrip.setOnClickListener(view -> startActivity(new Intent(this, Home_Activity.class)));
        binding.btnSignUp.setOnClickListener(view -> startActivity(new Intent(this, SignUp.class)));

        binding.loginBtn.setOnClickListener(view -> {
            String email = binding.emailEdtv.getText().toString();
            String password = binding.passwordEdtv.getText().toString();

            if (email.isEmpty()) {
                binding.emailEdtv.setError("Enter Email");
            } else if (password.length() < PASSWORD_LENGTH) {
                binding.passwordEdtv.setError("Enter 8 digit Password");
            } else {
                loginUser(email,password);
            }
        });


//        binding.forgetBtn.setOnClickListener();
    }

    public void loginUser(String email, String password) {
        // progress dialog
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Loading please wait...!");
        progressDialog.show();


        // make an object for volley library
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // response listener
        // error listener
        // error listener
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,                                    // send value
                Url.LOGIN_URL,                                     // url of server
                response -> {
                    progressDialog.hide();
                    if (response.trim().equals("fail")) {
                        Toast.makeText(Login.this, "Wrong Password!", Toast.LENGTH_SHORT).show();
                    } else if (response.trim().equals("success")) {

                        SessionManager sessionManager = new SessionManager(this);
                        sessionManager.setUserData(email);

                        startActivity(new Intent(Login.this, OTP.class).putExtra("activity","login"));
                    } else {
                        Toast.makeText(Login.this, "Email not Found!", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    progressDialog.hide();
                    Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
                    Log.e("connection", String.valueOf(error));
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> hm = new HashMap<>();
                hm.put("email", email);
                hm.put("password", password);

                return hm;
            }
        };

        requestQueue.add(stringRequest);
    }

}