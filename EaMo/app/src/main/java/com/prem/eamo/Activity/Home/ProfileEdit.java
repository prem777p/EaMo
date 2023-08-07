package com.prem.eamo.Activity.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.prem.eamo.Utilities.SessionManager;
import com.prem.eamo.databinding.ActivityProfileEditBinding;

import java.util.HashMap;

public class ProfileEdit extends AppCompatActivity {

    ActivityProfileEditBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SessionManager sessionManager = new SessionManager(this);
        HashMap<String, String> hm = sessionManager.getUserData();
        binding.UsernameEditEdtv.setText((CharSequence) hm.get("KEY_NAME"));
        binding.phoneEditEdtv.setText((CharSequence) hm.get("KEY_PHONE"));
        binding.emailEditEdtv.setText((CharSequence) hm.get("KEY_EMAIL"));

        binding.backToolbarEditBtn.setOnClickListener(view -> onBackPressed());

        //*******************************
        saveLocalAndUpdateOnServer();
    }

    private void saveLocalAndUpdateOnServer() {
    }
}