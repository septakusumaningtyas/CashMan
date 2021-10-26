package org.app.cashman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    ImageButton ImgButtonPem, ImgButtonPeng, ImgButtonDetail, ImgButtonSetting;
    TextView textPemasukan, textPengeluaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.cream));
        }

        textPemasukan = findViewById(R.id.textPemasukan);
        textPengeluaran = findViewById(R.id.textPengeluaran);

        getJmlhPemasukan();
        getJmlhPengeluaran();

        ImgButtonPem = findViewById(R.id.imageBtnPem);
        ImgButtonPem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PemasukanActivity.class);
                startActivity(intent);
            }
        });

        ImgButtonPeng = findViewById(R.id.imageBtnPeng);
        ImgButtonPeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PengeluaranActivity.class);
                startActivity(intent);
            }
        });

        ImgButtonDetail = findViewById(R.id.imageBtnDetail);
        ImgButtonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

        ImgButtonSetting = findViewById(R.id.imageBtnSetting);
        ImgButtonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getJmlhPemasukan() {
        SQLiteAccess sqLiteAccess = SQLiteAccess.getInstance(HomeActivity.this);
        sqLiteAccess.open();

        Cursor data = sqLiteAccess.Sum("jumlah", "cash", "arrow = 'arrowPem'");

        if(data.getCount() == 0){
            textPemasukan.setText("Rp. 0");
        } else {
            while(data.moveToNext()){
                if(data.getString(0) != null) {
                    textPemasukan.setText("Rp. " + data.getString(0));
                } else {
                    textPemasukan.setText("Rp. 0");
                }
            }
        }
    }

    private void getJmlhPengeluaran() {
        SQLiteAccess sqLiteAccess = SQLiteAccess.getInstance(HomeActivity.this);
        sqLiteAccess.open();

        Cursor data = sqLiteAccess.Sum("jumlah", "cash", "arrow = 'arrowPeng'");

        if(data.getCount() == 0){
            textPengeluaran.setText("Rp. 0");
        } else {
            while(data.moveToNext()){
                if(data.getString(0) != null) {
                    textPengeluaran.setText("Rp. " + data.getString(0));
                } else {
                    textPengeluaran.setText("Rp. 0");
                }
            }
        }
    }

    //keluar aplikasi
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        moveTaskToBack(true);
    }
}