package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class AppointmentActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment DatePicker = new DialogFragment();
                DatePicker.show(getSupportFragmentManager(),"date picker");

            }
        });
    }
    @Override
    public void onDateSet(DatePicker datePicker, int Year, int Month, int Day) {

        Calendar C = Calendar.getInstance();
        C.set(Calendar.YEAR, Year);
        C.set(Calendar.MONTH,Month);
        C.set(Calendar.DAY_OF_MONTH,Day);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(C.getTime());

        TextView textView = (TextView) findViewById(R.id.btnDOB);
        textView.setText(currentDateString);


    }

}


