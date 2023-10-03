package com.android.dialer.speeddial.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.android.dialer.common.Assert;
import com.android.dialer.common.database.Selection;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;

public final class SpeedDialEntryDatabaseHelper extends SQLiteOpenHelper implements SpeedDialEntryDao {
    private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
        if (th != null) {
            try {
                autoCloseable.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            autoCloseable.close();
        }
    }

    public SpeedDialEntryDatabaseHelper(Context context) {
        super(context, "CPSpeedDialEntry", (SQLiteDatabase.CursorFactory) null, 2);
    }

    private ContentValues buildContentValues(SpeedDialEntry speedDialEntry, boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("id", speedDialEntry.mo7325id());
        }
        contentValues.put("pinned_position", speedDialEntry.pinnedPosition().mo10247or(-1));
        contentValues.put("contact_id", Long.valueOf(speedDialEntry.contactId()));
        contentValues.put("lookup_key", speedDialEntry.lookupKey());
        if (speedDialEntry.defaultChannel() != null) {
            contentValues.put("phone_number", speedDialEntry.defaultChannel().number());
            contentValues.put("phone_type", Integer.valueOf(speedDialEntry.defaultChannel().phoneType()));
            contentValues.put("phone_label", speedDialEntry.defaultChannel().label());
            contentValues.put("phone_technology", Integer.valueOf(speedDialEntry.defaultChannel().technology()));
        }
        return contentValues;
    }

    private ImmutableMap<SpeedDialEntry, Long> insert(SQLiteDatabase sQLiteDatabase, ImmutableList<SpeedDialEntry> immutableList) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        UnmodifiableIterator<SpeedDialEntry> it = immutableList.iterator();
        while (it.hasNext()) {
            SpeedDialEntry next = it.next();
            Assert.checkArgument(next.mo7325id() == null);
            long insert = sQLiteDatabase.insert("speed_dial_entries", (String) null, buildContentValues(next, false));
            if (insert != -1) {
                builder.put(next, Long.valueOf(insert));
            } else {
                throw new UnsupportedOperationException("Attempted to insert a row that already exists.");
            }
        }
        return builder.build();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0016, code lost:
        if (r0 != null) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0018, code lost:
        $closeResource(r2, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001b, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void delete(com.google.common.collect.ImmutableList<java.lang.Long> r3) {
        /*
            r2 = this;
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            android.database.sqlite.SQLiteDatabase r0 = r2.getWritableDatabase()
            r1 = 0
            r2.delete(r0, r3)     // Catch:{ all -> 0x0013 }
            $closeResource(r1, r0)
            return
        L_0x0013:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0015 }
        L_0x0015:
            r3 = move-exception
            if (r0 == 0) goto L_0x001b
            $closeResource(r2, r0)
        L_0x001b:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.speeddial.database.SpeedDialEntryDatabaseHelper.delete(com.google.common.collect.ImmutableList):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00b3, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00b4, code lost:
        if (r1 != null) goto L_0x00b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        $closeResource(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00b9, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00bc, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00bd, code lost:
        if (r9 != null) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00bf, code lost:
        $closeResource(r0, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c2, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.common.collect.ImmutableList<com.android.dialer.speeddial.database.SpeedDialEntry> getAllEntries() {
        /*
            r9 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r1 = "SELECT * FROM speed_dial_entries"
            android.database.sqlite.SQLiteDatabase r9 = r9.getReadableDatabase()
            r2 = 0
            android.database.Cursor r1 = r9.rawQuery(r1, r2)     // Catch:{ all -> 0x00ba }
            r3 = -1
            r1.moveToPosition(r3)     // Catch:{ all -> 0x00b1 }
        L_0x0014:
            boolean r4 = r1.moveToNext()     // Catch:{ all -> 0x00b1 }
            if (r4 == 0) goto L_0x00a6
            r4 = 4
            java.lang.String r4 = r1.getString(r4)     // Catch:{ all -> 0x00b1 }
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x00b1 }
            if (r5 != 0) goto L_0x0055
            com.android.dialer.speeddial.database.SpeedDialEntry$Channel$Builder r5 = com.android.dialer.speeddial.database.SpeedDialEntry.Channel.builder()     // Catch:{ all -> 0x00b1 }
            r5.setNumber(r4)     // Catch:{ all -> 0x00b1 }
            r4 = 5
            int r4 = r1.getInt(r4)     // Catch:{ all -> 0x00b1 }
            r5.setPhoneType(r4)     // Catch:{ all -> 0x00b1 }
            r4 = 6
            java.lang.String r4 = r1.getString(r4)     // Catch:{ all -> 0x00b1 }
            com.google.common.base.Optional r4 = com.google.common.base.Optional.m67of(r4)     // Catch:{ all -> 0x00b1 }
            java.lang.String r6 = ""
            java.lang.Object r4 = r4.mo10247or(r6)     // Catch:{ all -> 0x00b1 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x00b1 }
            r5.setLabel(r4)     // Catch:{ all -> 0x00b1 }
            r4 = 7
            int r4 = r1.getInt(r4)     // Catch:{ all -> 0x00b1 }
            r5.setTechnology(r4)     // Catch:{ all -> 0x00b1 }
            com.android.dialer.speeddial.database.SpeedDialEntry$Channel r4 = r5.build()     // Catch:{ all -> 0x00b1 }
            goto L_0x0056
        L_0x0055:
            r4 = r2
        L_0x0056:
            r5 = 1
            int r5 = r1.getInt(r5)     // Catch:{ all -> 0x00b1 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x00b1 }
            com.google.common.base.Optional r5 = com.google.common.base.Optional.m67of(r5)     // Catch:{ all -> 0x00b1 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x00b1 }
            java.lang.Object r6 = r5.mo10247or(r6)     // Catch:{ all -> 0x00b1 }
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ all -> 0x00b1 }
            int r6 = r6.intValue()     // Catch:{ all -> 0x00b1 }
            if (r6 != r3) goto L_0x0077
            com.google.common.base.Optional r5 = com.google.common.base.Optional.absent()     // Catch:{ all -> 0x00b1 }
        L_0x0077:
            com.android.dialer.speeddial.database.SpeedDialEntry$Builder r6 = com.android.dialer.speeddial.database.SpeedDialEntry.builder()     // Catch:{ all -> 0x00b1 }
            r6.setDefaultChannel(r4)     // Catch:{ all -> 0x00b1 }
            r4 = 2
            long r7 = r1.getLong(r4)     // Catch:{ all -> 0x00b1 }
            r6.setContactId(r7)     // Catch:{ all -> 0x00b1 }
            r4 = 3
            java.lang.String r4 = r1.getString(r4)     // Catch:{ all -> 0x00b1 }
            r6.setLookupKey(r4)     // Catch:{ all -> 0x00b1 }
            r6.setPinnedPosition(r5)     // Catch:{ all -> 0x00b1 }
            r4 = 0
            long r4 = r1.getLong(r4)     // Catch:{ all -> 0x00b1 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x00b1 }
            r6.setId(r4)     // Catch:{ all -> 0x00b1 }
            com.android.dialer.speeddial.database.SpeedDialEntry r4 = r6.build()     // Catch:{ all -> 0x00b1 }
            r0.add(r4)     // Catch:{ all -> 0x00b1 }
            goto L_0x0014
        L_0x00a6:
            $closeResource(r2, r1)     // Catch:{ all -> 0x00ba }
            $closeResource(r2, r9)
            com.google.common.collect.ImmutableList r9 = com.google.common.collect.ImmutableList.copyOf(r0)
            return r9
        L_0x00b1:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00b3 }
        L_0x00b3:
            r2 = move-exception
            if (r1 == 0) goto L_0x00b9
            $closeResource(r0, r1)     // Catch:{ all -> 0x00ba }
        L_0x00b9:
            throw r2     // Catch:{ all -> 0x00ba }
        L_0x00ba:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00bc }
        L_0x00bc:
            r1 = move-exception
            if (r9 == 0) goto L_0x00c2
            $closeResource(r0, r9)
        L_0x00c2:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.speeddial.database.SpeedDialEntryDatabaseHelper.getAllEntries():com.google.common.collect.ImmutableList");
    }

    public ImmutableMap<SpeedDialEntry, Long> insertUpdateAndDelete(ImmutableList<SpeedDialEntry> immutableList, ImmutableList<SpeedDialEntry> immutableList2, ImmutableList<Long> immutableList3) {
        if (immutableList.isEmpty() && immutableList2.isEmpty() && immutableList3.isEmpty()) {
            return ImmutableMap.m82of();
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            ImmutableMap<SpeedDialEntry, Long> insert = insert(writableDatabase, immutableList);
            update(writableDatabase, immutableList2);
            delete(writableDatabase, immutableList3);
            writableDatabase.setTransactionSuccessful();
            return insert;
        } finally {
            writableDatabase.endTransaction();
            writableDatabase.close();
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists speed_dial_entries (id integer primary key, pinned_position integer, contact_id integer, lookup_key text, phone_number text, phone_type integer, phone_label text, phone_technology integer );");
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("drop table if exists speed_dial_entries");
        sQLiteDatabase.execSQL("create table if not exists speed_dial_entries (id integer primary key, pinned_position integer, contact_id integer, lookup_key text, phone_number text, phone_type integer, phone_label text, phone_technology integer );");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("drop table if exists speed_dial_entries");
        sQLiteDatabase.execSQL("create table if not exists speed_dial_entries (id integer primary key, pinned_position integer, contact_id integer, lookup_key text, phone_number text, phone_type integer, phone_label text, phone_technology integer );");
    }

    public void update(ImmutableList<SpeedDialEntry> immutableList) {
        if (!immutableList.isEmpty()) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            writableDatabase.beginTransaction();
            try {
                update(writableDatabase, immutableList);
                writableDatabase.setTransactionSuccessful();
            } finally {
                writableDatabase.endTransaction();
                writableDatabase.close();
            }
        }
    }

    private void delete(SQLiteDatabase sQLiteDatabase, ImmutableList<Long> immutableList) {
        ArrayList arrayList = new ArrayList();
        UnmodifiableIterator<Long> it = immutableList.iterator();
        while (it.hasNext()) {
            arrayList.add(Long.toString(it.next().longValue()));
        }
        Selection.Builder builder = Selection.builder();
        builder.and(Selection.column("id").mo5865in(arrayList));
        Selection build = builder.build();
        int delete = sQLiteDatabase.delete("speed_dial_entries", build.getSelection(), build.getSelectionArgs());
        if (delete != immutableList.size()) {
            throw new UnsupportedOperationException(GeneratedOutlineSupport.outline5("Attempted to delete an undetermined number of rows: ", delete));
        }
    }

    private void update(SQLiteDatabase sQLiteDatabase, ImmutableList<SpeedDialEntry> immutableList) {
        UnmodifiableIterator<SpeedDialEntry> it = immutableList.iterator();
        while (it.hasNext()) {
            SpeedDialEntry next = it.next();
            int update = sQLiteDatabase.update("speed_dial_entries", buildContentValues(next, true), "id = ?", new String[]{Long.toString(next.mo7325id().longValue())});
            if (update != 1) {
                throw new UnsupportedOperationException(GeneratedOutlineSupport.outline5("Attempted to update an undetermined number of rows: ", update));
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        if (r0 != null) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
        $closeResource(r3, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long insert(com.android.dialer.speeddial.database.SpeedDialEntry r4) {
        /*
            r3 = this;
            android.database.sqlite.SQLiteDatabase r0 = r3.getWritableDatabase()
            java.lang.String r1 = "speed_dial_entries"
            r2 = 0
            android.content.ContentValues r3 = r3.buildContentValues(r4, r2)     // Catch:{ all -> 0x0022 }
            r4 = 0
            long r1 = r0.insert(r1, r4, r3)     // Catch:{ all -> 0x0022 }
            $closeResource(r4, r0)
            r3 = -1
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x001a
            return r1
        L_0x001a:
            java.lang.UnsupportedOperationException r3 = new java.lang.UnsupportedOperationException
            java.lang.String r4 = "Attempted to insert a row that already exists."
            r3.<init>(r4)
            throw r3
        L_0x0022:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0024 }
        L_0x0024:
            r4 = move-exception
            if (r0 == 0) goto L_0x002a
            $closeResource(r3, r0)
        L_0x002a:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.speeddial.database.SpeedDialEntryDatabaseHelper.insert(com.android.dialer.speeddial.database.SpeedDialEntry):long");
    }
}
