package com.prem.eamo.Activity.Starting_Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.prem.eamo.Activity.Home.Home_Activity;
import com.prem.eamo.Fragments.Home;
import com.prem.eamo.Models.Url;
import com.prem.eamo.Utilities.SessionManager;
import com.prem.eamo.databinding.ActivityOtpBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import in.aabhasjindal.otptextview.OTPListener;

public class OTP extends AppCompatActivity {

    ActivityOtpBinding binding;
    private String otpConfirm;
    String name;
    String email;
    String phone;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        String activityFrom = bundle.getString("activity");

        if (activityFrom.equals("register")) {
            name = bundle.getString("name");
            email = bundle.getString("email");
            phone = bundle.getString("phone");
            password = bundle.getString("password");
        }


        binding.otpView.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {
                // fired when user types something in the Otp box
            }
            @Override
            public void onOTPComplete(String otp) {
                // fired when user has entered the OTP fully.
              binding.verifyBtn.setOnClickListener(view -> {
                  if(otp.equals(otpConfirm)){
                      if (activityFrom.equals("register")) {
                          registerByOtp(name,email,phone,password);
                      }
                      else {
                          addLocalData(email);
                          startActivity(new Intent(OTP.this, Home.class));
                      }
                  }else {
                      Toast.makeText(OTP.this, "Wrong OTP " + otp,  Toast.LENGTH_SHORT).show();
                  }
              });
            }
        });

        binding.idTv.setText(email);
    }

    private void registerByOtp(String name, String email, String phone, String password) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Loading please wait...!");
        progressDialog.show();

        // make an object for volley library
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,                                    // send value
                Url.REGISTER_URL,                                     // url of server
                response -> {
                    progressDialog.hide();

                    if (response.trim().equals("fail")) {
                        Toast.makeText(this, "Try after sometime!" , Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(OTP.this, SignUp.class));
                    } else if (response.trim().equals("success")) {
                        SessionManager sessionManager = new SessionManager(this);
                        sessionManager.setUserData(email);

                        addLocalData(email);
                        startActivity(new Intent(OTP.this, Home_Activity.class));
                        Toast.makeText(this, "Successful Register" , Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(this, "Email Already Present!" , Toast.LENGTH_SHORT).show();
                    }
                },
                error -> progressDialog.hide()
        ){
            @Override
            protected Map<String, String> getParams() {
                HashMap<String,String> hm = new HashMap<>();
                hm.put("name", name);
                hm.put("phone", phone);
                hm.put("email",email);
                hm.put("password",password);

                return hm;
            }
        };
        requestQueue.add(stringRequest);
    }

    // store user data at local database
    private void addLocalData(String email)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,                                    // send value
                Url.GETUSERDATA_URL,                                     // url of server
                response -> {
                        // get json object
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.optJSONArray("response_obj");       // get json array

                            // to traverse the JSON Array
                            for (int i = 0; i < Objects.requireNonNull(jsonArray).length(); i++) {

                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);  // get json obj

                                String nameServer = jsonObject1.optString("name");
                                String emailServer = jsonObject1.optString("email");
                                String phoneServer = jsonObject1.optString("phone");

                                new SessionManager(this).setUserData(nameServer, emailServer, phoneServer);

                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                }, error -> {
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                HashMap<String,String> hm = new HashMap<>();
                hm.put("email",email);
                return hm;
            }
        };

        requestQueue.add(stringRequest);
    }
}