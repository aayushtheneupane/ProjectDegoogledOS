package com.android.messaging.p041ui.conversation;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import com.android.messaging.datamodel.data.C0917ba;
import com.android.messaging.datamodel.data.C0924g;
import com.android.messaging.datamodel.data.C0931n;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.datamodel.p037a.C0786f;
import com.android.messaging.util.C1417V;
import com.android.messaging.util.C1418W;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.ui.conversation.aa */
public class C1158aa implements C1147P {
    /* access modifiers changed from: private */

    /* renamed from: Ih */
    public final C0786f f1855Ih;

    /* renamed from: Kh */
    private final C0924g f1856Kh = new C1150T(this);
    /* access modifiers changed from: private */

    /* renamed from: bH */
    public final C1153W f1857bH;

    /* renamed from: cH */
    private final C1417V f1858cH;
    /* access modifiers changed from: private */

    /* renamed from: dH */
    public final C0786f f1859dH;

    /* renamed from: eH */
    private final C1155Y f1860eH;
    /* access modifiers changed from: private */

    /* renamed from: fH */
    public final C1156Z f1861fH;
    /* access modifiers changed from: private */

    /* renamed from: gH */
    public final C1151U f1862gH;

    /* renamed from: hH */
    private int f1863hH;

    /* renamed from: iH */
    private final C1418W f1864iH = new C1149S(this);
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final FragmentManager mFragmentManager;
    /* access modifiers changed from: private */
    public final C1152V mHost;
    private final C1148Q[] mInputs;

    public C1158aa(Context context, C1152V v, C1153W w, C1417V v2, FragmentManager fragmentManager, C0784d dVar, C0784d dVar2, Bundle bundle) {
        this.mHost = v;
        this.f1857bH = w;
        this.mFragmentManager = fragmentManager;
        this.mContext = context;
        this.f1858cH = v2;
        this.f1855Ih = C0784d.m1314f(dVar);
        this.f1859dH = C0784d.m1314f(dVar2);
        this.f1858cH.mo6911a(this.f1864iH);
        ((C0931n) this.f1855Ih.getData()).mo6471a(this.f1856Kh);
        this.f1860eH = new C1155Y(this, this);
        this.f1861fH = new C1156Z(this, this);
        this.f1862gH = new C1151U(this, this, this.f1858cH.mo6909S());
        this.mInputs = new C1148Q[]{this.f1860eH, this.f1861fH, this.f1862gH};
        if (bundle != null) {
            int i = 0;
            while (true) {
                C1148Q[] qArr = this.mInputs;
                if (i >= qArr.length) {
                    break;
                }
                C1148Q q = qArr[i];
                if (bundle.getBoolean(q.f1843XG.mo7430a(q))) {
                    q.f1843XG.mo7431a(q, true, false);
                }
                i++;
            }
        }
        this.mHost.mo7392a(!this.f1860eH.isOpen());
    }

    /* renamed from: Sb */
    public void mo7464Sb() {
        this.f1860eH.mo7460Ka();
    }

    /* renamed from: a */
    public String mo7430a(C1148Q q) {
        return q.getClass().getCanonicalName() + "_savedstate_";
    }

    /* renamed from: ba */
    public void mo7466ba(boolean z) {
        mo7434ka();
        int i = 0;
        while (true) {
            C1148Q[] qArr = this.mInputs;
            if (i < qArr.length) {
                mo7431a(qArr[i], false, z);
                i++;
            } else {
                endUpdate();
                return;
            }
        }
    }

    public void endUpdate() {
        C1424b.m3592ia(this.f1863hH > 0);
        int i = this.f1863hH - 1;
        this.f1863hH = i;
        if (i == 0) {
            this.mHost.invalidateActionBar();
        }
    }

    /* renamed from: i */
    public void mo7467i(boolean z, boolean z2) {
        mo7431a(this.f1862gH, z, z2);
    }

    /* access modifiers changed from: package-private */
    public boolean isImeKeyboardVisible() {
        return this.f1862gH.mShowing;
    }

    /* access modifiers changed from: package-private */
    public boolean isMediaPickerVisible() {
        return this.f1860eH.mShowing;
    }

    /* access modifiers changed from: package-private */
    public boolean isSimSelectorVisible() {
        return this.f1861fH.mShowing;
    }

    /* renamed from: k */
    public void mo7472k(Bundle bundle) {
        int i = 0;
        while (true) {
            C1148Q[] qArr = this.mInputs;
            if (i < qArr.length) {
                C1148Q q = qArr[i];
                bundle.putBoolean(q.f1843XG.mo7430a(q), q.mShowing);
                i++;
            } else {
                return;
            }
        }
    }

    /* renamed from: ka */
    public void mo7434ka() {
        this.f1863hH++;
    }

    public boolean onBackPressed() {
        int i = 0;
        while (true) {
            C1148Q[] qArr = this.mInputs;
            if (i >= qArr.length) {
                return false;
            }
            if (qArr[i].onBackPressed()) {
                return true;
            }
            i++;
        }
    }

    public void onDetach() {
        this.f1858cH.mo6913b(this.f1864iH);
    }

    /* renamed from: sa */
    public boolean mo7476sa() {
        int i = 0;
        while (true) {
            C1148Q[] qArr = this.mInputs;
            if (i >= qArr.length) {
                return false;
            }
            if (qArr[i].mo7438sa()) {
                return true;
            }
            i++;
        }
    }

    /* access modifiers changed from: package-private */
    public void testNotifyImeStateChanged(boolean z) {
        ((C1149S) this.f1864iH).mo7441c(z);
    }

    public boolean updateActionBar(ActionBar actionBar) {
        int i = 0;
        while (true) {
            C1148Q[] qArr = this.mInputs;
            if (i >= qArr.length) {
                return false;
            }
            if (qArr[i].mShowing) {
                return qArr[i].updateActionBar(actionBar);
            }
            i++;
        }
    }

    /* renamed from: a */
    public boolean mo7465a(boolean z, C0917ba baVar) {
        this.f1861fH.mo7462d(baVar);
        C1156Z z2 = this.f1861fH;
        z2.f1843XG.mo7431a(z2, !z2.mShowing, true);
        return z2.mShowing;
    }

    /* renamed from: b */
    public void mo7432b(C1148Q q) {
        if (this.f1855Ih.isBound()) {
            mo7434ka();
            int i = 0;
            while (true) {
                C1148Q[] qArr = this.mInputs;
                if (i >= qArr.length) {
                    break;
                }
                C1148Q q2 = qArr[i];
                if (q2 != q) {
                    if (!(q2 instanceof C1155Y) || !(q instanceof C1151U) || this.f1860eH.m2964so() == null || !this.f1860eH.m2964so().mo7924ya()) {
                        mo7431a(q2, false, false);
                    } else {
                        this.f1860eH.m2964so().setFullScreen(true);
                    }
                }
                i++;
            }
            this.mHost.mo7380G();
            if (q != this.f1862gH) {
                this.mHost.mo7381I();
            }
            endUpdate();
        }
    }

    /* renamed from: j */
    public void mo7471j(boolean z, boolean z2) {
        mo7431a(this.f1860eH, z, z2);
    }

    /* renamed from: k */
    public boolean mo7473k(boolean z, boolean z2) {
        return mo7431a(this.f1861fH, z, z2);
    }

    /* renamed from: a */
    public boolean mo7431a(C1148Q q, boolean z, boolean z2) {
        boolean z3;
        if (!this.f1855Ih.isBound() || q.mShowing == z) {
            return false;
        }
        mo7434ka();
        if (!z) {
            z3 = q.mo7435aa(z2);
        } else {
            z3 = q.show(z2);
        }
        if (z3) {
            q.onVisibilityChanged(z);
        }
        endUpdate();
        return true;
    }
}
