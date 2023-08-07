package com.prem.eamo.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prem.eamo.R;
import com.prem.eamo.databinding.FragmentUpiPaymentBinding;

public class UPIPayment extends Fragment {

    FragmentUpiPaymentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upi_payment, container, false);
        binding = FragmentUpiPaymentBinding.bind(view);

        // ************************************************
        binding.withdrawUpiBtn.setOnClickListener(view1 -> {
            String upiId = binding.upiIdEdtv.getText().toString();
            String amount = binding.amountEdtv.getText().toString();
        });

        return view;
    }
}