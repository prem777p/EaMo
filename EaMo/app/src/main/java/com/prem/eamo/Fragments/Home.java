package com.prem.eamo.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.prem.eamo.Activity.Earming.Earning;
import com.prem.eamo.Adapter.StoreAdapter;
import com.prem.eamo.R;
import com.prem.eamo.databinding.FragmentHomeBinding;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.Objects;

public class Home extends Fragment {

    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        binding = FragmentHomeBinding.bind(view);

        binding.bannerContainer.addData(new CarouselItem("https://static.vecteezy.com/system/resources/previews/002/453/548/original/sale-discount-banner-template-promotion-illustration-free-vector.jpg"));
        binding.bannerContainer.addData(new CarouselItem("https://d1csarkz8obe9u.cloudfront.net/posterpreviews/modern-black-friday-sale-facebook-banner-template-design-b294cd4a4197410381b03b41d8d6e2cb_screen.jpg?ts=1561415608"));
        binding.bannerContainer.addData(new CarouselItem("https://static.vecteezy.com/system/resources/previews/002/453/548/original/sale-discount-banner-template-promotion-illustration-free-vector.jpg"));

        StoreAdapter storeAdapter = new StoreAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.storeRv.setLayoutManager(gridLayoutManager);
        binding.storeRv.setAdapter(storeAdapter);

        binding.withdrawBtn.setOnClickListener(view1 -> startActivity(new Intent(getContext(), Earning.class)));

        //********************************
        binding.telegramGroup.setOnClickListener(view1 -> {});
        binding.moneyTv.setText("₹ 0.00");

        return view;
    }
}