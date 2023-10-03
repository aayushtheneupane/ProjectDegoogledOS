package com.android.messaging.mmslib.p039a;

/* renamed from: com.android.messaging.mmslib.a.g */
public class C0977g extends C0976f {
    private C0980j mMessageBody;

    public C0977g() {
    }

    /* renamed from: a */
    public void mo6674a(C0980j jVar) {
        this.mMessageBody = jVar;
    }

    /* renamed from: c */
    public void mo6675c(C0975e eVar) {
        this.f1404Zl.mo6718b(eVar, 150);
    }

    public C0980j getBody() {
        return this.mMessageBody;
    }

    public C0975e getSubject() {
        return this.f1404Zl.mo6710C(150);
    }

    public void setDate(long j) {
        this.f1404Zl.mo6715a(j, 133);
    }

    public void setPriority(int i) {
        this.f1404Zl.mo6720e(i, 143);
    }

    public C0977g(C0987q qVar, C0980j jVar) {
        super(qVar);
        this.mMessageBody = jVar;
    }
}
