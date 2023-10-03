package com.android.messaging;

import android.content.Context;
import android.telephony.SmsManager;
import android.util.SparseArray;
import com.android.messaging.datamodel.C0761B;
import com.android.messaging.datamodel.C0774O;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.C0950k;
import com.android.messaging.datamodel.p038b.C0840C;
import com.android.messaging.datamodel.p038b.C0866f;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.sms.C1014j;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1447f;
import com.android.messaging.util.C1449g;
import com.android.messaging.util.C1451h;
import com.android.messaging.util.C1452ha;
import com.android.messaging.util.C1453i;
import com.android.messaging.util.C1455j;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1468pa;
import com.android.messaging.util.C1470qa;
import com.android.messaging.util.C1474sa;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.android.messaging.h */
class C0969h extends C0967f {

    /* renamed from: px */
    private static final Object f1383px = new Object();

    /* renamed from: qx */
    private static C1474sa f1384qx = null;

    /* renamed from: rx */
    private static final ConcurrentHashMap f1385rx = new ConcurrentHashMap();

    /* renamed from: ex */
    private C1449g f1386ex;

    /* renamed from: fx */
    private C1447f f1387fx;

    /* renamed from: gx */
    private C1455j f1388gx;

    /* renamed from: hx */
    private C1040Ea f1389hx;

    /* renamed from: ix */
    private C0761B f1390ix;

    /* renamed from: jx */
    private C0840C f1391jx;

    /* renamed from: kc */
    private Context f1392kc;

    /* renamed from: kx */
    private C0866f f1393kx;

    /* renamed from: lx */
    private C0774O f1394lx;
    /* access modifiers changed from: private */
    public BugleApplication mApplication;
    private C0947h mDataModel;

    /* renamed from: mx */
    private C1452ha f1395mx;

    /* renamed from: nx */
    private SparseArray f1396nx;

    /* renamed from: ox */
    private C1014j f1397ox;

    private C0969h() {
    }

    /* renamed from: Hd */
    public C1451h mo6645Hd() {
        return this.f1387fx;
    }

    /* renamed from: Id */
    public C1449g mo6646Id() {
        return this.f1386ex;
    }

    /* renamed from: Jd */
    public C0774O mo6647Jd() {
        return this.f1394lx;
    }

    /* renamed from: Kd */
    public C0866f mo6648Kd() {
        return this.f1393kx;
    }

    /* renamed from: Ld */
    public C0840C mo6649Ld() {
        return this.f1391jx;
    }

    /* renamed from: Md */
    public C1452ha mo6650Md() {
        return this.f1395mx;
    }

    /* renamed from: Nd */
    public C0761B mo6651Nd() {
        return this.f1390ix;
    }

    /* renamed from: Od */
    public C1040Ea mo6652Od() {
        return this.f1389hx;
    }

    /* renamed from: Pd */
    public C1451h mo6653Pd() {
        return this.f1388gx;
    }

    /* renamed from: Qd */
    public void mo6654Qd() {
    }

    /* renamed from: Rd */
    public void mo6655Rd() {
        if (!C0967f.sInitialized) {
            C0967f.sInitialized = true;
            this.mApplication.mo5856b(this);
            new C0968g(this).start();
        }
    }

    /* renamed from: Sd */
    public void mo6656Sd() {
        this.f1390ix.mo5865Sd();
    }

    /* renamed from: ga */
    public C1474sa mo6657ga(int i) {
        if (C1464na.m3759Zj()) {
            if (i == -1) {
                i = SmsManager.getDefaultSmsSubscriptionId();
            }
            if (i < 0) {
                C1430e.m3630w("MessagingApp", "PhoneUtils.getForLMR1(): invalid subId = " + i);
                i = -1;
            }
            C1474sa saVar = (C1474sa) f1385rx.get(Integer.valueOf(i));
            if (saVar != null) {
                return saVar;
            }
            C1468pa paVar = new C1468pa(i);
            f1385rx.putIfAbsent(Integer.valueOf(i), paVar);
            return paVar;
        }
        C1424b.m3592ia(i == -1);
        if (f1384qx == null) {
            synchronized (f1383px) {
                if (f1384qx == null) {
                    f1384qx = new C1470qa();
                }
            }
        }
        return f1384qx;
    }

    public Context getApplicationContext() {
        return this.f1392kc;
    }

    public C1014j getCarrierConfigValuesLoader() {
        return this.f1397ox;
    }

    public C0947h getDataModel() {
        return this.mDataModel;
    }

    /* renamed from: ha */
    public C1451h mo6661ha(int i) {
        int Na = C1474sa.getDefault().mo8201Na(i);
        C1453i iVar = (C1453i) this.f1396nx.get(Na);
        if (iVar == null) {
            synchronized (this) {
                iVar = (C1453i) this.f1396nx.get(Na);
                if (iVar == null) {
                    iVar = new C1453i(this.f1392kc, Na);
                    this.f1396nx.put(Na, iVar);
                }
            }
        }
        return iVar;
    }

    /* renamed from: a */
    public static C0967f m2192a(Context context, BugleApplication bugleApplication) {
        C1424b.m3592ia(!C0967f.sRegistered);
        C1424b.isNull(C0967f.get());
        C0969h hVar = new C0969h();
        C0967f.m2176c(hVar);
        C0967f.sRegistered = true;
        hVar.mApplication = bugleApplication;
        hVar.f1392kc = context;
        hVar.f1390ix = new C0761B();
        hVar.f1393kx = new C0866f();
        hVar.f1391jx = new C0840C();
        hVar.f1386ex = new C1449g();
        hVar.f1387fx = new C1447f(context);
        hVar.mDataModel = new C0950k(context);
        hVar.f1388gx = new C1455j(context);
        hVar.f1389hx = new C1040Ea();
        hVar.f1394lx = new C0774O();
        hVar.f1395mx = new C1452ha();
        hVar.f1396nx = new SparseArray();
        hVar.f1397ox = new C1014j(context);
        C1424b.m3589b(hVar.f1386ex);
        C1430e.m3616b(hVar.f1386ex);
        if (C1464na.m3752Sj()) {
            hVar.mo6655Rd();
        }
        return hVar;
    }
}
