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

import java.util.Calendar;

public class PengeluaranActivity extends AppCompatActivity {

    TextView textViewTglPeng;
    EditText editTextTglPeng, editTextNominalPeng, editTextKetPeng;
    DatePickerDialog.OnDateSetListener setListener;
    Button simpanButtonPeng, kembaliButtonPeng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengeluaran);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.brown));
        }

        textViewTglPeng = findViewById(R.id.textViewTglPeng);
        editTextTglPeng = findViewById(R.id.editTextTglPeng);
        editTextNominalPeng = findViewById(R.id.editTextNominalPeng);
        editTextKetPeng = findViewById(R.id.editTextKetPeng);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        textViewTglPeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PengeluaranActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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
                textViewTglPeng.setText(date);
            }
        };

        editTextTglPeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PengeluaranActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "-" + month + "-" + year;
                        editTextTglPeng.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        simpanButtonPeng = findViewById(R.id.btnSimpanPeng);
        simpanButtonPeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextNominalPeng.getText().toString().equals("") || editTextKetPeng.getText().toString().equals("") || editTextTglPeng.getText().toString().equals("")){
                    Toast.makeText(PengeluaranActivity.this, "Harap lengkapi data pengeluaran", Toast.LENGTH_SHORT).show();
                } else {
                    Integer jmlh = Integer.valueOf(editTextNominalPeng.getText().toString());
                    SQLiteAccess sqLiteAccess = SQLiteAccess.getInstance(PengeluaranActivity.this);
                    sqLiteAccess.open();

                    boolean isInserted = sqLiteAccess.insertCash(jmlh, editTextKetPeng.getText().toString(), editTextTglPeng.getText().toString(), "arrowPeng");

                    if(isInserted){
                        Toast.makeText(PengeluaranActivity.this, "Berhasil menambah data pengeluaran", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PengeluaranActivity.this, HomeActivity.class));
                    } else {
                        Toast.makeText(PengeluaranActivity.this, "Gagal menambah data pengeluaran", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        kembaliButtonPeng = findViewById(R.id.btnKembaliPeng);
        kembaliButtonPeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PengeluaranActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PengeluaranActivity.this, HomeActivity.class));
    }
}