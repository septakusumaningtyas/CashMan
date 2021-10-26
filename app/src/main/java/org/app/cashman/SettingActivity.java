package org.app.cashman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    Button buttonSimpan, buttonKembali;
    EditText passNow, passNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.brown));
        }

        buttonSimpan = findViewById(R.id.btnSimpanSetting);
        buttonKembali = findViewById(R.id.btnKembaliSetting);
        passNow = findViewById(R.id.editTextPassNow);
        passNew = findViewById(R.id.editTextPassNew);

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteAccess sqLiteAccess = SQLiteAccess.getInstance(SettingActivity.this);
                sqLiteAccess.open();

                Cursor data = sqLiteAccess.Where("user", "username = 'USER' AND password ='" + passNow.getText().toString() + "'");

                if (data.getCount() == 0) {
                    Toast.makeText(SettingActivity.this, "Password lama yang Anda masukkan salah", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isUpdated = sqLiteAccess.updateUser(passNew.getText().toString(), "USER");

                    if(isUpdated){
                        Toast.makeText(SettingActivity.this, "Password Anda berhasil diganti", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SettingActivity.this, HomeActivity.class));
                    } else {
                        Toast.makeText(SettingActivity.this, "Password Anda gagal diganti", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        );

        buttonKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, HomeActivity.class));
            }
        });
    }
}