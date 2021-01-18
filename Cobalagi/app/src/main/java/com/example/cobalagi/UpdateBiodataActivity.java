package com.example.cobalagi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.cobalagi.helper.DataHelper;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class UpdateBiodataActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dataHelper;
    ExtendedFloatingActionButton fabUpdate;
    EditText textNomor, textNama, textTanggalLahir, textJenisKelamin, textAlamat, textUsername, textPassword, textvaksinisasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_biodata);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Update Biodata");
        //setSupportActionBar(toolbar);
        //assert getSupportActionBar() != null;
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataHelper = new DataHelper(this);
        textNomor = findViewById(R.id.textValueNomor);
        textNama = findViewById(R.id.textValueNama);
        textTanggalLahir = findViewById(R.id.textValueTglLahir);
        textJenisKelamin = findViewById(R.id.textValueJenisKelamin);
        textAlamat = findViewById(R.id.textValueAlamat);
        textUsername = findViewById(R.id.textValueUsername);
        textPassword = findViewById(R.id.textValuePassword);
        textvaksinisasi = findViewById(R.id.textValueVaksin);

        SQLiteDatabase sqLiteDatabase = dataHelper.getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT no, username, password, nama, tgl, jk, alamat,vaksinisasi FROM biodata WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            textNomor.setText(cursor.getString(0));
            textUsername.setText(cursor.getString(1));
            textPassword.setText(cursor.getString(2));
            textNama.setText(cursor.getString(3));
            textTanggalLahir.setText(cursor.getString(4));
            textJenisKelamin.setText(cursor.getString(5));
            textAlamat.setText(cursor.getString(6));
            textvaksinisasi.setText(cursor.getString(7));
        }

        fabUpdate = findViewById(R.id.fabUpdate);
        fabUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase sqLiteDatabase = dataHelper.getWritableDatabase();
                sqLiteDatabase.execSQL("update biodata set nama='" +
                        textNama.getText().toString() + "', tgl='" +
                        textTanggalLahir.getText().toString() + "', jk='" +
                        textJenisKelamin.getText().toString() + "', alamat='" +
                        textAlamat.getText().toString() + "', username='"+
                        textUsername.getText().toString() +"', password='"+
                        textPassword.getText().toString() +"', vaksinisasi='"+
                        textvaksinisasi.getText().toString() +"' where no='" +
                        textNomor.getText().toString() + "'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.mainActivity.RefreshList();
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

