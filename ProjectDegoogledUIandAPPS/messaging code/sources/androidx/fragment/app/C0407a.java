package androidx.fragment.app;

import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.fragment.app.a */
final class C0407a extends C0396O implements C0430p, C0386E {
    boolean mCommitted;
    int mIndex = -1;
    final C0389H mManager;

    public C0407a(C0389H h) {
        this.mManager = h;
    }

    /* renamed from: b */
    private static boolean m379b(C0395N n) {
        C0424j jVar = n.f358Zo;
        return jVar != null && jVar.mAdded && jVar.mView != null && !jVar.mDetached && !jVar.mHidden && jVar.isPostponed();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4176a(int i, C0424j jVar, String str, int i2) {
        Class<?> cls = jVar.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            StringBuilder Pa = C0632a.m1011Pa("Fragment ");
            Pa.append(cls.getCanonicalName());
            Pa.append(" must be a public static class to be  properly recreated from instance state.");
            throw new IllegalStateException(Pa.toString());
        }
        if (str != null) {
            String str2 = jVar.mTag;
            if (str2 == null || str.equals(str2)) {
                jVar.mTag = str;
            } else {
                throw new IllegalStateException("Can't change tag of fragment " + jVar + ": was " + jVar.mTag + " now " + str);
            }
        }
        if (i != 0) {
            if (i != -1) {
                int i3 = jVar.mFragmentId;
                if (i3 == 0 || i3 == i) {
                    jVar.mFragmentId = i;
                    jVar.mContainerId = i;
                } else {
                    throw new IllegalStateException("Can't change container ID of fragment " + jVar + ": was " + jVar.mFragmentId + " now " + i);
                }
            } else {
                throw new IllegalArgumentException("Can't add fragment " + jVar + " with tag " + str + " to container view with no id");
            }
        }
        mo4177a(new C0395N(i2, jVar));
        jVar.mFragmentManager = this.mManager;
    }

    /* access modifiers changed from: package-private */
    public void bumpBackStackNesting(int i) {
        if (this.mAddToBackStack) {
            int size = this.mOps.size();
            for (int i2 = 0; i2 < size; i2++) {
                C0424j jVar = ((C0395N) this.mOps.get(i2)).f358Zo;
                if (jVar != null) {
                    jVar.mBackStackNesting += i;
                }
            }
        }
    }

    public int commit() {
        return commitInternal(false);
    }

    public int commitAllowingStateLoss() {
        return commitInternal(true);
    }

    /* access modifiers changed from: package-private */
    public int commitInternal(boolean z) {
        if (!this.mCommitted) {
            this.mCommitted = true;
            if (this.mAddToBackStack) {
                this.mIndex = this.mManager.mo4073a(this);
            } else {
                this.mIndex = -1;
            }
            this.mManager.mo4077a((C0386E) this, z);
            return this.mIndex;
        }
        throw new IllegalStateException("commit already called");
    }

    public void dump(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.mName);
            printWriter.print(" mIndex=");
            printWriter.print(this.mIndex);
            printWriter.print(" mCommitted=");
            printWriter.println(this.mCommitted);
            if (this.mTransition != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.mTransition));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.mTransitionStyle));
            }
            if (!(this.mEnterAnim == 0 && this.mExitAnim == 0)) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.mEnterAnim));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.mExitAnim));
            }
            if (!(this.mPopEnterAnim == 0 && this.mPopExitAnim == 0)) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.mPopEnterAnim));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.mPopExitAnim));
            }
            if (!(this.mBreadCrumbTitleRes == 0 && this.mBreadCrumbTitleText == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.mBreadCrumbTitleRes));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.mBreadCrumbTitleText);
            }
            if (!(this.mBreadCrumbShortTitleRes == 0 && this.mBreadCrumbShortTitleText == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.mBreadCrumbShortTitleRes));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.mBreadCrumbShortTitleText);
            }
        }
        if (!this.mOps.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Operations:");
            int size = this.mOps.size();
            for (int i = 0; i < size; i++) {
                C0395N n = (C0395N) this.mOps.get(i);
                switch (n.mCmd) {
                    case 0:
                        str2 = "NULL";
                        break;
                    case 1:
                        str2 = "ADD";
                        break;
                    case 2:
                        str2 = "REPLACE";
                        break;
                    case 3:
                        str2 = "REMOVE";
                        break;
                    case 4:
                        str2 = "HIDE";
                        break;
                    case 5:
                        str2 = "SHOW";
                        break;
                    case 6:
                        str2 = "DETACH";
                        break;
                    case 7:
                        str2 = "ATTACH";
                        break;
                    case 8:
                        str2 = "SET_PRIMARY_NAV";
                        break;
                    case 9:
                        str2 = "UNSET_PRIMARY_NAV";
                        break;
                    case 10:
                        str2 = "OP_SET_MAX_LIFECYCLE";
                        break;
                    default:
                        StringBuilder Pa = C0632a.m1011Pa("cmd=");
                        Pa.append(n.mCmd);
                        str2 = Pa.toString();
                        break;
                }
                printWriter.print(str);
                printWriter.print("  Op #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.print(str2);
                printWriter.print(" ");
                printWriter.println(n.f358Zo);
                if (z) {
                    if (!(n.mEnterAnim == 0 && n.mExitAnim == 0)) {
                        printWriter.print(str);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(n.mEnterAnim));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(n.mExitAnim));
                    }
                    if (n.mPopEnterAnim != 0 || n.mPopExitAnim != 0) {
                        printWriter.print(str);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(n.mPopEnterAnim));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(n.mPopExitAnim));
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void executeOps() {
        int size = this.mOps.size();
        for (int i = 0; i < size; i++) {
            C0395N n = (C0395N) this.mOps.get(i);
            C0424j jVar = n.f358Zo;
            if (jVar != null) {
                jVar.setNextTransition(this.mTransition, this.mTransitionStyle);
            }
            switch (n.mCmd) {
                case 1:
                    jVar.setNextAnim(n.mEnterAnim);
                    this.mManager.mo4084a(jVar, false);
                    break;
                case 3:
                    jVar.setNextAnim(n.mExitAnim);
                    this.mManager.mo4137j(jVar);
                    break;
                case 4:
                    jVar.setNextAnim(n.mExitAnim);
                    this.mManager.mo4122f(jVar);
                    break;
                case 5:
                    jVar.setNextAnim(n.mEnterAnim);
                    this.mManager.mo4142n(jVar);
                    break;
                case 6:
                    jVar.setNextAnim(n.mExitAnim);
                    this.mManager.mo4095d(jVar);
                    break;
                case 7:
                    jVar.setNextAnim(n.mEnterAnim);
                    this.mManager.mo4092c(jVar);
                    break;
                case 8:
                    this.mManager.mo4140m(jVar);
                    break;
                case 9:
                    this.mManager.mo4140m((C0424j) null);
                    break;
                case 10:
                    this.mManager.mo4083a(jVar, n.f360ap);
                    break;
                default:
                    StringBuilder Pa = C0632a.m1011Pa("Unknown cmd: ");
                    Pa.append(n.mCmd);
                    throw new IllegalArgumentException(Pa.toString());
            }
            if (!(this.mReorderingAllowed || n.mCmd == 1 || jVar == null)) {
                this.mManager.mo4132h(jVar);
            }
        }
        if (!this.mReorderingAllowed) {
            C0389H h = this.mManager;
            h.moveToState(h.mCurState, true);
        }
    }

    /* access modifiers changed from: package-private */
    public void executePopOps(boolean z) {
        for (int size = this.mOps.size() - 1; size >= 0; size--) {
            C0395N n = (C0395N) this.mOps.get(size);
            C0424j jVar = n.f358Zo;
            if (jVar != null) {
                jVar.setNextTransition(C0389H.reverseTransit(this.mTransition), this.mTransitionStyle);
            }
            switch (n.mCmd) {
                case 1:
                    jVar.setNextAnim(n.mPopExitAnim);
                    this.mManager.mo4137j(jVar);
                    break;
                case 3:
                    jVar.setNextAnim(n.mPopEnterAnim);
                    this.mManager.mo4084a(jVar, false);
                    break;
                case 4:
                    jVar.setNextAnim(n.mPopEnterAnim);
                    this.mManager.mo4142n(jVar);
                    break;
                case 5:
                    jVar.setNextAnim(n.mPopExitAnim);
                    this.mManager.mo4122f(jVar);
                    break;
                case 6:
                    jVar.setNextAnim(n.mPopEnterAnim);
                    this.mManager.mo4092c(jVar);
                    break;
                case 7:
                    jVar.setNextAnim(n.mPopExitAnim);
                    this.mManager.mo4095d(jVar);
                    break;
                case 8:
                    this.mManager.mo4140m((C0424j) null);
                    break;
                case 9:
                    this.mManager.mo4140m(jVar);
                    break;
                case 10:
                    this.mManager.mo4083a(jVar, n.f359_o);
                    break;
                default:
                    StringBuilder Pa = C0632a.m1011Pa("Unknown cmd: ");
                    Pa.append(n.mCmd);
                    throw new IllegalArgumentException(Pa.toString());
            }
            if (!(this.mReorderingAllowed || n.mCmd == 3 || jVar == null)) {
                this.mManager.mo4132h(jVar);
            }
        }
        if (!this.mReorderingAllowed && z) {
            C0389H h = this.mManager;
            h.moveToState(h.mCurState, true);
        }
    }

    public boolean generateOps(ArrayList arrayList, ArrayList arrayList2) {
        arrayList.add(this);
        arrayList2.add(false);
        if (!this.mAddToBackStack) {
            return true;
        }
        C0389H h = this.mManager;
        if (h.mBackStack == null) {
            h.mBackStack = new ArrayList();
        }
        h.mBackStack.add(this);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean interactsWith(int i) {
        int size = this.mOps.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0424j jVar = ((C0395N) this.mOps.get(i2)).f358Zo;
            int i3 = jVar != null ? jVar.mContainerId : 0;
            if (i3 != 0 && i3 == i) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return this.mOps.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public boolean isPostponed() {
        for (int i = 0; i < this.mOps.size(); i++) {
            if (m379b((C0395N) this.mOps.get(i))) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: p */
    public C0396O mo4183p(C0424j jVar) {
        C0389H h = jVar.mFragmentManager;
        if (h == null || h == this.mManager) {
            mo4177a(new C0395N(6, jVar));
            return this;
        }
        StringBuilder Pa = C0632a.m1011Pa("Cannot detach Fragment attached to a different FragmentManager. Fragment ");
        Pa.append(jVar.toString());
        Pa.append(" is already attached to a FragmentManager.");
        throw new IllegalStateException(Pa.toString());
    }

    /* renamed from: q */
    public C0396O mo4184q(C0424j jVar) {
        C0389H h = jVar.mFragmentManager;
        if (h == null || h == this.mManager) {
            mo4177a(new C0395N(3, jVar));
            return this;
        }
        StringBuilder Pa = C0632a.m1011Pa("Cannot remove Fragment attached to a different FragmentManager. Fragment ");
        Pa.append(jVar.toString());
        Pa.append(" is already attached to a FragmentManager.");
        throw new IllegalStateException(Pa.toString());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.mIndex >= 0) {
            sb.append(" #");
            sb.append(this.mIndex);
        }
        if (this.mName != null) {
            sb.append(" ");
            sb.append(this.mName);
        }
        sb.append("}");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean interactsWith(ArrayList arrayList, int i, int i2) {
        if (i2 == i) {
            return false;
        }
        int size = this.mOps.size();
        int i3 = -1;
        for (int i4 = 0; i4 < size; i4++) {
            C0424j jVar = ((C0395N) this.mOps.get(i4)).f358Zo;
            int i5 = jVar != null ? jVar.mContainerId : 0;
            if (!(i5 == 0 || i5 == i3)) {
                for (int i6 = i; i6 < i2; i6++) {
                    C0407a aVar = (C0407a) arrayList.get(i6);
                    int size2 = aVar.mOps.size();
                    for (int i7 = 0; i7 < size2; i7++) {
                        C0424j jVar2 = ((C0395N) aVar.mOps.get(i7)).f358Zo;
                        if ((jVar2 != null ? jVar2.mContainerId : 0) == i5) {
                            return true;
                        }
                    }
                }
                i3 = i5;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4201a(C0421h hVar) {
        for (int i = 0; i < this.mOps.size(); i++) {
            C0395N n = (C0395N) this.mOps.get(i);
            if (m379b(n)) {
                n.f358Zo.setOnStartEnterTransitionListener(hVar);
            }
        }
    }
}
