package com.example.foodplanner.Model;

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class AllCountries {
    private static AllCountries AllCountries=null;
    private final List<Country> Countrys= new ArrayList();
    private static final String TAG = "AllCountries";

    private AllCountries() {
        Countrys.add(new Country("American", R.drawable.america));
        Countrys.add(new Country("British", R.drawable.british));
        Countrys.add(new Country("Canadian", R.drawable.canada));
        Countrys.add(new Country("Chinese", R.drawable.china));
        Countrys.add(new Country("Croatian", R.drawable.croatian));
        Countrys.add(new Country("Dutch", R.drawable.dutch));
        Countrys.add(new Country("Egyptian", R.drawable.egypt));
        Countrys.add(new Country("French", R.drawable.french));
        Countrys.add(new Country("Greek", R.drawable.greek));
        Countrys.add(new Country("Indian", R.drawable.indian));
        Countrys.add(new Country("Irish", R.drawable.ireland));
        Countrys.add(new Country("Italian", R.drawable.italian));
        Countrys.add(new Country("Jamaican", R.drawable.jamaican));
        Countrys.add(new Country("Japanese", R.drawable.japan));
        Countrys.add(new Country("Kenyan", R.drawable.kenya));
        Countrys.add(new Country("Malaysian", R.drawable.malaysian));
        Countrys.add(new Country("Mexican", R.drawable.mexico));
        Countrys.add(new Country("Moroccan", R.drawable.moroco));
        Countrys.add(new Country("Polish", R.drawable.poland));
        Countrys.add(new Country("Portuguese", R.drawable.portug));
        Countrys.add(new Country("Russian", R.drawable.russian));
        Countrys.add(new Country("Spanish", R.drawable.spani));
        Countrys.add(new Country("Thai", R.drawable.thia));
        Countrys.add(new Country("Tunisian", R.drawable.tunisian));
        Countrys.add(new Country("Turkish", R.drawable.turcia));
        Countrys.add(new Country("Unknown", R.drawable.unknown));
        Countrys.add(new Country("Vietnamese", R.drawable.vietname));



    }

    public static AllCountries getInstance() {
        if (AllCountries == null) {
            AllCountries = new AllCountries();
        }

        return AllCountries;
    }
    public List<Country> getAllCountries() {
        return Countrys;
    }

    public Single<String>autoCompleteCountry(String query) {

        return Observable.fromIterable(getInstance().getAllCountries())
                .map(Country::getCountryName)
                .filter(country -> country.toLowerCase().startsWith(query.toLowerCase())).first("null");
    }
}