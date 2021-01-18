package com.example.cobalagi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cobalagi.helper.DataHelper;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class LoginActivity extends AppCompatActivity {
    String[] daftar;
    ListView lvData;
    protected Cursor cursor;
    DataHelper dataHelper;
    EditText txtUsername, txtPass;
    Button btnLogin, btnRegistt;
    public static LoginActivity loginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.username);
        txtPass = findViewById(R.id.password);

        dataHelper = new DataHelper(this);

        btnLogin = findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
            String strUsername = txtUsername.getText().toString();
                SQLiteDatabase sqLiteDatabase = dataHelper.getReadableDatabase();
                cursor = sqLiteDatabase.rawQuery("SELECT * FROM biodata "
                        + "where username ='"+strUsername
                        +"' and password='"+txtPass.getText().toString()
                        +"'", null);

                if (cursor.getCount()>0){
                    if (strUsername.equals("admin"))
                    {
                        //menu login
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);

                    }
                    else
                    {
                        Intent intent = new Intent(LoginActivity.this, UserMenuActivity.class);
                        intent.putExtra("nama", txtUsername.getText().toString());
                        startActivity(intent);
                    }
                }
                else
                {
                    //gagal login
                    Toast.makeText(getApplicationContext(), "Username atau Password Salah", Toast.LENGTH_LONG).show();

                }
            }
        });

        btnRegistt = findViewById(R.id.register);
        btnRegistt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
            });
    }
}