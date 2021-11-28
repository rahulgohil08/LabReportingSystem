package com.example.myproject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.security.DigestException;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar C = Calendar.getInstance();
        int Year = C.get(Calendar.YEAR);
        int month = C.get(Calendar.MONTH);
        int Day = C.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener)getActivity(),Year,month,Day);
    }
}
