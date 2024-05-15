package com.example.booksgestion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.booksgestion.entities.Book;
import com.example.booksgestion.entities.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "labadabadubdub.db";
    private static final int DATABASE_VERSION = 1;

    final String TABLE_USERS = "users";
    final String COLUMN_ID = "id";
    final String COLUMN_EMAIL = "email";
    final String COLUMN_PASSWORD = "password";
    final String COLUMN_USERNAME = "username";
    final String TABLE_BOOKS = "books";
    final String COLUMN_BOOK_ID = "idbook";
    final String COLUMN_BOOK_NAME = "book_name";
    final String COLUMN_BOOK_AUTHOR = "book_author";
    final String COLUMN_YEAR_PUBLICATION = "year_publication";
    final String COLUMN_USER_ID = "user_id";

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if they exist
        db.execSQL("DROP TABLE IF EXISTS " + "users");
        db.execSQL("DROP TABLE IF EXISTS " + "books");

        // Create new tables
        onCreate(db);
    }

    private void createTables(SQLiteDatabase db) {

        // Create User table
        String createUserTable = "CREATE TABLE " + TABLE_USERS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT NOT NULL, "
                + COLUMN_EMAIL + " TEXT UNIQUE CHECK (" + COLUMN_EMAIL + " LIKE '%@%.%'), "
                + COLUMN_PASSWORD + " TEXT CHECK (LENGTH(" + COLUMN_PASSWORD + ") >= 6)"
                + ")";

        db.execSQL(createUserTable);

        // Create Book table
        String createBookTable = "CREATE TABLE " + TABLE_BOOKS + " ("
                + COLUMN_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_BOOK_NAME + " TEXT NOT NULL, "
                + COLUMN_BOOK_AUTHOR + " TEXT, "
                + COLUMN_YEAR_PUBLICATION + " TEXT, "
                + COLUMN_USER_ID + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_USER_ID + ") REFERENCES " +
                TABLE_USERS + "(" + COLUMN_ID + "))";
        db.execSQL(createBookTable);
    }
    public long insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, user.getEmail());
        // Hash the password before inserting into the database
        values.put(COLUMN_PASSWORD, hashPassword(user.getPassword()));
        values.put(COLUMN_USERNAME, user.getUsername());
        // Insert row
        long id = db.insert(TABLE_USERS, null, values);
        // Close database connection
        db.close();
        // Return newly inserted row id
        return id;
    }
    // Hash the password using SHA-256 algorithm
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    public long insertBook(Book book, int userId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BOOK_NAME,book.getBook_name());
        values.put(COLUMN_BOOK_AUTHOR,book.getBook_author());
        values.put(COLUMN_YEAR_PUBLICATION,book.getYear_publication());
        values.put(COLUMN_USER_ID,userId);
        long id = db.insert(TABLE_BOOKS,null,values);
        return id;
    }
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null,
                COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{username, hashPassword(password)}, null, null, null);

        // Check if cursor contains any rows
        boolean userExists = cursor != null && cursor.getCount() > 0;

        // Close cursor and database connection
        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return userExists;
    }
}
