package com.example.foodplanner.Home.View;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Authorization.AuthorizActivity;
import com.example.foodplanner.FireBase.FireBaseRemoteDatasourceImpl;
import com.example.foodplanner.MainActivity;
import com.example.foodplanner.Model.AllCountries;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.NetworkUtil;
import com.example.foodplanner.Model.NoInternetDialog;
import com.example.foodplanner.Model.RepoRoom.Room.MealsfavLocalDataSourceImpl;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.Base.RemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.Home.Presenter.HomePresenterImpl;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class HomeFragment extends Fragment implements HomeView {
    RecyclerView rvCountry, rvCategory, rvMayLike;
    CountryAdapterNew countryAdapterNew;
    CategoryAdapterNew categoryAdapterNew;
    MayLikeAdapter mayLikeAdapter;
    HomePresenterImpl homePresenter;
    ImageView dailyInspiration, btnLogin;
    TextView nameOfMeal, backGroundProgressPar;
    ProgressBar progressBar;
    Button btnToDailsfromrandomMeal;
    SharedPreferences sp, sp2;

    SharedPreferences.Editor editor ;
    Meal random;
    volatile static Boolean internetFlag = false;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    final String TAG = "HomeFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sp = requireActivity().getSharedPreferences("date", MODE_PRIVATE);
        sp2 = requireActivity().getSharedPreferences("meal", MODE_PRIVATE);

        dailyInspiration = view.findViewById(R.id.imageDaily);
        btnLogin = view.findViewById(R.id.btnLogin);
        nameOfMeal = view.findViewById(R.id.nameOfDailyMeal);
        rvCountry = view.findViewById(R.id.rvCountry);
        rvCategory = view.findViewById(R.id.rvcategory);
        rvMayLike = view.findViewById(R.id.rvYouMightLike);
        backGroundProgressPar = view.findViewById(R.id.backProgrespar);
        progressBar = view.findViewById(R.id.progressBar2);
        btnToDailsfromrandomMeal = view.findViewById(R.id.btnFromRandomMealToDetails);
        countryAdapterNew = new CountryAdapterNew(AllCountries.getInstance().getAllCountries(), getContext(), getChildFragmentManager());

        categoryAdapterNew = new CategoryAdapterNew(new ArrayList<>(), getContext(), getChildFragmentManager());
        mayLikeAdapter = new MayLikeAdapter(new ArrayList<>(), getContext(), getChildFragmentManager());
        rvCountry.setAdapter(countryAdapterNew);
        rvCategory.setAdapter(categoryAdapterNew);
        rvMayLike.setAdapter(mayLikeAdapter);
        homePresenter = new HomePresenterImpl(ReposateryImpl.getInstance(RemoteDataSourceImpl.getInstance(), FireBaseRemoteDatasourceImpl.getInstance(), MealsfavLocalDataSourceImpl.getInstance(this.getContext())), this);
        if (sp.getString("today", "null").equals("null") ||
                !(sp.getString("today", "null").equals(LocalDate.now().toString()))||
        sp2.getString("random", "null").equals("null")) {
            editor = sp.edit();
            editor.putString("today", LocalDate.now().toString());
            editor.commit();
            homePresenter.fetchRandomMeal();
        } else
            showrandMeal(new Gson().fromJson(sp2.getString("random", null), Meal.class));

        btnToDailsfromrandomMeal.setOnClickListener(v -> {
            if (internetFlag) {
                HomeFragmentDirections.ActionNavigationHomeToDetailsFragment action =
                        HomeFragmentDirections.actionNavigationHomeToDetailsFragment(random.getIdMeal(), null);
                Navigation.findNavController(view).navigate(action);
            } else {
                NoInternetDialog d = new NoInternetDialog();
                d.show(getChildFragmentManager(), null);
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        compositeDisposable.add(NetworkUtil.observeNetworkConnectivity(requireContext())
                .distinctUntilChanged().subscribe(e -> {
                    internetFlag = e;
                    if (!e) {
                        NoInternetDialog d = new NoInternetDialog();
                        d.show(getChildFragmentManager(), null);
                    }
                }));
        if ("guest".equals(MainActivity.statUser)) {
            btnLogin.setVisibility(View.VISIBLE);
            btnLogin.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), AuthorizActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                requireActivity().finish();
            });
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }

    @Override
    public void showCountry() {

    }

    @Override
    public void showMeal(List<Meal> meals) {
        mayLikeAdapter.updatedata(meals);
    }

    @Override
    public void showrandMeal(Meal meal) {
        random = meal;
        nameOfMeal.setText(meal.getStrMeal());
        Glide.with(getContext())
                .load(meal.getStrMealThumb())
                .into(dailyInspiration);
    }

    @Override
    public void showMealByFirstLetter(List<Meal> meals) {

        mayLikeAdapter.updatedata(meals);
    }

    @Override
    public void showCategory(List<Categories> categories) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            backGroundProgressPar.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }, 1000);
        categoryAdapterNew.updatedata(categories);
    }

    @Override
    public void showErrorMsg(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void saveRandomMeal(Meal meal) {
        editor = sp2.edit();
        editor.putString("random", new Gson().toJson(meal));
        editor.commit();
    }

}