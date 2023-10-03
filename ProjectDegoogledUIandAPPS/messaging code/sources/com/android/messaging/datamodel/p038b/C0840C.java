package com.android.messaging.datamodel.p038b;

import android.util.Log;
import com.android.messaging.C0967f;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.b.C */
public class C0840C {

    /* renamed from: OC */
    private static final Executor f1092OC = Executors.newFixedThreadPool(10);
    /* access modifiers changed from: private */

    /* renamed from: PC */
    public static final Executor f1093PC = Executors.newSingleThreadExecutor(new C0885y());

    /* access modifiers changed from: private */
    /* renamed from: e */
    public C0838A m1505e(C0883w wVar) {
        C0846I i;
        C0846I i2;
        C0882v fa;
        ArrayList arrayList = new ArrayList();
        if (wVar.getRequestType() != 3 || (fa = wVar.mo6121fa()) == null || (i = fa.mo6163u(wVar.getKey())) == null) {
            i = null;
        }
        if (i == null) {
            i2 = wVar.mo6120a(arrayList);
            C1424b.m3594t(i2);
            i2.mo6100Oh();
            if (i2.mo6104Sh()) {
                addResourceToMemoryCache(wVar, i2);
            }
        } else if (i.mo6105Th()) {
            C0883w c = i.mo6107c(wVar);
            C1424b.m3594t(c);
            i.release();
            i2 = c.mo6120a(arrayList);
            C1424b.m3594t(i2);
            i2.mo6100Oh();
            if (i2.mo6104Sh()) {
                addResourceToMemoryCache(c, i2);
            }
        } else {
            i2 = i;
        }
        return new C0838A(this, i2, i != null, arrayList);
    }

    public static C0840C get() {
        return C0967f.get().mo6649Ld();
    }

    /* access modifiers changed from: package-private */
    public void addResourceToMemoryCache(C0883w wVar, C0846I i) {
        C1424b.m3592ia(i != null);
        C0882v fa = wVar.mo6121fa();
        if (fa != null) {
            fa.mo6095a(wVar.getKey(), i);
            if (Log.isLoggable("MessagingApp", 2)) {
                StringBuilder Pa = C0632a.m1011Pa("added media resource to ");
                Pa.append(fa.getName());
                Pa.append(". key=");
                Pa.append(C1430e.m3633xa(wVar.getKey()));
                C1430e.m3628v("MessagingApp", Pa.toString());
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0049  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.messaging.datamodel.p038b.C0846I mo6082b(com.android.messaging.datamodel.p038b.C0883w r6) {
        /*
            r5 = this;
            com.android.messaging.util.C1424b.m3584Gj()
            r0 = 0
            com.android.messaging.datamodel.b.A r5 = r5.m1505e(r6)     // Catch:{ Exception -> 0x0025, all -> 0x0022 }
            com.android.messaging.datamodel.b.I r1 = r5.f1089KC     // Catch:{ Exception -> 0x0020 }
            int r1 = r1.getRefCount()     // Catch:{ Exception -> 0x0020 }
            if (r1 <= 0) goto L_0x0012
            r1 = 1
            goto L_0x0013
        L_0x0012:
            r1 = 0
        L_0x0013:
            com.android.messaging.util.C1424b.m3592ia(r1)     // Catch:{ Exception -> 0x0020 }
            com.android.messaging.datamodel.b.I r6 = r5.f1089KC     // Catch:{ Exception -> 0x0020 }
            if (r5 == 0) goto L_0x001d
            r5.mo6077Nh()
        L_0x001d:
            return r6
        L_0x001e:
            r6 = move-exception
            goto L_0x0047
        L_0x0020:
            r1 = move-exception
            goto L_0x0027
        L_0x0022:
            r6 = move-exception
            r5 = r0
            goto L_0x0047
        L_0x0025:
            r1 = move-exception
            r5 = r0
        L_0x0027:
            java.lang.String r2 = "MessagingApp"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x001e }
            r3.<init>()     // Catch:{ all -> 0x001e }
            java.lang.String r4 = "Synchronous media loading failed, key="
            r3.append(r4)     // Catch:{ all -> 0x001e }
            java.lang.String r6 = r6.getKey()     // Catch:{ all -> 0x001e }
            r3.append(r6)     // Catch:{ all -> 0x001e }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x001e }
            com.android.messaging.util.C1430e.m3623e(r2, r6, r1)     // Catch:{ all -> 0x001e }
            if (r5 == 0) goto L_0x0046
            r5.mo6077Nh()
        L_0x0046:
            return r0
        L_0x0047:
            if (r5 == 0) goto L_0x004c
            r5.mo6077Nh()
        L_0x004c:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.p038b.C0840C.mo6082b(com.android.messaging.datamodel.b.w):com.android.messaging.datamodel.b.I");
    }

    /* renamed from: a */
    public void mo6080a(C0883w wVar) {
        m1503a(wVar, f1092OC);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1503a(C0883w wVar, Executor executor) {
        C0865e eVar = wVar instanceof C0865e ? (C0865e) wVar : null;
        if (eVar == null || eVar.isBound()) {
            new C0886z(this, eVar, wVar).executeOnExecutor(executor, new Void[]{null});
        }
    }
}
