package com.prem.eamo.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prem.eamo.R;
import com.prem.eamo.databinding.FragmentPartnerBinding;

public class Partner extends Fragment {

    FragmentPartnerBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_partner, container, false);
        binding = FragmentPartnerBinding.bind(view);



        return view;
    }
}