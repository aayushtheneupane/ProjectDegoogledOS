package p000;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* renamed from: ibd */
/* compiled from: PG */
public abstract class ibd extends idp implements Runnable {

    /* renamed from: a */
    private ieh f13826a;

    /* renamed from: f */
    private Class f13827f;

    /* renamed from: g */
    private Object f13828g;

    public ibd(ieh ieh, Class cls, Object obj) {
        this.f13826a = (ieh) ife.m12898e((Object) ieh);
        this.f13827f = (Class) ife.m12898e((Object) cls);
        this.f13828g = ife.m12898e(obj);
    }

    /* renamed from: a */
    public abstract Object mo8333a(Object obj, Throwable th);

    /* renamed from: a */
    public abstract void mo8334a(Object obj);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo6947b() {
        mo8345a((Future) this.f13826a);
        this.f13826a = null;
        this.f13827f = null;
        this.f13828g = null;
    }

    /* renamed from: a */
    public static ieh m12603a(ieh ieh, Class cls, hpr hpr, Executor executor) {
        ibc ibc = new ibc(ieh, cls, hpr);
        ieh.mo53a(ibc, ife.m12838a(executor, (ibr) ibc));
        return ibc;
    }

    /* renamed from: a */
    public static ieh m12604a(ieh ieh, Class cls, icf icf, Executor executor) {
        ibb ibb = new ibb(ieh, cls, icf);
        ieh.mo53a(ibb, ife.m12838a(executor, (ibr) ibb));
        return ibb;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo6386a() {
        String str;
        ieh ieh = this.f13826a;
        Class cls = this.f13827f;
        Object obj = this.f13828g;
        String a = super.mo6386a();
        if (ieh != null) {
            String valueOf = String.valueOf(ieh);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 16);
            sb.append("inputFuture=[");
            sb.append(valueOf);
            sb.append("], ");
            str = sb.toString();
        } else {
            str = "";
        }
        if (cls != null && obj != null) {
            String valueOf2 = String.valueOf(cls);
            String valueOf3 = String.valueOf(obj);
            StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 29 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
            sb2.append(str);
            sb2.append("exceptionType=[");
            sb2.append(valueOf2);
            sb2.append("], fallback=[");
            sb2.append(valueOf3);
            sb2.append("]");
            return sb2.toString();
        } else if (a == null) {
            return null;
        } else {
            String valueOf4 = String.valueOf(str);
            return a.length() == 0 ? new String(valueOf4) : valueOf4.concat(a);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00b8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r10 = this;
            ieh r0 = r10.f13826a
            java.lang.Class r1 = r10.f13827f
            java.lang.Object r2 = r10.f13828g
            r3 = 1
            r4 = 0
            if (r0 != 0) goto L_0x000c
            r5 = 1
            goto L_0x000d
        L_0x000c:
            r5 = 0
        L_0x000d:
            if (r1 != 0) goto L_0x0011
            r6 = 1
            goto L_0x0012
        L_0x0011:
            r6 = 0
        L_0x0012:
            r5 = r5 | r6
            if (r2 != 0) goto L_0x0016
            goto L_0x0017
        L_0x0016:
            r3 = 0
        L_0x0017:
            r3 = r3 | r5
            if (r3 != 0) goto L_0x00bb
            boolean r3 = r10.isCancelled()
            if (r3 != 0) goto L_0x00bb
            r3 = 0
            r10.f13826a = r3
            boolean r4 = r0 instanceof p000.iff     // Catch:{ ExecutionException -> 0x0040, all -> 0x003b }
            if (r4 == 0) goto L_0x002f
            r4 = r0
            iff r4 = (p000.iff) r4     // Catch:{ ExecutionException -> 0x0040, all -> 0x003b }
            java.lang.Throwable r4 = r4.mo8349e()     // Catch:{ ExecutionException -> 0x0040, all -> 0x003b }
            goto L_0x0031
        L_0x002f:
            r4 = r3
        L_0x0031:
            if (r4 != 0) goto L_0x0038
            java.lang.Object r5 = p000.ife.m12871b((java.util.concurrent.Future) r0)     // Catch:{ ExecutionException -> 0x0040, all -> 0x003b }
            goto L_0x0091
        L_0x0038:
            r5 = r3
            goto L_0x0091
        L_0x003b:
            r4 = move-exception
        L_0x003c:
            r5 = r3
            goto L_0x0091
        L_0x0040:
            r4 = move-exception
            java.lang.Throwable r5 = r4.getCause()
            if (r5 != 0) goto L_0x008f
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.Class r6 = r0.getClass()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.String r7 = java.lang.String.valueOf(r6)
            int r7 = r7.length()
            java.lang.String r8 = java.lang.String.valueOf(r4)
            int r8 = r8.length()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            int r7 = r7 + 35
            int r7 = r7 + r8
            r9.<init>(r7)
            java.lang.String r7 = "Future type "
            r9.append(r7)
            r9.append(r6)
            java.lang.String r6 = " threw "
            r9.append(r6)
            r9.append(r4)
            java.lang.String r4 = " without a cause"
            r9.append(r4)
            java.lang.String r4 = r9.toString()
            r5.<init>(r4)
            r4 = r5
            goto L_0x003c
        L_0x008f:
            r4 = r5
            goto L_0x003c
        L_0x0091:
            if (r4 == 0) goto L_0x00b8
            boolean r1 = r1.isInstance(r4)
            if (r1 == 0) goto L_0x00b4
            java.lang.Object r0 = r10.mo8333a(r2, r4)     // Catch:{ all -> 0x00a5 }
            r10.f13827f = r3
            r10.f13828g = r3
            r10.mo8334a(r0)
            return
        L_0x00a5:
            r0 = move-exception
            r10.mo6952a((java.lang.Throwable) r0)     // Catch:{ all -> 0x00ae }
            r10.f13827f = r3
            r10.f13828g = r3
            return
        L_0x00ae:
            r0 = move-exception
            r10.f13827f = r3
            r10.f13828g = r3
            throw r0
        L_0x00b4:
            r10.mo6946a((p000.ieh) r0)
            return
        L_0x00b8:
            r10.mo8346b((java.lang.Object) r5)
        L_0x00bb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ibd.run():void");
    }
}
