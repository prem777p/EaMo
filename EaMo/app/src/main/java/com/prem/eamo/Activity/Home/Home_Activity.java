package com.prem.eamo.Activity.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.prem.eamo.Fragments.Home;
import com.prem.eamo.Fragments.LinkGenerator;
import com.prem.eamo.Fragments.Partner;
import com.prem.eamo.Fragments.Profile;
import com.prem.eamo.R;
import com.prem.eamo.databinding.ActivityHomeBinding;

import java.util.Stack;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class Home_Activity extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getSupportFragmentManager().beginTransaction().add(R.id.home_fl, new Home()).commit();
        binding.backToolbarBtn.setOnClickListener(view -> onBackPressed());

        binding.bottomNevBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NonNull AnimatedBottomBar.Tab tab1) {
                int id = tab1.getId();
                if(id == R.id.home_bar)
                {
                    getSupportFragmentManager().popBackStack();
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_fl, new Home()).commit();
                    binding.backToolbarBtn.setImageResource(0);
                }
                else if (id == R.id.partner_bar)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_fl, new Partner()).addToBackStack(null).commit();
                    binding.backToolbarBtn.setImageResource(R.drawable.icon_left_back);
                }
                else if (id == R.id.linkConvert_bar)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_fl, new LinkGenerator()).addToBackStack(null).commit();
                    binding.backToolbarBtn.setImageResource(R.drawable.icon_left_back);
                }
                else if (id == R.id.profile_bar)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_fl, new Profile()).addToBackStack(null).commit();
                    binding.backToolbarBtn.setImageResource(R.drawable.icon_left_back);
                }
            }

            @Override
            public void onTabReselected(int i, @NonNull AnimatedBottomBar.Tab tab) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}