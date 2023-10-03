package com.android.messaging.p041ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/* renamed from: com.android.messaging.ui.B */
public class C1033B extends BaseAdapter {
    private int mCount = 0;
    private final C1393z mObserver = new C1393z(this, (C1391y) null);
    private int mSize = 0;

    /* renamed from: ul */
    private C1031A[] f1597ul = new C1031A[2];

    /* renamed from: vl */
    private boolean f1598vl = true;

    public C1033B(Context context) {
    }

    /* renamed from: xn */
    private void m2503xn() {
        if (!this.f1598vl) {
            this.mCount = 0;
            for (int i = 0; i < this.mSize; i++) {
                this.mCount = this.f1597ul[i].getCount() + this.mCount;
            }
        }
    }

    /* renamed from: a */
    public void mo6888a(C1031A a) {
        int i = this.mSize;
        C1031A[] aArr = this.f1597ul;
        if (i >= aArr.length) {
            C1031A[] aArr2 = new C1031A[(i + 2)];
            System.arraycopy(aArr, 0, aArr2, 0, i);
            this.f1597ul = aArr2;
        }
        C1031A[] aArr3 = this.f1597ul;
        int i2 = this.mSize;
        this.mSize = i2 + 1;
        aArr3[i2] = a;
        a.mAdapter.registerDataSetObserver(this.mObserver);
        this.f1598vl = false;
        notifyDataSetChanged();
    }

    public int getCount() {
        m2503xn();
        return this.mCount;
    }

    public Object getItem(int i) {
        m2503xn();
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.mSize) {
            int count = this.f1597ul[i2].getCount() + i3;
            if (i < i3 || i >= count) {
                i2++;
                i3 = count;
            } else {
                int i4 = i - i3;
                C1031A a = this.f1597ul[i2];
                if (!a.f1559DF || i4 != 0 || (a.getCount() <= 0 && !a.f1558CF)) {
                    return this.f1597ul[i2].mAdapter.getItem(i4);
                }
                return null;
            }
        }
        return null;
    }

    public long getItemId(int i) {
        m2503xn();
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.mSize) {
            int count = this.f1597ul[i2].getCount() + i3;
            if (i < i3 || i >= count) {
                i2++;
                i3 = count;
            } else {
                int i4 = i - i3;
                C1031A a = this.f1597ul[i2];
                if (!a.f1559DF || i4 != 0 || (a.getCount() <= 0 && !a.f1558CF)) {
                    return this.f1597ul[i2].mAdapter.getItemId(i4);
                }
                return 0;
            }
        }
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        m2503xn();
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.mSize) {
            C1031A a = this.f1597ul[i2];
            int count = a.getCount() + i3;
            if (i < i3 || i >= count) {
                i2++;
                i3 = count;
            } else {
                int i4 = i - i3;
                if (a.f1559DF && (a.getCount() > 0 || a.f1558CF)) {
                    i4--;
                }
                if (i4 == -1) {
                    view2 = a.mo6855a(view, viewGroup);
                } else {
                    view2 = a.mAdapter.getView(i4, view, viewGroup);
                }
                if (view2 != null) {
                    return view2;
                }
                throw new NullPointerException("View should not be null, partition: " + i2 + " position: " + i4);
            }
        }
        throw new ArrayIndexOutOfBoundsException(i);
    }

    public boolean isEnabled(int i) {
        m2503xn();
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.mSize) {
            int count = this.f1597ul[i2].getCount() + i3;
            if (i < i3 || i >= count) {
                i2++;
                i3 = count;
            } else {
                int i4 = i - i3;
                C1031A a = this.f1597ul[i2];
                if (!a.f1559DF || i4 != 0 || (a.getCount() <= 0 && !a.f1558CF)) {
                    return true;
                }
                return false;
            }
        }
        return true;
    }
}
