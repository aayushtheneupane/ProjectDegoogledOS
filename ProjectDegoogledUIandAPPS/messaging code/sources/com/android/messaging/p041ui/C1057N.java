package com.android.messaging.p041ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.C0616a;
import com.android.messaging.C0967f;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.N */
public class C1057N extends C0616a {

    /* renamed from: Ru */
    private final C1068V[] f1659Ru;

    public C1057N(C1068V[] vArr) {
        C1424b.m3594t(vArr);
        this.f1659Ru = vArr;
    }

    /* renamed from: Ab */
    private String m2619Ab(int i) {
        return mo7049ba(i).getClass().getCanonicalName() + "_savedstate_" + i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: A */
    public int mo7048A(int i) {
        return C1486ya.m3860pk() ? (this.f1659Ru.length - 1) - i : i;
    }

    /* renamed from: ba */
    public C1068V mo7049ba(int i) {
        return getViewHolder(i, true);
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        View destroyView = mo7049ba(i).destroyView();
        if (destroyView != null) {
            viewGroup.removeView(destroyView);
        }
    }

    public int getCount() {
        return this.f1659Ru.length;
    }

    public C1068V getViewHolder(int i, boolean z) {
        C1068V[] vArr = this.f1659Ru;
        if (z) {
            i = mo7048A(i);
        }
        return vArr[i];
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        C1068V ba = mo7049ba(i);
        View view = ba.getView(viewGroup);
        if (view == null) {
            return null;
        }
        view.setTag(ba);
        viewGroup.addView(view);
        return ba;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view.getTag() == obj;
    }

    public void resetState() {
        for (int i = 0; i < this.f1659Ru.length; i++) {
            mo7049ba(i).resetState();
        }
    }

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            bundle.setClassLoader(C0967f.get().getApplicationContext().getClassLoader());
            for (int i = 0; i < this.f1659Ru.length; i++) {
                mo7049ba(i).restoreState(bundle.getParcelable(m2619Ab(i)));
            }
        }
    }

    public Parcelable saveState() {
        Bundle bundle = new Bundle(C0967f.get().getApplicationContext().getClassLoader());
        for (int i = 0; i < this.f1659Ru.length; i++) {
            bundle.putParcelable(m2619Ab(i), mo7049ba(i).saveState());
        }
        return bundle;
    }
}
