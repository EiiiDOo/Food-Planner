package com.example.foodplanner.Profile.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Authorization.AuthorizActivity;
import com.example.foodplanner.FireBase.FireBaseRemoteDatasourceImpl;
import com.example.foodplanner.Model.MealWithDay;
import com.example.foodplanner.Model.RepoRoom.Room.MealsfavLocalDataSourceImpl;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.Base.RemoteDataSourceImpl;
import com.example.foodplanner.Profile.Presenter.ProfilePresenter;
import com.example.foodplanner.Profile.Presenter.ProfilePresenterImpl;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment implements ProfileInterface,ListenerProfile {
    final String TAG = "ProfileFragment";
    TextView email;
    ProfilePresenter profilePresenter;
    RecyclerView rvDay1, rvDay2, rvDay3, rvDay4, rvDay5, rvDay6, rvDay7;
    PlanAdaoter planAdapter1, planAdapter2, planAdapter3, planAdapter4, planAdapter5, planAdapter6, planAdapter7;
    ImageButton btnSignOut;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = view.findViewById(R.id.textEmail);
        rvDay1 = view.findViewById(R.id.rvDay1);
        rvDay2 = view.findViewById(R.id.rvDay2);
        rvDay3 = view.findViewById(R.id.rvDay3);
        rvDay4 = view.findViewById(R.id.rvDay4);
        rvDay5 = view.findViewById(R.id.rvDay5);
        rvDay6 = view.findViewById(R.id.rvDay6);
        rvDay7 = view.findViewById(R.id.rvday7);

        btnSignOut = view.findViewById(R.id.btnLogedOut);
        btnSignOut.setOnClickListener(v ->
                {
                    profilePresenter.logout();
                    Toast.makeText(getActivity(), "Logout", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), AuthorizActivity.class));
                    finish();
                });

        planAdapter1 = new PlanAdaoter(new ArrayList<>(), this.getContext(),this);
        planAdapter2 = new PlanAdaoter(new ArrayList<>(), this.getContext(),this);
        planAdapter3 = new PlanAdaoter(new ArrayList<>(), this.getContext(),this);
        planAdapter4 = new PlanAdaoter(new ArrayList<>(), this.getContext(),this);
        planAdapter5 = new PlanAdaoter(new ArrayList<>(), this.getContext(),this);
        planAdapter6 = new PlanAdaoter(new ArrayList<>(), this.getContext(),this);
        planAdapter7 = new PlanAdaoter(new ArrayList<>(), this.getContext(),this);

        rvDay1.setAdapter(planAdapter1);
        rvDay2.setAdapter(planAdapter2);
        rvDay3.setAdapter(planAdapter3);
        rvDay4.setAdapter(planAdapter4);
        rvDay5.setAdapter(planAdapter5);
        rvDay6.setAdapter(planAdapter6);
        rvDay7.setAdapter(planAdapter7);

        profilePresenter = new ProfilePresenterImpl(ReposateryImpl.getInstance(RemoteDataSourceImpl.getInstance(), FireBaseRemoteDatasourceImpl.getInstance(), MealsfavLocalDataSourceImpl.getInstance(this.getContext())), this);
    }

    @Override
    public void setEmail(String email) {
        this.email.setText(email);
    }

    @Override
    public void setDay1(List<MealWithDay> mealWithDays) {
        planAdapter1.updatedata(mealWithDays);
    }

    @Override
    public void setDay2(List<MealWithDay> mealWithDays) {
        planAdapter2.updatedata(mealWithDays);
    }

    @Override
    public void setDay3(List<MealWithDay> mealWithDays) {
        planAdapter3.updatedata(mealWithDays);
    }

    @Override
    public void setDay4(List<MealWithDay> mealWithDays) {
        planAdapter4.updatedata(mealWithDays);
    }

    @Override
    public void setDay5(List<MealWithDay> mealWithDays) {
        planAdapter5.updatedata(mealWithDays);
    }

    @Override
    public void setDay6(List<MealWithDay> mealWithDays) {
        planAdapter6.updatedata(mealWithDays);
    }

    @Override
    public void setDay7(List<MealWithDay> mealWithDays) {
        planAdapter7.updatedata(mealWithDays);
    }

    @Override
    public void setDay(List<MealWithDay> mealWithDays, String day) {
        switch (day){
            case "1":
                planAdapter1.updatedata(planAdapter1.MealWithDay);
                break;
            case "2":
                planAdapter2.updatedata(planAdapter2.MealWithDay);
                break;
            case "3":
                planAdapter3.updatedata(planAdapter3.MealWithDay);
                break;
            case "4":
                planAdapter4.updatedata(planAdapter4.MealWithDay);
                break;
            case "5":
                planAdapter5.updatedata(planAdapter5.MealWithDay);
                break;
            case "6":
                planAdapter6.updatedata(planAdapter6.MealWithDay);
                break;
            case "7":
                planAdapter7.updatedata(planAdapter7.MealWithDay);
                break;
        }
    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        new Handler().postDelayed(() -> getActivity().finish(), 1000);
//        getActivity().finish();
    }

    @Override
    public void deleteed(MealWithDay mealWithDay) {
        switch (mealWithDay.getDay()){
            case "1":
                planAdapter1.MealWithDay.remove(mealWithDay);
                planAdapter1.updatedata(planAdapter1.MealWithDay);
                break;
            case "2":
                planAdapter2.MealWithDay.remove(mealWithDay);
                planAdapter2.updatedata(planAdapter2.MealWithDay);
                break;
            case "3":
                planAdapter3.MealWithDay.remove(mealWithDay);
                planAdapter3.updatedata(planAdapter3.MealWithDay);
                break;
            case "4":
                planAdapter4.MealWithDay.remove(mealWithDay);
                planAdapter4.updatedata(planAdapter4.MealWithDay);
                break;
            case "5":
                planAdapter5.MealWithDay.remove(mealWithDay);
                planAdapter5.updatedata(planAdapter5.MealWithDay);
                break;
            case "6":
                planAdapter6.MealWithDay.remove(mealWithDay);
                planAdapter6.updatedata(planAdapter6.MealWithDay);
                break;
            case "7":
                planAdapter7.MealWithDay.remove(mealWithDay);
                planAdapter7.updatedata(planAdapter7.MealWithDay);
                break;


        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        profilePresenter.clear();
    }

    @Override
    public void delete(MealWithDay mealWithDay) {
        profilePresenter.delete(mealWithDay);
    }
}