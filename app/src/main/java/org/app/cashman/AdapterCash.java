package org.app.cashman;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class AdapterCash extends ArrayAdapter {
    Integer[] Nominal;
    String[] Keterangan, Tanggal, Arrow;

    public AdapterCash(@NonNull Context context, Integer[] Nominal, String[] Keterangan, String[] Tanggal, String[] Arrow){
        super(context, R.layout.item_cash, R.id.tanggal, Tanggal);
        this.Nominal = Nominal;
        this.Keterangan = Keterangan;
        this.Tanggal = Tanggal;
        this.Arrow = Arrow;
    }

    @SuppressLint("SetTextI18n")
    public View getView(final int position, View converView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View row = inflater.inflate(R.layout.item_cash, parent, false);

        TextView tvNominal = row.findViewById(R.id.nominal);
        TextView tvKeterangan = row.findViewById(R.id.keterangan);
        TextView tvTanggal = row.findViewById(R.id.tanggal);
        ImageView pem = row.findViewById(R.id.arrowPem);
        ImageView peng = row.findViewById(R.id.arrowPeng);

        tvKeterangan.setText(Keterangan[position]);
        tvTanggal.setText(Tanggal[position]);
        if(Arrow[position].equals("income")){
            tvNominal.setText("[+] Rp. " + Nominal[position]);
            pem.setVisibility(View.VISIBLE);
            peng.setVisibility(View.GONE);
        } else {
            tvNominal.setText("[-] Rp. " + Nominal[position]);
            pem.setVisibility(View.GONE);
            peng.setVisibility(View.VISIBLE);
        }

        return row;
    }
}
