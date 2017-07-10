package com.hfad.starbuzz;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.hfad.starbuzz.DrinksScheme.DrinksTable;

public class DrinkCategoryActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*StarbuzzDatabaseHelper databaseHelper;
        databaseHelper = new StarbuzzDatabaseHelper(this);

        SQLiteDatabase db = databaseHelper.getWritableDatabase();*/

        /*Cursor cursor = db.query(DrinksTable.TABLE_NAME, new String[]{DrinksScheme.Cols.NAME, DrinksTable.TABLE_NAME}, null, null, null, null, null);

        CursorAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{DrinksTable.TABLE_NAME}, new int[]{android.R.id.text1}, 0);
*/


        ListView listDrinks = getListView();
         ArrayAdapter<Drink> listAdapter = new ArrayAdapter<Drink>(this,
         android.R.layout.simple_list_item_1,
         Drink.drinks);
         listDrinks.setAdapter(listAdapter);
    }


    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        Intent intent = new Intent(DrinkCategoryActivity.this, DrinkActivity.class);
        intent.putExtra(DrinkActivity.EXTRA_DRINKNO, (int) id);
        startActivity(intent);

    }
}
