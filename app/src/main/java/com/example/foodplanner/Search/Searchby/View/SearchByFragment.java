package com.example.foodplanner.Search.Searchby.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.FireBase.FireBaseRemoteDatasourceImpl;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Model.TypeSearch;
import com.example.foodplanner.Network.Base.RemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.Searchby.Presenter.SearchByPresenter;
import com.example.foodplanner.Search.Searchby.Presenter.SearchByPresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchByFragment extends Fragment implements SearchByView {
    String nameOfsearchBy;
    RecyclerView recyclerView;
    MealsAdapter adapter;
    SearchView searchView;
    SearchByPresenter searchByPresenter;
    TypeSearch typeSearch;
    ProgressBar progressBar;
    TextView backGroundProgressBar, typeTitle;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeSearch = SearchByFragmentArgs.fromBundle(getArguments()).getType();
        nameOfsearchBy = typeSearch.getParam();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        typeTitle = view.findViewById(R.id.typeOfSearch);
        progressBar = view.findViewById(R.id.progressBar2);
        backGroundProgressBar = view.findViewById(R.id.backProgrespar);
        recyclerView = view.findViewById(R.id.countryRecyclerView);
        searchView = view.findViewById(R.id.searchView);
        adapter = new MealsAdapter(new ArrayList<>(), view.getContext());
        searchView.clearFocus();
        searchByPresenter = new SearchByPresenterImpl(typeSearch, ReposateryImpl.getInstance(RemoteDataSourceImpl.getInstance(), FireBaseRemoteDatasourceImpl.getInstance()), this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_searchby, container, false);
    }

    @Override
    public void showMeal(List<Meal> meals) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            progressBar.setVisibility(View.INVISIBLE);
            backGroundProgressBar.setVisibility(View.INVISIBLE);
        }, 1000);
        adapter.updatedata(meals);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showErrorMsg(String error) {
        Log.d("TAG", "showErrorMsg: " + error);
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getTitle(String title) {
        typeTitle.setText(getString(R.string.SearchBy) + title);
    }

    @Override
    public Observable<String> withTyping() {
        Observable<String> observable = Observable.create(emitter -> {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    emitter.onNext(newText);
                    return false;
                }
            });

        });
        return observable
                .subscribeOn(Schedulers.io())
                .debounce(500, TimeUnit.MILLISECONDS)
                .map(e -> e.toLowerCase())
                .filter(e -> (e != null && !e.isEmpty()))
                .observeOn(AndroidSchedulers.mainThread());
    }

}