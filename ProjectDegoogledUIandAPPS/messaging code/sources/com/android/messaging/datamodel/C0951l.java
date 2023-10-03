package com.android.messaging.datamodel;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.android.messaging.BugleApplication;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0934q;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;

/* renamed from: com.android.messaging.datamodel.l */
public class C0951l extends SQLiteOpenHelper {

    /* renamed from: nc */
    private static final String[] f1359nc = {"name"};

    /* renamed from: oc */
    private static final String[] f1360oc = {"CREATE TABLE conversations(_id INTEGER PRIMARY KEY AUTOINCREMENT, sms_thread_id INT DEFAULT(0), name TEXT, latest_message_id INT, snippet_text TEXT, subject_text TEXT, preview_uri TEXT, preview_content_type TEXT, show_draft INT DEFAULT(0), draft_snippet_text TEXT, draft_subject_text TEXT, draft_preview_uri TEXT, draft_preview_content_type TEXT, archive_status INT DEFAULT(0), sort_timestamp INT DEFAULT(0), last_read_timestamp INT DEFAULT(0), icon TEXT, participant_contact_id INT DEFAULT ( -1), participant_lookup_key TEXT, participant_normalized_destination TEXT, current_self_id TEXT, participant_count INT DEFAULT(0), include_email_addr INT DEFAULT(0), sms_service_center TEXT ,IS_ENTERPRISE INT DEFAULT(0));", "CREATE TABLE messages (_id INTEGER PRIMARY KEY AUTOINCREMENT, conversation_id INT, sender_id INT, sent_timestamp INT DEFAULT(0), received_timestamp INT DEFAULT(0), message_protocol INT DEFAULT(0), message_status INT DEFAULT(0), seen INT DEFAULT(0), read INT DEFAULT(0), sms_message_uri TEXT, sms_priority INT DEFAULT(0), sms_message_size INT DEFAULT(0), mms_subject TEXT, mms_transaction_id TEXT, mms_content_location TEXT, mms_expiry INT DEFAULT(0), raw_status INT DEFAULT(0), self_id INT, retry_start_timestamp INT DEFAULT(0), FOREIGN KEY (conversation_id) REFERENCES conversations(_id) ON DELETE CASCADE FOREIGN KEY (sender_id) REFERENCES participants(_id) ON DELETE SET NULL FOREIGN KEY (self_id) REFERENCES participants(_id) ON DELETE SET NULL );", "CREATE TABLE parts(_id INTEGER PRIMARY KEY AUTOINCREMENT,message_id INT,text TEXT,uri TEXT,content_type TEXT,width INT DEFAULT(-1),height INT DEFAULT(-1),timestamp INT, conversation_id INT NOT NULL,FOREIGN KEY (message_id) REFERENCES messages(_id) ON DELETE CASCADE FOREIGN KEY (conversation_id) REFERENCES conversations(_id) ON DELETE CASCADE );", "CREATE TABLE participants(_id INTEGER PRIMARY KEY AUTOINCREMENT,sub_id INT DEFAULT(-2),sim_slot_id INT DEFAULT(-1),normalized_destination TEXT,send_destination TEXT,display_destination TEXT,full_name TEXT,first_name TEXT,profile_photo_uri TEXT, contact_id INT DEFAULT( -1), lookup_key STRING, blocked INT DEFAULT(0), subscription_name TEXT, subscription_color INT DEFAULT(0), contact_destination TEXT, UNIQUE (normalized_destination, sub_id) ON CONFLICT FAIL);", "CREATE TABLE conversation_participants(_id INTEGER PRIMARY KEY AUTOINCREMENT,conversation_id INT,participant_id INT,UNIQUE (conversation_id,participant_id) ON CONFLICT FAIL, FOREIGN KEY (conversation_id) REFERENCES conversations(_id) ON DELETE CASCADE FOREIGN KEY (participant_id) REFERENCES participants(_id));"};

    /* renamed from: pc */
    private static final String[] f1361pc = {"CREATE INDEX index_conversations_sms_thread_id ON conversations(sms_thread_id)", "CREATE INDEX index_conversations_archive_status ON conversations(archive_status)", "CREATE INDEX index_conversations_sort_timestamp ON conversations(sort_timestamp)", "CREATE INDEX index_messages_sort ON messages(conversation_id, message_status, received_timestamp)", "CREATE INDEX index_messages_status_seen ON messages(message_status, seen)", "CREATE INDEX index_parts_message_id ON parts(message_id)", "CREATE INDEX index_conversation_participants_conversation_id ON conversation_participants(conversation_id)"};

    /* renamed from: qc */
    private static final String[] f1362qc = {"CREATE TRIGGER parts_TRIGGER AFTER INSERT ON parts FOR EACH ROW  BEGIN UPDATE parts SET timestamp= (SELECT received_timestamp FROM messages WHERE messages._id=NEW.message_id) WHERE parts._id=NEW._id; END", "CREATE TRIGGER messages_TRIGGER AFTER UPDATE OF received_timestamp ON messages FOR EACH ROW BEGIN UPDATE parts SET timestamp = NEW.received_timestamp WHERE parts.message_id = NEW._id; END;"};

    /* renamed from: rc */
    private static final String[] f1363rc = {"CREATE VIEW conversation_list_view AS SELECT conversations._id as _id, conversations.name as name, conversations.current_self_id as current_self_id, conversations.archive_status as archive_status, messages.read as read, conversations.icon as icon, conversations.participant_contact_id as participant_contact_id, conversations.participant_lookup_key as participant_lookup_key, conversations.participant_normalized_destination as participant_normalized_destination, conversations.sort_timestamp as sort_timestamp, conversations.show_draft as show_draft, conversations.draft_snippet_text as draft_snippet_text, conversations.draft_preview_uri as draft_preview_uri, conversations.draft_subject_text as draft_subject_text, conversations.draft_preview_content_type as draft_preview_content_type, conversations.preview_uri as preview_uri, conversations.preview_content_type as preview_content_type, conversations.participant_count as participant_count, conversations.include_email_addr as include_email_addr, messages.message_status as message_status, messages.raw_status as raw_status, messages._id as message_id, participants.first_name as snippet_sender_first_name, participants.display_destination as snippet_sender_display_destination, conversations.IS_ENTERPRISE as IS_ENTERPRISE, conversations.snippet_text as snippet_text, conversations.subject_text as subject_text  FROM conversations LEFT JOIN messages ON (conversations.latest_message_id=messages._id)  LEFT JOIN participants ON (messages.sender_id=participants._id) ORDER BY conversations.sort_timestamp DESC", "CREATE VIEW conversation_image_parts_view AS SELECT messages.conversation_id as conversation_id, parts.uri as uri, participants.full_name as _display_name, parts.uri as contentUri,  NULL as thumbnailUri, parts.content_type as contentType, participants.display_destination as display_destination, messages.received_timestamp as received_timestamp, messages.message_status as message_status  FROM messages LEFT JOIN parts ON (messages._id=parts.message_id)  LEFT JOIN participants ON (messages.sender_id=participants._id) WHERE parts.content_type like 'image/%' ORDER BY messages.received_timestamp ASC, parts._id ASC", "CREATE VIEW draft_parts_view AS SELECT parts._id as _id, parts.message_id as message_id, parts.text as text, parts.uri as uri, parts.content_type as content_type, parts.width as width, parts.height as height, messages.conversation_id as conversation_id  FROM messages LEFT JOIN parts ON (messages._id=parts.message_id) WHERE messages.message_status = 3"};
    private static final Object sLock = new Object();

    /* renamed from: sc */
    private static C0951l f1364sc;

    /* renamed from: Ab */
    private C0955p f1365Ab;

    /* renamed from: kc */
    private final Context f1366kc;

    /* renamed from: lc */
    private final Object f1367lc = new Object();

    /* renamed from: mc */
    private final C0952m f1368mc = new C0952m();

    static {
        C0934q.m1990Gf();
    }

    private C0951l(Context context) {
        super(context, "bugle_db", (SQLiteDatabase.CursorFactory) null, Integer.parseInt(context.getResources().getString(R.string.database_version)), (DatabaseErrorHandler) null);
        this.f1366kc = context;
    }

    /* renamed from: a */
    private static void m2155a(SQLiteDatabase sQLiteDatabase, String str, boolean z) {
        try {
            sQLiteDatabase.execSQL("DROP VIEW IF EXISTS " + str);
        } catch (SQLException e) {
            if (Log.isLoggable("MessagingApp", 3)) {
                C1430e.m3620d("MessagingApp", "unable to drop view " + str + " " + e);
            }
            if (z) {
                throw e;
            }
        }
    }

    /* renamed from: b */
    public static void m2157b(SQLiteDatabase sQLiteDatabase) {
        String string;
        String string2;
        String string3;
        Cursor query = sQLiteDatabase.query("sqlite_master", f1359nc, "type='table'", (String[]) null, (String) null, (String) null, (String) null);
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    string3 = query.getString(0);
                    if (!string3.startsWith("android_") && !string3.startsWith("sqlite_")) {
                        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + string3);
                    }
                } catch (SQLException e) {
                    if (Log.isLoggable("MessagingApp", 3)) {
                        C1430e.m3620d("MessagingApp", "unable to drop table " + string3 + " " + e);
                    }
                } catch (Throwable th) {
                    query.close();
                    throw th;
                }
            }
            query.close();
        }
        m2154a(sQLiteDatabase);
        Cursor query2 = sQLiteDatabase.query("sqlite_master", f1359nc, "type='index'", (String[]) null, (String) null, (String) null, (String) null);
        if (query2 != null) {
            while (query2.moveToNext()) {
                try {
                    string2 = query2.getString(0);
                    sQLiteDatabase.execSQL("DROP INDEX IF EXISTS " + string2);
                } catch (SQLException e2) {
                    if (Log.isLoggable("MessagingApp", 3)) {
                        C1430e.m3620d("MessagingApp", "unable to drop index " + string2 + " " + e2);
                    }
                } catch (Throwable th2) {
                    query2.close();
                    throw th2;
                }
            }
            query2.close();
        }
        Cursor query3 = sQLiteDatabase.query("sqlite_master", f1359nc, "type='trigger'", (String[]) null, (String) null, (String) null, (String) null);
        if (query3 != null) {
            while (query3.moveToNext()) {
                try {
                    string = query3.getString(0);
                    if (!string.startsWith("android_") && !string.startsWith("sqlite_")) {
                        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS " + string);
                    }
                } catch (SQLException e3) {
                    if (Log.isLoggable("MessagingApp", 3)) {
                        C1430e.m3620d("MessagingApp", "unable to drop trigger " + string + " " + e3);
                    }
                } catch (Throwable th3) {
                    query3.close();
                    throw th3;
                }
            }
            query3.close();
        }
        m2158d(sQLiteDatabase);
    }

    /* renamed from: d */
    private static void m2158d(SQLiteDatabase sQLiteDatabase) {
        for (String execSQL : f1360oc) {
            sQLiteDatabase.execSQL(execSQL);
        }
        for (String execSQL2 : f1361pc) {
            sQLiteDatabase.execSQL(execSQL2);
        }
        for (String execSQL3 : f1363rc) {
            sQLiteDatabase.execSQL(execSQL3);
        }
        for (String execSQL4 : f1362qc) {
            sQLiteDatabase.execSQL(execSQL4);
        }
        sQLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
        sQLiteDatabase.execSQL(m2159p(-1));
        C0947h.get().mo6607c(sQLiteDatabase);
    }

    public static C0951l getInstance(Context context) {
        C0951l lVar;
        synchronized (sLock) {
            if (f1364sc == null) {
                f1364sc = new C0951l(context);
            }
            lVar = f1364sc;
        }
        return lVar;
    }

    static C0951l getNewInstanceForTest(Context context) {
        C1424b.m3583Fj();
        C1424b.m3592ia(BugleApplication.m1223Za());
        return new C0951l(context);
    }

    /* renamed from: p */
    static String m2159p(int i) {
        return String.format("INSERT INTO participants ( sub_id ) VALUES ( %s )", new Object[]{Integer.valueOf(i)});
    }

    /* access modifiers changed from: package-private */
    public C0955p getDatabase() {
        C0955p pVar;
        C1424b.m3584Gj();
        synchronized (this.f1367lc) {
            if (this.f1365Ab == null) {
                this.f1365Ab = new C0955p(this.f1366kc, getWritableDatabase());
            }
            pVar = this.f1365Ab;
        }
        return pVar;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        m2158d(sQLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.f1368mc.onDowngrade(sQLiteDatabase, i, i2);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.f1368mc.mo6618a(sQLiteDatabase, i, i2);
    }

    /* renamed from: a */
    public static void m2156a(C0955p pVar) {
        for (String execSQL : f1363rc) {
            pVar.execSQL(execSQL);
        }
    }

    /* renamed from: a */
    public static void m2154a(SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query("sqlite_master", f1359nc, "type='view'", (String[]) null, (String) null, (String) null, (String) null);
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    m2155a(sQLiteDatabase, query.getString(0), false);
                } finally {
                    query.close();
                }
            }
        }
    }
}
