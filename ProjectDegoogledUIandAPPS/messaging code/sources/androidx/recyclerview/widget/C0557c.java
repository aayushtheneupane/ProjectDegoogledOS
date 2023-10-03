package androidx.recyclerview.widget;

/* renamed from: androidx.recyclerview.widget.c */
class C0557c {
    long mData = 0;
    C0557c mNext;

    C0557c() {
    }

    /* renamed from: In */
    private void m818In() {
        if (this.mNext == null) {
            this.mNext = new C0557c();
        }
    }

    /* access modifiers changed from: package-private */
    public void clear(int i) {
        if (i >= 64) {
            C0557c cVar = this.mNext;
            if (cVar != null) {
                cVar.clear(i - 64);
                return;
            }
            return;
        }
        this.mData &= ~(1 << i);
    }

    /* access modifiers changed from: package-private */
    public int countOnesBefore(int i) {
        C0557c cVar = this.mNext;
        if (cVar == null) {
            if (i >= 64) {
                return Long.bitCount(this.mData);
            }
            return Long.bitCount(((1 << i) - 1) & this.mData);
        } else if (i < 64) {
            return Long.bitCount(((1 << i) - 1) & this.mData);
        } else {
            return Long.bitCount(this.mData) + cVar.countOnesBefore(i - 64);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean get(int i) {
        if (i >= 64) {
            m818In();
            return this.mNext.get(i - 64);
        }
        return ((1 << i) & this.mData) != 0;
    }

    /* access modifiers changed from: package-private */
    public void insert(int i, boolean z) {
        if (i >= 64) {
            m818In();
            this.mNext.insert(i - 64, z);
            return;
        }
        boolean z2 = (this.mData & Long.MIN_VALUE) != 0;
        long j = (1 << i) - 1;
        long j2 = this.mData;
        this.mData = ((j2 & (~j)) << 1) | (j2 & j);
        if (z) {
            set(i);
        } else {
            clear(i);
        }
        if (z2 || this.mNext != null) {
            m818In();
            this.mNext.insert(0, z2);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean remove(int i) {
        if (i >= 64) {
            m818In();
            return this.mNext.remove(i - 64);
        }
        long j = 1 << i;
        boolean z = (this.mData & j) != 0;
        this.mData &= ~j;
        long j2 = j - 1;
        long j3 = this.mData;
        this.mData = Long.rotateRight(j3 & (~j2), 1) | (j3 & j2);
        C0557c cVar = this.mNext;
        if (cVar != null) {
            if (cVar.get(0)) {
                set(63);
            }
            this.mNext.remove(0);
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        this.mData = 0;
        C0557c cVar = this.mNext;
        if (cVar != null) {
            cVar.reset();
        }
    }

    /* access modifiers changed from: package-private */
    public void set(int i) {
        if (i >= 64) {
            m818In();
            this.mNext.set(i - 64);
            return;
        }
        this.mData |= 1 << i;
    }

    public String toString() {
        if (this.mNext == null) {
            return Long.toBinaryString(this.mData);
        }
        return this.mNext.toString() + "xx" + Long.toBinaryString(this.mData);
    }
}
