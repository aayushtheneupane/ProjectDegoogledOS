package p000;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* renamed from: ene */
/* compiled from: PG */
public final class ene implements enu, elz {

    /* renamed from: a */
    public final Lock f8628a;

    /* renamed from: b */
    public final Condition f8629b;

    /* renamed from: c */
    public final Context f8630c;

    /* renamed from: d */
    public final ejx f8631d;

    /* renamed from: e */
    public final end f8632e;

    /* renamed from: f */
    public final Map f8633f;

    /* renamed from: g */
    public final Map f8634g = new HashMap();

    /* renamed from: h */
    public final epk f8635h;

    /* renamed from: i */
    public final Map f8636i;

    /* renamed from: j */
    public volatile enb f8637j;

    /* renamed from: k */
    public int f8638k;

    /* renamed from: l */
    public final ena f8639l;

    /* renamed from: m */
    public final ent f8640m;

    /* renamed from: n */
    public final eov f8641n;

    public ene(Context context, ena ena, Lock lock, Looper looper, ejx ejx, Map map, epk epk, Map map2, eov eov, ArrayList arrayList, ent ent, byte[] bArr, byte[] bArr2) {
        this.f8630c = context;
        this.f8628a = lock;
        this.f8631d = ejx;
        this.f8633f = map;
        this.f8635h = epk;
        this.f8636i = map2;
        this.f8641n = eov;
        this.f8639l = ena;
        this.f8640m = ent;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((ely) arrayList.get(i)).f8532b = this;
        }
        this.f8632e = new end(this, looper);
        this.f8629b = lock.newCondition();
        this.f8637j = new emw(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final void mo5037d() {
        this.f8628a.lock();
        try {
            this.f8637j = new emw(this);
            this.f8637j.mo5010a();
            this.f8629b.signalAll();
        } finally {
            this.f8628a.unlock();
        }
    }

    /* renamed from: a */
    public final void mo4999a() {
        this.f8637j.mo5015b();
    }

    /* renamed from: b */
    public final void mo5003b() {
        this.f8637j.mo5016c();
        this.f8634g.clear();
    }

    /* renamed from: a */
    public final void mo5001a(String str, PrintWriter printWriter) {
        String concat = String.valueOf(str).concat("  ");
        printWriter.append(str).append("mState=").println(this.f8637j);
        for (ekn ekn : this.f8636i.keySet()) {
            printWriter.append(str).append(ekn.f8476a).println(":");
            ((ekm) this.f8633f.get(ekn.mo4940a())).mo4930a(concat, printWriter);
        }
    }

    /* renamed from: a */
    public final elq mo4998a(elq elq) {
        elq.mo3512c();
        return this.f8637j.mo5009a(elq);
    }

    /* renamed from: b */
    public final elq mo5002b(elq elq) {
        elq.mo3512c();
        return this.f8637j.mo5014b(elq);
    }

    /* renamed from: c */
    public final boolean mo5004c() {
        return this.f8637j instanceof emk;
    }

    /* renamed from: a */
    public final void mo4993a(Bundle bundle) {
        this.f8628a.lock();
        try {
            this.f8637j.mo5012a(bundle);
        } finally {
            this.f8628a.unlock();
        }
    }

    /* renamed from: a */
    public final void mo4992a(int i) {
        this.f8628a.lock();
        try {
            this.f8637j.mo5011a(i);
        } finally {
            this.f8628a.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo5036a(enc enc) {
        this.f8632e.sendMessage(this.f8632e.obtainMessage(1, enc));
    }
}
