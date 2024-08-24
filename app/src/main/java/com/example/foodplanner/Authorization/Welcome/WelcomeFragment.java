package com.example.foodplanner.Authorization.Welcome;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.MainActivity;

public class WelcomeFragment extends Fragment {

    Button normalSignUp,login,guest;
    ImageButton googleSignUp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        normalSignUp = view.findViewById(R.id.btnNormalSign);
        normalSignUp.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_welcomeFragment_to_signUpFragment);
            //startActivity(new Intent(getActivity(), MainActivity.class));
        });
        login = view.findViewById(R.id.btnToLogin);
        login.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_welcomeFragment_to_signinFragment);
        });
        guest = view.findViewById(R.id.btnguest);
        guest.setOnClickListener(v -> {

            Navigation.findNavController(v).navigate(R.id.action_welcomeFragment_to_mainActivity);
            //startActivity(new Intent(getActivity(), MainActivity.class));
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }
}