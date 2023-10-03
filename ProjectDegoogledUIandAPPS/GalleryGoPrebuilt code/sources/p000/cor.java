package p000;

import android.content.ContentValues;

/* renamed from: cor */
/* compiled from: PG */
final /* synthetic */ class cor implements hga {

    /* renamed from: a */
    private final long f5322a;

    /* renamed from: b */
    private final byte[] f5323b;

    /* renamed from: c */
    private final byte[] f5324c;

    public cor(long j, byte[] bArr, byte[] bArr2) {
        this.f5322a = j;
        this.f5323b = bArr;
        this.f5324c = bArr2;
    }

    /* renamed from: a */
    public final void mo2584a(hfz hfz) {
        long j = this.f5322a;
        byte[] bArr = this.f5323b;
        byte[] bArr2 = this.f5324c;
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("o", bArr);
        contentValues.put("w", 1);
        contentValues.put("ag", bArr2);
        hfz.mo7386a("mt", contentValues, "a = ?", String.valueOf(j));
    }
}
