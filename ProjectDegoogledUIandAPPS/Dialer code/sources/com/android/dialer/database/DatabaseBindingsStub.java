package com.android.dialer.database;

import android.content.Context;

public class DatabaseBindingsStub {
    private DialerDatabaseHelper dialerDatabaseHelper;

    public DialerDatabaseHelper getDatabaseHelper(Context context) {
        if (this.dialerDatabaseHelper == null) {
            this.dialerDatabaseHelper = new DialerDatabaseHelper(context, "dialer.db", 10);
        }
        return this.dialerDatabaseHelper;
    }
}
