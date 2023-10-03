package androidx.recyclerview.widget;

/* renamed from: androidx.recyclerview.widget.Ca */
class C0526Ca {

    /* renamed from: Et */
    int f509Et = 0;

    /* renamed from: Ft */
    int f510Ft;

    /* renamed from: Gt */
    int f511Gt;

    /* renamed from: Ht */
    int f512Ht;

    /* renamed from: It */
    int f513It;

    C0526Ca() {
    }

    /* access modifiers changed from: package-private */
    public int compare(int i, int i2) {
        if (i > i2) {
            return 1;
        }
        return i == i2 ? 2 : 4;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: jd */
    public boolean mo4639jd() {
        int i = this.f509Et;
        if ((i & 7) != 0 && (i & (compare(this.f512Ht, this.f510Ft) << 0)) == 0) {
            return false;
        }
        int i2 = this.f509Et;
        if ((i2 & 112) != 0 && (i2 & (compare(this.f512Ht, this.f511Gt) << 4)) == 0) {
            return false;
        }
        int i3 = this.f509Et;
        if ((i3 & 1792) != 0 && (i3 & (compare(this.f513It, this.f510Ft) << 8)) == 0) {
            return false;
        }
        int i4 = this.f509Et;
        if ((i4 & 28672) == 0 || ((compare(this.f513It, this.f511Gt) << 12) & i4) != 0) {
            return true;
        }
        return false;
    }
}
