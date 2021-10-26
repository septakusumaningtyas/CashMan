package org.app.cashman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PemasukanActivity extends AppCompatActivity {

    TextView textViewTgl;
    EditText editTextTgl, editTextNominal, editTextKet;
    DatePickerDialog.OnDateSetListener setListener;
    Button simpanButton, kembaliButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemasukan);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.brown));
        }

        textViewTgl = findViewById(R.id.textViewTgl);
        editTextTgl = findViewById(R.id.editTextTgl);
        editTextNominal = findViewById(R.id.editTextNominal);
        editTextKet = findViewById(R.id.editTextKet);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        textViewTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PemasukanActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = day + "-" + month + "-" + year;
                textViewTgl.setText(date);
            }
        };

        editTextTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PemasukanActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "-" + month + "-" + year;
                        editTextTgl.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        simpanButton = findViewById(R.id.btnSimpan);
        simpanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextNominal.getText().toString().equals("") || editTextKet.getText().toString().equals("") || editTextTgl.getText().toString().equals("")){
                    Toast.makeText(PemasukanActivity.this, "Harap lengkapi data pemasukan", Toast.LENGTH_SHORT).show();
                } else {
                    Integer jmlh = Integer.valueOf(editTextNominal.getText().toString());
                    SQLiteAccess sqLiteAccess = SQLiteAccess.getInstance(PemasukanActivity.this);
                    sqLiteAccess.open();

                    boolean isInserted = sqLiteAccess.insertCash(jmlh, editTextKet.getText().toString(), editTextTgl.getText().toString(), "arrowPem");

                    if(isInserted){
                        Toast.makeText(PemasukanActivity.this, "Berhasil menambah data pemasukan", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PemasukanActivity.this, HomeActivity.class));
                    } else {
                        Toast.makeText(PemasukanActivity.this, "Gagal menambah data pemasukan", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        kembaliButton = findViewById(R.id.btnKembali);
        kembaliButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PemasukanActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PemasukanActivity.this, HomeActivity.class));
    }
}