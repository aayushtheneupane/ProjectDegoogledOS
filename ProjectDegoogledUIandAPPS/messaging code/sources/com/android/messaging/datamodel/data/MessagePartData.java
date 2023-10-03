package com.android.messaging.datamodel.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.datamodel.MediaScratchFileProvider;
import com.android.messaging.datamodel.action.UpdateMessagePartSizeAction;
import com.android.messaging.util.C1416U;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1478ua;
import com.android.messaging.util.C1481w;
import com.android.messaging.util.C1488za;
import com.android.messaging.util.GifTranscoder;
import java.util.Arrays;
import p026b.p027a.p030b.p031a.C0632a;

public class MessagePartData implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0898J();

    /* renamed from: UB */
    public static final String[] f1178UB = {"image/jpeg", "image/jpg", "image/png", "image/gif", "image/vnd.wap.wbmp", "image/x-ms-bmp", "video/3gp", "video/3gpp", "video/3gpp2", "video/h263", "video/m4v", "video/mp4", "video/mpeg", "video/mpeg4", "video/webm", "audio/mp3", "audio/mp4", "audio/midi", "audio/mid", "audio/amr", "audio/x-wav", "audio/aac", "audio/x-midi", "audio/x-mid", "audio/x-mp3"};

    /* renamed from: VB */
    private static final String f1179VB;

    /* renamed from: ja */
    private static final String[] f1180ja = {"_id", "message_id", "text", "uri", "content_type", "width", "height"};

    /* renamed from: SB */
    private String f1181SB;

    /* renamed from: TB */
    private boolean f1182TB;

    /* renamed from: jy */
    private String f1183jy;

    /* renamed from: kv */
    private Uri f1184kv;
    private String mContentType;
    private boolean mDestroyed;
    private int mHeight;
    private String mText;
    private int mWidth;

    static {
        StringBuilder Pa = C0632a.m1011Pa("INSERT INTO parts ( ");
        Pa.append(TextUtils.join(",", Arrays.copyOfRange(f1180ja, 1, 7)));
        Pa.append(", ");
        Pa.append("conversation_id");
        Pa.append(") VALUES (?, ?, ?, ?, ?, ?, ?)");
        f1179VB = Pa.toString();
    }

    protected MessagePartData(String str) {
        this((String) null, str, "text/plain", (Uri) null, -1, -1, false);
    }

    /* renamed from: Zg */
    public static MessagePartData m1805Zg() {
        return new MessagePartData((String) null, "", "text/plain", (Uri) null, -1, -1, false);
    }

    /* renamed from: a */
    public static MessagePartData m1806a(String str, Uri uri, int i, int i2) {
        return new MessagePartData((String) null, (String) null, str, uri, i, i2, false);
    }

    public static MessagePartData createFromCursor(Cursor cursor) {
        MessagePartData messagePartData = new MessagePartData((String) null, (Uri) null, -1, -1);
        messagePartData.mo6297c(cursor);
        return messagePartData;
    }

    /* renamed from: fa */
    public static MessagePartData m1808fa(String str) {
        return new MessagePartData((String) null, str, "text/plain", (Uri) null, -1, -1, false);
    }

    /* renamed from: ga */
    public static boolean m1809ga(String str) {
        return C1481w.m3829Da(str) || Arrays.asList(f1178UB).contains(str);
    }

    public static String[] getProjection() {
        return f1180ja;
    }

    /* renamed from: N */
    public void mo6292N(boolean z) {
        if (mo6304fh()) {
            Rect g = C1416U.m3570g(C0967f.get().getApplicationContext(), this.f1184kv);
            if (g.width() != -1 && g.height() != -1) {
                this.mWidth = g.width();
                this.mHeight = g.height();
                if (z) {
                    UpdateMessagePartSizeAction.m1462a(this.f1181SB, this.mWidth, this.mHeight);
                }
            }
        }
    }

    /* renamed from: O */
    public void mo6293O(boolean z) {
        this.f1182TB = z;
    }

    /* renamed from: _g */
    public void mo6294_g() {
        Uri jh = mo6318jh();
        if (jh != null) {
            C1478ua.m3823a((Runnable) new C0899K(this, jh));
        }
    }

    /* renamed from: ah */
    public void mo6295ah() {
        Uri jh = mo6318jh();
        if (jh != null) {
            C0967f.get().getApplicationContext().getContentResolver().delete(jh, (String) null, (String[]) null);
        }
    }

    /* renamed from: bh */
    public long mo6296bh() {
        C1424b.m3584Gj();
        if (!mo6300dh()) {
            return 0;
        }
        if (mo6304fh()) {
            if (!C1416U.m3569b(this.mContentType, this.f1184kv)) {
                return 16384;
            }
            long w = C1488za.m3874w(this.f1184kv);
            mo6292N(false);
            return GifTranscoder.m3540G(this.mWidth, this.mHeight) ? (long) (((float) w) * 0.35f) : w;
        } else if (isMedia()) {
            return C1488za.m3874w(this.f1184kv);
        } else {
            StringBuilder Pa = C0632a.m1011Pa("Unknown attachment type ");
            Pa.append(getContentType());
            C1430e.m3622e("MessagingAppDataModel", Pa.toString());
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo6297c(Cursor cursor) {
        this.f1181SB = cursor.getString(0);
        this.f1183jy = cursor.getString(1);
        this.mText = cursor.getString(2);
        this.f1184kv = C1488za.m3868Oa(cursor.getString(3));
        this.mContentType = cursor.getString(4);
        this.mWidth = cursor.getInt(5);
        this.mHeight = cursor.getInt(6);
    }

    /* renamed from: ch */
    public final String mo6298ch() {
        return this.f1181SB;
    }

    public int describeContents() {
        return 0;
    }

    /* renamed from: dh */
    public boolean mo6300dh() {
        return this.f1184kv != null;
    }

    /* renamed from: ea */
    public void mo6301ea(String str) {
        C1424b.m3592ia(TextUtils.isEmpty(str) || TextUtils.isEmpty(this.f1183jy));
        this.f1183jy = str;
    }

    /* renamed from: eh */
    public boolean mo6302eh() {
        return C1481w.m3831za(this.mContentType);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MessagePartData)) {
            return false;
        }
        MessagePartData messagePartData = (MessagePartData) obj;
        if (this.mWidth == messagePartData.mWidth && this.mHeight == messagePartData.mHeight && TextUtils.equals(this.f1183jy, messagePartData.f1183jy) && TextUtils.equals(this.mText, messagePartData.mText) && TextUtils.equals(this.mContentType, messagePartData.mContentType)) {
            Uri uri = this.f1184kv;
            if (uri == null) {
                if (messagePartData.f1184kv == null) {
                    return true;
                }
            } else if (uri.equals(messagePartData.f1184kv)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: fh */
    public boolean mo6304fh() {
        return C1481w.isImageType(this.mContentType);
    }

    public final String getContentType() {
        return this.mContentType;
    }

    public final Uri getContentUri() {
        return this.f1184kv;
    }

    public final int getHeight() {
        return this.mHeight;
    }

    public final String getMessageId() {
        return this.f1183jy;
    }

    public final String getText() {
        return this.mText;
    }

    public final int getWidth() {
        return this.mWidth;
    }

    /* renamed from: gh */
    public boolean mo6311gh() {
        return this.f1182TB;
    }

    /* renamed from: ha */
    public void mo6312ha(String str) {
        C1424b.m3592ia(TextUtils.isEmpty(str) || TextUtils.isEmpty(this.f1181SB));
        this.f1181SB = str;
    }

    public int hashCode() {
        int i = (((527 + this.mWidth) * 31) + this.mHeight) * 31;
        String str = this.f1183jy;
        int i2 = 0;
        int hashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.mText;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.mContentType;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Uri uri = this.f1184kv;
        if (uri != null) {
            i2 = uri.hashCode();
        }
        return hashCode3 + i2;
    }

    /* renamed from: hh */
    public boolean mo6314hh() {
        return C1481w.m3829Da(this.mContentType);
    }

    /* renamed from: ih */
    public boolean mo6315ih() {
        return C1481w.m3830Ea(this.mContentType);
    }

    public boolean isMedia() {
        return C1481w.m3827Ba(this.mContentType);
    }

    public boolean isText() {
        return C1481w.m3828Ca(this.mContentType);
    }

    /* access modifiers changed from: protected */
    /* renamed from: jh */
    public Uri mo6318jh() {
        C1424b.m3592ia(!this.mDestroyed);
        this.mDestroyed = true;
        Uri uri = this.f1184kv;
        this.f1184kv = null;
        this.mContentType = null;
        if (!MediaScratchFileProvider.m1258d(uri)) {
            return null;
        }
        return uri;
    }

    /* renamed from: k */
    public SQLiteStatement mo6319k(C0955p pVar, String str) {
        SQLiteStatement b = pVar.mo6624b(0, f1179VB);
        b.clearBindings();
        b.bindString(1, this.f1183jy);
        String str2 = this.mText;
        if (str2 != null) {
            b.bindString(2, str2);
        }
        Uri uri = this.f1184kv;
        if (uri != null) {
            b.bindString(3, uri.toString());
        }
        String str3 = this.mContentType;
        if (str3 != null) {
            b.bindString(4, str3);
        }
        b.bindLong(5, (long) this.mWidth);
        b.bindLong(6, (long) this.mHeight);
        b.bindString(7, str);
        return b;
    }

    public String toString() {
        if (isText()) {
            return C1430e.m3633xa(getText());
        }
        return getContentType() + " (" + getContentUri() + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        C1424b.m3592ia(!this.mDestroyed);
        parcel.writeString(this.f1183jy);
        parcel.writeString(this.mText);
        parcel.writeString(C1488za.m3865E(this.f1184kv));
        parcel.writeString(this.mContentType);
        parcel.writeInt(this.mWidth);
        parcel.writeInt(this.mHeight);
    }

    protected MessagePartData() {
        this((String) null, (String) null, (String) null, (Uri) null, -1, -1, false);
    }

    protected MessagePartData(String str, Uri uri, int i, int i2) {
        this((String) null, (String) null, str, uri, i, i2, false);
    }

    /* renamed from: a */
    public static MessagePartData m1807a(String str, String str2, Uri uri, int i, int i2) {
        return new MessagePartData((String) null, str, str2, uri, i, i2, false);
    }

    protected MessagePartData(String str, String str2, Uri uri, int i, int i2, boolean z) {
        this((String) null, str, str2, uri, i, i2, z);
    }

    private MessagePartData(String str, String str2, String str3, Uri uri, int i, int i2, boolean z) {
        this.f1183jy = str;
        this.mText = str2;
        this.mContentType = str3;
        this.f1184kv = uri;
        this.mWidth = i;
        this.mHeight = i2;
        this.f1182TB = z;
    }

    protected MessagePartData(Parcel parcel) {
        this.f1183jy = parcel.readString();
        this.mText = parcel.readString();
        this.f1184kv = C1488za.m3868Oa(parcel.readString());
        this.mContentType = parcel.readString();
        this.mWidth = parcel.readInt();
        this.mHeight = parcel.readInt();
    }
}
