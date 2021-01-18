package com.example.cobalagi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.cobalagi.helper.DataHelper;

public class UserMenuActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dataHelper;
    TextView txtNomor, txtNama, txtUsername, txtPassword, txtAlamat, txtJeniskelamin, txtTglLahir, txtvaksinisasi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Lihat Biodata");
        //setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataHelper = new DataHelper(this);
        txtNomor = findViewById(R.id.txtValueNomor);
        txtAlamat = findViewById(R.id.txtValueAlamat);
        txtJeniskelamin = findViewById(R.id.txtValueJenisKelamin);
        txtNama = findViewById(R.id.txtValueName);
        txtPassword = findViewById(R.id.txtValuePassword);
        txtTglLahir = findViewById(R.id.txtValueTglLahir);
        txtUsername = findViewById(R.id.txtValueUsername);
        txtvaksinisasi = findViewById(R.id.textValueVaksin);
        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT no, username, password, nama, tgl, jk, alamat,vaksinisasi FROM biodata WHERE username = '" +
                getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            txtNomor.setText(cursor.getString(0));
            txtAlamat.setText(cursor.getString(6));
            txtJeniskelamin.setText(cursor.getString(5));
            txtNama.setText(cursor.getString(3));
            txtPassword.setText(cursor.getString(2));
            txtTglLahir.setText(cursor.getString(4));
            txtUsername.setText(cursor.getString(1));
            txtvaksinisasi.setText(cursor.getString(7));
        }
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
