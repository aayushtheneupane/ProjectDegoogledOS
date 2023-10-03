package p000;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;

/* renamed from: io */
/* compiled from: PG */
public abstract class C0237io {
    /* renamed from: a */
    public abstract void mo6613a();

    /* renamed from: a */
    public abstract void mo6614a(Typeface typeface);

    /* renamed from: a */
    public final void mo9035a(int i) {
        new Handler(Looper.getMainLooper()).post(new C0236in(this));
    }

    /* renamed from: b */
    public final void mo9036b(Typeface typeface) {
        new Handler(Looper.getMainLooper()).post(new C0235im(this, typeface));
    }
}
