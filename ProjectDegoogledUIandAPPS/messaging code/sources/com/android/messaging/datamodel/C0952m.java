package com.android.messaging.datamodel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.android.messaging.C0967f;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;

/* renamed from: com.android.messaging.datamodel.m */
public class C0952m {
    /* renamed from: a */
    public void mo6618a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        C1424b.m3592ia(i2 >= i);
        if (i != i2) {
            C1430e.m3625i("MessagingAppDb", "Database upgrade started from version " + i + " to " + i2);
            try {
                mo6619b(sQLiteDatabase, i, i2);
                C1430e.m3625i("MessagingAppDb", "Finished database upgrade");
            } catch (Exception e) {
                C1430e.m3623e("MessagingAppDb", "Failed to perform db upgrade from version " + i + " to version " + i2, e);
                C0951l.m2157b(sQLiteDatabase);
            }
        }
    }

    /* renamed from: b */
    public void mo6619b(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        int i3 = 2;
        if (i < 2) {
            sQLiteDatabase.execSQL("ALTER TABLE conversations ADD COLUMN IS_ENTERPRISE INT DEFAULT(0)");
            C1430e.m3625i("MessagingAppDb", "Ugraded database to version 2");
        } else {
            i3 = i;
        }
        Context applicationContext = C0967f.get().getApplicationContext();
        C0951l.m2154a(sQLiteDatabase);
        C0951l.m2156a(new C0955p(applicationContext, sQLiteDatabase));
        if (Integer.MAX_VALUE >= i2 && i3 != i2) {
            throw new Exception("Missing upgrade handler from version " + i3 + " to version " + i2);
        }
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        C0951l.m2157b(sQLiteDatabase);
        C1430e.m3622e("MessagingAppDb", "Database downgrade requested for version " + i + " version " + i2 + ", forcing db rebuild!");
    }
}
