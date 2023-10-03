package com.android.dialer.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultFutureCallback;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.common.concurrent.DialerFutureSerializer;
import com.android.dialer.common.database.Selection;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.smartdial.util.SmartDialNameMatcher;
import com.android.dialer.smartdial.util.SmartDialPrefix;
import com.android.dialer.util.PermissionsUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.Callable;

public class DialerDatabaseHelper extends SQLiteOpenHelper {
    static final String DEFAULT_LAST_UPDATED_CONFIG_KEY = "smart_dial_default_last_update_millis";
    private final Context context;
    private final DialerFutureSerializer dialerFutureSerializer = new DialerFutureSerializer();
    private boolean isTestInstance = false;

    private static class ContactMatch {

        /* renamed from: id */
        private final long f19id;
        private final String lookupKey;

        public ContactMatch(String str, long j) {
            this.lookupKey = str;
            this.f19id = j;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ContactMatch)) {
                return false;
            }
            ContactMatch contactMatch = (ContactMatch) obj;
            if (!Objects.equals(this.lookupKey, contactMatch.lookupKey) || !Objects.equals(Long.valueOf(this.f19id), Long.valueOf(contactMatch.f19id))) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.lookupKey, Long.valueOf(this.f19id)});
        }
    }

    public static class ContactNumber {
        public final int carrierPresence;
        public final long dataId;
        public final String displayName;

        /* renamed from: id */
        public final long f20id;
        public final String lookupKey;
        public final String phoneNumber;
        public final long photoId;

        public ContactNumber(long j, long j2, String str, String str2, String str3, long j3, int i) {
            this.dataId = j2;
            this.f20id = j;
            this.displayName = str;
            this.phoneNumber = str2;
            this.lookupKey = str3;
            this.photoId = j3;
            this.carrierPresence = i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ContactNumber)) {
                return false;
            }
            ContactNumber contactNumber = (ContactNumber) obj;
            if (!Objects.equals(Long.valueOf(this.f20id), Long.valueOf(contactNumber.f20id)) || !Objects.equals(Long.valueOf(this.dataId), Long.valueOf(contactNumber.dataId)) || !Objects.equals(this.displayName, contactNumber.displayName) || !Objects.equals(this.phoneNumber, contactNumber.phoneNumber) || !Objects.equals(this.lookupKey, contactNumber.lookupKey) || !Objects.equals(Long.valueOf(this.photoId), Long.valueOf(contactNumber.photoId)) || !Objects.equals(Integer.valueOf(this.carrierPresence), Integer.valueOf(contactNumber.carrierPresence))) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Long.valueOf(this.f20id), Long.valueOf(this.dataId), this.displayName, this.phoneNumber, this.lookupKey, Long.valueOf(this.photoId), Integer.valueOf(this.carrierPresence)});
        }
    }

    public interface DeleteContactQuery {
        public static final String[] PROJECTION = {"contact_id", "contact_deleted_timestamp"};
        public static final Uri URI = ContactsContract.DeletedContacts.CONTENT_URI;
    }

    public interface PhoneQuery {
        public static final String[] PROJECTION = {"_id", "data2", "data3", "data1", "contact_id", "lookup", "display_name", "photo_id", "last_time_used", "times_used", "starred", "is_super_primary", "in_visible_group", "is_primary", "carrier_presence"};
        public static final Uri URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI.buildUpon().appendQueryParameter("directory", String.valueOf(0)).appendQueryParameter("remove_duplicate_entries", "true").build();
    }

    public interface UpdatedContactQuery {
        public static final String[] PROJECTION = {"_id"};
        public static final Uri URI = ContactsContract.Contacts.CONTENT_URI;
    }

    protected DialerDatabaseHelper(Context context2, String str, int i) {
        super(context2, str, (SQLiteDatabase.CursorFactory) null, i);
        this.context = (Context) Objects.requireNonNull(context2, "Context must not be null");
    }

    private void setupTables(SQLiteDatabase sQLiteDatabase) {
        dropTables(sQLiteDatabase);
        sQLiteDatabase.execSQL("CREATE TABLE smartdial_table (id INTEGER PRIMARY KEY AUTOINCREMENT,data_id INTEGER, phone_number TEXT,contact_id INTEGER,lookup_key TEXT,display_name TEXT, photo_id INTEGER, last_smartdial_update_time LONG, last_time_used LONG, times_used INTEGER, starred INTEGER, is_super_primary INTEGER, in_visible_group INTEGER, is_primary INTEGER, carrier_presence INTEGER NOT NULL DEFAULT 0);");
        sQLiteDatabase.execSQL("CREATE TABLE prefix_table (_id INTEGER PRIMARY KEY AUTOINCREMENT,prefix TEXT COLLATE NOCASE, contact_id INTEGER);");
        sQLiteDatabase.execSQL("CREATE TABLE properties (property_key TEXT PRIMARY KEY, property_value TEXT );");
        sQLiteDatabase.execSQL("CREATE TABLE filtered_numbers_table (_id INTEGER PRIMARY KEY AUTOINCREMENT,normalized_number TEXT UNIQUE,number TEXT,country_iso TEXT,times_filtered INTEGER,last_time_filtered LONG,creation_time LONG,type INTEGER,source INTEGER);");
        setProperty(sQLiteDatabase, "database_version", String.valueOf(10));
        if (!this.isTestInstance) {
            SharedPreferences.Editor edit = this.context.getSharedPreferences("com.android.dialer", 0).edit();
            edit.putLong("last_updated_millis", 0);
            edit.apply();
        }
    }

    public void dropTables(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS prefix_table");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS smartdial_table");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS properties");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS filtered_numbers_table");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS voicemail_archive_table");
    }

    public synchronized ArrayList<ContactNumber> getLooseMatches(String str, SmartDialNameMatcher smartDialNameMatcher) {
        String str2 = str;
        SmartDialNameMatcher smartDialNameMatcher2 = smartDialNameMatcher;
        synchronized (this) {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            ArrayList<ContactNumber> arrayList = new ArrayList<>();
            String str3 = "SELECT data_id, display_name, photo_id, phone_number, contact_id, lookup_key, carrier_presence FROM smartdial_table WHERE contact_id IN  (SELECT contact_id FROM prefix_table WHERE prefix_table.prefix LIKE '" + (str2 + "%") + "') ORDER BY " + "smartdial_table.starred DESC, smartdial_table.is_super_primary DESC, (CASE WHEN ( ?1 - smartdial_table.last_time_used) < 259200000 THEN 0  WHEN ( ?1 - smartdial_table.last_time_used) < 2592000000 THEN 1  ELSE 2 END), smartdial_table.times_used DESC, smartdial_table.in_visible_group DESC, smartdial_table.display_name, smartdial_table.contact_id, smartdial_table.is_primary DESC";
            int i = 1;
            int i2 = 0;
            Cursor rawQuery = readableDatabase.rawQuery(str3, new String[]{Long.toString(System.currentTimeMillis())});
            if (rawQuery == null) {
                return arrayList;
            }
            try {
                HashSet hashSet = new HashSet();
                int i3 = 0;
                while (rawQuery.moveToNext() && i3 < 20) {
                    if (rawQuery.isNull(i2)) {
                        LogUtil.m9i("DialerDatabaseHelper.getLooseMatches", "_id column null. Row was deleted during iteration, skipping", new Object[i2]);
                    } else {
                        long j = rawQuery.getLong(i2);
                        String string = rawQuery.getString(i);
                        String string2 = rawQuery.getString(3);
                        long j2 = rawQuery.getLong(4);
                        long j3 = rawQuery.getLong(2);
                        String string3 = rawQuery.getString(5);
                        int i4 = rawQuery.getInt(6);
                        ContactMatch contactMatch = new ContactMatch(string3, j2);
                        if (!hashSet.contains(contactMatch)) {
                            boolean matches = smartDialNameMatcher2.matches(this.context, string);
                            boolean z = smartDialNameMatcher2.matchesNumber(this.context, string2, str2) != null;
                            if (matches || z) {
                                hashSet.add(contactMatch);
                                arrayList.add(new ContactNumber(j2, j, string, string2, string3, j3, i4));
                                i3++;
                            }
                            i = 1;
                            i2 = 0;
                        }
                    }
                }
                return arrayList;
            } finally {
                rawQuery.close();
            }
        }
    }

    public String getProperty(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        Cursor query;
        String str3 = null;
        try {
            query = sQLiteDatabase.query("properties", new String[]{"property_value"}, "property_key=?", new String[]{str}, (String) null, (String) null, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    str3 = query.getString(0);
                }
                query.close();
            }
            return str3 != null ? str3 : str2;
        } catch (SQLiteException unused) {
            return str2;
        } catch (Throwable th) {
            query.close();
            throw th;
        }
    }

    public int getPropertyAsInt(SQLiteDatabase sQLiteDatabase, String str, int i) {
        try {
            return Integer.parseInt(getProperty(sQLiteDatabase, str, ""));
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    /* access modifiers changed from: package-private */
    public void insertNamePrefixes(SQLiteDatabase sQLiteDatabase, Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("display_name");
        int columnIndex2 = cursor.getColumnIndex("contact_id");
        sQLiteDatabase.beginTransaction();
        try {
            SQLiteStatement compileStatement = sQLiteDatabase.compileStatement("INSERT INTO prefix_table (contact_id, prefix)  VALUES (?, ?)");
            while (cursor.moveToNext()) {
                if (cursor.isNull(columnIndex2)) {
                    LogUtil.m9i("DialerDatabaseHelper.insertNamePrefixes", "contact_id column null. Row was deleted during iteration, skipping", new Object[0]);
                } else {
                    Iterator<String> it = SmartDialPrefix.generateNamePrefixes(this.context, cursor.getString(columnIndex)).iterator();
                    while (it.hasNext()) {
                        compileStatement.bindLong(1, cursor.getLong(columnIndex2));
                        compileStatement.bindString(2, it.next());
                        compileStatement.executeInsert();
                        compileStatement.clearBindings();
                    }
                }
            }
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    /* access modifiers changed from: protected */
    public void insertUpdatedContactsAndNumberPrefix(SQLiteDatabase sQLiteDatabase, Cursor cursor, Long l) {
        sQLiteDatabase.beginTransaction();
        try {
            SQLiteStatement compileStatement = sQLiteDatabase.compileStatement("INSERT INTO smartdial_table (data_id, phone_number, contact_id, lookup_key, display_name, photo_id, last_time_used, times_used, starred, is_super_primary, in_visible_group, is_primary, carrier_presence, last_smartdial_update_time)  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            SQLiteStatement compileStatement2 = sQLiteDatabase.compileStatement("INSERT INTO prefix_table (contact_id, prefix)  VALUES (?, ?)");
            cursor.moveToPosition(-1);
            while (cursor.moveToNext()) {
                compileStatement.clearBindings();
                if (cursor.isNull(0)) {
                    LogUtil.m9i("DialerDatabaseHelper.insertUpdatedContactsAndNumberPrefix", "_id column null. Row was deleted during iteration, skipping", new Object[0]);
                } else {
                    String string = cursor.getString(3);
                    if (!TextUtils.isEmpty(string)) {
                        compileStatement.bindString(2, string);
                        String string2 = cursor.getString(5);
                        if (!TextUtils.isEmpty(string2)) {
                            compileStatement.bindString(4, string2);
                            String string3 = cursor.getString(6);
                            if (string3 == null) {
                                compileStatement.bindString(5, this.context.getResources().getString(R.string.missing_name));
                            } else {
                                compileStatement.bindString(5, string3);
                            }
                            compileStatement.bindLong(1, cursor.getLong(0));
                            compileStatement.bindLong(3, cursor.getLong(4));
                            compileStatement.bindLong(6, cursor.getLong(7));
                            compileStatement.bindLong(7, cursor.getLong(8));
                            compileStatement.bindLong(8, (long) cursor.getInt(9));
                            compileStatement.bindLong(9, (long) cursor.getInt(10));
                            compileStatement.bindLong(10, (long) cursor.getInt(11));
                            compileStatement.bindLong(11, (long) cursor.getInt(12));
                            compileStatement.bindLong(12, (long) cursor.getInt(13));
                            compileStatement.bindLong(13, (long) cursor.getInt(14));
                            compileStatement.bindLong(14, l.longValue());
                            compileStatement.executeInsert();
                            Iterator<String> it = SmartDialPrefix.parseToNumberTokens(this.context, cursor.getString(3)).iterator();
                            while (it.hasNext()) {
                                compileStatement2.bindLong(1, cursor.getLong(4));
                                compileStatement2.bindString(2, it.next());
                                compileStatement2.executeInsert();
                                compileStatement2.clearBindings();
                            }
                        }
                    }
                }
            }
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public /* synthetic */ Object lambda$startSmartDialUpdateThread$0$DialerDatabaseHelper(boolean z) throws Exception {
        updateSmartDialDatabase(z);
        return null;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        setupTables(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        int propertyAsInt = getPropertyAsInt(sQLiteDatabase, "database_version", 0);
        if (propertyAsInt == 0) {
            LogUtil.m8e("DialerDatabaseHelper.onUpgrade", "malformed database version..recreating database", new Object[0]);
        }
        if (propertyAsInt < 4) {
            setupTables(sQLiteDatabase);
            return;
        }
        int i3 = 7;
        if (propertyAsInt < 7) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS filtered_numbers_table");
            sQLiteDatabase.execSQL("CREATE TABLE filtered_numbers_table (_id INTEGER PRIMARY KEY AUTOINCREMENT,normalized_number TEXT UNIQUE,number TEXT,country_iso TEXT,times_filtered INTEGER,last_time_filtered LONG,creation_time LONG,type INTEGER,source INTEGER);");
        } else {
            i3 = propertyAsInt;
        }
        if (i3 < 8) {
            upgradeToVersion8(sQLiteDatabase);
            i3 = 8;
        }
        if (i3 < 10) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS voicemail_archive_table");
            i3 = 10;
        }
        if (i3 == 10) {
            setProperty(sQLiteDatabase, "database_version", String.valueOf(10));
            return;
        }
        throw new IllegalStateException("error upgrading the database to version 10");
    }

    /* access modifiers changed from: package-private */
    public void removeUpdatedContacts(SQLiteDatabase sQLiteDatabase, Cursor cursor) {
        sQLiteDatabase.beginTransaction();
        try {
            cursor.moveToPosition(-1);
            while (cursor.moveToNext()) {
                if (cursor.isNull(0)) {
                    LogUtil.m9i("DialerDatabaseHelper.removeUpdatedContacts", "contact_id column null. Row was deleted during iteration, skipping", new Object[0]);
                } else {
                    Long valueOf = Long.valueOf(cursor.getLong(0));
                    sQLiteDatabase.delete("smartdial_table", "contact_id=" + valueOf, (String[]) null);
                    sQLiteDatabase.delete("prefix_table", "contact_id=" + valueOf, (String[]) null);
                }
            }
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public void setProperty(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("property_key", str);
        contentValues.put("property_value", str2);
        sQLiteDatabase.replace("properties", (String) null, contentValues);
    }

    public void startSmartDialUpdateThread(boolean z) {
        if (PermissionsUtil.hasContactsReadPermissions(this.context)) {
            Futures.addCallback(this.dialerFutureSerializer.submit(new Callable(z) {
                private final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final Object call() {
                    return DialerDatabaseHelper.this.lambda$startSmartDialUpdateThread$0$DialerDatabaseHelper(this.f$1);
                }
            }, DialerExecutorComponent.get(this.context).backgroundExecutor()), new DefaultFutureCallback(), MoreExecutors.directExecutor());
        }
    }

    public void updateSmartDialDatabase(boolean z) {
        LogUtil.enterBlock("DialerDatabaseHelper.updateSmartDialDatabase");
        SQLiteDatabase writableDatabase = getWritableDatabase();
        int i = 0;
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("com.android.dialer", 0);
        long j = 0;
        long j2 = sharedPreferences.getLong("last_updated_millis", ((SharedPrefConfigProvider) ConfigProviderComponent.get(this.context).getConfigProvider()).getLong(DEFAULT_LAST_UPDATED_CONFIG_KEY, 0));
        if (!z) {
            j = j2;
        }
        String valueOf = String.valueOf(j);
        LogUtil.m9i("DialerDatabaseHelper.updateSmartDialDatabase", "last updated at %s", valueOf);
        Long valueOf2 = Long.valueOf(System.currentTimeMillis());
        Cursor query = this.context.getContentResolver().query(DeleteContactQuery.URI, DeleteContactQuery.PROJECTION, "contact_deleted_timestamp > ?", new String[]{valueOf}, (String) null);
        if (query != null) {
            writableDatabase.beginTransaction();
            try {
                if (query.moveToFirst()) {
                    while (true) {
                        if (query.isNull(i)) {
                            LogUtil.m9i("DialerDatabaseHelper.removeDeletedContacts", "contact_id column null. Row was deleted during iteration, skipping", new Object[i]);
                        } else {
                            long j3 = query.getLong(i);
                            Selection is = Selection.column("contact_id").mo5867is("=", Long.valueOf(j3));
                            writableDatabase.delete("smartdial_table", is.getSelection(), is.getSelectionArgs());
                            Selection is2 = Selection.column("contact_id").mo5867is("=", Long.valueOf(j3));
                            writableDatabase.delete("prefix_table", is2.getSelection(), is2.getSelectionArgs());
                        }
                        if (!query.moveToNext()) {
                            break;
                        }
                        i = 0;
                    }
                    writableDatabase.setTransactionSuccessful();
                }
            } finally {
                query.close();
                writableDatabase.endTransaction();
            }
        }
        writableDatabase.delete("prefix_table", GeneratedOutlineSupport.outline9("contact_id IN (SELECT contact_id FROM smartdial_table WHERE last_smartdial_update_time > ", valueOf, ")"), (String[]) null);
        writableDatabase.delete("smartdial_table", "last_smartdial_update_time > " + valueOf, (String[]) null);
        if (!valueOf.equals("0")) {
            Cursor query2 = this.context.getContentResolver().query(UpdatedContactQuery.URI, UpdatedContactQuery.PROJECTION, "contact_last_updated_timestamp > ?", new String[]{valueOf}, (String) null);
            if (query2 == null) {
                LogUtil.m8e("DialerDatabaseHelper.updateSmartDialDatabase", "smartDial query received null for cursor", new Object[0]);
                return;
            }
            try {
                removeUpdatedContacts(writableDatabase, query2);
                query2.close();
            } catch (Throwable th) {
                Throwable th2 = th;
                query2.close();
                throw th2;
            }
        }
        Cursor query3 = this.context.getContentResolver().query(PhoneQuery.URI, PhoneQuery.PROJECTION, "contact_last_updated_timestamp > ? AND length(lookup) < 1000", new String[]{valueOf}, (String) null);
        if (query3 == null) {
            LogUtil.m8e("DialerDatabaseHelper.updateSmartDialDatabase", "smartDial query received null for cursor", new Object[0]);
            return;
        }
        try {
            insertUpdatedContactsAndNumberPrefix(writableDatabase, query3, valueOf2);
            query3.close();
            query3 = writableDatabase.rawQuery("SELECT DISTINCT display_name, contact_id FROM smartdial_table WHERE last_smartdial_update_time = " + valueOf2, new String[0]);
            if (query3 != null) {
                try {
                    insertNamePrefixes(writableDatabase, query3);
                    query3.close();
                } catch (Throwable th3) {
                    throw th3;
                }
            }
            writableDatabase.execSQL("CREATE INDEX IF NOT EXISTS smartdial_contact_id_index ON smartdial_table (contact_id);");
            writableDatabase.execSQL("CREATE INDEX IF NOT EXISTS smartdial_last_update_index ON smartdial_table (last_smartdial_update_time);");
            writableDatabase.execSQL("CREATE INDEX IF NOT EXISTS smartdial_sort_index ON smartdial_table (starred, is_super_primary, last_time_used, times_used, in_visible_group, display_name, contact_id, is_primary);");
            writableDatabase.execSQL("CREATE INDEX IF NOT EXISTS nameprefix_index ON prefix_table (prefix);");
            writableDatabase.execSQL("CREATE INDEX IF NOT EXISTS nameprefix_contact_id_index ON prefix_table (contact_id);");
            writableDatabase.execSQL("ANALYZE smartdial_table");
            writableDatabase.execSQL("ANALYZE prefix_table");
            writableDatabase.execSQL("ANALYZE smartdial_contact_id_index");
            writableDatabase.execSQL("ANALYZE smartdial_last_update_index");
            writableDatabase.execSQL("ANALYZE nameprefix_index");
            writableDatabase.execSQL("ANALYZE nameprefix_contact_id_index");
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putLong("last_updated_millis", valueOf2.longValue());
            edit.apply();
            LogUtil.m9i("DialerDatabaseHelper.updateSmartDialDatabase", "broadcasting smart dial update", new Object[0]);
            Intent intent = new Intent("com.android.dialer.database.ACTION_SMART_DIAL_UPDATED");
            intent.setPackage(this.context.getPackageName());
            this.context.sendBroadcast(intent);
        } finally {
            Throwable th4 = th3;
            query3.close();
        }
    }

    public void upgradeToVersion8(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE smartdial_table ADD carrier_presence INTEGER NOT NULL DEFAULT 0");
    }
}
