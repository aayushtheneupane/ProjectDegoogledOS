package com.android.messaging.datamodel.action;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1480va;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import p000a.p005b.C0027n;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.action.c */
public class C0813c {
    static C0027n sActionMonitors = new C0027n();
    /* access modifiers changed from: private */

    /* renamed from: Uy */
    public C0812b f1083Uy;

    /* renamed from: Vy */
    private final String f1084Vy;
    /* access modifiers changed from: private */
    public final Object mData;
    private final Handler mHandler = C1480va.getMainThreadHandler();
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    protected int mState;

    protected C0813c(int i, String str, Object obj) {
        this.f1084Vy = str;
        this.mState = i;
        this.mData = obj;
    }

    /* renamed from: eb */
    private static C0813c m1481eb(String str) {
        C0813c cVar;
        synchronized (sActionMonitors) {
            cVar = (C0813c) sActionMonitors.get(str);
        }
        return cVar;
    }

    static void unregisterActionMonitor(String str, C0813c cVar) {
        if (cVar != null) {
            synchronized (sActionMonitors) {
                sActionMonitors.remove(str);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: Me */
    public final void mo6032Me() {
        synchronized (this.mLock) {
            this.f1083Uy = null;
        }
    }

    /* renamed from: Ne */
    public String mo6033Ne() {
        return this.f1084Vy;
    }

    public boolean isComplete() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mState == 8;
        }
        return z;
    }

    public void unregister() {
        mo6032Me();
    }

    /* access modifiers changed from: protected */
    public void updateState(Action action, int i, int i2) {
        synchronized (this.mLock) {
            if (i != 0) {
                if (this.mState != i) {
                    throw new IllegalStateException("On updateState to " + i2 + " was " + this.mState + " expecting " + i);
                }
            }
            if (i2 != this.mState) {
                this.mState = i2;
            }
        }
    }

    /* renamed from: b */
    private final void m1476b(Action action, int i, Object obj, boolean z) {
        C0812b bVar;
        synchronized (this.mLock) {
            m1472a(action, i, 8);
            bVar = this.f1083Uy;
        }
        if (bVar != null) {
            this.mHandler.post(new C0811a(this, z, action, obj));
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo6035a(C0812b bVar) {
        synchronized (this.mLock) {
            this.f1083Uy = bVar;
        }
    }

    /* renamed from: a */
    static void m1472a(Action action, int i, int i2) {
        C0813c eb = m1481eb(action.f1056Ny);
        if (eb != null) {
            int i3 = eb.mState;
            eb.updateState(action, i, i2);
            i2 = eb.mState;
            i = i3;
        }
        if (Log.isLoggable("MessagingAppDataModel", 2)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            C1430e.m3628v("MessagingAppDataModel", "Operation-" + action.f1056Ny + ": @" + simpleDateFormat.format(new Date()) + "UTC State = " + i + " - " + i2);
        }
    }

    /* renamed from: b */
    static void m1477b(Action action, int i, boolean z, Object obj) {
        C0813c eb = m1481eb(action.f1056Ny);
        if (eb != null) {
            int i2 = eb.mState;
            eb.mo6034a(action, i, z, obj);
            i = i2;
        }
        if (Log.isLoggable("MessagingAppDataModel", 2)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            C1430e.m3628v("MessagingAppDataModel", "Operation-" + action.f1056Ny + ": @" + simpleDateFormat.format(new Date()) + "UTC State = " + i + " - EXECUTED");
        }
    }

    /* renamed from: a */
    static void m1473a(Action action, int i, Object obj, boolean z) {
        C0813c eb = m1481eb(action.f1056Ny);
        if (eb != null) {
            int i2 = eb.mState;
            eb.m1476b(action, i, obj, z);
            m1478b(action.f1056Ny, eb);
            i = i2;
        }
        if (Log.isLoggable("MessagingAppDataModel", 2)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            C1430e.m3628v("MessagingAppDataModel", "Operation-" + action.f1056Ny + ": @" + simpleDateFormat.format(new Date()) + "UTC State = " + i + " - " + 8);
        }
    }

    /* renamed from: b */
    static void m1478b(String str, C0813c cVar) {
        if (cVar != null && cVar.isComplete()) {
            synchronized (sActionMonitors) {
                sActionMonitors.remove(str);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo6034a(Action action, int i, boolean z, Object obj) {
        synchronized (this.mLock) {
            if (z) {
                try {
                    m1472a(action, i, 4);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    /* renamed from: a */
    static void m1474a(String str, C0813c cVar) {
        if (cVar == null || (!TextUtils.isEmpty(cVar.mo6033Ne()) && !TextUtils.isEmpty(str) && str.equals(cVar.mo6033Ne()))) {
            synchronized (sActionMonitors) {
                sActionMonitors.put(str, cVar);
            }
            return;
        }
        StringBuilder Pa = C0632a.m1011Pa("Monitor key ");
        Pa.append(cVar.mo6033Ne());
        Pa.append(" not compatible with action key ");
        Pa.append(str);
        throw new IllegalArgumentException(Pa.toString());
    }
}
