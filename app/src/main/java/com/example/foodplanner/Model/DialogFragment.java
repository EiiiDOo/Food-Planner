package com.example.foodplanner.Model;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodplanner.Profile.View.ListenerLogOut;
import com.example.foodplanner.Profile.View.ListenerSignin;
import com.example.foodplanner.R;

public class DialogFragment extends androidx.fragment.app.DialogFragment {
    private ListenerDialog listenerDialog;
    private ListenerLogOut listenerLogOut;
    private ListenerSignin listenerSignin;
    Boolean isLogOut = false;
    Boolean isLogin = false;
    private static final String TITLE = "title",
            MESSAGE = "message", ICON = "icon", BUTTONOK = "buttonOk", BUTTONCANCEL = "buttonCancel", ISLOGOUT = "isLogOut", ISLOGIN = "isLogin";
    private static int title, message, icon, buttonOk, buttonCancel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Fragment fragment = getParentFragment();
        if (fragment instanceof ListenerDialog)
            listenerDialog = (ListenerDialog) fragment;
//        else
//            throw new RuntimeException("Please Implements Listener :ListenerDialog");


    }

    public static DialogFragment newInstance(int title, int message, int icon, int buttonOk, int buttonCancel) {
        DialogFragment dialogFragment = new DialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TITLE, title);
        bundle.putInt(MESSAGE, message);
        bundle.putInt(ICON, icon);
        bundle.putInt(BUTTONOK, buttonOk);
        bundle.putInt(BUTTONCANCEL, buttonCancel);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    public static DialogFragment newInstance(int title, int message, int icon, int buttonOk, int buttonCancel, Boolean isLogIn, ListenerSignin listenerSignin) {
        DialogFragment dialogFragment = new DialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TITLE, title);
        bundle.putInt(MESSAGE, message);
        bundle.putInt(ICON, icon);
        bundle.putInt(BUTTONOK, buttonOk);
        bundle.putInt(BUTTONCANCEL, buttonCancel);
        bundle.putBoolean(ISLOGIN, isLogIn);
        dialogFragment.listenerSignin = listenerSignin;
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    public static DialogFragment newInstance(int title, int message, int icon, int buttonOk, int buttonCancel, Boolean isLogOut, ListenerLogOut listenerLogOut) {
        DialogFragment dialogFragment = new DialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TITLE, title);
        bundle.putInt(MESSAGE, message);
        bundle.putInt(ICON, icon);
        bundle.putInt(BUTTONOK, buttonOk);
        bundle.putInt(BUTTONCANCEL, buttonCancel);
        bundle.putBoolean(ISLOGOUT, isLogOut);
        dialogFragment.setArguments(bundle);
        dialogFragment.listenerLogOut = listenerLogOut;
        return dialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            title = getArguments().getInt(TITLE);
            message = getArguments().getInt(MESSAGE);
            icon = getArguments().getInt(ICON);
            buttonOk = getArguments().getInt(BUTTONOK);
            buttonCancel = getArguments().getInt(BUTTONCANCEL);
            isLogOut = getArguments().getBoolean(ISLOGOUT, false);
            isLogin = getArguments().getBoolean(ISLOGIN, false);
        }
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);
        }
        if (isLogin)
            return inflater.inflate(R.layout.customdialoggreen, container, false);
        return inflater.inflate(R.layout.customdialogred, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView titleTxt = view.findViewById(R.id.title);
        TextView messageTxt = view.findViewById(R.id.message);
        ImageView iconImg = view.findViewById(R.id.icon);
        Button btnOk = view.findViewById(R.id.ok);
        Button cancel = view.findViewById(R.id.canc);
        titleTxt.setText(title);
        messageTxt.setText(message);
        iconImg.setImageResource(icon);
        btnOk.setText(buttonOk);
        cancel.setText(buttonCancel);
        btnOk.setOnClickListener(v -> {
            if (isLogOut)
                listenerLogOut.okLogOut();
            else if (isLogin)
                listenerSignin.okSignin();
            else
                listenerDialog.onOkClicked();
            dismiss();
        });
        cancel.setOnClickListener(v -> dismiss());

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listenerDialog = null;
    }
}