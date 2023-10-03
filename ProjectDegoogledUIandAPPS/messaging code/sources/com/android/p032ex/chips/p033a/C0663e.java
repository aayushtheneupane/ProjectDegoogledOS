package com.android.p032ex.chips.p033a;

import android.text.TextUtils;
import com.android.p032ex.chips.C0699ra;

/* renamed from: com.android.ex.chips.a.e */
class C0663e implements C0659a {

    /* renamed from: Cv */
    private final long f772Cv;

    /* renamed from: Jv */
    private final String f773Jv;

    /* renamed from: Kv */
    private final C0699ra f774Kv;

    /* renamed from: Lv */
    private CharSequence f775Lv;

    /* renamed from: Rj */
    private final long f776Rj;
    private final CharSequence mDisplay;
    private boolean mSelected = false;
    private final CharSequence mValue;

    /* renamed from: ql */
    private final Long f777ql;

    public C0663e(C0699ra raVar) {
        this.mDisplay = raVar.getDisplayName();
        this.mValue = raVar.getDestination().trim();
        this.f776Rj = raVar.getContactId();
        this.f777ql = raVar.mo5649R();
        this.f773Jv = raVar.mo5659m();
        this.f772Cv = raVar.mo5651ga();
        this.f774Kv = raVar;
    }

    /* renamed from: R */
    public Long mo5481R() {
        return this.f777ql;
    }

    /* renamed from: a */
    public void mo5482a(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f775Lv = str;
        } else {
            this.f775Lv = str.trim();
        }
    }

    /* renamed from: ga */
    public long mo5483ga() {
        return this.f772Cv;
    }

    public long getContactId() {
        return this.f776Rj;
    }

    public C0699ra getEntry() {
        return this.f774Kv;
    }

    public CharSequence getOriginalText() {
        return !TextUtils.isEmpty(this.f775Lv) ? this.f775Lv : this.f774Kv.getDestination();
    }

    public CharSequence getValue() {
        return this.mValue;
    }

    public boolean isSelected() {
        return this.mSelected;
    }

    /* renamed from: m */
    public String mo5489m() {
        return this.f773Jv;
    }

    public String toString() {
        return this.mDisplay + " <" + this.mValue + ">";
    }
}
