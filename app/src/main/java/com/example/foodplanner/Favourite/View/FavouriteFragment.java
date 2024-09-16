package com.example.foodplanner.Favourite.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Authorization.AuthorizActivity;
import com.example.foodplanner.Favourite.Presnter.FavouritePresenterImpl;
import com.example.foodplanner.FireBase.FireBaseRemoteDatasourceImpl;
import com.example.foodplanner.MainActivity;
import com.example.foodplanner.Model.DialogFragment;
import com.example.foodplanner.Model.ListenerDialog;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.RepoRoom.Room.MealsfavLocalDataSourceImpl;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.Base.RemoteDataSourceImpl;
import com.example.foodplanner.Profile.View.ListenerSignin;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment implements FavouriteInterface, FavLestener , ListenerDialog, ListenerSignin {
    static final String TAG = "FavouriteFragment";
    RecyclerView rvfavourite;
    MealsAdapterFavourite adapter;
    FavouritePresenterImpl favouritePresenter;
    TextView textView;
    Meal delmeal;
    Button btnLogin;



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvfavourite = view.findViewById(R.id.rvFavourite);
        btnLogin = view.findViewById(R.id.btnLogin);
        adapter = new MealsAdapterFavourite(new ArrayList<>(), getContext(),this);
        rvfavourite.setAdapter(adapter);
        textView = view.findViewById(R.id.textIsEmpty);
        if(!(MainActivity.statUser).equals("guest")){

        favouritePresenter = new FavouritePresenterImpl(this,ReposateryImpl.getInstance(RemoteDataSourceImpl.getInstance(), FireBaseRemoteDatasourceImpl.getInstance(), MealsfavLocalDataSourceImpl.getInstance(this.getContext())));
        favouritePresenter.getListOfFavMeal();
        }else {
            DialogFragment dialogFragment = DialogFragment.newInstance(R.string.confirmation,R.string.loginmessage,R.drawable.kawali,R.string.login,R.string.cancel,true,(ListenerSignin) this);
            dialogFragment.show(getChildFragmentManager(), null);
            textView.setText(getString(R.string.loginfirst));
            btnLogin.setVisibility(View.VISIBLE);
            btnLogin.setOnClickListener(v -> {
                startActivity(new Intent(getActivity(), AuthorizActivity.class));
                requireActivity().finish();
            });
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }


    @Override
    public void showFavMeals(List<Meal> list) {
        Log.d(TAG, "showFavMeals: "+list);
        adapter.updatedata(list);
        ifIsEmpty(adapter.meals.isEmpty());

    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMsg(String msg) {
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ifIsEmpty(boolean b) {
        textView.setVisibility(b ? View.VISIBLE : View.GONE);
    }

    @Override
    public void deletedSuccessfully(Meal meal) {
        adapter.meals.remove(meal);
        adapter.updatedata(adapter.meals);
        ifIsEmpty(adapter.meals.isEmpty());
    }

    @Override
    public void delete(Meal meal) {
        delmeal = meal;
        DialogFragment dialogFragment = DialogFragment.newInstance(R.string.confirmation,R.string.areyousure,R.drawable.baseline_delete_forever_24,R.string.delete,R.string.cancel);
        dialogFragment.show(getChildFragmentManager(), null);
    }

    @Override
    public void onOkClicked() {
        favouritePresenter.deleteFromFav(delmeal);
    }

    @Override
    public void okSignin() {
        startActivity(new Intent(getActivity(), AuthorizActivity.class));
        requireActivity().finish();
    }
}