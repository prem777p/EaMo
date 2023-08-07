package com.prem.eamo.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prem.eamo.R;
import com.prem.eamo.databinding.FragmentBankDetailBinding;

public class BankDetail extends Fragment {

    FragmentBankDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bank_detail, container, false);
        binding = FragmentBankDetailBinding.bind(view);

        // ************************************************
        binding.withdrawBtn.setOnClickListener(view1 -> {
            String name = binding.bankHoldeNameEdtv.getText().toString();
            String bankName = binding.bankNameEdtv.getText().toString();
            String account = binding.bankAccountNumberEdtv.getText().toString();
            String ifsc = binding.bankIfscCodeEdtv.getText().toString();
            String amount = binding.amountEdtv.getText().toString();

        });





        return view;
    }
}