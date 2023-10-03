package com.android.messaging.datamodel.action;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.messaging.datamodel.C0947h;
import java.util.LinkedList;
import java.util.List;

public abstract class Action implements Parcelable {

    /* renamed from: Qy */
    private static long f1055Qy = (System.currentTimeMillis() * 1000);
    private static final Object sLock = new Object();

    /* renamed from: Ny */
    public final String f1056Ny;

    /* renamed from: Oy */
    protected Bundle f1057Oy;

    /* renamed from: Py */
    private final List f1058Py;

    protected Action(String str) {
        this.f1058Py = new LinkedList();
        this.f1056Ny = str;
        this.f1057Oy = new Bundle();
    }

    /* renamed from: P */
    protected static String m1321P(String str) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
        }
        sb.append(":");
        sb.append(m1322we());
        return sb.toString();
    }

    /* renamed from: we */
    protected static long m1322we() {
        long j;
        synchronized (sLock) {
            j = f1055Qy + 1;
            f1055Qy = j;
        }
        return j;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ae */
    public final void mo5940Ae() {
        C0813c.m1472a(this, 2, 3);
    }

    /* access modifiers changed from: protected */
    /* renamed from: Be */
    public final void mo5941Be() {
        C0813c.m1472a(this, 1, 2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ce */
    public Object mo5942Ce() {
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: De */
    public final void mo5943De() {
        C0813c.m1473a(this, 0, mo5942Ce(), false);
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ee */
    public void mo5944Ee() {
        this.f1058Py.add(this);
    }

    /* renamed from: a */
    public void mo5945a(int i, long j) {
        C0947h.get().mo6594_d().mo6040a(this, i, j);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo5949b(Action action) {
        this.f1058Py.add(action);
    }

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public Object mo5951i(Bundle bundle) {
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public final void mo5952j(Bundle bundle) {
        C0813c.m1472a(this, 6, 7);
        C0813c.m1473a(this, 7, mo5951i(bundle), true);
    }

    /* access modifiers changed from: protected */
    /* renamed from: p */
    public final void mo5953p(Object obj) {
        boolean xe = mo5957xe();
        C0813c.m1477b(this, 3, xe, obj);
        if (!xe) {
            C0813c.m1473a(this, 3, obj, true);
        }
    }

    public void start() {
        C0947h.get().mo6594_d().mo6039a(this);
    }

    /* access modifiers changed from: protected */
    /* renamed from: ue */
    public Bundle mo5955ue() {
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        return null;
    }

    /* renamed from: xe */
    public boolean mo5957xe() {
        return !this.f1058Py.isEmpty();
    }

    /* access modifiers changed from: protected */
    /* renamed from: ye */
    public final void mo5958ye() {
        C0813c.m1472a(this, 5, 6);
    }

    /* access modifiers changed from: protected */
    /* renamed from: ze */
    public final void mo5959ze() {
        C0813c.m1472a(this, 4, 5);
    }

    /* renamed from: a */
    public void mo5948a(C0815e eVar) {
        eVar.mo6043b(this.f1058Py);
        this.f1058Py.clear();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo5947a(C0813c cVar) {
        C0813c.m1474a(this.f1056Ny, cVar);
        C0947h.get().mo6594_d().mo6039a(this);
    }

    protected Action() {
        this.f1058Py = new LinkedList();
        this.f1056Ny = m1321P(getClass().getSimpleName());
        this.f1057Oy = new Bundle();
    }

    /* renamed from: a */
    public void mo5946a(Parcel parcel, int i) {
        parcel.writeString(this.f1056Ny);
        parcel.writeBundle(this.f1057Oy);
    }

    public Action(Parcel parcel) {
        this.f1058Py = new LinkedList();
        this.f1056Ny = parcel.readString();
        this.f1057Oy = parcel.readBundle(Action.class.getClassLoader());
    }
}
