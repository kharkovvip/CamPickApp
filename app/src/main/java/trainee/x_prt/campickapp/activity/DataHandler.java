package trainee.x_prt.campickapp.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DataHandler {
    SQLiteDatabase db;
    public static final String MAILS_TABLE_NAME = "mails";
    public static final String DATA_BASE_NAME = "myDatabase";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE = "CREATE TABLE mails(address TEXT, subject TEXT, message TEXT);";

    public DataBaseHelper dbHelper;

    public DataHandler(Context ctx) {
        dbHelper = new DataBaseHelper(ctx.getApplicationContext());
    }

    private static class DataBaseHelper extends SQLiteOpenHelper {

        public DataBaseHelper(Context ctx) {
            super(ctx, DATA_BASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i2) {
            db.execSQL("DROP TABLE IF EXIST " + MAILS_TABLE_NAME);
            onCreate(db);
        }
    }

    public DataHandler open() {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void saveMail(Mail mail) {
        ContentValues content = new ContentValues();
        content.put("address", mail.getTo());
        content.put("subject", mail.getSubject());
        content.put("message", mail.getMessage());
        db.insert(MAILS_TABLE_NAME, null, content);
    }


    public ArrayList<Mail> getMails() {
        Cursor cursor = db.query(MAILS_TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Mail> mails = new ArrayList();
        while (cursor.moveToNext()) {
            String to = cursor.getString(cursor.getColumnIndex("address"));
            String subject = cursor.getString(cursor.getColumnIndex("subject"));
            String message = cursor.getString(cursor.getColumnIndex("message"));
            Mail mail = new Mail(to, subject, message);
            mails.add(mail);
            // do what ever you want here
        }

        cursor.close();
        return mails;
    }
}

