package com.prem.eamo.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.prem.eamo.Activity.Earming.Earning;
import com.prem.eamo.Activity.Home.ProfileEdit;
import com.prem.eamo.Activity.Starting_Activity.Login;
import com.prem.eamo.R;
import com.prem.eamo.Utilities.SessionManager;
import com.prem.eamo.databinding.FragmentProfileBinding;
public class Profile extends Fragment {

    FragmentProfileBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        binding = FragmentProfileBinding.bind(view);

        binding.homeBtn.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().popBackStack();
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_fl, new Home()).commit();
//            binding.backToolbarBtn.setImageResource(0);
        });

        binding.storeBtn.setOnClickListener(view1 -> requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_fl, new Partner()).addToBackStack(null).commit());
        binding.makeLinksBtn.setOnClickListener(view1 -> requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_fl, new LinkGenerator()).addToBackStack(null).commit());
        binding.accountBtn.setOnClickListener(view1 -> startActivity(new Intent(getContext(), ProfileEdit.class)));
        binding.myEarningBtn.setOnClickListener(view1 ->   startActivity(new Intent(getContext(), Earning.class)));
        binding.telegramBtn.setOnClickListener(view1 -> {}); //***********************************

        binding.logoutBtn.setOnClickListener(view1 -> {
            new SessionManager(requireContext()).logout();
            startActivity(new Intent(getContext(), Login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        });

        return view;
    }
}