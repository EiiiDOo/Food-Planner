package com.example.foodplanner.FireBase;

import com.google.firebase.auth.FirebaseUser;

public interface FireBaseRemoteDatasource {
    public  void signin(String email,String password, FireBaseCallback fireBaseCallback);
    public  void signUp(String email, String password, FireBaseCallback fireBaseCallback);
    public  void signOut(FireBaseCallback fireBaseCallback);
    FirebaseUser getCurrentUser();
}
