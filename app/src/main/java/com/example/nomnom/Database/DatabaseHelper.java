package com.example.nomnom.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.text.Selection;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "NomNom.db";
    private static final String TAG = "DatabaseHelper";

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

    //Booking Table
    public static final String TABLE_NAME_BOOKINGS = "Bookings";
    public static final String COLUMN_NAME_BID = "bID";
    public static final String COLUMN_BNAME = "name";
    public static final String COLUMN_NAME_BCONTACT = "contactNum";
    public static final String COLUMN_NAME_BDESCRIPTION = "description";
    public static final String COLUMN_NAME_BPEOPLE = "people";


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
                        COLUMN_NAME_DESCRIPTION + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES_FEEDBACK);

        //Create Table Order
        String SQL_CREATE_ENTRIES_ORDER =
                "CREATE TABLE " + TABLE_NAME_ORDER + " (" +
                        COLUMN_NAME_OID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_NAME + " TEXT," +
                        COLUMN_NAME_ADDRESS + " TEXT," +
                        COLUMN_NAME_CONTACT + " TEXT," +
                        COLUMN_NAME_QUANTITY + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES_ORDER);

        //Create Table Booking
        String SQL_CREATE_ENTRIES_BOOKING =
                "CREATE TABLE " + TABLE_NAME_BOOKINGS + " (" +
                        COLUMN_NAME_BID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_BNAME + " TEXT," +
                        COLUMN_NAME_BCONTACT + " TEXT," +
                        COLUMN_NAME_BDESCRIPTION + " TEXT," +
                        COLUMN_NAME_BPEOPLE + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES_BOOKING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_REGISTER + ";");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FEEDBACK + ";");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ORDER + ";");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BOOKINGS + ";");
        onCreate(db);

    }

    //Add user to register table
    public boolean addUser(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_USERNAME, username);
        values.put(COLUMN_NAME_EMAIL, email);
        values.put(COLUMN_NAME_PASSWORD, password);

        long result = db.insert(TABLE_NAME_REGISTER, null, values);
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
    public boolean addInfoOrder(String name, String address, String contactNo, String quantity) {
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

    //Add data to Booking table
    public boolean addInfoBook(String name, String contactNum, String description, String people) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_BNAME, name);
        values.put(COLUMN_NAME_BCONTACT, contactNum);
        values.put(COLUMN_NAME_BDESCRIPTION, description);
        values.put(COLUMN_NAME_BPEOPLE, people);

        long result = db.insert(TABLE_NAME_BOOKINGS, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }

    //Check username and password
    public boolean checkUser(String username, String password) {
        String[] columns = {COLUMN_NAME_ID};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_NAME_USERNAME + "=?" + " and " + COLUMN_NAME_PASSWORD + "=?";
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

    //show database register table
    public Cursor getAllUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_REGISTER, null);
        return res;
    }

    //show database feedback table
    public Cursor getAllfeedback() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_FEEDBACK, null);
        return res;
    }

    //show database order table
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_ORDER, null);
        return res;
    }

    //show database book table
    public Cursor getAllBookings() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_BOOKINGS, null);
        return res;
    }

    //Return only id with username
    public Cursor getUserID(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_NAME_ID + " FROM " + TABLE_NAME_REGISTER +
                " WHERE " + COLUMN_NAME_USERNAME + " = '" + username + "' ";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    //Return only id with name
    public Cursor getorderID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_NAME_OID + " FROM " + TABLE_NAME_ORDER +
                " WHERE " + COLUMN_NAME + " = '" + name + "' ";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    //Return only id with name
    public Cursor getbookID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_NAME_BID + " FROM " + TABLE_NAME_BOOKINGS +
                " WHERE " + COLUMN_NAME + " = '" + name + "' ";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    //Delete user from register table
    public void deleteUser(int id, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME_REGISTER + " WHERE "
                + COLUMN_NAME_ID + " = '" + id + "'" +
                " AND " + COLUMN_NAME_USERNAME + " = '" + username + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + username + " from database.");
        db.execSQL(query);
    }

    //Delete feedback from feedback table
    public Integer deletefeed(String topic) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_NAME_TOPIC + " LIKE ?";
        String[] selectionArgs = {topic};
        int count = db.delete(TABLE_NAME_FEEDBACK, selection, selectionArgs);
        return count;
    }

    //Delete data from Order table
    public void deleteOrder(int orderID, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME_ORDER + " WHERE "
                + COLUMN_NAME_OID + " = '" + orderID + "'" +
                " AND " + COLUMN_NAME + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

    //Delete data from Booking table
    public void deleteBooking(int bookID, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME_BOOKINGS + " WHERE "
                + COLUMN_NAME_BID + " = '" + bookID + "'" +
                " AND " + COLUMN_NAME + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

    //Update data in register table
    public void updateUser(String newusername, int id, String oldusername) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME_REGISTER + " SET " + COLUMN_NAME_USERNAME + " = '" + newusername + "' WHERE " + COLUMN_NAME_ID + " = '" + id + "'" + " AND " + COLUMN_NAME_USERNAME + " = '" + oldusername + "'";
        Log.d(TAG, "updateUsername:query: " + query);
        Log.d(TAG, "updateUsername:setting name to: " + newusername);
        db.execSQL(query);
    }

    //Update data in feedback table
    public boolean updatefeedback(String topic, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TOPIC, topic);
        values.put(COLUMN_NAME_DESCRIPTION, description);

        String selection = COLUMN_NAME_TOPIC + " LIKE ?";
        String[] selectionArgs = {topic};
        int count = db.update(TABLE_NAME_FEEDBACK, values, selection, selectionArgs);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    //Update data in Order table
    public void updateOrder(String newname, int oID, String oldname) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME_ORDER + " SET " + COLUMN_NAME + " = '" + newname + "' WHERE " + COLUMN_NAME_OID + " = '" + oID + "'" + " AND " + COLUMN_NAME + " = '" + oldname + "'";
        Log.d(TAG, "updateUsername:query: " + query);
        Log.d(TAG, "updateUsername:setting name to: " + newname);
        db.execSQL(query);
    }

    //Update data in Booking table
    public void updateBooking(String newname, int bID, String oldname) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME_BOOKINGS + " SET " + COLUMN_NAME + " = '" + newname + "' WHERE " + COLUMN_NAME_BID + " = '" + bID + "'" + " AND " + COLUMN_NAME + " = '" + oldname + "'";
        Log.d(TAG, "updateUsername:query: " + query);
        Log.d(TAG, "updateUsername:setting name to: " + newname);
        db.execSQL(query);
    }
}