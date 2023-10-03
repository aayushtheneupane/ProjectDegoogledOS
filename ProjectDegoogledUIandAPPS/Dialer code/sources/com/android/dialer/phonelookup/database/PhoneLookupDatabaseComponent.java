package com.android.dialer.phonelookup.database;

public abstract class PhoneLookupDatabaseComponent {

    public interface HasComponent {
    }

    public abstract PhoneLookupHistoryDatabaseHelper phoneLookupHistoryDatabaseHelper();
}
