package org.app.cashman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    Button kembaliButton;
    ListView listView;

    Integer[] Id, Nominal;
    String[] Keterangan, Tgl, Arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.brown));
        }

        listView = findViewById(R.id.detailCashFlow);

        getDetailCashFlow();

        kembaliButton = findViewById(R.id.btnKembaliDetail);
        kembaliButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getDetailCashFlow() {
        SQLiteAccess sqLiteAccess = SQLiteAccess.getInstance(DetailActivity.this);
        sqLiteAccess.open();

        Cursor data = sqLiteAccess.Get("cash");

        if(data.getCount() == 0){
            Toast.makeText(DetailActivity.this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            Id = new Integer[data.getCount()];
            Nominal = new Integer[data.getCount()];
            Keterangan = new String[data.getCount()];
            Tgl = new String[data.getCount()];
            Arrow = new String[data.getCount()];
            int i = 0;
            while (data.moveToNext()){
                Id[i] = data.getInt(0);
                Nominal[i] = data.getInt(1);
                Keterangan[i] = data.getString(2);
                Tgl[i] = data.getString(3);
                Arrow[i] = data.getString(4);
                i++;
            }
            listView.setAdapter(new AdapterCash(DetailActivity.this, Nominal, Keterangan, Tgl, Arrow));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DetailActivity.this, HomeActivity.class));
    }
}