package rob.myappcompany.webmenagementapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "WEB_Store_DB";
    public static final String CONTACTS_TABLE_NAME = "WEB_STORE";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) throws SQLiteException {
        try {
            db.execSQL(
                    "create table "+ CONTACTS_TABLE_NAME +"(id INTEGER PRIMARY KEY AUTOINCREMENT, name text,title text, address text, description text, andere text)"
            );
        } catch (SQLiteException e) {
            try {
                throw new IOException(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+CONTACTS_TABLE_NAME);
        onCreate(db);
    }
    public boolean insert(String nameIn, String titleIn, String addressIn, String descriptionIn, String andere) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", nameIn);
        contentValues.put("title", titleIn);
        contentValues.put("address", addressIn);
        contentValues.put("description", descriptionIn);
        contentValues.put("andere",andere);
        long i = db.insert(CONTACTS_TABLE_NAME, null, contentValues);
        Log.i("Data", String.valueOf(i));
        return true;
    }

    //WORKING HERE
    public boolean delete(String idIn) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE  FROM '"+CONTACTS_TABLE_NAME+"'WHERE id='"+idIn+"'");
        db.close();
        return true;
    }
    public ArrayList getAllCotacts() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> array_list = new ArrayList<String>();
        ArrayList<StrucktDB> arrayList = new ArrayList<StrucktDB>();

        Cursor res = db.rawQuery( "select * from "+CONTACTS_TABLE_NAME, null );
        res.moveToFirst();
        while(res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex("name")));
            //insert(String nameIn, String titleIn, String addressIn, String descriptionIn, String andere)
            arrayList.add(new StrucktDB(res.getString(res.getColumnIndex("id")),res.getString(res.getColumnIndex("name")),
                    res.getString(res.getColumnIndex("title")), res.getString(res.getColumnIndex("address")),
                    res.getString(res.getColumnIndex("description")),res.getString(res.getColumnIndex("andere"))));
            Log.i("Data", res.getString(res.getColumnIndex("name")));
            res.moveToNext();
        }

        return arrayList;
    }
}