package com.example.foodplanner.FireBase;

public interface FireBaseRemoteDatasource {
    public  void signin(String email,String password, FireBaseCallback fireBaseCallback);
    public  void signUp(String email, String password, FireBaseCallback fireBaseCallback);
    public  void signOut(FireBaseCallback fireBaseCallback);
}
