package p000;

import android.database.sqlite.SQLiteException;
import android.util.Log;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;

/* renamed from: bu */
/* compiled from: PG */
public final class C0050bu {

    /* renamed from: a */
    public final C0053bx f3599a;

    /* renamed from: b */
    public AtomicBoolean f3600b = new AtomicBoolean(false);

    /* renamed from: c */
    public volatile boolean f3601c = false;

    /* renamed from: d */
    public volatile C0037bh f3602d;

    /* renamed from: e */
    public final C0303l f3603e = new C0303l();

    /* renamed from: f */
    public Runnable f3604f = new C0048bs(this);

    /* renamed from: g */
    private final HashMap f3605g;

    /* renamed from: h */
    private final String[] f3606h;

    /* renamed from: i */
    private C0049bt f3607i;

    public C0050bu(C0053bx bxVar, Map map, String... strArr) {
        this.f3599a = bxVar;
        int length = strArr.length;
        this.f3607i = new C0049bt(length);
        this.f3605g = new HashMap();
        new C0350mt();
        this.f3606h = new String[length];
        for (int i = 0; i < length; i++) {
            String lowerCase = strArr[i].toLowerCase(Locale.US);
            this.f3605g.put(lowerCase, Integer.valueOf(i));
            String str = (String) map.get(strArr[i]);
            if (str != null) {
                this.f3606h[i] = str.toLowerCase(Locale.US);
            } else {
                this.f3606h[i] = lowerCase;
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            String lowerCase2 = ((String) entry.getValue()).toLowerCase(Locale.US);
            if (this.f3605g.containsKey(lowerCase2)) {
                String lowerCase3 = ((String) entry.getKey()).toLowerCase(Locale.US);
                HashMap hashMap = this.f3605g;
                hashMap.put(lowerCase3, hashMap.get(lowerCase2));
            }
        }
    }

    /* renamed from: a */
    public final void mo2761a(C0028az azVar) {
        if (!azVar.mo1737d()) {
            try {
                Lock a = this.f3599a.mo2842a();
                a.lock();
                try {
                    synchronized (this.f3607i) {
                    }
                    a.unlock();
                } catch (Throwable th) {
                    a.unlock();
                    throw th;
                }
            } catch (SQLiteException | IllegalStateException e) {
                Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e);
            }
        }
    }
}
