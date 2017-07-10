package com.hfad.starbuzz;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.hfad.starbuzz.DrinksScheme.*;

public class DrinkActivity extends Activity {

    public static final String EXTRA_DRINKNO = "drinkNo";

    private String name;
    private String description;
    private int photoId;

    StarbuzzDatabaseHelper databaseHelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkNo = (Integer) getIntent().getExtras().get(EXTRA_DRINKNO);

        int realDrinkNo = drinkNo + 1;

        String drinkNum = drinkNo + "";

        Toast toast2 = Toast.makeText(this, drinkNum, Toast.LENGTH_LONG);
        toast2.show();

        /*Drink drink = Drink.drinks[drinkNo];
        //Заполнение изображения напитка
        ImageView photo = (ImageView)findViewById(R.id.photo);
        photo.setImageResource(drink.getImageResourceId());
        photo.setContentDescription(drink.getName());
        //Заполнение названия напитка
        TextView name = (TextView)findViewById(R.id.name);
        name.setText(drink.getName());
        //Заполнение описания напитка
        TextView description = (TextView)findViewById(R.id.description);
        description.setText(drink.getDescription());*/

        try {
            databaseHelper = new StarbuzzDatabaseHelper(this);
            // Gets the data repository in write modex
            SQLiteDatabase db = databaseHelper.getWritableDatabase();

            cursor = db.query(DrinksTable.TABLE_NAME, new String[]{Cols.NAME, Cols.DESCRIPTION, Cols.IMAGE_ID}, "id = ?", new String[]{Integer.toString(realDrinkNo)}, null, null, null);

            //Cursor cursor2 = db.query("DRINK",new String[] {"COUNT(_id) AS count"},null, null, null, null, null);

         /*int columnCount = cursor.getColumnCount();
         String columnCount1 = String.valueOf(columnCount);*/

            //            Toast toast = Toast.makeText(this, columnCount1, Toast.LENGTH_LONG);
            //            toast.show();

            //Move to the first record in the Cursor
            if (cursor.moveToFirst()) {

                //Get the drink details from the cursor
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                //boolean isFavorite = (cursor.getInt(3) == 1);

                //Populate the drink name
                TextView nameTV = (TextView) findViewById(R.id.name);
                nameTV.setText(nameText);

                //Populate the drink description
                TextView descriptionTV = (TextView) findViewById(R.id.description);
                descriptionTV.setText(descriptionText);

                //Populate the drink image
                ImageView photo = (ImageView) findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);
            }

            cursor.close();
            db.close();

        } catch (SQLiteException e) {
            Toast toast3 = Toast.makeText(this, "database  unavailable", Toast.LENGTH_SHORT);
            toast3.show();
        }


    }
}
