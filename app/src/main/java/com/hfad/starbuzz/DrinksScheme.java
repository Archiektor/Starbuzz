package com.hfad.starbuzz;

import android.provider.BaseColumns;

/**
 * Created by Archiektor on 26.05.2017.
 */

public final class DrinksScheme {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public static final class DrinksTable{
        public static final String TABLE_NAME = "drinks";
    }

    /* Inner class that defines the table contents */

    public static final class Cols{
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String IMAGE_ID = "imageId";
        //public static final String COLUMN_NAME_NULLABLE = ;
    }
}
