package com.android.messaging.datamodel;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.android.messaging.BugleApplication;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.data.C0934q;
import com.android.messaging.datamodel.data.C0936s;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1474sa;
import com.android.messaging.widget.BugleWidgetProvider;
import com.android.messaging.widget.WidgetConversationProvider;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import p026b.p027a.p030b.p031a.C0632a;

public class MessagingContentProvider extends ContentProvider {
    public static final String AUTHORITY = "com.android.messaging.datamodel.MessagingContentProvider";

    /* renamed from: Bb */
    public static final Uri f1020Bb = Uri.parse("content://com.android.messaging.datamodel.MessagingContentProvider/conversations");

    /* renamed from: Cb */
    static final Uri f1021Cb = Uri.parse("content://com.android.messaging.datamodel.MessagingContentProvider/parts");

    /* renamed from: Db */
    public static final Uri f1022Db = Uri.parse("content://com.android.messaging.datamodel.MessagingContentProvider/messages/conversation");

    /* renamed from: Eb */
    static final Uri f1023Eb = Uri.parse("content://com.android.messaging.datamodel.MessagingContentProvider/participants/conversation");

    /* renamed from: Fb */
    public static final Uri f1024Fb = Uri.parse("content://com.android.messaging.datamodel.MessagingContentProvider/participants");

    /* renamed from: Gb */
    public static final Uri f1025Gb = Uri.parse("content://com.android.messaging.datamodel.MessagingContentProvider/conversation_images");

    /* renamed from: Hb */
    public static final Uri f1026Hb = Uri.parse("content://com.android.messaging.datamodel.MessagingContentProvider/draft_images");

    /* renamed from: Ib */
    private static final UriMatcher f1027Ib = new UriMatcher(-1);

    /* renamed from: Ab */
    private C0955p f1028Ab;

    /* renamed from: zb */
    private C0951l f1029zb;

    static {
        Uri.parse("content://com.android.messaging.datamodel.MessagingContentProvider/messages");
        f1027Ib.addURI(AUTHORITY, "conversations", 10);
        f1027Ib.addURI(AUTHORITY, "conversations/*", 20);
        f1027Ib.addURI(AUTHORITY, "messages/conversation/*", 30);
        f1027Ib.addURI(AUTHORITY, "participants/conversation/*", 40);
        f1027Ib.addURI(AUTHORITY, "participants", 70);
        f1027Ib.addURI(AUTHORITY, "conversation_images/*", 50);
        f1027Ib.addURI(AUTHORITY, "draft_images/*", 60);
    }

    /* renamed from: Va */
    public static void m1262Va() {
        C0632a.m1012Pk().notifyChange(f1023Eb, (ContentObserver) null);
    }

    /* renamed from: Wa */
    public static void m1263Wa() {
        Context applicationContext = C0967f.get().getApplicationContext();
        applicationContext.getContentResolver().notifyChange(f1020Bb, (ContentObserver) null);
        BugleWidgetProvider.m3882a(applicationContext);
    }

    /* renamed from: Xa */
    public static void m1264Xa() {
        Uri parse = Uri.parse("content://com.android.messaging.datamodel.MessagingContentProvider/");
        Context applicationContext = C0967f.get().getApplicationContext();
        applicationContext.getContentResolver().notifyChange(parse, (ContentObserver) null);
        BugleWidgetProvider.m3882a(applicationContext);
        WidgetConversationProvider.m3889c(applicationContext, (String) null);
    }

    /* renamed from: Ya */
    public static void m1265Ya() {
        C0632a.m1012Pk().notifyChange(f1021Cb, (ContentObserver) null);
    }

    /* renamed from: a */
    private String[] m1266a(String[] strArr, String... strArr2) {
        int i;
        if (strArr2 == null || strArr2.length == 0) {
            return strArr;
        }
        if (strArr == null) {
            i = 0;
        } else {
            i = strArr.length;
        }
        int length = strArr2.length;
        String[] strArr3 = new String[(i + length)];
        System.arraycopy(strArr2, 0, strArr3, 0, length);
        if (i > 0) {
            System.arraycopy(strArr, 0, strArr3, length, i);
        }
        return strArr3;
    }

    /* renamed from: l */
    public static Uri m1267l(String str) {
        Uri.Builder buildUpon = f1025Gb.buildUpon();
        buildUpon.appendPath(str);
        return buildUpon.build();
    }

    /* renamed from: m */
    public static Uri m1268m(String str) {
        Uri.Builder buildUpon = f1022Db.buildUpon();
        buildUpon.appendPath(str);
        return buildUpon.build();
    }

    /* renamed from: n */
    public static Uri m1269n(String str) {
        Uri.Builder buildUpon = f1020Bb.buildUpon();
        buildUpon.appendPath(str);
        return buildUpon.build();
    }

    /* renamed from: o */
    public static Uri m1270o(String str) {
        Uri.Builder buildUpon = f1023Eb.buildUpon();
        buildUpon.appendPath(str);
        return buildUpon.build();
    }

    /* renamed from: p */
    public static Uri m1271p(String str) {
        Uri.Builder buildUpon = f1026Hb.buildUpon();
        buildUpon.appendPath(str);
        return buildUpon.build();
    }

    /* renamed from: q */
    public static void m1272q(String str) {
        C0967f.get().getApplicationContext().getContentResolver().notifyChange(m1269n(str), (ContentObserver) null);
        m1263Wa();
    }

    /* renamed from: r */
    public static void m1273r(String str) {
        Uri m = m1268m(str);
        Context applicationContext = C0967f.get().getApplicationContext();
        applicationContext.getContentResolver().notifyChange(m, (ContentObserver) null);
        m1263Wa();
        WidgetConversationProvider.m3889c(applicationContext, str);
    }

    /* renamed from: s */
    public static void m1274s(String str) {
        C0967f.get().getApplicationContext().getContentResolver().notifyChange(m1270o(str), (ContentObserver) null);
    }

    public int delete(Uri uri, String str, String[] strArr) {
        throw new IllegalArgumentException(C0632a.m1014a("Delete not supported: ", uri));
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String ck = C1474sa.getDefault().mo8223ck();
        if (TextUtils.isEmpty(ck)) {
            ck = C1464na.m3757Xj() ? "None" : "None (pre-Kitkat)";
        }
        printWriter.println("Default SMS app: " + ck);
        C1430e.dump(printWriter);
    }

    /* access modifiers changed from: protected */
    public C0951l getDatabase() {
        return C0951l.getInstance(getContext());
    }

    public String getType(Uri uri) {
        StringBuilder sb = new StringBuilder("vnd.android.cursor.dir/vnd.android.messaging.");
        if (f1027Ib.match(uri) == 10) {
            sb.append("conversations");
            return sb.toString();
        }
        throw new IllegalArgumentException(C0632a.m1014a("Unknown URI: ", uri));
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new IllegalStateException(C0632a.m1014a("Insert not supported ", uri));
    }

    public boolean onCreate() {
        this.f1029zb = getDatabase();
        return true;
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) {
        throw new IllegalArgumentException(C0632a.m1014a("openFile not supported: ", uri));
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        int match = f1027Ib.match(uri);
        if (match == 10) {
            C0934q.m1989Ff();
            sQLiteQueryBuilder.setTables("conversation_list_view");
            sQLiteQueryBuilder.appendWhere("sort_timestamp > 0 ");
        } else if (match == 20) {
            C0934q.m1989Ff();
            sQLiteQueryBuilder.setTables("conversation_list_view");
            if (uri.getPathSegments().size() == 2) {
                sQLiteQueryBuilder.appendWhere("_id=?");
                strArr2 = m1266a(strArr2, uri.getPathSegments().get(1));
            } else {
                throw new IllegalArgumentException(C0632a.m1014a("Malformed URI ", uri));
            }
        } else if (match != 30) {
            if (match == 40) {
                sQLiteQueryBuilder.setTables("participants");
                if (uri.getPathSegments().size() != 3 || !TextUtils.equals(uri.getPathSegments().get(1), "conversation")) {
                    throw new IllegalArgumentException(C0632a.m1014a("Malformed URI ", uri));
                }
                sQLiteQueryBuilder.appendWhere("_id IN ( SELECT participant_id AS _id FROM conversation_participants WHERE conversation_id =? UNION SELECT _id FROM participants WHERE sub_id != -2 )");
                strArr2 = m1266a(strArr2, uri.getPathSegments().get(2));
            } else if (match == 50) {
                sQLiteQueryBuilder.setTables("conversation_image_parts_view");
                if (uri.getPathSegments().size() == 2) {
                    sQLiteQueryBuilder.appendWhere("conversation_id =? AND message_status<>3");
                    strArr2 = m1266a(strArr2, uri.getPathSegments().get(1));
                } else {
                    throw new IllegalArgumentException(C0632a.m1014a("Malformed URI ", uri));
                }
            } else if (match == 60) {
                sQLiteQueryBuilder.setTables("conversation_image_parts_view");
                if (uri.getPathSegments().size() == 2) {
                    sQLiteQueryBuilder.appendWhere("conversation_id =? AND message_status=3");
                    strArr2 = m1266a(strArr2, uri.getPathSegments().get(1));
                } else {
                    throw new IllegalArgumentException(C0632a.m1014a("Malformed URI ", uri));
                }
            } else if (match == 70) {
                sQLiteQueryBuilder.setTables("participants");
                if (uri.getPathSegments().size() != 1) {
                    throw new IllegalArgumentException(C0632a.m1014a("Malformed URI ", uri));
                }
            } else {
                throw new IllegalArgumentException(C0632a.m1014a("Unknown URI ", uri));
            }
        } else if (uri.getPathSegments().size() != 3 || !TextUtils.equals(uri.getPathSegments().get(1), "conversation")) {
            throw new IllegalArgumentException(C0632a.m1014a("Malformed URI ", uri));
        } else {
            String str3 = uri.getPathSegments().get(2);
            if (str == null && strArr2 == null && str2 == null) {
                String[] strArr3 = {str3};
                if (this.f1028Ab == null) {
                    this.f1028Ab = this.f1029zb.getDatabase();
                }
                Cursor rawQuery = this.f1028Ab.rawQuery(C0936s.m2021eg(), strArr3);
                rawQuery.setNotificationUri(getContext().getContentResolver(), uri);
                return rawQuery;
            }
            throw new IllegalArgumentException("Cannot set selection or sort order with this query");
        }
        String[] strArr4 = strArr2;
        if (this.f1028Ab == null) {
            this.f1028Ab = this.f1029zb.getDatabase();
        }
        Cursor a = this.f1028Ab.mo6623a(sQLiteQueryBuilder, strArr, str, strArr4, (String) null, (String) null, str2, (String) null);
        a.setNotificationUri(getContext().getContentResolver(), uri);
        return a;
    }

    public void setDatabaseForTest(C0955p pVar) {
        C1424b.m3592ia(BugleApplication.m1223Za());
        this.f1028Ab = pVar;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new IllegalArgumentException(C0632a.m1014a("Update not supported: ", uri));
    }
}
