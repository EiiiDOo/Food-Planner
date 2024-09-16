package com.example.foodplanner.Model;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AlertDialog;

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class DaysDialog {
    private final AlertDialog alertDialog;

    private final Button Add;

    private final CheckBox day1;
    private final CheckBox day2;
    private final CheckBox day3;
    private final CheckBox day4;
    private final CheckBox day5;
    private final CheckBox day6;
    private final CheckBox day7;

    public Button getConfirmButton() {
        return Add;
    }

    private List<String> days;

    public Observable<String> getDays() {
        return Observable.fromIterable(days);
    }

    public Button getAdd() {
        return Add;
    }

    public DaysDialog(Activity activity) {
        days = new ArrayList<>();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.daysdialog, null);
        builder.setView(view);
        alertDialog = builder.create();
        day1 = view.findViewById(R.id.chbDay1);
        day2 = view.findViewById(R.id.chbDay2);
        day3 = view.findViewById(R.id.chbDay3);
        day4 = view.findViewById(R.id.chbDay4);
        day5 = view.findViewById(R.id.chbDay5);
        day6 = view.findViewById(R.id.chbDay6);
        day7 = view.findViewById(R.id.chbDay7);
        Add = view.findViewById(R.id.btnAdd);
        Button cancel = view.findViewById(R.id.btnCancleDialog);
        Add.setEnabled(false);
        cancel.setOnClickListener(v -> {
            alertDialog.dismiss();
            days = new ArrayList<>();


        });
        day1.setOnClickListener(v -> {

            if (day1.isChecked()) {
                days.add("1");
            } else {
                days.remove("1");
            }
            Add.setEnabled(!days.isEmpty());
            View vi = layoutInflater.inflate(R.layout.daysdialog, null);
            alertDialog.setView(vi);
            alertDialog.show();
        });
        day2.setOnClickListener(v -> {
            if (day2.isChecked()) {
                days.add("2");
            } else {
                days.remove("2");
            }
            Add.setEnabled(!days.isEmpty());
        });
        day3.setOnClickListener(v -> {
            if (day3.isChecked()) {
                days.add("3");
            } else {
                days.remove("3");
            }
            Add.setEnabled(!days.isEmpty());
        });
        day4.setOnClickListener(v -> {
            if (day4.isChecked()) {
                days.add("4");
            } else {
                days.remove("4");
            }
            Add.setEnabled(!days.isEmpty());
        });
        day5.setOnClickListener(v -> {
            if (day5.isChecked()) {
                days.add("5");
            } else {
                days.remove("5");
            }
            Add.setEnabled(!days.isEmpty());
        });
        day6.setOnClickListener(v -> {
            if (day6.isChecked()) {
                days.add("6");
            } else {
                days.remove("6");
            }
            Add.setEnabled(!days.isEmpty());
        });
        day7.setOnClickListener(v -> {
            if (day7.isChecked()) {
                days.add("7");
            } else {
                days.remove("7");
            }
            Add.setEnabled(!days.isEmpty());
        });
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
        Window window = alertDialog.getWindow();
        if (window != null) {
            // Set a specific width (e.g., 80% of the screen width)
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(window.getAttributes());
            layoutParams.width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.8); // 80% of screen width
            window.setAttributes(layoutParams);
        }
    }


    public void dismissDialog() {
        alertDialog.dismiss();
    }



}
