package androidx.work.impl;

import android.content.Context;
import java.util.HashMap;

/* compiled from: PG */
public final class WorkDatabase_Impl extends WorkDatabase {

    /* renamed from: h */
    private volatile alh f1171h;

    /* renamed from: i */
    private volatile akk f1172i;

    /* renamed from: j */
    private volatile alt f1173j;

    /* renamed from: k */
    private volatile aks f1174k;

    /* renamed from: l */
    private volatile akx f1175l;

    /* renamed from: m */
    private volatile ala f1176m;

    /* renamed from: n */
    private volatile ako f1177n;

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final C0050bu mo1234b() {
        HashMap hashMap = new HashMap(0);
        new HashMap(0);
        return new C0050bu(this, hashMap, "Dependency", "WorkSpec", "WorkTag", "SystemIdInfo", "WorkName", "WorkProgress", "Preference");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final C0034be mo1233a(C0046bq bqVar) {
        C0030ba baVar = new C0030ba(bqVar, new aio(this), "cf029002fffdcadf079e8d0a1c9a70ac", "93205bef463538646dbc6d91bb3dbe19");
        C0031bb bbVar = new C0031bb(bqVar.f3338a);
        bbVar.f1984b = bqVar.f3339b;
        bbVar.f1985c = baVar;
        C0030ba baVar2 = bbVar.f1985c;
        if (baVar2 != null) {
            Context context = bbVar.f1983a;
            if (context != null) {
                C0032bc bcVar = new C0032bc(context, bbVar.f1984b, baVar2);
                return new C0042bm(bcVar.f2022a, bcVar.f2023b, bcVar.f2024c);
            }
            throw new IllegalArgumentException("Must set a non-null context to create the configuration.");
        }
        throw new IllegalArgumentException("Must set a callback to create the configuration.");
    }

    /* renamed from: k */
    public final akk mo1227k() {
        akk akk;
        if (this.f1172i != null) {
            return this.f1172i;
        }
        synchronized (this) {
            if (this.f1172i == null) {
                this.f1172i = new akm(this);
            }
            akk = this.f1172i;
        }
        return akk;
    }

    /* renamed from: p */
    public final ako mo1232p() {
        ako ako;
        if (this.f1177n != null) {
            return this.f1177n;
        }
        synchronized (this) {
            if (this.f1177n == null) {
                this.f1177n = new akq(this);
            }
            ako = this.f1177n;
        }
        return ako;
    }

    /* renamed from: m */
    public final aks mo1229m() {
        aks aks;
        if (this.f1174k != null) {
            return this.f1174k;
        }
        synchronized (this) {
            if (this.f1174k == null) {
                this.f1174k = new akv(this);
            }
            aks = this.f1174k;
        }
        return aks;
    }

    /* renamed from: n */
    public final akx mo1230n() {
        akx akx;
        if (this.f1175l != null) {
            return this.f1175l;
        }
        synchronized (this) {
            if (this.f1175l == null) {
                this.f1175l = new akz(this);
            }
            akx = this.f1175l;
        }
        return akx;
    }

    /* renamed from: o */
    public final ala mo1231o() {
        ala ala;
        if (this.f1176m != null) {
            return this.f1176m;
        }
        synchronized (this) {
            if (this.f1176m == null) {
                this.f1176m = new ale(this);
            }
            ala = this.f1176m;
        }
        return ala;
    }

    /* renamed from: j */
    public final alh mo1226j() {
        alh alh;
        if (this.f1171h != null) {
            return this.f1171h;
        }
        synchronized (this) {
            if (this.f1171h == null) {
                this.f1171h = new alr(this);
            }
            alh = this.f1171h;
        }
        return alh;
    }

    /* renamed from: l */
    public final alt mo1228l() {
        alt alt;
        if (this.f1173j != null) {
            return this.f1173j;
        }
        synchronized (this) {
            if (this.f1173j == null) {
                this.f1173j = new alv(this);
            }
            alt = this.f1173j;
        }
        return alt;
    }
}
