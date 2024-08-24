package com.example.foodplanner.Details.View;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Details.Presenter.DetailsPresenterImpl;
import com.example.foodplanner.FireBase.FireBaseRemoteDatasourceImpl;
import com.example.foodplanner.Model.AllCountries;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealWithDay;
import com.example.foodplanner.Model.RepoRoom.Room.MealsfavLocalDataSourceImpl;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.Base.RemoteDataSourceImpl;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class DetailsFragment extends Fragment implements DetailsView {
    final String TAG = "DetailsFragment";
    boolean fav = true, calend = true;
    TextView nameOfMeal;
    DetailsPresenterImpl detailsPresenter;
    ProgressBar progressBar;
    ImageButton addToFav, addToPlan;
    RecyclerView rvIngredients;
    TextView instractions, category, backGroundProgressBar;
    IngredientAdapterdetails ingredientAdapter;
    ImageView image, imageCountry;
    String id;
    WebView wv;
    Meal mealFromLastfragment, mealReadyToSave;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = DetailsFragmentArgs.fromBundle(getArguments()).getID();
        mealFromLastfragment = DetailsFragmentArgs.fromBundle(getArguments()).getMealObj();
        Log.d(TAG, "onCreate: " + id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBar2);
        backGroundProgressBar = view.findViewById(R.id.backProgrespar);
        nameOfMeal = view.findViewById(R.id.nameMeal);
        rvIngredients = view.findViewById(R.id.recyclerView);
        instractions = view.findViewById(R.id.instractions);
        category = view.findViewById(R.id.categoryText);
        imageCountry = view.findViewById(R.id.imageCountry);
        addToFav = view.findViewById(R.id.addTofav);
        addToPlan = view.findViewById(R.id.addToPlan);
        ingredientAdapter = new IngredientAdapterdetails(new ArrayList<>(), getContext());
        rvIngredients.setAdapter(ingredientAdapter);
        wv = view.findViewById(R.id.webView);
        image = view.findViewById(R.id.imageDaily);
        detailsPresenter = new DetailsPresenterImpl(ReposateryImpl.getInstance(RemoteDataSourceImpl.getInstance(), FireBaseRemoteDatasourceImpl.getInstance(), MealsfavLocalDataSourceImpl.getInstance(this.getContext())), this);
        Log.d(TAG, "onViewCreated: ");
        detailsPresenter.getFavMeals();
        if (mealFromLastfragment == null)
            detailsPresenter.fetchMealsById(id);
        else
            detailsPresenter.invokeShowMealWithObj(mealFromLastfragment);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void showMeal(List<Meal> meal) {
        mealReadyToSave = meal.get(0);
        setImgCalend(mealReadyToSave.transferToMealWithDay(mealReadyToSave));
        setImgIc(mealReadyToSave);
        hideProgressBar();
        setData(mealReadyToSave);
        if (meal.get(0).getStrYoutube() != null && !meal.get(0).getStrYoutube().isEmpty()) {
            wv.setVisibility(View.VISIBLE);
            wv.setWebViewClient(new WebViewClient());
            WebSettings webSettings = wv.getSettings();
            webSettings.setJavaScriptEnabled(true);
            wv.loadUrl(meal.get(0).getStrYoutube());
        } else
            wv.setVisibility(View.GONE);
    }
    @Override
    public void showMealWithObj(Meal meal) {
        mealReadyToSave = meal;
        setImgIc(meal);
        addToPlan.setImageDrawable(getContext().getDrawable(R.drawable.addedtocalend));
        hideProgressBar();
        setData(meal);

    }

    @Override
    public void showErrorMsg(String error) {
        Toast.makeText(this.getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getResourceId(String name) {
        for (Country c : AllCountries.getInstance().getAllCountries()) {
            if (c.getCountryName().equals(name)) {
                return c.getImageResourceId();
            }
        }
        return R.drawable.egypt;
    }



    @Override
    public void showSuccessMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    void setImgIc(Meal meal) {
        if (detailsPresenter.isFav(meal)) {
            addToFav.setImageDrawable(getContext().getDrawable(R.drawable.favblack));
            addToFav.setClickable(false);
        } else {
            addToFav.setImageDrawable(getContext().getDrawable(R.drawable.favwhite));
            addToFav.setClickable(true);
            addToFav.setOnClickListener(v -> {
                addToFav.setImageDrawable(getContext().getDrawable(R.drawable.favblack));
                Log.d(TAG, "onViewCreated: " + "insid");
                mealReadyToSave.setUserId(detailsPresenter.getUserId());
                detailsPresenter.addTofav(mealReadyToSave);
                addToFav.setOnClickListener(null);
            });
        }
    }

    void setImgCalend(MealWithDay meal) {
        if (detailsPresenter.isPlan(meal)) {
            addToPlan.setImageDrawable(getContext().getDrawable(R.drawable.addedtocalend));
            addToPlan.setEnabled(false);
        } else {
            addToPlan.setImageDrawable(getContext().getDrawable(R.drawable.addtocalend));
            addToPlan.setEnabled(true);
            addToPlan.setOnClickListener(v -> {
                addToPlan.setImageDrawable(getContext().getDrawable(R.drawable.addedtocalend));
                meal.setUserId(detailsPresenter.getUserId());
                meal.setDay("4");
                detailsPresenter.addPlan(meal);
                addToPlan.setOnClickListener(null);
            });
        }
    }

    void hideProgressBar() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            progressBar.setVisibility(View.INVISIBLE);
            backGroundProgressBar.setVisibility(View.INVISIBLE);
        }, 1500);
    }

    void setData(Meal meal) {
        nameOfMeal.setText(meal.getStrMeal());
        instractions.setText(meal.getStrInstructions());
        category.setText(meal.getStrCategory());
        imageCountry.setImageResource(getResourceId(meal.getStrArea()));
        Glide.with(getContext()).load(meal.getStrMealThumb()).placeholder(R.drawable.foodplaceholder).into(image);
        ingredientAdapter.updatedata(meal.getIngredients());
    }


}