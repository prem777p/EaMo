package com.prem.eamo.Activity.Earming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.prem.eamo.Fragments.BankDetail;
import com.prem.eamo.Fragments.UPIPayment;
import com.prem.eamo.R;
import com.prem.eamo.databinding.ActivityEarningBinding;

public class Earning extends AppCompatActivity {

    ActivityEarningBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEarningBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backToolbarEditBtn.setOnClickListener(view -> onBackPressed());
        binding.upiIdBtn.setOnClickListener(view -> getSupportFragmentManager().beginTransaction().replace(R.id.payment_fl, new UPIPayment()).commit());
        binding.bankDetailBtn.setOnClickListener(view -> getSupportFragmentManager().beginTransaction().replace(R.id.payment_fl, new BankDetail()).commit());

        //********************************
        updateEarning();
    }

    private void updateEarning() {

    }
}