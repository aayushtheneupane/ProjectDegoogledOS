package com.android.messaging.datamodel.p037a;

import java.util.concurrent.atomic.AtomicLong;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.a.c */
public class C0783c extends C0784d {

    /* renamed from: Vz */
    private static AtomicLong f1051Vz = new AtomicLong(System.currentTimeMillis() * 1000);

    /* renamed from: Uz */
    private boolean f1052Uz;

    /* renamed from: gc */
    private String f1053gc;
    private C0781a mData;

    C0783c(Object obj) {
    }

    /* renamed from: a */
    public void mo5929a(C0781a aVar) {
        if (!isBound()) {
            StringBuilder Pa = C0632a.m1011Pa("not bound; wasBound = ");
            Pa.append(this.f1052Uz);
            throw new IllegalStateException(Pa.toString());
        } else if (aVar != this.mData) {
            throw new IllegalStateException("not bound to correct data " + aVar + " vs " + this.mData);
        }
    }

    /* renamed from: b */
    public void mo5930b(C0781a aVar) {
        if (this.mData != null || aVar.isBound()) {
            throw new IllegalStateException(C0632a.m1014a("already bound when binding to ", aVar));
        }
        this.f1053gc = Long.toHexString(f1051Vz.getAndIncrement());
        aVar.mo5925V(this.f1053gc);
        this.mData = aVar;
        this.f1052Uz = true;
    }

    public String getBindingId() {
        return this.f1053gc;
    }

    public C0781a getData() {
        mo5935yf();
        return this.mData;
    }

    public boolean isBound() {
        C0781a aVar = this.mData;
        return aVar != null && aVar.mo5926W(this.f1053gc);
    }

    public void unbind() {
        C0781a aVar = this.mData;
        if (aVar == null || !aVar.mo5926W(this.f1053gc)) {
            throw new IllegalStateException("not bound when unbind");
        }
        this.mData.mo5927X(this.f1053gc);
        this.mData = null;
        this.f1053gc = null;
    }

    /* renamed from: yf */
    public void mo5935yf() {
        if (!isBound()) {
            StringBuilder Pa = C0632a.m1011Pa("not bound; wasBound = ");
            Pa.append(this.f1052Uz);
            throw new IllegalStateException(Pa.toString());
        }
    }
}
