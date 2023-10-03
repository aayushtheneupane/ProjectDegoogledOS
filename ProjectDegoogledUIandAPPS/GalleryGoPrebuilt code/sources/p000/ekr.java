package p000;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.util.Collections;
import java.util.Set;

/* renamed from: ekr */
/* compiled from: PG */
public class ekr {

    /* renamed from: a */
    public final Context f8484a;

    /* renamed from: b */
    public final ekn f8485b;

    /* renamed from: c */
    public final eln f8486c;

    /* renamed from: d */
    public final Looper f8487d;

    /* renamed from: e */
    public final int f8488e;

    /* renamed from: f */
    public final ekv f8489f;

    /* renamed from: g */
    private final enp f8490g;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: eon} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: eon} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: eny} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: eon} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: eon} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: eon} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: eon} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: eon} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v21, resolved type: eny} */
    /* JADX WARNING: type inference failed for: r1v14, types: [eny, android.app.Fragment] */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x007f, code lost:
        if (r2 != false) goto L_0x0081;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x006c, code lost:
        if (r1 == null) goto L_0x006e;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ekr(android.app.Activity r4, p000.ekn r5, p000.ekq r6) {
        /*
            r3 = this;
            java.lang.String r0 = "LifecycleFragmentImpl"
            java.lang.String r1 = "SupportLifecycleFragmentImpl"
            r3.<init>()
            java.lang.String r2 = "Null activity is not permitted."
            p000.abj.m86a((java.lang.Object) r4, (java.lang.Object) r2)
            java.lang.String r2 = "Api must not be null."
            p000.abj.m86a((java.lang.Object) r5, (java.lang.Object) r2)
            java.lang.String r2 = "Settings must not be null; use Settings.DEFAULT_SETTINGS instead."
            p000.abj.m86a((java.lang.Object) r6, (java.lang.Object) r2)
            android.content.Context r2 = r4.getApplicationContext()
            r3.f8484a = r2
            r3.f8485b = r5
            android.os.Looper r5 = r6.f8483c
            r3.f8487d = r5
            ekn r5 = r3.f8485b
            eln r5 = p000.eln.m7737a(r5)
            r3.f8486c = r5
            enq r5 = new enq
            r5.<init>(r3)
            r3.f8489f = r5
            android.content.Context r5 = r3.f8484a
            enp r5 = p000.enp.m7890a((android.content.Context) r5)
            r3.f8490g = r5
            int r5 = r5.mo5058a()
            r3.f8488e = r5
            eok r5 = r6.f8482b
            boolean r5 = r4 instanceof com.google.android.gms.common.api.GoogleApiActivity
            if (r5 == 0) goto L_0x0047
            goto L_0x011b
        L_0x0047:
            enp r5 = r3.f8490g
            eln r6 = r3.f8486c
            env r2 = new env
            r2.<init>(r4)
            java.lang.Object r4 = r2.f8691a
            boolean r2 = r4 instanceof p000.C0149fj
            if (r2 != 0) goto L_0x00b1
            boolean r1 = r4 instanceof android.app.Activity
            if (r1 == 0) goto L_0x00a9
            android.app.Activity r4 = (android.app.Activity) r4
            java.util.WeakHashMap r1 = p000.eny.f8695a
            java.lang.Object r1 = r1.get(r4)
            java.lang.ref.WeakReference r1 = (java.lang.ref.WeakReference) r1
            if (r1 == 0) goto L_0x006e
            java.lang.Object r1 = r1.get()
            eny r1 = (p000.eny) r1
            if (r1 != 0) goto L_0x00f9
        L_0x006e:
            android.app.FragmentManager r1 = r4.getFragmentManager()     // Catch:{ ClassCastException -> 0x00a0 }
            android.app.Fragment r1 = r1.findFragmentByTag(r0)     // Catch:{ ClassCastException -> 0x00a0 }
            eny r1 = (p000.eny) r1     // Catch:{ ClassCastException -> 0x00a0 }
            if (r1 != 0) goto L_0x007b
            goto L_0x0081
        L_0x007b:
            boolean r2 = r1.isRemoving()
            if (r2 == 0) goto L_0x0095
        L_0x0081:
            eny r1 = new eny
            r1.<init>()
            android.app.FragmentManager r2 = r4.getFragmentManager()
            android.app.FragmentTransaction r2 = r2.beginTransaction()
            android.app.FragmentTransaction r0 = r2.add(r1, r0)
            r0.commitAllowingStateLoss()
        L_0x0095:
            java.util.WeakHashMap r0 = p000.eny.f8695a
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference
            r2.<init>(r1)
            r0.put(r4, r2)
            goto L_0x00f9
        L_0x00a0:
            r4 = move-exception
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl"
            r5.<init>(r6, r4)
            throw r5
        L_0x00a9:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "Can't get fragment for unexpected activity."
            r4.<init>(r5)
            throw r4
        L_0x00b1:
            fj r4 = (p000.C0149fj) r4
            java.util.WeakHashMap r0 = p000.eon.f8717a
            java.lang.Object r0 = r0.get(r4)
            java.lang.ref.WeakReference r0 = (java.lang.ref.WeakReference) r0
            if (r0 == 0) goto L_0x00c8
            java.lang.Object r0 = r0.get()
            eon r0 = (p000.eon) r0
            if (r0 != 0) goto L_0x00c6
            goto L_0x00c8
        L_0x00c6:
            r1 = r0
            goto L_0x00f9
        L_0x00c8:
            gd r0 = r4.mo5851d()     // Catch:{ ClassCastException -> 0x0121 }
            fh r0 = r0.mo6418a((java.lang.String) r1)     // Catch:{ ClassCastException -> 0x0121 }
            eon r0 = (p000.eon) r0     // Catch:{ ClassCastException -> 0x0121 }
            if (r0 != 0) goto L_0x00d5
            goto L_0x00d9
        L_0x00d5:
            boolean r2 = r0.f9598q
            if (r2 == 0) goto L_0x00ee
        L_0x00d9:
            eon r0 = new eon
            r0.<init>()
            gd r2 = r4.mo5851d()
            gn r2 = r2.mo6419a()
            r2.mo6851a((p000.C0147fh) r0, (java.lang.String) r1)
            r2.mo5252c()
            r1 = r0
            goto L_0x00ef
        L_0x00ee:
            r1 = r0
        L_0x00ef:
            java.util.WeakHashMap r0 = p000.eon.f8717a
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference
            r2.<init>(r1)
            r0.put(r4, r2)
        L_0x00f9:
            java.lang.Class<emh> r4 = p000.emh.class
            java.lang.String r0 = "ConnectionlessLifecycleHelper"
            com.google.android.gms.common.api.internal.LifecycleCallback r4 = r1.mo5068a((java.lang.String) r0, (java.lang.Class) r4)
            emh r4 = (p000.emh) r4
            if (r4 != 0) goto L_0x010b
            emh r4 = new emh
            r4.<init>(r1)
            goto L_0x010c
        L_0x010b:
        L_0x010c:
            r4.f8555f = r5
            java.lang.String r0 = "ApiKey cannot be null"
            p000.abj.m86a((java.lang.Object) r6, (java.lang.Object) r0)
            kp r0 = r4.f8554e
            r0.add(r6)
            r5.mo5060a((p000.emh) r4)
        L_0x011b:
            enp r4 = r3.f8490g
            r4.mo5059a((p000.ekr) r3)
            return
        L_0x0121:
            r4 = move-exception
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl"
            r5.<init>(r6, r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ekr.<init>(android.app.Activity, ekn, ekq):void");
    }

    public ekr(Context context, ekn ekn, ekq ekq) {
        abj.m86a((Object) context, (Object) "Null context is not permitted.");
        abj.m86a((Object) ekn, (Object) "Api must not be null.");
        abj.m86a((Object) ekq, (Object) "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.f8484a = context.getApplicationContext();
        this.f8485b = ekn;
        this.f8487d = ekq.f8483c;
        this.f8486c = eln.m7737a(this.f8485b);
        this.f8489f = new enq(this);
        enp a = enp.m7890a(this.f8484a);
        this.f8490g = a;
        this.f8488e = a.mo5058a();
        eok eok = ekq.f8482b;
        this.f8490g.mo5059a(this);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ekr(android.content.Context r3, p000.ekn r4, p000.eok r5) {
        /*
            r2 = this;
            ekp r0 = new ekp
            r0.<init>()
            java.lang.String r1 = "StatusExceptionMapper must not be null."
            p000.abj.m86a((java.lang.Object) r5, (java.lang.Object) r1)
            r0.f8479a = r5
            ekq r5 = r0.mo4942a()
            r2.<init>((android.content.Context) r3, (p000.ekn) r4, (p000.ekq) r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ekr.<init>(android.content.Context, ekn, eok):void");
    }

    /* renamed from: a */
    public final epj mo4944a() {
        epj epj = new epj();
        Set emptySet = Collections.emptySet();
        if (epj.f8772a == null) {
            epj.f8772a = new C0292kp();
        }
        epj.f8772a.addAll(emptySet);
        epj.f8774c = this.f8484a.getClass().getName();
        epj.f8773b = this.f8484a.getPackageName();
        return epj;
    }

    /* renamed from: a */
    public final elq mo4943a(elq elq) {
        elq.mo3512c();
        enp enp = this.f8490g;
        eli eli = new eli(elq);
        Handler handler = enp.f8684m;
        handler.sendMessage(handler.obtainMessage(4, new eoc(eli, enp.f8680i.get(), this)));
        return elq;
    }

    /* renamed from: a */
    private final exb m7693a(int i, eoq eoq) {
        exe exe = new exe();
        enp enp = this.f8490g;
        elj elj = new elj(eoq, exe);
        Handler handler = enp.f8684m;
        handler.sendMessage(handler.obtainMessage(4, new eoc(elj, enp.f8680i.get(), this)));
        return exe.f9167a;
    }

    /* renamed from: a */
    public final exb mo4945a(eoq eoq) {
        return m7693a(0, eoq);
    }

    /* renamed from: b */
    public final void mo4946b(eoq eoq) {
        m7693a(1, eoq);
    }

    public ekr(Context context) {
        this(context, esd.f8901b, ekq.f8481a);
        exl.m8321a(context.getApplicationContext());
    }

    public ekr(Context context, byte[] bArr) {
        this(context, evb.f9083a, ekq.f8481a);
    }
}
