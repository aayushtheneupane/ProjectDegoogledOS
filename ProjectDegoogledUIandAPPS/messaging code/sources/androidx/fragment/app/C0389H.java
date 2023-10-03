package androidx.fragment.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import androidx.activity.C0122d;
import androidx.activity.C0124f;
import androidx.core.util.DebugUtils;
import androidx.core.util.LogWriter;
import androidx.core.view.InputDeviceCompat;
import androidx.lifecycle.C0442A;
import androidx.lifecycle.Lifecycle$State;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import p000a.p005b.C0017d;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.fragment.app.H */
final class C0389H extends C0433s implements LayoutInflater.Factory2 {

    /* renamed from: So */
    static final Interpolator f346So = new DecelerateInterpolator(2.5f);

    /* renamed from: To */
    static final Interpolator f347To = new DecelerateInterpolator(1.5f);

    /* renamed from: Il */
    private final C0122d f348Il = new C0434t(this, false);

    /* renamed from: Ro */
    private C0392K f349Ro;
    final HashMap mActive = new HashMap();
    final ArrayList mAdded = new ArrayList();
    ArrayList mAvailBackStackIndices;
    ArrayList mBackStack;
    ArrayList mBackStackChangeListeners;
    ArrayList mBackStackIndices;
    C0426l mContainer;
    ArrayList mCreatedMenus;
    int mCurState = 0;
    boolean mDestroyed;
    Runnable mExecCommit = new C0435u(this);
    boolean mExecutingActions;
    boolean mHavePendingDeferredStart;
    C0429o mHost;
    private final CopyOnWriteArrayList mLifecycleCallbacks = new CopyOnWriteArrayList();
    boolean mNeedMenuInvalidate;
    int mNextFragmentIndex = 0;
    private C0124f mOnBackPressedDispatcher;
    C0424j mParent;
    ArrayList mPendingActions;
    ArrayList mPostponedTransactions;
    C0424j mPrimaryNav;
    SparseArray mStateArray = null;
    Bundle mStateBundle = null;
    boolean mStateSaved;
    boolean mStopped;
    ArrayList mTmpAddedFragments;
    ArrayList mTmpIsPop;
    ArrayList mTmpRecords;

    C0389H() {
    }

    /* renamed from: Ba */
    private void m286Ba(boolean z) {
        if (this.mExecutingActions) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        } else if (this.mHost == null) {
            throw new IllegalStateException("Fragment host has been destroyed");
        } else if (Looper.myLooper() == this.mHost.getHandler().getLooper()) {
            if (!z) {
                m288Dn();
            }
            if (this.mTmpRecords == null) {
                this.mTmpRecords = new ArrayList();
                this.mTmpIsPop = new ArrayList();
            }
            this.mExecutingActions = true;
            try {
                m295a((ArrayList) null, (ArrayList) null);
            } finally {
                this.mExecutingActions = false;
            }
        } else {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
    }

    /* renamed from: Cn */
    private void m287Cn() {
        this.mActive.values().removeAll(Collections.singleton((Object) null));
    }

    /* renamed from: Dn */
    private void m288Dn() {
        if (isStateSaved()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    /* renamed from: En */
    private void m289En() {
        this.mExecutingActions = false;
        this.mTmpIsPop.clear();
        this.mTmpRecords.clear();
    }

    /* renamed from: Fn */
    private void m290Fn() {
        if (this.mPostponedTransactions != null) {
            while (!this.mPostponedTransactions.isEmpty()) {
                ((C0388G) this.mPostponedTransactions.remove(0)).completeTransaction();
            }
        }
    }

    /* renamed from: Gn */
    private void m291Gn() {
        ArrayList arrayList = this.mPendingActions;
        boolean z = true;
        if (arrayList == null || arrayList.isEmpty()) {
            C0122d dVar = this.f348Il;
            ArrayList arrayList2 = this.mBackStack;
            if ((arrayList2 != null ? arrayList2.size() : 0) <= 0 || !m301x(this.mParent)) {
                z = false;
            }
            dVar.setEnabled(z);
            return;
        }
        this.f348Il.setEnabled(true);
    }

    /* renamed from: a */
    private void m294a(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
        C0429o oVar = this.mHost;
        if (oVar != null) {
            try {
                ((C0425k) oVar).this$0.dump("  ", (FileDescriptor) null, printWriter, new String[0]);
            } catch (Exception e) {
                Log.e("FragmentManager", "Failed dumping state", e);
            }
        } else {
            try {
                dump("  ", (FileDescriptor) null, printWriter, new String[0]);
            } catch (Exception e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
            }
        }
        throw runtimeException;
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: pb */
    private void m299pb(int i) {
        try {
            this.mExecutingActions = true;
            moveToState(i, false);
            this.mExecutingActions = false;
            execPendingActions();
        } catch (Throwable th) {
            this.mExecutingActions = false;
            throw th;
        }
    }

    public static int reverseTransit(int i) {
        if (i == 4097) {
            return InputDeviceCompat.SOURCE_MOUSE;
        }
        if (i != 4099) {
            return i != 8194 ? 0 : 4097;
        }
        return 4099;
    }

    /* renamed from: w */
    private void m300w(C0424j jVar) {
        C0389H h;
        if (jVar != null && (h = jVar.mChildFragmentManager) != null) {
            h.m291Gn();
            for (C0424j w : h.mActive.values()) {
                h.m300w(w);
            }
        }
    }

    /* renamed from: x */
    private boolean m301x(C0424j jVar) {
        if (jVar == null) {
            return true;
        }
        C0389H h = jVar.mFragmentManager;
        if (jVar != h.mPrimaryNav || !m301x(h.mParent)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo4087b(C0424j jVar) {
        if (!isStateSaved()) {
            boolean b = this.f349Ro.mo4158b(jVar);
        }
    }

    public C0396O beginTransaction() {
        return new C0407a(this);
    }

    /* renamed from: c */
    public void mo4092c(C0424j jVar) {
        if (jVar.mDetached) {
            jVar.mDetached = false;
            if (jVar.mAdded) {
                return;
            }
            if (!this.mAdded.contains(jVar)) {
                synchronized (this.mAdded) {
                    this.mAdded.add(jVar);
                }
                jVar.mAdded = true;
                if (jVar.mHasMenu && jVar.mMenuVisible) {
                    this.mNeedMenuInvalidate = true;
                    return;
                }
                return;
            }
            throw new IllegalStateException(C0632a.m1014a("Fragment already added: ", jVar));
        }
    }

    /* renamed from: d */
    public void mo4095d(C0424j jVar) {
        if (!jVar.mDetached) {
            jVar.mDetached = true;
            if (jVar.mAdded) {
                synchronized (this.mAdded) {
                    this.mAdded.remove(jVar);
                }
                if (jVar.mHasMenu && jVar.mMenuVisible) {
                    this.mNeedMenuInvalidate = true;
                }
                jVar.mAdded = false;
            }
        }
    }

    public void dispatchActivityCreated() {
        this.mStateSaved = false;
        this.mStopped = false;
        m299pb(2);
    }

    public void dispatchConfigurationChanged(Configuration configuration) {
        for (int i = 0; i < this.mAdded.size(); i++) {
            C0424j jVar = (C0424j) this.mAdded.get(i);
            if (jVar != null) {
                jVar.performConfigurationChanged(configuration);
            }
        }
    }

    public boolean dispatchContextItemSelected(MenuItem menuItem) {
        if (this.mCurState < 1) {
            return false;
        }
        for (int i = 0; i < this.mAdded.size(); i++) {
            C0424j jVar = (C0424j) this.mAdded.get(i);
            if (jVar != null && jVar.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void dispatchCreate() {
        this.mStateSaved = false;
        this.mStopped = false;
        m299pb(1);
    }

    public boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (this.mCurState < 1) {
            return false;
        }
        ArrayList arrayList = null;
        boolean z = false;
        for (int i = 0; i < this.mAdded.size(); i++) {
            C0424j jVar = (C0424j) this.mAdded.get(i);
            if (jVar != null && jVar.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(jVar);
                z = true;
            }
        }
        if (this.mCreatedMenus != null) {
            for (int i2 = 0; i2 < this.mCreatedMenus.size(); i2++) {
                C0424j jVar2 = (C0424j) this.mCreatedMenus.get(i2);
                if (arrayList == null || !arrayList.contains(jVar2)) {
                    jVar2.onDestroyOptionsMenu();
                }
            }
        }
        this.mCreatedMenus = arrayList;
        return z;
    }

    public void dispatchDestroy() {
        this.mDestroyed = true;
        execPendingActions();
        m299pb(0);
        this.mHost = null;
        this.mContainer = null;
        this.mParent = null;
        if (this.mOnBackPressedDispatcher != null) {
            this.f348Il.remove();
            this.mOnBackPressedDispatcher = null;
        }
    }

    public void dispatchDestroyView() {
        m299pb(1);
    }

    public void dispatchLowMemory() {
        for (int i = 0; i < this.mAdded.size(); i++) {
            C0424j jVar = (C0424j) this.mAdded.get(i);
            if (jVar != null) {
                jVar.performLowMemory();
            }
        }
    }

    public void dispatchMultiWindowModeChanged(boolean z) {
        for (int size = this.mAdded.size() - 1; size >= 0; size--) {
            C0424j jVar = (C0424j) this.mAdded.get(size);
            if (jVar != null) {
                jVar.performMultiWindowModeChanged(z);
            }
        }
    }

    public boolean dispatchOptionsItemSelected(MenuItem menuItem) {
        if (this.mCurState < 1) {
            return false;
        }
        for (int i = 0; i < this.mAdded.size(); i++) {
            C0424j jVar = (C0424j) this.mAdded.get(i);
            if (jVar != null && jVar.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void dispatchOptionsMenuClosed(Menu menu) {
        if (this.mCurState >= 1) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                C0424j jVar = (C0424j) this.mAdded.get(i);
                if (jVar != null) {
                    jVar.performOptionsMenuClosed(menu);
                }
            }
        }
    }

    public void dispatchPause() {
        m299pb(3);
    }

    public void dispatchPictureInPictureModeChanged(boolean z) {
        for (int size = this.mAdded.size() - 1; size >= 0; size--) {
            C0424j jVar = (C0424j) this.mAdded.get(size);
            if (jVar != null) {
                jVar.performPictureInPictureModeChanged(z);
            }
        }
    }

    public boolean dispatchPrepareOptionsMenu(Menu menu) {
        if (this.mCurState < 1) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < this.mAdded.size(); i++) {
            C0424j jVar = (C0424j) this.mAdded.get(i);
            if (jVar != null && jVar.performPrepareOptionsMenu(menu)) {
                z = true;
            }
        }
        return z;
    }

    public void dispatchResume() {
        this.mStateSaved = false;
        this.mStopped = false;
        m299pb(4);
    }

    public void dispatchStart() {
        this.mStateSaved = false;
        this.mStopped = false;
        m299pb(3);
    }

    public void dispatchStop() {
        this.mStopped = true;
        m299pb(2);
    }

    /* access modifiers changed from: package-private */
    public void doPendingDeferredStart() {
        if (this.mHavePendingDeferredStart) {
            this.mHavePendingDeferredStart = false;
            startPendingDeferredFragments();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        int size3;
        int size4;
        String k = C0632a.m1025k(str, "    ");
        if (!this.mActive.isEmpty()) {
            printWriter.print(str);
            printWriter.print("Active Fragments in ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(":");
            for (C0424j jVar : this.mActive.values()) {
                printWriter.print(str);
                printWriter.println(jVar);
                if (jVar != null) {
                    jVar.dump(k, fileDescriptor, printWriter, strArr);
                }
            }
        }
        int size5 = this.mAdded.size();
        if (size5 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i = 0; i < size5; i++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.println(((C0424j) this.mAdded.get(i)).toString());
            }
        }
        ArrayList arrayList = this.mCreatedMenus;
        if (arrayList != null && (size4 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i2 = 0; i2 < size4; i2++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(((C0424j) this.mCreatedMenus.get(i2)).toString());
            }
        }
        ArrayList arrayList2 = this.mBackStack;
        if (arrayList2 != null && (size3 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i3 = 0; i3 < size3; i3++) {
                C0407a aVar = (C0407a) this.mBackStack.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.dump(k, printWriter, true);
            }
        }
        synchronized (this) {
            if (this.mBackStackIndices != null && (size2 = this.mBackStackIndices.size()) > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack Indices:");
                for (int i4 = 0; i4 < size2; i4++) {
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i4);
                    printWriter.print(": ");
                    printWriter.println((C0407a) this.mBackStackIndices.get(i4));
                }
            }
            if (this.mAvailBackStackIndices != null && this.mAvailBackStackIndices.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.mAvailBackStackIndices.toArray()));
            }
        }
        ArrayList arrayList3 = this.mPendingActions;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Pending Actions:");
            for (int i5 = 0; i5 < size; i5++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i5);
                printWriter.print(": ");
                printWriter.println((C0386E) this.mPendingActions.get(i5));
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.mHost);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.mContainer);
        if (this.mParent != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.mParent);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.mCurState);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.mStateSaved);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.mDestroyed);
        if (this.mNeedMenuInvalidate) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.mNeedMenuInvalidate);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public C0442A mo4117e(C0424j jVar) {
        return this.f349Ro.mo4159e(jVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ec */
    public void mo4119ec() {
        execPendingActions();
        if (this.f348Il.isEnabled()) {
            popBackStackImmediate();
        } else {
            this.mOnBackPressedDispatcher.onBackPressed();
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean execPendingActions() {
        m286Ba(true);
        boolean z = false;
        while (m297b(this.mTmpRecords, this.mTmpIsPop)) {
            this.mExecutingActions = true;
            try {
                m298c(this.mTmpRecords, this.mTmpIsPop);
                m289En();
                z = true;
            } catch (Throwable th) {
                m289En();
                throw th;
            }
        }
        m291Gn();
        doPendingDeferredStart();
        m287Cn();
        return z;
    }

    public boolean executePendingTransactions() {
        boolean execPendingActions = execPendingActions();
        m290Fn();
        return execPendingActions;
    }

    /* renamed from: f */
    public void mo4122f(C0424j jVar) {
        if (!jVar.mHidden) {
            jVar.mHidden = true;
            jVar.mHiddenChanged = true ^ jVar.mHiddenChanged;
        }
    }

    public C0424j findFragmentById(int i) {
        for (int size = this.mAdded.size() - 1; size >= 0; size--) {
            C0424j jVar = (C0424j) this.mAdded.get(size);
            if (jVar != null && jVar.mFragmentId == i) {
                return jVar;
            }
        }
        for (C0424j jVar2 : this.mActive.values()) {
            if (jVar2 != null && jVar2.mFragmentId == i) {
                return jVar2;
            }
        }
        return null;
    }

    public C0424j findFragmentByTag(String str) {
        if (str != null) {
            for (int size = this.mAdded.size() - 1; size >= 0; size--) {
                C0424j jVar = (C0424j) this.mAdded.get(size);
                if (jVar != null && str.equals(jVar.mTag)) {
                    return jVar;
                }
            }
        }
        if (str == null) {
            return null;
        }
        for (C0424j jVar2 : this.mActive.values()) {
            if (jVar2 != null && str.equals(jVar2.mTag)) {
                return jVar2;
            }
        }
        return null;
    }

    public C0424j findFragmentByWho(String str) {
        C0424j findFragmentByWho;
        for (C0424j jVar : this.mActive.values()) {
            if (jVar != null && (findFragmentByWho = jVar.findFragmentByWho(str)) != null) {
                return findFragmentByWho;
            }
        }
        return null;
    }

    public void freeBackStackIndex(int i) {
        synchronized (this) {
            this.mBackStackIndices.set(i, (Object) null);
            if (this.mAvailBackStackIndices == null) {
                this.mAvailBackStackIndices = new ArrayList();
            }
            this.mAvailBackStackIndices.add(Integer.valueOf(i));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public void mo4128g(C0424j jVar) {
        if (this.mActive.get(jVar.mWho) == null) {
            this.mActive.put(jVar.mWho, jVar);
            if (jVar.mRetainInstanceChangedWhileDetached) {
                if (jVar.mRetainInstance) {
                    mo4087b(jVar);
                } else {
                    mo4138k(jVar);
                }
                jVar.mRetainInstanceChangedWhileDetached = false;
            }
        }
    }

    public List getFragments() {
        List list;
        if (this.mAdded.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.mAdded) {
            list = (List) this.mAdded.clone();
        }
        return list;
    }

    /* access modifiers changed from: package-private */
    public LayoutInflater.Factory2 getLayoutInflaterFactory() {
        return this;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005d, code lost:
        r0 = r4.mView;
     */
    /* renamed from: h */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4132h(androidx.fragment.app.C0424j r11) {
        /*
            r10 = this;
            if (r11 != 0) goto L_0x0003
            return
        L_0x0003:
            java.util.HashMap r0 = r10.mActive
            java.lang.String r1 = r11.mWho
            boolean r0 = r0.containsKey(r1)
            if (r0 != 0) goto L_0x000e
            return
        L_0x000e:
            int r0 = r10.mCurState
            boolean r1 = r11.mRemoving
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0025
            boolean r1 = r11.isInBackStack()
            if (r1 == 0) goto L_0x0021
            int r0 = java.lang.Math.min(r0, r2)
            goto L_0x0025
        L_0x0021:
            int r0 = java.lang.Math.min(r0, r3)
        L_0x0025:
            r6 = r0
            int r7 = r11.getNextTransition()
            int r8 = r11.getNextTransitionStyle()
            r9 = 0
            r4 = r10
            r5 = r11
            r4.mo4079a(r5, r6, r7, r8, r9)
            android.view.View r0 = r11.mView
            if (r0 == 0) goto L_0x00b1
            android.view.ViewGroup r1 = r11.mContainer
            r4 = 0
            if (r1 == 0) goto L_0x005b
            if (r0 != 0) goto L_0x0040
            goto L_0x005b
        L_0x0040:
            java.util.ArrayList r0 = r10.mAdded
            int r0 = r0.indexOf(r11)
        L_0x0046:
            int r0 = r0 + -1
            if (r0 < 0) goto L_0x005b
            java.util.ArrayList r5 = r10.mAdded
            java.lang.Object r5 = r5.get(r0)
            androidx.fragment.app.j r5 = (androidx.fragment.app.C0424j) r5
            android.view.ViewGroup r6 = r5.mContainer
            if (r6 != r1) goto L_0x0046
            android.view.View r6 = r5.mView
            if (r6 == 0) goto L_0x0046
            r4 = r5
        L_0x005b:
            if (r4 == 0) goto L_0x0075
            android.view.View r0 = r4.mView
            android.view.ViewGroup r1 = r11.mContainer
            int r0 = r1.indexOfChild(r0)
            android.view.View r4 = r11.mView
            int r4 = r1.indexOfChild(r4)
            if (r4 >= r0) goto L_0x0075
            r1.removeViewAt(r4)
            android.view.View r4 = r11.mView
            r1.addView(r4, r0)
        L_0x0075:
            boolean r0 = r11.mIsNewlyAdded
            if (r0 == 0) goto L_0x00b1
            android.view.ViewGroup r0 = r11.mContainer
            if (r0 == 0) goto L_0x00b1
            float r0 = r11.mPostponedAlpha
            r1 = 0
            int r4 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r4 <= 0) goto L_0x0089
            android.view.View r4 = r11.mView
            r4.setAlpha(r0)
        L_0x0089:
            r11.mPostponedAlpha = r1
            r11.mIsNewlyAdded = r3
            int r0 = r11.getNextTransition()
            int r1 = r11.getNextTransitionStyle()
            androidx.fragment.app.A r0 = r10.mo4074a((androidx.fragment.app.C0424j) r11, (int) r0, (boolean) r2, (int) r1)
            if (r0 == 0) goto L_0x00b1
            android.view.animation.Animation r1 = r0.animation
            if (r1 == 0) goto L_0x00a5
            android.view.View r0 = r11.mView
            r0.startAnimation(r1)
            goto L_0x00b1
        L_0x00a5:
            android.animation.Animator r1 = r0.animator
            android.view.View r4 = r11.mView
            r1.setTarget(r4)
            android.animation.Animator r0 = r0.animator
            r0.start()
        L_0x00b1:
            boolean r0 = r11.mHiddenChanged
            if (r0 == 0) goto L_0x013d
            android.view.View r0 = r11.mView
            if (r0 == 0) goto L_0x0128
            int r0 = r11.getNextTransition()
            boolean r1 = r11.mHidden
            r1 = r1 ^ r2
            int r4 = r11.getNextTransitionStyle()
            androidx.fragment.app.A r0 = r10.mo4074a((androidx.fragment.app.C0424j) r11, (int) r0, (boolean) r1, (int) r4)
            if (r0 == 0) goto L_0x00fe
            android.animation.Animator r1 = r0.animator
            if (r1 == 0) goto L_0x00fe
            android.view.View r4 = r11.mView
            r1.setTarget(r4)
            boolean r1 = r11.mHidden
            if (r1 == 0) goto L_0x00f3
            boolean r1 = r11.isHideReplaced()
            if (r1 == 0) goto L_0x00e1
            r11.setHideReplaced(r3)
            goto L_0x00f8
        L_0x00e1:
            android.view.ViewGroup r1 = r11.mContainer
            android.view.View r4 = r11.mView
            r1.startViewTransition(r4)
            android.animation.Animator r5 = r0.animator
            androidx.fragment.app.y r6 = new androidx.fragment.app.y
            r6.<init>(r10, r1, r4, r11)
            r5.addListener(r6)
            goto L_0x00f8
        L_0x00f3:
            android.view.View r1 = r11.mView
            r1.setVisibility(r3)
        L_0x00f8:
            android.animation.Animator r0 = r0.animator
            r0.start()
            goto L_0x0128
        L_0x00fe:
            if (r0 == 0) goto L_0x010c
            android.view.View r1 = r11.mView
            android.view.animation.Animation r4 = r0.animation
            r1.startAnimation(r4)
            android.view.animation.Animation r0 = r0.animation
            r0.start()
        L_0x010c:
            boolean r0 = r11.mHidden
            if (r0 == 0) goto L_0x0119
            boolean r0 = r11.isHideReplaced()
            if (r0 != 0) goto L_0x0119
            r0 = 8
            goto L_0x011a
        L_0x0119:
            r0 = r3
        L_0x011a:
            android.view.View r1 = r11.mView
            r1.setVisibility(r0)
            boolean r0 = r11.isHideReplaced()
            if (r0 == 0) goto L_0x0128
            r11.setHideReplaced(r3)
        L_0x0128:
            boolean r0 = r11.mAdded
            if (r0 == 0) goto L_0x0136
            boolean r0 = r11.mHasMenu
            if (r0 == 0) goto L_0x0136
            boolean r0 = r11.mMenuVisible
            if (r0 == 0) goto L_0x0136
            r10.mNeedMenuInvalidate = r2
        L_0x0136:
            r11.mHiddenChanged = r3
            boolean r10 = r11.mHidden
            r11.onHiddenChanged(r10)
        L_0x013d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0389H.mo4132h(androidx.fragment.app.j):void");
    }

    /* renamed from: i */
    public void mo4134i(C0424j jVar) {
        if (!jVar.mDeferStart) {
            return;
        }
        if (this.mExecutingActions) {
            this.mHavePendingDeferredStart = true;
            return;
        }
        jVar.mDeferStart = false;
        mo4079a(jVar, this.mCurState, 0, 0, false);
    }

    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    public boolean isStateSaved() {
        return this.mStateSaved || this.mStopped;
    }

    /* renamed from: j */
    public void mo4137j(C0424j jVar) {
        boolean z = !jVar.isInBackStack();
        if (!jVar.mDetached || z) {
            synchronized (this.mAdded) {
                this.mAdded.remove(jVar);
            }
            if (jVar.mHasMenu && jVar.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            jVar.mAdded = false;
            jVar.mRemoving = true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: k */
    public void mo4138k(C0424j jVar) {
        if (!isStateSaved()) {
            boolean k = this.f349Ro.mo4162k(jVar);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: l */
    public void mo4139l(C0424j jVar) {
        if (jVar.mInnerView != null) {
            SparseArray sparseArray = this.mStateArray;
            if (sparseArray == null) {
                this.mStateArray = new SparseArray();
            } else {
                sparseArray.clear();
            }
            jVar.mInnerView.saveHierarchyState(this.mStateArray);
            if (this.mStateArray.size() > 0) {
                jVar.mSavedViewState = this.mStateArray;
                this.mStateArray = null;
            }
        }
    }

    /* renamed from: m */
    public void mo4140m(C0424j jVar) {
        if (jVar == null || (this.mActive.get(jVar.mWho) == jVar && (jVar.mHost == null || jVar.getFragmentManager() == this))) {
            C0424j jVar2 = this.mPrimaryNav;
            this.mPrimaryNav = jVar;
            m300w(jVar2);
            m300w(this.mPrimaryNav);
            return;
        }
        throw new IllegalArgumentException("Fragment " + jVar + " is not an active fragment of FragmentManager " + this);
    }

    /* access modifiers changed from: package-private */
    public void moveToState(int i, boolean z) {
        C0429o oVar;
        if (this.mHost == null && i != 0) {
            throw new IllegalStateException("No activity");
        } else if (z || i != this.mCurState) {
            this.mCurState = i;
            int size = this.mAdded.size();
            for (int i2 = 0; i2 < size; i2++) {
                mo4132h((C0424j) this.mAdded.get(i2));
            }
            for (C0424j jVar : this.mActive.values()) {
                if (jVar != null && ((jVar.mRemoving || jVar.mDetached) && !jVar.mIsNewlyAdded)) {
                    mo4132h(jVar);
                }
            }
            startPendingDeferredFragments();
            if (this.mNeedMenuInvalidate && (oVar = this.mHost) != null && this.mCurState == 4) {
                ((C0425k) oVar).this$0.supportInvalidateOptionsMenu();
                this.mNeedMenuInvalidate = false;
            }
        }
    }

    /* renamed from: n */
    public void mo4142n(C0424j jVar) {
        if (jVar.mHidden) {
            jVar.mHidden = false;
            jVar.mHiddenChanged = !jVar.mHiddenChanged;
        }
    }

    public void noteStateNotSaved() {
        this.mStateSaved = false;
        this.mStopped = false;
        int size = this.mAdded.size();
        for (int i = 0; i < size; i++) {
            C0424j jVar = (C0424j) this.mAdded.get(i);
            if (jVar != null) {
                jVar.noteStateNotSaved();
            }
        }
    }

    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        C0424j jVar;
        C0424j jVar2 = null;
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue((String) null, "class");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0385D.Fragment);
        int i = 0;
        if (attributeValue == null) {
            attributeValue = obtainStyledAttributes.getString(0);
        }
        String str2 = attributeValue;
        int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        String string = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (str2 == null || !C0428n.m423b(context.getClassLoader(), str2)) {
            return null;
        }
        if (view != null) {
            i = view.getId();
        }
        if (i == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + str2);
        }
        if (resourceId != -1) {
            jVar2 = findFragmentById(resourceId);
        }
        if (jVar2 == null && string != null) {
            jVar2 = findFragmentByTag(string);
        }
        if (jVar2 == null && i != -1) {
            jVar2 = findFragmentById(i);
        }
        if (jVar2 == null) {
            C0424j a = mo4152vc().mo4424a(context.getClassLoader(), str2);
            a.mFromLayout = true;
            a.mFragmentId = resourceId != 0 ? resourceId : i;
            a.mContainerId = i;
            a.mTag = string;
            a.mInLayout = true;
            a.mFragmentManager = this;
            C0429o oVar = this.mHost;
            a.mHost = oVar;
            a.onInflate(oVar.getContext(), attributeSet, a.mSavedFragmentState);
            mo4084a(a, true);
            jVar = a;
        } else if (!jVar2.mInLayout) {
            jVar2.mInLayout = true;
            C0429o oVar2 = this.mHost;
            jVar2.mHost = oVar2;
            jVar2.onInflate(oVar2.getContext(), attributeSet, jVar2.mSavedFragmentState);
            jVar = jVar2;
        } else {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(i) + " with another fragment for " + str2);
        }
        if (this.mCurState >= 1 || !jVar.mFromLayout) {
            mo4079a(jVar, this.mCurState, 0, 0, false);
        } else {
            mo4079a(jVar, 1, 0, 0, false);
        }
        View view2 = jVar.mView;
        if (view2 != null) {
            if (resourceId != 0) {
                view2.setId(resourceId);
            }
            if (jVar.mView.getTag() == null) {
                jVar.mView.setTag(string);
            }
            return jVar.mView;
        }
        throw new IllegalStateException(C0632a.m1023d("Fragment ", str2, " did not create a view."));
    }

    public void popBackStack(int i, int i2) {
        if (i >= 0) {
            mo4077a((C0386E) new C0387F(this, (String) null, i, i2), false);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i);
    }

    public boolean popBackStackImmediate() {
        C0433s peekChildFragmentManager;
        m288Dn();
        execPendingActions();
        m286Ba(true);
        C0424j jVar = this.mPrimaryNav;
        if (jVar != null && (peekChildFragmentManager = jVar.peekChildFragmentManager()) != null && peekChildFragmentManager.popBackStackImmediate()) {
            return true;
        }
        boolean popBackStackState = popBackStackState(this.mTmpRecords, this.mTmpIsPop, (String) null, -1, 0);
        if (popBackStackState) {
            this.mExecutingActions = true;
            try {
                m298c(this.mTmpRecords, this.mTmpIsPop);
            } finally {
                m289En();
            }
        }
        m291Gn();
        doPendingDeferredStart();
        m287Cn();
        return popBackStackState;
    }

    /* access modifiers changed from: package-private */
    public boolean popBackStackState(ArrayList arrayList, ArrayList arrayList2, String str, int i, int i2) {
        int i3;
        ArrayList arrayList3 = this.mBackStack;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i < 0 && (i2 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.mBackStack.remove(size));
            arrayList2.add(true);
        } else {
            if (str != null || i >= 0) {
                i3 = this.mBackStack.size() - 1;
                while (i3 >= 0) {
                    C0407a aVar = (C0407a) this.mBackStack.get(i3);
                    if ((str != null && str.equals(aVar.mName)) || (i >= 0 && i == aVar.mIndex)) {
                        break;
                    }
                    i3--;
                }
                if (i3 < 0) {
                    return false;
                }
                if ((i2 & 1) != 0) {
                    while (true) {
                        i3--;
                        if (i3 < 0) {
                            break;
                        }
                        C0407a aVar2 = (C0407a) this.mBackStack.get(i3);
                        if ((str == null || !str.equals(aVar2.mName)) && (i < 0 || i != aVar2.mIndex)) {
                            break;
                        }
                    }
                }
            } else {
                i3 = -1;
            }
            if (i3 == this.mBackStack.size() - 1) {
                return false;
            }
            for (int size2 = this.mBackStack.size() - 1; size2 > i3; size2--) {
                arrayList.add(this.mBackStack.remove(size2));
                arrayList2.add(true);
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public Parcelable saveAllState() {
        BackStackState[] backStackStateArr;
        ArrayList arrayList;
        int size;
        Bundle bundle;
        m290Fn();
        Iterator it = this.mActive.values().iterator();
        while (true) {
            backStackStateArr = null;
            if (!it.hasNext()) {
                break;
            }
            C0424j jVar = (C0424j) it.next();
            if (jVar != null) {
                if (jVar.getAnimatingAway() != null) {
                    int stateAfterAnimating = jVar.getStateAfterAnimating();
                    View animatingAway = jVar.getAnimatingAway();
                    Animation animation = animatingAway.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        animatingAway.clearAnimation();
                    }
                    jVar.setAnimatingAway((View) null);
                    mo4079a(jVar, stateAfterAnimating, 0, 0, false);
                } else if (jVar.getAnimator() != null) {
                    jVar.getAnimator().end();
                }
            }
        }
        execPendingActions();
        this.mStateSaved = true;
        if (this.mActive.isEmpty()) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList(this.mActive.size());
        boolean z = false;
        for (C0424j jVar2 : this.mActive.values()) {
            if (jVar2 != null) {
                if (jVar2.mFragmentManager == this) {
                    FragmentState fragmentState = new FragmentState(jVar2);
                    arrayList2.add(fragmentState);
                    if (jVar2.mState <= 0 || fragmentState.mSavedFragmentState != null) {
                        fragmentState.mSavedFragmentState = jVar2.mSavedFragmentState;
                    } else {
                        if (this.mStateBundle == null) {
                            this.mStateBundle = new Bundle();
                        }
                        jVar2.performSaveInstanceState(this.mStateBundle);
                        mo4096d(jVar2, this.mStateBundle, false);
                        if (!this.mStateBundle.isEmpty()) {
                            bundle = this.mStateBundle;
                            this.mStateBundle = null;
                        } else {
                            bundle = null;
                        }
                        if (jVar2.mView != null) {
                            mo4139l(jVar2);
                        }
                        if (jVar2.mSavedViewState != null) {
                            if (bundle == null) {
                                bundle = new Bundle();
                            }
                            bundle.putSparseParcelableArray("android:view_state", jVar2.mSavedViewState);
                        }
                        if (!jVar2.mUserVisibleHint) {
                            if (bundle == null) {
                                bundle = new Bundle();
                            }
                            bundle.putBoolean("android:user_visible_hint", jVar2.mUserVisibleHint);
                        }
                        fragmentState.mSavedFragmentState = bundle;
                        String str = jVar2.mTargetWho;
                        if (str != null) {
                            C0424j jVar3 = (C0424j) this.mActive.get(str);
                            if (jVar3 != null) {
                                if (fragmentState.mSavedFragmentState == null) {
                                    fragmentState.mSavedFragmentState = new Bundle();
                                }
                                Bundle bundle2 = fragmentState.mSavedFragmentState;
                                if (jVar3.mFragmentManager == this) {
                                    bundle2.putString("android:target_state", jVar3.mWho);
                                    int i = jVar2.mTargetRequestCode;
                                    if (i != 0) {
                                        fragmentState.mSavedFragmentState.putInt("android:target_req_state", i);
                                    }
                                } else {
                                    m294a((RuntimeException) new IllegalStateException(C0632a.m1015a("Fragment ", (Object) jVar3, " is not currently in the FragmentManager")));
                                    throw null;
                                }
                            } else {
                                m294a((RuntimeException) new IllegalStateException("Failure saving state: " + jVar2 + " has target not in fragment manager: " + jVar2.mTargetWho));
                                throw null;
                            }
                        }
                    }
                    z = true;
                } else {
                    m294a((RuntimeException) new IllegalStateException(C0632a.m1015a("Failure saving state: active ", (Object) jVar2, " was removed from the FragmentManager")));
                    throw null;
                }
            }
        }
        if (!z) {
            return null;
        }
        int size2 = this.mAdded.size();
        if (size2 > 0) {
            arrayList = new ArrayList(size2);
            Iterator it2 = this.mAdded.iterator();
            while (it2.hasNext()) {
                C0424j jVar4 = (C0424j) it2.next();
                arrayList.add(jVar4.mWho);
                if (jVar4.mFragmentManager != this) {
                    m294a((RuntimeException) new IllegalStateException(C0632a.m1015a("Failure saving state: active ", (Object) jVar4, " was removed from the FragmentManager")));
                    throw null;
                }
            }
        } else {
            arrayList = null;
        }
        ArrayList arrayList3 = this.mBackStack;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i2 = 0; i2 < size; i2++) {
                backStackStateArr[i2] = new BackStackState((C0407a) this.mBackStack.get(i2));
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.mActive = arrayList2;
        fragmentManagerState.mAdded = arrayList;
        fragmentManagerState.mBackStack = backStackStateArr;
        C0424j jVar5 = this.mPrimaryNav;
        if (jVar5 != null) {
            fragmentManagerState.f340Xo = jVar5.mWho;
        }
        fragmentManagerState.mNextFragmentIndex = this.mNextFragmentIndex;
        return fragmentManagerState;
    }

    /* access modifiers changed from: package-private */
    public void startPendingDeferredFragments() {
        for (C0424j jVar : this.mActive.values()) {
            if (jVar != null) {
                mo4134i(jVar);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        C0424j jVar = this.mParent;
        if (jVar != null) {
            DebugUtils.buildShortClassTag(jVar, sb);
        } else {
            DebugUtils.buildShortClassTag(this.mHost, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    /* renamed from: vc */
    public C0428n mo4152vc() {
        if (super.mo4152vc() == C0433s.f417Qo) {
            C0424j jVar = this.mParent;
            if (jVar != null) {
                return jVar.mFragmentManager.mo4152vc();
            }
            mo4429a(new C0440z(this));
        }
        return super.mo4152vc();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: wc */
    public void mo4153wc() {
        synchronized (this) {
            boolean z = false;
            boolean z2 = this.mPostponedTransactions != null && !this.mPostponedTransactions.isEmpty();
            if (this.mPendingActions != null && this.mPendingActions.size() == 1) {
                z = true;
            }
            if (z2 || z) {
                this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                this.mHost.getHandler().post(this.mExecCommit);
                m291Gn();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4118e(androidx.fragment.app.C0424j r3, boolean r4) {
        /*
            r2 = this;
            androidx.fragment.app.j r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            androidx.fragment.app.s r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof androidx.fragment.app.C0389H
            if (r1 == 0) goto L_0x0012
            androidx.fragment.app.H r0 = (androidx.fragment.app.C0389H) r0
            r1 = 1
            r0.mo4118e(r3, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            androidx.fragment.app.C r3 = (androidx.fragment.app.C0384C) r3
            if (r4 == 0) goto L_0x002b
            boolean r0 = r3.f339Uo
            if (r0 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            androidx.fragment.app.q r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0389H.mo4118e(androidx.fragment.app.j, boolean):void");
    }

    /* renamed from: b */
    public void mo4086b(C0386E e, boolean z) {
        if (!z || (this.mHost != null && !this.mDestroyed)) {
            m286Ba(z);
            if (e.generateOps(this.mTmpRecords, this.mTmpIsPop)) {
                this.mExecutingActions = true;
                try {
                    m298c(this.mTmpRecords, this.mTmpIsPop);
                } finally {
                    m289En();
                }
            }
            m291Gn();
            doPendingDeferredStart();
            m287Cn();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4123f(androidx.fragment.app.C0424j r3, boolean r4) {
        /*
            r2 = this;
            androidx.fragment.app.j r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            androidx.fragment.app.s r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof androidx.fragment.app.C0389H
            if (r1 == 0) goto L_0x0012
            androidx.fragment.app.H r0 = (androidx.fragment.app.C0389H) r0
            r1 = 1
            r0.mo4123f(r3, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            androidx.fragment.app.C r3 = (androidx.fragment.app.C0384C) r3
            if (r4 == 0) goto L_0x002b
            boolean r0 = r3.f339Uo
            if (r0 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            androidx.fragment.app.q r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0389H.mo4123f(androidx.fragment.app.j, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* renamed from: g */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4129g(androidx.fragment.app.C0424j r3, boolean r4) {
        /*
            r2 = this;
            androidx.fragment.app.j r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            androidx.fragment.app.s r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof androidx.fragment.app.C0389H
            if (r1 == 0) goto L_0x0012
            androidx.fragment.app.H r0 = (androidx.fragment.app.C0389H) r0
            r1 = 1
            r0.mo4129g(r3, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            androidx.fragment.app.C r3 = (androidx.fragment.app.C0384C) r3
            if (r4 == 0) goto L_0x002b
            boolean r0 = r3.f339Uo
            if (r0 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            androidx.fragment.app.q r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0389H.mo4129g(androidx.fragment.app.j, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4097d(androidx.fragment.app.C0424j r3, boolean r4) {
        /*
            r2 = this;
            androidx.fragment.app.j r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            androidx.fragment.app.s r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof androidx.fragment.app.C0389H
            if (r1 == 0) goto L_0x0012
            androidx.fragment.app.H r0 = (androidx.fragment.app.C0389H) r0
            r1 = 1
            r0.mo4097d(r3, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            androidx.fragment.app.C r3 = (androidx.fragment.app.C0384C) r3
            if (r4 == 0) goto L_0x002b
            boolean r0 = r3.f339Uo
            if (r0 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            androidx.fragment.app.q r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0389H.mo4097d(androidx.fragment.app.j, boolean):void");
    }

    /* renamed from: a */
    static C0382A m292a(float f, float f2, float f3, float f4) {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(f, f2, f, f2, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(f346So);
        scaleAnimation.setDuration(220);
        animationSet.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(f3, f4);
        alphaAnimation.setInterpolator(f347To);
        alphaAnimation.setDuration(220);
        animationSet.addAnimation(alphaAnimation);
        return new C0382A((Animation) animationSet);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003b, code lost:
        return false;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m297b(java.util.ArrayList r5, java.util.ArrayList r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.ArrayList r0 = r4.mPendingActions     // Catch:{ all -> 0x003c }
            r1 = 0
            if (r0 == 0) goto L_0x003a
            java.util.ArrayList r0 = r4.mPendingActions     // Catch:{ all -> 0x003c }
            int r0 = r0.size()     // Catch:{ all -> 0x003c }
            if (r0 != 0) goto L_0x000f
            goto L_0x003a
        L_0x000f:
            java.util.ArrayList r0 = r4.mPendingActions     // Catch:{ all -> 0x003c }
            int r0 = r0.size()     // Catch:{ all -> 0x003c }
            r2 = r1
        L_0x0016:
            if (r1 >= r0) goto L_0x0028
            java.util.ArrayList r3 = r4.mPendingActions     // Catch:{ all -> 0x003c }
            java.lang.Object r3 = r3.get(r1)     // Catch:{ all -> 0x003c }
            androidx.fragment.app.E r3 = (androidx.fragment.app.C0386E) r3     // Catch:{ all -> 0x003c }
            boolean r3 = r3.generateOps(r5, r6)     // Catch:{ all -> 0x003c }
            r2 = r2 | r3
            int r1 = r1 + 1
            goto L_0x0016
        L_0x0028:
            java.util.ArrayList r5 = r4.mPendingActions     // Catch:{ all -> 0x003c }
            r5.clear()     // Catch:{ all -> 0x003c }
            androidx.fragment.app.o r5 = r4.mHost     // Catch:{ all -> 0x003c }
            android.os.Handler r5 = r5.getHandler()     // Catch:{ all -> 0x003c }
            java.lang.Runnable r6 = r4.mExecCommit     // Catch:{ all -> 0x003c }
            r5.removeCallbacks(r6)     // Catch:{ all -> 0x003c }
            monitor-exit(r4)     // Catch:{ all -> 0x003c }
            return r2
        L_0x003a:
            monitor-exit(r4)     // Catch:{ all -> 0x003c }
            return r1
        L_0x003c:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x003c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0389H.m297b(java.util.ArrayList, java.util.ArrayList):boolean");
    }

    /* renamed from: c */
    private void m298c(ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList != null && !arrayList.isEmpty()) {
            if (arrayList2 == null || arrayList.size() != arrayList2.size()) {
                throw new IllegalStateException("Internal error with the back stack records");
            }
            m295a(arrayList, arrayList2);
            int size = arrayList.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                if (!((C0407a) arrayList.get(i)).mReorderingAllowed) {
                    if (i2 != i) {
                        m296a(arrayList, arrayList2, i2, i);
                    }
                    i2 = i + 1;
                    if (((Boolean) arrayList2.get(i)).booleanValue()) {
                        while (i2 < size && ((Boolean) arrayList2.get(i2)).booleanValue() && !((C0407a) arrayList.get(i2)).mReorderingAllowed) {
                            i2++;
                        }
                    }
                    m296a(arrayList, arrayList2, i, i2);
                    i = i2 - 1;
                }
                i++;
            }
            if (i2 != size) {
                m296a(arrayList, arrayList2, i2, size);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4096d(androidx.fragment.app.C0424j r3, android.os.Bundle r4, boolean r5) {
        /*
            r2 = this;
            androidx.fragment.app.j r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            androidx.fragment.app.s r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof androidx.fragment.app.C0389H
            if (r1 == 0) goto L_0x0012
            androidx.fragment.app.H r0 = (androidx.fragment.app.C0389H) r0
            r1 = 1
            r0.mo4096d(r3, r4, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            androidx.fragment.app.C r3 = (androidx.fragment.app.C0384C) r3
            if (r5 == 0) goto L_0x002b
            boolean r4 = r3.f339Uo
            if (r4 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            androidx.fragment.app.q r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0389H.mo4096d(androidx.fragment.app.j, android.os.Bundle, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4088b(androidx.fragment.app.C0424j r3, android.content.Context r4, boolean r5) {
        /*
            r2 = this;
            androidx.fragment.app.j r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            androidx.fragment.app.s r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof androidx.fragment.app.C0389H
            if (r1 == 0) goto L_0x0012
            androidx.fragment.app.H r0 = (androidx.fragment.app.C0389H) r0
            r1 = 1
            r0.mo4088b((androidx.fragment.app.C0424j) r3, (android.content.Context) r4, (boolean) r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            androidx.fragment.app.C r3 = (androidx.fragment.app.C0384C) r3
            if (r5 == 0) goto L_0x002b
            boolean r4 = r3.f339Uo
            if (r4 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            androidx.fragment.app.q r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0389H.mo4088b(androidx.fragment.app.j, android.content.Context, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004f A[SYNTHETIC, Splitter:B:23:0x004f] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.fragment.app.C0382A mo4074a(androidx.fragment.app.C0424j r7, int r8, boolean r9, int r10) {
        /*
            r6 = this;
            int r0 = r7.getNextAnim()
            android.view.animation.Animation r1 = r7.onCreateAnimation(r8, r9, r0)
            if (r1 == 0) goto L_0x0010
            androidx.fragment.app.A r6 = new androidx.fragment.app.A
            r6.<init>((android.view.animation.Animation) r1)
            return r6
        L_0x0010:
            android.animation.Animator r7 = r7.onCreateAnimator(r8, r9, r0)
            if (r7 == 0) goto L_0x001c
            androidx.fragment.app.A r6 = new androidx.fragment.app.A
            r6.<init>((android.animation.Animator) r7)
            return r6
        L_0x001c:
            r7 = 1
            r1 = 0
            if (r0 == 0) goto L_0x0077
            androidx.fragment.app.o r2 = r6.mHost
            android.content.Context r2 = r2.getContext()
            android.content.res.Resources r2 = r2.getResources()
            java.lang.String r2 = r2.getResourceTypeName(r0)
            java.lang.String r3 = "anim"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x004c
            androidx.fragment.app.o r3 = r6.mHost     // Catch:{ NotFoundException -> 0x004a, RuntimeException -> 0x004c }
            android.content.Context r3 = r3.getContext()     // Catch:{ NotFoundException -> 0x004a, RuntimeException -> 0x004c }
            android.view.animation.Animation r3 = android.view.animation.AnimationUtils.loadAnimation(r3, r0)     // Catch:{ NotFoundException -> 0x004a, RuntimeException -> 0x004c }
            if (r3 == 0) goto L_0x0048
            androidx.fragment.app.A r4 = new androidx.fragment.app.A     // Catch:{ NotFoundException -> 0x004a, RuntimeException -> 0x004c }
            r4.<init>((android.view.animation.Animation) r3)     // Catch:{ NotFoundException -> 0x004a, RuntimeException -> 0x004c }
            return r4
        L_0x0048:
            r3 = r7
            goto L_0x004d
        L_0x004a:
            r6 = move-exception
            throw r6
        L_0x004c:
            r3 = r1
        L_0x004d:
            if (r3 != 0) goto L_0x0077
            androidx.fragment.app.o r3 = r6.mHost     // Catch:{ RuntimeException -> 0x0061 }
            android.content.Context r3 = r3.getContext()     // Catch:{ RuntimeException -> 0x0061 }
            android.animation.Animator r3 = android.animation.AnimatorInflater.loadAnimator(r3, r0)     // Catch:{ RuntimeException -> 0x0061 }
            if (r3 == 0) goto L_0x0077
            androidx.fragment.app.A r4 = new androidx.fragment.app.A     // Catch:{ RuntimeException -> 0x0061 }
            r4.<init>((android.animation.Animator) r3)     // Catch:{ RuntimeException -> 0x0061 }
            return r4
        L_0x0061:
            r3 = move-exception
            if (r2 != 0) goto L_0x0076
            androidx.fragment.app.o r2 = r6.mHost
            android.content.Context r2 = r2.getContext()
            android.view.animation.Animation r0 = android.view.animation.AnimationUtils.loadAnimation(r2, r0)
            if (r0 == 0) goto L_0x0077
            androidx.fragment.app.A r6 = new androidx.fragment.app.A
            r6.<init>((android.view.animation.Animation) r0)
            return r6
        L_0x0076:
            throw r3
        L_0x0077:
            r0 = 0
            if (r8 != 0) goto L_0x007b
            return r0
        L_0x007b:
            r2 = 4097(0x1001, float:5.741E-42)
            if (r8 == r2) goto L_0x0095
            r2 = 4099(0x1003, float:5.744E-42)
            if (r8 == r2) goto L_0x008f
            r2 = 8194(0x2002, float:1.1482E-41)
            if (r8 == r2) goto L_0x0089
            r8 = -1
            goto L_0x009a
        L_0x0089:
            if (r9 == 0) goto L_0x008d
            r8 = 3
            goto L_0x009a
        L_0x008d:
            r8 = 4
            goto L_0x009a
        L_0x008f:
            if (r9 == 0) goto L_0x0093
            r8 = 5
            goto L_0x009a
        L_0x0093:
            r8 = 6
            goto L_0x009a
        L_0x0095:
            if (r9 == 0) goto L_0x0099
            r8 = r7
            goto L_0x009a
        L_0x0099:
            r8 = 2
        L_0x009a:
            if (r8 >= 0) goto L_0x009d
            return r0
        L_0x009d:
            r9 = 1064933786(0x3f79999a, float:0.975)
            r2 = 0
            r3 = 1065353216(0x3f800000, float:1.0)
            r4 = 220(0xdc, double:1.087E-321)
            switch(r8) {
                case 1: goto L_0x00e5;
                case 2: goto L_0x00e0;
                case 3: goto L_0x00db;
                case 4: goto L_0x00d3;
                case 5: goto L_0x00c0;
                case 6: goto L_0x00ad;
                default: goto L_0x00a8;
            }
        L_0x00a8:
            if (r10 != 0) goto L_0x010d
            androidx.fragment.app.o r8 = r6.mHost
            goto L_0x00ec
        L_0x00ad:
            android.view.animation.AlphaAnimation r6 = new android.view.animation.AlphaAnimation
            r6.<init>(r3, r2)
            android.view.animation.Interpolator r7 = f347To
            r6.setInterpolator(r7)
            r6.setDuration(r4)
            androidx.fragment.app.A r7 = new androidx.fragment.app.A
            r7.<init>((android.view.animation.Animation) r6)
            return r7
        L_0x00c0:
            android.view.animation.AlphaAnimation r6 = new android.view.animation.AlphaAnimation
            r6.<init>(r2, r3)
            android.view.animation.Interpolator r7 = f347To
            r6.setInterpolator(r7)
            r6.setDuration(r4)
            androidx.fragment.app.A r7 = new androidx.fragment.app.A
            r7.<init>((android.view.animation.Animation) r6)
            return r7
        L_0x00d3:
            r6 = 1065982362(0x3f89999a, float:1.075)
            androidx.fragment.app.A r6 = m292a((float) r3, (float) r6, (float) r3, (float) r2)
            return r6
        L_0x00db:
            androidx.fragment.app.A r6 = m292a((float) r9, (float) r3, (float) r2, (float) r3)
            return r6
        L_0x00e0:
            androidx.fragment.app.A r6 = m292a((float) r3, (float) r9, (float) r3, (float) r2)
            return r6
        L_0x00e5:
            r6 = 1066401792(0x3f900000, float:1.125)
            androidx.fragment.app.A r6 = m292a((float) r6, (float) r3, (float) r2, (float) r3)
            return r6
        L_0x00ec:
            androidx.fragment.app.k r8 = (androidx.fragment.app.C0425k) r8
            androidx.fragment.app.FragmentActivity r8 = r8.this$0
            android.view.Window r8 = r8.getWindow()
            if (r8 == 0) goto L_0x00f7
            goto L_0x00f8
        L_0x00f7:
            r7 = r1
        L_0x00f8:
            if (r7 == 0) goto L_0x010d
            androidx.fragment.app.o r6 = r6.mHost
            androidx.fragment.app.k r6 = (androidx.fragment.app.C0425k) r6
            androidx.fragment.app.FragmentActivity r6 = r6.this$0
            android.view.Window r6 = r6.getWindow()
            if (r6 != 0) goto L_0x0107
            goto L_0x010d
        L_0x0107:
            android.view.WindowManager$LayoutParams r6 = r6.getAttributes()
            int r6 = r6.windowAnimations
        L_0x010d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0389H.mo4074a(androidx.fragment.app.j, int, boolean, int):androidx.fragment.app.A");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4093c(androidx.fragment.app.C0424j r3, android.os.Bundle r4, boolean r5) {
        /*
            r2 = this;
            androidx.fragment.app.j r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            androidx.fragment.app.s r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof androidx.fragment.app.C0389H
            if (r1 == 0) goto L_0x0012
            androidx.fragment.app.H r0 = (androidx.fragment.app.C0389H) r0
            r1 = 1
            r0.mo4093c(r3, r4, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            androidx.fragment.app.C r3 = (androidx.fragment.app.C0384C) r3
            if (r5 == 0) goto L_0x002b
            boolean r4 = r3.f339Uo
            if (r4 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            androidx.fragment.app.q r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0389H.mo4093c(androidx.fragment.app.j, android.os.Bundle, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4089b(androidx.fragment.app.C0424j r3, android.os.Bundle r4, boolean r5) {
        /*
            r2 = this;
            androidx.fragment.app.j r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            androidx.fragment.app.s r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof androidx.fragment.app.C0389H
            if (r1 == 0) goto L_0x0012
            androidx.fragment.app.H r0 = (androidx.fragment.app.C0389H) r0
            r1 = 1
            r0.mo4089b((androidx.fragment.app.C0424j) r3, (android.os.Bundle) r4, (boolean) r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            androidx.fragment.app.C r3 = (androidx.fragment.app.C0384C) r3
            if (r5 == 0) goto L_0x002b
            boolean r4 = r3.f339Uo
            if (r4 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            androidx.fragment.app.q r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0389H.mo4089b(androidx.fragment.app.j, android.os.Bundle, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4094c(androidx.fragment.app.C0424j r3, boolean r4) {
        /*
            r2 = this;
            androidx.fragment.app.j r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            androidx.fragment.app.s r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof androidx.fragment.app.C0389H
            if (r1 == 0) goto L_0x0012
            androidx.fragment.app.H r0 = (androidx.fragment.app.C0389H) r0
            r1 = 1
            r0.mo4094c((androidx.fragment.app.C0424j) r3, (boolean) r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            androidx.fragment.app.C r3 = (androidx.fragment.app.C0384C) r3
            if (r4 == 0) goto L_0x002b
            boolean r0 = r3.f339Uo
            if (r0 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            androidx.fragment.app.q r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0389H.mo4094c(androidx.fragment.app.j, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4090b(androidx.fragment.app.C0424j r3, boolean r4) {
        /*
            r2 = this;
            androidx.fragment.app.j r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            androidx.fragment.app.s r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof androidx.fragment.app.C0389H
            if (r1 == 0) goto L_0x0012
            androidx.fragment.app.H r0 = (androidx.fragment.app.C0389H) r0
            r1 = 1
            r0.mo4090b((androidx.fragment.app.C0424j) r3, (boolean) r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            androidx.fragment.app.C r3 = (androidx.fragment.app.C0384C) r3
            if (r4 == 0) goto L_0x002b
            boolean r0 = r3.f339Uo
            if (r0 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            androidx.fragment.app.q r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0389H.mo4090b(androidx.fragment.app.j, boolean):void");
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView((View) null, str, context, attributeSet);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: char} */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: type inference failed for: r11v1 */
    /* JADX WARNING: type inference failed for: r0v129, types: [int] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0082, code lost:
        if (r0 != 3) goto L_0x04c9;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x02ec  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02f5  */
    /* JADX WARNING: Removed duplicated region for block: B:262:0x04ce  */
    /* JADX WARNING: Removed duplicated region for block: B:269:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4079a(androidx.fragment.app.C0424j r17, int r18, int r19, int r20, boolean r21) {
        /*
            r16 = this;
            r6 = r16
            r7 = r17
            boolean r0 = r7.mAdded
            r8 = 1
            if (r0 == 0) goto L_0x0011
            boolean r0 = r7.mDetached
            if (r0 == 0) goto L_0x000e
            goto L_0x0011
        L_0x000e:
            r0 = r18
            goto L_0x0016
        L_0x0011:
            r0 = r18
            if (r0 <= r8) goto L_0x0016
            r0 = r8
        L_0x0016:
            boolean r1 = r7.mRemoving
            if (r1 == 0) goto L_0x002a
            int r1 = r7.mState
            if (r0 <= r1) goto L_0x002a
            if (r1 != 0) goto L_0x0028
            boolean r0 = r17.isInBackStack()
            if (r0 == 0) goto L_0x0028
            r0 = r8
            goto L_0x002a
        L_0x0028:
            int r0 = r7.mState
        L_0x002a:
            boolean r1 = r7.mDeferStart
            r9 = 3
            r10 = 2
            if (r1 == 0) goto L_0x0037
            int r1 = r7.mState
            if (r1 >= r9) goto L_0x0037
            if (r0 <= r10) goto L_0x0037
            r0 = r10
        L_0x0037:
            androidx.lifecycle.Lifecycle$State r1 = r7.mMaxState
            androidx.lifecycle.Lifecycle$State r2 = androidx.lifecycle.Lifecycle$State.CREATED
            if (r1 != r2) goto L_0x0042
            int r0 = java.lang.Math.min(r0, r8)
            goto L_0x004a
        L_0x0042:
            int r1 = r1.ordinal()
            int r0 = java.lang.Math.min(r0, r1)
        L_0x004a:
            r11 = r0
            int r0 = r7.mState
            r12 = 0
            r13 = 0
            if (r0 > r11) goto L_0x0301
            boolean r0 = r7.mFromLayout
            if (r0 == 0) goto L_0x005a
            boolean r0 = r7.mInLayout
            if (r0 != 0) goto L_0x005a
            return
        L_0x005a:
            android.view.View r0 = r17.getAnimatingAway()
            if (r0 != 0) goto L_0x0066
            android.animation.Animator r0 = r17.getAnimator()
            if (r0 == 0) goto L_0x007a
        L_0x0066:
            r7.setAnimatingAway(r13)
            r7.setAnimator(r13)
            int r2 = r17.getStateAfterAnimating()
            r3 = 0
            r4 = 0
            r5 = 1
            r0 = r16
            r1 = r17
            r0.mo4079a(r1, r2, r3, r4, r5)
        L_0x007a:
            int r0 = r7.mState
            if (r0 == 0) goto L_0x0086
            if (r0 == r8) goto L_0x01ed
            if (r0 == r10) goto L_0x02ea
            if (r0 == r9) goto L_0x02f3
            goto L_0x04c9
        L_0x0086:
            if (r11 <= 0) goto L_0x01ed
            android.os.Bundle r0 = r7.mSavedFragmentState
            if (r0 == 0) goto L_0x0112
            androidx.fragment.app.o r1 = r6.mHost
            android.content.Context r1 = r1.getContext()
            java.lang.ClassLoader r1 = r1.getClassLoader()
            r0.setClassLoader(r1)
            android.os.Bundle r0 = r7.mSavedFragmentState
            java.lang.String r1 = "android:view_state"
            android.util.SparseArray r0 = r0.getSparseParcelableArray(r1)
            r7.mSavedViewState = r0
            android.os.Bundle r0 = r7.mSavedFragmentState
            java.lang.String r1 = "android:target_state"
            java.lang.String r0 = r0.getString(r1)
            if (r0 != 0) goto L_0x00af
            r2 = r13
            goto L_0x00b9
        L_0x00af:
            java.util.HashMap r2 = r6.mActive
            java.lang.Object r2 = r2.get(r0)
            androidx.fragment.app.j r2 = (androidx.fragment.app.C0424j) r2
            if (r2 == 0) goto L_0x00f0
        L_0x00b9:
            if (r2 == 0) goto L_0x00be
            java.lang.String r0 = r2.mWho
            goto L_0x00bf
        L_0x00be:
            r0 = r13
        L_0x00bf:
            r7.mTargetWho = r0
            java.lang.String r0 = r7.mTargetWho
            if (r0 == 0) goto L_0x00cf
            android.os.Bundle r0 = r7.mSavedFragmentState
            java.lang.String r1 = "android:target_req_state"
            int r0 = r0.getInt(r1, r12)
            r7.mTargetRequestCode = r0
        L_0x00cf:
            java.lang.Boolean r0 = r7.mSavedUserVisibleHint
            if (r0 == 0) goto L_0x00dc
            boolean r0 = r0.booleanValue()
            r7.mUserVisibleHint = r0
            r7.mSavedUserVisibleHint = r13
            goto L_0x00e6
        L_0x00dc:
            android.os.Bundle r0 = r7.mSavedFragmentState
            java.lang.String r1 = "android:user_visible_hint"
            boolean r0 = r0.getBoolean(r1, r8)
            r7.mUserVisibleHint = r0
        L_0x00e6:
            boolean r0 = r7.mUserVisibleHint
            if (r0 != 0) goto L_0x0112
            r7.mDeferStart = r8
            if (r11 <= r10) goto L_0x0112
            r11 = r10
            goto L_0x0112
        L_0x00f0:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Fragment no longer exists for key "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = ": unique id "
            r3.append(r1)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            r6.m294a((java.lang.RuntimeException) r2)
            throw r13
        L_0x0112:
            androidx.fragment.app.o r0 = r6.mHost
            r7.mHost = r0
            androidx.fragment.app.j r1 = r6.mParent
            r7.mParentFragment = r1
            if (r1 == 0) goto L_0x011f
            androidx.fragment.app.H r0 = r1.mChildFragmentManager
            goto L_0x0121
        L_0x011f:
            androidx.fragment.app.H r0 = r0.mFragmentManager
        L_0x0121:
            r7.mFragmentManager = r0
            androidx.fragment.app.j r0 = r7.mTarget
            java.lang.String r9 = " that does not belong to this FragmentManager!"
            java.lang.String r14 = " declared target fragment "
            java.lang.String r15 = "Fragment "
            if (r0 == 0) goto L_0x016f
            java.util.HashMap r1 = r6.mActive
            java.lang.String r0 = r0.mWho
            java.lang.Object r0 = r1.get(r0)
            androidx.fragment.app.j r1 = r7.mTarget
            if (r0 != r1) goto L_0x014f
            int r0 = r1.mState
            if (r0 >= r8) goto L_0x0146
            r2 = 1
            r3 = 0
            r4 = 0
            r5 = 1
            r0 = r16
            r0.mo4079a(r1, r2, r3, r4, r5)
        L_0x0146:
            androidx.fragment.app.j r0 = r7.mTarget
            java.lang.String r0 = r0.mWho
            r7.mTargetWho = r0
            r7.mTarget = r13
            goto L_0x016f
        L_0x014f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r15)
            r1.append(r7)
            r1.append(r14)
            androidx.fragment.app.j r2 = r7.mTarget
            r1.append(r2)
            r1.append(r9)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x016f:
            java.lang.String r0 = r7.mTargetWho
            if (r0 == 0) goto L_0x01ac
            java.util.HashMap r1 = r6.mActive
            java.lang.Object r0 = r1.get(r0)
            r1 = r0
            androidx.fragment.app.j r1 = (androidx.fragment.app.C0424j) r1
            if (r1 == 0) goto L_0x018c
            int r0 = r1.mState
            if (r0 >= r8) goto L_0x01ac
            r2 = 1
            r3 = 0
            r4 = 0
            r5 = 1
            r0 = r16
            r0.mo4079a(r1, r2, r3, r4, r5)
            goto L_0x01ac
        L_0x018c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r15)
            r1.append(r7)
            r1.append(r14)
            java.lang.String r2 = r7.mTargetWho
            r1.append(r2)
            r1.append(r9)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x01ac:
            androidx.fragment.app.o r0 = r6.mHost
            android.content.Context r0 = r0.getContext()
            r6.mo4088b((androidx.fragment.app.C0424j) r7, (android.content.Context) r0, (boolean) r12)
            r17.performAttach()
            androidx.fragment.app.j r0 = r7.mParentFragment
            if (r0 != 0) goto L_0x01c6
            androidx.fragment.app.o r0 = r6.mHost
            androidx.fragment.app.k r0 = (androidx.fragment.app.C0425k) r0
            androidx.fragment.app.FragmentActivity r0 = r0.this$0
            r0.onAttachFragment(r7)
            goto L_0x01c9
        L_0x01c6:
            r0.onAttachFragment(r7)
        L_0x01c9:
            androidx.fragment.app.o r0 = r6.mHost
            android.content.Context r0 = r0.getContext()
            r6.mo4080a((androidx.fragment.app.C0424j) r7, (android.content.Context) r0, (boolean) r12)
            boolean r0 = r7.mIsCreated
            if (r0 != 0) goto L_0x01e6
            android.os.Bundle r0 = r7.mSavedFragmentState
            r6.mo4093c(r7, r0, r12)
            android.os.Bundle r0 = r7.mSavedFragmentState
            r7.performCreate(r0)
            android.os.Bundle r0 = r7.mSavedFragmentState
            r6.mo4089b((androidx.fragment.app.C0424j) r7, (android.os.Bundle) r0, (boolean) r12)
            goto L_0x01ed
        L_0x01e6:
            android.os.Bundle r0 = r7.mSavedFragmentState
            r7.restoreChildFragmentState(r0)
            r7.mState = r8
        L_0x01ed:
            r0 = 8
            if (r11 <= 0) goto L_0x0227
            boolean r1 = r7.mFromLayout
            if (r1 == 0) goto L_0x0227
            boolean r1 = r7.mPerformedCreateView
            if (r1 != 0) goto L_0x0227
            android.os.Bundle r1 = r7.mSavedFragmentState
            android.view.LayoutInflater r1 = r7.performGetLayoutInflater(r1)
            android.os.Bundle r2 = r7.mSavedFragmentState
            r7.performCreateView(r1, r13, r2)
            android.view.View r1 = r7.mView
            if (r1 == 0) goto L_0x0225
            r7.mInnerView = r1
            r1.setSaveFromParentEnabled(r12)
            boolean r1 = r7.mHidden
            if (r1 == 0) goto L_0x0216
            android.view.View r1 = r7.mView
            r1.setVisibility(r0)
        L_0x0216:
            android.view.View r1 = r7.mView
            android.os.Bundle r2 = r7.mSavedFragmentState
            r7.onViewCreated(r1, r2)
            android.view.View r1 = r7.mView
            android.os.Bundle r2 = r7.mSavedFragmentState
            r6.mo4082a((androidx.fragment.app.C0424j) r7, (android.view.View) r1, (android.os.Bundle) r2, (boolean) r12)
            goto L_0x0227
        L_0x0225:
            r7.mInnerView = r13
        L_0x0227:
            if (r11 <= r8) goto L_0x02ea
            boolean r1 = r7.mFromLayout
            if (r1 != 0) goto L_0x02d5
            int r1 = r7.mContainerId
            if (r1 == 0) goto L_0x028d
            r2 = -1
            if (r1 == r2) goto L_0x027c
            androidx.fragment.app.l r2 = r6.mContainer
            android.view.View r1 = r2.onFindViewById(r1)
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            if (r1 != 0) goto L_0x028e
            boolean r2 = r7.mRestored
            if (r2 == 0) goto L_0x0243
            goto L_0x028e
        L_0x0243:
            android.content.res.Resources r0 = r17.getResources()     // Catch:{ NotFoundException -> 0x024e }
            int r1 = r7.mContainerId     // Catch:{ NotFoundException -> 0x024e }
            java.lang.String r0 = r0.getResourceName(r1)     // Catch:{ NotFoundException -> 0x024e }
            goto L_0x0250
        L_0x024e:
            java.lang.String r0 = "unknown"
        L_0x0250:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "No view found for id 0x"
            java.lang.StringBuilder r2 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r2)
            int r3 = r7.mContainerId
            java.lang.String r3 = java.lang.Integer.toHexString(r3)
            r2.append(r3)
            java.lang.String r3 = " ("
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = ") for fragment "
            r2.append(r0)
            r2.append(r7)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            r6.m294a((java.lang.RuntimeException) r1)
            throw r13
        L_0x027c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Cannot create fragment "
            java.lang.String r2 = " for a container view with no id"
            java.lang.String r1 = p026b.p027a.p030b.p031a.C0632a.m1015a((java.lang.String) r1, (java.lang.Object) r7, (java.lang.String) r2)
            r0.<init>(r1)
            r6.m294a((java.lang.RuntimeException) r0)
            throw r13
        L_0x028d:
            r1 = r13
        L_0x028e:
            r7.mContainer = r1
            android.os.Bundle r2 = r7.mSavedFragmentState
            android.view.LayoutInflater r2 = r7.performGetLayoutInflater(r2)
            android.os.Bundle r3 = r7.mSavedFragmentState
            r7.performCreateView(r2, r1, r3)
            android.view.View r2 = r7.mView
            if (r2 == 0) goto L_0x02d3
            r7.mInnerView = r2
            r2.setSaveFromParentEnabled(r12)
            if (r1 == 0) goto L_0x02ab
            android.view.View r2 = r7.mView
            r1.addView(r2)
        L_0x02ab:
            boolean r1 = r7.mHidden
            if (r1 == 0) goto L_0x02b4
            android.view.View r1 = r7.mView
            r1.setVisibility(r0)
        L_0x02b4:
            android.view.View r0 = r7.mView
            android.os.Bundle r1 = r7.mSavedFragmentState
            r7.onViewCreated(r0, r1)
            android.view.View r0 = r7.mView
            android.os.Bundle r1 = r7.mSavedFragmentState
            r6.mo4082a((androidx.fragment.app.C0424j) r7, (android.view.View) r0, (android.os.Bundle) r1, (boolean) r12)
            android.view.View r0 = r7.mView
            int r0 = r0.getVisibility()
            if (r0 != 0) goto L_0x02cf
            android.view.ViewGroup r0 = r7.mContainer
            if (r0 == 0) goto L_0x02cf
            goto L_0x02d0
        L_0x02cf:
            r8 = r12
        L_0x02d0:
            r7.mIsNewlyAdded = r8
            goto L_0x02d5
        L_0x02d3:
            r7.mInnerView = r13
        L_0x02d5:
            android.os.Bundle r0 = r7.mSavedFragmentState
            r7.performActivityCreated(r0)
            android.os.Bundle r0 = r7.mSavedFragmentState
            r6.mo4081a((androidx.fragment.app.C0424j) r7, (android.os.Bundle) r0, (boolean) r12)
            android.view.View r0 = r7.mView
            if (r0 == 0) goto L_0x02e8
            android.os.Bundle r0 = r7.mSavedFragmentState
            r7.restoreViewState(r0)
        L_0x02e8:
            r7.mSavedFragmentState = r13
        L_0x02ea:
            if (r11 <= r10) goto L_0x02f2
            r17.performStart()
            r6.mo4123f(r7, r12)
        L_0x02f2:
            r9 = 3
        L_0x02f3:
            if (r11 <= r9) goto L_0x04c9
            r17.performResume()
            r6.mo4118e(r7, r12)
            r7.mSavedFragmentState = r13
            r7.mSavedViewState = r13
            goto L_0x04c9
        L_0x0301:
            if (r0 <= r11) goto L_0x04c9
            if (r0 == r8) goto L_0x03c6
            if (r0 == r10) goto L_0x0320
            r1 = 3
            if (r0 == r1) goto L_0x0318
            r1 = 4
            if (r0 == r1) goto L_0x030f
            goto L_0x04c9
        L_0x030f:
            if (r11 >= r1) goto L_0x0317
            r17.performPause()
            r6.mo4097d(r7, r12)
        L_0x0317:
            r1 = 3
        L_0x0318:
            if (r11 >= r1) goto L_0x0320
            r17.performStop()
            r6.mo4129g(r7, r12)
        L_0x0320:
            if (r11 >= r10) goto L_0x03c6
            android.view.View r0 = r7.mView
            if (r0 == 0) goto L_0x033a
            androidx.fragment.app.o r0 = r6.mHost
            androidx.fragment.app.k r0 = (androidx.fragment.app.C0425k) r0
            androidx.fragment.app.FragmentActivity r0 = r0.this$0
            boolean r0 = r0.isFinishing()
            r0 = r0 ^ r8
            if (r0 == 0) goto L_0x033a
            android.util.SparseArray r0 = r7.mSavedViewState
            if (r0 != 0) goto L_0x033a
            r16.mo4139l(r17)
        L_0x033a:
            r17.performDestroyView()
            r6.mo4133h(r7, r12)
            android.view.View r0 = r7.mView
            if (r0 == 0) goto L_0x03b7
            android.view.ViewGroup r1 = r7.mContainer
            if (r1 == 0) goto L_0x03b7
            r1.endViewTransition(r0)
            android.view.View r0 = r7.mView
            r0.clearAnimation()
            int r0 = r6.mCurState
            r1 = 0
            if (r0 <= 0) goto L_0x0370
            boolean r0 = r6.mDestroyed
            if (r0 != 0) goto L_0x0370
            android.view.View r0 = r7.mView
            int r0 = r0.getVisibility()
            if (r0 != 0) goto L_0x0370
            float r0 = r7.mPostponedAlpha
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 < 0) goto L_0x0370
            r0 = r19
            r2 = r20
            androidx.fragment.app.A r0 = r6.mo4074a((androidx.fragment.app.C0424j) r7, (int) r0, (boolean) r12, (int) r2)
            goto L_0x0371
        L_0x0370:
            r0 = r13
        L_0x0371:
            r7.mPostponedAlpha = r1
            if (r0 == 0) goto L_0x03b0
            android.view.View r1 = r7.mView
            android.view.ViewGroup r2 = r7.mContainer
            r2.startViewTransition(r1)
            r7.setStateAfterAnimating(r11)
            android.view.animation.Animation r3 = r0.animation
            if (r3 == 0) goto L_0x039b
            androidx.fragment.app.B r0 = new androidx.fragment.app.B
            r0.<init>(r3, r2, r1)
            android.view.View r1 = r7.mView
            r7.setAnimatingAway(r1)
            androidx.fragment.app.w r1 = new androidx.fragment.app.w
            r1.<init>(r6, r2, r7)
            r0.setAnimationListener(r1)
            android.view.View r1 = r7.mView
            r1.startAnimation(r0)
            goto L_0x03b0
        L_0x039b:
            android.animation.Animator r0 = r0.animator
            r7.setAnimator(r0)
            androidx.fragment.app.x r3 = new androidx.fragment.app.x
            r3.<init>(r6, r2, r1, r7)
            r0.addListener(r3)
            android.view.View r1 = r7.mView
            r0.setTarget(r1)
            r0.start()
        L_0x03b0:
            android.view.ViewGroup r0 = r7.mContainer
            android.view.View r1 = r7.mView
            r0.removeView(r1)
        L_0x03b7:
            r7.mContainer = r13
            r7.mView = r13
            r7.mViewLifecycleOwner = r13
            androidx.lifecycle.s r0 = r7.mViewLifecycleOwnerLiveData
            r0.setValue(r13)
            r7.mInnerView = r13
            r7.mInLayout = r12
        L_0x03c6:
            if (r11 >= r8) goto L_0x04c9
            boolean r0 = r6.mDestroyed
            if (r0 == 0) goto L_0x03ed
            android.view.View r0 = r17.getAnimatingAway()
            if (r0 == 0) goto L_0x03dd
            android.view.View r0 = r17.getAnimatingAway()
            r7.setAnimatingAway(r13)
            r0.clearAnimation()
            goto L_0x03ed
        L_0x03dd:
            android.animation.Animator r0 = r17.getAnimator()
            if (r0 == 0) goto L_0x03ed
            android.animation.Animator r0 = r17.getAnimator()
            r7.setAnimator(r13)
            r0.cancel()
        L_0x03ed:
            android.view.View r0 = r17.getAnimatingAway()
            if (r0 != 0) goto L_0x04c5
            android.animation.Animator r0 = r17.getAnimator()
            if (r0 == 0) goto L_0x03fb
            goto L_0x04c5
        L_0x03fb:
            boolean r0 = r7.mRemoving
            if (r0 == 0) goto L_0x0407
            boolean r0 = r17.isInBackStack()
            if (r0 != 0) goto L_0x0407
            r0 = r8
            goto L_0x0408
        L_0x0407:
            r0 = r12
        L_0x0408:
            if (r0 != 0) goto L_0x0416
            androidx.fragment.app.K r1 = r6.f349Ro
            boolean r1 = r1.mo4165t(r7)
            if (r1 == 0) goto L_0x0413
            goto L_0x0416
        L_0x0413:
            r7.mState = r12
            goto L_0x0447
        L_0x0416:
            androidx.fragment.app.o r1 = r6.mHost
            boolean r2 = r1 instanceof androidx.lifecycle.C0443B
            if (r2 == 0) goto L_0x0423
            androidx.fragment.app.K r1 = r6.f349Ro
            boolean r8 = r1.mo4157Fc()
            goto L_0x0438
        L_0x0423:
            android.content.Context r1 = r1.getContext()
            boolean r1 = r1 instanceof android.app.Activity
            if (r1 == 0) goto L_0x0438
            androidx.fragment.app.o r1 = r6.mHost
            android.content.Context r1 = r1.getContext()
            android.app.Activity r1 = (android.app.Activity) r1
            boolean r1 = r1.isChangingConfigurations()
            r8 = r8 ^ r1
        L_0x0438:
            if (r0 != 0) goto L_0x043c
            if (r8 == 0) goto L_0x0441
        L_0x043c:
            androidx.fragment.app.K r1 = r6.f349Ro
            r1.mo4163r(r7)
        L_0x0441:
            r17.performDestroy()
            r6.mo4090b((androidx.fragment.app.C0424j) r7, (boolean) r12)
        L_0x0447:
            r17.performDetach()
            r6.mo4094c((androidx.fragment.app.C0424j) r7, (boolean) r12)
            if (r21 != 0) goto L_0x04c9
            if (r0 != 0) goto L_0x0477
            androidx.fragment.app.K r0 = r6.f349Ro
            boolean r0 = r0.mo4165t(r7)
            if (r0 == 0) goto L_0x045a
            goto L_0x0477
        L_0x045a:
            r7.mHost = r13
            r7.mParentFragment = r13
            r7.mFragmentManager = r13
            java.lang.String r0 = r7.mTargetWho
            if (r0 == 0) goto L_0x04c9
            java.util.HashMap r1 = r6.mActive
            java.lang.Object r0 = r1.get(r0)
            androidx.fragment.app.j r0 = (androidx.fragment.app.C0424j) r0
            if (r0 == 0) goto L_0x04c9
            boolean r1 = r0.getRetainInstance()
            if (r1 == 0) goto L_0x04c9
            r7.mTarget = r0
            goto L_0x04c9
        L_0x0477:
            java.util.HashMap r0 = r6.mActive
            java.lang.String r1 = r7.mWho
            java.lang.Object r0 = r0.get(r1)
            if (r0 != 0) goto L_0x0482
            goto L_0x04c9
        L_0x0482:
            java.util.HashMap r0 = r6.mActive
            java.util.Collection r0 = r0.values()
            java.util.Iterator r0 = r0.iterator()
        L_0x048c:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x04a9
            java.lang.Object r1 = r0.next()
            androidx.fragment.app.j r1 = (androidx.fragment.app.C0424j) r1
            if (r1 == 0) goto L_0x048c
            java.lang.String r2 = r7.mWho
            java.lang.String r3 = r1.mTargetWho
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x048c
            r1.mTarget = r7
            r1.mTargetWho = r13
            goto L_0x048c
        L_0x04a9:
            java.util.HashMap r0 = r6.mActive
            java.lang.String r1 = r7.mWho
            r0.put(r1, r13)
            r16.mo4138k(r17)
            java.lang.String r0 = r7.mTargetWho
            if (r0 == 0) goto L_0x04c1
            java.util.HashMap r1 = r6.mActive
            java.lang.Object r0 = r1.get(r0)
            androidx.fragment.app.j r0 = (androidx.fragment.app.C0424j) r0
            r7.mTarget = r0
        L_0x04c1:
            r17.initState()
            goto L_0x04c9
        L_0x04c5:
            r7.setStateAfterAnimating(r11)
            goto L_0x04ca
        L_0x04c9:
            r8 = r11
        L_0x04ca:
            int r0 = r7.mState
            if (r0 == r8) goto L_0x04f8
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "moveToState: Fragment state for "
            r0.append(r1)
            r0.append(r7)
            java.lang.String r1 = " not updated inline; expected state "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r1 = " found "
            r0.append(r1)
            int r1 = r7.mState
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "FragmentManager"
            android.util.Log.w(r1, r0)
            r7.mState = r8
        L_0x04f8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0389H.mo4079a(androidx.fragment.app.j, int, int, int, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* renamed from: h */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4133h(androidx.fragment.app.C0424j r3, boolean r4) {
        /*
            r2 = this;
            androidx.fragment.app.j r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            androidx.fragment.app.s r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof androidx.fragment.app.C0389H
            if (r1 == 0) goto L_0x0012
            androidx.fragment.app.H r0 = (androidx.fragment.app.C0389H) r0
            r1 = 1
            r0.mo4133h(r3, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            androidx.fragment.app.C r3 = (androidx.fragment.app.C0384C) r3
            if (r4 == 0) goto L_0x002b
            boolean r0 = r3.f339Uo
            if (r0 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            androidx.fragment.app.q r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0389H.mo4133h(androidx.fragment.app.j, boolean):void");
    }

    /* renamed from: a */
    public void mo4084a(C0424j jVar, boolean z) {
        mo4128g(jVar);
        if (jVar.mDetached) {
            return;
        }
        if (!this.mAdded.contains(jVar)) {
            synchronized (this.mAdded) {
                this.mAdded.add(jVar);
            }
            jVar.mAdded = true;
            jVar.mRemoving = false;
            if (jVar.mView == null) {
                jVar.mHiddenChanged = false;
            }
            if (jVar.mHasMenu && jVar.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            if (z) {
                mo4079a(jVar, this.mCurState, 0, 0, false);
                return;
            }
            return;
        }
        throw new IllegalStateException(C0632a.m1014a("Fragment already added: ", jVar));
    }

    /* renamed from: a */
    public void mo4077a(C0386E e, boolean z) {
        if (!z) {
            m288Dn();
        }
        synchronized (this) {
            if (!this.mDestroyed) {
                if (this.mHost != null) {
                    if (this.mPendingActions == null) {
                        this.mPendingActions = new ArrayList();
                    }
                    this.mPendingActions.add(e);
                    mo4153wc();
                    return;
                }
            }
            if (!z) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    /* renamed from: a */
    public int mo4073a(C0407a aVar) {
        synchronized (this) {
            if (this.mAvailBackStackIndices != null) {
                if (this.mAvailBackStackIndices.size() > 0) {
                    int intValue = ((Integer) this.mAvailBackStackIndices.remove(this.mAvailBackStackIndices.size() - 1)).intValue();
                    this.mBackStackIndices.set(intValue, aVar);
                    return intValue;
                }
            }
            if (this.mBackStackIndices == null) {
                this.mBackStackIndices = new ArrayList();
            }
            int size = this.mBackStackIndices.size();
            this.mBackStackIndices.add(aVar);
            return size;
        }
    }

    /* renamed from: a */
    public void mo4075a(int i, C0407a aVar) {
        synchronized (this) {
            if (this.mBackStackIndices == null) {
                this.mBackStackIndices = new ArrayList();
            }
            int size = this.mBackStackIndices.size();
            if (i < size) {
                this.mBackStackIndices.set(i, aVar);
            } else {
                while (size < i) {
                    this.mBackStackIndices.add((Object) null);
                    if (this.mAvailBackStackIndices == null) {
                        this.mAvailBackStackIndices = new ArrayList();
                    }
                    this.mAvailBackStackIndices.add(Integer.valueOf(size));
                    size++;
                }
                this.mBackStackIndices.add(aVar);
            }
        }
    }

    /* renamed from: a */
    private void m295a(ArrayList arrayList, ArrayList arrayList2) {
        int indexOf;
        int indexOf2;
        ArrayList arrayList3 = this.mPostponedTransactions;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i = 0;
        while (i < size) {
            C0388G g = (C0388G) this.mPostponedTransactions.get(i);
            if (arrayList != null && !g.f343Vo && (indexOf2 = arrayList.indexOf(g.f345go)) != -1 && ((Boolean) arrayList2.get(indexOf2)).booleanValue()) {
                C0407a aVar = g.f345go;
                aVar.mManager.mo4078a(aVar, g.f343Vo, false, false);
            } else if (g.isReady() || (arrayList != null && g.f345go.interactsWith(arrayList, 0, arrayList.size()))) {
                this.mPostponedTransactions.remove(i);
                i--;
                size--;
                if (arrayList == null || g.f343Vo || (indexOf = arrayList.indexOf(g.f345go)) == -1 || !((Boolean) arrayList2.get(indexOf)).booleanValue()) {
                    g.completeTransaction();
                } else {
                    C0407a aVar2 = g.f345go;
                    aVar2.mManager.mo4078a(aVar2, g.f343Vo, false, false);
                }
            }
            i++;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: androidx.fragment.app.j} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m296a(java.util.ArrayList r20, java.util.ArrayList r21, int r22, int r23) {
        /*
            r19 = this;
            r6 = r19
            r7 = r20
            r8 = r21
            r9 = r22
            r10 = r23
            java.lang.Object r0 = r7.get(r9)
            androidx.fragment.app.a r0 = (androidx.fragment.app.C0407a) r0
            boolean r11 = r0.mReorderingAllowed
            java.util.ArrayList r0 = r6.mTmpAddedFragments
            if (r0 != 0) goto L_0x001e
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r6.mTmpAddedFragments = r0
            goto L_0x0021
        L_0x001e:
            r0.clear()
        L_0x0021:
            java.util.ArrayList r0 = r6.mTmpAddedFragments
            java.util.ArrayList r1 = r6.mAdded
            r0.addAll(r1)
            androidx.fragment.app.j r0 = r6.mPrimaryNav
            r1 = r0
            r0 = r9
            r13 = 0
        L_0x002d:
            r5 = 1
            if (r0 >= r10) goto L_0x0176
            java.lang.Object r2 = r7.get(r0)
            androidx.fragment.app.a r2 = (androidx.fragment.app.C0407a) r2
            java.lang.Object r3 = r8.get(r0)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            r4 = 3
            if (r3 != 0) goto L_0x012a
            java.util.ArrayList r3 = r6.mTmpAddedFragments
            r14 = r1
            r1 = 0
        L_0x0047:
            java.util.ArrayList r12 = r2.mOps
            int r12 = r12.size()
            if (r1 >= r12) goto L_0x0127
            java.util.ArrayList r12 = r2.mOps
            java.lang.Object r12 = r12.get(r1)
            androidx.fragment.app.N r12 = (androidx.fragment.app.C0395N) r12
            int r15 = r12.mCmd
            if (r15 == r5) goto L_0x0116
            r5 = 2
            r9 = 9
            if (r15 == r5) goto L_0x0098
            if (r15 == r4) goto L_0x007f
            r5 = 6
            if (r15 == r5) goto L_0x007f
            r5 = 7
            if (r15 == r5) goto L_0x007c
            r5 = 8
            if (r15 == r5) goto L_0x006d
            goto L_0x0095
        L_0x006d:
            java.util.ArrayList r5 = r2.mOps
            androidx.fragment.app.N r15 = new androidx.fragment.app.N
            r15.<init>(r9, r14)
            r5.add(r1, r15)
            int r1 = r1 + 1
            androidx.fragment.app.j r14 = r12.f358Zo
            goto L_0x0095
        L_0x007c:
            r7 = 1
            goto L_0x0117
        L_0x007f:
            androidx.fragment.app.j r5 = r12.f358Zo
            r3.remove(r5)
            androidx.fragment.app.j r5 = r12.f358Zo
            if (r5 != r14) goto L_0x0095
            java.util.ArrayList r12 = r2.mOps
            androidx.fragment.app.N r14 = new androidx.fragment.app.N
            r14.<init>(r9, r5)
            r12.add(r1, r14)
            int r1 = r1 + 1
            r14 = 0
        L_0x0095:
            r7 = 1
            goto L_0x011c
        L_0x0098:
            androidx.fragment.app.j r5 = r12.f358Zo
            int r15 = r5.mContainerId
            int r17 = r3.size()
            r16 = -1
            int r17 = r17 + -1
            r4 = r1
            r9 = r14
            r1 = r17
            r14 = 0
        L_0x00a9:
            if (r1 < 0) goto L_0x0101
            java.lang.Object r18 = r3.get(r1)
            r8 = r18
            androidx.fragment.app.j r8 = (androidx.fragment.app.C0424j) r8
            int r7 = r8.mContainerId
            if (r7 != r15) goto L_0x00f6
            if (r8 != r5) goto L_0x00bd
            r18 = r15
            r14 = 1
            goto L_0x00f8
        L_0x00bd:
            if (r8 != r9) goto L_0x00d1
            java.util.ArrayList r7 = r2.mOps
            androidx.fragment.app.N r9 = new androidx.fragment.app.N
            r18 = r15
            r15 = 9
            r9.<init>(r15, r8)
            r7.add(r4, r9)
            int r4 = r4 + 1
            r9 = 0
            goto L_0x00d5
        L_0x00d1:
            r18 = r15
            r15 = 9
        L_0x00d5:
            androidx.fragment.app.N r7 = new androidx.fragment.app.N
            r15 = 3
            r7.<init>(r15, r8)
            int r15 = r12.mEnterAnim
            r7.mEnterAnim = r15
            int r15 = r12.mPopEnterAnim
            r7.mPopEnterAnim = r15
            int r15 = r12.mExitAnim
            r7.mExitAnim = r15
            int r15 = r12.mPopExitAnim
            r7.mPopExitAnim = r15
            java.util.ArrayList r15 = r2.mOps
            r15.add(r4, r7)
            r3.remove(r8)
            r7 = 1
            int r4 = r4 + r7
            goto L_0x00f8
        L_0x00f6:
            r18 = r15
        L_0x00f8:
            int r1 = r1 + -1
            r7 = r20
            r8 = r21
            r15 = r18
            goto L_0x00a9
        L_0x0101:
            if (r14 == 0) goto L_0x010d
            java.util.ArrayList r1 = r2.mOps
            r1.remove(r4)
            int r4 = r4 + -1
            r1 = r4
            r7 = 1
            goto L_0x0114
        L_0x010d:
            r7 = 1
            r12.mCmd = r7
            r3.add(r5)
            r1 = r4
        L_0x0114:
            r14 = r9
            goto L_0x011c
        L_0x0116:
            r7 = r5
        L_0x0117:
            androidx.fragment.app.j r4 = r12.f358Zo
            r3.add(r4)
        L_0x011c:
            int r1 = r1 + r7
            r8 = r21
            r9 = r22
            r5 = r7
            r4 = 3
            r7 = r20
            goto L_0x0047
        L_0x0127:
            r7 = r5
            r1 = r14
            goto L_0x0162
        L_0x012a:
            r7 = r5
            java.util.ArrayList r3 = r6.mTmpAddedFragments
            java.util.ArrayList r4 = r2.mOps
            int r4 = r4.size()
            int r4 = r4 - r7
        L_0x0134:
            if (r4 < 0) goto L_0x0162
            java.util.ArrayList r5 = r2.mOps
            java.lang.Object r5 = r5.get(r4)
            androidx.fragment.app.N r5 = (androidx.fragment.app.C0395N) r5
            int r8 = r5.mCmd
            if (r8 == r7) goto L_0x0159
            r9 = 3
            if (r8 == r9) goto L_0x0153
            switch(r8) {
                case 6: goto L_0x0153;
                case 7: goto L_0x015a;
                case 8: goto L_0x0151;
                case 9: goto L_0x014e;
                case 10: goto L_0x0149;
                default: goto L_0x0148;
            }
        L_0x0148:
            goto L_0x015f
        L_0x0149:
            androidx.lifecycle.Lifecycle$State r8 = r5.f359_o
            r5.f360ap = r8
            goto L_0x015f
        L_0x014e:
            androidx.fragment.app.j r1 = r5.f358Zo
            goto L_0x015f
        L_0x0151:
            r1 = 0
            goto L_0x015f
        L_0x0153:
            androidx.fragment.app.j r5 = r5.f358Zo
            r3.add(r5)
            goto L_0x015f
        L_0x0159:
            r9 = 3
        L_0x015a:
            androidx.fragment.app.j r5 = r5.f358Zo
            r3.remove(r5)
        L_0x015f:
            int r4 = r4 + -1
            goto L_0x0134
        L_0x0162:
            if (r13 != 0) goto L_0x016b
            boolean r2 = r2.mAddToBackStack
            if (r2 == 0) goto L_0x0169
            goto L_0x016b
        L_0x0169:
            r13 = 0
            goto L_0x016c
        L_0x016b:
            r13 = r7
        L_0x016c:
            int r0 = r0 + 1
            r7 = r20
            r8 = r21
            r9 = r22
            goto L_0x002d
        L_0x0176:
            r7 = r5
            java.util.ArrayList r0 = r6.mTmpAddedFragments
            r0.clear()
            if (r11 != 0) goto L_0x018c
            r5 = 0
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            r4 = r23
            androidx.fragment.app.C0402V.m370a((androidx.fragment.app.C0389H) r0, (java.util.ArrayList) r1, (java.util.ArrayList) r2, (int) r3, (int) r4, (boolean) r5)
        L_0x018c:
            r0 = r22
        L_0x018e:
            if (r0 >= r10) goto L_0x01be
            r8 = r20
            java.lang.Object r1 = r8.get(r0)
            androidx.fragment.app.a r1 = (androidx.fragment.app.C0407a) r1
            r9 = r21
            java.lang.Object r2 = r9.get(r0)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x01b5
            r2 = -1
            r1.bumpBackStackNesting(r2)
            int r2 = r10 + -1
            if (r0 != r2) goto L_0x01b0
            r2 = r7
            goto L_0x01b1
        L_0x01b0:
            r2 = 0
        L_0x01b1:
            r1.executePopOps(r2)
            goto L_0x01bb
        L_0x01b5:
            r1.bumpBackStackNesting(r7)
            r1.executeOps()
        L_0x01bb:
            int r0 = r0 + 1
            goto L_0x018e
        L_0x01be:
            r8 = r20
            r9 = r21
            if (r11 == 0) goto L_0x0250
            a.b.d r0 = new a.b.d
            r0.<init>()
            r6.m293a((p000a.p005b.C0017d) r0)
            int r1 = r10 + -1
            r12 = r22
            r2 = r10
        L_0x01d1:
            if (r1 < r12) goto L_0x022b
            java.lang.Object r3 = r8.get(r1)
            androidx.fragment.app.a r3 = (androidx.fragment.app.C0407a) r3
            java.lang.Object r4 = r9.get(r1)
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            boolean r5 = r3.isPostponed()
            if (r5 == 0) goto L_0x01f3
            int r5 = r1 + 1
            boolean r5 = r3.interactsWith(r8, r5, r10)
            if (r5 != 0) goto L_0x01f3
            r5 = r7
            goto L_0x01f4
        L_0x01f3:
            r5 = 0
        L_0x01f4:
            if (r5 == 0) goto L_0x0227
            java.util.ArrayList r5 = r6.mPostponedTransactions
            if (r5 != 0) goto L_0x0201
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r6.mPostponedTransactions = r5
        L_0x0201:
            androidx.fragment.app.G r5 = new androidx.fragment.app.G
            r5.<init>(r3, r4)
            java.util.ArrayList r14 = r6.mPostponedTransactions
            r14.add(r5)
            r3.mo4201a(r5)
            if (r4 == 0) goto L_0x0215
            r3.executeOps()
            r14 = 0
            goto L_0x0219
        L_0x0215:
            r14 = 0
            r3.executePopOps(r14)
        L_0x0219:
            int r2 = r2 + -1
            if (r1 == r2) goto L_0x0223
            r8.remove(r1)
            r8.add(r2, r3)
        L_0x0223:
            r6.m293a((p000a.p005b.C0017d) r0)
            goto L_0x0228
        L_0x0227:
            r14 = 0
        L_0x0228:
            int r1 = r1 + -1
            goto L_0x01d1
        L_0x022b:
            r14 = 0
            int r1 = r0.size()
            r3 = r14
        L_0x0231:
            if (r3 >= r1) goto L_0x024e
            java.lang.Object r4 = r0.valueAt(r3)
            androidx.fragment.app.j r4 = (androidx.fragment.app.C0424j) r4
            boolean r5 = r4.mAdded
            if (r5 != 0) goto L_0x024b
            android.view.View r5 = r4.requireView()
            float r15 = r5.getAlpha()
            r4.mPostponedAlpha = r15
            r4 = 0
            r5.setAlpha(r4)
        L_0x024b:
            int r3 = r3 + 1
            goto L_0x0231
        L_0x024e:
            r4 = r2
            goto L_0x0254
        L_0x0250:
            r12 = r22
            r14 = 0
            r4 = r10
        L_0x0254:
            if (r4 == r12) goto L_0x0269
            if (r11 == 0) goto L_0x0269
            r5 = 1
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            androidx.fragment.app.C0402V.m370a((androidx.fragment.app.C0389H) r0, (java.util.ArrayList) r1, (java.util.ArrayList) r2, (int) r3, (int) r4, (boolean) r5)
            int r0 = r6.mCurState
            r6.moveToState(r0, r7)
        L_0x0269:
            if (r12 >= r10) goto L_0x02ac
            java.lang.Object r0 = r8.get(r12)
            androidx.fragment.app.a r0 = (androidx.fragment.app.C0407a) r0
            java.lang.Object r1 = r9.get(r12)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0288
            int r1 = r0.mIndex
            if (r1 < 0) goto L_0x0288
            r6.freeBackStackIndex(r1)
            r1 = -1
            r0.mIndex = r1
            goto L_0x0289
        L_0x0288:
            r1 = -1
        L_0x0289:
            java.util.ArrayList r2 = r0.mCommitRunnables
            if (r2 == 0) goto L_0x02a8
            r2 = r14
        L_0x028e:
            java.util.ArrayList r3 = r0.mCommitRunnables
            int r3 = r3.size()
            if (r2 >= r3) goto L_0x02a4
            java.util.ArrayList r3 = r0.mCommitRunnables
            java.lang.Object r3 = r3.get(r2)
            java.lang.Runnable r3 = (java.lang.Runnable) r3
            r3.run()
            int r2 = r2 + 1
            goto L_0x028e
        L_0x02a4:
            r2 = 0
            r0.mCommitRunnables = r2
            goto L_0x02a9
        L_0x02a8:
            r2 = 0
        L_0x02a9:
            int r12 = r12 + 1
            goto L_0x0269
        L_0x02ac:
            if (r13 == 0) goto L_0x02c8
            java.util.ArrayList r0 = r6.mBackStackChangeListeners
            if (r0 == 0) goto L_0x02c8
        L_0x02b2:
            java.util.ArrayList r0 = r6.mBackStackChangeListeners
            int r0 = r0.size()
            if (r14 >= r0) goto L_0x02c8
            java.util.ArrayList r0 = r6.mBackStackChangeListeners
            java.lang.Object r0 = r0.get(r14)
            androidx.fragment.app.r r0 = (androidx.fragment.app.C0432r) r0
            r0.onBackStackChanged()
            int r14 = r14 + 1
            goto L_0x02b2
        L_0x02c8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0389H.m296a(java.util.ArrayList, java.util.ArrayList, int, int):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4078a(C0407a aVar, boolean z, boolean z2, boolean z3) {
        if (z) {
            aVar.executePopOps(z3);
        } else {
            aVar.executeOps();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(aVar);
        arrayList2.add(Boolean.valueOf(z));
        if (z2) {
            C0402V.m370a(this, arrayList, arrayList2, 0, 1, true);
        }
        if (z3) {
            moveToState(this.mCurState, true);
        }
        for (C0424j jVar : this.mActive.values()) {
            if (jVar != null && jVar.mView != null && jVar.mIsNewlyAdded && aVar.interactsWith(jVar.mContainerId)) {
                float f = jVar.mPostponedAlpha;
                if (f > 0.0f) {
                    jVar.mView.setAlpha(f);
                }
                if (z3) {
                    jVar.mPostponedAlpha = 0.0f;
                } else {
                    jVar.mPostponedAlpha = -1.0f;
                    jVar.mIsNewlyAdded = false;
                }
            }
        }
    }

    /* renamed from: a */
    private void m293a(C0017d dVar) {
        int i = this.mCurState;
        if (i >= 1) {
            int min = Math.min(i, 3);
            int size = this.mAdded.size();
            for (int i2 = 0; i2 < size; i2++) {
                C0424j jVar = (C0424j) this.mAdded.get(i2);
                if (jVar.mState < min) {
                    mo4079a(jVar, min, jVar.getNextAnim(), jVar.getNextTransition(), false);
                    if (jVar.mView != null && !jVar.mHidden && jVar.mIsNewlyAdded) {
                        dVar.add(jVar);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4076a(Parcelable parcelable) {
        int i;
        FragmentState fragmentState;
        if (parcelable != null) {
            FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
            if (fragmentManagerState.mActive != null) {
                Iterator it = this.f349Ro.mo4156Ec().iterator();
                while (true) {
                    i = 0;
                    if (!it.hasNext()) {
                        break;
                    }
                    C0424j jVar = (C0424j) it.next();
                    Iterator it2 = fragmentManagerState.mActive.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            fragmentState = null;
                            break;
                        }
                        fragmentState = (FragmentState) it2.next();
                        if (fragmentState.mWho.equals(jVar.mWho)) {
                            break;
                        }
                    }
                    if (fragmentState == null) {
                        C0424j jVar2 = jVar;
                        mo4079a(jVar2, 1, 0, 0, false);
                        jVar.mRemoving = true;
                        mo4079a(jVar2, 0, 0, 0, false);
                    } else {
                        fragmentState.mInstance = jVar;
                        jVar.mSavedViewState = null;
                        jVar.mBackStackNesting = 0;
                        jVar.mInLayout = false;
                        jVar.mAdded = false;
                        C0424j jVar3 = jVar.mTarget;
                        jVar.mTargetWho = jVar3 != null ? jVar3.mWho : null;
                        jVar.mTarget = null;
                        Bundle bundle = fragmentState.mSavedFragmentState;
                        if (bundle != null) {
                            bundle.setClassLoader(this.mHost.getContext().getClassLoader());
                            jVar.mSavedViewState = fragmentState.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                            jVar.mSavedFragmentState = fragmentState.mSavedFragmentState;
                        }
                    }
                }
                this.mActive.clear();
                Iterator it3 = fragmentManagerState.mActive.iterator();
                while (it3.hasNext()) {
                    FragmentState fragmentState2 = (FragmentState) it3.next();
                    if (fragmentState2 != null) {
                        C0424j a = fragmentState2.mo4063a(this.mHost.getContext().getClassLoader(), mo4152vc());
                        a.mFragmentManager = this;
                        this.mActive.put(a.mWho, a);
                        fragmentState2.mInstance = null;
                    }
                }
                this.mAdded.clear();
                ArrayList arrayList = fragmentManagerState.mAdded;
                if (arrayList != null) {
                    Iterator it4 = arrayList.iterator();
                    while (it4.hasNext()) {
                        String str = (String) it4.next();
                        C0424j jVar4 = (C0424j) this.mActive.get(str);
                        if (jVar4 != null) {
                            jVar4.mAdded = true;
                            if (!this.mAdded.contains(jVar4)) {
                                synchronized (this.mAdded) {
                                    this.mAdded.add(jVar4);
                                }
                            } else {
                                throw new IllegalStateException(C0632a.m1014a("Already added ", jVar4));
                            }
                        } else {
                            m294a((RuntimeException) new IllegalStateException(C0632a.m1023d("No instantiated fragment for (", str, ")")));
                            throw null;
                        }
                    }
                }
                BackStackState[] backStackStateArr = fragmentManagerState.mBackStack;
                if (backStackStateArr != null) {
                    this.mBackStack = new ArrayList(backStackStateArr.length);
                    while (true) {
                        BackStackState[] backStackStateArr2 = fragmentManagerState.mBackStack;
                        if (i >= backStackStateArr2.length) {
                            break;
                        }
                        C0407a a2 = backStackStateArr2[i].mo3998a(this);
                        this.mBackStack.add(a2);
                        int i2 = a2.mIndex;
                        if (i2 >= 0) {
                            mo4075a(i2, a2);
                        }
                        i++;
                    }
                } else {
                    this.mBackStack = null;
                }
                String str2 = fragmentManagerState.f340Xo;
                if (str2 != null) {
                    this.mPrimaryNav = (C0424j) this.mActive.get(str2);
                    m300w(this.mPrimaryNav);
                }
                this.mNextFragmentIndex = fragmentManagerState.mNextFragmentIndex;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: androidx.activity.g} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: androidx.fragment.app.j} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: androidx.fragment.app.j} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: androidx.fragment.app.j} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4085a(androidx.fragment.app.C0429o r3, androidx.fragment.app.C0426l r4, androidx.fragment.app.C0424j r5) {
        /*
            r2 = this;
            androidx.fragment.app.o r0 = r2.mHost
            if (r0 != 0) goto L_0x004f
            r2.mHost = r3
            r2.mContainer = r4
            r2.mParent = r5
            androidx.fragment.app.j r4 = r2.mParent
            if (r4 == 0) goto L_0x0011
            r2.m291Gn()
        L_0x0011:
            boolean r4 = r3 instanceof androidx.activity.C0125g
            if (r4 == 0) goto L_0x0028
            r4 = r3
            androidx.activity.g r4 = (androidx.activity.C0125g) r4
            androidx.activity.f r0 = r4.getOnBackPressedDispatcher()
            r2.mOnBackPressedDispatcher = r0
            if (r5 == 0) goto L_0x0021
            r4 = r5
        L_0x0021:
            androidx.activity.f r0 = r2.mOnBackPressedDispatcher
            androidx.activity.d r1 = r2.f348Il
            r0.mo620a(r4, r1)
        L_0x0028:
            if (r5 == 0) goto L_0x0035
            androidx.fragment.app.H r3 = r5.mFragmentManager
            androidx.fragment.app.K r3 = r3.f349Ro
            androidx.fragment.app.K r3 = r3.mo4164s(r5)
            r2.f349Ro = r3
            goto L_0x004e
        L_0x0035:
            boolean r4 = r3 instanceof androidx.lifecycle.C0443B
            if (r4 == 0) goto L_0x0046
            androidx.lifecycle.B r3 = (androidx.lifecycle.C0443B) r3
            androidx.lifecycle.A r3 = r3.getViewModelStore()
            androidx.fragment.app.K r3 = androidx.fragment.app.C0392K.m344a(r3)
            r2.f349Ro = r3
            goto L_0x004e
        L_0x0046:
            androidx.fragment.app.K r3 = new androidx.fragment.app.K
            r4 = 0
            r3.<init>(r4)
            r2.f349Ro = r3
        L_0x004e:
            return
        L_0x004f:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r3 = "Already attached"
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0389H.mo4085a(androidx.fragment.app.o, androidx.fragment.app.l, androidx.fragment.app.j):void");
    }

    /* renamed from: a */
    public void mo4083a(C0424j jVar, Lifecycle$State lifecycle$State) {
        if (this.mActive.get(jVar.mWho) == jVar && (jVar.mHost == null || jVar.getFragmentManager() == this)) {
            jVar.mMaxState = lifecycle$State;
            return;
        }
        throw new IllegalArgumentException("Fragment " + jVar + " is not an active fragment of FragmentManager " + this);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4080a(androidx.fragment.app.C0424j r3, android.content.Context r4, boolean r5) {
        /*
            r2 = this;
            androidx.fragment.app.j r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            androidx.fragment.app.s r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof androidx.fragment.app.C0389H
            if (r1 == 0) goto L_0x0012
            androidx.fragment.app.H r0 = (androidx.fragment.app.C0389H) r0
            r1 = 1
            r0.mo4080a((androidx.fragment.app.C0424j) r3, (android.content.Context) r4, (boolean) r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            androidx.fragment.app.C r3 = (androidx.fragment.app.C0384C) r3
            if (r5 == 0) goto L_0x002b
            boolean r4 = r3.f339Uo
            if (r4 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            androidx.fragment.app.q r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0389H.mo4080a(androidx.fragment.app.j, android.content.Context, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4081a(androidx.fragment.app.C0424j r3, android.os.Bundle r4, boolean r5) {
        /*
            r2 = this;
            androidx.fragment.app.j r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            androidx.fragment.app.s r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof androidx.fragment.app.C0389H
            if (r1 == 0) goto L_0x0012
            androidx.fragment.app.H r0 = (androidx.fragment.app.C0389H) r0
            r1 = 1
            r0.mo4081a((androidx.fragment.app.C0424j) r3, (android.os.Bundle) r4, (boolean) r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            androidx.fragment.app.C r3 = (androidx.fragment.app.C0384C) r3
            if (r5 == 0) goto L_0x002b
            boolean r4 = r3.f339Uo
            if (r4 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            androidx.fragment.app.q r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0389H.mo4081a(androidx.fragment.app.j, android.os.Bundle, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4082a(androidx.fragment.app.C0424j r3, android.view.View r4, android.os.Bundle r5, boolean r6) {
        /*
            r2 = this;
            androidx.fragment.app.j r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            androidx.fragment.app.s r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof androidx.fragment.app.C0389H
            if (r1 == 0) goto L_0x0012
            androidx.fragment.app.H r0 = (androidx.fragment.app.C0389H) r0
            r1 = 1
            r0.mo4082a((androidx.fragment.app.C0424j) r3, (android.view.View) r4, (android.os.Bundle) r5, (boolean) r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            androidx.fragment.app.C r3 = (androidx.fragment.app.C0384C) r3
            if (r6 == 0) goto L_0x002b
            boolean r4 = r3.f339Uo
            if (r4 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            androidx.fragment.app.q r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0389H.mo4082a(androidx.fragment.app.j, android.view.View, android.os.Bundle, boolean):void");
    }
}
