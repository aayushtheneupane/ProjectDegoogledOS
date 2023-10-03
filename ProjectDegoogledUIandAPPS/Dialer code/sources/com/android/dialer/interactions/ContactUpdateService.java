package com.android.dialer.interactions;

import android.app.IntentService;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;

public class ContactUpdateService extends IntentService {
    public ContactUpdateService() {
        super(ContactUpdateService.class.getSimpleName());
        setIntentRedelivery(true);
    }

    public static Intent createSetSuperPrimaryIntent(Context context, long j) {
        Intent intent = new Intent(context, ContactUpdateService.class);
        intent.putExtra("phone_number_data_id", j);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        long longExtra = intent.getLongExtra("phone_number_data_id", -1);
        if (longExtra == -1) {
            Log.e("ContactUpdateUtils", "Invalid arguments for setSuperPrimary request");
            return;
        }
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("is_super_primary", 1);
        contentValues.put("is_primary", 1);
        getContentResolver().update(ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, longExtra), contentValues, (String) null, (String[]) null);
    }
}
