package com.android.settings.homepage.contextualcards;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CardDatabaseHelper extends SQLiteOpenHelper {
    static CardDatabaseHelper sCardDatabaseHelper;

    public CardDatabaseHelper(Context context) {
        super(context, "homepage_cards.db", (SQLiteDatabase.CursorFactory) null, 5);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE cards(name TEXT NOT NULL PRIMARY KEY, type INTEGER NOT NULL, score DOUBLE NOT NULL, slice_uri TEXT, category INTEGER DEFAULT 0, localized_to_locale TEXT, package_name TEXT NOT NULL, app_version INTEGER NOT NULL, title_res_name TEXT, title_text TEXT, summary_res_name TEXT, summary_text TEXT, icon_res_name TEXT, icon_res_id INTEGER DEFAULT 0, card_action INTEGER, expire_time_ms INTEGER, support_half_width INTEGER DEFAULT 0, card_dismissed INTEGER DEFAULT 0 );");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < i2) {
            Log.d("CardDatabaseHelper", "Reconstructing DB from " + i + " to " + i2);
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cards");
            onCreate(sQLiteDatabase);
        }
    }

    public static synchronized CardDatabaseHelper getInstance(Context context) {
        CardDatabaseHelper cardDatabaseHelper;
        synchronized (CardDatabaseHelper.class) {
            if (sCardDatabaseHelper == null) {
                sCardDatabaseHelper = new CardDatabaseHelper(context.getApplicationContext());
            }
            cardDatabaseHelper = sCardDatabaseHelper;
        }
        return cardDatabaseHelper;
    }

    /* access modifiers changed from: package-private */
    public Cursor getContextualCards() {
        return getReadableDatabase().query("cards", (String[]) null, "card_dismissed=0", (String[]) null, (String) null, (String) null, "score DESC");
    }

    public int markContextualCardAsDismissed(Context context, String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("card_dismissed", 1);
        int update = writableDatabase.update("cards", contentValues, "name=?", new String[]{str});
        writableDatabase.close();
        context.getContentResolver().notifyChange(CardContentProvider.DELETE_CARD_URI, (ContentObserver) null);
        return update;
    }
}
