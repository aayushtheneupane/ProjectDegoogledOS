package com.google.common.base;

/* renamed from: com.google.common.base.l */
class C1537l extends C1540o {
    C1537l(String str) {
        super(str);
    }

    /* renamed from: a */
    public C1545t mo8550a(C1545t tVar) {
        if (tVar != null) {
            return this;
        }
        throw new NullPointerException();
    }

    /* renamed from: c */
    public int mo8554c(CharSequence charSequence) {
        return charSequence.length() == 0 ? -1 : 0;
    }

    /* renamed from: d */
    public boolean mo8551d(char c) {
        return true;
    }

    /* renamed from: d */
    public boolean mo8555d(CharSequence charSequence) {
        if (charSequence != null) {
            return true;
        }
        throw new NullPointerException();
    }

    /* renamed from: e */
    public boolean mo8556e(CharSequence charSequence) {
        return charSequence.length() == 0;
    }

    public C1545t negate() {
        return C1545t.NONE;
    }
}
