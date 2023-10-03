package com.android.messaging.datamodel.data;

import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.android.messaging.datamodel.action.DeleteConversationAction;
import com.android.messaging.util.C1485y;
import com.google.common.base.C1504A;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.android.messaging.datamodel.data.q */
public class C0934q {

    /* renamed from: Wu */
    public static final String[] f1249Wu = {"_id", "name", "icon", "snippet_text", "sort_timestamp", "read", "preview_uri", "preview_content_type", "participant_contact_id", "participant_lookup_key", "participant_normalized_destination", "participant_count", "current_self_id", "include_email_addr", "message_status", "show_draft", "draft_preview_uri", "draft_preview_content_type", "draft_snippet_text", "archive_status", "message_id", "subject_text", "draft_subject_text", "raw_status", "snippet_sender_first_name", "snippet_sender_display_destination", "IS_ENTERPRISE"};

    /* renamed from: Hz */
    private String f1250Hz;

    /* renamed from: Ka */
    private String f1251Ka;

    /* renamed from: Yx */
    private boolean f1252Yx;

    /* renamed from: dy */
    private int f1253dy;

    /* renamed from: eA */
    private boolean f1254eA;

    /* renamed from: fA */
    private String f1255fA;

    /* renamed from: gA */
    private Uri f1256gA;

    /* renamed from: hA */
    private String f1257hA;

    /* renamed from: iA */
    private long f1258iA;

    /* renamed from: jA */
    private String f1259jA;

    /* renamed from: kA */
    private String f1260kA;

    /* renamed from: lA */
    private int f1261lA;
    private String mIcon;
    private String mName;
    private long mTimestamp;

    /* renamed from: nA */
    private int f1262nA;

    /* renamed from: oA */
    private boolean f1263oA;

    /* renamed from: pA */
    private Uri f1264pA;

    /* renamed from: qA */
    private String f1265qA;

    /* renamed from: rA */
    private String f1266rA;

    /* renamed from: sA */
    private boolean f1267sA;

    /* renamed from: tA */
    private String f1268tA;

    /* renamed from: uA */
    private String f1269uA;

    /* renamed from: vA */
    private String f1270vA;

    /* renamed from: wA */
    private String f1271wA;

    /* renamed from: xA */
    private boolean f1272xA;

    /* renamed from: Ff */
    public static final String m1989Ff() {
        return "conversation_list_view";
    }

    /* renamed from: Gf */
    public static final String m1990Gf() {
        return "CREATE VIEW conversation_list_view AS SELECT conversations._id as _id, conversations.name as name, conversations.current_self_id as current_self_id, conversations.archive_status as archive_status, messages.read as read, conversations.icon as icon, conversations.participant_contact_id as participant_contact_id, conversations.participant_lookup_key as participant_lookup_key, conversations.participant_normalized_destination as participant_normalized_destination, conversations.sort_timestamp as sort_timestamp, conversations.show_draft as show_draft, conversations.draft_snippet_text as draft_snippet_text, conversations.draft_preview_uri as draft_preview_uri, conversations.draft_subject_text as draft_subject_text, conversations.draft_preview_content_type as draft_preview_content_type, conversations.preview_uri as preview_uri, conversations.preview_content_type as preview_content_type, conversations.participant_count as participant_count, conversations.include_email_addr as include_email_addr, messages.message_status as message_status, messages.raw_status as raw_status, messages._id as message_id, participants.first_name as snippet_sender_first_name, participants.display_destination as snippet_sender_display_destination, conversations.IS_ENTERPRISE as IS_ENTERPRISE, conversations.snippet_text as snippet_text, conversations.subject_text as subject_text  FROM conversations LEFT JOIN messages ON (conversations.latest_message_id=messages._id)  LEFT JOIN participants ON (messages.sender_id=participants._id) ORDER BY conversations.sort_timestamp DESC";
    }

    /* renamed from: i */
    public static String m1991i(List list) {
        if (list.size() == 1) {
            return ((ParticipantData) list.get(0)).mo6330P(true);
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((ParticipantData) it.next()).mo6330P(false));
        }
        return C1504A.m3943Ua(", ").mo8510Vk().mo8513a((Iterable) arrayList);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0034  */
    /* renamed from: j */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.android.messaging.datamodel.data.C0934q m1992j(com.android.messaging.datamodel.C0955p r11, java.lang.String r12) {
        /*
            java.lang.String r1 = "conversation_list_view"
            r8 = 0
            java.lang.String[] r2 = f1249Wu     // Catch:{ all -> 0x0030 }
            java.lang.String r3 = "_id=?"
            r9 = 1
            java.lang.String[] r4 = new java.lang.String[r9]     // Catch:{ all -> 0x0030 }
            r10 = 0
            r4[r10] = r12     // Catch:{ all -> 0x0030 }
            r5 = 0
            r6 = 0
            r7 = 0
            r0 = r11
            android.database.Cursor r11 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0030 }
            int r12 = r11.getCount()     // Catch:{ all -> 0x002e }
            com.android.messaging.util.C1424b.m3588b(r12, r10, r9)     // Catch:{ all -> 0x002e }
            boolean r12 = r11.moveToFirst()     // Catch:{ all -> 0x002e }
            if (r12 == 0) goto L_0x002a
            com.android.messaging.datamodel.data.q r8 = new com.android.messaging.datamodel.data.q     // Catch:{ all -> 0x002e }
            r8.<init>()     // Catch:{ all -> 0x002e }
            r8.mo6513c(r11)     // Catch:{ all -> 0x002e }
        L_0x002a:
            r11.close()
            return r8
        L_0x002e:
            r12 = move-exception
            goto L_0x0032
        L_0x0030:
            r12 = move-exception
            r11 = r8
        L_0x0032:
            if (r11 == 0) goto L_0x0037
            r11.close()
        L_0x0037:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.data.C0934q.m1992j(com.android.messaging.datamodel.p, java.lang.String):com.android.messaging.datamodel.data.q");
    }

    /* renamed from: Hf */
    public String mo6492Hf() {
        return this.f1265qA;
    }

    /* renamed from: If */
    public Uri mo6493If() {
        return this.f1264pA;
    }

    /* renamed from: Jf */
    public String mo6494Jf() {
        return this.f1266rA;
    }

    /* renamed from: Kf */
    public String mo6495Kf() {
        return this.f1269uA;
    }

    /* renamed from: Lf */
    public String mo6496Lf() {
        return C1485y.m3835B(this.mTimestamp).toString();
    }

    /* renamed from: Mf */
    public boolean mo6497Mf() {
        return this.f1252Yx;
    }

    /* renamed from: Nf */
    public final boolean mo6498Nf() {
        int i = this.f1261lA;
        return i == 8 || i == 9 || i == 106 || i == 107;
    }

    /* renamed from: Of */
    public boolean mo6499Of() {
        return this.f1253dy > 1;
    }

    /* renamed from: Pf */
    public boolean mo6500Pf() {
        return !MessageData.m1759oa(this.f1261lA);
    }

    /* renamed from: Qf */
    public boolean mo6501Qf() {
        return this.f1254eA;
    }

    /* renamed from: Rf */
    public final boolean mo6502Rf() {
        int i = this.f1261lA;
        return i == 4 || i == 7 || i == 5 || i == 6;
    }

    /* renamed from: Sf */
    public int mo6503Sf() {
        return this.f1262nA;
    }

    /* renamed from: Tf */
    public String mo6504Tf() {
        return this.f1260kA;
    }

    /* renamed from: Ue */
    public String mo6505Ue() {
        return this.f1251Ka;
    }

    /* renamed from: Uf */
    public long mo6506Uf() {
        return this.f1258iA;
    }

    /* renamed from: Vf */
    public int mo6507Vf() {
        return this.f1253dy;
    }

    /* renamed from: We */
    public boolean mo6508We() {
        return this.f1267sA;
    }

    /* renamed from: Wf */
    public String mo6509Wf() {
        return this.f1259jA;
    }

    /* renamed from: Xf */
    public String mo6510Xf() {
        return this.f1257hA;
    }

    /* renamed from: Yf */
    public boolean mo6511Yf() {
        return this.f1263oA;
    }

    /* renamed from: Zf */
    public String mo6512Zf() {
        if (!TextUtils.isEmpty(this.f1270vA)) {
            return this.f1270vA;
        }
        return this.f1271wA;
    }

    /* renamed from: c */
    public void mo6513c(Cursor cursor) {
        boolean z = false;
        this.f1251Ka = cursor.getString(0);
        this.mName = cursor.getString(1);
        this.mIcon = cursor.getString(2);
        this.f1255fA = cursor.getString(3);
        this.mTimestamp = cursor.getLong(4);
        this.f1254eA = cursor.getInt(5) == 1;
        String string = cursor.getString(6);
        Uri uri = null;
        this.f1256gA = TextUtils.isEmpty(string) ? null : Uri.parse(string);
        this.f1257hA = cursor.getString(7);
        this.f1258iA = cursor.getLong(8);
        this.f1259jA = cursor.getString(9);
        this.f1260kA = cursor.getString(10);
        this.f1250Hz = cursor.getString(12);
        this.f1253dy = cursor.getInt(11);
        this.f1252Yx = cursor.getInt(13) == 1;
        this.f1261lA = cursor.getInt(14);
        this.f1262nA = cursor.getInt(23);
        this.f1263oA = cursor.getInt(15) == 1;
        String string2 = cursor.getString(16);
        if (!TextUtils.isEmpty(string2)) {
            uri = Uri.parse(string2);
        }
        this.f1264pA = uri;
        this.f1265qA = cursor.getString(17);
        this.f1266rA = cursor.getString(18);
        this.f1269uA = cursor.getString(22);
        this.f1267sA = cursor.getInt(19) == 1;
        this.f1268tA = cursor.getString(21);
        this.f1270vA = cursor.getString(24);
        this.f1271wA = cursor.getString(25);
        if (cursor.getInt(26) == 1) {
            z = true;
        }
        this.f1272xA = z;
    }

    public String getIcon() {
        return this.mIcon;
    }

    public int getMessageStatus() {
        return this.f1261lA;
    }

    public String getName() {
        return this.mName;
    }

    public Uri getPreviewUri() {
        return this.f1256gA;
    }

    public String getSnippetText() {
        return this.f1255fA;
    }

    public String getSubject() {
        return this.f1268tA;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public boolean isEnterprise() {
        return this.f1272xA;
    }

    /* renamed from: kf */
    public String mo6522kf() {
        return this.f1250Hz;
    }

    /* renamed from: pa */
    public void mo6523pa() {
        DeleteConversationAction.m1351a(this.f1251Ka, this.mTimestamp);
    }
}
