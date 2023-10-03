package p000;

import android.content.ServiceConnection;

/* renamed from: epz */
/* compiled from: PG */
public abstract class epz {

    /* renamed from: a */
    public static final Object f8818a = new Object();

    /* renamed from: b */
    public static epz f8819b;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo5147a(epy epy, ServiceConnection serviceConnection);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract boolean mo5149b(epy epy, ServiceConnection serviceConnection);

    /* renamed from: a */
    public final void mo5148a(String str, String str2, ServiceConnection serviceConnection) {
        mo5147a(new epy(str, str2), serviceConnection);
    }
}
