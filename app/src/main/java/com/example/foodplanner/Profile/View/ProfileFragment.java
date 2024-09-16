package com.example.foodplanner.Profile.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.example.foodplanner.FireBase.FireBaseCallback;
import com.example.foodplanner.FireBase.FireBaseRemoteDatasourceImpl;
import com.example.foodplanner.MainActivity;
import com.example.foodplanner.Model.DialogFragment;
import com.example.foodplanner.Model.ListenerDialog;
import com.example.foodplanner.Model.MealWithDay;
import com.example.foodplanner.Model.NetworkUtil;
import com.example.foodplanner.Model.NoInternetDialog;
import com.example.foodplanner.Model.RepoRoom.Room.MealsfavLocalDataSourceImpl;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.Base.RemoteDataSourceImpl;
import com.example.foodplanner.Profile.Presenter.ProfilePresenter;
import com.example.foodplanner.Profile.Presenter.ProfilePresenterImpl;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class ProfileFragment extends Fragment implements ProfileInterface, ListenerProfile, ListenerDialog, ListenerLogOut, ListenerSignin, FireBaseCallback {
    //    final String TAG = "ProfileFragment";
    TextView email, textNoMealSunday, textNoMealMonday, textNoMealTuesday, textNoMealWednesday, textNoMealThursday, textNoMealFriday, textNoMealSaturday;
    ProfilePresenter profilePresenter;
    RecyclerView rvDay1, rvDay2, rvDay3, rvDay4, rvDay5, rvDay6, rvDay7;
    PlanAdaoter planAdapter1, planAdapter2, planAdapter3, planAdapter4, planAdapter5, planAdapter6, planAdapter7;
    ImageButton btnSignOut, btnBackup, btnDownload;
    MealWithDay delMeal;
    volatile Boolean checkInternet = false;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

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

        textNoMealSunday = view.findViewById(R.id.textNoMealSunday);
        textNoMealMonday = view.findViewById(R.id.textNoMealMonday);
        textNoMealTuesday = view.findViewById(R.id.textNoMealTuesday);
        textNoMealWednesday = view.findViewById(R.id.textNoMealWednesday);
        textNoMealThursday = view.findViewById(R.id.textNoMealThursday);
        textNoMealFriday = view.findViewById(R.id.textNoMealFriday);
        textNoMealSaturday = view.findViewById(R.id.textNoMealSaturday);

        btnSignOut = view.findViewById(R.id.btnLogedOut);
        btnBackup = view.findViewById(R.id.btnUpLoadToFireBase);
        btnDownload = view.findViewById(R.id.btnDownloadFromFireBase);


        planAdapter1 = new PlanAdaoter(new ArrayList<>(), this.getContext(), this);
        planAdapter2 = new PlanAdaoter(new ArrayList<>(), this.getContext(), this);
        planAdapter3 = new PlanAdaoter(new ArrayList<>(), this.getContext(), this);
        planAdapter4 = new PlanAdaoter(new ArrayList<>(), this.getContext(), this);
        planAdapter5 = new PlanAdaoter(new ArrayList<>(), this.getContext(), this);
        planAdapter6 = new PlanAdaoter(new ArrayList<>(), this.getContext(), this);
        planAdapter7 = new PlanAdaoter(new ArrayList<>(), this.getContext(), this);

        rvDay1.setAdapter(planAdapter1);
        rvDay2.setAdapter(planAdapter2);
        rvDay3.setAdapter(planAdapter3);
        rvDay4.setAdapter(planAdapter4);
        rvDay5.setAdapter(planAdapter5);
        rvDay6.setAdapter(planAdapter6);
        rvDay7.setAdapter(planAdapter7);
        if (!(MainActivity.statUser.equals("guest"))) {
            profilePresenter = new ProfilePresenterImpl(ReposateryImpl.getInstance(RemoteDataSourceImpl.getInstance(), FireBaseRemoteDatasourceImpl.getInstance(), MealsfavLocalDataSourceImpl.getInstance(this.getContext())), this);
            btnSignOut.setOnClickListener(v -> {
                if (checkInternet) {
                    DialogFragment dialogFragment = DialogFragment.newInstance(R.string.confirmation, R.string.logoutmessage, R.drawable.ic_logout, R.string.logout, R.string.cancel, true, (ListenerLogOut) this);
                    dialogFragment.show(getChildFragmentManager(), null);
                } else {
                    NoInternetDialog d = new NoInternetDialog();
                    d.show(getChildFragmentManager(), null);
                }

            });
            btnBackup.setOnClickListener(v -> {
                if (checkInternet)
                    profilePresenter.backupAllMeals(this);
                else {
                    NoInternetDialog d = new NoInternetDialog();
                    d.show(getChildFragmentManager(), null);
                }
            });

            btnDownload.setOnClickListener(v -> {
                if (checkInternet)
                    profilePresenter.downloadAll(this);
                else {
                    NoInternetDialog d = new NoInternetDialog();
                    d.show(getChildFragmentManager(), null);
                }
            });
        } else {
            btnSignOut.setOnClickListener(v -> {
                DialogFragment dialogFragment = DialogFragment.newInstance(R.string.confirmation, R.string.loginmessage, R.drawable.ic_logout, R.string.login, R.string.cancel, true, (ListenerSignin) this);
                dialogFragment.show(getChildFragmentManager(), null);
            });
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        compositeDisposable.add(NetworkUtil.observeNetworkConnectivity(requireContext())
                .distinctUntilChanged().subscribe(e -> {
                    checkInternet = e;
                    if (!e) {
                        NoInternetDialog d = new NoInternetDialog();
                        d.show(getChildFragmentManager(), null);
                    }
                }, e -> {
                }));
    }

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }


    @Override
    public void setEmail(String email) {
        this.email.setText(getString(R.string.email) + ": " + email);
    }

    @Override
    public void setDay1(List<MealWithDay> mealWithDays) {
        if (mealWithDays.isEmpty()) {
            textNoMealSunday.setVisibility(View.VISIBLE);
        } else {
            textNoMealSunday.setVisibility(View.INVISIBLE);
        }
        planAdapter1.updatedata(mealWithDays);
    }

    @Override
    public void setDay2(List<MealWithDay> mealWithDays) {
        if (mealWithDays.isEmpty()) {
            textNoMealMonday.setVisibility(View.VISIBLE);
        } else {
            textNoMealMonday.setVisibility(View.INVISIBLE);
        }
        planAdapter2.updatedata(mealWithDays);
    }

    @Override
    public void setDay3(List<MealWithDay> mealWithDays) {
        if (mealWithDays.isEmpty()) {
            textNoMealTuesday.setVisibility(View.VISIBLE);
        } else {
            textNoMealTuesday.setVisibility(View.INVISIBLE);
        }
        planAdapter3.updatedata(mealWithDays);
    }

    @Override
    public void setDay4(List<MealWithDay> mealWithDays) {
        if (mealWithDays.isEmpty()) {
            textNoMealWednesday.setVisibility(View.VISIBLE);
        } else {
            textNoMealWednesday.setVisibility(View.INVISIBLE);
        }
        planAdapter4.updatedata(mealWithDays);
    }

    @Override
    public void setDay5(List<MealWithDay> mealWithDays) {
        if (mealWithDays.isEmpty()) {
            textNoMealThursday.setVisibility(View.VISIBLE);
        } else {
            textNoMealThursday.setVisibility(View.INVISIBLE);
        }
        planAdapter5.updatedata(mealWithDays);
    }

    @Override
    public void setDay6(List<MealWithDay> mealWithDays) {
        if (mealWithDays.isEmpty()) {
            textNoMealFriday.setVisibility(View.VISIBLE);
        } else {
            textNoMealFriday.setVisibility(View.INVISIBLE);
        }
        planAdapter6.updatedata(mealWithDays);
    }

    @Override
    public void setDay7(List<MealWithDay> mealWithDays) {
        if (mealWithDays.isEmpty()) {
            textNoMealSaturday.setVisibility(View.VISIBLE);
        } else {
            textNoMealSaturday.setVisibility(View.INVISIBLE);
        }
        planAdapter7.updatedata(mealWithDays);
    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMsg(String msg) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        new Handler().postDelayed(() -> Objects.requireNonNull(requireActivity()).finish(), 1000);
//        getActivity().finish();
    }

    @Override
    public void deleteed(MealWithDay mealWithDay) {
        switch (mealWithDay.getDay()) {
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
//        profilePresenter.clear();
    }

    @Override
    public void delete(MealWithDay mealWithDay) {
        delMeal = mealWithDay;
        DialogFragment dialogFragment = DialogFragment.newInstance(R.string.confirmation, R.string.areyousure, R.drawable.baseline_delete_forever_24, R.string.delete, R.string.cancel);
        dialogFragment.show(getChildFragmentManager(), null);
    }

    @Override
    public void onOkClicked() {
        profilePresenter.delete(delMeal);
        Toast.makeText(getContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void okLogOut() {
        profilePresenter.logout();
        Toast.makeText(getActivity(), "Logout", Toast.LENGTH_SHORT).show();
        requireActivity();
        SharedPreferences sp = Objects.requireNonNull(requireActivity()).getSharedPreferences("userdetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("user", "guest");
        editor.apply();
        startActivity(new Intent(getActivity(), AuthorizActivity.class));
        finish();
    }

    @Override
    public void okSignin() {
        startActivity(new Intent(getActivity(), AuthorizActivity.class));
        finish();
    }

    @Override
    public void onSuccess(String message) {
        try {
            Log.d("kero", "onSuccess try: "+message);
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("kero", "onSuccess catch: " + e.getMessage());
        }
    }

    @Override
    public void onFailure(String errorMsg) {
        try {
            Log.d("kero", "onSuccess try error: "+errorMsg);
//            Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("kero", "onFailure: " + e.getMessage());
        }
    }
}