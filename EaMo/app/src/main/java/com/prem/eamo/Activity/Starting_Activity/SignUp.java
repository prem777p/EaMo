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
import com.prem.eamo.Models.Url;
import com.prem.eamo.databinding.ActivitySignUpBinding;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signUpBtn.setOnClickListener(view -> signUpUser());
        binding.btnLogin.setOnClickListener(view -> startActivity(new Intent(this, Login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
    }

    private void signUpUser() {
        String name = binding.nameSignUpEdtv.getText().toString();
        String email = binding.emailSignUpEdtv.getText().toString();
        String phone = binding.phoneSignupEdtv.getText().toString();
        String password = binding.passwordSignupEdtv.getText().toString();
        String confirmPassword = binding.confirnPasswordSignupEdtv.getText().toString();

        if (phone.length() != 10) {
            binding.phoneSignupEdtv.setError("Enter Correct Phone no.");
        }
        else if (password.length() < 6 ) {
            binding.passwordSignupEdtv.setError("Enter 8 Digit Password");
        } else if (!password.equals(confirmPassword)) {
            binding.confirnPasswordSignupEdtv.setError("Password doesn't match");
        }else{
            // progress dialog
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Loading");
            progressDialog.setMessage("Loading please wait...!");
            progressDialog.show();

            // make an object for volley library
            RequestQueue requestQueue = Volley.newRequestQueue(this);

            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,                                    // send value
                    Url.GETUSERDATA_URL,                                     // url of server
                    response -> {
                        progressDialog.hide();
                        if (response.trim().equals("user not found")) {

                            Intent intent = new Intent(SignUp.this, OTP.class);
                            intent.putExtra("name", name);
                            intent.putExtra("email", email);
                            intent.putExtra("phone", phone);
                            intent.putExtra("password", password);
                            intent.putExtra("activity","register");
                            startActivity(intent);
                        } else {
                            Toast.makeText(this, "Email Already Present!" , Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> progressDialog.hide()
            ){
                @Override
                protected Map<String, String> getParams() {
                    HashMap<String,String> hm = new HashMap<>();
                    hm.put("email", email);
                    return hm;
                }
            };

            requestQueue.add(stringRequest);
        }
    }
}