package com.example.nomnom.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "NomNom.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Table Order
        String SQL_CREATE_ENTRIES_ORDER =
                "CREATE TABLE " + UserMaster.Orders.TABLE_NAME + " (" +
                        UserMaster.Orders._ID + " INTEGER PRIMARY KEY," +
                        UserMaster.Orders.COLUMN_NAME1 + " TEXT," +
                        UserMaster.Orders.COLUMN_NAME2 + " TEXT," +
                        UserMaster.Orders.COLUMN_NAME3 + " TEXT," +
                        UserMaster.Orders.COLUMN_NAME4 + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES_ORDER);

        //Create Table FeedBack
        String SQL_CREATE_ENTRIES_FEEDBACK =
                "CREATE TABLE " + UserMaster.Feedback.TABLE_NAME + " (" +
                        UserMaster.Feedback._ID + " INTEGER PRIMARY KEY," +
                        UserMaster.Feedback.COLUMN_NAME1 + " TEXT," +
                        UserMaster.Feedback.COLUMN_NAME2 + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES_FEEDBACK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + UserMaster.Orders.TABLE_NAME);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + UserMaster.Feedback.TABLE_NAME);
        onCreate(db);
    }

    //Add data to Order table
    public boolean addInfoOrder(String name, String address,String contactNo, String quantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Orders.COLUMN_NAME1, name);
        values.put(UserMaster.Orders.COLUMN_NAME2, address);
        values.put(UserMaster.Orders.COLUMN_NAME3, contactNo);
        values.put(UserMaster.Orders.COLUMN_NAME4, quantity);

        long result = db.insert(UserMaster.Orders.TABLE_NAME, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }
    //interface which provides random read write access to result
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ UserMaster.Orders.TABLE_NAME,null);
        return res;
    }

    //Update data in Order table
    public boolean updateData(String id, String name, String address,String contactNo, String quantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Orders.COLUMN_NAME1, name);
        values.put(UserMaster.Orders.COLUMN_NAME2, address);
        values.put(UserMaster.Orders.COLUMN_NAME3, contactNo);
        values.put(UserMaster.Orders.COLUMN_NAME4, quantity);

        db.update(UserMaster.Orders.TABLE_NAME, values, "orderID = ?", new String[] {id});
        return true;
    }

    //Delete data from Order table
    public Integer deleteData (String orderID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(UserMaster.Orders.TABLE_NAME, "orderID = ?", new String[] {orderID});
    }

    //Menu
    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String name, String price, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO FOOD VALUES (NULL, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindBlob(3, image);

        statement.executeInsert();
    }

    public void updateData(String name, String price, byte[] image, int id){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE FOOD SET name = ?, price = ?, image = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1,name);
        statement.bindString(2,price);
        statement.bindBlob(3,image);
        statement.bindDouble(4,(double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData (String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    //Feedback
    public boolean addFeedback(String topic, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Feedback.COLUMN_NAME1, topic);
        values.put(UserMaster.Feedback.COLUMN_NAME2, description);

        long result = db.insert(UserMaster.Feedback.TABLE_NAME, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }


}
