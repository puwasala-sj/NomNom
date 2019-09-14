package com.example.nomnom.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "NomNom.db";

    //Register Table
    public static final String TABLE_NAME_REGISTER = "signUp";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_USERNAME = "username";
    public static final String COLUMN_NAME_EMAIL = "email";
    public static final String COLUMN_NAME_PASSWORD = "password";

    //Feedback Table
    public static final String TABLE_NAME_FEEDBACK = "Foodfeedback";
    public static final String COLUMN_NAME_FID = "fID";
    public static final String COLUMN_NAME_TOPIC = "Topic";
    public static final String COLUMN_NAME_DESCRIPTION = "Description";

    //Order Table
    public static final String TABLE_NAME_ORDER = "Foodorders";
    public static final String COLUMN_NAME_OID = "oID";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NAME_ADDRESS = "address";
    public static final String COLUMN_NAME_CONTACT = "contactNo";
    public static final String COLUMN_NAME_QUANTITY = "quantity";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Table Register
        String SQL_CREATE_ENTRIES_REGISTER =
                "CREATE TABLE " + TABLE_NAME_REGISTER + " (" +
                        COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_NAME_USERNAME + " TEXT," +
                        COLUMN_NAME_EMAIL + " TEXT," +
                        COLUMN_NAME_PASSWORD + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES_REGISTER);

        //Create Table FeedBack
        String SQL_CREATE_ENTRIES_FEEDBACK =
                "CREATE TABLE " + TABLE_NAME_FEEDBACK + " (" +
                        COLUMN_NAME_FID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_NAME_TOPIC + " TEXT," +
                        COLUMN_NAME_DESCRIPTION  + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES_FEEDBACK);

        //Create Table Order
        String SQL_CREATE_ENTRIES_ORDER =
                "CREATE TABLE " + TABLE_NAME_ORDER + " (" +
                        COLUMN_NAME_OID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME + " TEXT," +
                        COLUMN_NAME_ADDRESS + " TEXT," +
                        COLUMN_NAME_CONTACT + " TEXT," +
                        COLUMN_NAME_QUANTITY + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES_ORDER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_REGISTER+";");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FEEDBACK+";");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ORDER+";");
        onCreate(db);

    }

    //Add user to register table
    public boolean addUser(String username, String email,String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_USERNAME, username);
        values.put(COLUMN_NAME_EMAIL, email);
        values.put(COLUMN_NAME_PASSWORD, password);

        long result = db.insert(TABLE_NAME_REGISTER,null,values);
        if (result == -1)
            return false;
        else
            return true;
    }
    //Add feedback to feedback table
    public boolean addFeedback(String topic, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TOPIC, topic);
        values.put(COLUMN_NAME_DESCRIPTION, description);

        long result = db.insert(TABLE_NAME_FEEDBACK, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }
    //Add data to Order table
    public boolean addInfoOrder(String name, String address,String contactNo, String quantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_NAME_ADDRESS, address);
        values.put(COLUMN_NAME_CONTACT, contactNo);
        values.put(COLUMN_NAME_QUANTITY, quantity);

        long result = db.insert(TABLE_NAME_ORDER, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean checkUser  (String  username, String password) {
        String[] columns = {COLUMN_NAME_ID };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_NAME_USERNAME + "=?" + "and" + COLUMN_NAME_PASSWORD + "m?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_NAME_REGISTER, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count > 0)
            return true;
        else
            return false;
    }

    //interface which provides random read write access to result
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_ORDER,null);
        return res;
    }

    //Update data in Order table
    public boolean updateData(String id, String name, String address,String contactNo, String quantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_NAME_ADDRESS, address);
        values.put(COLUMN_NAME_CONTACT, contactNo);
        values.put(COLUMN_NAME_QUANTITY, quantity);

        db.update(TABLE_NAME_ORDER, values, "orderID = ?", new String[] {id});
        return true;
    }

    //Delete data from Order table
    public Integer deleteData (String orderID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_ORDER, "orderID = ?", new String[] {orderID});
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
    public void deleteData(int id){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM FOOD WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1,(double)id);

        statement.execute();
        database.close();

    }

    public Cursor getData (String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

}
