package il.co.niv.avi.nofardictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.security.cert.CollectionCertStoreParameters;

/**
 * Created by Avi on 19/09/2015.
 */
public class WordDbAdapter {

    public static final String COL_ID = "_id";
    public static final String COL_HE = "_he";
    public static final String COL_EN = "en";

    public static final int IDX_ID = 0;
    public static final int IDX_HE = 1;
    public static final int IDX_EN = 2;

    private static final String DATABSAE_NAME = "db_words";
    private static final String TABLE_NAME = "tbl_words";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_SQL
            = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME
            + " ( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT"
            + " , " + COL_HE + " TEXT"
            + " , " + COL_EN + " TEXT"
            + " );";
    private static final String DROP_TABLE_SQL
            = "DROP TABLE IF EXISTS " + TABLE_NAME;

    //used for logging
    private static final String TAG = "WordDbAdapter";

    private static class DatabaseHelper extends SQLiteOpenHelper {
        Context mContext;

        DatabaseHelper(Context context) {
            super(context, DATABSAE_NAME, null, DATABASE_VERSION);
            mContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                Log.w(TAG, CREATE_TABLE_SQL);
                db.execSQL(CREATE_TABLE_SQL);
            } catch (Exception e) {
                Utilities.showException(mContext, e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                String upgradeMessage = "Upgrading database from version " + oldVersion
                        + " to version " + newVersion + ", old data will be removed.";
                Log.w(TAG, upgradeMessage);
                Log.w(TAG, DROP_TABLE_SQL);
                db.execSQL(DROP_TABLE_SQL);
                onCreate(db);
            } catch (Exception e) {
                Utilities.showException(mContext, e);
            }
        }
    }

    private final Context mContext;

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    public WordDbAdapter(Context context) {
        mContext = context;
    }

    public void open() throws SQLException {
        mDbHelper = new DatabaseHelper(mContext);
        mDb = mDbHelper.getWritableDatabase();
    }

    public void close() throws SQLException {
        if (mDbHelper != null)
            mDb.close();
    }

    //CREATE
    public void createWord(String hebrew, String english) {
        ContentValues values = new ContentValues();
        //id is auto-incremented
        values.put(COL_HE, hebrew);
        values.put(COL_EN, english);
        mDb.insert(TABLE_NAME, null, values);
    }

    public long createWord(Word word) {
        ContentValues values = new ContentValues();
        values.put(COL_HE, word.getHebrew());
        values.put(COL_EN, word.getEnglish());
        return mDb.insert(TABLE_NAME, null, values);
    }

    //READ
    public Word fetchWordById(int id) {
        String[] columns = new String[]{COL_ID, COL_HE, COL_EN};
        String selection = COL_ID + "=?";
        String[] arguments = new String[]{String.valueOf(id)};
        Cursor cursor = mDb.query(TABLE_NAME, columns, selection, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return new Word(cursor.getInt(IDX_ID), cursor.getString(IDX_HE), cursor.getString(IDX_EN));
    }

    public Cursor fetchAllWords() {
        String[] columns = new String[]{COL_ID, COL_HE, COL_EN};
        Cursor cursor = mDb.query(TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }

    //UPDATE
    public void updateWord(Word word) {
        String selection = COL_ID + "=?";
        String[] arguments = new String[]{String.valueOf(word.getId())};
        ContentValues values = new ContentValues();
        values.put(COL_HE, word.getHebrew());
        values.put(COL_EN, word.getEnglish());
        mDb.update(TABLE_NAME, values, selection, arguments);
    }

    //DELETE
    public void deleteWordById(int id) {
        String selection = COL_ID + "=?";
        String[] arguments = new String[]{String.valueOf(id)};
        mDb.delete(TABLE_NAME, selection, arguments);
    }

    public void deleteAllWords() {
        mDb.delete(TABLE_NAME, null, null);
    }
}
