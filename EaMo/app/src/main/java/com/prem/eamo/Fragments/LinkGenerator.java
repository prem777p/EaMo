package com.prem.eamo.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prem.eamo.R;
import com.prem.eamo.databinding.FragmentLinkGeneraterBinding;


public class LinkGenerator extends Fragment {

    FragmentLinkGeneraterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_link_generater, container, false);
        binding = FragmentLinkGeneraterBinding.bind(view);

        String link = binding.pasteLinkEdt.getText().toString();

        binding.clearBtn.setOnClickListener(view1 -> binding.pasteLinkEdt.setText(""));

        //**************************************
        binding.linkConvertBtn.setOnClickListener(view1 ->{} );
        binding.copyToClipBoardBtn.setOnClickListener(view1 -> {});
        binding.postBtn.setOnClickListener(view1 -> {});
        binding.shareBtn.setOnClickListener(view1 -> {});


        return view;
    }
}