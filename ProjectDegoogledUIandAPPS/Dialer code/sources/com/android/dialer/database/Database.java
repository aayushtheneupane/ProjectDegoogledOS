package com.android.dialer.database;

import android.content.Context;
import java.util.Objects;

public class Database {
    private static DatabaseBindingsStub databaseBindings;

    public static DatabaseBindingsStub get(Context context) {
        Objects.requireNonNull(context);
        DatabaseBindingsStub databaseBindingsStub = databaseBindings;
        if (databaseBindingsStub != null) {
            return databaseBindingsStub;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext instanceof DatabaseBindingsFactory) {
            databaseBindings = ((DatabaseBindingsFactory) applicationContext).newDatabaseBindings();
        }
        if (databaseBindings == null) {
            databaseBindings = new DatabaseBindingsStub();
        }
        return databaseBindings;
    }
}
