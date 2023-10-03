package androidx.fragment.app;

import java.util.ArrayList;

/* renamed from: androidx.fragment.app.O */
public abstract class C0396O {
    boolean mAddToBackStack;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    ArrayList mCommitRunnables;
    int mEnterAnim;
    int mExitAnim;
    String mName;
    ArrayList mOps = new ArrayList();
    int mPopEnterAnim;
    int mPopExitAnim;
    boolean mReorderingAllowed = false;
    ArrayList mSharedElementSourceNames;
    ArrayList mSharedElementTargetNames;
    int mTransition;
    int mTransitionStyle;

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract void mo4176a(int i, C0424j jVar, String str, int i2);

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4177a(C0395N n) {
        this.mOps.add(n);
        n.mEnterAnim = this.mEnterAnim;
        n.mExitAnim = this.mExitAnim;
        n.mPopEnterAnim = this.mPopEnterAnim;
        n.mPopExitAnim = this.mPopExitAnim;
    }

    public abstract int commit();

    public abstract int commitAllowingStateLoss();

    public C0396O disallowAddToBackStack() {
        if (!this.mAddToBackStack) {
            return this;
        }
        throw new IllegalStateException("This transaction is already being added to the back stack");
    }

    public abstract boolean isEmpty();

    /* renamed from: o */
    public C0396O mo4182o(C0424j jVar) {
        mo4177a(new C0395N(7, jVar));
        return this;
    }

    /* renamed from: p */
    public abstract C0396O mo4183p(C0424j jVar);

    /* renamed from: q */
    public abstract C0396O mo4184q(C0424j jVar);

    /* renamed from: a */
    public C0396O mo4175a(int i, C0424j jVar, String str) {
        mo4176a(i, jVar, str, 1);
        return this;
    }
}
