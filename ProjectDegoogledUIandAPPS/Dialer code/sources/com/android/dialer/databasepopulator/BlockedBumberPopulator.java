package com.android.dialer.databasepopulator;

import android.content.ContentValues;
import android.content.Context;
import android.provider.BlockedNumberContract;
import java.util.Arrays;

public class BlockedBumberPopulator {
    static {
        ContentValues contentValues = new ContentValues();
        contentValues.put("original_number", "123456789");
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("original_number", "987654321");
        Arrays.asList(new ContentValues[]{contentValues, contentValues2});
    }

    public static void deleteBlockedNumbers(Context context) {
        context.getContentResolver().delete(BlockedNumberContract.BlockedNumbers.CONTENT_URI, (String) null, (String[]) null);
    }
}
