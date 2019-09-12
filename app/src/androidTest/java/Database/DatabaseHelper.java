package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "NomNom.db";
    public static final String TABLE_NAME = "Orders";
    public static final String COLUMN_NAME1 = "orderID";
    public static final String COLUMN_NAME2 = "name";
    public static final String COLUMN_NAME3 = "address";
    public static final String COLUMN_NAME4 = "quantity";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (orderID INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, address TEXT, quantity TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String address, String quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME2, name);
        contentValues.put(COLUMN_NAME3, address);
        contentValues.put(COLUMN_NAME4, quantity);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    //interface which provides random read write access to result
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id, String name, String address, String quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME1, id);
        contentValues.put(COLUMN_NAME2, name);
        contentValues.put(COLUMN_NAME3, address);
        contentValues.put(COLUMN_NAME4, quantity);
        db.update(TABLE_NAME, contentValues, "orderID = ?", new String[] {id});
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "orderID = ?", new String[] {id});
    }

}
