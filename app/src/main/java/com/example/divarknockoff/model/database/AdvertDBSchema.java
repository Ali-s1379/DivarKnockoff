package com.example.divarknockoff.model.database;

public class AdvertDBSchema {
    public static final String NAME = "advert.db";

    public static final class Advert {
        public static final String NAME = "advert";

        public static final class Cols {
            public static final String _ID = "_id";
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String VIP = "vip";
            public static final String PHONE = "phone";
            public static final String CITY = "city";
            public static final String DESCRIPTION = "description";
        }
    }
}
