package com.google.common.base;

/* renamed from: com.google.common.base.m */
class C1538m extends C1540o {
    C1538m(String str) {
        super(str);
    }

    /* renamed from: a */
    public C1545t mo8550a(C1545t tVar) {
        if (tVar != null) {
            return tVar;
        }
        throw new NullPointerException();
    }

    /* renamed from: c */
    public int mo8554c(CharSequence charSequence) {
        if (charSequence != null) {
            return -1;
        }
        throw new NullPointerException();
    }

    /* renamed from: d */
    public boolean mo8551d(char c) {
        return false;
    }

    /* renamed from: d */
    public boolean mo8555d(CharSequence charSequence) {
        return charSequence.length() == 0;
    }

    /* renamed from: e */
    public boolean mo8556e(CharSequence charSequence) {
        if (charSequence != null) {
            return true;
        }
        throw new NullPointerException();
    }

    public C1545t negate() {
        return C1545t.ANY;
    }
}
