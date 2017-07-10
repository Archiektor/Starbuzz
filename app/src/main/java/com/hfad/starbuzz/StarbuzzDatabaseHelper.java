package com.hfad.starbuzz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hfad.starbuzz.DrinksScheme.DrinksTable;


/**
 * Created by Archiektor on 16.05.2017.
 */

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "starbuzzDb.db";

    private static final int DB_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String DATABASE_CREATE = "CREATE TABLE " + DrinksTable.TABLE_NAME + "("
            + DrinksScheme.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DrinksScheme.Cols.NAME + TEXT_TYPE + COMMA_SEP
            + DrinksScheme.Cols.DESCRIPTION + TEXT_TYPE + COMMA_SEP
            + DrinksScheme.Cols.IMAGE_ID + INTEGER_TYPE + ")";

    StarbuzzDatabaseHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }


    String addColumn = "ALTER TABLE " + DrinksTable.TABLE_NAME + " ADD COLUMN FAVORITE NUMERIC;";
    String deleteTable = "DROP TABLE IF EXISTS " + DrinksTable.TABLE_NAME;
    String renameTable = "ALTER TABLE DRINK RENAME TO FOO;";


    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private static void insertData(SQLiteDatabase db, String name, String description, int resourceId) {

        ContentValues drinkValues = new ContentValues();

        drinkValues.put(DrinksScheme.Cols.NAME, name);
        drinkValues.put(DrinksScheme.Cols.DESCRIPTION, description);
        drinkValues.put(DrinksScheme.Cols.IMAGE_ID, resourceId);

        db.insert(DrinksTable.TABLE_NAME, null, drinkValues);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL(DATABASE_CREATE);

            insertData(db, "Latte", "Espresso and steamed milk", R.drawable.latte);
            insertData(db, "Cappuccino", "Espresso, hot milk and steamed-milk foam",
                    R.drawable.cappuccino);
            insertData(db, "Filter", "Our best drip coffee", R.drawable.filter);
        }

        if (oldVersion < 2) {
            ContentValues drinkValues = new ContentValues();
            drinkValues.put(DrinksScheme.Cols.DESCRIPTION, "Tasty");
            db.update(DrinksTable.TABLE_NAME, drinkValues, DrinksScheme.Cols.NAME + " = ?", new String[]{"Latte"});
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(deleteTable);
        updateMyDatabase(db, 0, DB_VERSION);
    }
}
