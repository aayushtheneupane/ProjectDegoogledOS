package com.android.messaging.datamodel.data;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1449g;
import com.android.messaging.util.C1481w;
import com.android.messaging.util.C1485y;
import com.google.common.base.C1509F;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.data.s */
public class C0936s {

    /* renamed from: AB */
    private static final int f1274AB;

    /* renamed from: BB */
    private static final int f1275BB;

    /* renamed from: CB */
    private static final int f1276CB;

    /* renamed from: DB */
    private static final int f1277DB;

    /* renamed from: TA */
    private static final Character f1278TA = '\'';

    /* renamed from: UA */
    private static final StringBuilder f1279UA = new StringBuilder();

    /* renamed from: VA */
    private static final ArrayList f1280VA = new ArrayList();

    /* renamed from: WA */
    private static final Object f1281WA = new Object();

    /* renamed from: XA */
    private static final String f1282XA;

    /* renamed from: YA */
    private static final String f1283YA;

    /* renamed from: ZA */
    private static int f1284ZA;

    /* renamed from: _A */
    private static final int f1285_A;

    /* renamed from: aB */
    private static final int f1286aB;

    /* renamed from: bB */
    private static final int f1287bB;

    /* renamed from: cB */
    private static final int f1288cB;

    /* renamed from: dB */
    private static final int f1289dB;

    /* renamed from: eB */
    private static final int f1290eB;

    /* renamed from: fB */
    private static final int f1291fB;

    /* renamed from: gB */
    private static final int f1292gB;

    /* renamed from: hB */
    private static final int f1293hB;

    /* renamed from: iB */
    private static final int f1294iB;

    /* renamed from: jB */
    private static final int f1295jB;

    /* renamed from: ja */
    private static String[] f1296ja = {"_id", "conversation_id", "sender_id", "parts_ids", "parts_content_types", "parts_content_uris", "parts_widths", "parts_heights", "parts_texts", "parts_count", "sent_timestamp", "received_timestamp", "seen", "read", "message_protocol", "message_status", "sms_message_uri", "sms_priority", "sms_message_size", "mms_subject", "mms_expiry", "raw_status", "self_id", "full_name", "first_name", "display_destination", "normalized_destination", "profile_photo_uri", "contact_id", "lookup_key"};

    /* renamed from: kB */
    private static final int f1297kB;

    /* renamed from: lB */
    private static final int f1298lB;

    /* renamed from: nB */
    private static final int f1299nB;

    /* renamed from: oB */
    private static final int f1300oB;

    /* renamed from: pB */
    private static final int f1301pB;

    /* renamed from: qB */
    private static final int f1302qB;

    /* renamed from: rB */
    private static final int f1303rB;

    /* renamed from: sB */
    private static final int f1304sB;

    /* renamed from: tB */
    private static final int f1305tB;

    /* renamed from: uB */
    private static final int f1306uB;

    /* renamed from: vB */
    private static final int f1307vB;

    /* renamed from: wB */
    private static final int f1308wB;

    /* renamed from: xB */
    private static final int f1309xB;

    /* renamed from: yB */
    private static final int f1310yB;

    /* renamed from: zB */
    private static final int f1311zB;

    /* renamed from: BA */
    private boolean f1312BA;

    /* renamed from: CA */
    private String f1313CA;

    /* renamed from: DA */
    private int f1314DA;

    /* renamed from: EA */
    private int f1315EA;

    /* renamed from: FA */
    private String f1316FA;

    /* renamed from: GA */
    private long f1317GA;

    /* renamed from: HA */
    private int f1318HA;

    /* renamed from: JA */
    private String f1319JA;

    /* renamed from: KA */
    private String f1320KA;

    /* renamed from: Ka */
    private String f1321Ka;

    /* renamed from: LA */
    private String f1322LA;

    /* renamed from: MA */
    private String f1323MA;

    /* renamed from: OA */
    private String f1324OA;

    /* renamed from: PA */
    private long f1325PA;

    /* renamed from: QA */
    private String f1326QA;

    /* renamed from: RA */
    private boolean f1327RA;

    /* renamed from: SA */
    private boolean f1328SA;

    /* renamed from: Zx */
    private long f1329Zx;

    /* renamed from: _l */
    private List f1330_l;

    /* renamed from: _x */
    private String f1331_x;

    /* renamed from: jy */
    private String f1332jy;
    private String mParticipantId;
    private int mProtocol;
    private int mStatus;

    /* renamed from: yA */
    private int f1333yA;

    /* renamed from: zA */
    private long f1334zA;

    static {
        StringBuilder Pa = C0632a.m1011Pa("messages._id as _id, messages.conversation_id as conversation_id, messages.sender_id as sender_id, ");
        Pa.append(m2020a("_id", false, "parts_ids"));
        Pa.append(", ");
        Pa.append(m2020a("content_type", true, "parts_content_types"));
        Pa.append(", ");
        Pa.append(m2020a("uri", true, "parts_content_uris"));
        Pa.append(", ");
        Pa.append(m2020a("width", false, "parts_widths"));
        Pa.append(", ");
        Pa.append(m2020a("height", false, "parts_heights"));
        Pa.append(", ");
        Pa.append(m2020a("text", true, "parts_texts"));
        Pa.append(", ");
        Pa.append("count(parts._id)");
        Pa.append(" as ");
        Pa.append("parts_count");
        Pa.append(", ");
        Pa.append("messages");
        Pa.append('.');
        Pa.append("sent_timestamp");
        Pa.append(" as ");
        Pa.append("sent_timestamp");
        Pa.append(", ");
        Pa.append("messages");
        Pa.append('.');
        Pa.append("received_timestamp");
        Pa.append(" as ");
        Pa.append("received_timestamp");
        Pa.append(", ");
        Pa.append("messages");
        Pa.append('.');
        Pa.append("seen");
        Pa.append(" as ");
        Pa.append("seen");
        Pa.append(", ");
        Pa.append("messages");
        Pa.append('.');
        Pa.append("read");
        Pa.append(" as ");
        Pa.append("read");
        Pa.append(", ");
        Pa.append("messages");
        Pa.append('.');
        Pa.append("message_protocol");
        Pa.append(" as ");
        Pa.append("message_protocol");
        Pa.append(", ");
        Pa.append("messages");
        Pa.append('.');
        Pa.append("message_status");
        Pa.append(" as ");
        Pa.append("message_status");
        Pa.append(", ");
        Pa.append("messages");
        Pa.append('.');
        Pa.append("sms_message_uri");
        Pa.append(" as ");
        Pa.append("sms_message_uri");
        Pa.append(", ");
        Pa.append("messages");
        Pa.append('.');
        Pa.append("sms_priority");
        Pa.append(" as ");
        Pa.append("sms_priority");
        Pa.append(", ");
        Pa.append("messages");
        Pa.append('.');
        Pa.append("sms_message_size");
        Pa.append(" as ");
        Pa.append("sms_message_size");
        Pa.append(", ");
        Pa.append("messages");
        Pa.append('.');
        Pa.append("mms_subject");
        Pa.append(" as ");
        Pa.append("mms_subject");
        Pa.append(", ");
        Pa.append("messages");
        Pa.append('.');
        Pa.append("mms_expiry");
        Pa.append(" as ");
        Pa.append("mms_expiry");
        Pa.append(", ");
        Pa.append("messages");
        Pa.append('.');
        Pa.append("raw_status");
        Pa.append(" as ");
        Pa.append("raw_status");
        Pa.append(", ");
        Pa.append("messages");
        Pa.append('.');
        Pa.append("self_id");
        Pa.append(" as ");
        Pa.append("self_id");
        Pa.append(", ");
        Pa.append("participants");
        Pa.append('.');
        Pa.append("full_name");
        Pa.append(" as ");
        Pa.append("full_name");
        Pa.append(", ");
        Pa.append("participants");
        Pa.append('.');
        Pa.append("first_name");
        Pa.append(" as ");
        Pa.append("first_name");
        Pa.append(", ");
        Pa.append("participants");
        Pa.append('.');
        Pa.append("display_destination");
        Pa.append(" as ");
        Pa.append("display_destination");
        Pa.append(", ");
        Pa.append("participants");
        Pa.append('.');
        Pa.append("normalized_destination");
        Pa.append(" as ");
        Pa.append("normalized_destination");
        Pa.append(", ");
        Pa.append("participants");
        Pa.append('.');
        Pa.append("profile_photo_uri");
        Pa.append(" as ");
        Pa.append("profile_photo_uri");
        Pa.append(", ");
        Pa.append("participants");
        Pa.append('.');
        Pa.append("contact_id");
        Pa.append(" as ");
        Pa.append("contact_id");
        Pa.append(", ");
        Pa.append("participants");
        Pa.append('.');
        Pa.append("lookup_key");
        Pa.append(" as ");
        Pa.append("lookup_key");
        Pa.append(" ");
        f1282XA = Pa.toString();
        StringBuilder Pa2 = C0632a.m1011Pa("SELECT ");
        Pa2.append(f1282XA);
        Pa2.append(" FROM messages LEFT JOIN parts ON (messages._id=parts.message_id)  LEFT JOIN participants ON (messages.sender_id=participants._id) WHERE (messages.message_status <> 3");
        f1283YA = Pa2.toString();
        f1284ZA = 0;
        int i = f1284ZA;
        f1284ZA = i + 1;
        f1285_A = i;
        int i2 = f1284ZA;
        f1284ZA = i2 + 1;
        f1286aB = i2;
        int i3 = f1284ZA;
        f1284ZA = i3 + 1;
        f1287bB = i3;
        int i4 = f1284ZA;
        f1284ZA = i4 + 1;
        f1288cB = i4;
        int i5 = f1284ZA;
        f1284ZA = i5 + 1;
        f1289dB = i5;
        int i6 = f1284ZA;
        f1284ZA = i6 + 1;
        f1290eB = i6;
        int i7 = f1284ZA;
        f1284ZA = i7 + 1;
        f1291fB = i7;
        int i8 = f1284ZA;
        f1284ZA = i8 + 1;
        f1292gB = i8;
        int i9 = f1284ZA;
        f1284ZA = i9 + 1;
        f1293hB = i9;
        int i10 = f1284ZA;
        f1284ZA = i10 + 1;
        f1294iB = i10;
        int i11 = f1284ZA;
        f1284ZA = i11 + 1;
        f1295jB = i11;
        int i12 = f1284ZA;
        f1284ZA = i12 + 1;
        f1297kB = i12;
        int i13 = f1284ZA;
        f1284ZA = i13 + 1;
        f1298lB = i13;
        int i14 = f1284ZA;
        f1284ZA = i14 + 1;
        f1299nB = i14;
        int i15 = f1284ZA;
        f1284ZA = i15 + 1;
        f1300oB = i15;
        int i16 = f1284ZA;
        f1284ZA = i16 + 1;
        f1301pB = i16;
        int i17 = f1284ZA;
        f1284ZA = i17 + 1;
        f1302qB = i17;
        int i18 = f1284ZA;
        f1284ZA = i18 + 1;
        f1303rB = i18;
        int i19 = f1284ZA;
        f1284ZA = i19 + 1;
        f1304sB = i19;
        int i20 = f1284ZA;
        f1284ZA = i20 + 1;
        f1305tB = i20;
        int i21 = f1284ZA;
        f1284ZA = i21 + 1;
        f1306uB = i21;
        int i22 = f1284ZA;
        f1284ZA = i22 + 1;
        f1307vB = i22;
        int i23 = f1284ZA;
        f1284ZA = i23 + 1;
        f1308wB = i23;
        int i24 = f1284ZA;
        f1284ZA = i24 + 1;
        f1309xB = i24;
        int i25 = f1284ZA;
        f1284ZA = i25 + 1;
        f1310yB = i25;
        int i26 = f1284ZA;
        f1284ZA = i26 + 1;
        f1311zB = i26;
        int i27 = f1284ZA;
        f1284ZA = i27 + 1;
        f1274AB = i27;
        int i28 = f1284ZA;
        f1284ZA = i28 + 1;
        f1275BB = i28;
        int i29 = f1284ZA;
        f1284ZA = i29 + 1;
        f1276CB = i29;
        int i30 = f1284ZA;
        f1284ZA = i30 + 1;
        f1277DB = i30;
    }

    /* renamed from: Hg */
    public static final String m2018Hg() {
        return f1283YA + " AND " + "messages" + "." + "conversation_id" + "=? AND " + "message_status" + " IN (" + 2 + ", " + 1 + ", " + 4 + ", " + 5 + ", " + 6 + ", " + 7 + ", " + 100 + ", " + 101 + "))" + " GROUP BY parts.message_id ORDER BY messages.received_timestamp DESC";
    }

    /* renamed from: a */
    static MessagePartData m2019a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        if (C1481w.m3828Ca(str2)) {
            MessagePartData messagePartData = new MessagePartData(str6);
            messagePartData.mo6312ha(str);
            messagePartData.mo6301ea(str7);
            return messagePartData;
        }
        MessagePartData a = MessagePartData.m1806a(str2, Uri.parse(str3), Integer.parseInt(str4), Integer.parseInt(str5));
        a.mo6312ha(str);
        a.mo6301ea(str7);
        return a;
    }

    /* renamed from: eg */
    public static final String m2021eg() {
        return f1283YA + " AND " + "messages" + "." + "conversation_id" + "=?)" + " GROUP BY parts.message_id ORDER BY messages.received_timestamp DESC";
    }

    /* renamed from: fb */
    private static String m2022fb(String str) {
        return "group_concat(" + str + ", '" + '|' + "')";
    }

    public static String[] getProjection() {
        return f1296ja;
    }

    static List makeParts(String str, String str2, String str3, String str4, String str5, String str6, int i, String str7) {
        LinkedList linkedList = new LinkedList();
        if (i == 1) {
            linkedList.add(m2019a(str, str2, str3, str4, str5, str6, str7));
        } else {
            unpackMessageParts(linkedList, splitUnquotedString(str), splitQuotedString(str2), splitQuotedString(str3), splitUnquotedString(str4), splitUnquotedString(str5), splitQuotedString(str6), i, str7);
        }
        return linkedList;
    }

    /* renamed from: ng */
    public static final String m2023ng() {
        return f1283YA + " AND (" + "message_status" + " in (" + 100 + ", " + 101 + ") AND " + "seen" + " = 0))" + " GROUP BY parts.message_id ORDER BY messages.received_timestamp DESC";
    }

    /* renamed from: p */
    private boolean m2024p(Cursor cursor) {
        if (!TextUtils.equals(this.mParticipantId, cursor.getString(f1287bB))) {
            return false;
        }
        if (mo6546gg() != (cursor.getInt(f1301pB) >= 100)) {
            return false;
        }
        if (Math.abs(this.f1329Zx - cursor.getLong(f1297kB)) > 60000) {
            return false;
        }
        if (!TextUtils.equals(this.f1331_x, cursor.getString(f1308wB))) {
            return false;
        }
        return true;
    }

    static String[] splitQuotedString(String str) {
        String[] strArr;
        if (TextUtils.isEmpty(str)) {
            return new String[0];
        }
        synchronized (f1281WA) {
            int length = str.length();
            ArrayList arrayList = f1280VA;
            arrayList.clear();
            int i = -1;
            while (true) {
                boolean z = true;
                i++;
                if (i < length) {
                    C1424b.m3592ia(f1278TA.charValue() == str.charAt(i));
                    while (true) {
                        i++;
                        if (i >= length) {
                            break;
                        }
                        char charAt = str.charAt(i);
                        if (charAt == f1278TA.charValue()) {
                            char charAt2 = i < length + -1 ? str.charAt(i + 1) : 0;
                            if (charAt2 == f1278TA.charValue()) {
                                i++;
                            } else {
                                StringBuilder sb = f1279UA;
                                if (sb.length() > 0) {
                                    arrayList.add(sb.toString());
                                } else {
                                    arrayList.add("");
                                }
                                f1279UA.setLength(0);
                                if (charAt2 != '|') {
                                    if (charAt2 != 0) {
                                        z = false;
                                    }
                                }
                                C1424b.m3592ia(z);
                                i++;
                            }
                        }
                        f1279UA.append(charAt);
                    }
                } else {
                    strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
                }
            }
        }
        return strArr;
    }

    static String[] splitUnquotedString(String str) {
        if (TextUtils.isEmpty(str)) {
            return new String[0];
        }
        return str.split("\\|");
    }

    static void unpackMessageParts(List list, String[] strArr, String[] strArr2, String[] strArr3, String[] strArr4, String[] strArr5, String[] strArr6, int i, String str) {
        String[] strArr7 = strArr;
        String[] strArr8 = strArr2;
        String[] strArr9 = strArr3;
        String[] strArr10 = strArr4;
        String[] strArr11 = strArr5;
        String[] strArr12 = strArr6;
        int i2 = i;
        C1424b.equals(i2, strArr7.length);
        C1424b.equals(i2, strArr8.length);
        C1424b.equals(i2, strArr9.length);
        C1424b.equals(i2, strArr10.length);
        C1424b.equals(i2, strArr11.length);
        C1424b.equals(i2, strArr12.length);
        for (int i3 = 0; i3 < i2; i3++) {
            List list2 = list;
            list.add(m2019a(strArr7[i3], strArr8[i3], strArr9[i3], strArr10[i3], strArr11[i3], strArr12[i3], str));
        }
        List list3 = list;
        if (list.size() != i2) {
            StringBuilder Pa = C0632a.m1011Pa("Only unpacked ");
            Pa.append(list.size());
            Pa.append(" parts from message (id=");
            Pa.append(str);
            Pa.append("), expected ");
            Pa.append(i2);
            Pa.append(" parts");
            C1430e.wtf("MessagingApp", Pa.toString());
        }
    }

    /* renamed from: Ag */
    public String mo6525Ag() {
        return this.f1323MA;
    }

    /* renamed from: Bg */
    public Uri mo6526Bg() {
        String str = this.f1324OA;
        if (str == null) {
            return null;
        }
        return Uri.parse(str);
    }

    /* renamed from: Cg */
    public final long mo6527Cg() {
        return this.f1334zA;
    }

    /* renamed from: Dg */
    public boolean mo6528Dg() {
        return MessageData.m1760pa(this.mStatus);
    }

    /* renamed from: Eg */
    public boolean mo6529Eg() {
        return MessageData.m1761qa(this.mStatus);
    }

    /* renamed from: Fg */
    public final int mo6530Fg() {
        return this.f1315EA;
    }

    /* renamed from: Gg */
    public final int mo6531Gg() {
        return this.f1314DA;
    }

    /* renamed from: Ig */
    public boolean mo6532Ig() {
        int i = this.mStatus;
        return i == 107 || i == 106;
    }

    /* renamed from: Ue */
    public final String mo6533Ue() {
        return this.f1321Ka;
    }

    /* renamed from: _f */
    public List mo6534_f() {
        return mo6535a((C1509F) null);
    }

    /* renamed from: ag */
    public boolean mo6536ag() {
        return this.f1328SA;
    }

    /* renamed from: bg */
    public boolean mo6537bg() {
        return this.f1327RA;
    }

    /* renamed from: c */
    public void mo6538c(Cursor cursor) {
        this.f1332jy = cursor.getString(f1285_A);
        this.f1321Ka = cursor.getString(f1286aB);
        this.mParticipantId = cursor.getString(f1287bB);
        this.f1333yA = cursor.getInt(f1294iB);
        this.f1330_l = makeParts(cursor.getString(f1288cB), cursor.getString(f1289dB), cursor.getString(f1290eB), cursor.getString(f1291fB), cursor.getString(f1292gB), cursor.getString(f1293hB), this.f1333yA, this.f1332jy);
        this.f1334zA = cursor.getLong(f1295jB);
        this.f1329Zx = cursor.getLong(f1297kB);
        this.f1312BA = cursor.getInt(f1298lB) != 0;
        int i = cursor.getInt(f1299nB);
        this.mProtocol = cursor.getInt(f1300oB);
        this.mStatus = cursor.getInt(f1301pB);
        this.f1313CA = cursor.getString(f1302qB);
        this.f1314DA = cursor.getInt(f1303rB);
        this.f1315EA = cursor.getInt(f1304sB);
        this.f1316FA = cursor.getString(f1305tB);
        this.f1317GA = cursor.getLong(f1306uB);
        this.f1318HA = cursor.getInt(f1307vB);
        this.f1319JA = cursor.getString(f1309xB);
        this.f1320KA = cursor.getString(f1310yB);
        this.f1322LA = cursor.getString(f1311zB);
        this.f1323MA = cursor.getString(f1274AB);
        this.f1324OA = cursor.getString(f1275BB);
        this.f1325PA = cursor.getLong(f1276CB);
        this.f1326QA = cursor.getString(f1277DB);
        this.f1331_x = cursor.getString(f1308wB);
        if (cursor.isFirst() || !cursor.moveToPrevious()) {
            this.f1327RA = false;
        } else {
            this.f1327RA = m2024p(cursor);
            cursor.moveToNext();
        }
        if (cursor.isLast() || !cursor.moveToNext()) {
            this.f1328SA = false;
            return;
        }
        this.f1328SA = m2024p(cursor);
        cursor.moveToPrevious();
    }

    /* renamed from: cg */
    public boolean mo6539cg() {
        return hasText() && (!mo6546gg() || this.mStatus == 100);
    }

    /* renamed from: dg */
    public boolean mo6540dg() {
        int i = this.mStatus;
        return i == 1 || i == 2 || i == 100;
    }

    /* renamed from: fg */
    public final String mo6541fg() {
        return C1485y.m3838E(this.f1329Zx).toString();
    }

    public final String getMessageId() {
        return this.f1332jy;
    }

    public List getParts() {
        return this.f1330_l;
    }

    public final int getStatus() {
        return this.mStatus;
    }

    public String getText() {
        StringBuilder sb = null;
        String str = null;
        boolean z = false;
        String str2 = null;
        for (MessagePartData messagePartData : this.f1330_l) {
            if (messagePartData.isText()) {
                if (!z) {
                    str2 = messagePartData.getText();
                    z = true;
                } else {
                    if (sb == null) {
                        sb = new StringBuilder();
                        if (!TextUtils.isEmpty(str2)) {
                            sb.append(str2);
                        }
                        C1449g.get().getString("bugle_mms_text_concat_separator", " ");
                        str = " ";
                    }
                    String text = messagePartData.getText();
                    if (!TextUtils.isEmpty(text)) {
                        if (!TextUtils.isEmpty(str) && sb.length() > 0) {
                            sb.append(str);
                        }
                        sb.append(text);
                    }
                }
            }
        }
        if (sb == null) {
            return str2;
        }
        return sb.toString();
    }

    /* renamed from: gg */
    public boolean mo6546gg() {
        return this.mStatus >= 100;
    }

    public boolean hasText() {
        for (MessagePartData isText : this.f1330_l) {
            if (isText.isText()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: hg */
    public final boolean mo6548hg() {
        return this.mProtocol == 2;
    }

    /* renamed from: ig */
    public final boolean mo6549ig() {
        return this.f1312BA;
    }

    /* renamed from: jg */
    public boolean mo6550jg() {
        int i = this.mStatus;
        return i == 1 || i == 2;
    }

    /* renamed from: kg */
    public final boolean mo6551kg() {
        return this.mProtocol == 0;
    }

    /* renamed from: lg */
    public final long mo6552lg() {
        return this.f1317GA;
    }

    /* renamed from: mf */
    public boolean mo6553mf() {
        for (MessagePartData dh : this.f1330_l) {
            if (dh.mo6300dh()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: mg */
    public final String mo6554mg() {
        return this.f1316FA;
    }

    /* renamed from: og */
    public boolean mo6555og() {
        return MessageData.m1762t(this.mStatus, this.f1318HA);
    }

    /* renamed from: pg */
    public final String mo6556pg() {
        return this.mParticipantId;
    }

    /* renamed from: qg */
    public final int mo6557qg() {
        return this.f1318HA;
    }

    /* renamed from: rg */
    public final long mo6558rg() {
        return this.f1329Zx;
    }

    /* renamed from: sg */
    public final String mo6559sg() {
        return this.f1331_x;
    }

    /* renamed from: tg */
    public long mo6560tg() {
        return this.f1325PA;
    }

    public String toString() {
        return MessageData.m1756a(this.f1332jy, this.f1330_l);
    }

    /* renamed from: ug */
    public String mo6562ug() {
        return this.f1326QA;
    }

    /* renamed from: vg */
    public Uri mo6563vg() {
        if (this.f1325PA <= -1 || TextUtils.isEmpty(this.f1326QA)) {
            return null;
        }
        return ContactsContract.Contacts.getLookupUri(this.f1325PA, this.f1326QA);
    }

    /* renamed from: wg */
    public String mo6564wg() {
        return this.f1322LA;
    }

    /* renamed from: xg */
    public String mo6565xg() {
        if (!TextUtils.isEmpty(this.f1319JA)) {
            return this.f1319JA;
        }
        if (!TextUtils.isEmpty(this.f1320KA)) {
            return this.f1320KA;
        }
        return this.f1322LA;
    }

    /* renamed from: yg */
    public String mo6566yg() {
        return this.f1320KA;
    }

    /* renamed from: zg */
    public String mo6567zg() {
        return this.f1319JA;
    }

    /* renamed from: a */
    public List mo6535a(C1509F f) {
        if (this.f1330_l.isEmpty()) {
            return Collections.emptyList();
        }
        LinkedList linkedList = new LinkedList();
        for (MessagePartData messagePartData : this.f1330_l) {
            if (messagePartData.mo6300dh() && (f == null || f.apply(messagePartData))) {
                linkedList.add(messagePartData);
            }
        }
        return linkedList;
    }

    /* renamed from: a */
    private static String m2020a(String str, boolean z, String str2) {
        String str3;
        String d = C0632a.m1023d("ifnull(", C0632a.m1025k("parts.", str), ",'')");
        if (z) {
            str3 = m2022fb(C0632a.m1023d("quote(", d, ")"));
        } else {
            str3 = m2022fb(d);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("CASE WHEN (count(parts._id)>1) THEN ");
        sb.append(str3);
        sb.append(" ELSE ");
        sb.append("parts." + str);
        sb.append(" END AS ");
        sb.append(str2);
        return sb.toString();
    }
}
