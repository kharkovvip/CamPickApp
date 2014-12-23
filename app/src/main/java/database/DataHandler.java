package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DataHandler {
    SQLiteDatabase db;
    public static final String MAILS_TABLE_NAME = "mailsTable";
    public static final String DATA_BASE_NAME = "myDatabase";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE = "CREATE TABLE mailsTable(draftsID TEXT, filePath TEXT, address TEXT, subject TEXT, message TEXT);";

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
        content.put("draftsID", Long.toString(System.currentTimeMillis()));
        content.put("filePath", mail.getFilePath());
        content.put("address", mail.getTo());
        content.put("subject", mail.getSubject());
        content.put("message", mail.getMessage());
        db.insert(MAILS_TABLE_NAME, null, content);
    }

    public void removeMail(String mailId) {
        String whereClause = "draftsID = ?";
        String[] whereArgs = new String[]{mailId};
        db.delete("mailsTable", whereClause, whereArgs);
    }

    public void removeMails() {
        db.delete("mailsTable", null, null);
    }

    public Mail getDraft(String mailId) {
        String whereClause = "draftsID = ?";
        String[] whereArgs = new String[]{mailId};
        Cursor c = db.query(MAILS_TABLE_NAME, null, whereClause, whereArgs, null, null, null);
        c.moveToFirst();
        return getMail(c);
    }

    public ArrayList<Mail> getDraftlist() {
        Cursor cursor = db.query(MAILS_TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Mail> myMails = new ArrayList();
        while (cursor.moveToNext()) {
            myMails.add(getMail(cursor));
        }
        cursor.close();
        return myMails;
    }

    Mail getMail(Cursor c) {
        String draftsID = c.getString(c.getColumnIndex("draftsID"));
        String filePath = c.getString(c.getColumnIndex("filePath"));
        String to = c.getString(c.getColumnIndex("address"));
        String subject = c.getString(c.getColumnIndex("subject"));
        String message = c.getString(c.getColumnIndex("message"));
        Mail mail = new Mail(draftsID, filePath, to, subject, message);
        return mail;
    }

}

