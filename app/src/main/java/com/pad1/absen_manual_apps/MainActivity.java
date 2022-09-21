package com.pad1.absen_manual_apps;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    EditText tanggal;
    EditText waktu;
    EditText keterangan;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanggal = findViewById(R.id.tanggal);
        waktu = findViewById(R.id.waktu);
        keterangan = findViewById(R.id.keterangan);
        tanggal.setKeyListener(null);
        waktu.setKeyListener(null);


        tanggal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b == true){
                    showDatePicker();
                }
            }
        });
       waktu.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b == true){
                    showTimePicker();
                }
            }
        });


        spinner = (Spinner) findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.keterangann, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
    }


    public void showDatePicker(){
        DialogFragment dateFragment = new fragment_date_picker();
        dateFragment.show(getSupportFragmentManager(),"date-picker");

    }

    public void showTimePicker(){
        DialogFragment timeFragment = new fragment_time_picker();
        timeFragment.show(getSupportFragmentManager(),"time-picker");

    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = month_string + "-" + day_string + "-" + year_string;
        tanggal.setText(dateMessage);
    }

    public void processTimePickerResult(int hour, int minute) {
        String hour_string = Integer.toString(hour);
        String minute_string = Integer.toString(minute);
        String timeMessage = hour_string + ":" + minute_string;
        waktu.setText(timeMessage);
    }

    public void onClickShowAlert(View view){
        AlertDialog.Builder peringatan = new AlertDialog.Builder(MainActivity.this);
        peringatan.setTitle("Konfirmasi");
        peringatan.setMessage("Apakah kamu yakin data yang akan kamu kirim sudah sesuai?");

        peringatan.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Absen Berhasil", Toast.LENGTH_SHORT).show();
                tanggal.setText("");
                waktu.setText("");
                keterangan.setText("");
                spinner.setSelection(0);
            }
        });

        peringatan.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        peringatan.show();
    }

    // Spinner
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        String mSpinnerLabel = adapterView.getItemAtPosition(pos).toString();
        if(mSpinnerLabel.equals("Hadir tepat waktu")){
            keterangan.setVisibility(View.INVISIBLE);
        } else {
            keterangan.setVisibility(View.VISIBLE);
        }
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
        keterangan.setVisibility(View.INVISIBLE);
    }
}
