package p000;

import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/* renamed from: bdc */
/* compiled from: PG */
public final class bdc implements Handler.Callback {

    /* renamed from: f */
    private static final bdb f2072f = new bda();

    /* renamed from: a */
    private volatile apn f2073a;

    /* renamed from: b */
    private final Map f2074b = new HashMap();

    /* renamed from: c */
    private final Map f2075c = new HashMap();

    /* renamed from: d */
    private final Handler f2076d;

    /* renamed from: e */
    private final bdb f2077e;

    public bdc(bdb bdb) {
        new C0290kn();
        new C0290kn();
        new Bundle();
        this.f2077e = bdb == null ? f2072f : bdb;
        this.f2076d = new Handler(Looper.getMainLooper(), this);
    }

    /* renamed from: a */
    private static void m2179a(Activity activity) {
        int i = Build.VERSION.SDK_INT;
        if (activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    /* renamed from: c */
    private static Activity m2181c(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return m2181c(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    /* renamed from: a */
    public final apn mo1824a(Context context) {
        if (context != null) {
            if (bfp.m2437c() && !(context instanceof Application)) {
                if (context instanceof C0149fj) {
                    C0149fj fjVar = (C0149fj) context;
                    if (bfp.m2439d()) {
                        return mo1824a(fjVar.getApplicationContext());
                    }
                    m2179a((Activity) fjVar);
                    return mo1825a(fjVar, fjVar.mo5851d(), (C0147fh) null, m2180b(fjVar));
                } else if (context instanceof Activity) {
                    Activity activity = (Activity) context;
                    if (bfp.m2439d()) {
                        return mo1824a(activity.getApplicationContext());
                    }
                    m2179a(activity);
                    bcz a = mo1826a(activity.getFragmentManager(), m2180b(activity));
                    apn apn = a.f2069c;
                    if (apn != null) {
                        return apn;
                    }
                    apn a2 = this.f2077e.mo1393a(aow.m1346a((Context) activity), a.f2067a, a.f2068b, activity);
                    a.f2069c = a2;
                    return a2;
                } else if (context instanceof ContextWrapper) {
                    ContextWrapper contextWrapper = (ContextWrapper) context;
                    if (contextWrapper.getBaseContext().getApplicationContext() != null) {
                        return mo1824a(contextWrapper.getBaseContext());
                    }
                }
            }
            if (this.f2073a == null) {
                synchronized (this) {
                    if (this.f2073a == null) {
                        this.f2073a = this.f2077e.mo1393a(aow.m1346a(context.getApplicationContext()), new bcn(), new bcu(), context.getApplicationContext());
                    }
                }
            }
            return this.f2073a;
        }
        throw new IllegalArgumentException("You cannot start a load on a null Context");
    }

    /* renamed from: a */
    public final bcz mo1826a(FragmentManager fragmentManager, boolean z) {
        bcz bcz = (bcz) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (bcz == null && (bcz = (bcz) this.f2074b.get(fragmentManager)) == null) {
            bcz = new bcz();
            if (z) {
                bcz.f2067a.mo1811a();
            }
            this.f2074b.put(fragmentManager, bcz);
            fragmentManager.beginTransaction().add(bcz, "com.bumptech.glide.manager").commitAllowingStateLoss();
            this.f2076d.obtainMessage(1, fragmentManager).sendToTarget();
        }
        return bcz;
    }

    /* renamed from: a */
    public final bdg mo1827a(C0171gd gdVar, C0147fh fhVar, boolean z) {
        C0171gd b;
        bdg bdg = (bdg) gdVar.mo6418a("com.bumptech.glide.manager");
        if (bdg == null && (bdg = (bdg) this.f2075c.get(gdVar)) == null) {
            bdg = new bdg();
            bdg.f2087d = fhVar;
            if (!(fhVar == null || fhVar.mo2634k() == null || (b = bdg.m2188b(fhVar)) == null)) {
                bdg.mo1833a(fhVar.mo2634k(), b);
            }
            if (z) {
                bdg.f2083a.mo1811a();
            }
            this.f2075c.put(gdVar, bdg);
            C0182gn a = gdVar.mo6419a();
            a.mo6851a((C0147fh) bdg, "com.bumptech.glide.manager");
            a.mo5252c();
            this.f2076d.obtainMessage(2, gdVar).sendToTarget();
        }
        return bdg;
    }

    public final boolean handleMessage(Message message) {
        boolean z;
        C0171gd gdVar;
        int i = message.what;
        Object obj = null;
        if (i == 1) {
            FragmentManager fragmentManager = (FragmentManager) message.obj;
            gdVar = fragmentManager;
            obj = this.f2074b.remove(fragmentManager);
            z = true;
        } else if (i != 2) {
            z = false;
            gdVar = null;
        } else {
            C0171gd gdVar2 = (C0171gd) message.obj;
            gdVar = gdVar2;
            obj = this.f2075c.remove(gdVar2);
            z = true;
        }
        if (!z || obj != null || !Log.isLoggable("RMRetriever", 5)) {
            return z;
        }
        String valueOf = String.valueOf(gdVar);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 61);
        sb.append("Failed to remove expected request manager fragment, manager: ");
        sb.append(valueOf);
        Log.w("RMRetriever", sb.toString());
        return true;
    }

    /* renamed from: b */
    public static boolean m2180b(Context context) {
        Activity c = m2181c(context);
        return c == null || !c.isFinishing();
    }

    /* renamed from: a */
    public final apn mo1825a(Context context, C0171gd gdVar, C0147fh fhVar, boolean z) {
        bdg a = mo1827a(gdVar, fhVar, z);
        apn apn = a.f2086c;
        if (apn != null) {
            return apn;
        }
        apn a2 = this.f2077e.mo1393a(aow.m1346a(context), a.f2083a, a.f2085b, context);
        a.f2086c = a2;
        return a2;
    }
}
