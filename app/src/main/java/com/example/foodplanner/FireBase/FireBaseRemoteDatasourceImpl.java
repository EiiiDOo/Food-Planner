package com.example.foodplanner.FireBase;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FireBaseRemoteDatasourceImpl implements FireBaseRemoteDatasource {
    private static final String TAG = "FireBaseRemoteDatasourceImpl";
    private static FireBaseRemoteDatasourceImpl instance = null;
    private FirebaseAuth firebaseAuth;
    private FireBaseRemoteDatasourceImpl(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.getCurrentUser();
    }
    public static FireBaseRemoteDatasourceImpl getInstance(){
        if(instance == null ){
            instance = new FireBaseRemoteDatasourceImpl();
        }
        return instance;
    }
    @Override
    public void signUp(String email, String password, FireBaseCallback fireBaseCallback){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            fireBaseCallback.onSuccess();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            fireBaseCallback.onFailure(task.getException().getMessage());
                        }
                    }
                });
    }

    @Override
    public void signOut(FireBaseCallback fireBaseCallback) {
//        firebaseAuth.signOut();

    }

    @Override
    public void signin(String email,String password, FireBaseCallback fireBaseCallback){
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            fireBaseCallback.onSuccess();
                        } else {
                            // If sign in fails, display a message to the user.
                            fireBaseCallback.onFailure(task.getException().getMessage());
                        }
                    }
                });
    }
}
