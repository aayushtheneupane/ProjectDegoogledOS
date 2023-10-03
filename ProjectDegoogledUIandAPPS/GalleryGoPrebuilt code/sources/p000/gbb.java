package p000;

import android.os.Build;
import java.util.concurrent.Executor;

/* renamed from: gbb */
/* compiled from: PG */
public abstract class gbb extends ibr implements Runnable {

    /* renamed from: a */
    private final gba f10822a;

    public /* synthetic */ gbb(gba gba) {
        this.f10822a = gba;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo6383a(gba gba);

    /* renamed from: b */
    public static gbb m9975b(gba gba) {
        int i = Build.VERSION.SDK_INT;
        return new gaz(gba);
    }

    /* renamed from: a */
    public final void mo6387a(Executor executor) {
        executor.execute(hmq.m11748a((Runnable) this));
    }

    /* renamed from: a */
    public final String mo6386a() {
        String a = this.f10822a.mo6369a();
        StringBuilder sb = new StringBuilder(String.valueOf(a).length() + 8);
        sb.append("query=[");
        sb.append(a);
        sb.append("]");
        return sb.toString();
    }

    public final void run() {
        hlj a;
        if (!isCancelled()) {
            try {
                String valueOf = String.valueOf(this.f10822a.mo6369a());
                a = hnb.m11766a(valueOf.length() == 0 ? new String("Query: ") : "Query: ".concat(valueOf), hnf.f13084a);
                mo6383a(this.f10822a);
                if (a != null) {
                    a.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                mo6952a(th);
                return;
            }
        } else {
            return;
        }
        throw th;
    }
}
