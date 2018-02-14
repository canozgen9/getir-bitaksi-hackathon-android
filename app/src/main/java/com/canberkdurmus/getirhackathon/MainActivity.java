package com.canberkdurmus.getirhackathon;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.net.URL;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button setStartDate;
    Button setEndDate;
    EditText etMinCount;
    EditText etMaxCount;
    Button ok;

    enum PickerState {
        START_DATE,
        END_DATE
    }

    PickerState pickerState;
    String startDate;
    String endDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStartDate = findViewById(R.id.setStartDate);
        setEndDate = findViewById(R.id.setEndDate);
        etMinCount = findViewById(R.id.minCount);
        etMaxCount = findViewById(R.id.maxCount);
        ok = findViewById(R.id.ok);

        setStartDate.setOnClickListener(view -> {
            pickerState = PickerState.START_DATE;
            getDate();

        });

        setEndDate.setOnClickListener(view -> {
            pickerState = PickerState.END_DATE;
            getDate();

        });

        ok.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ListActivity.class);

            if (startDate != null && endDate != null && etMaxCount.getText().toString().length() > 0 && etMinCount.getText().toString().length() > 0) {
                intent.putExtra("startDate", startDate);
                intent.putExtra("endDate", endDate);
                intent.putExtra("minCount", Integer.parseInt(etMinCount.getText().toString()));
                intent.putExtra("maxCount", Integer.parseInt(etMaxCount.getText().toString()));

            }

            startActivity(intent);

        });

    }





    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String month;
        String day;
        monthOfYear++;

        if (monthOfYear < 10) {
            month = "0" + monthOfYear;
        } else {
            month = "" + monthOfYear;
        }

        if (dayOfMonth < 10) {
            day = "0" + dayOfMonth;
        } else {
            day = "" + dayOfMonth;
        }
        monthOfYear++;

        if (pickerState == PickerState.START_DATE) {
            startDate = year + "-" + month + "-" + day;
            setStartDate.setText("Start Date: " + startDate);
        } else if (pickerState == PickerState.END_DATE) {
            endDate = year + "-" + month + "-" + day;
            setEndDate.setText("End Date: " + endDate);
        } else {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG);
        }
    }

    public void getDate() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                MainActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH));
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }


}
