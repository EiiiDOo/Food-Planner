package com.example.foodplanner.Details.View;

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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Details.Presenter.DetailsPresenterImpl;
import com.example.foodplanner.FireBase.FireBaseRemoteDatasourceImpl;
import com.example.foodplanner.Model.AllCountries;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.Base.RemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.main.View.IngredientAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailsFragment extends Fragment implements DetailsView {
    boolean fav = true, calend = true;
    Button button;
    ProgressBar progressBar;
    ImageButton addToFav, addToPlan;
    RecyclerView rvIngredients;
    TextView instractions, category,backGroundProgressBar;
    IngredientAdapterdetails ingredientAdapter;
    ImageView image, imageCountry;
    String id;
    WebView wv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = DetailsFragmentArgs.fromBundle(getArguments()).getID();
        Log.d("TAG", "onCreate: " + id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBar2);
        backGroundProgressBar = view.findViewById(R.id.backProgrespar);
        button = view.findViewById(R.id.nameMeal);
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
        addToFav.setOnClickListener(v -> {
            if (fav) {
                addToFav.setImageDrawable(getContext().getDrawable(R.drawable.favblack));
                fav = !fav;
            } else {
                addToFav.setImageDrawable(getContext().getDrawable(R.drawable.favwhite));
                fav = !fav;
            }
        });
        addToPlan.setOnClickListener(v -> {
            if (calend) {
                addToPlan.setImageDrawable(getContext().getDrawable(R.drawable.addedtocalend));
                calend = !calend;
            } else {
                addToPlan.setImageDrawable(getContext().getDrawable(R.drawable.addtocalend));
                calend = !calend;
            }
        });
        DetailsPresenterImpl detailsPresenter = new DetailsPresenterImpl(ReposateryImpl.getInstance(RemoteDataSourceImpl.getInstance(), FireBaseRemoteDatasourceImpl.getInstance()), this);
        detailsPresenter.fetchMealsById(id);
    }

    @Override
    public void showMeal(List<Meal> meal) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            progressBar.setVisibility(View.INVISIBLE);
            backGroundProgressBar.setVisibility(View.INVISIBLE);
        }, 1500);
        button.setText(meal.get(0).getStrMeal());
        instractions.setText(meal.get(0).getStrInstructions());
        Log.d("TAG", "showMeal: " + meal.get(0).getStrMealThumb());
        category.setText(meal.get(0).getStrCategory());
        String s = meal.get(0).getStrArea();
        imageCountry.setImageResource(getResourceId(s));
        Glide.with(getContext()).load(meal.get(0).getStrMealThumb()).placeholder(R.drawable.foodplaceholder).into(image);
        Log.d("TAG", "showMeal: " + meal.get(0).getIngredients());
        ingredientAdapter.updatedata(meal.get(0).getIngredients());
        if (meal.get(0).getStrYoutube() != null && meal.get(0).getStrYoutube().length() != 0) {
            wv.setVisibility(View.VISIBLE);
//        wv.setWebChromeClient(new WebChromeClient());
            wv.setWebViewClient(new WebViewClient());
            WebSettings webSettings = wv.getSettings();
            webSettings.setJavaScriptEnabled(true);
            wv.loadUrl(meal.get(0).getStrYoutube());

        } else
            wv.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMsg(String error) {

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

}