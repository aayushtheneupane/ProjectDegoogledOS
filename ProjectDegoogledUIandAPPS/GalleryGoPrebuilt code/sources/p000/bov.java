package p000;

import android.content.ContentValues;

/* renamed from: bov */
/* compiled from: PG */
final /* synthetic */ class bov implements hga {

    /* renamed from: a */
    private final ContentValues f3279a;

    /* renamed from: b */
    private final long f3280b;

    public bov(ContentValues contentValues, long j) {
        this.f3279a = contentValues;
        this.f3280b = j;
    }

    /* renamed from: a */
    public final void mo2584a(hfz hfz) {
        boolean z = true;
        if (hfz.mo7386a("mt", this.f3279a, "a = ?", String.valueOf(this.f3280b)) != 1) {
            z = false;
        }
        cwn.m5512a(z, "CompressionDao: Failed to mark media as compressed", new Object[0]);
    }
}
