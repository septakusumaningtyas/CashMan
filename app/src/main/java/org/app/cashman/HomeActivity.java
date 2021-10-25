package org.app.cashman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {

    ImageButton ImgButtonPem, ImgButtonPeng, ImgButtonDetail, ImgButtonSetting;

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
}