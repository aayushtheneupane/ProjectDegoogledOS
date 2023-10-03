package com.android.messaging.datamodel.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.util.C1410N;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1449g;
import com.android.messaging.util.C1464na;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import p026b.p027a.p030b.p031a.C0632a;

public class MessageData implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0897I();

    /* renamed from: RB */
    private static final String f1159RB;

    /* renamed from: ja */
    private static final String[] f1160ja = {"_id", "conversation_id", "sender_id", "self_id", "sent_timestamp", "received_timestamp", "seen", "read", "message_protocol", "message_status", "sms_message_uri", "sms_priority", "sms_message_size", "mms_subject", "mms_transaction_id", "mms_content_location", "mms_expiry", "raw_status", "retry_start_timestamp"};

    /* renamed from: BA */
    private boolean f1161BA;

    /* renamed from: CA */
    private Uri f1162CA;

    /* renamed from: DA */
    private int f1163DA;

    /* renamed from: EA */
    private long f1164EA;

    /* renamed from: FA */
    private String f1165FA;

    /* renamed from: GA */
    private long f1166GA;

    /* renamed from: Hz */
    private String f1167Hz;

    /* renamed from: Ka */
    private String f1168Ka;

    /* renamed from: NB */
    private String f1169NB;

    /* renamed from: OB */
    private String f1170OB;

    /* renamed from: PB */
    private int f1171PB;

    /* renamed from: QB */
    private long f1172QB;

    /* renamed from: Zx */
    private long f1173Zx;

    /* renamed from: _b */
    private boolean f1174_b;

    /* renamed from: _l */
    private final ArrayList f1175_l;

    /* renamed from: jy */
    private String f1176jy;
    private String mParticipantId;
    private int mProtocol;
    private int mStatus;

    /* renamed from: zA */
    private long f1177zA;

    static {
        StringBuilder Pa = C0632a.m1011Pa("INSERT INTO messages ( ");
        Pa.append(TextUtils.join(", ", Arrays.copyOfRange(f1160ja, 1, 19)));
        Pa.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        f1159RB = Pa.toString();
    }

    public MessageData() {
        this.f1175_l = new ArrayList();
    }

    /* renamed from: a */
    public static MessageData m1752a(String str, String str2, MessageData messageData) {
        MessageData messageData2 = new MessageData();
        messageData2.mStatus = 3;
        messageData2.mProtocol = -1;
        messageData2.f1168Ka = str;
        messageData2.mParticipantId = str2;
        messageData2.f1173Zx = System.currentTimeMillis();
        if (messageData == null) {
            messageData2.f1175_l.add(new MessagePartData(""));
        } else {
            if (!TextUtils.isEmpty(messageData.mParticipantId)) {
                messageData2.mParticipantId = messageData.mParticipantId;
            }
            if (!TextUtils.isEmpty(messageData.f1165FA)) {
                messageData2.f1165FA = messageData.f1165FA;
            }
            for (MessagePartData add : messageData.getParts()) {
                messageData2.f1175_l.add(add);
            }
        }
        messageData2.f1167Hz = str2;
        return messageData2;
    }

    /* renamed from: b */
    public static MessageData m1757b(String str, String str2, String str3) {
        MessageData messageData = new MessageData();
        messageData.mStatus = 3;
        messageData.mProtocol = 0;
        messageData.f1168Ka = str;
        messageData.mParticipantId = str2;
        messageData.f1167Hz = str2;
        messageData.f1175_l.add(new MessagePartData(str3));
        messageData.f1173Zx = System.currentTimeMillis();
        return messageData;
    }

    /* renamed from: g */
    public static MessageData m1758g(String str, String str2) {
        MessageData messageData = new MessageData();
        messageData.mStatus = 3;
        messageData.f1165FA = str2;
        if (!TextUtils.isEmpty(str)) {
            messageData.f1175_l.add(new MessagePartData(str));
        }
        return messageData;
    }

    public static String[] getProjection() {
        return f1160ja;
    }

    /* renamed from: oa */
    public static boolean m1759oa(int i) {
        return i >= 100;
    }

    /* renamed from: pa */
    static boolean m1760pa(int i) {
        if (C1464na.m3762ak()) {
            return false;
        }
        if (i == 106 || i == 101) {
            return true;
        }
        C1410N.m3547Nj();
        return false;
    }

    /* renamed from: qa */
    static boolean m1761qa(int i) {
        return i == 8;
    }

    /* renamed from: t */
    static boolean m1762t(int i, int i2) {
        return i == 8 && i2 == 0;
    }

    /* renamed from: Cg */
    public final long mo6242Cg() {
        return this.f1177zA;
    }

    /* renamed from: M */
    public final void mo6243M(boolean z) {
        this.f1161BA = z;
    }

    /* renamed from: Og */
    public boolean mo6244Og() {
        if (C1464na.m3762ak()) {
            return false;
        }
        int i = this.mStatus;
        if (i == 102 || i == 104) {
            return true;
        }
        return false;
    }

    /* renamed from: Pg */
    public boolean mo6245Pg() {
        if (C1464na.m3762ak()) {
            return false;
        }
        int i = this.mStatus;
        if (i == 106 || i == 101) {
            return true;
        }
        C1410N.m3547Nj();
        return false;
    }

    /* renamed from: Qg */
    public boolean mo6246Qg() {
        return this.mStatus == 8;
    }

    /* renamed from: Rg */
    public boolean mo6247Rg() {
        int i = this.mStatus;
        return i == 4 || i == 7;
    }

    /* renamed from: Sg */
    public final void mo6248Sg() {
        String property = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        MessagePartData messagePartData = null;
        int i = -1;
        for (int i2 = 0; i2 < this.f1175_l.size(); i2++) {
            MessagePartData messagePartData2 = (MessagePartData) this.f1175_l.get(i2);
            if (messagePartData == null && !messagePartData2.mo6300dh()) {
                i = i2;
                messagePartData = messagePartData2;
            }
            if (messagePartData2.mo6300dh() && !TextUtils.isEmpty(messagePartData2.getText())) {
                if (sb.length() > 0) {
                    sb.append(property);
                }
                sb.append(messagePartData2.getText());
            }
        }
        if (sb.length() != 0) {
            if (messagePartData == null) {
                mo6267g(new MessagePartData(sb.toString()));
                return;
            }
            String text = messagePartData.getText();
            if (text.length() > 0) {
                sb.append(property);
                sb.append(text);
            }
            this.f1175_l.set(i, new MessagePartData(sb.toString()));
        }
    }

    /* renamed from: Tg */
    public final MessagePartData mo6249Tg() {
        Iterator it = this.f1175_l.iterator();
        while (it.hasNext()) {
            MessagePartData messagePartData = (MessagePartData) it.next();
            if (messagePartData.mo6300dh()) {
                return messagePartData;
            }
        }
        return null;
    }

    /* renamed from: Ue */
    public final String mo6250Ue() {
        return this.f1168Ka;
    }

    /* renamed from: Ug */
    public final String mo6251Ug() {
        return this.f1170OB;
    }

    /* renamed from: Vg */
    public final String mo6252Vg() {
        return this.f1169NB;
    }

    /* renamed from: Wg */
    public final Uri mo6253Wg() {
        return this.f1162CA;
    }

    /* renamed from: Xg */
    public final boolean mo6254Xg() {
        return this.mStatus == 4;
    }

    /* renamed from: Yg */
    public final void mo6255Yg() {
        for (MessagePartData N : getParts()) {
            N.mo6292N(false);
        }
    }

    /* renamed from: ba */
    public final void mo6259ba(String str) {
        this.mParticipantId = str;
    }

    /* renamed from: c */
    public void mo6260c(Cursor cursor) {
        Uri uri;
        boolean z = false;
        this.f1176jy = cursor.getString(0);
        this.f1168Ka = cursor.getString(1);
        this.mParticipantId = cursor.getString(2);
        this.f1167Hz = cursor.getString(3);
        this.f1177zA = cursor.getLong(4);
        this.f1173Zx = cursor.getLong(5);
        this.f1161BA = cursor.getInt(6) != 0;
        if (cursor.getInt(7) != 0) {
            z = true;
        }
        this.f1174_b = z;
        this.mProtocol = cursor.getInt(8);
        this.mStatus = cursor.getInt(9);
        String string = cursor.getString(10);
        if (string == null) {
            uri = null;
        } else {
            uri = Uri.parse(string);
        }
        this.f1162CA = uri;
        this.f1163DA = cursor.getInt(11);
        this.f1164EA = cursor.getLong(12);
        this.f1166GA = cursor.getLong(16);
        this.f1171PB = cursor.getInt(17);
        this.f1165FA = cursor.getString(13);
        this.f1169NB = cursor.getString(14);
        this.f1170OB = cursor.getString(15);
        this.f1172QB = cursor.getLong(18);
    }

    /* renamed from: ca */
    public final void mo6261ca(String str) {
        this.f1167Hz = str;
    }

    /* renamed from: d */
    public SQLiteStatement mo6262d(C0955p pVar) {
        SQLiteStatement b = pVar.mo6624b(1, f1159RB);
        b.clearBindings();
        b.bindString(1, this.f1168Ka);
        b.bindString(2, this.mParticipantId);
        b.bindString(3, this.f1167Hz);
        b.bindLong(4, this.f1177zA);
        b.bindLong(5, this.f1173Zx);
        long j = 1;
        b.bindLong(6, this.f1161BA ? 1 : 0);
        if (!this.f1174_b) {
            j = 0;
        }
        b.bindLong(7, j);
        b.bindLong(8, (long) this.mProtocol);
        b.bindLong(9, (long) this.mStatus);
        Uri uri = this.f1162CA;
        if (uri != null) {
            b.bindString(10, uri.toString());
        }
        b.bindLong(11, (long) this.f1163DA);
        b.bindLong(12, this.f1164EA);
        b.bindLong(16, this.f1166GA);
        String str = this.f1165FA;
        if (str != null) {
            b.bindString(13, str);
        }
        String str2 = this.f1169NB;
        if (str2 != null) {
            b.bindString(14, str2);
        }
        String str3 = this.f1170OB;
        if (str3 != null) {
            b.bindString(15, str3);
        }
        b.bindLong(17, (long) this.f1171PB);
        b.bindLong(18, this.f1172QB);
        return b;
    }

    /* renamed from: da */
    public final void mo6263da(String str) {
        this.f1165FA = str;
    }

    public int describeContents() {
        return 0;
    }

    /* renamed from: ea */
    public void mo6265ea(String str) {
        C1424b.m3592ia(TextUtils.isEmpty(str) || TextUtils.isEmpty(this.f1176jy));
        this.f1176jy = str;
    }

    /* renamed from: ff */
    public final boolean mo6266ff() {
        int i = this.mProtocol;
        return i == 1 || i == 2;
    }

    public final String getMessageId() {
        return this.f1176jy;
    }

    public Iterable getParts() {
        return this.f1175_l;
    }

    public final int getProtocol() {
        return this.mProtocol;
    }

    public final int getStatus() {
        return this.mStatus;
    }

    /* renamed from: gg */
    public boolean mo6272gg() {
        return m1759oa(this.mStatus);
    }

    public boolean hasContent() {
        return !TextUtils.isEmpty(this.f1165FA) || mo6249Tg() != null || !TextUtils.isEmpty(mo6274hf());
    }

    /* renamed from: hf */
    public final String mo6274hf() {
        String property = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        Iterator it = this.f1175_l.iterator();
        while (it.hasNext()) {
            MessagePartData messagePartData = (MessagePartData) it.next();
            if (!messagePartData.mo6300dh() && !TextUtils.isEmpty(messagePartData.getText())) {
                if (sb.length() > 0) {
                    sb.append(property);
                }
                sb.append(messagePartData.getText());
            }
        }
        return sb.toString();
    }

    /* renamed from: kf */
    public final String mo6275kf() {
        return this.f1167Hz;
    }

    /* renamed from: l */
    public final boolean mo6276l(long j) {
        C1449g.get().getLong("bugle_download_timeout_in_millis", 1200000);
        return j - this.f1172QB < 1200000;
    }

    /* renamed from: lg */
    public final long mo6277lg() {
        return this.f1166GA;
    }

    /* renamed from: m */
    public final boolean mo6278m(long j) {
        C1449g.get().getLong("bugle_resend_timeout_in_millis", 1200000);
        return j - this.f1172QB < 1200000;
    }

    /* renamed from: mg */
    public final String mo6279mg() {
        return this.f1165FA;
    }

    /* renamed from: n */
    public final void mo6280n(long j) {
        this.f1177zA = j;
        this.mStatus = 8;
    }

    /* renamed from: o */
    public final void mo6281o(long j) {
        this.f1177zA = j;
        this.mStatus = 9;
    }

    /* renamed from: p */
    public final void mo6282p(long j) {
        this.f1177zA = j;
        this.mStatus = 7;
    }

    /* renamed from: pg */
    public final String mo6283pg() {
        return this.mParticipantId;
    }

    /* renamed from: q */
    public final void mo6284q(long j) {
        this.mStatus = 6;
        this.f1177zA = j;
    }

    /* renamed from: qg */
    public final int mo6285qg() {
        return this.f1171PB;
    }

    /* renamed from: r */
    public final void mo6286r(long j) {
        this.mStatus = 5;
        this.f1177zA = j;
    }

    /* renamed from: ra */
    public final void mo6287ra(int i) {
        this.f1171PB = i;
    }

    /* renamed from: rg */
    public final long mo6288rg() {
        return this.f1173Zx;
    }

    /* renamed from: s */
    public final void mo6289s(long j) {
        this.f1177zA = j;
        this.mStatus = 1;
    }

    public String toString() {
        return m1756a(this.f1176jy, this.f1175_l);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1176jy);
        parcel.writeString(this.f1168Ka);
        parcel.writeString(this.mParticipantId);
        parcel.writeString(this.f1167Hz);
        parcel.writeLong(this.f1177zA);
        parcel.writeLong(this.f1173Zx);
        parcel.writeInt(this.f1174_b ? 1 : 0);
        parcel.writeInt(this.f1161BA ? 1 : 0);
        parcel.writeInt(this.mProtocol);
        parcel.writeInt(this.mStatus);
        Uri uri = this.f1162CA;
        parcel.writeString(uri == null ? null : uri.toString());
        parcel.writeInt(this.f1163DA);
        parcel.writeLong(this.f1164EA);
        parcel.writeLong(this.f1166GA);
        parcel.writeString(this.f1165FA);
        parcel.writeString(this.f1169NB);
        parcel.writeString(this.f1170OB);
        parcel.writeInt(this.f1171PB);
        parcel.writeLong(this.f1172QB);
        parcel.writeInt(this.f1175_l.size());
        Iterator it = this.f1175_l.iterator();
        while (it.hasNext()) {
            parcel.writeParcelable((MessagePartData) it.next(), i);
        }
    }

    protected MessageData(Parcel parcel) {
        Uri uri;
        this.f1176jy = parcel.readString();
        this.f1168Ka = parcel.readString();
        this.mParticipantId = parcel.readString();
        this.f1167Hz = parcel.readString();
        this.f1177zA = parcel.readLong();
        this.f1173Zx = parcel.readLong();
        boolean z = true;
        this.f1161BA = parcel.readInt() != 0;
        this.f1174_b = parcel.readInt() == 0 ? false : z;
        this.mProtocol = parcel.readInt();
        this.mStatus = parcel.readInt();
        String readString = parcel.readString();
        if (readString == null) {
            uri = null;
        } else {
            uri = Uri.parse(readString);
        }
        this.f1162CA = uri;
        this.f1163DA = parcel.readInt();
        this.f1164EA = parcel.readLong();
        this.f1166GA = parcel.readLong();
        this.f1165FA = parcel.readString();
        this.f1169NB = parcel.readString();
        this.f1170OB = parcel.readString();
        this.f1171PB = parcel.readInt();
        this.f1172QB = parcel.readLong();
        this.f1175_l = new ArrayList();
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            this.f1175_l.add((MessagePartData) parcel.readParcelable(MessagePartData.class.getClassLoader()));
        }
    }

    /* renamed from: g */
    public void mo6267g(MessagePartData messagePartData) {
        if (messagePartData instanceof PendingAttachmentData) {
            C1424b.m3592ia(this.f1168Ka == null);
        }
        this.f1175_l.add(messagePartData);
    }

    /* renamed from: b */
    public void mo6258b(Cursor cursor, String str) {
        mo6260c(cursor);
        this.f1167Hz = str;
    }

    /* renamed from: a */
    public static MessageData m1753a(String str, String str2, String str3, String str4) {
        MessageData messageData = new MessageData();
        messageData.mStatus = 3;
        messageData.mProtocol = 1;
        messageData.f1168Ka = str;
        messageData.mParticipantId = str2;
        messageData.f1167Hz = str2;
        messageData.f1165FA = str4;
        messageData.f1173Zx = System.currentTimeMillis();
        if (!TextUtils.isEmpty(str3)) {
            messageData.f1175_l.add(new MessagePartData(str3));
        }
        return messageData;
    }

    /* renamed from: a */
    public static MessageData m1751a(Uri uri, String str, String str2, String str3, String str4, String str5, long j, long j2, boolean z, boolean z2) {
        MessageData messageData = new MessageData();
        messageData.f1162CA = uri;
        messageData.f1168Ka = str;
        messageData.mParticipantId = str2;
        messageData.f1167Hz = str3;
        messageData.mProtocol = 0;
        messageData.mStatus = 100;
        messageData.f1165FA = str5;
        messageData.f1173Zx = j2;
        messageData.f1177zA = j;
        messageData.f1175_l.add(new MessagePartData(str4));
        messageData.f1161BA = z;
        messageData.f1174_b = z2;
        return messageData;
    }

    /* renamed from: a */
    public static MessageData m1754a(String str, String str2, String str3, String str4, int i, boolean z, boolean z2, long j, long j2, String str5) {
        MessageData messageData = new MessageData();
        messageData.mParticipantId = str2;
        messageData.f1167Hz = str3;
        messageData.f1168Ka = str4;
        messageData.f1177zA = j;
        messageData.f1173Zx = j2;
        messageData.f1161BA = z;
        messageData.f1174_b = z2;
        messageData.mProtocol = 0;
        messageData.mStatus = i;
        messageData.f1162CA = Uri.parse(str);
        messageData.f1175_l.add(new MessagePartData(str5));
        return messageData;
    }

    /* renamed from: a */
    public static MessageData m1755a(String str, String str2, String str3, String str4, boolean z, int i, String str5, String str6, int i2, String str7, boolean z2, boolean z3, long j, int i3, long j2, long j3, long j4) {
        int i4 = i;
        long j5 = j4;
        MessageData messageData = new MessageData();
        messageData.mParticipantId = str2;
        messageData.f1167Hz = str3;
        messageData.f1168Ka = str4;
        messageData.f1177zA = j3;
        messageData.f1173Zx = j5;
        messageData.f1170OB = str5;
        messageData.f1169NB = str6;
        messageData.f1161BA = z2;
        messageData.f1174_b = z3;
        messageData.mStatus = i4;
        messageData.mProtocol = z ? 2 : 1;
        messageData.f1162CA = Uri.parse(str);
        messageData.f1163DA = i2;
        messageData.f1164EA = j;
        messageData.f1165FA = str7;
        messageData.f1166GA = j2;
        messageData.f1171PB = i3;
        if (i4 == 104 || i4 == 6) {
            messageData.f1172QB = j5;
        }
        return messageData;
    }

    /* renamed from: a */
    public void mo6256a(ContentValues contentValues) {
        contentValues.put("conversation_id", this.f1168Ka);
        contentValues.put("sender_id", this.mParticipantId);
        contentValues.put("self_id", this.f1167Hz);
        contentValues.put("sent_timestamp", Long.valueOf(this.f1177zA));
        contentValues.put("received_timestamp", Long.valueOf(this.f1173Zx));
        contentValues.put("seen", Integer.valueOf(this.f1161BA ? 1 : 0));
        contentValues.put("read", Integer.valueOf(this.f1174_b ? 1 : 0));
        contentValues.put("message_protocol", Integer.valueOf(this.mProtocol));
        contentValues.put("message_status", Integer.valueOf(this.mStatus));
        Uri uri = this.f1162CA;
        contentValues.put("sms_message_uri", uri == null ? null : uri.toString());
        contentValues.put("sms_priority", Integer.valueOf(this.f1163DA));
        contentValues.put("sms_message_size", Long.valueOf(this.f1164EA));
        contentValues.put("mms_expiry", Long.valueOf(this.f1166GA));
        contentValues.put("mms_subject", this.f1165FA);
        contentValues.put("mms_transaction_id", this.f1169NB);
        contentValues.put("mms_content_location", this.f1170OB);
        contentValues.put("raw_status", Integer.valueOf(this.f1171PB));
        contentValues.put("retry_start_timestamp", Long.valueOf(this.f1172QB));
    }

    /* renamed from: a */
    public final void mo6257a(String str, Uri uri, long j) {
        this.f1168Ka = str;
        this.f1162CA = uri;
        this.f1174_b = true;
        this.f1161BA = true;
        this.f1173Zx = j;
        this.f1177zA = j;
        this.mStatus = 4;
        this.f1172QB = j;
    }

    /* renamed from: a */
    public static String m1756a(String str, List list) {
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(str);
            sb.append(": ");
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            sb.append(((MessagePartData) it.next()).toString());
            sb.append(" ");
        }
        return sb.toString();
    }
}
