package com.gengs.myrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CheckBox cb_soto, cb_rawon, cb_bakso;
    EditText edt_soto, edt_rawon, edt_bakso;
    Button btn_process, btn_reset;
    TextView textNama, textJumlah, textHarga, textTotal;

    ArrayList<Pilihan> pesanan = new ArrayList<Pilihan>();
    Pilihan soto = new Pilihan("Soto",12000,1);
    Pilihan rawon = new Pilihan("Rawon",15000,1);
    Pilihan bakso = new Pilihan("Bakso",9000,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb_soto = findViewById(R.id.checkBoxSoto);
        cb_rawon = findViewById(R.id.checkBoxRawon);
        cb_bakso = findViewById(R.id.checkBoxBakso);
        edt_soto = findViewById(R.id.editTextSoto);
        edt_rawon = findViewById(R.id.editTextRawon);
        edt_bakso = findViewById(R.id.editTextBakso);
        btn_process = findViewById(R.id.buttonProcess);
        btn_reset = findViewById(R.id.buttonReset);
        textNama = findViewById(R.id.textNama);
        textJumlah = findViewById(R.id.textJumlah);
        textHarga = findViewById(R.id.textHarga);
        textTotal = findViewById(R.id.textTotal);

        clearTextPesanan();

        btn_process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = "";
                String jumlah = "";
                String harga = "";
                int tot = 0;

                if (edt_soto.isEnabled()){
                    int val = Integer.parseInt(edt_soto.getText().toString());
                    soto.setJumlah(val);
                }
                if (edt_rawon.isEnabled()){
                    int val = Integer.parseInt(edt_rawon.getText().toString());
                    rawon.setJumlah(val);
                }
                if (edt_bakso.isEnabled()){
                    int val = Integer.parseInt(edt_bakso.getText().toString());
                    bakso.setJumlah(val);
                }

                for (int i = 0; i < pesanan.size(); i++){
                    int hasil = 0;
                    hasil = pesanan.get(i).getHarga() * pesanan.get(i).getJumlah();

                    nama += pesanan.get(i).getNama()+"\n";
                    jumlah += pesanan.get(i).getJumlah()+"\n";
                    harga += hasil +"\n";
                    tot += hasil;

                }

                if (nama.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Anda Belum Pesan",Toast.LENGTH_SHORT).show();
                    clearTextPesanan();
                }else {
                    Intent intent = new Intent(getApplicationContext(),PesananActivity.class);
                    intent.putExtra("nama",nama);
                    intent.putExtra("jumlah",jumlah);
                    intent.putExtra("harga",harga);
                    intent.putExtra("total",String.valueOf(tot));

                    startActivity(intent);
//                    textNama.setText(nama);
//                    textJumlah.setText(jumlah);
//                    textHarga.setText(harga);
//                    textTotal.setText(String.valueOf(tot));
                }
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pesanan.clear();
                clearTextPesanan();
                clearCheckMenu();
                disableInputText();

                if (pesanan.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Pesanan Kosong "+pesanan,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onCentang(View view) {

        switch (view.getId()){
            case R.id.checkBoxSoto:
                if (cb_soto.isChecked()){
                    pesanan.add(soto);

                    edt_soto.setEnabled(true);
                    edt_soto.setText("1");

                }else if (!cb_soto.isChecked()){
                    pesanan.remove(soto);

                    edt_soto.setEnabled(false);
                }
                break;
            case R.id.checkBoxRawon:
                if (cb_rawon.isChecked()){
                    edt_rawon.setEnabled(true);
                    edt_rawon.setText("1");

                    pesanan.add(rawon);
                }else if (!cb_rawon.isChecked()){
                    edt_rawon.setEnabled(false);
                    pesanan.remove(rawon);
                }
                break;
            case R.id.checkBoxBakso:
                if (cb_bakso.isChecked()){
                    edt_bakso.setEnabled(true);
                    edt_bakso.setText("1");

                    pesanan.add(bakso);
                }else if (!cb_bakso.isChecked()){
                    edt_bakso.setEnabled(false);
                    pesanan.remove(bakso);
                }
                break;
        }
    }

    private void disableInputText() {
        edt_soto.setEnabled(false);
        edt_rawon.setEnabled(false);
        edt_bakso.setEnabled(false);
    }

    private void clearCheckMenu() {
        cb_soto.setChecked(false);
        cb_rawon.setChecked(false);
        cb_bakso.setChecked(false);
    }

    private void clearTextPesanan() {
        textNama.setText("");
        textJumlah.setText("");
        textHarga.setText("");
        textTotal.setText("");
    }
}

