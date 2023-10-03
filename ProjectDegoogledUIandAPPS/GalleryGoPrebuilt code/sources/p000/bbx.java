package p000;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;

/* renamed from: bbx */
/* compiled from: PG */
final class bbx extends bek {

    /* renamed from: a */
    public final int f2017a;

    /* renamed from: b */
    public Bitmap f2018b;

    /* renamed from: c */
    private final Handler f2019c;

    /* renamed from: d */
    private final long f2020d;

    public bbx(Handler handler, int i, long j) {
        this.f2019c = handler;
        this.f2017a = i;
        this.f2020d = j;
    }

    /* renamed from: b */
    public final void mo1798b(Drawable drawable) {
        this.f2018b = null;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo1433a(Object obj, bex bex) {
        this.f2018b = (Bitmap) obj;
        this.f2019c.sendMessageAtTime(this.f2019c.obtainMessage(1, this), this.f2020d);
    }
}
