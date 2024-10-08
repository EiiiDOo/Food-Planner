package com.example.foodplanner.Search.Searchby.View;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.FireBase.FireBaseRemoteDatasourceImpl;
import com.example.foodplanner.Model.AllCountries;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.NetworkUtil;
import com.example.foodplanner.Model.NoInternetDialog;
import com.example.foodplanner.Model.RepoRoom.Room.MealsfavLocalDataSourceImpl;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Model.TypeSearch;
import com.example.foodplanner.Network.Base.RemoteDataSourceImpl;

import com.example.foodplanner.R;
import com.example.foodplanner.Search.Searchby.Presenter.SearchByPresenter;
import com.example.foodplanner.Search.Searchby.Presenter.SearchByPresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;


public class SearchByFragment extends Fragment implements SearchByView {
    final static String TAG = "SearchByFragment";
    String nameOfsearchBy;
    RecyclerView recyclerView;
    MealsAdapter adapter;
    SearchView searchView;
    SearchByPresenter searchByPresenter;
    TypeSearch typeSearch;
    ProgressBar progressBar;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    TextView backGroundProgressBar, typeTitle;
    static Boolean isInternetAvailable = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeSearch = SearchByFragmentArgs.fromBundle(getArguments()).getType();
        nameOfsearchBy = typeSearch.getParam();
        Log.d(TAG, "onCreate: ");


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        typeTitle = view.findViewById(R.id.typeOfSearch);
        progressBar = view.findViewById(R.id.progressBar2);
        backGroundProgressBar = view.findViewById(R.id.backProgrespar);
        recyclerView = view.findViewById(R.id.countryRecyclerView);
        searchView = view.findViewById(R.id.searchView);
        adapter = new MealsAdapter(new ArrayList<>(), view.getContext(), getChildFragmentManager());
        searchView.clearFocus();
        searchByPresenter = new SearchByPresenterImpl(typeSearch, ReposateryImpl.getInstance(RemoteDataSourceImpl.getInstance(), FireBaseRemoteDatasourceImpl.getInstance(), MealsfavLocalDataSourceImpl.getInstance(this.getContext())), this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_searchby, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        compositeDisposable.add(NetworkUtil.observeNetworkConnectivity(requireContext())
                .distinctUntilChanged().subscribe(e -> {
                    isInternetAvailable = e;
                    if (!e) {
                        NoInternetDialog d = new NoInternetDialog();
                        d.show(getChildFragmentManager(), null);
                    }

                }));
    }

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.clear();
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
    public Observable<String> withTypingMeal() {
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
                .debounce(650, TimeUnit.MILLISECONDS)
                .map(String::toLowerCase)
                .filter(e -> (e != null && !e.isEmpty()))
                .distinctUntilChanged();
    }

    @Override
    public Observable<String> withTypingCountry() {
        Observable<String> observable = Observable.create(emitter -> {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    emitter.onNext(AllCountries.getInstance().autoCompleteCountry(newText).blockingGet());
                    return false;
                }
            });
        });
        return observable
                .debounce(650, TimeUnit.MILLISECONDS)
                .distinctUntilChanged();
    }

    @Override
    public Observable<List<String>> withTypingIngredient() {
        Observable<List<String>> observable = Observable.create(emitter -> {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    emitter.onNext(searchByPresenter.autoCompleteIngredient(newText).blockingGet());
                    return false;
                }
            });
        });
        return observable
                .debounce(650, TimeUnit.MILLISECONDS)
                .distinctUntilChanged();
    }

    @Override
    public Observable<String> withTypingCategory() {
        Observable<String> observable = Observable.create(emitter -> {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    emitter.onNext(searchByPresenter.autoCompleteCategory(newText).blockingGet());
                    return false;
                }
            });
        });
        return observable
                .debounce(650, TimeUnit.MILLISECONDS)
                .distinctUntilChanged();
    }


//    @SuppressLint("NotifyDataSetChanged")
//    private void filterSuggestions(String query) {
//        List<String> filteredList = AllCountries.getInstance().getAllCountries().stream()
//                .map(Country::getCountryName)
//                .filter(e -> e.toLowerCase()
//                .contains(query.toLowerCase()))
//                .collect(Collectors.toList());
//        // Update the ListView with the filtered suggestions
//        arrayAdapter.clear();
//        arrayAdapter.addAll(filteredList);
//        adapter.notifyDataSetChanged();
//    }
//    private void hideKeyboard(View view) {
//        InputMethodManager imm = (InputMethodManager) getSystemService(getContext(), InputMethodManager.class);
//        if (imm != null) {
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//    }
//    void ignorefoucs() {
//        getView().findViewById(R.id.searchByRoot).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (searchView.isFocused()) {
//                    searchView.clearFocus();
//                    hideKeyboard(v);
//                }
//                return false;
//            }
//        });
//    }
}

