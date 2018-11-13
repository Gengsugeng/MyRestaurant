package com.gengs.myrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PesananActivity extends AppCompatActivity {

    TextView textNama, textJumlah, textHarga, textTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);

        textNama = findViewById(R.id.textNama);
        textJumlah = findViewById(R.id.textJumlah);
        textHarga = findViewById(R.id.textHarga);
        textTotal = findViewById(R.id.textTotal);

        Intent intent = getIntent();
        textNama.setText(intent.getStringExtra("nama"));
        textJumlah.setText(intent.getStringExtra("jumlah"));
        textHarga.setText(intent.getStringExtra("harga"));
        textTotal.setText("Rp."+intent.getStringExtra("total"));
    }
}
