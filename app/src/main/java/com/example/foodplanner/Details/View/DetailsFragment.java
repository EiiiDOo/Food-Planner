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
import com.example.foodplanner.Model.DaysDialog;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealWithDay;
import com.example.foodplanner.Model.RepoRoom.Room.MealsfavLocalDataSourceImpl;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.Base.RemoteDataSourceImpl;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

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
    DaysDialog daysDialog;
    Meal mealFromLastfragment, mealReadyToSave;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        id = DetailsFragmentArgs.fromBundle(getArguments()).getID();
        mealFromLastfragment = DetailsFragmentArgs.fromBundle(getArguments()).getMealObj();

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

        detailsPresenter.getFavMeals();

        if (mealFromLastfragment == null){
            detailsPresenter.fetchMealsById(id);
        }
        else {
            Log.d(TAG, "onViewCreated ELSE: "+mealFromLastfragment.getUserId());
            detailsPresenter.invokeShowMealWithObj(mealFromLastfragment);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void showMeal(List<Meal> meal) {
        mealReadyToSave = meal.get(0);
        Log.d(TAG, "showMeal: "+mealReadyToSave.getStrArea());;
        setImgCalend(mealReadyToSave.transferToMealWithDay(mealReadyToSave));
        setImgIc(mealReadyToSave);
        hideProgressBar(mealReadyToSave);
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

        hideProgressBar(meal);
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
        return R.drawable.unknown;
    }



    @Override
    public void showSuccessMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    void setImgIc(Meal meal) {
        if (detailsPresenter.isFav(meal)) {
            Log.d(TAG, "setImgIc: in check is fav"+detailsPresenter.isFav(meal));
            addToFav.setImageDrawable(getContext().getDrawable(R.drawable.favblack));
            addToFav.setEnabled(false);
        } else {
            addToFav.setImageDrawable(getContext().getDrawable(R.drawable.favwhite));
            addToFav.setEnabled(true);
            addToFav.setOnClickListener(v -> {
                meal.setUserId(detailsPresenter.getUserId());
                detailsPresenter.addTofav(meal);
                addToFav.setImageDrawable(getContext().getDrawable(R.drawable.favblack));
                addToFav.setOnClickListener(null);});
        }
    }

    void setImgCalend(MealWithDay meal) {
            addToPlan.setImageDrawable(getContext().getDrawable(R.drawable.addtocalend));
            addToPlan.setOnClickListener(v -> {
                daysDialog = new DaysDialog(getActivity());
                daysDialog.getConfirmButton().setOnClickListener(v1 -> {
                    daysDialog.getConfirmButton().setEnabled(false);
                    meal.setUserId(detailsPresenter.getUserId());
                    daysDialog.getDays()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(day -> {
//                                Log.d(TAG, "setImgCalend emit: "+day);
//                                newMeal.setDay(day);
//                                Log.d(TAG, "setImgCalend emit: "+newMeal.getDay());
//                                detailsPresenter.addPlan(newMeal);
//                                Log.d(TAG, "setImgCalend emit: after emitted");
                                switch(day){
                                    case "1":
                                        MealWithDay newMeal = new MealWithDay(meal);
                                        newMeal.setDay(day);
                                        detailsPresenter.addPlan(newMeal);
                                        break;
                                    case "2":
                                        MealWithDay newMeal2 = new MealWithDay(meal);
                                        newMeal2.setDay(day);
                                        detailsPresenter.addPlan(newMeal2);
                                        break;
                                    case "3":
                                        MealWithDay newMeal3 = new MealWithDay(meal);
                                        newMeal3.setDay(day);
                                        detailsPresenter.addPlan(newMeal3);
                                        break;
                                    case "4":
                                        MealWithDay newMeal4 = new MealWithDay(meal);
                                        newMeal4.setDay(day);
                                        detailsPresenter.addPlan(newMeal4);
                                        break;
                                    case "5":
                                        MealWithDay newMeal5 = new MealWithDay(meal);
                                        newMeal5.setDay(day);
                                        detailsPresenter.addPlan(newMeal5);
                                        break;
                                    case "6":
                                        MealWithDay newMeal6 = new MealWithDay(meal);
                                        newMeal6.setDay(day);
                                        detailsPresenter.addPlan(newMeal6);
                                        break;
                                    case "7":
                                        MealWithDay newMeal7 = new MealWithDay(meal);
                                        newMeal7.setDay(day);
                                        detailsPresenter.addPlan(newMeal7);
                                        break;
                                }
                            },e->{
                                Toast.makeText(this.getContext(), "", Toast.LENGTH_SHORT).show();
                            },()->{
                                daysDialog.dismissDialog();
                                Toast.makeText(this.getContext(), "", Toast.LENGTH_SHORT).show();});
                });
            });
        }


    void hideProgressBar(Meal meal) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            progressBar.setVisibility(View.INVISIBLE);
            backGroundProgressBar.setVisibility(View.INVISIBLE);
            setImgIc(meal);
            setImgCalend(meal.transferToMealWithDay(meal));
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