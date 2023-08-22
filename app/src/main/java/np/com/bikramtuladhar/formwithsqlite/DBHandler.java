package np.com.bikramtuladhar.formwithsqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "details.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "details";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "full_name TEXT,"
                + "address TEXT )";
        db.execSQL(query);
    }

    public void addNewEntry(String fullName, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("full_name", fullName);
        values.put("address", address);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public String[] getAllEntries() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        String[] data = new String[0];

        @SuppressLint("Recycle")
        android.database.Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            data = new String[cursor.getCount()];
            int i = 0;
            do {
                data[i] = cursor.getString(1) + " - " + cursor.getString(2);
                i++;
            } while (cursor.moveToNext());
        }

        return data;
    }
}
