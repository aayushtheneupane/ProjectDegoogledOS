package p000;

import android.content.res.Configuration;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: gd */
/* compiled from: PG */
public final class C0171gd {

    /* renamed from: A */
    private ArrayList f10980A;

    /* renamed from: B */
    private final Runnable f10981B = new C0165fz(this);

    /* renamed from: a */
    public final C0179gl f10982a = new C0179gl();

    /* renamed from: b */
    public ArrayList f10983b;

    /* renamed from: c */
    public final C0161fv f10984c = new C0161fv(this);

    /* renamed from: d */
    public aag f10985d;

    /* renamed from: e */
    public final aad f10986e = new aad(this);

    /* renamed from: f */
    public final AtomicInteger f10987f = new AtomicInteger();

    /* renamed from: g */
    public final ConcurrentHashMap f10988g = new ConcurrentHashMap();

    /* renamed from: h */
    public final C0162fw f10989h = new C0162fw(this);

    /* renamed from: i */
    public int f10990i = -1;

    /* renamed from: j */
    public C0160fu f10991j;

    /* renamed from: k */
    public C0156fq f10992k;

    /* renamed from: l */
    public C0147fh f10993l;

    /* renamed from: m */
    public C0175gh f10994m;

    /* renamed from: n */
    private final ArrayList f10995n = new ArrayList();

    /* renamed from: o */
    private boolean f10996o;

    /* renamed from: p */
    private ArrayList f10997p;

    /* renamed from: q */
    private final C0189gu f10998q = new C0163fx(this);

    /* renamed from: r */
    private C0147fh f10999r;

    /* renamed from: s */
    private final C0159ft f11000s = new C0164fy(this);

    /* renamed from: t */
    private boolean f11001t;

    /* renamed from: u */
    private boolean f11002u;

    /* renamed from: v */
    private boolean f11003v;

    /* renamed from: w */
    private boolean f11004w;

    /* renamed from: x */
    private boolean f11005x;

    /* renamed from: y */
    private ArrayList f11006y;

    /* renamed from: z */
    private ArrayList f11007z;

    public C0171gd() {
    }

    /* renamed from: c */
    public final boolean mo6443c() {
        return this.f11002u || this.f11003v;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public final void mo6447e(C0147fh fhVar) {
        if (m10054a(2)) {
            "add: " + fhVar;
        }
        mo6445d(fhVar);
        if (!fhVar.f9566E) {
            this.f10982a.mo6822a(fhVar);
            fhVar.f9598q = false;
            if (fhVar.f9573L == null) {
                fhVar.f9578Q = false;
            }
            if (m10075r(fhVar)) {
                this.f11001t = true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo6422a(C0147fh fhVar) {
        if (!mo6443c()) {
            C0175gh ghVar = this.f10994m;
            if (!ghVar.f11331c.containsKey(fhVar.f9591j)) {
                ghVar.f11331c.put(fhVar.f9591j, fhVar);
                if (m10054a(2)) {
                    "Updating retained Fragments: Added " + fhVar;
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [ax, fu] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo6424a(p000.C0160fu r5, p000.C0156fq r6, p000.C0147fh r7) {
        /*
            r4 = this;
            fu r0 = r4.f10991j
            if (r0 != 0) goto L_0x0077
            r4.f10991j = r5
            r4.f10992k = r6
            r4.f10993l = r7
            if (r7 == 0) goto L_0x000f
            r4.m10066n()
        L_0x000f:
            boolean r6 = r5 instanceof p000.aah
            if (r6 != 0) goto L_0x0014
            goto L_0x003a
        L_0x0014:
            r6 = r5
            fi r6 = (p000.C0148fi) r6
            fj r6 = r6.f9696a
            aag r6 = r6.f7d
            r4.f10985d = r6
            if (r7 != 0) goto L_0x0021
            r6 = r5
            goto L_0x0022
        L_0x0021:
            r6 = r7
        L_0x0022:
            aag r0 = r4.f10985d
            aad r1 = r4.f10986e
            w r6 = r6.mo5ad()
            v r2 = r6.mo61a()
            v r3 = p000.C0573v.DESTROYED
            if (r2 == r3) goto L_0x003a
            aae r2 = new aae
            r2.<init>(r0, r6, r1)
            r1.mo12a(r2)
        L_0x003a:
            if (r7 == 0) goto L_0x005f
            gd r5 = r7.f9604w
            gh r5 = r5.f10994m
            java.util.HashMap r6 = r5.f11332d
            java.lang.String r0 = r7.f9591j
            java.lang.Object r6 = r6.get(r0)
            gh r6 = (p000.C0175gh) r6
            if (r6 != 0) goto L_0x005b
            gh r6 = new gh
            boolean r0 = r5.f11334f
            r6.<init>(r0)
            java.util.HashMap r5 = r5.f11332d
            java.lang.String r7 = r7.f9591j
            r5.put(r7, r6)
            goto L_0x005c
        L_0x005b:
        L_0x005c:
            r4.f10994m = r6
            return
        L_0x005f:
            boolean r6 = r5 instanceof p000.C0026ax
            if (r6 == 0) goto L_0x006e
            aw r5 = r5.mo4aa()
            gh r5 = p000.C0175gh.m10324a((p000.C0025aw) r5)
            r4.f10994m = r5
            return
        L_0x006e:
            gh r5 = new gh
            r6 = 0
            r5.<init>(r6)
            r4.f10994m = r5
            return
        L_0x0077:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "Already attached"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0171gd.mo6424a(fu, fq, fh):void");
    }

    /* renamed from: m */
    private final void m10065m(C0147fh fhVar) {
        if (m10054a(2)) {
            "attach: " + fhVar;
        }
        if (fhVar.f9566E) {
            fhVar.f9566E = false;
            if (!fhVar.f9597p) {
                this.f10982a.mo6822a(fhVar);
                if (m10054a(2)) {
                    "add from attach: " + fhVar;
                }
                if (m10075r(fhVar)) {
                    this.f11001t = true;
                }
            }
        }
    }

    /* renamed from: a */
    public final C0182gn mo6419a() {
        return new C0133eu(this);
    }

    /* renamed from: h */
    private final void m10060h(C0147fh fhVar) {
        HashSet hashSet = (HashSet) this.f10988g.get(fhVar);
        if (hashSet != null) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                C0259jj jjVar = (C0259jj) it.next();
                synchronized (jjVar) {
                    if (!jjVar.f15079a) {
                        jjVar.f15079a = true;
                        jjVar.f15081c = true;
                        C0258ji jiVar = jjVar.f15080b;
                        if (jiVar != null) {
                            try {
                                jiVar.mo279a();
                            } catch (Throwable th) {
                                synchronized (jjVar) {
                                    jjVar.f15081c = false;
                                    jjVar.notifyAll();
                                    throw th;
                                }
                            }
                        }
                        synchronized (jjVar) {
                            jjVar.f15081c = false;
                            jjVar.notifyAll();
                        }
                    }
                }
            }
            hashSet.clear();
            mo6435b(fhVar);
            this.f10988g.remove(fhVar);
        }
    }

    /* renamed from: p */
    private final void m10070p() {
        if (mo6443c()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    /* renamed from: q */
    private final void m10072q() {
        this.f10996o = false;
        this.f11007z.clear();
        this.f11006y.clear();
    }

    /* renamed from: b */
    public final void mo6435b(C0147fh fhVar) {
        fhVar.f9606y.m10056c(1);
        if (fhVar.f9573L != null) {
            fhVar.f9584W.mo7435a(C0546u.ON_DESTROY);
        }
        fhVar.f9587f = 1;
        fhVar.f9571J = false;
        fhVar.mo212f();
        if (fhVar.f9571J) {
            C0209hn hnVar = ((C0210ho) C0205hj.m11569a(fhVar)).f13136b;
            int b = hnVar.f13070c.mo9338b();
            for (int i = 0; i < b; i++) {
                ((C0206hk) hnVar.f13070c.mo9342d(i)).mo7502c();
            }
            fhVar.f9602u = false;
            this.f10989h.mo6240e(fhVar, false);
            fhVar.f9572K = null;
            fhVar.f9573L = null;
            fhVar.f9584W = null;
            fhVar.f9585X.mo512a((Object) null);
            fhVar.f9600s = false;
            return;
        }
        throw new C0214hs("Fragment " + fhVar + " did not call through to super.onDestroyView()");
    }

    /* renamed from: l */
    private final void m10064l(C0147fh fhVar) {
        if (m10054a(2)) {
            "detach: " + fhVar;
        }
        if (!fhVar.f9566E) {
            fhVar.f9566E = true;
            if (fhVar.f9597p) {
                if (m10054a(2)) {
                    "remove from detach: " + fhVar;
                }
                this.f10982a.mo6827b(fhVar);
                if (m10075r(fhVar)) {
                    this.f11001t = true;
                }
                m10067n(fhVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public final void mo6448f() {
        this.f11002u = false;
        this.f11003v = false;
        m10056c(2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo6420a(Configuration configuration) {
        for (C0147fh fhVar : this.f10982a.mo6826b()) {
            if (fhVar != null) {
                fhVar.onConfigurationChanged(configuration);
                fhVar.f9606y.mo6420a(configuration);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final boolean mo6439b(MenuItem menuItem) {
        if (this.f10990i > 0) {
            for (C0147fh fhVar : this.f10982a.mo6826b()) {
                if (fhVar != null && !fhVar.f9565D) {
                    if (fhVar.mo5633N() || fhVar.f9606y.mo6439b(menuItem)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public final void mo6446e() {
        this.f11002u = false;
        this.f11003v = false;
        m10056c(1);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo6429a(Menu menu, MenuInflater menuInflater) {
        boolean z;
        if (this.f10990i <= 0) {
            return false;
        }
        ArrayList arrayList = null;
        boolean z2 = false;
        for (C0147fh fhVar : this.f10982a.mo6826b()) {
            if (fhVar != null && !fhVar.f9565D) {
                if (!fhVar.f9569H || !fhVar.f9570I) {
                    z = false;
                } else {
                    fhVar.mo2758a(menu, menuInflater);
                    z = true;
                }
                if (z || fhVar.f9606y.mo6429a(menu, menuInflater)) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(fhVar);
                    z2 = true;
                }
            }
        }
        if (this.f10997p != null) {
            for (int i = 0; i < this.f10997p.size(); i++) {
                C0147fh fhVar2 = (C0147fh) this.f10997p.get(i);
                if (arrayList != null) {
                    arrayList.contains(fhVar2);
                }
            }
        }
        this.f10997p = arrayList;
        return z2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: k */
    public final void mo6453k() {
        this.f11004w = true;
        mo6442c(true);
        m10074r();
        m10056c(-1);
        this.f10991j = null;
        this.f10992k = null;
        this.f10993l = null;
        if (this.f10985d != null) {
            Iterator it = this.f10986e.f9b.iterator();
            while (it.hasNext()) {
                ((C0705zx) it.next()).mo14a();
            }
            this.f10985d = null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: l */
    public final void mo6454l() {
        for (C0147fh fhVar : this.f10982a.mo6826b()) {
            if (fhVar != null) {
                fhVar.onLowMemory();
                fhVar.f9606y.mo6454l();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo6427a(boolean z) {
        for (C0147fh fhVar : this.f10982a.mo6826b()) {
            if (fhVar != null) {
                fhVar.f9606y.mo6427a(z);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo6430a(MenuItem menuItem) {
        if (this.f10990i > 0) {
            for (C0147fh fhVar : this.f10982a.mo6826b()) {
                if (fhVar != null && !fhVar.f9565D) {
                    if ((fhVar.f9569H && fhVar.f9570I && fhVar.mo2666a(menuItem)) || fhVar.f9606y.mo6430a(menuItem)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo6434b(Menu menu) {
        if (this.f10990i > 0) {
            for (C0147fh fhVar : this.f10982a.mo6826b()) {
                if (fhVar != null && !fhVar.f9565D) {
                    fhVar.f9606y.mo6434b(menu);
                }
            }
        }
    }

    /* renamed from: q */
    private final void m10073q(C0147fh fhVar) {
        if (fhVar != null && fhVar.equals(mo6440c(fhVar.f9591j))) {
            boolean f = fhVar.f9604w.m10058f(fhVar);
            Boolean bool = fhVar.f9596o;
            if (bool == null || bool.booleanValue() != f) {
                fhVar.f9596o = Boolean.valueOf(f);
                C0171gd gdVar = fhVar.f9606y;
                gdVar.m10066n();
                gdVar.m10073q(gdVar.f10999r);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: i */
    public final void mo6451i() {
        m10056c(3);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo6437b(boolean z) {
        for (C0147fh fhVar : this.f10982a.mo6826b()) {
            if (fhVar != null) {
                fhVar.f9606y.mo6437b(z);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo6428a(Menu menu) {
        boolean z;
        if (this.f10990i <= 0) {
            return false;
        }
        boolean z2 = false;
        for (C0147fh fhVar : this.f10982a.mo6826b()) {
            if (fhVar != null && !fhVar.f9565D) {
                if (!fhVar.f9569H || !fhVar.f9570I) {
                    z = false;
                } else {
                    fhVar.mo5634O();
                    z = true;
                }
                if (fhVar.f9606y.mo6428a(menu) || z) {
                    z2 = true;
                }
            }
        }
        return z2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public final void mo6450h() {
        this.f11002u = false;
        this.f11003v = false;
        m10056c(4);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public final void mo6449g() {
        this.f11002u = false;
        this.f11003v = false;
        m10056c(3);
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: c */
    private final void m10056c(int i) {
        try {
            this.f10996o = true;
            m10050a(i, false);
            this.f10996o = false;
            mo6442c(true);
        } catch (Throwable th) {
            this.f10996o = false;
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: j */
    public final void mo6452j() {
        this.f11003v = true;
        m10056c(2);
    }

    /* renamed from: s */
    private final void m10076s() {
        if (this.f11005x) {
            this.f11005x = false;
            m10069o();
        }
    }

    /* renamed from: a */
    public final void mo6426a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        String str2 = str + "    ";
        C0179gl glVar = this.f10982a;
        String str3 = str + "    ";
        if (!glVar.f11562b.isEmpty()) {
            printWriter.print(str);
            printWriter.print("Active Fragments:");
            for (C0178gk gkVar : glVar.f11562b.values()) {
                printWriter.print(str);
                if (gkVar != null) {
                    C0147fh fhVar = gkVar.f11534b;
                    printWriter.println(fhVar);
                    fhVar.mo5088a(str3, fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        int size3 = glVar.f11561a.size();
        if (size3 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i = 0; i < size3; i++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.println(((C0147fh) glVar.f11561a.get(i)).toString());
            }
        }
        ArrayList arrayList = this.f10997p;
        if (arrayList != null && (size2 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i2 = 0; i2 < size2; i2++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(((C0147fh) this.f10997p.get(i2)).toString());
            }
        }
        ArrayList arrayList2 = this.f10983b;
        if (arrayList2 != null && (size = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i3 = 0; i3 < size; i3++) {
                C0133eu euVar = (C0133eu) this.f10983b.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(euVar.toString());
                euVar.mo5247a(str2, printWriter);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.f10987f.get());
        synchronized (this.f10995n) {
            int size4 = this.f10995n.size();
            if (size4 > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                for (int i4 = 0; i4 < size4; i4++) {
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i4);
                    printWriter.print(": ");
                    printWriter.println((C0168gb) this.f10995n.get(i4));
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.f10991j);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.f10992k);
        if (this.f10993l != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.f10993l);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.f10990i);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.f11002u);
        printWriter.print(" mStopped=");
        printWriter.print(this.f11003v);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.f11004w);
        if (this.f11001t) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.f11001t);
        }
    }

    /* renamed from: r */
    private final void m10074r() {
        if (!this.f10988g.isEmpty()) {
            for (C0147fh fhVar : this.f10988g.keySet()) {
                m10060h(fhVar);
                mo6423a(fhVar, fhVar.mo5625F());
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo6425a(C0168gb gbVar, boolean z) {
        if (!z) {
            if (this.f10991j != null) {
                m10070p();
            } else if (!this.f11004w) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            } else {
                throw new IllegalStateException("FragmentManager has been destroyed");
            }
        }
        synchronized (this.f10995n) {
            if (this.f10991j != null) {
                this.f10995n.add(gbVar);
                synchronized (this.f10995n) {
                    if (this.f10995n.size() == 1) {
                        this.f10991j.f10594d.removeCallbacks(this.f10981B);
                        this.f10991j.f10594d.post(this.f10981B);
                        m10066n();
                    }
                }
            } else if (!z) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    /* renamed from: d */
    private final void m10057d(boolean z) {
        if (this.f10996o) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        } else if (this.f10991j != null) {
            if (Looper.myLooper() == this.f10991j.f10594d.getLooper()) {
                if (!z) {
                    m10070p();
                }
                if (this.f11006y == null) {
                    this.f11006y = new ArrayList();
                    this.f11007z = new ArrayList();
                }
                this.f10996o = false;
                return;
            }
            throw new IllegalStateException("Must be called from main thread of fragment host");
        } else if (this.f11004w) {
            throw new IllegalStateException("FragmentManager has been destroyed");
        } else {
            throw new IllegalStateException("FragmentManager has not been attached to a host.");
        }
    }

    /* renamed from: c */
    public final void mo6442c(boolean z) {
        m10057d(z);
        while (true) {
            ArrayList arrayList = this.f11006y;
            ArrayList arrayList2 = this.f11007z;
            synchronized (this.f10995n) {
                if (this.f10995n.isEmpty()) {
                    break;
                }
                int size = this.f10995n.size();
                boolean z2 = false;
                for (int i = 0; i < size; i++) {
                    z2 |= ((C0168gb) this.f10995n.get(i)).mo5249a(arrayList, arrayList2);
                }
                this.f10995n.clear();
                this.f10991j.f10594d.removeCallbacks(this.f10981B);
                if (!z2) {
                    break;
                }
                this.f10996o = true;
                try {
                    m10053a(this.f11006y, this.f11007z);
                } finally {
                    m10072q();
                }
            }
        }
        m10066n();
        m10076s();
        this.f10982a.mo6821a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo6436b(C0168gb gbVar, boolean z) {
        if (!z || (this.f10991j != null && !this.f11004w)) {
            m10057d(z);
            if (gbVar.mo5249a(this.f11006y, this.f11007z)) {
                this.f10996o = true;
                try {
                    m10053a(this.f11006y, this.f11007z);
                } finally {
                    m10072q();
                }
            }
            m10066n();
            m10076s();
            this.f10982a.mo6821a();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v55, resolved type: fh} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m10055b(java.util.ArrayList r20, java.util.ArrayList r21, int r22, int r23) {
        /*
            r19 = this;
            r7 = r19
            r8 = r20
            r9 = r21
            r10 = r22
            r11 = r23
            java.lang.Object r0 = r8.get(r10)
            eu r0 = (p000.C0133eu) r0
            boolean r12 = r0.f11659r
            java.util.ArrayList r0 = r7.f10980A
            if (r0 != 0) goto L_0x001e
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r7.f10980A = r0
            goto L_0x0021
        L_0x001e:
            r0.clear()
        L_0x0021:
            java.util.ArrayList r0 = r7.f10980A
            gl r1 = r7.f10982a
            java.util.List r1 = r1.mo6826b()
            r0.addAll(r1)
            fh r0 = r7.f10999r
            r1 = r10
            r2 = 0
        L_0x0030:
            r15 = 3
            r6 = -1
            r5 = 1
            if (r1 >= r11) goto L_0x017e
            java.lang.Object r3 = r8.get(r1)
            eu r3 = (p000.C0133eu) r3
            java.lang.Object r4 = r9.get(r1)
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L_0x007c
            java.util.ArrayList r4 = r7.f10980A
            java.util.ArrayList r13 = r3.f11644c
            int r13 = r13.size()
            int r13 = r13 + r6
        L_0x0050:
            if (r13 < 0) goto L_0x016b
            java.util.ArrayList r6 = r3.f11644c
            java.lang.Object r6 = r6.get(r13)
            gm r6 = (p000.C0180gm) r6
            int r14 = r6.f11606a
            if (r14 == r5) goto L_0x0074
            if (r14 == r15) goto L_0x006e
            switch(r14) {
                case 6: goto L_0x006e;
                case 7: goto L_0x0074;
                case 8: goto L_0x006c;
                case 9: goto L_0x0069;
                case 10: goto L_0x0064;
                default: goto L_0x0063;
            }
        L_0x0063:
            goto L_0x0079
        L_0x0064:
            v r14 = r6.f11612g
            r6.f11613h = r14
            goto L_0x0079
        L_0x0069:
            fh r0 = r6.f11607b
            goto L_0x0079
        L_0x006c:
            r0 = 0
            goto L_0x0079
        L_0x006e:
            fh r6 = r6.f11607b
            r4.add(r6)
            goto L_0x0079
        L_0x0074:
            fh r6 = r6.f11607b
            r4.remove(r6)
        L_0x0079:
            int r13 = r13 + -1
            goto L_0x0050
        L_0x007c:
            java.util.ArrayList r4 = r7.f10980A
            r13 = 0
        L_0x007f:
            java.util.ArrayList r14 = r3.f11644c
            int r14 = r14.size()
            if (r13 >= r14) goto L_0x016b
            java.util.ArrayList r14 = r3.f11644c
            java.lang.Object r14 = r14.get(r13)
            gm r14 = (p000.C0180gm) r14
            int r6 = r14.f11606a
            if (r6 == r5) goto L_0x00ba
            r5 = 2
            r10 = 9
            if (r6 == r5) goto L_0x00d9
            if (r6 == r15) goto L_0x00be
            r5 = 6
            if (r6 == r5) goto L_0x00be
            r5 = 7
            if (r6 == r5) goto L_0x00ba
            r5 = 8
            if (r6 == r5) goto L_0x00a8
        L_0x00a4:
            r16 = -1
            goto L_0x015f
        L_0x00a8:
            java.util.ArrayList r5 = r3.f11644c
            gm r6 = new gm
            r6.<init>(r10, r0)
            r5.add(r13, r6)
            int r13 = r13 + 1
            fh r0 = r14.f11607b
            r16 = -1
            goto L_0x015f
        L_0x00ba:
            r16 = -1
            goto L_0x0158
        L_0x00be:
            fh r5 = r14.f11607b
            r4.remove(r5)
            fh r5 = r14.f11607b
            if (r5 != r0) goto L_0x00a4
            java.util.ArrayList r0 = r3.f11644c
            gm r6 = new gm
            r6.<init>(r10, r5)
            r0.add(r13, r6)
            int r13 = r13 + 1
            r0 = 0
            r16 = -1
            goto L_0x015f
        L_0x00d9:
            fh r5 = r14.f11607b
            int r6 = r5.f9563B
            int r17 = r4.size()
            r16 = -1
            int r17 = r17 + -1
            r15 = r0
            r10 = r17
            r0 = 0
        L_0x00e9:
            if (r10 < 0) goto L_0x0144
            java.lang.Object r18 = r4.get(r10)
            r9 = r18
            fh r9 = (p000.C0147fh) r9
            int r8 = r9.f9563B
            if (r8 == r6) goto L_0x00fa
            r18 = r6
            goto L_0x013a
        L_0x00fa:
            if (r9 != r5) goto L_0x0100
            r18 = r6
            r0 = 1
            goto L_0x013a
        L_0x0100:
            if (r9 != r15) goto L_0x0114
            java.util.ArrayList r8 = r3.f11644c
            gm r15 = new gm
            r18 = r6
            r6 = 9
            r15.<init>(r6, r9)
            r8.add(r13, r15)
            int r13 = r13 + 1
            r15 = 0
            goto L_0x0118
        L_0x0114:
            r18 = r6
            r6 = 9
        L_0x0118:
            gm r8 = new gm
            r6 = 3
            r8.<init>(r6, r9)
            int r6 = r14.f11608c
            r8.f11608c = r6
            int r6 = r14.f11610e
            r8.f11610e = r6
            int r6 = r14.f11609d
            r8.f11609d = r6
            int r6 = r14.f11611f
            r8.f11611f = r6
            java.util.ArrayList r6 = r3.f11644c
            r6.add(r13, r8)
            r4.remove(r9)
            r6 = 1
            int r13 = r13 + r6
        L_0x013a:
            int r10 = r10 + -1
            r8 = r20
            r9 = r21
            r6 = r18
            goto L_0x00e9
        L_0x0144:
            if (r0 == 0) goto L_0x014f
            java.util.ArrayList r0 = r3.f11644c
            r0.remove(r13)
            int r13 = r13 + -1
            r0 = r15
            goto L_0x015f
        L_0x014f:
            r0 = 1
            r14.f11606a = r0
            r4.add(r5)
            r0 = r15
            goto L_0x015f
        L_0x0158:
            fh r5 = r14.f11607b
            r4.add(r5)
        L_0x015f:
            r5 = 1
            int r13 = r13 + r5
            r8 = r20
            r9 = r21
            r10 = r22
            r6 = -1
            r15 = 3
            goto L_0x007f
        L_0x016b:
            if (r2 != 0) goto L_0x0173
            boolean r2 = r3.f11650i
            if (r2 != 0) goto L_0x0173
            r2 = 0
            goto L_0x0174
        L_0x0173:
            r2 = 1
        L_0x0174:
            int r1 = r1 + 1
            r8 = r20
            r9 = r21
            r10 = r22
            goto L_0x0030
        L_0x017e:
            r16 = -1
            java.util.ArrayList r0 = r7.f10980A
            r0.clear()
            if (r12 != 0) goto L_0x019c
            r6 = 0
            gu r8 = r7.f10998q
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            r4 = r23
            r9 = 1
            r5 = r6
            r10 = -1
            r6 = r8
            p000.C0191gw.m10919a(r0, r1, r2, r3, r4, r5, r6)
            goto L_0x019e
        L_0x019c:
            r9 = 1
            r10 = -1
        L_0x019e:
            r0 = r22
        L_0x01a0:
            if (r0 >= r11) goto L_0x037e
            r8 = r20
            java.lang.Object r1 = r8.get(r0)
            eu r1 = (p000.C0133eu) r1
            r13 = r21
            java.lang.Object r2 = r13.get(r0)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            java.lang.String r3 = "Unknown cmd: "
            if (r2 == 0) goto L_0x02a5
            r1.mo5245a((int) r10)
            int r2 = r11 + -1
            java.util.ArrayList r4 = r1.f11644c
            int r4 = r4.size()
            int r4 = r4 + r10
        L_0x01c6:
            if (r4 < 0) goto L_0x028d
            java.util.ArrayList r5 = r1.f11644c
            java.lang.Object r5 = r5.get(r4)
            gm r5 = (p000.C0180gm) r5
            fh r6 = r5.f11607b
            if (r6 == 0) goto L_0x01f4
            int r14 = r1.f11649h
            r15 = 8194(0x2002, float:1.1482E-41)
            r10 = 4099(0x1003, float:5.744E-42)
            r9 = 4097(0x1001, float:5.741E-42)
            if (r14 == r9) goto L_0x01ee
            if (r14 == r10) goto L_0x01e9
            if (r14 == r15) goto L_0x01e4
            r15 = 0
            goto L_0x01f1
        L_0x01e4:
            r15 = 4097(0x1001, float:5.741E-42)
            goto L_0x01f1
        L_0x01e9:
            r15 = 4099(0x1003, float:5.744E-42)
            goto L_0x01f1
        L_0x01ee:
        L_0x01f1:
            r6.mo5644c(r15)
        L_0x01f4:
            int r9 = r5.f11606a
            switch(r9) {
                case 1: goto L_0x0266;
                case 2: goto L_0x01f9;
                case 3: goto L_0x025b;
                case 4: goto L_0x0252;
                case 5: goto L_0x0241;
                case 6: goto L_0x0236;
                case 7: goto L_0x0225;
                case 8: goto L_0x021e;
                case 9: goto L_0x0218;
                case 10: goto L_0x0210;
                default: goto L_0x01f9;
            }
        L_0x01f9:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            int r2 = r5.f11606a
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0210:
            gd r9 = r1.f9018a
            v r10 = r5.f11612g
            r9.m10051a((p000.C0147fh) r6, (p000.C0573v) r10)
            goto L_0x0276
        L_0x0218:
            gd r9 = r1.f9018a
            r9.m10071p(r6)
            goto L_0x0276
        L_0x021e:
            gd r9 = r1.f9018a
            r10 = 0
            r9.m10071p(r10)
            goto L_0x0276
        L_0x0225:
            int r9 = r5.f11611f
            r6.mo5642b((int) r9)
            gd r9 = r1.f9018a
            r10 = 1
            r9.m10052a((p000.C0147fh) r6, (boolean) r10)
            gd r9 = r1.f9018a
            r9.m10064l(r6)
            goto L_0x0276
        L_0x0236:
            int r9 = r5.f11610e
            r6.mo5642b((int) r9)
            gd r9 = r1.f9018a
            r9.m10065m(r6)
            goto L_0x0276
        L_0x0241:
            int r9 = r5.f11611f
            r6.mo5642b((int) r9)
            gd r9 = r1.f9018a
            r10 = 1
            r9.m10052a((p000.C0147fh) r6, (boolean) r10)
            gd r9 = r1.f9018a
            r9.m10063k(r6)
            goto L_0x0276
        L_0x0252:
            int r9 = r5.f11610e
            r6.mo5642b((int) r9)
            m10077s(r6)
            goto L_0x0276
        L_0x025b:
            int r9 = r5.f11610e
            r6.mo5642b((int) r9)
            gd r9 = r1.f9018a
            r9.mo6447e(r6)
            goto L_0x0276
        L_0x0266:
            int r9 = r5.f11611f
            r6.mo5642b((int) r9)
            gd r9 = r1.f9018a
            r10 = 1
            r9.m10052a((p000.C0147fh) r6, (boolean) r10)
            gd r9 = r1.f9018a
            r9.m10062j(r6)
        L_0x0276:
            boolean r9 = r1.f11659r
            if (r9 == 0) goto L_0x027b
            goto L_0x0287
        L_0x027b:
            int r5 = r5.f11606a
            r9 = 3
            if (r5 == r9) goto L_0x0287
            if (r6 == 0) goto L_0x0287
            gd r5 = r1.f9018a
            r5.m10061i(r6)
        L_0x0287:
            int r4 = r4 + -1
            r9 = 1
            r10 = -1
            goto L_0x01c6
        L_0x028d:
            boolean r3 = r1.f11659r
            if (r3 == 0) goto L_0x0295
        L_0x0291:
            r10 = 0
            r14 = 0
            goto L_0x0378
        L_0x0295:
            if (r0 != r2) goto L_0x02a3
            gd r1 = r1.f9018a
            int r2 = r1.f10990i
            r4 = 1
            r1.m10050a((int) r2, (boolean) r4)
            r10 = 0
            r14 = 0
            goto L_0x0378
        L_0x02a3:
            r4 = 1
            goto L_0x0291
        L_0x02a5:
            r4 = 1
            r1.mo5245a((int) r4)
            java.util.ArrayList r2 = r1.f11644c
            int r2 = r2.size()
            r4 = 0
        L_0x02b0:
            if (r4 >= r2) goto L_0x036a
            java.util.ArrayList r5 = r1.f11644c
            java.lang.Object r5 = r5.get(r4)
            gm r5 = (p000.C0180gm) r5
            fh r6 = r5.f11607b
            if (r6 == 0) goto L_0x02c3
            int r9 = r1.f11649h
            r6.mo5644c(r9)
        L_0x02c3:
            int r9 = r5.f11606a
            switch(r9) {
                case 1: goto L_0x0344;
                case 2: goto L_0x02c8;
                case 3: goto L_0x0337;
                case 4: goto L_0x032a;
                case 5: goto L_0x0319;
                case 6: goto L_0x030c;
                case 7: goto L_0x02f9;
                case 8: goto L_0x02f1;
                case 9: goto L_0x02e9;
                case 10: goto L_0x02df;
                default: goto L_0x02c8;
            }
        L_0x02c8:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            int r2 = r5.f11606a
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x02df:
            gd r9 = r1.f9018a
            v r10 = r5.f11613h
            r9.m10051a((p000.C0147fh) r6, (p000.C0573v) r10)
            r10 = 0
            r14 = 0
            goto L_0x0355
        L_0x02e9:
            gd r9 = r1.f9018a
            r10 = 0
            r9.m10071p(r10)
            r14 = 0
            goto L_0x0355
        L_0x02f1:
            r10 = 0
            gd r9 = r1.f9018a
            r9.m10071p(r6)
            r14 = 0
            goto L_0x0355
        L_0x02f9:
            r10 = 0
            int r9 = r5.f11608c
            r6.mo5642b((int) r9)
            gd r9 = r1.f9018a
            r14 = 0
            r9.m10052a((p000.C0147fh) r6, (boolean) r14)
            gd r9 = r1.f9018a
            r9.m10065m(r6)
            r14 = 0
            goto L_0x0355
        L_0x030c:
            r10 = 0
            int r9 = r5.f11609d
            r6.mo5642b((int) r9)
            gd r9 = r1.f9018a
            r9.m10064l(r6)
            r14 = 0
            goto L_0x0355
        L_0x0319:
            r10 = 0
            int r9 = r5.f11608c
            r6.mo5642b((int) r9)
            gd r9 = r1.f9018a
            r14 = 0
            r9.m10052a((p000.C0147fh) r6, (boolean) r14)
            m10077s(r6)
            r14 = 0
            goto L_0x0355
        L_0x032a:
            r10 = 0
            int r9 = r5.f11609d
            r6.mo5642b((int) r9)
            gd r9 = r1.f9018a
            r9.m10063k(r6)
            r14 = 0
            goto L_0x0355
        L_0x0337:
            r10 = 0
            int r9 = r5.f11609d
            r6.mo5642b((int) r9)
            gd r9 = r1.f9018a
            r9.m10062j(r6)
            r14 = 0
            goto L_0x0355
        L_0x0344:
            r10 = 0
            int r9 = r5.f11608c
            r6.mo5642b((int) r9)
            gd r9 = r1.f9018a
            r14 = 0
            r9.m10052a((p000.C0147fh) r6, (boolean) r14)
            gd r9 = r1.f9018a
            r9.mo6447e(r6)
        L_0x0355:
            boolean r9 = r1.f11659r
            if (r9 == 0) goto L_0x035a
            goto L_0x0366
        L_0x035a:
            int r5 = r5.f11606a
            r9 = 1
            if (r5 == r9) goto L_0x0366
            if (r6 == 0) goto L_0x0366
            gd r5 = r1.f9018a
            r5.m10061i(r6)
        L_0x0366:
            int r4 = r4 + 1
            goto L_0x02b0
        L_0x036a:
            r10 = 0
            r14 = 0
            boolean r2 = r1.f11659r
            if (r2 != 0) goto L_0x0378
            gd r1 = r1.f9018a
            int r2 = r1.f10990i
            r3 = 1
            r1.m10050a((int) r2, (boolean) r3)
        L_0x0378:
            int r0 = r0 + 1
            r9 = 1
            r10 = -1
            goto L_0x01a0
        L_0x037e:
            r8 = r20
            r13 = r21
            r14 = 0
            if (r12 == 0) goto L_0x040b
            kp r0 = new kp
            r0.<init>()
            int r1 = r7.f10990i
            if (r1 <= 0) goto L_0x03c0
            r2 = 3
            int r1 = java.lang.Math.min(r1, r2)
            gl r2 = r7.f10982a
            java.util.List r2 = r2.mo6826b()
            java.util.Iterator r2 = r2.iterator()
        L_0x039d:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x03c0
            java.lang.Object r3 = r2.next()
            fh r3 = (p000.C0147fh) r3
            int r4 = r3.f9587f
            if (r4 >= r1) goto L_0x039d
            r7.mo6423a((p000.C0147fh) r3, (int) r1)
            android.view.View r4 = r3.f9573L
            if (r4 == 0) goto L_0x039d
            boolean r4 = r3.f9565D
            if (r4 != 0) goto L_0x039d
            boolean r4 = r3.f9577P
            if (r4 == 0) goto L_0x039d
            r0.add(r3)
            goto L_0x039d
        L_0x03c0:
            int r1 = r11 + -1
        L_0x03c2:
            r9 = r22
            if (r1 < r9) goto L_0x03ec
            java.lang.Object r2 = r8.get(r1)
            eu r2 = (p000.C0133eu) r2
            java.lang.Object r3 = r13.get(r1)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            r3.booleanValue()
            r3 = 0
        L_0x03d6:
            java.util.ArrayList r4 = r2.f11644c
            int r4 = r4.size()
            if (r3 >= r4) goto L_0x03e9
            java.util.ArrayList r4 = r2.f11644c
            java.lang.Object r4 = r4.get(r3)
            gm r4 = (p000.C0180gm) r4
            int r3 = r3 + 1
            goto L_0x03d6
        L_0x03e9:
            int r1 = r1 + -1
            goto L_0x03c2
        L_0x03ec:
            int r1 = r0.f15147b
        L_0x03ee:
            if (r14 >= r1) goto L_0x040d
            java.lang.Object r2 = r0.mo9204a((int) r14)
            fh r2 = (p000.C0147fh) r2
            boolean r3 = r2.f9597p
            if (r3 != 0) goto L_0x0408
            android.view.View r3 = r2.mo5663u()
            float r4 = r3.getAlpha()
            r2.f9579R = r4
            r2 = 0
            r3.setAlpha(r2)
        L_0x0408:
            int r14 = r14 + 1
            goto L_0x03ee
        L_0x040b:
            r9 = r22
        L_0x040d:
            if (r11 != r9) goto L_0x0410
        L_0x040f:
            goto L_0x042a
        L_0x0410:
            if (r12 == 0) goto L_0x040f
            r5 = 1
            gu r6 = r7.f10998q
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            r4 = r23
            p000.C0191gw.m10919a(r0, r1, r2, r3, r4, r5, r6)
            int r0 = r7.f10990i
            r1 = 1
            r7.m10050a((int) r0, (boolean) r1)
        L_0x042a:
            if (r9 >= r11) goto L_0x044c
            java.lang.Object r0 = r8.get(r9)
            eu r0 = (p000.C0133eu) r0
            java.lang.Object r1 = r13.get(r9)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L_0x0440
            r1 = -1
            goto L_0x0449
        L_0x0440:
            int r1 = r0.f9019b
            if (r1 < 0) goto L_0x0448
            r1 = -1
            r0.f9019b = r1
            goto L_0x0449
        L_0x0448:
            r1 = -1
        L_0x0449:
            int r9 = r9 + 1
            goto L_0x042a
        L_0x044c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0171gd.m10055b(java.util.ArrayList, java.util.ArrayList, int, int):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final C0147fh mo6440c(String str) {
        return this.f10982a.mo6825b(str);
    }

    /* renamed from: b */
    public final C0147fh mo6432b(int i) {
        C0179gl glVar = this.f10982a;
        for (int size = glVar.f11561a.size() - 1; size >= 0; size--) {
            C0147fh fhVar = (C0147fh) glVar.f11561a.get(size);
            if (fhVar != null && fhVar.f9562A == i) {
                return fhVar;
            }
        }
        for (C0178gk gkVar : glVar.f11562b.values()) {
            if (gkVar != null) {
                C0147fh fhVar2 = gkVar.f11534b;
                if (fhVar2.f9562A == i) {
                    return fhVar2;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public final C0147fh mo6418a(String str) {
        C0179gl glVar = this.f10982a;
        if (str != null) {
            for (int size = glVar.f11561a.size() - 1; size >= 0; size--) {
                C0147fh fhVar = (C0147fh) glVar.f11561a.get(size);
                if (fhVar != null && str.equals(fhVar.f9564C)) {
                    return fhVar;
                }
            }
        }
        if (str == null) {
            return null;
        }
        for (C0178gk gkVar : glVar.f11562b.values()) {
            if (gkVar != null) {
                C0147fh fhVar2 = gkVar.f11534b;
                if (str.equals(fhVar2.f9564C)) {
                    return fhVar2;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final C0147fh mo6433b(String str) {
        for (C0178gk gkVar : this.f10982a.f11562b.values()) {
            if (gkVar != null) {
                C0147fh fhVar = gkVar.f11534b;
                if (!str.equals(fhVar.f9591j)) {
                    fhVar = fhVar.f9606y.mo6433b(str);
                }
                if (fhVar != null) {
                    return fhVar;
                }
            }
        }
        return null;
    }

    /* renamed from: o */
    private final ViewGroup m10068o(C0147fh fhVar) {
        if (fhVar.f9563B > 0 && this.f10992k.mo5558Z()) {
            View a = this.f10992k.mo5559a(fhVar.f9563B);
            if (a instanceof ViewGroup) {
                return (ViewGroup) a;
            }
        }
        return null;
    }

    /* renamed from: m */
    public final C0159ft mo6455m() {
        C0147fh fhVar = this.f10993l;
        return fhVar != null ? fhVar.f9604w.mo6455m() : this.f11000s;
    }

    /* renamed from: a */
    static C0147fh m10049a(View view) {
        Object tag = view.getTag(R.id.fragment_container_view_tag);
        if (!(tag instanceof C0147fh)) {
            return null;
        }
        return (C0147fh) tag;
    }

    /* renamed from: k */
    private final void m10063k(C0147fh fhVar) {
        if (m10054a(2)) {
            "hide: " + fhVar;
        }
        if (!fhVar.f9565D) {
            fhVar.f9565D = true;
            fhVar.f9578Q = true ^ fhVar.f9578Q;
            m10067n(fhVar);
        }
    }

    /* renamed from: a */
    static boolean m10054a(int i) {
        return Log.isLoggable("FragmentManager", i);
    }

    /* renamed from: r */
    private static final boolean m10075r(C0147fh fhVar) {
        if (fhVar.f9569H && fhVar.f9570I) {
            return true;
        }
        List c = fhVar.f9606y.f10982a.mo6828c();
        int size = c.size();
        int i = 0;
        boolean z = false;
        while (i < size) {
            C0147fh fhVar2 = (C0147fh) c.get(i);
            if (fhVar2 != null) {
                z = m10075r(fhVar2);
            }
            i++;
            if (z) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: f */
    private final boolean m10058f(C0147fh fhVar) {
        if (fhVar == null) {
            return true;
        }
        C0171gd gdVar = fhVar.f9604w;
        if (!fhVar.equals(gdVar.f10999r) || !m10058f(gdVar.f10993l)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final void mo6445d(C0147fh fhVar) {
        if (!this.f10982a.mo6824a(fhVar.f9591j)) {
            C0178gk gkVar = new C0178gk(this.f10989h, fhVar);
            gkVar.mo6792a(this.f10991j.f10593c.getClassLoader());
            this.f10982a.mo6823a(gkVar);
            if (fhVar.f9568G) {
                if (fhVar.f9567F) {
                    mo6422a(fhVar);
                } else {
                    m10059g(fhVar);
                }
                fhVar.f9568G = false;
            }
            if (m10054a(2)) {
                "Added fragment to active set " + fhVar;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0068, code lost:
        r0 = r4.f9573L;
        r3 = r9.f9572K;
     */
    /* renamed from: i */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m10061i(p000.C0147fh r9) {
        /*
            r8 = this;
            gl r0 = r8.f10982a
            java.lang.String r1 = r9.f9591j
            boolean r0 = r0.mo6824a((java.lang.String) r1)
            if (r0 != 0) goto L_0x0034
            r0 = 3
            boolean r0 = m10054a((int) r0)
            if (r0 == 0) goto L_0x0033
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Ignoring moving "
            r0.append(r1)
            r0.append(r9)
            java.lang.String r9 = " to state "
            r0.append(r9)
            int r9 = r8.f10990i
            r0.append(r9)
            java.lang.String r9 = "since it is not added to "
            r0.append(r9)
            r0.append(r8)
            r0.toString()
        L_0x0033:
            return
        L_0x0034:
            r8.mo6441c((p000.C0147fh) r9)
            android.view.View r0 = r9.f9573L
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x00bb
            gl r0 = r8.f10982a
            android.view.ViewGroup r3 = r9.f9572K
            r4 = 0
            if (r3 == 0) goto L_0x0065
            java.util.ArrayList r5 = r0.f11561a
            int r5 = r5.indexOf(r9)
            int r5 = r5 + -1
        L_0x004c:
            if (r5 < 0) goto L_0x0064
            java.util.ArrayList r6 = r0.f11561a
            java.lang.Object r6 = r6.get(r5)
            fh r6 = (p000.C0147fh) r6
            android.view.ViewGroup r7 = r6.f9572K
            if (r7 == r3) goto L_0x005b
        L_0x005a:
            goto L_0x0061
        L_0x005b:
            android.view.View r7 = r6.f9573L
            if (r7 == 0) goto L_0x005a
            r4 = r6
            goto L_0x0066
        L_0x0061:
            int r5 = r5 + -1
            goto L_0x004c
        L_0x0064:
        L_0x0065:
        L_0x0066:
            if (r4 == 0) goto L_0x0080
            android.view.View r0 = r4.f9573L
            android.view.ViewGroup r3 = r9.f9572K
            int r0 = r3.indexOfChild(r0)
            android.view.View r4 = r9.f9573L
            int r4 = r3.indexOfChild(r4)
            if (r4 >= r0) goto L_0x0080
            r3.removeViewAt(r4)
            android.view.View r4 = r9.f9573L
            r3.addView(r4, r0)
        L_0x0080:
            boolean r0 = r9.f9577P
            if (r0 == 0) goto L_0x00bb
            android.view.ViewGroup r0 = r9.f9572K
            if (r0 == 0) goto L_0x00bb
            float r0 = r9.f9579R
            r3 = 0
            int r4 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r4 <= 0) goto L_0x0094
            android.view.View r4 = r9.f9573L
            r4.setAlpha(r0)
        L_0x0094:
            r9.f9579R = r3
            r9.f9577P = r2
            fu r0 = r8.f10991j
            android.content.Context r0 = r0.f10593c
            fq r3 = r8.f10992k
            fo r0 = p000.C0257jh.m14469a(r0, r3, r9, r1)
            if (r0 == 0) goto L_0x00bb
            android.view.animation.Animation r3 = r0.f10127a
            if (r3 != 0) goto L_0x00b6
            android.animation.Animator r3 = r0.f10128b
            android.view.View r4 = r9.f9573L
            r3.setTarget(r4)
            android.animation.Animator r0 = r0.f10128b
            r0.start()
            goto L_0x00bb
        L_0x00b6:
            android.view.View r0 = r9.f9573L
            r0.startAnimation(r3)
        L_0x00bb:
            boolean r0 = r9.f9578Q
            if (r0 == 0) goto L_0x0146
            android.view.View r0 = r9.f9573L
            if (r0 == 0) goto L_0x0134
            fu r0 = r8.f10991j
            android.content.Context r0 = r0.f10593c
            fq r3 = r8.f10992k
            boolean r4 = r9.f9565D
            r4 = r4 ^ r1
            fo r0 = p000.C0257jh.m14469a(r0, r3, r9, r4)
            if (r0 == 0) goto L_0x0107
            android.animation.Animator r3 = r0.f10128b
            if (r3 != 0) goto L_0x00d7
            goto L_0x0107
        L_0x00d7:
            android.view.View r4 = r9.f9573L
            r3.setTarget(r4)
            boolean r3 = r9.f9565D
            if (r3 == 0) goto L_0x00fc
            boolean r3 = r9.mo5626G()
            if (r3 == 0) goto L_0x00ea
            r9.mo5639a((boolean) r2)
            goto L_0x0101
        L_0x00ea:
            android.view.ViewGroup r3 = r9.f9572K
            android.view.View r4 = r9.f9573L
            r3.startViewTransition(r4)
            android.animation.Animator r5 = r0.f10128b
            ga r6 = new ga
            r6.<init>(r3, r4, r9)
            r5.addListener(r6)
            goto L_0x0101
        L_0x00fc:
            android.view.View r3 = r9.f9573L
            r3.setVisibility(r2)
        L_0x0101:
            android.animation.Animator r0 = r0.f10128b
            r0.start()
            goto L_0x0134
        L_0x0107:
            if (r0 == 0) goto L_0x0115
            android.view.View r3 = r9.f9573L
            android.view.animation.Animation r4 = r0.f10127a
            r3.startAnimation(r4)
            android.view.animation.Animation r0 = r0.f10127a
            r0.start()
        L_0x0115:
            boolean r0 = r9.f9565D
            if (r0 == 0) goto L_0x0123
            boolean r0 = r9.mo5626G()
            if (r0 == 0) goto L_0x0120
            goto L_0x0123
        L_0x0120:
            r0 = 8
            goto L_0x0124
        L_0x0123:
            r0 = 0
        L_0x0124:
            android.view.View r3 = r9.f9573L
            r3.setVisibility(r0)
            boolean r0 = r9.mo5626G()
            if (r0 != 0) goto L_0x0130
            goto L_0x0134
        L_0x0130:
            r9.mo5639a((boolean) r2)
        L_0x0134:
            boolean r0 = r9.f9597p
            if (r0 == 0) goto L_0x0140
            boolean r0 = m10075r(r9)
            if (r0 == 0) goto L_0x0140
            r8.f11001t = r1
        L_0x0140:
            r9.f9578Q = r2
            boolean r9 = r9.f9565D
            return
        L_0x0146:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0171gd.m10061i(fh):void");
    }

    /* renamed from: a */
    private final void m10050a(int i, boolean z) {
        C0160fu fuVar;
        if (this.f10991j == null && i != -1) {
            throw new IllegalStateException("No activity");
        } else if (z || i != this.f10990i) {
            this.f10990i = i;
            for (C0147fh i2 : this.f10982a.mo6826b()) {
                m10061i(i2);
            }
            List c = this.f10982a.mo6828c();
            int size = c.size();
            for (int i3 = 0; i3 < size; i3++) {
                C0147fh fhVar = (C0147fh) c.get(i3);
                if (fhVar != null && !fhVar.f9577P) {
                    m10061i(fhVar);
                }
            }
            m10069o();
            if (this.f11001t && (fuVar = this.f10991j) != null && this.f10990i == 4) {
                fuVar.mo5744c();
                this.f11001t = false;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final void mo6441c(C0147fh fhVar) {
        mo6423a(fhVar, this.f10990i);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x049e, code lost:
        if (r1 != 3) goto L_0x07e7;
     */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x03bb  */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x0613  */
    /* JADX WARNING: Removed duplicated region for block: B:268:0x0672  */
    /* JADX WARNING: Removed duplicated region for block: B:288:0x070c  */
    /* JADX WARNING: Removed duplicated region for block: B:300:0x0777  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo6423a(p000.C0147fh r19, int r20) {
        /*
            r18 = this;
            r0 = r18
            r7 = r19
            gl r1 = r0.f10982a
            java.lang.String r2 = r7.f9591j
            java.util.HashMap r1 = r1.f11562b
            java.lang.Object r1 = r1.get(r2)
            gk r1 = (p000.C0178gk) r1
            if (r1 == 0) goto L_0x0014
            r8 = r1
            goto L_0x001c
        L_0x0014:
            gk r1 = new gk
            fw r2 = r0.f10989h
            r1.<init>(r2, r7)
            r8 = r1
        L_0x001c:
            fh r1 = r8.f11534b
            boolean r2 = r1.f9599r
            r3 = 4
            if (r2 == 0) goto L_0x002f
            boolean r2 = r1.f9600s
            if (r2 != 0) goto L_0x002e
            int r1 = r1.f9587f
            int r1 = java.lang.Math.min(r3, r1)
            goto L_0x0030
        L_0x002e:
        L_0x002f:
            r1 = 4
        L_0x0030:
            fh r2 = r8.f11534b
            boolean r2 = r2.f9597p
            r9 = 1
            if (r2 == 0) goto L_0x0038
            goto L_0x003d
        L_0x0038:
            int r1 = java.lang.Math.min(r1, r9)
        L_0x003d:
            fh r2 = r8.f11534b
            boolean r4 = r2.f9598q
            r10 = -1
            if (r4 == 0) goto L_0x0054
            boolean r2 = r2.mo5650i()
            if (r2 == 0) goto L_0x004f
            int r1 = java.lang.Math.min(r1, r9)
            goto L_0x0054
        L_0x004f:
            int r1 = java.lang.Math.min(r1, r10)
        L_0x0054:
            fh r2 = r8.f11534b
            boolean r4 = r2.f9574M
            r11 = 2
            r12 = 3
            if (r4 == 0) goto L_0x0064
            int r2 = r2.f9587f
            if (r2 >= r12) goto L_0x0064
            int r1 = java.lang.Math.min(r1, r11)
        L_0x0064:
            v r2 = p000.C0573v.DESTROYED
            fh r2 = r8.f11534b
            v r2 = r2.f9582U
            int r2 = r2.ordinal()
            if (r2 == r11) goto L_0x007f
            if (r2 == r12) goto L_0x0079
            if (r2 == r3) goto L_0x0084
            int r1 = java.lang.Math.min(r1, r10)
            goto L_0x0084
        L_0x0079:
            int r1 = java.lang.Math.min(r1, r12)
            goto L_0x0084
        L_0x007f:
            int r1 = java.lang.Math.min(r1, r9)
        L_0x0084:
            r2 = r20
            int r13 = java.lang.Math.min(r2, r1)
            int r1 = r7.f9587f
            java.lang.String r14 = "Fragment "
            r15 = 0
            r6 = 0
            if (r1 <= r13) goto L_0x0484
            if (r1 > r13) goto L_0x0096
        L_0x0094:
            goto L_0x07e7
        L_0x0096:
            if (r1 == 0) goto L_0x03b6
            if (r1 == r9) goto L_0x0245
            if (r1 == r11) goto L_0x0162
            if (r1 == r12) goto L_0x0102
            if (r1 == r3) goto L_0x00a1
            goto L_0x0094
        L_0x00a1:
            if (r13 >= r3) goto L_0x0102
            boolean r1 = m10054a((int) r12)
            if (r1 == 0) goto L_0x00bc
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "movefrom RESUMED: "
            r1.append(r2)
            fh r2 = r8.f11534b
            r1.append(r2)
            r1.toString()
        L_0x00bc:
            fh r1 = r8.f11534b
            gd r2 = r1.f9606y
            r2.mo6451i()
            android.view.View r2 = r1.f9573L
            if (r2 == 0) goto L_0x00ce
            hh r2 = r1.f9584W
            u r3 = p000.C0546u.ON_PAUSE
            r2.mo7435a(r3)
        L_0x00ce:
            ab r2 = r1.f9583V
            u r3 = p000.C0546u.ON_PAUSE
            r2.mo62a((p000.C0546u) r3)
            r1.f9587f = r12
            r1.f9571J = r6
            r1.mo2669w()
            boolean r2 = r1.f9571J
            if (r2 == 0) goto L_0x00e8
            fw r1 = r8.f11533a
            fh r2 = r8.f11534b
            r1.mo6237c(r2, r6)
            goto L_0x0102
        L_0x00e8:
            hs r2 = new hs
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r14)
            r3.append(r1)
            java.lang.String r1 = " did not call through to super.onPause()"
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        L_0x0102:
            if (r13 >= r12) goto L_0x0162
            boolean r1 = m10054a((int) r12)
            if (r1 == 0) goto L_0x011c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "movefrom STARTED: "
            r1.append(r2)
            fh r2 = r8.f11534b
            r1.append(r2)
            r1.toString()
        L_0x011c:
            fh r1 = r8.f11534b
            gd r2 = r1.f9606y
            r2.mo6452j()
            android.view.View r2 = r1.f9573L
            if (r2 == 0) goto L_0x012e
            hh r2 = r1.f9584W
            u r3 = p000.C0546u.ON_STOP
            r2.mo7435a(r3)
        L_0x012e:
            ab r2 = r1.f9583V
            u r3 = p000.C0546u.ON_STOP
            r2.mo62a((p000.C0546u) r3)
            r1.f9587f = r11
            r1.f9571J = r6
            r1.mo211e()
            boolean r2 = r1.f9571J
            if (r2 == 0) goto L_0x0148
            fw r1 = r8.f11533a
            fh r2 = r8.f11534b
            r1.mo6239d(r2, r6)
            goto L_0x0162
        L_0x0148:
            hs r2 = new hs
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r14)
            r3.append(r1)
            java.lang.String r1 = " did not call through to super.onStop()"
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        L_0x0162:
            if (r13 >= r11) goto L_0x0243
            boolean r1 = m10054a((int) r12)
            if (r1 == 0) goto L_0x017a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "movefrom ACTIVITY_CREATED: "
            r1.append(r2)
            r1.append(r7)
            r1.toString()
        L_0x017a:
            android.view.View r1 = r7.f9573L
            if (r1 != 0) goto L_0x017f
            goto L_0x0193
        L_0x017f:
            fu r1 = r0.f10991j
            fi r1 = (p000.C0148fi) r1
            fj r1 = r1.f9696a
            boolean r1 = r1.isFinishing()
            r1 = r1 ^ r9
            if (r1 == 0) goto L_0x0193
            android.util.SparseArray r1 = r7.f9589h
            if (r1 != 0) goto L_0x0193
            r8.mo6790a()
        L_0x0193:
            android.view.View r1 = r7.f9573L
            if (r1 == 0) goto L_0x0232
            android.view.ViewGroup r2 = r7.f9572K
            if (r2 == 0) goto L_0x0232
            r2.endViewTransition(r1)
            android.view.View r1 = r7.f9573L
            r1.clearAnimation()
            boolean r1 = r19.mo5662t()
            if (r1 != 0) goto L_0x0230
            int r1 = r0.f10990i
            r2 = 0
            if (r1 < 0) goto L_0x01cc
            boolean r1 = r0.f11004w
            if (r1 != 0) goto L_0x01cc
            android.view.View r1 = r7.f9573L
            int r1 = r1.getVisibility()
            if (r1 != 0) goto L_0x01cc
            float r1 = r7.f9579R
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 < 0) goto L_0x01cb
            fu r1 = r0.f10991j
            android.content.Context r1 = r1.f10593c
            fq r3 = r0.f10992k
            fo r1 = p000.C0257jh.m14469a(r1, r3, r7, r6)
            goto L_0x01cd
        L_0x01cb:
        L_0x01cc:
            r1 = r15
        L_0x01cd:
            r7.f9579R = r2
            if (r1 == 0) goto L_0x0227
            gu r5 = r0.f10998q
            android.view.View r3 = r7.f9573L
            android.view.ViewGroup r2 = r7.f9572K
            r2.startViewTransition(r3)
            jj r4 = new jj
            r4.<init>()
            fk r6 = new fk
            r6.<init>(r7)
            r4.mo9160a(r6)
            r5.mo6296a(r7, r4)
            android.view.animation.Animation r6 = r1.f10127a
            if (r6 == 0) goto L_0x0207
            fp r1 = new fp
            r1.<init>(r6, r2, r3)
            android.view.View r3 = r7.f9573L
            r7.mo5637a((android.view.View) r3)
            fm r3 = new fm
            r3.<init>(r2, r7, r5, r4)
            r1.setAnimationListener(r3)
            android.view.View r2 = r7.f9573L
            r2.startAnimation(r1)
            r10 = 0
            goto L_0x0228
        L_0x0207:
            android.animation.Animator r6 = r1.f10128b
            r7.mo5636a((android.animation.Animator) r6)
            fn r1 = new fn
            r16 = r1
            r17 = r4
            r4 = r19
            r9 = r6
            r10 = 0
            r6 = r17
            r1.<init>(r2, r3, r4, r5, r6)
            r9.addListener(r1)
            android.view.View r1 = r7.f9573L
            r9.setTarget(r1)
            r9.start()
            goto L_0x0228
        L_0x0227:
            r10 = 0
        L_0x0228:
            android.view.ViewGroup r1 = r7.f9572K
            android.view.View r2 = r7.f9573L
            r1.removeView(r2)
            goto L_0x0233
        L_0x0230:
            r10 = 0
            goto L_0x0233
        L_0x0232:
            r10 = 0
        L_0x0233:
            j$.util.concurrent.ConcurrentHashMap r1 = r0.f10988g
            java.lang.Object r1 = r1.get(r7)
            if (r1 != 0) goto L_0x023f
            r18.mo6435b((p000.C0147fh) r19)
            goto L_0x0246
        L_0x023f:
            r7.mo5645d((int) r13)
            goto L_0x0246
        L_0x0243:
            r10 = 0
            goto L_0x0246
        L_0x0245:
            r10 = 0
        L_0x0246:
            if (r13 > 0) goto L_0x03b7
            boolean r1 = r7.f9598q
            if (r1 != 0) goto L_0x024d
        L_0x024c:
            goto L_0x0254
        L_0x024d:
            boolean r1 = r19.mo5650i()
            if (r1 == 0) goto L_0x026e
            goto L_0x024c
        L_0x0254:
            gh r1 = r0.f10994m
            boolean r1 = r1.mo6666a((p000.C0147fh) r7)
            if (r1 == 0) goto L_0x025d
            goto L_0x026e
        L_0x025d:
            java.lang.String r1 = r7.f9594m
            if (r1 == 0) goto L_0x02d1
            fh r1 = r0.mo6440c((java.lang.String) r1)
            if (r1 == 0) goto L_0x02d1
            boolean r2 = r1.f9567F
            if (r2 == 0) goto L_0x02d1
            r7.f9593l = r1
            goto L_0x02d1
        L_0x026e:
            fh r1 = r8.f11534b
            gl r2 = r0.f10982a
            java.lang.String r3 = r1.f9591j
            boolean r2 = r2.mo6824a((java.lang.String) r3)
            if (r2 == 0) goto L_0x02d1
            boolean r2 = m10054a((int) r11)
            if (r2 == 0) goto L_0x0290
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Removed fragment from active set "
            r2.append(r3)
            r2.append(r1)
            r2.toString()
        L_0x0290:
            gl r2 = r0.f10982a
            fh r3 = r8.f11534b
            java.util.HashMap r4 = r2.f11562b
            java.util.Collection r4 = r4.values()
            java.util.Iterator r4 = r4.iterator()
        L_0x029e:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x02bd
            java.lang.Object r5 = r4.next()
            gk r5 = (p000.C0178gk) r5
            if (r5 == 0) goto L_0x029e
            fh r5 = r5.f11534b
            java.lang.String r6 = r3.f9591j
            java.lang.String r9 = r5.f9594m
            boolean r6 = r6.equals(r9)
            if (r6 == 0) goto L_0x029e
            r5.f9593l = r3
            r5.f9594m = r15
            goto L_0x029e
        L_0x02bd:
            java.util.HashMap r4 = r2.f11562b
            java.lang.String r5 = r3.f9591j
            r4.put(r5, r15)
            java.lang.String r4 = r3.f9594m
            if (r4 == 0) goto L_0x02ce
            fh r2 = r2.mo6825b((java.lang.String) r4)
            r3.f9593l = r2
        L_0x02ce:
            r0.m10059g(r1)
        L_0x02d1:
            j$.util.concurrent.ConcurrentHashMap r1 = r0.f10988g
            java.lang.Object r1 = r1.get(r7)
            if (r1 != 0) goto L_0x03b0
            fu r1 = r0.f10991j
            gh r2 = r0.f10994m
            boolean r3 = m10054a((int) r12)
            if (r3 == 0) goto L_0x02f5
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "movefrom CREATED: "
            r3.append(r4)
            fh r4 = r8.f11534b
            r3.append(r4)
            r3.toString()
        L_0x02f5:
            fh r3 = r8.f11534b
            boolean r4 = r3.f9598q
            if (r4 == 0) goto L_0x0305
            boolean r3 = r3.mo5650i()
            if (r3 != 0) goto L_0x0303
            r6 = 1
            goto L_0x0306
        L_0x0303:
        L_0x0305:
            r6 = 0
        L_0x0306:
            if (r6 != 0) goto L_0x0318
            fh r3 = r8.f11534b
            boolean r3 = r2.mo6666a((p000.C0147fh) r3)
            if (r3 == 0) goto L_0x0311
            goto L_0x0318
        L_0x0311:
            fh r1 = r8.f11534b
            r1.f9587f = r10
            goto L_0x03b8
        L_0x0318:
            boolean r3 = r1 instanceof p000.C0026ax
            if (r3 != 0) goto L_0x0327
            android.content.Context r1 = r1.f10593c
            android.app.Activity r1 = (android.app.Activity) r1
            boolean r1 = r1.isChangingConfigurations()
            r3 = 1
            r1 = r1 ^ r3
            goto L_0x0329
        L_0x0327:
            boolean r1 = r2.f11335g
        L_0x0329:
            if (r6 == 0) goto L_0x032c
            goto L_0x032e
        L_0x032c:
            if (r1 == 0) goto L_0x0372
        L_0x032e:
            fh r1 = r8.f11534b
            boolean r3 = m10054a((int) r12)
            if (r3 == 0) goto L_0x0346
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Clearing non-config state for "
            r3.append(r4)
            r3.append(r1)
            r3.toString()
        L_0x0346:
            java.util.HashMap r3 = r2.f11332d
            java.lang.String r4 = r1.f9591j
            java.lang.Object r3 = r3.get(r4)
            gh r3 = (p000.C0175gh) r3
            if (r3 == 0) goto L_0x035c
            r3.mo1506a()
            java.util.HashMap r3 = r2.f11332d
            java.lang.String r4 = r1.f9591j
            r3.remove(r4)
        L_0x035c:
            java.util.HashMap r3 = r2.f11333e
            java.lang.String r4 = r1.f9591j
            java.lang.Object r3 = r3.get(r4)
            aw r3 = (p000.C0025aw) r3
            if (r3 == 0) goto L_0x0372
            r3.mo1694a()
            java.util.HashMap r2 = r2.f11333e
            java.lang.String r1 = r1.f9591j
            r2.remove(r1)
        L_0x0372:
            fh r1 = r8.f11534b
            gd r2 = r1.f9606y
            r2.mo6453k()
            ab r2 = r1.f9583V
            u r3 = p000.C0546u.ON_DESTROY
            r2.mo62a((p000.C0546u) r3)
            r1.f9587f = r10
            r1.f9571J = r10
            r1.f9581T = r10
            r1.mo1836x()
            boolean r2 = r1.f9571J
            if (r2 == 0) goto L_0x0396
            fw r1 = r8.f11533a
            fh r2 = r8.f11534b
            r1.mo6241f(r2, r10)
            goto L_0x03b8
        L_0x0396:
            hs r2 = new hs
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r14)
            r3.append(r1)
            java.lang.String r1 = " did not call through to super.onDestroy()"
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        L_0x03b0:
            r7.mo5645d((int) r13)
            r9 = 1
            goto L_0x03b9
        L_0x03b6:
            r10 = 0
        L_0x03b7:
        L_0x03b8:
            r9 = r13
        L_0x03b9:
            if (r9 >= 0) goto L_0x0480
            gh r1 = r0.f10994m
            boolean r2 = m10054a((int) r12)
            if (r2 == 0) goto L_0x03d5
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "movefrom ATTACHED: "
            r2.append(r3)
            fh r3 = r8.f11534b
            r2.append(r3)
            r2.toString()
        L_0x03d5:
            fh r2 = r8.f11534b
            r3 = -1
            r2.f9587f = r3
            r2.f9571J = r10
            r2.mo1834c()
            r2.f9580S = r15
            boolean r3 = r2.f9571J
            if (r3 == 0) goto L_0x0466
            gd r3 = r2.f9606y
            boolean r4 = r3.f11004w
            if (r4 != 0) goto L_0x03f5
            r3.mo6453k()
            gd r3 = new gd
            r3.<init>(r15)
            r2.f9606y = r3
        L_0x03f5:
            fw r2 = r8.f11533a
            fh r3 = r8.f11534b
            r2.mo6242g(r3, r10)
            fh r2 = r8.f11534b
            r3 = -1
            r2.f9587f = r3
            r2.f9605x = r15
            r2.f9607z = r15
            r2.f9604w = r15
            boolean r3 = r2.f9598q
            if (r3 != 0) goto L_0x040c
        L_0x040b:
            goto L_0x0413
        L_0x040c:
            boolean r2 = r2.mo5650i()
            if (r2 == 0) goto L_0x041b
            goto L_0x040b
        L_0x0413:
            fh r2 = r8.f11534b
            boolean r1 = r1.mo6666a((p000.C0147fh) r2)
            if (r1 == 0) goto L_0x0480
        L_0x041b:
            boolean r1 = m10054a((int) r12)
            if (r1 == 0) goto L_0x0434
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "initState called for fragment: "
            r1.append(r2)
            fh r2 = r8.f11534b
            r1.append(r2)
            r1.toString()
        L_0x0434:
            fh r1 = r8.f11534b
            r1.mo5648h()
            java.util.UUID r2 = java.util.UUID.randomUUID()
            java.lang.String r2 = r2.toString()
            r1.f9591j = r2
            r1.f9597p = r10
            r1.f9598q = r10
            r1.f9599r = r10
            r1.f9600s = r10
            r1.f9601t = r10
            r1.f9603v = r10
            r1.f9604w = r15
            gd r2 = new gd
            r2.<init>(r15)
            r1.f9606y = r2
            r1.f9605x = r15
            r1.f9562A = r10
            r1.f9563B = r10
            r1.f9564C = r15
            r1.f9565D = r10
            r1.f9566E = r10
            goto L_0x0481
        L_0x0466:
            hs r1 = new hs
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r14)
            r3.append(r2)
            java.lang.String r2 = " did not call through to super.onDetach()"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.<init>(r2)
            throw r1
        L_0x0480:
        L_0x0481:
            r13 = r9
            goto L_0x07e7
        L_0x0484:
            r10 = 0
            if (r1 >= r13) goto L_0x0492
            j$.util.concurrent.ConcurrentHashMap r1 = r0.f10988g
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0492
            r18.m10060h(r19)
        L_0x0492:
            int r1 = r7.f9587f
            r2 = -1
            if (r1 == r2) goto L_0x04a2
            if (r1 == 0) goto L_0x0599
            r2 = 1
            if (r1 == r2) goto L_0x0611
            if (r1 == r11) goto L_0x070a
            if (r1 == r12) goto L_0x0775
            goto L_0x0094
        L_0x04a2:
            if (r13 < 0) goto L_0x0599
            boolean r1 = m10054a((int) r12)
            if (r1 == 0) goto L_0x04ba
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "moveto ATTACHED: "
            r1.append(r2)
            r1.append(r7)
            r1.toString()
        L_0x04ba:
            fh r1 = r7.f9593l
            java.lang.String r2 = " that does not belong to this FragmentManager!"
            java.lang.String r4 = " declared target fragment "
            if (r1 == 0) goto L_0x0501
            java.lang.String r5 = r1.f9591j
            fh r5 = r0.mo6440c((java.lang.String) r5)
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L_0x04e1
            fh r1 = r7.f9593l
            int r5 = r1.f9587f
            if (r5 > 0) goto L_0x04d8
            r5 = 1
            r0.mo6423a((p000.C0147fh) r1, (int) r5)
        L_0x04d8:
            fh r1 = r7.f9593l
            java.lang.String r1 = r1.f9591j
            r7.f9594m = r1
            r7.f9593l = r15
            goto L_0x0501
        L_0x04e1:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r14)
            r3.append(r7)
            r3.append(r4)
            fh r4 = r7.f9593l
            r3.append(r4)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.<init>(r2)
            throw r1
        L_0x0501:
            java.lang.String r1 = r7.f9594m
            if (r1 == 0) goto L_0x0536
            fh r1 = r0.mo6440c((java.lang.String) r1)
            if (r1 == 0) goto L_0x0516
            int r2 = r1.f9587f
            if (r2 <= 0) goto L_0x0510
            goto L_0x0536
        L_0x0510:
            r2 = 1
            r0.mo6423a((p000.C0147fh) r1, (int) r2)
            goto L_0x0536
        L_0x0516:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r14)
            r3.append(r7)
            r3.append(r4)
            java.lang.String r4 = r7.f9594m
            r3.append(r4)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.<init>(r2)
            throw r1
        L_0x0536:
            fu r1 = r0.f10991j
            fh r2 = r0.f10993l
            fh r4 = r8.f11534b
            r4.f9605x = r1
            r4.f9607z = r2
            r4.f9604w = r0
            fw r2 = r8.f11533a
            android.content.Context r5 = r1.f10593c
            r2.mo6229a((p000.C0147fh) r4, (android.content.Context) r5, (boolean) r10)
            fh r2 = r8.f11534b
            gd r4 = r2.f9606y
            fu r5 = r2.f9605x
            fe r6 = new fe
            r6.<init>(r2)
            r4.mo6424a(r5, r6, r2)
            r2.f9587f = r10
            r2.f9571J = r10
            fu r4 = r2.f9605x
            android.content.Context r4 = r4.f10593c
            r2.mo1832a((android.content.Context) r4)
            boolean r4 = r2.f9571J
            if (r4 == 0) goto L_0x057f
            fh r2 = r8.f11534b
            fh r2 = r2.f9607z
            if (r2 == 0) goto L_0x056d
            goto L_0x0575
        L_0x056d:
            r2 = r1
            fi r2 = (p000.C0148fi) r2
            fj r2 = r2.f9696a
            r2.mo5853e()
        L_0x0575:
            fw r2 = r8.f11533a
            fh r4 = r8.f11534b
            android.content.Context r1 = r1.f10593c
            r2.mo6233b((p000.C0147fh) r4, (android.content.Context) r1, (boolean) r10)
            goto L_0x0599
        L_0x057f:
            hs r1 = new hs
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r14)
            r3.append(r2)
            java.lang.String r2 = " did not call through to super.onAttach()"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.<init>(r2)
            throw r1
        L_0x0599:
            if (r13 <= 0) goto L_0x0611
            boolean r1 = m10054a((int) r12)
            if (r1 == 0) goto L_0x05b3
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "moveto CREATED: "
            r1.append(r2)
            fh r2 = r8.f11534b
            r1.append(r2)
            r1.toString()
        L_0x05b3:
            fh r1 = r8.f11534b
            boolean r2 = r1.f9581T
            if (r2 != 0) goto L_0x0607
            fw r2 = r8.f11533a
            android.os.Bundle r4 = r1.f9588g
            r2.mo6230a((p000.C0147fh) r1, (android.os.Bundle) r4, (boolean) r10)
            fh r1 = r8.f11534b
            android.os.Bundle r2 = r1.f9588g
            gd r4 = r1.f9606y
            r4.noteStateNotSaved()
            r4 = 1
            r1.f9587f = r4
            r1.f9571J = r10
            aen r5 = r1.f9586Y
            r5.mo250a((android.os.Bundle) r2)
            r1.mo165a((android.os.Bundle) r2)
            r1.f9581T = r4
            boolean r2 = r1.f9571J
            if (r2 == 0) goto L_0x05ed
            ab r1 = r1.f9583V
            u r2 = p000.C0546u.ON_CREATE
            r1.mo62a((p000.C0546u) r2)
            fw r1 = r8.f11533a
            fh r2 = r8.f11534b
            android.os.Bundle r4 = r2.f9588g
            r1.mo6234b((p000.C0147fh) r2, (android.os.Bundle) r4, (boolean) r10)
            goto L_0x0611
        L_0x05ed:
            hs r2 = new hs
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r14)
            r3.append(r1)
            java.lang.String r1 = " did not call through to super.onCreate()"
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        L_0x0607:
            android.os.Bundle r2 = r1.f9588g
            r1.mo5649h(r2)
            fh r1 = r8.f11534b
            r2 = 1
            r1.f9587f = r2
        L_0x0611:
            if (r13 < 0) goto L_0x066f
            fh r1 = r8.f11534b
            boolean r2 = r1.f9599r
            if (r2 == 0) goto L_0x066f
            boolean r1 = r1.f9602u
            if (r1 != 0) goto L_0x066f
            boolean r1 = m10054a((int) r12)
            if (r1 == 0) goto L_0x0635
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "moveto CREATE_VIEW: "
            r1.append(r2)
            fh r2 = r8.f11534b
            r1.append(r2)
            r1.toString()
        L_0x0635:
            fh r1 = r8.f11534b
            android.os.Bundle r2 = r1.f9588g
            android.view.LayoutInflater r2 = r1.mo5647g(r2)
            fh r4 = r8.f11534b
            android.os.Bundle r4 = r4.f9588g
            r1.mo5643b(r2, r15, r4)
            fh r1 = r8.f11534b
            android.view.View r1 = r1.f9573L
            if (r1 == 0) goto L_0x066f
            r1.setSaveFromParentEnabled(r10)
            fh r1 = r8.f11534b
            boolean r2 = r1.f9565D
            if (r2 != 0) goto L_0x0654
            goto L_0x065b
        L_0x0654:
            android.view.View r1 = r1.f9573L
            r2 = 8
            r1.setVisibility(r2)
        L_0x065b:
            fh r1 = r8.f11534b
            android.view.View r2 = r1.f9573L
            android.os.Bundle r4 = r1.f9588g
            r1.mo2632a((android.view.View) r2, (android.os.Bundle) r4)
            fw r1 = r8.f11533a
            fh r2 = r8.f11534b
            android.view.View r4 = r2.f9573L
            android.os.Bundle r5 = r2.f9588g
            r1.mo6231a(r2, r4, r5, r10)
        L_0x066f:
            r1 = 1
            if (r13 <= r1) goto L_0x070a
            fq r1 = r0.f10992k
            r8.mo6791a((p000.C0156fq) r1)
            boolean r1 = m10054a((int) r12)
            if (r1 == 0) goto L_0x068f
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "moveto ACTIVITY_CREATED: "
            r1.append(r2)
            fh r2 = r8.f11534b
            r1.append(r2)
            r1.toString()
        L_0x068f:
            fh r1 = r8.f11534b
            android.os.Bundle r2 = r1.f9588g
            gd r4 = r1.f9606y
            r4.noteStateNotSaved()
            r1.f9587f = r11
            r1.f9571J = r10
            r1.mo2667d((android.os.Bundle) r2)
            boolean r2 = r1.f9571J
            if (r2 == 0) goto L_0x06f0
            gd r1 = r1.f9606y
            r1.mo6448f()
            fw r1 = r8.f11533a
            fh r2 = r8.f11534b
            android.os.Bundle r4 = r2.f9588g
            r1.mo6236c(r2, r4, r10)
            boolean r1 = m10054a((int) r12)
            if (r1 == 0) goto L_0x06c9
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "moveto RESTORE_VIEW_STATE: "
            r1.append(r2)
            fh r2 = r8.f11534b
            r1.append(r2)
            r1.toString()
        L_0x06c9:
            fh r1 = r8.f11534b
            android.view.View r2 = r1.f9573L
            if (r2 == 0) goto L_0x06eb
            android.os.Bundle r4 = r1.f9588g
            android.util.SparseArray r4 = r1.f9589h
            if (r4 == 0) goto L_0x06da
            r2.restoreHierarchyState(r4)
            r1.f9589h = r15
        L_0x06da:
            r1.f9571J = r10
            r2 = 1
            r1.f9571J = r2
            android.view.View r2 = r1.f9573L
            if (r2 == 0) goto L_0x06eb
            hh r1 = r1.f9584W
            u r2 = p000.C0546u.ON_CREATE
            r1.mo7435a(r2)
        L_0x06eb:
            fh r1 = r8.f11534b
            r1.f9588g = r15
            goto L_0x070a
        L_0x06f0:
            hs r2 = new hs
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r14)
            r3.append(r1)
            java.lang.String r1 = " did not call through to super.onActivityCreated()"
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        L_0x070a:
            if (r13 <= r11) goto L_0x0775
            boolean r1 = m10054a((int) r12)
            if (r1 == 0) goto L_0x0724
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "moveto STARTED: "
            r1.append(r2)
            fh r2 = r8.f11534b
            r1.append(r2)
            r1.toString()
        L_0x0724:
            fh r1 = r8.f11534b
            gd r2 = r1.f9606y
            r2.noteStateNotSaved()
            gd r2 = r1.f9606y
            r4 = 1
            r2.mo6442c((boolean) r4)
            r1.f9587f = r12
            r1.f9571J = r10
            r1.mo210d()
            boolean r2 = r1.f9571J
            if (r2 == 0) goto L_0x075b
            ab r2 = r1.f9583V
            u r4 = p000.C0546u.ON_START
            r2.mo62a((p000.C0546u) r4)
            android.view.View r2 = r1.f9573L
            if (r2 == 0) goto L_0x074e
            hh r2 = r1.f9584W
            u r4 = p000.C0546u.ON_START
            r2.mo7435a(r4)
        L_0x074e:
            gd r1 = r1.f9606y
            r1.mo6449g()
            fw r1 = r8.f11533a
            fh r2 = r8.f11534b
            r1.mo6232a(r2, r10)
            goto L_0x0775
        L_0x075b:
            hs r2 = new hs
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r14)
            r3.append(r1)
            java.lang.String r1 = " did not call through to super.onStart()"
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        L_0x0775:
            if (r13 <= r12) goto L_0x0094
            boolean r1 = m10054a((int) r12)
            if (r1 == 0) goto L_0x078f
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "moveto RESUMED: "
            r1.append(r2)
            fh r2 = r8.f11534b
            r1.append(r2)
            r1.toString()
        L_0x078f:
            fh r1 = r8.f11534b
            gd r2 = r1.f9606y
            r2.noteStateNotSaved()
            gd r2 = r1.f9606y
            r4 = 1
            r2.mo6442c((boolean) r4)
            r1.f9587f = r3
            r1.f9571J = r10
            r1.mo2668v()
            boolean r2 = r1.f9571J
            if (r2 == 0) goto L_0x07cd
            ab r2 = r1.f9583V
            u r3 = p000.C0546u.ON_RESUME
            r2.mo62a((p000.C0546u) r3)
            android.view.View r2 = r1.f9573L
            if (r2 == 0) goto L_0x07b9
            hh r2 = r1.f9584W
            u r3 = p000.C0546u.ON_RESUME
            r2.mo7435a(r3)
        L_0x07b9:
            gd r1 = r1.f9606y
            r1.mo6450h()
            fw r1 = r8.f11533a
            fh r2 = r8.f11534b
            r1.mo6235b(r2, r10)
            fh r1 = r8.f11534b
            r1.f9588g = r15
            r1.f9589h = r15
            goto L_0x07e7
        L_0x07cd:
            hs r2 = new hs
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r14)
            r3.append(r1)
            java.lang.String r1 = " did not call through to super.onResume()"
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        L_0x07e7:
            int r1 = r7.f9587f
            if (r1 == r13) goto L_0x0815
            boolean r1 = m10054a((int) r12)
            if (r1 == 0) goto L_0x0813
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "moveToState: Fragment state for "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r2 = " not updated inline; expected state "
            r1.append(r2)
            r1.append(r13)
            java.lang.String r2 = " found "
            r1.append(r2)
            int r2 = r7.f9587f
            r1.append(r2)
            r1.toString()
        L_0x0813:
            r7.f9587f = r13
        L_0x0815:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0171gd.mo6423a(fh, int):void");
    }

    public void noteStateNotSaved() {
        this.f11002u = false;
        this.f11003v = false;
        for (C0147fh fhVar : this.f10982a.mo6826b()) {
            if (fhVar != null) {
                fhVar.f9606y.noteStateNotSaved();
            }
        }
    }

    /* renamed from: b */
    public final boolean mo6438b() {
        mo6442c(false);
        m10057d(true);
        C0147fh fhVar = this.f10999r;
        if (fhVar != null && fhVar.mo5659r().mo6438b()) {
            return true;
        }
        boolean a = mo6431a(this.f11006y, this.f11007z, -1, 0);
        if (a) {
            this.f10996o = true;
            try {
                m10053a(this.f11006y, this.f11007z);
            } finally {
                m10072q();
            }
        }
        m10066n();
        m10076s();
        this.f10982a.mo6821a();
        return a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo6431a(ArrayList arrayList, ArrayList arrayList2, int i, int i2) {
        int i3;
        ArrayList arrayList3 = this.f10983b;
        if (arrayList3 != null) {
            if (i >= 0 || i2 != 0) {
                if (i >= 0) {
                    int size = arrayList3.size() - 1;
                    while (i3 >= 0 && i != ((C0133eu) this.f10983b.get(i3)).f9019b) {
                        size = i3 - 1;
                    }
                    if (i3 >= 0) {
                        if (i2 != 0) {
                            i3--;
                            while (i3 >= 0 && i == ((C0133eu) this.f10983b.get(i3)).f9019b) {
                                i3--;
                            }
                        }
                    }
                } else {
                    i3 = -1;
                }
                if (i3 == this.f10983b.size() - 1) {
                    return false;
                }
                for (int size2 = this.f10983b.size() - 1; size2 > i3; size2--) {
                    arrayList.add(this.f10983b.remove(size2));
                    arrayList2.add(true);
                }
            } else {
                int size3 = arrayList3.size() - 1;
                if (size3 < 0) {
                    return false;
                }
                arrayList.add(this.f10983b.remove(size3));
                arrayList2.add(true);
            }
            return true;
        }
        return false;
    }

    /* renamed from: j */
    private final void m10062j(C0147fh fhVar) {
        if (m10054a(2)) {
            "remove: " + fhVar + " nesting=" + fhVar.f9603v;
        }
        boolean z = !fhVar.mo5650i();
        if (!fhVar.f9566E || z) {
            this.f10982a.mo6827b(fhVar);
            if (m10075r(fhVar)) {
                this.f11001t = true;
            }
            fhVar.f9598q = true;
            m10067n(fhVar);
        }
    }

    /* renamed from: a */
    private final void m10053a(ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() == arrayList2.size()) {
            int size = arrayList.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                if (!((C0133eu) arrayList.get(i)).f11659r) {
                    if (i2 != i) {
                        m10055b(arrayList, arrayList2, i2, i);
                    }
                    i2 = i + 1;
                    if (((Boolean) arrayList2.get(i)).booleanValue()) {
                        while (i2 < size && ((Boolean) arrayList2.get(i2)).booleanValue() && !((C0133eu) arrayList.get(i2)).f11659r) {
                            i2++;
                        }
                    }
                    m10055b(arrayList, arrayList2, i, i2);
                    i = i2 - 1;
                }
                i++;
            }
            if (i2 != size) {
                m10055b(arrayList, arrayList2, i2, size);
                return;
            }
            return;
        }
        throw new IllegalStateException("Internal error with the back stack records");
    }

    /* renamed from: g */
    private final void m10059g(C0147fh fhVar) {
        if (!mo6443c() && this.f10994m.f11331c.remove(fhVar.f9591j) != null && m10054a(2)) {
            "Updating retained Fragments: Removed " + fhVar;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo6421a(Parcelable parcelable) {
        C0178gk gkVar;
        if (parcelable != null) {
            C0173gf gfVar = (C0173gf) parcelable;
            if (gfVar.f11148a != null) {
                this.f10982a.f11562b.clear();
                ArrayList arrayList = gfVar.f11148a;
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    C0177gj gjVar = (C0177gj) arrayList.get(i);
                    if (gjVar != null) {
                        C0147fh fhVar = (C0147fh) this.f10994m.f11331c.get(gjVar.f11452b);
                        if (fhVar != null) {
                            if (m10054a(2)) {
                                "restoreSaveState: re-attaching retained " + fhVar;
                            }
                            gkVar = new C0178gk(this.f10989h, fhVar, gjVar);
                        } else {
                            gkVar = new C0178gk(this.f10989h, this.f10991j.f10593c.getClassLoader(), mo6455m(), gjVar);
                        }
                        C0147fh fhVar2 = gkVar.f11534b;
                        fhVar2.f9604w = this;
                        if (m10054a(2)) {
                            "restoreSaveState: active (" + fhVar2.f9591j + "): " + fhVar2;
                        }
                        gkVar.mo6792a(this.f10991j.f10593c.getClassLoader());
                        this.f10982a.mo6823a(gkVar);
                    }
                }
                for (C0147fh fhVar3 : this.f10994m.f11331c.values()) {
                    if (!this.f10982a.mo6824a(fhVar3.f9591j)) {
                        if (m10054a(2)) {
                            "Discarding retained Fragment " + fhVar3 + " that was not found in the set of active Fragments " + gfVar.f11148a;
                        }
                        mo6423a(fhVar3, 1);
                        fhVar3.f9598q = true;
                        mo6423a(fhVar3, -1);
                    }
                }
                C0179gl glVar = this.f10982a;
                ArrayList arrayList2 = gfVar.f11149b;
                glVar.f11561a.clear();
                if (arrayList2 != null) {
                    int size2 = arrayList2.size();
                    int i2 = 0;
                    while (i2 < size2) {
                        String str = (String) arrayList2.get(i2);
                        C0147fh b = glVar.mo6825b(str);
                        if (b != null) {
                            if (m10054a(2)) {
                                "restoreSaveState: added (" + str + "): " + b;
                            }
                            glVar.mo6822a(b);
                            i2++;
                        } else {
                            throw new IllegalStateException("No instantiated fragment for (" + str + ")");
                        }
                    }
                }
                C0135ew[] ewVarArr = gfVar.f11150c;
                if (ewVarArr != null) {
                    this.f10983b = new ArrayList(ewVarArr.length);
                    int i3 = 0;
                    while (true) {
                        C0135ew[] ewVarArr2 = gfVar.f11150c;
                        if (i3 >= ewVarArr2.length) {
                            break;
                        }
                        C0135ew ewVar = ewVarArr2[i3];
                        C0133eu euVar = new C0133eu(this);
                        int i4 = 0;
                        int i5 = 0;
                        while (i4 < ewVar.f9109a.length) {
                            C0180gm gmVar = new C0180gm();
                            int i6 = i4 + 1;
                            gmVar.f11606a = ewVar.f9109a[i4];
                            if (m10054a(2)) {
                                "Instantiate " + euVar + " op #" + i5 + " base fragment #" + ewVar.f9109a[i6];
                            }
                            String str2 = (String) ewVar.f9110b.get(i5);
                            if (str2 != null) {
                                gmVar.f11607b = mo6440c(str2);
                            } else {
                                gmVar.f11607b = null;
                            }
                            gmVar.f11612g = C0573v.values()[ewVar.f9111c[i5]];
                            gmVar.f11613h = C0573v.values()[ewVar.f9112d[i5]];
                            int[] iArr = ewVar.f9109a;
                            int i7 = i6 + 1;
                            int i8 = iArr[i6];
                            gmVar.f11608c = i8;
                            int i9 = i7 + 1;
                            int i10 = iArr[i7];
                            gmVar.f11609d = i10;
                            int i11 = i9 + 1;
                            int i12 = iArr[i9];
                            gmVar.f11610e = i12;
                            int i13 = iArr[i11];
                            gmVar.f11611f = i13;
                            euVar.f11645d = i8;
                            euVar.f11646e = i10;
                            euVar.f11647f = i12;
                            euVar.f11648g = i13;
                            euVar.mo6852a(gmVar);
                            i5++;
                            i4 = i11 + 1;
                        }
                        euVar.f11649h = ewVar.f9113e;
                        euVar.f11652k = ewVar.f9114f;
                        euVar.f9019b = ewVar.f9115g;
                        euVar.f11650i = true;
                        euVar.f11653l = ewVar.f9116h;
                        euVar.f11654m = ewVar.f9117i;
                        euVar.f11655n = ewVar.f9118j;
                        euVar.f11656o = ewVar.f9119k;
                        euVar.f11657p = ewVar.f9120l;
                        euVar.f11658q = ewVar.f9121m;
                        euVar.f11659r = ewVar.f9122n;
                        euVar.mo5245a(1);
                        if (m10054a(2)) {
                            "restoreAllState: back stack #" + i3 + " (index " + euVar.f9019b + "): " + euVar;
                            PrintWriter printWriter = new PrintWriter(new C0295ks());
                            euVar.mo5248a("  ", printWriter, false);
                            printWriter.close();
                        }
                        this.f10983b.add(euVar);
                        i3++;
                    }
                } else {
                    this.f10983b = null;
                }
                this.f10987f.set(gfVar.f11151d);
                String str3 = gfVar.f11152e;
                if (str3 != null) {
                    C0147fh c = mo6440c(str3);
                    this.f10999r = c;
                    m10073q(c);
                }
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: ew[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: ew[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: ew[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: ew[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v11, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v13, resolved type: ew[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v14, resolved type: ew[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v15, resolved type: ew[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v16, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v17, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v18, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v19, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v20, resolved type: ew[]} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Parcelable mo6444d() {
        /*
            r11 = this;
            r11.m10074r()
            r0 = 1
            r11.mo6442c((boolean) r0)
            r11.f11002u = r0
            gl r0 = r11.f10982a
            java.util.ArrayList r1 = new java.util.ArrayList
            java.util.HashMap r2 = r0.f11562b
            int r2 = r2.size()
            r1.<init>(r2)
            java.util.HashMap r0 = r0.f11562b
            java.util.Collection r0 = r0.values()
            java.util.Iterator r0 = r0.iterator()
        L_0x0020:
            boolean r2 = r0.hasNext()
            r3 = 0
            r4 = 2
            r5 = 0
            if (r2 == 0) goto L_0x0101
            java.lang.Object r2 = r0.next()
            gk r2 = (p000.C0178gk) r2
            if (r2 == 0) goto L_0x0020
            fh r6 = r2.f11534b
            gj r7 = new gj
            r7.<init>((p000.C0147fh) r6)
            fh r8 = r2.f11534b
            int r9 = r8.f9587f
            if (r9 < 0) goto L_0x00d8
            android.os.Bundle r9 = r7.f11463m
            if (r9 != 0) goto L_0x00d8
            android.os.Bundle r8 = new android.os.Bundle
            r8.<init>()
            fh r9 = r2.f11534b
            r9.mo168e(r8)
            aen r10 = r9.f9586Y
            r10.mo251b(r8)
            gd r9 = r9.f9606y
            android.os.Parcelable r9 = r9.mo6444d()
            if (r9 == 0) goto L_0x005e
            java.lang.String r10 = "android:support:fragments"
            r8.putParcelable(r10, r9)
        L_0x005e:
            fw r9 = r2.f11533a
            fh r10 = r2.f11534b
            r9.mo6238d(r10, r8, r3)
            boolean r3 = r8.isEmpty()
            if (r3 != 0) goto L_0x006d
            r5 = r8
            goto L_0x006f
        L_0x006d:
        L_0x006f:
            fh r3 = r2.f11534b
            android.view.View r3 = r3.f9573L
            if (r3 != 0) goto L_0x0076
            goto L_0x0079
        L_0x0076:
            r2.mo6790a()
        L_0x0079:
            fh r3 = r2.f11534b
            android.util.SparseArray r3 = r3.f9589h
            if (r3 != 0) goto L_0x0080
            goto L_0x0092
        L_0x0080:
            if (r5 == 0) goto L_0x0083
            goto L_0x0089
        L_0x0083:
            android.os.Bundle r3 = new android.os.Bundle
            r3.<init>()
            r5 = r3
        L_0x0089:
            fh r3 = r2.f11534b
            android.util.SparseArray r3 = r3.f9589h
            java.lang.String r8 = "android:view_state"
            r5.putSparseParcelableArray(r8, r3)
        L_0x0092:
            fh r3 = r2.f11534b
            boolean r3 = r3.f9575N
            if (r3 == 0) goto L_0x0099
            goto L_0x00ab
        L_0x0099:
            if (r5 == 0) goto L_0x009c
            goto L_0x00a2
        L_0x009c:
            android.os.Bundle r3 = new android.os.Bundle
            r3.<init>()
            r5 = r3
        L_0x00a2:
            fh r3 = r2.f11534b
            boolean r3 = r3.f9575N
            java.lang.String r8 = "android:user_visible_hint"
            r5.putBoolean(r8, r3)
        L_0x00ab:
            r7.f11463m = r5
            fh r3 = r2.f11534b
            java.lang.String r3 = r3.f9594m
            if (r3 == 0) goto L_0x00dc
            android.os.Bundle r3 = r7.f11463m
            if (r3 == 0) goto L_0x00b8
            goto L_0x00bf
        L_0x00b8:
            android.os.Bundle r3 = new android.os.Bundle
            r3.<init>()
            r7.f11463m = r3
        L_0x00bf:
            android.os.Bundle r3 = r7.f11463m
            fh r5 = r2.f11534b
            java.lang.String r5 = r5.f9594m
            java.lang.String r8 = "android:target_state"
            r3.putString(r8, r5)
            fh r2 = r2.f11534b
            int r2 = r2.f9595n
            if (r2 == 0) goto L_0x00dc
            android.os.Bundle r3 = r7.f11463m
            java.lang.String r5 = "android:target_req_state"
            r3.putInt(r5, r2)
            goto L_0x00dc
        L_0x00d8:
            android.os.Bundle r2 = r8.f9588g
            r7.f11463m = r2
        L_0x00dc:
            r1.add(r7)
            boolean r2 = m10054a((int) r4)
            if (r2 == 0) goto L_0x0020
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Saved state of "
            r2.append(r3)
            r2.append(r6)
            java.lang.String r3 = ": "
            r2.append(r3)
            android.os.Bundle r3 = r7.f11463m
            r2.append(r3)
            r2.toString()
            goto L_0x0020
        L_0x0101:
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x01c0
            gl r0 = r11.f10982a
            java.util.ArrayList r2 = r0.f11561a
            monitor-enter(r2)
            java.util.ArrayList r6 = r0.f11561a     // Catch:{ all -> 0x01bd }
            boolean r6 = r6.isEmpty()     // Catch:{ all -> 0x01bd }
            if (r6 != 0) goto L_0x0159
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x01bd }
            java.util.ArrayList r7 = r0.f11561a     // Catch:{ all -> 0x01bd }
            int r7 = r7.size()     // Catch:{ all -> 0x01bd }
            r6.<init>(r7)     // Catch:{ all -> 0x01bd }
            java.util.ArrayList r0 = r0.f11561a     // Catch:{ all -> 0x01bd }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x01bd }
        L_0x0125:
            boolean r7 = r0.hasNext()     // Catch:{ all -> 0x01bd }
            if (r7 == 0) goto L_0x0157
            java.lang.Object r7 = r0.next()     // Catch:{ all -> 0x01bd }
            fh r7 = (p000.C0147fh) r7     // Catch:{ all -> 0x01bd }
            java.lang.String r8 = r7.f9591j     // Catch:{ all -> 0x01bd }
            r6.add(r8)     // Catch:{ all -> 0x01bd }
            boolean r8 = m10054a((int) r4)     // Catch:{ all -> 0x01bd }
            if (r8 == 0) goto L_0x0125
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x01bd }
            r8.<init>()     // Catch:{ all -> 0x01bd }
            java.lang.String r9 = "saveAllState: adding fragment ("
            r8.append(r9)     // Catch:{ all -> 0x01bd }
            java.lang.String r9 = r7.f9591j     // Catch:{ all -> 0x01bd }
            r8.append(r9)     // Catch:{ all -> 0x01bd }
            java.lang.String r9 = "): "
            r8.append(r9)     // Catch:{ all -> 0x01bd }
            r8.append(r7)     // Catch:{ all -> 0x01bd }
            r8.toString()     // Catch:{ all -> 0x01bd }
            goto L_0x0125
        L_0x0157:
            monitor-exit(r2)     // Catch:{ all -> 0x01bd }
            goto L_0x015c
        L_0x0159:
            monitor-exit(r2)     // Catch:{ all -> 0x01bd }
            r6 = r5
        L_0x015c:
            java.util.ArrayList r0 = r11.f10983b
            if (r0 != 0) goto L_0x0161
        L_0x0160:
            goto L_0x01a1
        L_0x0161:
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x0160
            ew[] r5 = new p000.C0135ew[r0]
        L_0x0169:
            if (r3 >= r0) goto L_0x0160
            ew r2 = new ew
            java.util.ArrayList r7 = r11.f10983b
            java.lang.Object r7 = r7.get(r3)
            eu r7 = (p000.C0133eu) r7
            r2.<init>((p000.C0133eu) r7)
            r5[r3] = r2
            boolean r2 = m10054a((int) r4)
            if (r2 == 0) goto L_0x019e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r7 = "saveAllState: adding back stack #"
            r2.append(r7)
            r2.append(r3)
            java.lang.String r7 = ": "
            r2.append(r7)
            java.util.ArrayList r7 = r11.f10983b
            java.lang.Object r7 = r7.get(r3)
            r2.append(r7)
            r2.toString()
        L_0x019e:
            int r3 = r3 + 1
            goto L_0x0169
        L_0x01a1:
            gf r0 = new gf
            r0.<init>()
            r0.f11148a = r1
            r0.f11149b = r6
            r0.f11150c = r5
            java.util.concurrent.atomic.AtomicInteger r1 = r11.f10987f
            int r1 = r1.get()
            r0.f11151d = r1
            fh r1 = r11.f10999r
            if (r1 == 0) goto L_0x01bc
            java.lang.String r1 = r1.f9591j
            r0.f11152e = r1
        L_0x01bc:
            return r0
        L_0x01bd:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x01bd }
            throw r0
        L_0x01c0:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0171gd.mo6444d():android.os.Parcelable");
    }

    /* renamed from: a */
    private final void m10052a(C0147fh fhVar, boolean z) {
        ViewGroup o = m10068o(fhVar);
        if (o != null && (o instanceof C0157fr)) {
            ((C0157fr) o).f10299a = !z;
        }
    }

    /* renamed from: a */
    private final void m10051a(C0147fh fhVar, C0573v vVar) {
        if (!fhVar.equals(mo6440c(fhVar.f9591j)) || !(fhVar.f9605x == null || fhVar.f9604w == this)) {
            throw new IllegalArgumentException("Fragment " + fhVar + " is not an active fragment of FragmentManager " + this);
        }
        fhVar.f9582U = vVar;
    }

    /* renamed from: p */
    private final void m10071p(C0147fh fhVar) {
        if (fhVar != null && (!fhVar.equals(mo6440c(fhVar.f9591j)) || !(fhVar.f9605x == null || fhVar.f9604w == this))) {
            throw new IllegalArgumentException("Fragment " + fhVar + " is not an active fragment of FragmentManager " + this);
        }
        C0147fh fhVar2 = this.f10999r;
        this.f10999r = fhVar;
        m10073q(fhVar2);
        m10073q(this.f10999r);
    }

    /* renamed from: n */
    private final void m10067n(C0147fh fhVar) {
        ViewGroup o = m10068o(fhVar);
        if (o != null) {
            if (o.getTag(R.id.visible_removing_fragment_view_tag) == null) {
                o.setTag(R.id.visible_removing_fragment_view_tag, fhVar);
            }
            ((C0147fh) o.getTag(R.id.visible_removing_fragment_view_tag)).mo5642b(fhVar.mo5623D());
        }
    }

    /* renamed from: s */
    private static final void m10077s(C0147fh fhVar) {
        if (m10054a(2)) {
            "show: " + fhVar;
        }
        if (fhVar.f9565D) {
            fhVar.f9565D = false;
            fhVar.f9578Q = !fhVar.f9578Q;
        }
    }

    /* renamed from: o */
    private final void m10069o() {
        List c = this.f10982a.mo6828c();
        int size = c.size();
        for (int i = 0; i < size; i++) {
            C0147fh fhVar = (C0147fh) c.get(i);
            if (fhVar != null && fhVar.f9574M) {
                if (!this.f10996o) {
                    fhVar.f9574M = false;
                    mo6423a(fhVar, this.f10990i);
                } else {
                    this.f11005x = true;
                }
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        C0147fh fhVar = this.f10993l;
        if (fhVar != null) {
            sb.append(fhVar.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.f10993l)));
            sb.append("}");
        } else {
            sb.append(this.f10991j.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.f10991j)));
            sb.append("}");
        }
        sb.append("}}");
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        if (r1 == null) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        if (r1.size() <= 0) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0026, code lost:
        if (m10058f(r4.f10993l) == false) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002a, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002b, code lost:
        r0.f8a = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        r0 = r4.f10986e;
        r1 = r4.f10983b;
     */
    /* renamed from: n */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m10066n() {
        /*
            r4 = this;
            java.util.ArrayList r0 = r4.f10995n
            monitor-enter(r0)
            java.util.ArrayList r1 = r4.f10995n     // Catch:{ all -> 0x002e }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x002e }
            r2 = 1
            if (r1 != 0) goto L_0x0012
            aad r1 = r4.f10986e     // Catch:{ all -> 0x002e }
            r1.f8a = r2     // Catch:{ all -> 0x002e }
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            return
        L_0x0012:
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            aad r0 = r4.f10986e
            java.util.ArrayList r1 = r4.f10983b
            r3 = 0
            if (r1 == 0) goto L_0x002a
            int r1 = r1.size()
            if (r1 <= 0) goto L_0x002a
            fh r1 = r4.f10993l
            boolean r1 = r4.m10058f(r1)
            if (r1 == 0) goto L_0x0029
            goto L_0x002b
        L_0x0029:
        L_0x002a:
            r2 = 0
        L_0x002b:
            r0.f8a = r2
            return
        L_0x002e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0171gd.m10066n():void");
    }

    public C0171gd(byte[] bArr) {
    }
}
