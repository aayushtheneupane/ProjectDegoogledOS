package p000;

import android.app.Activity;
import android.app.Application;
import java.util.HashMap;
import java.util.Map;

/* renamed from: ftl */
/* compiled from: PG */
public class ftl implements ioe, fti {

    /* renamed from: a */
    public final Activity f10570a;

    /* renamed from: b */
    public final Object f10571b = new Object();

    /* renamed from: c */
    public final Map f10572c = new HashMap();

    /* renamed from: d */
    private volatile Object f10573d;

    /* renamed from: e */
    private final Object f10574e = new Object();

    public ftl(Activity activity) {
        this.f10570a = activity;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Object mo6178a() {
        if (this.f10570a.getApplication() instanceof ioe) {
            return ((ftk) ((ioe) this.f10570a.getApplication()).mo2453b()).mo2322cv().mo2129a(this.f10570a).mo2128a();
        }
        if (Application.class.equals(this.f10570a.getApplication().getClass())) {
            throw new IllegalStateException("Sting Activity must be attached to an @Sting Application. Did you forget to specify your Application's class name in your manifest's <application />'s android:name attribute?");
        }
        String valueOf = String.valueOf(this.f10570a.getApplication().getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 65);
        sb.append("Sting Activity must be attached to an @Sting Application. Found: ");
        sb.append(valueOf);
        throw new IllegalStateException(sb.toString());
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public Object mo6179b(gkn gkn) {
        ife.m12878b(this.f10570a.getApplication() instanceof ioe, "Sting Activity must be attached to an @Sting Application. Found: %s", (Object) this.f10570a.getApplication().getClass());
        return ((ftj) ((fti) this.f10570a.getApplication()).mo2452a(gkn)).mo2460f().mo2137a(this.f10570a).mo2136a();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f10573d == null) {
            synchronized (this.f10574e) {
                if (this.f10573d == null) {
                    this.f10573d = mo6178a();
                }
            }
        }
        return this.f10573d;
    }

    /* renamed from: a */
    public Object mo2452a(gkn gkn) {
        Object obj;
        synchronized (this.f10571b) {
            if (!this.f10572c.containsKey(gkn)) {
                this.f10572c.put(gkn, mo6179b(gkn));
            }
            obj = this.f10572c.get(gkn);
        }
        return obj;
    }
}
