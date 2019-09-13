package com.example.nomnom.Database;

import android.provider.BaseColumns;

public final class UserMaster {
    private UserMaster(){}

    //Order Table
    protected static class Orders implements BaseColumns{
        public static final String TABLE_NAME = "Orders";
        public static final String COLUMN_NAME1 = "name";
        public static final String COLUMN_NAME2 = "address";
        public static final String COLUMN_NAME3 = "contactNo";
        public static final String COLUMN_NAME4 = "quantity";
    }

    //Register Table
    protected static class Register implements BaseColumns{
        public static final String TABLE_NAME = "Register";
        public static final String COLUMN_NAME1 = "id";
        public static final String COLUMN_NAME2 = "username";
        public static final String COLUMN_NAME3 = "email";
        public static final String COLUMN_NAME4 = "password";
    }

    //Menu Table
    protected static class Menu implements BaseColumns{
        public static final String TABLE_NAME = "Menu";
        public static final String COLUMN_NAME1 = "";
        public static final String COLUMN_NAME2 = "";
    }

    //Feedback Table
    protected static class Feedback implements BaseColumns {
        public static final String TABLE_NAME = "Feedback";
        public static final String COLUMN_NAME1 = "Topic";
        public static final String COLUMN_NAME2 = "Description";
    }
}
