package  com.example.cobalagi.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "biodatadiri.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE = "biodata";
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table biodata(no integer primary key, username text null, password text null, nama text null, tgl text null, jk text null, alamat text null, vaksinisasi text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO "+ DATABASE_TABLE +" (no, username, password, nama, tgl, jk, alamat,vaksinisasi) VALUES ('001', 'admin', 'password', 'Admin', '1996-06-18', 'Laki-laki','Depok', '0');";
        db.execSQL(sql);
        sql = "INSERT INTO "+ DATABASE_TABLE +" (no, username, password, nama, tgl, jk, alamat,vaksinisasi) VALUES ('002', 'hahahihi', 'uekuek', 'HaHiHu HeHo', '1945-07-17', 'Eke Bencis :)','Alam lain','0');";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
//        db.execSQL("drop table if exists " + DATABASE_TABLE);
//        onCreate(db);
    }

}
