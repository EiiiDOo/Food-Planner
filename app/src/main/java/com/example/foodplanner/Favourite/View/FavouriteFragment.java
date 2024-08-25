package com.example.foodplanner.Favourite.View;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Favourite.Presnter.FavouritePresenterImpl;
import com.example.foodplanner.FireBase.FireBaseRemoteDatasourceImpl;
import com.example.foodplanner.MainActivity;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.RepoRoom.Room.MealsfavLocalDataSourceImpl;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.Base.RemoteDataSourceImpl;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment implements FavouriteInterface, FavLestener {
    static final String TAG = "FavouriteFragment";
    RecyclerView rvfavourite;
    MealsAdapterFavourite adapter;
    FavouritePresenterImpl favouritePresenter;
    TextView textView;



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvfavourite = view.findViewById(R.id.rvFavourite);
        adapter = new MealsAdapterFavourite(new ArrayList<>(), getContext(),this);
        rvfavourite.setAdapter(adapter);
        textView = view.findViewById(R.id.textIsEmpty);
        if(!(MainActivity.statUser).equals("guest")){

        favouritePresenter = new FavouritePresenterImpl(this,ReposateryImpl.getInstance(RemoteDataSourceImpl.getInstance(), FireBaseRemoteDatasourceImpl.getInstance(), MealsfavLocalDataSourceImpl.getInstance(this.getContext())));
        favouritePresenter.getListOfFavMeal();
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
        favouritePresenter.deleteFromFav(meal);
    }

}