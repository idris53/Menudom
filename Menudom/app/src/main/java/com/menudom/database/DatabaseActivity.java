package com.menudom.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Idris Bohra on 12/21/2016.
 */
public class DatabaseActivity extends SQLiteOpenHelper {

    public DatabaseActivity(Context context) {
        super(context, "menudom.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query1 = "CREATE TABLE oauth(Id INTEGER PRIMARY KEY AUTOINCREMENT, device_id VARCHAR, access_token VARCHAR," +
                "created_on LONG)";

        String query2 = "CREATE TABLE customer(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, password VARCHAR, " +
                "restaurant_name VARCHAR, customer_id INTEGER, email VARCHAR, phone INTEGER, last_login INTEGER, status VARCHAR)";

        String query3 = "CREATE TABLE layout(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, table_name VARCHAR, description VARCHAR, " +
                "template_id VARCHAR, created_on VARCHAR, status VARCHAR)";

        String query4 = "CREATE TABLE template(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, display_name VARCHAR, description VARCHAR, " +
                "status VARCHAR)";

        String query5 = "CREATE TABLE customerlayout(Id INTEGER PRIMARY KEY AUTOINCREMENT, layout_id INTEGER, customer_id INTEGER," +
                "orderin INTEGER, created_date INTEGER, status VARCHAR)";

        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            String query1 = "DROP TABLE IF EXIST customer";
            String query2 = "DROP TABLE IF EXIST oauth";
            String query3 = "DROP TABLE IF EXIST layout";
            String query4 = "DROP TABLE IF EXIST template";
            String query5 = "DROP TABLE IF EXIST customerlayout";
            db.execSQL(query1);
            db.execSQL(query2);
            db.execSQL(query3);
            db.execSQL(query4);
            db.execSQL(query5);

    }

    public void insertCredentials(String device_id, String token, long timestamp){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("device_id", device_id);
            values.put("access_token", token);
            values.put("created_on", timestamp);
            db.insert("oauth", null, values);
            db.close();
    }

    public void insertCustomer(HashMap<String, String> map){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("customer_id", map.get("customer_id"));
        values.put("name", map.get("customer_name"));
        values.put("email", map.get("customer_email"));
        values.put("restaurant_name", map.get("restaurant_name"));
        values.put("status", map.get("status"));
        db.insert("customer", null, values);
        db.close();
    }

    public void insertTemplate(HashMap<String, String> map){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Id", map.get("Id"));
        values.put("name", map.get("name"));
        values.put("display_name", map.get("display_name"));
        values.put("description", map.get("description"));
        values.put("status", map.get("status"));
        db.insert("template", null, values);
        db.close();
    }

    public void insertLayout(HashMap<String, String> map){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Id", map.get("Id"));
        values.put("name", map.get("name"));
        values.put("table_name", map.get("table_name"));
        values.put("description", map.get("description"));
        values.put("template_id", map.get("template_id"));
        values.put("created_on", map.get("created_on"));
        values.put("status", map.get("status"));
        db.insert("layout", null, values);
        db.close();
    }

    public ArrayList<String> getToken(){

        ArrayList<String> list = new ArrayList<>();
        String query = "SELECT device_id, access_token, created_on FROM oauth LIMIT 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                list.add(cursor.getString(0));
                list.add(cursor.getString(1));
                list.add(cursor.getString(2));

            }while(cursor.moveToNext());
        }
        db.close();
        return list;
    }


    public ArrayList<String> getCustomerCredentials(){

        ArrayList<String> list = new ArrayList<>();
        String query = "SELECT name, restaurant_name, customer_id, email, phone, status FROM customer LIMIT 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                list.add(cursor.getString(0));
                list.add(cursor.getString(1));
                list.add(cursor.getString(2));
                list.add(cursor.getString(3));
                list.add(cursor.getString(4));
                list.add(cursor.getString(5));

            }while(cursor.moveToNext());
        }
        db.close();
        return list;
    }

    public void refreshAccessToken(String device_id, String access_token, long timestamp){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("access_token",access_token);
        values.put("created_on",timestamp);
        db.update("oauth", values, "device_id = '"+device_id+"'", null );

    }

    public void resetOauthTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete("oauth", null, null);
        db.close();
    }

    public void resetCustomerTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete("customer", null, null);
        db.close();
    }

    public void resetTemplateTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete("template", null, null);
        db.close();
    }

    public void resetLayoutTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete("layout", null, null);
        db.close();
    }

}


