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

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.cream));
        }

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        loginButton = findViewById(R.id.btnLogin);
        cekUser();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextUsername.getText().toString().equals("") || editTextPassword
                        .getText().toString().equals("")){
                    editTextUsername.setError("");
                    editTextPassword.setError("");
                    Toast.makeText(MainActivity.this, "Lengkapi username dan password Anda", Toast.LENGTH_SHORT).show();
                } else {
                    SQLiteAccess sqLiteAccess = SQLiteAccess.getInstance(MainActivity.this);
                    sqLiteAccess.open();

                    Cursor data = sqLiteAccess.Where("user", "username = '" + editTextUsername.getText().toString().toUpperCase() + "' AND password = '" + editTextPassword.getText().toString() + "'");

                    if(data.getCount() == 0){
                        Toast.makeText(MainActivity.this, "Username atau Password Salah", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(MainActivity.this, HomeActivity.class)); //pindah ke halaman menu
                    }
                }
            }
        });
    }

    private void cekUser() {
        SQLiteAccess sqLiteAccess = SQLiteAccess.getInstance(MainActivity.this);
        sqLiteAccess.open();

        Cursor data = sqLiteAccess.Get("user");

        if(data.getCount() == 0){
            boolean isInserted = sqLiteAccess.insertUser("USER", "user");

            if(isInserted){
                Toast.makeText(MainActivity.this, "User Berhasil Dibuat", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "User Gagal Dibuat", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        moveTaskToBack(true);
    }
}