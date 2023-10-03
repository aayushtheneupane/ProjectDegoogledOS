package android.support.p000v4.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.arch.lifecycle.ViewModelStore;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.support.design.R$dimen;
import android.support.p000v4.app.BackStackRecord;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.util.ArraySet;
import android.support.p000v4.util.LogWriter;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: android.support.v4.app.FragmentManagerImpl */
/* compiled from: FragmentManager */
final class FragmentManagerImpl extends FragmentManager implements LayoutInflater.Factory2 {
    static final Interpolator DECELERATE_CUBIC = new DecelerateInterpolator(1.5f);
    static final Interpolator DECELERATE_QUINT = new DecelerateInterpolator(2.5f);
    static Field sAnimationListenerField;
    SparseArray<Fragment> mActive;
    final ArrayList<Fragment> mAdded = new ArrayList<>();
    ArrayList<Integer> mAvailBackStackIndices;
    ArrayList<BackStackRecord> mBackStack;
    ArrayList<FragmentManager.OnBackStackChangedListener> mBackStackChangeListeners;
    ArrayList<BackStackRecord> mBackStackIndices;
    FragmentContainer mContainer;
    ArrayList<Fragment> mCreatedMenus;
    int mCurState = 0;
    boolean mDestroyed;
    Runnable mExecCommit = new Runnable() {
        public void run() {
            FragmentManagerImpl.this.execPendingActions();
        }
    };
    boolean mExecutingActions;
    boolean mHavePendingDeferredStart;
    FragmentHostCallback mHost;
    private final CopyOnWriteArrayList<FragmentLifecycleCallbacksHolder> mLifecycleCallbacks = new CopyOnWriteArrayList<>();
    boolean mNeedMenuInvalidate;
    int mNextFragmentIndex = 0;
    String mNoTransactionsBecause;
    Fragment mParent;
    ArrayList<OpGenerator> mPendingActions;
    ArrayList<StartEnterTransitionListener> mPostponedTransactions;
    Fragment mPrimaryNav;
    FragmentManagerNonConfig mSavedNonConfig;
    SparseArray<Parcelable> mStateArray = null;
    Bundle mStateBundle = null;
    boolean mStateSaved;
    boolean mStopped;
    ArrayList<Fragment> mTmpAddedFragments;
    ArrayList<Boolean> mTmpIsPop;
    ArrayList<BackStackRecord> mTmpRecords;

    /* renamed from: android.support.v4.app.FragmentManagerImpl$AnimateOnHWLayerIfNeededListener */
    /* compiled from: FragmentManager */
    private static class AnimateOnHWLayerIfNeededListener extends AnimationListenerWrapper {
        View mView;

        AnimateOnHWLayerIfNeededListener(View view, Animation.AnimationListener animationListener) {
            super(animationListener, (C00871) null);
            this.mView = view;
        }

        public void onAnimationEnd(Animation animation) {
            if (!ViewCompat.isAttachedToWindow(this.mView)) {
                int i = Build.VERSION.SDK_INT;
            }
            this.mView.post(new Runnable() {
                public void run() {
                    AnimateOnHWLayerIfNeededListener.this.mView.setLayerType(0, (Paint) null);
                }
            });
            super.onAnimationEnd(animation);
        }
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$AnimationListenerWrapper */
    /* compiled from: FragmentManager */
    private static class AnimationListenerWrapper implements Animation.AnimationListener {
        private final Animation.AnimationListener mWrapped;

        /* synthetic */ AnimationListenerWrapper(Animation.AnimationListener animationListener, C00871 r2) {
            this.mWrapped = animationListener;
        }

        public void onAnimationEnd(Animation animation) {
            Animation.AnimationListener animationListener = this.mWrapped;
            if (animationListener != null) {
                animationListener.onAnimationEnd(animation);
            }
        }

        public void onAnimationRepeat(Animation animation) {
            Animation.AnimationListener animationListener = this.mWrapped;
            if (animationListener != null) {
                animationListener.onAnimationRepeat(animation);
            }
        }

        public void onAnimationStart(Animation animation) {
            Animation.AnimationListener animationListener = this.mWrapped;
            if (animationListener != null) {
                animationListener.onAnimationStart(animation);
            }
        }
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$AnimatorOnHWLayerIfNeededListener */
    /* compiled from: FragmentManager */
    private static class AnimatorOnHWLayerIfNeededListener extends AnimatorListenerAdapter {
        View mView;

        AnimatorOnHWLayerIfNeededListener(View view) {
            this.mView = view;
        }

        public void onAnimationEnd(Animator animator) {
            this.mView.setLayerType(0, (Paint) null);
            animator.removeListener(this);
        }

        public void onAnimationStart(Animator animator) {
            this.mView.setLayerType(2, (Paint) null);
        }
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder */
    /* compiled from: FragmentManager */
    private static final class FragmentLifecycleCallbacksHolder {
        final FragmentManager.FragmentLifecycleCallbacks mCallback;
        final boolean mRecursive;
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$FragmentTag */
    /* compiled from: FragmentManager */
    static class FragmentTag {
        public static final int[] Fragment = {16842755, 16842960, 16842961};
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$OpGenerator */
    /* compiled from: FragmentManager */
    interface OpGenerator {
        boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2);
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$PopBackStackState */
    /* compiled from: FragmentManager */
    private class PopBackStackState implements OpGenerator {
        final int mFlags;
        final int mId;
        final String mName;

        PopBackStackState(String str, int i, int i2) {
            this.mName = str;
            this.mId = i;
            this.mFlags = i2;
        }

        public boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
            FragmentManager peekChildFragmentManager;
            Fragment fragment = FragmentManagerImpl.this.mPrimaryNav;
            if (fragment != null && this.mId < 0 && this.mName == null && (peekChildFragmentManager = fragment.peekChildFragmentManager()) != null && peekChildFragmentManager.popBackStackImmediate()) {
                return false;
            }
            return FragmentManagerImpl.this.popBackStackState(arrayList, arrayList2, this.mName, this.mId, this.mFlags);
        }
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$StartEnterTransitionListener */
    /* compiled from: FragmentManager */
    static class StartEnterTransitionListener implements Fragment.OnStartEnterTransitionListener {
        /* access modifiers changed from: private */
        public final boolean mIsBack;
        private int mNumPostponed;
        /* access modifiers changed from: private */
        public final BackStackRecord mRecord;

        StartEnterTransitionListener(BackStackRecord backStackRecord, boolean z) {
            this.mIsBack = z;
            this.mRecord = backStackRecord;
        }

        public void cancelTransaction() {
            BackStackRecord backStackRecord = this.mRecord;
            backStackRecord.mManager.completeExecute(backStackRecord, this.mIsBack, false, false);
        }

        public void completeTransaction() {
            boolean z = this.mNumPostponed > 0;
            FragmentManagerImpl fragmentManagerImpl = this.mRecord.mManager;
            int size = fragmentManagerImpl.mAdded.size();
            for (int i = 0; i < size; i++) {
                Fragment fragment = fragmentManagerImpl.mAdded.get(i);
                fragment.setOnStartEnterTransitionListener((Fragment.OnStartEnterTransitionListener) null);
                if (z && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            BackStackRecord backStackRecord = this.mRecord;
            backStackRecord.mManager.completeExecute(backStackRecord, this.mIsBack, !z, true);
        }

        public boolean isReady() {
            return this.mNumPostponed == 0;
        }

        public void onStartEnterTransition() {
            this.mNumPostponed--;
            if (this.mNumPostponed == 0) {
                this.mRecord.mManager.scheduleCommit();
            }
        }

        public void startListening() {
            this.mNumPostponed++;
        }
    }

    static {
        new AccelerateInterpolator(2.5f);
        new AccelerateInterpolator(1.5f);
    }

    FragmentManagerImpl() {
    }

    private void addAddedFragments(ArraySet<Fragment> arraySet) {
        int i = this.mCurState;
        if (i >= 1) {
            int min = Math.min(i, 4);
            int size = this.mAdded.size();
            for (int i2 = 0; i2 < size; i2++) {
                Fragment fragment = this.mAdded.get(i2);
                if (fragment.mState < min) {
                    moveToState(fragment, min, fragment.getNextAnim(), fragment.getNextTransition(), false);
                    if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                        arraySet.add(fragment);
                    }
                }
            }
        }
    }

    private void burpActive() {
        SparseArray<Fragment> sparseArray = this.mActive;
        if (sparseArray != null) {
            for (int size = sparseArray.size() - 1; size >= 0; size--) {
                if (this.mActive.valueAt(size) == null) {
                    SparseArray<Fragment> sparseArray2 = this.mActive;
                    sparseArray2.delete(sparseArray2.keyAt(size));
                }
            }
        }
    }

    private void checkStateLoss() {
        if (isStateSaved()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        } else if (this.mNoTransactionsBecause != null) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Can not perform this action inside of ");
            outline13.append(this.mNoTransactionsBecause);
            throw new IllegalStateException(outline13.toString());
        }
    }

    private void cleanupExec() {
        this.mExecutingActions = false;
        this.mTmpIsPop.clear();
        this.mTmpRecords.clear();
    }

    /* access modifiers changed from: private */
    public void completeExecute(BackStackRecord backStackRecord, boolean z, boolean z2, boolean z3) {
        if (z) {
            backStackRecord.executePopOps(z3);
        } else {
            backStackRecord.executeOps();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(backStackRecord);
        arrayList2.add(Boolean.valueOf(z));
        if (z2) {
            FragmentTransition.startTransitions(this, arrayList, arrayList2, 0, 1, true);
        }
        if (z3) {
            moveToState(this.mCurState, true);
        }
        SparseArray<Fragment> sparseArray = this.mActive;
        if (sparseArray != null) {
            int size = sparseArray.size();
            for (int i = 0; i < size; i++) {
                Fragment valueAt = this.mActive.valueAt(i);
                if (valueAt != null && valueAt.mView != null && valueAt.mIsNewlyAdded && backStackRecord.interactsWith(valueAt.mContainerId)) {
                    float f = valueAt.mPostponedAlpha;
                    if (f > 0.0f) {
                        valueAt.mView.setAlpha(f);
                    }
                    if (z3) {
                        valueAt.mPostponedAlpha = 0.0f;
                    } else {
                        valueAt.mPostponedAlpha = -1.0f;
                        valueAt.mIsNewlyAdded = false;
                    }
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    private void dispatchStateChange(int i) {
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

    private void ensureExecReady(boolean z) {
        if (this.mExecutingActions) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        } else if (this.mHost == null) {
            throw new IllegalStateException("Fragment host has been destroyed");
        } else if (Looper.myLooper() == this.mHost.getHandler().getLooper()) {
            if (!z) {
                checkStateLoss();
            }
            if (this.mTmpRecords == null) {
                this.mTmpRecords = new ArrayList<>();
                this.mTmpIsPop = new ArrayList<>();
            }
            this.mExecutingActions = true;
            try {
                executePostponedTransaction((ArrayList<BackStackRecord>) null, (ArrayList<Boolean>) null);
            } finally {
                this.mExecutingActions = false;
            }
        } else {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
    }

    private void executeOpsTogether(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        ArrayList<BackStackRecord> arrayList3 = arrayList;
        ArrayList<Boolean> arrayList4 = arrayList2;
        int i11 = i;
        int i12 = i2;
        boolean z = arrayList3.get(i11).mReorderingAllowed;
        ArrayList<Fragment> arrayList5 = this.mTmpAddedFragments;
        if (arrayList5 == null) {
            this.mTmpAddedFragments = new ArrayList<>();
        } else {
            arrayList5.clear();
        }
        this.mTmpAddedFragments.addAll(this.mAdded);
        Fragment fragment = this.mPrimaryNav;
        int i13 = i11;
        boolean z2 = false;
        while (true) {
            int i14 = 1;
            if (i13 < i12) {
                BackStackRecord backStackRecord = arrayList3.get(i13);
                int i15 = 3;
                if (!arrayList4.get(i13).booleanValue()) {
                    ArrayList<Fragment> arrayList6 = this.mTmpAddedFragments;
                    Fragment fragment2 = fragment;
                    int i16 = 0;
                    while (i7 < backStackRecord.mOps.size()) {
                        BackStackRecord.C0080Op op = backStackRecord.mOps.get(i7);
                        int i17 = op.cmd;
                        if (i17 != i14) {
                            if (i17 != 2) {
                                if (i17 == i15 || i17 == 6) {
                                    arrayList6.remove(op.fragment);
                                    Fragment fragment3 = op.fragment;
                                    if (fragment3 == fragment2) {
                                        backStackRecord.mOps.add(i7, new BackStackRecord.C0080Op(9, fragment3));
                                        i7++;
                                        fragment2 = null;
                                    }
                                } else if (i17 == 7) {
                                    i9 = 1;
                                } else if (i17 == 8) {
                                    backStackRecord.mOps.add(i7, new BackStackRecord.C0080Op(9, fragment2));
                                    i7++;
                                    fragment2 = op.fragment;
                                }
                                i8 = 1;
                            } else {
                                Fragment fragment4 = op.fragment;
                                int i18 = fragment4.mContainerId;
                                int i19 = i7;
                                Fragment fragment5 = fragment2;
                                int size = arrayList6.size() - 1;
                                boolean z3 = false;
                                while (size >= 0) {
                                    Fragment fragment6 = arrayList6.get(size);
                                    if (fragment6.mContainerId != i18) {
                                        i10 = i18;
                                    } else if (fragment6 == fragment4) {
                                        i10 = i18;
                                        z3 = true;
                                    } else {
                                        if (fragment6 == fragment5) {
                                            i10 = i18;
                                            backStackRecord.mOps.add(i19, new BackStackRecord.C0080Op(9, fragment6));
                                            i19++;
                                            fragment5 = null;
                                        } else {
                                            i10 = i18;
                                        }
                                        BackStackRecord.C0080Op op2 = new BackStackRecord.C0080Op(3, fragment6);
                                        op2.enterAnim = op.enterAnim;
                                        op2.popEnterAnim = op.popEnterAnim;
                                        op2.exitAnim = op.exitAnim;
                                        op2.popExitAnim = op.popExitAnim;
                                        backStackRecord.mOps.add(i19, op2);
                                        arrayList6.remove(fragment6);
                                        i19++;
                                    }
                                    size--;
                                    ArrayList<BackStackRecord> arrayList7 = arrayList;
                                    ArrayList<Boolean> arrayList8 = arrayList2;
                                    i18 = i10;
                                }
                                if (z3) {
                                    backStackRecord.mOps.remove(i19);
                                    i7 = i19 - 1;
                                    i8 = 1;
                                } else {
                                    i8 = 1;
                                    op.cmd = 1;
                                    arrayList6.add(fragment4);
                                    i7 = i19;
                                }
                                fragment2 = fragment5;
                            }
                            i16 = i7 + i8;
                            ArrayList<Boolean> arrayList9 = arrayList2;
                            int i20 = i;
                            i14 = i8;
                            i15 = 3;
                            ArrayList<BackStackRecord> arrayList10 = arrayList;
                        } else {
                            i9 = i14;
                        }
                        arrayList6.add(op.fragment);
                        i16 = i7 + i8;
                        ArrayList<Boolean> arrayList92 = arrayList2;
                        int i202 = i;
                        i14 = i8;
                        i15 = 3;
                        ArrayList<BackStackRecord> arrayList102 = arrayList;
                    }
                    fragment = fragment2;
                } else {
                    ArrayList<Fragment> arrayList11 = this.mTmpAddedFragments;
                    Fragment fragment7 = fragment;
                    for (int i21 = 0; i21 < backStackRecord.mOps.size(); i21++) {
                        BackStackRecord.C0080Op op3 = backStackRecord.mOps.get(i21);
                        int i22 = op3.cmd;
                        if (i22 != 1) {
                            if (i22 != 3) {
                                switch (i22) {
                                    case 6:
                                        break;
                                    case 7:
                                        break;
                                    case 8:
                                        fragment7 = null;
                                        break;
                                    case 9:
                                        fragment7 = op3.fragment;
                                        break;
                                }
                            }
                            arrayList11.add(op3.fragment);
                        }
                        arrayList11.remove(op3.fragment);
                    }
                    fragment = fragment7;
                }
                z2 = z2 || backStackRecord.mAddToBackStack;
                i13++;
                arrayList3 = arrayList;
                arrayList4 = arrayList2;
                int i23 = i;
            } else {
                this.mTmpAddedFragments.clear();
                if (!z) {
                    FragmentTransition.startTransitions(this, arrayList, arrayList2, i, i2, false);
                }
                int i24 = i;
                while (i24 < i12) {
                    BackStackRecord backStackRecord2 = arrayList.get(i24);
                    if (arrayList2.get(i24).booleanValue()) {
                        backStackRecord2.bumpBackStackNesting(-1);
                        backStackRecord2.executePopOps(i24 == i12 + -1);
                    } else {
                        backStackRecord2.bumpBackStackNesting(1);
                        backStackRecord2.executeOps();
                    }
                    i24++;
                }
                ArrayList<BackStackRecord> arrayList12 = arrayList;
                ArrayList<Boolean> arrayList13 = arrayList2;
                if (z) {
                    ArraySet arraySet = new ArraySet();
                    addAddedFragments(arraySet);
                    i4 = i;
                    int i25 = i12;
                    for (int i26 = i12 - 1; i26 >= i4; i26--) {
                        BackStackRecord backStackRecord3 = arrayList12.get(i26);
                        boolean booleanValue = arrayList13.get(i26).booleanValue();
                        if (backStackRecord3.isPostponed() && !backStackRecord3.interactsWith(arrayList12, i26 + 1, i12)) {
                            if (this.mPostponedTransactions == null) {
                                this.mPostponedTransactions = new ArrayList<>();
                            }
                            StartEnterTransitionListener startEnterTransitionListener = new StartEnterTransitionListener(backStackRecord3, booleanValue);
                            this.mPostponedTransactions.add(startEnterTransitionListener);
                            backStackRecord3.setOnStartPostponedListener(startEnterTransitionListener);
                            if (booleanValue) {
                                backStackRecord3.executeOps();
                            } else {
                                backStackRecord3.executePopOps(false);
                            }
                            i25--;
                            if (i26 != i25) {
                                arrayList12.remove(i26);
                                arrayList12.add(i25, backStackRecord3);
                            }
                            addAddedFragments(arraySet);
                        }
                    }
                    i3 = 0;
                    int size2 = arraySet.size();
                    for (int i27 = 0; i27 < size2; i27++) {
                        Fragment fragment8 = (Fragment) arraySet.valueAt(i27);
                        if (!fragment8.mAdded) {
                            View view = fragment8.getView();
                            fragment8.mPostponedAlpha = view.getAlpha();
                            view.setAlpha(0.0f);
                        }
                    }
                    i5 = i25;
                } else {
                    i4 = i;
                    i3 = 0;
                    i5 = i12;
                }
                if (i5 != i4 && z) {
                    FragmentTransition.startTransitions(this, arrayList, arrayList2, i, i5, true);
                    moveToState(this.mCurState, true);
                }
                while (i4 < i12) {
                    BackStackRecord backStackRecord4 = arrayList12.get(i4);
                    if (arrayList13.get(i4).booleanValue() && (i6 = backStackRecord4.mIndex) >= 0) {
                        freeBackStackIndex(i6);
                        backStackRecord4.mIndex = -1;
                    }
                    ArrayList<Runnable> arrayList14 = backStackRecord4.mCommitRunnables;
                    if (arrayList14 != null) {
                        int size3 = arrayList14.size();
                        for (int i28 = i3; i28 < size3; i28++) {
                            backStackRecord4.mCommitRunnables.get(i28).run();
                        }
                        backStackRecord4.mCommitRunnables = null;
                    }
                    i4++;
                }
                if (z2 && this.mBackStackChangeListeners != null) {
                    while (i3 < this.mBackStackChangeListeners.size()) {
                        this.mBackStackChangeListeners.get(i3).onBackStackChanged();
                        i3++;
                    }
                    return;
                }
                return;
            }
        }
    }

    private void executePostponedTransaction(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
        int indexOf;
        int indexOf2;
        ArrayList<StartEnterTransitionListener> arrayList3 = this.mPostponedTransactions;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i = 0;
        while (i < size) {
            StartEnterTransitionListener startEnterTransitionListener = this.mPostponedTransactions.get(i);
            if (arrayList != null && !startEnterTransitionListener.mIsBack && (indexOf2 = arrayList.indexOf(startEnterTransitionListener.mRecord)) != -1 && arrayList2.get(indexOf2).booleanValue()) {
                startEnterTransitionListener.cancelTransaction();
            } else if (startEnterTransitionListener.isReady() || (arrayList != null && startEnterTransitionListener.mRecord.interactsWith(arrayList, 0, arrayList.size()))) {
                this.mPostponedTransactions.remove(i);
                i--;
                size--;
                if (arrayList == null || startEnterTransitionListener.mIsBack || (indexOf = arrayList.indexOf(startEnterTransitionListener.mRecord)) == -1 || !arrayList2.get(indexOf).booleanValue()) {
                    startEnterTransitionListener.completeTransaction();
                } else {
                    startEnterTransitionListener.cancelTransaction();
                }
            }
            i++;
        }
    }

    private void forcePostponedTransactions() {
        if (this.mPostponedTransactions != null) {
            while (!this.mPostponedTransactions.isEmpty()) {
                this.mPostponedTransactions.remove(0).completeTransaction();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003b, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean generateOpsForPendingActions(java.util.ArrayList<android.support.p000v4.app.BackStackRecord> r5, java.util.ArrayList<java.lang.Boolean> r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.ArrayList<android.support.v4.app.FragmentManagerImpl$OpGenerator> r0 = r4.mPendingActions     // Catch:{ all -> 0x003c }
            r1 = 0
            if (r0 == 0) goto L_0x003a
            java.util.ArrayList<android.support.v4.app.FragmentManagerImpl$OpGenerator> r0 = r4.mPendingActions     // Catch:{ all -> 0x003c }
            int r0 = r0.size()     // Catch:{ all -> 0x003c }
            if (r0 != 0) goto L_0x000f
            goto L_0x003a
        L_0x000f:
            java.util.ArrayList<android.support.v4.app.FragmentManagerImpl$OpGenerator> r0 = r4.mPendingActions     // Catch:{ all -> 0x003c }
            int r0 = r0.size()     // Catch:{ all -> 0x003c }
            r2 = r1
        L_0x0016:
            if (r1 >= r0) goto L_0x0028
            java.util.ArrayList<android.support.v4.app.FragmentManagerImpl$OpGenerator> r3 = r4.mPendingActions     // Catch:{ all -> 0x003c }
            java.lang.Object r3 = r3.get(r1)     // Catch:{ all -> 0x003c }
            android.support.v4.app.FragmentManagerImpl$OpGenerator r3 = (android.support.p000v4.app.FragmentManagerImpl.OpGenerator) r3     // Catch:{ all -> 0x003c }
            boolean r3 = r3.generateOps(r5, r6)     // Catch:{ all -> 0x003c }
            r2 = r2 | r3
            int r1 = r1 + 1
            goto L_0x0016
        L_0x0028:
            java.util.ArrayList<android.support.v4.app.FragmentManagerImpl$OpGenerator> r5 = r4.mPendingActions     // Catch:{ all -> 0x003c }
            r5.clear()     // Catch:{ all -> 0x003c }
            android.support.v4.app.FragmentHostCallback r5 = r4.mHost     // Catch:{ all -> 0x003c }
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
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.generateOpsForPendingActions(java.util.ArrayList, java.util.ArrayList):boolean");
    }

    private static Animation.AnimationListener getAnimationListener(Animation animation) {
        try {
            if (sAnimationListenerField == null) {
                sAnimationListenerField = Animation.class.getDeclaredField("mListener");
                sAnimationListenerField.setAccessible(true);
            }
            return (Animation.AnimationListener) sAnimationListenerField.get(animation);
        } catch (NoSuchFieldException e) {
            Log.e("FragmentManager", "No field with the name mListener is found in Animation class", e);
            return null;
        } catch (IllegalAccessException e2) {
            Log.e("FragmentManager", "Cannot access Animation's mListener field", e2);
            return null;
        }
    }

    static AnimationOrAnimator makeOpenCloseAnimation(float f, float f2, float f3, float f4) {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(f, f2, f, f2, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(DECELERATE_QUINT);
        scaleAnimation.setDuration(220);
        animationSet.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(f3, f4);
        alphaAnimation.setInterpolator(DECELERATE_CUBIC);
        alphaAnimation.setDuration(220);
        animationSet.addAnimation(alphaAnimation);
        return new AnimationOrAnimator((Animation) animationSet, (C00871) null);
    }

    static boolean modifiesAlpha(Animator animator) {
        if (animator == null) {
            return false;
        }
        if (animator instanceof ValueAnimator) {
            PropertyValuesHolder[] values = ((ValueAnimator) animator).getValues();
            for (PropertyValuesHolder propertyName : values) {
                if ("alpha".equals(propertyName.getPropertyName())) {
                    return true;
                }
            }
        } else if (animator instanceof AnimatorSet) {
            ArrayList<Animator> childAnimations = ((AnimatorSet) animator).getChildAnimations();
            for (int i = 0; i < childAnimations.size(); i++) {
                if (modifiesAlpha(childAnimations.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    private void removeRedundantOperationsAndExecute(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
        if (arrayList != null && !arrayList.isEmpty()) {
            if (arrayList2 == null || arrayList.size() != arrayList2.size()) {
                throw new IllegalStateException("Internal error with the back stack records");
            }
            executePostponedTransaction(arrayList, arrayList2);
            int size = arrayList.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                if (!arrayList.get(i).mReorderingAllowed) {
                    if (i2 != i) {
                        executeOpsTogether(arrayList, arrayList2, i2, i);
                    }
                    i2 = i + 1;
                    if (arrayList2.get(i).booleanValue()) {
                        while (i2 < size && arrayList2.get(i2).booleanValue() && !arrayList.get(i2).mReorderingAllowed) {
                            i2++;
                        }
                    }
                    executeOpsTogether(arrayList, arrayList2, i, i2);
                    i = i2 - 1;
                }
                i++;
            }
            if (i2 != size) {
                executeOpsTogether(arrayList, arrayList2, i2, size);
            }
        }
    }

    public static int reverseTransit(int i) {
        if (i == 4097) {
            return 8194;
        }
        if (i != 4099) {
            return i != 8194 ? 0 : 4097;
        }
        return 4099;
    }

    /* access modifiers changed from: private */
    public void scheduleCommit() {
        synchronized (this) {
            boolean z = false;
            boolean z2 = this.mPostponedTransactions != null && !this.mPostponedTransactions.isEmpty();
            if (this.mPendingActions != null && this.mPendingActions.size() == 1) {
                z = true;
            }
            if (z2 || z) {
                this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                this.mHost.getHandler().post(this.mExecCommit);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void setHWLayerAnimListenerIfAlpha(android.view.View r5, android.support.p000v4.app.FragmentManagerImpl.AnimationOrAnimator r6) {
        /*
            if (r5 == 0) goto L_0x006a
            if (r6 != 0) goto L_0x0006
            goto L_0x006a
        L_0x0006:
            r0 = 0
            int r1 = android.os.Build.VERSION.SDK_INT
            int r1 = r5.getLayerType()
            r2 = 1
            if (r1 != 0) goto L_0x0046
            boolean r1 = android.support.p000v4.view.ViewCompat.hasOverlappingRendering(r5)
            if (r1 == 0) goto L_0x0046
            android.view.animation.Animation r1 = r6.animation
            boolean r3 = r1 instanceof android.view.animation.AlphaAnimation
            if (r3 == 0) goto L_0x001d
            goto L_0x0036
        L_0x001d:
            boolean r3 = r1 instanceof android.view.animation.AnimationSet
            if (r3 == 0) goto L_0x003d
            android.view.animation.AnimationSet r1 = (android.view.animation.AnimationSet) r1
            java.util.List r1 = r1.getAnimations()
            r3 = r0
        L_0x0028:
            int r4 = r1.size()
            if (r3 >= r4) goto L_0x003b
            java.lang.Object r4 = r1.get(r3)
            boolean r4 = r4 instanceof android.view.animation.AlphaAnimation
            if (r4 == 0) goto L_0x0038
        L_0x0036:
            r1 = r2
            goto L_0x0043
        L_0x0038:
            int r3 = r3 + 1
            goto L_0x0028
        L_0x003b:
            r1 = r0
            goto L_0x0043
        L_0x003d:
            android.animation.Animator r1 = r6.animator
            boolean r1 = modifiesAlpha(r1)
        L_0x0043:
            if (r1 == 0) goto L_0x0046
            r0 = r2
        L_0x0046:
            if (r0 == 0) goto L_0x006a
            android.animation.Animator r0 = r6.animator
            if (r0 == 0) goto L_0x0055
            android.support.v4.app.FragmentManagerImpl$AnimatorOnHWLayerIfNeededListener r6 = new android.support.v4.app.FragmentManagerImpl$AnimatorOnHWLayerIfNeededListener
            r6.<init>(r5)
            r0.addListener(r6)
            goto L_0x006a
        L_0x0055:
            android.view.animation.Animation r0 = r6.animation
            android.view.animation.Animation$AnimationListener r0 = getAnimationListener(r0)
            r1 = 2
            r2 = 0
            r5.setLayerType(r1, r2)
            android.view.animation.Animation r6 = r6.animation
            android.support.v4.app.FragmentManagerImpl$AnimateOnHWLayerIfNeededListener r1 = new android.support.v4.app.FragmentManagerImpl$AnimateOnHWLayerIfNeededListener
            r1.<init>(r5, r0)
            r6.setAnimationListener(r1)
        L_0x006a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.setHWLayerAnimListenerIfAlpha(android.view.View, android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator):void");
    }

    private static void setRetaining(FragmentManagerNonConfig fragmentManagerNonConfig) {
        if (fragmentManagerNonConfig != null) {
            List<Fragment> fragments = fragmentManagerNonConfig.getFragments();
            if (fragments != null) {
                for (Fragment fragment : fragments) {
                    fragment.mRetaining = true;
                }
            }
            List<FragmentManagerNonConfig> childNonConfigs = fragmentManagerNonConfig.getChildNonConfigs();
            if (childNonConfigs != null) {
                for (FragmentManagerNonConfig retaining : childNonConfigs) {
                    setRetaining(retaining);
                }
            }
        }
    }

    private void throwException(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
        FragmentHostCallback fragmentHostCallback = this.mHost;
        if (fragmentHostCallback != null) {
            try {
                FragmentActivity.this.dump("  ", (FileDescriptor) null, printWriter, new String[0]);
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

    public void addFragment(Fragment fragment, boolean z) {
        makeActive(fragment);
        if (fragment.mDetached) {
            return;
        }
        if (!this.mAdded.contains(fragment)) {
            synchronized (this.mAdded) {
                this.mAdded.add(fragment);
            }
            fragment.mAdded = true;
            fragment.mRemoving = false;
            if (fragment.mView == null) {
                fragment.mHiddenChanged = false;
            }
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            if (z) {
                moveToState(fragment, this.mCurState, 0, 0, false);
                return;
            }
            return;
        }
        throw new IllegalStateException(GeneratedOutlineSupport.outline6("Fragment already added: ", fragment));
    }

    public int allocBackStackIndex(BackStackRecord backStackRecord) {
        synchronized (this) {
            if (this.mAvailBackStackIndices != null) {
                if (this.mAvailBackStackIndices.size() > 0) {
                    int intValue = this.mAvailBackStackIndices.remove(this.mAvailBackStackIndices.size() - 1).intValue();
                    this.mBackStackIndices.set(intValue, backStackRecord);
                    return intValue;
                }
            }
            if (this.mBackStackIndices == null) {
                this.mBackStackIndices = new ArrayList<>();
            }
            int size = this.mBackStackIndices.size();
            this.mBackStackIndices.add(backStackRecord);
            return size;
        }
    }

    public void attachFragment(Fragment fragment) {
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (fragment.mAdded) {
                return;
            }
            if (!this.mAdded.contains(fragment)) {
                synchronized (this.mAdded) {
                    this.mAdded.add(fragment);
                }
                fragment.mAdded = true;
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.mNeedMenuInvalidate = true;
                    return;
                }
                return;
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline6("Fragment already added: ", fragment));
        }
    }

    public FragmentTransaction beginTransaction() {
        return new BackStackRecord(this);
    }

    public void detachFragment(Fragment fragment) {
        if (!fragment.mDetached) {
            fragment.mDetached = true;
            if (fragment.mAdded) {
                synchronized (this.mAdded) {
                    this.mAdded.remove(fragment);
                }
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.mNeedMenuInvalidate = true;
                }
                fragment.mAdded = false;
            }
        }
    }

    public void dispatchActivityCreated() {
        this.mStateSaved = false;
        this.mStopped = false;
        dispatchStateChange(2);
    }

    public void dispatchConfigurationChanged(Configuration configuration) {
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment fragment = this.mAdded.get(i);
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
            }
        }
    }

    public boolean dispatchContextItemSelected(MenuItem menuItem) {
        if (this.mCurState < 1) {
            return false;
        }
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment fragment = this.mAdded.get(i);
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void dispatchCreate() {
        this.mStateSaved = false;
        this.mStopped = false;
        dispatchStateChange(1);
    }

    public boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (this.mCurState < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z = false;
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment fragment = this.mAdded.get(i);
            if (fragment != null && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z = true;
            }
        }
        if (this.mCreatedMenus != null) {
            for (int i2 = 0; i2 < this.mCreatedMenus.size(); i2++) {
                Fragment fragment2 = this.mCreatedMenus.get(i2);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.mCreatedMenus = arrayList;
        return z;
    }

    public void dispatchDestroy() {
        this.mDestroyed = true;
        execPendingActions();
        dispatchStateChange(0);
        this.mHost = null;
        this.mContainer = null;
        this.mParent = null;
    }

    public void dispatchDestroyView() {
        dispatchStateChange(1);
    }

    public void dispatchLowMemory() {
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment fragment = this.mAdded.get(i);
            if (fragment != null) {
                fragment.performLowMemory();
            }
        }
    }

    public void dispatchMultiWindowModeChanged(boolean z) {
        for (int size = this.mAdded.size() - 1; size >= 0; size--) {
            Fragment fragment = this.mAdded.get(size);
            if (fragment != null) {
                fragment.performMultiWindowModeChanged(z);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchOnFragmentActivityCreated(android.support.p000v4.app.Fragment r3, android.os.Bundle r4, boolean r5) {
        /*
            r2 = this;
            android.support.v4.app.Fragment r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            android.support.v4.app.FragmentManager r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof android.support.p000v4.app.FragmentManagerImpl
            if (r1 == 0) goto L_0x0012
            android.support.v4.app.FragmentManagerImpl r0 = (android.support.p000v4.app.FragmentManagerImpl) r0
            r1 = 1
            r0.dispatchOnFragmentActivityCreated(r3, r4, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList<android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder> r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder r3 = (android.support.p000v4.app.FragmentManagerImpl.FragmentLifecycleCallbacksHolder) r3
            if (r5 == 0) goto L_0x002b
            boolean r4 = r3.mRecursive
            if (r4 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            android.support.v4.app.FragmentManager$FragmentLifecycleCallbacks r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.dispatchOnFragmentActivityCreated(android.support.v4.app.Fragment, android.os.Bundle, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchOnFragmentAttached(android.support.p000v4.app.Fragment r3, android.content.Context r4, boolean r5) {
        /*
            r2 = this;
            android.support.v4.app.Fragment r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            android.support.v4.app.FragmentManager r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof android.support.p000v4.app.FragmentManagerImpl
            if (r1 == 0) goto L_0x0012
            android.support.v4.app.FragmentManagerImpl r0 = (android.support.p000v4.app.FragmentManagerImpl) r0
            r1 = 1
            r0.dispatchOnFragmentAttached(r3, r4, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList<android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder> r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder r3 = (android.support.p000v4.app.FragmentManagerImpl.FragmentLifecycleCallbacksHolder) r3
            if (r5 == 0) goto L_0x002b
            boolean r4 = r3.mRecursive
            if (r4 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            android.support.v4.app.FragmentManager$FragmentLifecycleCallbacks r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.dispatchOnFragmentAttached(android.support.v4.app.Fragment, android.content.Context, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchOnFragmentCreated(android.support.p000v4.app.Fragment r3, android.os.Bundle r4, boolean r5) {
        /*
            r2 = this;
            android.support.v4.app.Fragment r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            android.support.v4.app.FragmentManager r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof android.support.p000v4.app.FragmentManagerImpl
            if (r1 == 0) goto L_0x0012
            android.support.v4.app.FragmentManagerImpl r0 = (android.support.p000v4.app.FragmentManagerImpl) r0
            r1 = 1
            r0.dispatchOnFragmentCreated(r3, r4, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList<android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder> r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder r3 = (android.support.p000v4.app.FragmentManagerImpl.FragmentLifecycleCallbacksHolder) r3
            if (r5 == 0) goto L_0x002b
            boolean r4 = r3.mRecursive
            if (r4 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            android.support.v4.app.FragmentManager$FragmentLifecycleCallbacks r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.dispatchOnFragmentCreated(android.support.v4.app.Fragment, android.os.Bundle, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchOnFragmentDestroyed(android.support.p000v4.app.Fragment r3, boolean r4) {
        /*
            r2 = this;
            android.support.v4.app.Fragment r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            android.support.v4.app.FragmentManager r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof android.support.p000v4.app.FragmentManagerImpl
            if (r1 == 0) goto L_0x0012
            android.support.v4.app.FragmentManagerImpl r0 = (android.support.p000v4.app.FragmentManagerImpl) r0
            r1 = 1
            r0.dispatchOnFragmentDestroyed(r3, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList<android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder> r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder r3 = (android.support.p000v4.app.FragmentManagerImpl.FragmentLifecycleCallbacksHolder) r3
            if (r4 == 0) goto L_0x002b
            boolean r0 = r3.mRecursive
            if (r0 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            android.support.v4.app.FragmentManager$FragmentLifecycleCallbacks r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.dispatchOnFragmentDestroyed(android.support.v4.app.Fragment, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchOnFragmentDetached(android.support.p000v4.app.Fragment r3, boolean r4) {
        /*
            r2 = this;
            android.support.v4.app.Fragment r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            android.support.v4.app.FragmentManager r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof android.support.p000v4.app.FragmentManagerImpl
            if (r1 == 0) goto L_0x0012
            android.support.v4.app.FragmentManagerImpl r0 = (android.support.p000v4.app.FragmentManagerImpl) r0
            r1 = 1
            r0.dispatchOnFragmentDetached(r3, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList<android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder> r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder r3 = (android.support.p000v4.app.FragmentManagerImpl.FragmentLifecycleCallbacksHolder) r3
            if (r4 == 0) goto L_0x002b
            boolean r0 = r3.mRecursive
            if (r0 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            android.support.v4.app.FragmentManager$FragmentLifecycleCallbacks r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.dispatchOnFragmentDetached(android.support.v4.app.Fragment, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchOnFragmentPaused(android.support.p000v4.app.Fragment r3, boolean r4) {
        /*
            r2 = this;
            android.support.v4.app.Fragment r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            android.support.v4.app.FragmentManager r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof android.support.p000v4.app.FragmentManagerImpl
            if (r1 == 0) goto L_0x0012
            android.support.v4.app.FragmentManagerImpl r0 = (android.support.p000v4.app.FragmentManagerImpl) r0
            r1 = 1
            r0.dispatchOnFragmentPaused(r3, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList<android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder> r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder r3 = (android.support.p000v4.app.FragmentManagerImpl.FragmentLifecycleCallbacksHolder) r3
            if (r4 == 0) goto L_0x002b
            boolean r0 = r3.mRecursive
            if (r0 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            android.support.v4.app.FragmentManager$FragmentLifecycleCallbacks r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.dispatchOnFragmentPaused(android.support.v4.app.Fragment, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchOnFragmentPreAttached(android.support.p000v4.app.Fragment r3, android.content.Context r4, boolean r5) {
        /*
            r2 = this;
            android.support.v4.app.Fragment r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            android.support.v4.app.FragmentManager r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof android.support.p000v4.app.FragmentManagerImpl
            if (r1 == 0) goto L_0x0012
            android.support.v4.app.FragmentManagerImpl r0 = (android.support.p000v4.app.FragmentManagerImpl) r0
            r1 = 1
            r0.dispatchOnFragmentPreAttached(r3, r4, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList<android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder> r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder r3 = (android.support.p000v4.app.FragmentManagerImpl.FragmentLifecycleCallbacksHolder) r3
            if (r5 == 0) goto L_0x002b
            boolean r4 = r3.mRecursive
            if (r4 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            android.support.v4.app.FragmentManager$FragmentLifecycleCallbacks r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.dispatchOnFragmentPreAttached(android.support.v4.app.Fragment, android.content.Context, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchOnFragmentPreCreated(android.support.p000v4.app.Fragment r3, android.os.Bundle r4, boolean r5) {
        /*
            r2 = this;
            android.support.v4.app.Fragment r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            android.support.v4.app.FragmentManager r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof android.support.p000v4.app.FragmentManagerImpl
            if (r1 == 0) goto L_0x0012
            android.support.v4.app.FragmentManagerImpl r0 = (android.support.p000v4.app.FragmentManagerImpl) r0
            r1 = 1
            r0.dispatchOnFragmentPreCreated(r3, r4, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList<android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder> r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder r3 = (android.support.p000v4.app.FragmentManagerImpl.FragmentLifecycleCallbacksHolder) r3
            if (r5 == 0) goto L_0x002b
            boolean r4 = r3.mRecursive
            if (r4 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            android.support.v4.app.FragmentManager$FragmentLifecycleCallbacks r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.dispatchOnFragmentPreCreated(android.support.v4.app.Fragment, android.os.Bundle, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchOnFragmentResumed(android.support.p000v4.app.Fragment r3, boolean r4) {
        /*
            r2 = this;
            android.support.v4.app.Fragment r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            android.support.v4.app.FragmentManager r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof android.support.p000v4.app.FragmentManagerImpl
            if (r1 == 0) goto L_0x0012
            android.support.v4.app.FragmentManagerImpl r0 = (android.support.p000v4.app.FragmentManagerImpl) r0
            r1 = 1
            r0.dispatchOnFragmentResumed(r3, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList<android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder> r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder r3 = (android.support.p000v4.app.FragmentManagerImpl.FragmentLifecycleCallbacksHolder) r3
            if (r4 == 0) goto L_0x002b
            boolean r0 = r3.mRecursive
            if (r0 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            android.support.v4.app.FragmentManager$FragmentLifecycleCallbacks r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.dispatchOnFragmentResumed(android.support.v4.app.Fragment, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchOnFragmentSaveInstanceState(android.support.p000v4.app.Fragment r3, android.os.Bundle r4, boolean r5) {
        /*
            r2 = this;
            android.support.v4.app.Fragment r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            android.support.v4.app.FragmentManager r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof android.support.p000v4.app.FragmentManagerImpl
            if (r1 == 0) goto L_0x0012
            android.support.v4.app.FragmentManagerImpl r0 = (android.support.p000v4.app.FragmentManagerImpl) r0
            r1 = 1
            r0.dispatchOnFragmentSaveInstanceState(r3, r4, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList<android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder> r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder r3 = (android.support.p000v4.app.FragmentManagerImpl.FragmentLifecycleCallbacksHolder) r3
            if (r5 == 0) goto L_0x002b
            boolean r4 = r3.mRecursive
            if (r4 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            android.support.v4.app.FragmentManager$FragmentLifecycleCallbacks r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.dispatchOnFragmentSaveInstanceState(android.support.v4.app.Fragment, android.os.Bundle, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchOnFragmentStarted(android.support.p000v4.app.Fragment r3, boolean r4) {
        /*
            r2 = this;
            android.support.v4.app.Fragment r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            android.support.v4.app.FragmentManager r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof android.support.p000v4.app.FragmentManagerImpl
            if (r1 == 0) goto L_0x0012
            android.support.v4.app.FragmentManagerImpl r0 = (android.support.p000v4.app.FragmentManagerImpl) r0
            r1 = 1
            r0.dispatchOnFragmentStarted(r3, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList<android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder> r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder r3 = (android.support.p000v4.app.FragmentManagerImpl.FragmentLifecycleCallbacksHolder) r3
            if (r4 == 0) goto L_0x002b
            boolean r0 = r3.mRecursive
            if (r0 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            android.support.v4.app.FragmentManager$FragmentLifecycleCallbacks r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.dispatchOnFragmentStarted(android.support.v4.app.Fragment, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchOnFragmentStopped(android.support.p000v4.app.Fragment r3, boolean r4) {
        /*
            r2 = this;
            android.support.v4.app.Fragment r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            android.support.v4.app.FragmentManager r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof android.support.p000v4.app.FragmentManagerImpl
            if (r1 == 0) goto L_0x0012
            android.support.v4.app.FragmentManagerImpl r0 = (android.support.p000v4.app.FragmentManagerImpl) r0
            r1 = 1
            r0.dispatchOnFragmentStopped(r3, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList<android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder> r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder r3 = (android.support.p000v4.app.FragmentManagerImpl.FragmentLifecycleCallbacksHolder) r3
            if (r4 == 0) goto L_0x002b
            boolean r0 = r3.mRecursive
            if (r0 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            android.support.v4.app.FragmentManager$FragmentLifecycleCallbacks r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.dispatchOnFragmentStopped(android.support.v4.app.Fragment, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchOnFragmentViewCreated(android.support.p000v4.app.Fragment r3, android.view.View r4, android.os.Bundle r5, boolean r6) {
        /*
            r2 = this;
            android.support.v4.app.Fragment r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            android.support.v4.app.FragmentManager r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof android.support.p000v4.app.FragmentManagerImpl
            if (r1 == 0) goto L_0x0012
            android.support.v4.app.FragmentManagerImpl r0 = (android.support.p000v4.app.FragmentManagerImpl) r0
            r1 = 1
            r0.dispatchOnFragmentViewCreated(r3, r4, r5, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList<android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder> r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder r3 = (android.support.p000v4.app.FragmentManagerImpl.FragmentLifecycleCallbacksHolder) r3
            if (r6 == 0) goto L_0x002b
            boolean r4 = r3.mRecursive
            if (r4 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            android.support.v4.app.FragmentManager$FragmentLifecycleCallbacks r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.dispatchOnFragmentViewCreated(android.support.v4.app.Fragment, android.view.View, android.os.Bundle, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchOnFragmentViewDestroyed(android.support.p000v4.app.Fragment r3, boolean r4) {
        /*
            r2 = this;
            android.support.v4.app.Fragment r0 = r2.mParent
            if (r0 == 0) goto L_0x0012
            android.support.v4.app.FragmentManager r0 = r0.getFragmentManager()
            boolean r1 = r0 instanceof android.support.p000v4.app.FragmentManagerImpl
            if (r1 == 0) goto L_0x0012
            android.support.v4.app.FragmentManagerImpl r0 = (android.support.p000v4.app.FragmentManagerImpl) r0
            r1 = 1
            r0.dispatchOnFragmentViewDestroyed(r3, r1)
        L_0x0012:
            java.util.concurrent.CopyOnWriteArrayList<android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder> r2 = r2.mLifecycleCallbacks
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r3 = r2.next()
            android.support.v4.app.FragmentManagerImpl$FragmentLifecycleCallbacksHolder r3 = (android.support.p000v4.app.FragmentManagerImpl.FragmentLifecycleCallbacksHolder) r3
            if (r4 == 0) goto L_0x002b
            boolean r0 = r3.mRecursive
            if (r0 != 0) goto L_0x002b
            goto L_0x0018
        L_0x002b:
            android.support.v4.app.FragmentManager$FragmentLifecycleCallbacks r2 = r3.mCallback
            r2 = 0
            throw r2
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.dispatchOnFragmentViewDestroyed(android.support.v4.app.Fragment, boolean):void");
    }

    public boolean dispatchOptionsItemSelected(MenuItem menuItem) {
        if (this.mCurState < 1) {
            return false;
        }
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment fragment = this.mAdded.get(i);
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void dispatchOptionsMenuClosed(Menu menu) {
        if (this.mCurState >= 1) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                Fragment fragment = this.mAdded.get(i);
                if (fragment != null) {
                    fragment.performOptionsMenuClosed(menu);
                }
            }
        }
    }

    public void dispatchPause() {
        dispatchStateChange(4);
    }

    public void dispatchPictureInPictureModeChanged(boolean z) {
        for (int size = this.mAdded.size() - 1; size >= 0; size--) {
            Fragment fragment = this.mAdded.get(size);
            if (fragment != null) {
                fragment.performPictureInPictureModeChanged(z);
            }
        }
    }

    public boolean dispatchPrepareOptionsMenu(Menu menu) {
        if (this.mCurState < 1) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment fragment = this.mAdded.get(i);
            if (fragment != null && fragment.performPrepareOptionsMenu(menu)) {
                z = true;
            }
        }
        return z;
    }

    public void dispatchReallyStop() {
        dispatchStateChange(2);
    }

    public void dispatchResume() {
        this.mStateSaved = false;
        this.mStopped = false;
        dispatchStateChange(5);
    }

    public void dispatchStart() {
        this.mStateSaved = false;
        this.mStopped = false;
        dispatchStateChange(4);
    }

    public void dispatchStop() {
        this.mStopped = true;
        dispatchStateChange(3);
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
        int size5;
        String outline8 = GeneratedOutlineSupport.outline8(str, "    ");
        SparseArray<Fragment> sparseArray = this.mActive;
        if (sparseArray != null && (size5 = sparseArray.size()) > 0) {
            printWriter.print(str);
            printWriter.print("Active Fragments in ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(":");
            for (int i = 0; i < size5; i++) {
                Fragment valueAt = this.mActive.valueAt(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.println(valueAt);
                if (valueAt != null) {
                    valueAt.dump(outline8, fileDescriptor, printWriter, strArr);
                }
            }
        }
        int size6 = this.mAdded.size();
        if (size6 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i2 = 0; i2 < size6; i2++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(this.mAdded.get(i2).toString());
            }
        }
        ArrayList<Fragment> arrayList = this.mCreatedMenus;
        if (arrayList != null && (size4 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i3 = 0; i3 < size4; i3++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(this.mCreatedMenus.get(i3).toString());
            }
        }
        ArrayList<BackStackRecord> arrayList2 = this.mBackStack;
        if (arrayList2 != null && (size3 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i4 = 0; i4 < size3; i4++) {
                BackStackRecord backStackRecord = this.mBackStack.get(i4);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i4);
                printWriter.print(": ");
                printWriter.println(backStackRecord.toString());
                backStackRecord.dump(outline8, printWriter, true);
            }
        }
        synchronized (this) {
            if (this.mBackStackIndices != null && (size2 = this.mBackStackIndices.size()) > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack Indices:");
                for (int i5 = 0; i5 < size2; i5++) {
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i5);
                    printWriter.print(": ");
                    printWriter.println(this.mBackStackIndices.get(i5));
                }
            }
            if (this.mAvailBackStackIndices != null && this.mAvailBackStackIndices.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.mAvailBackStackIndices.toArray()));
            }
        }
        ArrayList<OpGenerator> arrayList3 = this.mPendingActions;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Pending Actions:");
            for (int i6 = 0; i6 < size; i6++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i6);
                printWriter.print(": ");
                printWriter.println(this.mPendingActions.get(i6));
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
        if (this.mNoTransactionsBecause != null) {
            printWriter.print(str);
            printWriter.print("  mNoTransactionsBecause=");
            printWriter.println(this.mNoTransactionsBecause);
        }
    }

    public void enqueueAction(OpGenerator opGenerator, boolean z) {
        if (!z) {
            checkStateLoss();
        }
        synchronized (this) {
            if (!this.mDestroyed) {
                if (this.mHost != null) {
                    if (this.mPendingActions == null) {
                        this.mPendingActions = new ArrayList<>();
                    }
                    this.mPendingActions.add(opGenerator);
                    scheduleCommit();
                    return;
                }
            }
            if (!z) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean execPendingActions() {
        ensureExecReady(true);
        boolean z = false;
        while (generateOpsForPendingActions(this.mTmpRecords, this.mTmpIsPop)) {
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
                cleanupExec();
                z = true;
            } catch (Throwable th) {
                cleanupExec();
                throw th;
            }
        }
        doPendingDeferredStart();
        burpActive();
        return z;
    }

    public void execSingleAction(OpGenerator opGenerator, boolean z) {
        if (!z || (this.mHost != null && !this.mDestroyed)) {
            ensureExecReady(z);
            if (opGenerator.generateOps(this.mTmpRecords, this.mTmpIsPop)) {
                this.mExecutingActions = true;
                try {
                    removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
                } finally {
                    cleanupExec();
                }
            }
            doPendingDeferredStart();
            burpActive();
        }
    }

    public boolean executePendingTransactions() {
        boolean execPendingActions = execPendingActions();
        forcePostponedTransactions();
        return execPendingActions;
    }

    public Fragment findFragmentById(int i) {
        for (int size = this.mAdded.size() - 1; size >= 0; size--) {
            Fragment fragment = this.mAdded.get(size);
            if (fragment != null && fragment.mFragmentId == i) {
                return fragment;
            }
        }
        SparseArray<Fragment> sparseArray = this.mActive;
        if (sparseArray == null) {
            return null;
        }
        for (int size2 = sparseArray.size() - 1; size2 >= 0; size2--) {
            Fragment valueAt = this.mActive.valueAt(size2);
            if (valueAt != null && valueAt.mFragmentId == i) {
                return valueAt;
            }
        }
        return null;
    }

    public Fragment findFragmentByTag(String str) {
        if (str != null) {
            for (int size = this.mAdded.size() - 1; size >= 0; size--) {
                Fragment fragment = this.mAdded.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        SparseArray<Fragment> sparseArray = this.mActive;
        if (sparseArray == null || str == null) {
            return null;
        }
        for (int size2 = sparseArray.size() - 1; size2 >= 0; size2--) {
            Fragment valueAt = this.mActive.valueAt(size2);
            if (valueAt != null && str.equals(valueAt.mTag)) {
                return valueAt;
            }
        }
        return null;
    }

    public Fragment findFragmentByWho(String str) {
        Fragment findFragmentByWho;
        SparseArray<Fragment> sparseArray = this.mActive;
        if (sparseArray == null || str == null) {
            return null;
        }
        for (int size = sparseArray.size() - 1; size >= 0; size--) {
            Fragment valueAt = this.mActive.valueAt(size);
            if (valueAt != null && (findFragmentByWho = valueAt.findFragmentByWho(str)) != null) {
                return findFragmentByWho;
            }
        }
        return null;
    }

    public void freeBackStackIndex(int i) {
        synchronized (this) {
            this.mBackStackIndices.set(i, (Object) null);
            if (this.mAvailBackStackIndices == null) {
                this.mAvailBackStackIndices = new ArrayList<>();
            }
            this.mAvailBackStackIndices.add(Integer.valueOf(i));
        }
    }

    public Fragment getFragment(Bundle bundle, String str) {
        int i = bundle.getInt(str, -1);
        if (i == -1) {
            return null;
        }
        Fragment fragment = this.mActive.get(i);
        if (fragment != null) {
            return fragment;
        }
        throwException(new IllegalStateException("Fragment no longer exists for key " + str + ": index " + i));
        throw null;
    }

    public List<Fragment> getFragments() {
        List<Fragment> list;
        if (this.mAdded.isEmpty()) {
            return Collections.EMPTY_LIST;
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

    public void hideFragment(Fragment fragment) {
        if (!fragment.mHidden) {
            fragment.mHidden = true;
            fragment.mHiddenChanged = true ^ fragment.mHiddenChanged;
        }
    }

    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    public boolean isStateSaved() {
        return this.mStateSaved || this.mStopped;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0050 A[SYNTHETIC, Splitter:B:23:0x0050] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.support.p000v4.app.FragmentManagerImpl.AnimationOrAnimator loadAnimation(android.support.p000v4.app.Fragment r7, int r8, boolean r9, int r10) {
        /*
            r6 = this;
            int r0 = r7.getNextAnim()
            android.view.animation.Animation r1 = r7.onCreateAnimation(r8, r9, r0)
            r2 = 0
            if (r1 == 0) goto L_0x0011
            android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator r6 = new android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator
            r6.<init>((android.view.animation.Animation) r1, (android.support.p000v4.app.FragmentManagerImpl.C00871) r2)
            return r6
        L_0x0011:
            android.animation.Animator r7 = r7.onCreateAnimator(r8, r9, r0)
            if (r7 == 0) goto L_0x001d
            android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator r6 = new android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator
            r6.<init>((android.animation.Animator) r7, (android.support.p000v4.app.FragmentManagerImpl.C00871) r2)
            return r6
        L_0x001d:
            r7 = 1
            r1 = 0
            if (r0 == 0) goto L_0x0078
            android.support.v4.app.FragmentHostCallback r3 = r6.mHost
            android.content.Context r3 = r3.getContext()
            android.content.res.Resources r3 = r3.getResources()
            java.lang.String r3 = r3.getResourceTypeName(r0)
            java.lang.String r4 = "anim"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x004d
            android.support.v4.app.FragmentHostCallback r4 = r6.mHost     // Catch:{ NotFoundException -> 0x004b, RuntimeException -> 0x004d }
            android.content.Context r4 = r4.getContext()     // Catch:{ NotFoundException -> 0x004b, RuntimeException -> 0x004d }
            android.view.animation.Animation r4 = android.view.animation.AnimationUtils.loadAnimation(r4, r0)     // Catch:{ NotFoundException -> 0x004b, RuntimeException -> 0x004d }
            if (r4 == 0) goto L_0x0049
            android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator r5 = new android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator     // Catch:{ NotFoundException -> 0x004b, RuntimeException -> 0x004d }
            r5.<init>((android.view.animation.Animation) r4, (android.support.p000v4.app.FragmentManagerImpl.C00871) r2)     // Catch:{ NotFoundException -> 0x004b, RuntimeException -> 0x004d }
            return r5
        L_0x0049:
            r4 = r7
            goto L_0x004e
        L_0x004b:
            r6 = move-exception
            throw r6
        L_0x004d:
            r4 = r1
        L_0x004e:
            if (r4 != 0) goto L_0x0078
            android.support.v4.app.FragmentHostCallback r4 = r6.mHost     // Catch:{ RuntimeException -> 0x0062 }
            android.content.Context r4 = r4.getContext()     // Catch:{ RuntimeException -> 0x0062 }
            android.animation.Animator r4 = android.animation.AnimatorInflater.loadAnimator(r4, r0)     // Catch:{ RuntimeException -> 0x0062 }
            if (r4 == 0) goto L_0x0078
            android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator r5 = new android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator     // Catch:{ RuntimeException -> 0x0062 }
            r5.<init>((android.animation.Animator) r4, (android.support.p000v4.app.FragmentManagerImpl.C00871) r2)     // Catch:{ RuntimeException -> 0x0062 }
            return r5
        L_0x0062:
            r4 = move-exception
            if (r3 != 0) goto L_0x0077
            android.support.v4.app.FragmentHostCallback r3 = r6.mHost
            android.content.Context r3 = r3.getContext()
            android.view.animation.Animation r0 = android.view.animation.AnimationUtils.loadAnimation(r3, r0)
            if (r0 == 0) goto L_0x0078
            android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator r6 = new android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator
            r6.<init>((android.view.animation.Animation) r0, (android.support.p000v4.app.FragmentManagerImpl.C00871) r2)
            return r6
        L_0x0077:
            throw r4
        L_0x0078:
            if (r8 != 0) goto L_0x007b
            return r2
        L_0x007b:
            r0 = 4097(0x1001, float:5.741E-42)
            if (r8 == r0) goto L_0x0095
            r0 = 4099(0x1003, float:5.744E-42)
            if (r8 == r0) goto L_0x008f
            r0 = 8194(0x2002, float:1.1482E-41)
            if (r8 == r0) goto L_0x0089
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
            return r2
        L_0x009d:
            r9 = 1064933786(0x3f79999a, float:0.975)
            r0 = 0
            r3 = 1065353216(0x3f800000, float:1.0)
            r4 = 220(0xdc, double:1.087E-321)
            switch(r8) {
                case 1: goto L_0x00fe;
                case 2: goto L_0x00f4;
                case 3: goto L_0x00ea;
                case 4: goto L_0x00dd;
                case 5: goto L_0x00c5;
                case 6: goto L_0x00ad;
                default: goto L_0x00a8;
            }
        L_0x00a8:
            if (r10 != 0) goto L_0x012b
            android.support.v4.app.FragmentHostCallback r8 = r6.mHost
            goto L_0x010a
        L_0x00ad:
            android.support.v4.app.FragmentHostCallback r6 = r6.mHost
            r6.getContext()
            android.view.animation.AlphaAnimation r6 = new android.view.animation.AlphaAnimation
            r6.<init>(r3, r0)
            android.view.animation.Interpolator r7 = DECELERATE_CUBIC
            r6.setInterpolator(r7)
            r6.setDuration(r4)
            android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator r7 = new android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator
            r7.<init>((android.view.animation.Animation) r6, (android.support.p000v4.app.FragmentManagerImpl.C00871) r2)
            return r7
        L_0x00c5:
            android.support.v4.app.FragmentHostCallback r6 = r6.mHost
            r6.getContext()
            android.view.animation.AlphaAnimation r6 = new android.view.animation.AlphaAnimation
            r6.<init>(r0, r3)
            android.view.animation.Interpolator r7 = DECELERATE_CUBIC
            r6.setInterpolator(r7)
            r6.setDuration(r4)
            android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator r7 = new android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator
            r7.<init>((android.view.animation.Animation) r6, (android.support.p000v4.app.FragmentManagerImpl.C00871) r2)
            return r7
        L_0x00dd:
            android.support.v4.app.FragmentHostCallback r6 = r6.mHost
            r6.getContext()
            r6 = 1065982362(0x3f89999a, float:1.075)
            android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator r6 = makeOpenCloseAnimation(r3, r6, r3, r0)
            return r6
        L_0x00ea:
            android.support.v4.app.FragmentHostCallback r6 = r6.mHost
            r6.getContext()
            android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator r6 = makeOpenCloseAnimation(r9, r3, r0, r3)
            return r6
        L_0x00f4:
            android.support.v4.app.FragmentHostCallback r6 = r6.mHost
            r6.getContext()
            android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator r6 = makeOpenCloseAnimation(r3, r9, r3, r0)
            return r6
        L_0x00fe:
            android.support.v4.app.FragmentHostCallback r6 = r6.mHost
            r6.getContext()
            r6 = 1066401792(0x3f900000, float:1.125)
            android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator r6 = makeOpenCloseAnimation(r6, r3, r0, r3)
            return r6
        L_0x010a:
            android.support.v4.app.FragmentActivity$HostCallbacks r8 = (android.support.p000v4.app.FragmentActivity.HostCallbacks) r8
            android.support.v4.app.FragmentActivity r8 = android.support.p000v4.app.FragmentActivity.this
            android.view.Window r8 = r8.getWindow()
            if (r8 == 0) goto L_0x0115
            goto L_0x0116
        L_0x0115:
            r7 = r1
        L_0x0116:
            if (r7 == 0) goto L_0x012b
            android.support.v4.app.FragmentHostCallback r6 = r6.mHost
            android.support.v4.app.FragmentActivity$HostCallbacks r6 = (android.support.p000v4.app.FragmentActivity.HostCallbacks) r6
            android.support.v4.app.FragmentActivity r6 = android.support.p000v4.app.FragmentActivity.this
            android.view.Window r6 = r6.getWindow()
            if (r6 != 0) goto L_0x0125
            goto L_0x012b
        L_0x0125:
            android.view.WindowManager$LayoutParams r6 = r6.getAttributes()
            int r6 = r6.windowAnimations
        L_0x012b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.loadAnimation(android.support.v4.app.Fragment, int, boolean, int):android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator");
    }

    /* access modifiers changed from: package-private */
    public void makeActive(Fragment fragment) {
        if (fragment.mIndex < 0) {
            int i = this.mNextFragmentIndex;
            this.mNextFragmentIndex = i + 1;
            fragment.setIndex(i, this.mParent);
            if (this.mActive == null) {
                this.mActive = new SparseArray<>();
            }
            this.mActive.put(fragment.mIndex, fragment);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0052, code lost:
        r0 = r4.mView;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void moveFragmentToExpectedState(final android.support.p000v4.app.Fragment r11) {
        /*
            r10 = this;
            if (r11 != 0) goto L_0x0003
            return
        L_0x0003:
            int r0 = r10.mCurState
            boolean r1 = r11.mRemoving
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x001a
            boolean r1 = r11.isInBackStack()
            if (r1 == 0) goto L_0x0016
            int r0 = java.lang.Math.min(r0, r2)
            goto L_0x001a
        L_0x0016:
            int r0 = java.lang.Math.min(r0, r3)
        L_0x001a:
            r6 = r0
            int r7 = r11.getNextTransition()
            int r8 = r11.getNextTransitionStyle()
            r9 = 0
            r4 = r10
            r5 = r11
            r4.moveToState(r5, r6, r7, r8, r9)
            android.view.View r0 = r11.mView
            if (r0 == 0) goto L_0x00ab
            android.view.ViewGroup r1 = r11.mContainer
            r4 = 0
            if (r1 == 0) goto L_0x0050
            if (r0 != 0) goto L_0x0035
            goto L_0x0050
        L_0x0035:
            java.util.ArrayList<android.support.v4.app.Fragment> r0 = r10.mAdded
            int r0 = r0.indexOf(r11)
        L_0x003b:
            int r0 = r0 + -1
            if (r0 < 0) goto L_0x0050
            java.util.ArrayList<android.support.v4.app.Fragment> r5 = r10.mAdded
            java.lang.Object r5 = r5.get(r0)
            android.support.v4.app.Fragment r5 = (android.support.p000v4.app.Fragment) r5
            android.view.ViewGroup r6 = r5.mContainer
            if (r6 != r1) goto L_0x003b
            android.view.View r6 = r5.mView
            if (r6 == 0) goto L_0x003b
            r4 = r5
        L_0x0050:
            if (r4 == 0) goto L_0x006a
            android.view.View r0 = r4.mView
            android.view.ViewGroup r1 = r11.mContainer
            int r0 = r1.indexOfChild(r0)
            android.view.View r4 = r11.mView
            int r4 = r1.indexOfChild(r4)
            if (r4 >= r0) goto L_0x006a
            r1.removeViewAt(r4)
            android.view.View r4 = r11.mView
            r1.addView(r4, r0)
        L_0x006a:
            boolean r0 = r11.mIsNewlyAdded
            if (r0 == 0) goto L_0x00ab
            android.view.ViewGroup r0 = r11.mContainer
            if (r0 == 0) goto L_0x00ab
            float r0 = r11.mPostponedAlpha
            r1 = 0
            int r4 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r4 <= 0) goto L_0x007e
            android.view.View r4 = r11.mView
            r4.setAlpha(r0)
        L_0x007e:
            r11.mPostponedAlpha = r1
            r11.mIsNewlyAdded = r3
            int r0 = r11.getNextTransition()
            int r1 = r11.getNextTransitionStyle()
            android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator r0 = r10.loadAnimation(r11, r0, r2, r1)
            if (r0 == 0) goto L_0x00ab
            android.view.View r1 = r11.mView
            setHWLayerAnimListenerIfAlpha(r1, r0)
            android.view.animation.Animation r1 = r0.animation
            if (r1 == 0) goto L_0x009f
            android.view.View r0 = r11.mView
            r0.startAnimation(r1)
            goto L_0x00ab
        L_0x009f:
            android.animation.Animator r1 = r0.animator
            android.view.View r4 = r11.mView
            r1.setTarget(r4)
            android.animation.Animator r0 = r0.animator
            r0.start()
        L_0x00ab:
            boolean r0 = r11.mHiddenChanged
            if (r0 == 0) goto L_0x0141
            android.view.View r0 = r11.mView
            if (r0 == 0) goto L_0x012c
            int r0 = r11.getNextTransition()
            boolean r1 = r11.mHidden
            r1 = r1 ^ r2
            int r4 = r11.getNextTransitionStyle()
            android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator r0 = r10.loadAnimation(r11, r0, r1, r4)
            if (r0 == 0) goto L_0x00fd
            android.animation.Animator r1 = r0.animator
            if (r1 == 0) goto L_0x00fd
            android.view.View r4 = r11.mView
            r1.setTarget(r4)
            boolean r1 = r11.mHidden
            if (r1 == 0) goto L_0x00ed
            boolean r1 = r11.isHideReplaced()
            if (r1 == 0) goto L_0x00db
            r11.setHideReplaced(r3)
            goto L_0x00f2
        L_0x00db:
            android.view.ViewGroup r1 = r11.mContainer
            android.view.View r4 = r11.mView
            r1.startViewTransition(r4)
            android.animation.Animator r5 = r0.animator
            android.support.v4.app.FragmentManagerImpl$4 r6 = new android.support.v4.app.FragmentManagerImpl$4
            r6.<init>(r10, r1, r4, r11)
            r5.addListener(r6)
            goto L_0x00f2
        L_0x00ed:
            android.view.View r1 = r11.mView
            r1.setVisibility(r3)
        L_0x00f2:
            android.view.View r1 = r11.mView
            setHWLayerAnimListenerIfAlpha(r1, r0)
            android.animation.Animator r0 = r0.animator
            r0.start()
            goto L_0x012c
        L_0x00fd:
            if (r0 == 0) goto L_0x0110
            android.view.View r1 = r11.mView
            setHWLayerAnimListenerIfAlpha(r1, r0)
            android.view.View r1 = r11.mView
            android.view.animation.Animation r4 = r0.animation
            r1.startAnimation(r4)
            android.view.animation.Animation r0 = r0.animation
            r0.start()
        L_0x0110:
            boolean r0 = r11.mHidden
            if (r0 == 0) goto L_0x011d
            boolean r0 = r11.isHideReplaced()
            if (r0 != 0) goto L_0x011d
            r0 = 8
            goto L_0x011e
        L_0x011d:
            r0 = r3
        L_0x011e:
            android.view.View r1 = r11.mView
            r1.setVisibility(r0)
            boolean r0 = r11.isHideReplaced()
            if (r0 == 0) goto L_0x012c
            r11.setHideReplaced(r3)
        L_0x012c:
            boolean r0 = r11.mAdded
            if (r0 == 0) goto L_0x013a
            boolean r0 = r11.mHasMenu
            if (r0 == 0) goto L_0x013a
            boolean r0 = r11.mMenuVisible
            if (r0 == 0) goto L_0x013a
            r10.mNeedMenuInvalidate = r2
        L_0x013a:
            r11.mHiddenChanged = r3
            boolean r10 = r11.mHidden
            r11.onHiddenChanged(r10)
        L_0x0141:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.moveFragmentToExpectedState(android.support.v4.app.Fragment):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: int} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0073, code lost:
        if (r0 != 4) goto L_0x03c8;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x0274  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0278  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0280  */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x03cd  */
    /* JADX WARNING: Removed duplicated region for block: B:213:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void moveToState(android.support.p000v4.app.Fragment r17, int r18, int r19, int r20, boolean r21) {
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
            r9 = 4
            r10 = 3
            if (r1 == 0) goto L_0x0038
            int r1 = r7.mState
            if (r1 >= r9) goto L_0x0038
            if (r0 <= r10) goto L_0x0038
            r11 = r10
            goto L_0x0039
        L_0x0038:
            r11 = r0
        L_0x0039:
            int r0 = r7.mState
            r12 = 2
            r13 = 0
            r14 = 0
            if (r0 > r11) goto L_0x028c
            boolean r0 = r7.mFromLayout
            if (r0 == 0) goto L_0x0049
            boolean r0 = r7.mInLayout
            if (r0 != 0) goto L_0x0049
            return
        L_0x0049:
            android.view.View r0 = r17.getAnimatingAway()
            if (r0 != 0) goto L_0x0055
            android.animation.Animator r0 = r17.getAnimator()
            if (r0 == 0) goto L_0x0069
        L_0x0055:
            r7.setAnimatingAway(r14)
            r7.setAnimator(r14)
            int r2 = r17.getStateAfterAnimating()
            r3 = 0
            r4 = 0
            r5 = 1
            r0 = r16
            r1 = r17
            r0.moveToState(r1, r2, r3, r4, r5)
        L_0x0069:
            int r0 = r7.mState
            if (r0 == 0) goto L_0x0077
            if (r0 == r8) goto L_0x0177
            if (r0 == r12) goto L_0x0272
            if (r0 == r10) goto L_0x0276
            if (r0 == r9) goto L_0x027e
            goto L_0x03c8
        L_0x0077:
            if (r11 <= 0) goto L_0x0177
            android.os.Bundle r0 = r7.mSavedFragmentState
            if (r0 == 0) goto L_0x00cc
            android.support.v4.app.FragmentHostCallback r1 = r6.mHost
            android.content.Context r1 = r1.getContext()
            java.lang.ClassLoader r1 = r1.getClassLoader()
            r0.setClassLoader(r1)
            android.os.Bundle r0 = r7.mSavedFragmentState
            java.lang.String r1 = "android:view_state"
            android.util.SparseArray r0 = r0.getSparseParcelableArray(r1)
            r7.mSavedViewState = r0
            android.os.Bundle r0 = r7.mSavedFragmentState
            java.lang.String r1 = "android:target_state"
            android.support.v4.app.Fragment r0 = r6.getFragment(r0, r1)
            r7.mTarget = r0
            android.support.v4.app.Fragment r0 = r7.mTarget
            if (r0 == 0) goto L_0x00ac
            android.os.Bundle r0 = r7.mSavedFragmentState
            java.lang.String r1 = "android:target_req_state"
            int r0 = r0.getInt(r1, r13)
            r7.mTargetRequestCode = r0
        L_0x00ac:
            java.lang.Boolean r0 = r7.mSavedUserVisibleHint
            if (r0 == 0) goto L_0x00b9
            boolean r0 = r0.booleanValue()
            r7.mUserVisibleHint = r0
            r7.mSavedUserVisibleHint = r14
            goto L_0x00c3
        L_0x00b9:
            android.os.Bundle r0 = r7.mSavedFragmentState
            java.lang.String r1 = "android:user_visible_hint"
            boolean r0 = r0.getBoolean(r1, r8)
            r7.mUserVisibleHint = r0
        L_0x00c3:
            boolean r0 = r7.mUserVisibleHint
            if (r0 != 0) goto L_0x00cc
            r7.mDeferStart = r8
            if (r11 <= r10) goto L_0x00cc
            r11 = r10
        L_0x00cc:
            android.support.v4.app.FragmentHostCallback r0 = r6.mHost
            r7.mHost = r0
            android.support.v4.app.Fragment r1 = r6.mParent
            r7.mParentFragment = r1
            if (r1 == 0) goto L_0x00d9
            android.support.v4.app.FragmentManagerImpl r0 = r1.mChildFragmentManager
            goto L_0x00db
        L_0x00d9:
            android.support.v4.app.FragmentManagerImpl r0 = r0.mFragmentManager
        L_0x00db:
            r7.mFragmentManager = r0
            android.support.v4.app.Fragment r0 = r7.mTarget
            java.lang.String r15 = "Fragment "
            if (r0 == 0) goto L_0x011b
            android.util.SparseArray<android.support.v4.app.Fragment> r1 = r6.mActive
            int r0 = r0.mIndex
            java.lang.Object r0 = r1.get(r0)
            android.support.v4.app.Fragment r1 = r7.mTarget
            if (r0 != r1) goto L_0x00fd
            int r0 = r1.mState
            if (r0 >= r8) goto L_0x011b
            r2 = 1
            r3 = 0
            r4 = 0
            r5 = 1
            r0 = r16
            r0.moveToState(r1, r2, r3, r4, r5)
            goto L_0x011b
        L_0x00fd:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r15)
            r1.append(r7)
            java.lang.String r2 = " declared target fragment "
            r1.append(r2)
            android.support.v4.app.Fragment r2 = r7.mTarget
            java.lang.String r3 = " that does not belong to this FragmentManager!"
            java.lang.String r1 = com.android.tools.p006r8.GeneratedOutlineSupport.outline11(r1, r2, r3)
            r0.<init>(r1)
            throw r0
        L_0x011b:
            android.support.v4.app.FragmentHostCallback r0 = r6.mHost
            android.content.Context r0 = r0.getContext()
            r6.dispatchOnFragmentPreAttached(r7, r0, r13)
            r7.mCalled = r13
            android.support.v4.app.FragmentHostCallback r0 = r6.mHost
            android.content.Context r0 = r0.getContext()
            r7.onAttach((android.content.Context) r0)
            boolean r0 = r7.mCalled
            if (r0 == 0) goto L_0x016b
            android.support.v4.app.Fragment r0 = r7.mParentFragment
            if (r0 != 0) goto L_0x0141
            android.support.v4.app.FragmentHostCallback r0 = r6.mHost
            android.support.v4.app.FragmentActivity$HostCallbacks r0 = (android.support.p000v4.app.FragmentActivity.HostCallbacks) r0
            android.support.v4.app.FragmentActivity r0 = android.support.p000v4.app.FragmentActivity.this
            r0.onAttachFragment(r7)
            goto L_0x0144
        L_0x0141:
            r0.onAttachFragment(r7)
        L_0x0144:
            android.support.v4.app.FragmentHostCallback r0 = r6.mHost
            android.content.Context r0 = r0.getContext()
            r6.dispatchOnFragmentAttached(r7, r0, r13)
            boolean r0 = r7.mIsCreated
            if (r0 != 0) goto L_0x0161
            android.os.Bundle r0 = r7.mSavedFragmentState
            r6.dispatchOnFragmentPreCreated(r7, r0, r13)
            android.os.Bundle r0 = r7.mSavedFragmentState
            r7.performCreate(r0)
            android.os.Bundle r0 = r7.mSavedFragmentState
            r6.dispatchOnFragmentCreated(r7, r0, r13)
            goto L_0x0168
        L_0x0161:
            android.os.Bundle r0 = r7.mSavedFragmentState
            r7.restoreChildFragmentState(r0)
            r7.mState = r8
        L_0x0168:
            r7.mRetaining = r13
            goto L_0x0177
        L_0x016b:
            android.support.v4.app.SuperNotCalledException r0 = new android.support.v4.app.SuperNotCalledException
            java.lang.String r1 = " did not call through to super.onAttach()"
            java.lang.String r1 = com.android.tools.p006r8.GeneratedOutlineSupport.outline7(r15, r7, r1)
            r0.<init>(r1)
            throw r0
        L_0x0177:
            boolean r0 = r7.mFromLayout
            r1 = 8
            if (r0 == 0) goto L_0x01af
            boolean r0 = r7.mPerformedCreateView
            if (r0 != 0) goto L_0x01af
            android.os.Bundle r0 = r7.mSavedFragmentState
            android.view.LayoutInflater r0 = r7.performGetLayoutInflater(r0)
            android.os.Bundle r2 = r7.mSavedFragmentState
            r7.performCreateView(r0, r14, r2)
            android.view.View r0 = r7.mView
            if (r0 == 0) goto L_0x01ad
            r7.mInnerView = r0
            r0.setSaveFromParentEnabled(r13)
            boolean r0 = r7.mHidden
            if (r0 == 0) goto L_0x019e
            android.view.View r0 = r7.mView
            r0.setVisibility(r1)
        L_0x019e:
            android.view.View r0 = r7.mView
            android.os.Bundle r2 = r7.mSavedFragmentState
            r7.onViewCreated(r0, r2)
            android.view.View r0 = r7.mView
            android.os.Bundle r2 = r7.mSavedFragmentState
            r6.dispatchOnFragmentViewCreated(r7, r0, r2, r13)
            goto L_0x01af
        L_0x01ad:
            r7.mInnerView = r14
        L_0x01af:
            if (r11 <= r8) goto L_0x0272
            boolean r0 = r7.mFromLayout
            if (r0 != 0) goto L_0x025d
            int r0 = r7.mContainerId
            if (r0 == 0) goto L_0x0215
            r2 = -1
            if (r0 == r2) goto L_0x0204
            android.support.v4.app.FragmentContainer r2 = r6.mContainer
            android.view.View r0 = r2.onFindViewById(r0)
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            if (r0 != 0) goto L_0x0216
            boolean r2 = r7.mRestored
            if (r2 == 0) goto L_0x01cb
            goto L_0x0216
        L_0x01cb:
            android.content.res.Resources r0 = r17.getResources()     // Catch:{ NotFoundException -> 0x01d6 }
            int r1 = r7.mContainerId     // Catch:{ NotFoundException -> 0x01d6 }
            java.lang.String r0 = r0.getResourceName(r1)     // Catch:{ NotFoundException -> 0x01d6 }
            goto L_0x01d8
        L_0x01d6:
            java.lang.String r0 = "unknown"
        L_0x01d8:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "No view found for id 0x"
            java.lang.StringBuilder r2 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r2)
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
            r6.throwException(r1)
            throw r14
        L_0x0204:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Cannot create fragment "
            java.lang.String r2 = " for a container view with no id"
            java.lang.String r1 = com.android.tools.p006r8.GeneratedOutlineSupport.outline7(r1, r7, r2)
            r0.<init>(r1)
            r6.throwException(r0)
            throw r14
        L_0x0215:
            r0 = r14
        L_0x0216:
            r7.mContainer = r0
            android.os.Bundle r2 = r7.mSavedFragmentState
            android.view.LayoutInflater r2 = r7.performGetLayoutInflater(r2)
            android.os.Bundle r3 = r7.mSavedFragmentState
            r7.performCreateView(r2, r0, r3)
            android.view.View r2 = r7.mView
            if (r2 == 0) goto L_0x025b
            r7.mInnerView = r2
            r2.setSaveFromParentEnabled(r13)
            if (r0 == 0) goto L_0x0233
            android.view.View r2 = r7.mView
            r0.addView(r2)
        L_0x0233:
            boolean r0 = r7.mHidden
            if (r0 == 0) goto L_0x023c
            android.view.View r0 = r7.mView
            r0.setVisibility(r1)
        L_0x023c:
            android.view.View r0 = r7.mView
            android.os.Bundle r1 = r7.mSavedFragmentState
            r7.onViewCreated(r0, r1)
            android.view.View r0 = r7.mView
            android.os.Bundle r1 = r7.mSavedFragmentState
            r6.dispatchOnFragmentViewCreated(r7, r0, r1, r13)
            android.view.View r0 = r7.mView
            int r0 = r0.getVisibility()
            if (r0 != 0) goto L_0x0257
            android.view.ViewGroup r0 = r7.mContainer
            if (r0 == 0) goto L_0x0257
            goto L_0x0258
        L_0x0257:
            r8 = r13
        L_0x0258:
            r7.mIsNewlyAdded = r8
            goto L_0x025d
        L_0x025b:
            r7.mInnerView = r14
        L_0x025d:
            android.os.Bundle r0 = r7.mSavedFragmentState
            r7.performActivityCreated(r0)
            android.os.Bundle r0 = r7.mSavedFragmentState
            r6.dispatchOnFragmentActivityCreated(r7, r0, r13)
            android.view.View r0 = r7.mView
            if (r0 == 0) goto L_0x0270
            android.os.Bundle r0 = r7.mSavedFragmentState
            r7.restoreViewState(r0)
        L_0x0270:
            r7.mSavedFragmentState = r14
        L_0x0272:
            if (r11 <= r12) goto L_0x0276
            r7.mState = r10
        L_0x0276:
            if (r11 <= r10) goto L_0x027e
            r17.performStart()
            r6.dispatchOnFragmentStarted(r7, r13)
        L_0x027e:
            if (r11 <= r9) goto L_0x03c8
            r17.performResume()
            r6.dispatchOnFragmentResumed(r7, r13)
            r7.mSavedFragmentState = r14
            r7.mSavedViewState = r14
            goto L_0x03c8
        L_0x028c:
            if (r0 <= r11) goto L_0x03c8
            if (r0 == r8) goto L_0x0362
            if (r0 == r12) goto L_0x02b0
            if (r0 == r10) goto L_0x02ab
            if (r0 == r9) goto L_0x02a3
            r1 = 5
            if (r0 == r1) goto L_0x029b
            goto L_0x03c8
        L_0x029b:
            if (r11 >= r1) goto L_0x02a3
            r17.performPause()
            r6.dispatchOnFragmentPaused(r7, r13)
        L_0x02a3:
            if (r11 >= r9) goto L_0x02ab
            r17.performStop()
            r6.dispatchOnFragmentStopped(r7, r13)
        L_0x02ab:
            if (r11 >= r10) goto L_0x02b0
            r17.performReallyStop()
        L_0x02b0:
            if (r11 >= r12) goto L_0x0362
            android.view.View r0 = r7.mView
            if (r0 == 0) goto L_0x02ca
            android.support.v4.app.FragmentHostCallback r0 = r6.mHost
            android.support.v4.app.FragmentActivity$HostCallbacks r0 = (android.support.p000v4.app.FragmentActivity.HostCallbacks) r0
            android.support.v4.app.FragmentActivity r0 = android.support.p000v4.app.FragmentActivity.this
            boolean r0 = r0.isFinishing()
            r0 = r0 ^ r8
            if (r0 == 0) goto L_0x02ca
            android.util.SparseArray<android.os.Parcelable> r0 = r7.mSavedViewState
            if (r0 != 0) goto L_0x02ca
            r16.saveFragmentViewState(r17)
        L_0x02ca:
            r17.performDestroyView()
            r6.dispatchOnFragmentViewDestroyed(r7, r13)
            android.view.View r0 = r7.mView
            if (r0 == 0) goto L_0x0353
            android.view.ViewGroup r1 = r7.mContainer
            if (r1 == 0) goto L_0x0353
            r1.endViewTransition(r0)
            android.view.View r0 = r7.mView
            r0.clearAnimation()
            int r0 = r6.mCurState
            r1 = 0
            if (r0 <= 0) goto L_0x0300
            boolean r0 = r6.mDestroyed
            if (r0 != 0) goto L_0x0300
            android.view.View r0 = r7.mView
            int r0 = r0.getVisibility()
            if (r0 != 0) goto L_0x0300
            float r0 = r7.mPostponedAlpha
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 < 0) goto L_0x0300
            r0 = r19
            r2 = r20
            android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator r0 = r6.loadAnimation(r7, r0, r13, r2)
            goto L_0x0301
        L_0x0300:
            r0 = r14
        L_0x0301:
            r7.mPostponedAlpha = r1
            if (r0 == 0) goto L_0x034c
            android.view.View r1 = r7.mView
            android.view.ViewGroup r2 = r7.mContainer
            r2.startViewTransition(r1)
            r7.setStateAfterAnimating(r11)
            android.view.animation.Animation r3 = r0.animation
            if (r3 == 0) goto L_0x0332
            android.support.v4.app.FragmentManagerImpl$EndViewTransitionAnimator r4 = new android.support.v4.app.FragmentManagerImpl$EndViewTransitionAnimator
            r4.<init>(r3, r2, r1)
            android.view.View r3 = r7.mView
            r7.setAnimatingAway(r3)
            android.view.animation.Animation$AnimationListener r3 = getAnimationListener(r4)
            android.support.v4.app.FragmentManagerImpl$2 r5 = new android.support.v4.app.FragmentManagerImpl$2
            r5.<init>(r3, r2, r7)
            r4.setAnimationListener(r5)
            setHWLayerAnimListenerIfAlpha(r1, r0)
            android.view.View r0 = r7.mView
            r0.startAnimation(r4)
            goto L_0x034c
        L_0x0332:
            android.animation.Animator r3 = r0.animator
            r7.setAnimator(r3)
            android.support.v4.app.FragmentManagerImpl$3 r4 = new android.support.v4.app.FragmentManagerImpl$3
            r4.<init>(r2, r1, r7)
            r3.addListener(r4)
            android.view.View r1 = r7.mView
            r3.setTarget(r1)
            android.view.View r1 = r7.mView
            setHWLayerAnimListenerIfAlpha(r1, r0)
            r3.start()
        L_0x034c:
            android.view.ViewGroup r0 = r7.mContainer
            android.view.View r1 = r7.mView
            r0.removeView(r1)
        L_0x0353:
            r7.mContainer = r14
            r7.mView = r14
            r7.mViewLifecycleOwner = r14
            android.arch.lifecycle.MutableLiveData<android.arch.lifecycle.LifecycleOwner> r0 = r7.mViewLifecycleOwnerLiveData
            r0.setValue(r14)
            r7.mInnerView = r14
            r7.mInLayout = r13
        L_0x0362:
            if (r11 >= r8) goto L_0x03c8
            boolean r0 = r6.mDestroyed
            if (r0 == 0) goto L_0x0389
            android.view.View r0 = r17.getAnimatingAway()
            if (r0 == 0) goto L_0x0379
            android.view.View r0 = r17.getAnimatingAway()
            r7.setAnimatingAway(r14)
            r0.clearAnimation()
            goto L_0x0389
        L_0x0379:
            android.animation.Animator r0 = r17.getAnimator()
            if (r0 == 0) goto L_0x0389
            android.animation.Animator r0 = r17.getAnimator()
            r7.setAnimator(r14)
            r0.cancel()
        L_0x0389:
            android.view.View r0 = r17.getAnimatingAway()
            if (r0 != 0) goto L_0x03c4
            android.animation.Animator r0 = r17.getAnimator()
            if (r0 == 0) goto L_0x0396
            goto L_0x03c4
        L_0x0396:
            boolean r0 = r7.mRetaining
            if (r0 != 0) goto L_0x03a1
            r17.performDestroy()
            r6.dispatchOnFragmentDestroyed(r7, r13)
            goto L_0x03a3
        L_0x03a1:
            r7.mState = r13
        L_0x03a3:
            r17.performDetach()
            r6.dispatchOnFragmentDetached(r7, r13)
            if (r21 != 0) goto L_0x03c8
            boolean r0 = r7.mRetaining
            if (r0 != 0) goto L_0x03bd
            int r0 = r7.mIndex
            if (r0 >= 0) goto L_0x03b4
            goto L_0x03c8
        L_0x03b4:
            android.util.SparseArray<android.support.v4.app.Fragment> r1 = r6.mActive
            r1.put(r0, r14)
            r17.initState()
            goto L_0x03c8
        L_0x03bd:
            r7.mHost = r14
            r7.mParentFragment = r14
            r7.mFragmentManager = r14
            goto L_0x03c8
        L_0x03c4:
            r7.setStateAfterAnimating(r11)
            goto L_0x03c9
        L_0x03c8:
            r8 = r11
        L_0x03c9:
            int r0 = r7.mState
            if (r0 == r8) goto L_0x03fc
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "moveToState: Fragment state for "
            r0.append(r1)
            r0.append(r7)
            java.lang.String r1 = " not updated inline; "
            r0.append(r1)
            java.lang.String r1 = "expected state "
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
        L_0x03fc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.moveToState(android.support.v4.app.Fragment, int, int, int, boolean):void");
    }

    public void noteStateNotSaved() {
        this.mSavedNonConfig = null;
        this.mStateSaved = false;
        this.mStopped = false;
        int size = this.mAdded.size();
        for (int i = 0; i < size; i++) {
            Fragment fragment = this.mAdded.get(i);
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        Fragment fragment;
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue((String) null, "class");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FragmentTag.Fragment);
        int i = 0;
        if (attributeValue == null) {
            attributeValue = obtainStyledAttributes.getString(0);
        }
        String str2 = attributeValue;
        int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        String string = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (!Fragment.isSupportFragmentClass(this.mHost.getContext(), str2)) {
            return null;
        }
        if (view != null) {
            i = view.getId();
        }
        if (i == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + str2);
        }
        Fragment findFragmentById = resourceId != -1 ? findFragmentById(resourceId) : null;
        if (findFragmentById == null && string != null) {
            findFragmentById = findFragmentByTag(string);
        }
        if (findFragmentById == null && i != -1) {
            findFragmentById = findFragmentById(i);
        }
        if (findFragmentById == null) {
            Fragment instantiate = this.mContainer.instantiate(context, str2, (Bundle) null);
            instantiate.mFromLayout = true;
            instantiate.mFragmentId = resourceId != 0 ? resourceId : i;
            instantiate.mContainerId = i;
            instantiate.mTag = string;
            instantiate.mInLayout = true;
            instantiate.mFragmentManager = this;
            FragmentHostCallback fragmentHostCallback = this.mHost;
            instantiate.mHost = fragmentHostCallback;
            instantiate.onInflate(fragmentHostCallback.getContext(), attributeSet, instantiate.mSavedFragmentState);
            addFragment(instantiate, true);
            fragment = instantiate;
        } else if (!findFragmentById.mInLayout) {
            findFragmentById.mInLayout = true;
            FragmentHostCallback fragmentHostCallback2 = this.mHost;
            findFragmentById.mHost = fragmentHostCallback2;
            if (!findFragmentById.mRetaining) {
                findFragmentById.onInflate(fragmentHostCallback2.getContext(), attributeSet, findFragmentById.mSavedFragmentState);
            }
            fragment = findFragmentById;
        } else {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(i) + " with another fragment for " + str2);
        }
        if (this.mCurState >= 1 || !fragment.mFromLayout) {
            moveToState(fragment, this.mCurState, 0, 0, false);
        } else {
            moveToState(fragment, 1, 0, 0, false);
        }
        View view2 = fragment.mView;
        if (view2 != null) {
            if (resourceId != 0) {
                view2.setId(resourceId);
            }
            if (fragment.mView.getTag() == null) {
                fragment.mView.setTag(string);
            }
            return fragment.mView;
        }
        throw new IllegalStateException(GeneratedOutlineSupport.outline9("Fragment ", str2, " did not create a view."));
    }

    public void performPendingDeferredStart(Fragment fragment) {
        if (!fragment.mDeferStart) {
            return;
        }
        if (this.mExecutingActions) {
            this.mHavePendingDeferredStart = true;
            return;
        }
        fragment.mDeferStart = false;
        moveToState(fragment, this.mCurState, 0, 0, false);
    }

    public void popBackStack(int i, int i2) {
        if (i >= 0) {
            enqueueAction(new PopBackStackState((String) null, i, i2), false);
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport.outline5("Bad id: ", i));
    }

    public boolean popBackStackImmediate() {
        FragmentManager peekChildFragmentManager;
        checkStateLoss();
        execPendingActions();
        ensureExecReady(true);
        Fragment fragment = this.mPrimaryNav;
        if (fragment != null && (peekChildFragmentManager = fragment.peekChildFragmentManager()) != null && peekChildFragmentManager.popBackStackImmediate()) {
            return true;
        }
        boolean popBackStackState = popBackStackState(this.mTmpRecords, this.mTmpIsPop, (String) null, -1, 0);
        if (popBackStackState) {
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            } finally {
                cleanupExec();
            }
        }
        doPendingDeferredStart();
        burpActive();
        return popBackStackState;
    }

    /* access modifiers changed from: package-private */
    public boolean popBackStackState(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, String str, int i, int i2) {
        int i3;
        ArrayList<BackStackRecord> arrayList3 = this.mBackStack;
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
                    BackStackRecord backStackRecord = this.mBackStack.get(i3);
                    if ((str != null && str.equals(backStackRecord.mName)) || (i >= 0 && i == backStackRecord.mIndex)) {
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
                        BackStackRecord backStackRecord2 = this.mBackStack.get(i3);
                        if ((str == null || !str.equals(backStackRecord2.mName)) && (i < 0 || i != backStackRecord2.mIndex)) {
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

    public void putFragment(Bundle bundle, String str, Fragment fragment) {
        int i = fragment.mIndex;
        if (i >= 0) {
            bundle.putInt(str, i);
        } else {
            throwException(new IllegalStateException(GeneratedOutlineSupport.outline7("Fragment ", fragment, " is not currently in the FragmentManager")));
            throw null;
        }
    }

    public void removeFragment(Fragment fragment) {
        boolean z = !fragment.isInBackStack();
        if (!fragment.mDetached || z) {
            synchronized (this.mAdded) {
                this.mAdded.remove(fragment);
            }
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void restoreAllState(Parcelable parcelable, FragmentManagerNonConfig fragmentManagerNonConfig) {
        List<ViewModelStore> list;
        List<FragmentManagerNonConfig> list2;
        if (parcelable != null) {
            FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
            if (fragmentManagerState.mActive != null) {
                int i = 0;
                if (fragmentManagerNonConfig != null) {
                    List<Fragment> fragments = fragmentManagerNonConfig.getFragments();
                    list2 = fragmentManagerNonConfig.getChildNonConfigs();
                    list = fragmentManagerNonConfig.getViewModelStores();
                    int size = fragments != null ? fragments.size() : 0;
                    int i2 = 0;
                    while (i2 < size) {
                        Fragment fragment = fragments.get(i2);
                        int i3 = 0;
                        while (true) {
                            FragmentState[] fragmentStateArr = fragmentManagerState.mActive;
                            if (i3 >= fragmentStateArr.length || fragmentStateArr[i3].mIndex == fragment.mIndex) {
                                FragmentState[] fragmentStateArr2 = fragmentManagerState.mActive;
                            } else {
                                i3++;
                            }
                        }
                        FragmentState[] fragmentStateArr22 = fragmentManagerState.mActive;
                        if (i3 != fragmentStateArr22.length) {
                            FragmentState fragmentState = fragmentStateArr22[i3];
                            fragmentState.mInstance = fragment;
                            fragment.mSavedViewState = null;
                            fragment.mBackStackNesting = 0;
                            fragment.mInLayout = false;
                            fragment.mAdded = false;
                            fragment.mTarget = null;
                            Bundle bundle = fragmentState.mSavedFragmentState;
                            if (bundle != null) {
                                bundle.setClassLoader(this.mHost.getContext().getClassLoader());
                                fragment.mSavedViewState = fragmentState.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                                fragment.mSavedFragmentState = fragmentState.mSavedFragmentState;
                            }
                            i2++;
                        } else {
                            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Could not find active fragment with index ");
                            outline13.append(fragment.mIndex);
                            throwException(new IllegalStateException(outline13.toString()));
                            throw null;
                        }
                    }
                } else {
                    list2 = null;
                    list = null;
                }
                this.mActive = new SparseArray<>(fragmentManagerState.mActive.length);
                int i4 = 0;
                while (true) {
                    FragmentState[] fragmentStateArr3 = fragmentManagerState.mActive;
                    if (i4 >= fragmentStateArr3.length) {
                        break;
                    }
                    FragmentState fragmentState2 = fragmentStateArr3[i4];
                    if (fragmentState2 != null) {
                        Fragment instantiate = fragmentState2.instantiate(this.mHost, this.mContainer, this.mParent, (list2 == null || i4 >= list2.size()) ? null : list2.get(i4), (list == null || i4 >= list.size()) ? null : list.get(i4));
                        this.mActive.put(instantiate.mIndex, instantiate);
                        fragmentState2.mInstance = null;
                    }
                    i4++;
                }
                if (fragmentManagerNonConfig != null) {
                    List<Fragment> fragments2 = fragmentManagerNonConfig.getFragments();
                    int size2 = fragments2 != null ? fragments2.size() : 0;
                    for (int i5 = 0; i5 < size2; i5++) {
                        Fragment fragment2 = fragments2.get(i5);
                        int i6 = fragment2.mTargetIndex;
                        if (i6 >= 0) {
                            fragment2.mTarget = this.mActive.get(i6);
                            if (fragment2.mTarget == null) {
                                Log.w("FragmentManager", "Re-attaching retained fragment " + fragment2 + " target no longer exists: " + fragment2.mTargetIndex);
                            }
                        }
                    }
                }
                this.mAdded.clear();
                if (fragmentManagerState.mAdded != null) {
                    int i7 = 0;
                    while (true) {
                        int[] iArr = fragmentManagerState.mAdded;
                        if (i7 >= iArr.length) {
                            break;
                        }
                        Fragment fragment3 = this.mActive.get(iArr[i7]);
                        if (fragment3 != null) {
                            fragment3.mAdded = true;
                            if (!this.mAdded.contains(fragment3)) {
                                synchronized (this.mAdded) {
                                    this.mAdded.add(fragment3);
                                }
                                i7++;
                            } else {
                                throw new IllegalStateException("Already added!");
                            }
                        } else {
                            StringBuilder outline132 = GeneratedOutlineSupport.outline13("No instantiated fragment for index #");
                            outline132.append(fragmentManagerState.mAdded[i7]);
                            throwException(new IllegalStateException(outline132.toString()));
                            throw null;
                        }
                    }
                }
                BackStackState[] backStackStateArr = fragmentManagerState.mBackStack;
                if (backStackStateArr != null) {
                    this.mBackStack = new ArrayList<>(backStackStateArr.length);
                    while (true) {
                        BackStackState[] backStackStateArr2 = fragmentManagerState.mBackStack;
                        if (i >= backStackStateArr2.length) {
                            break;
                        }
                        BackStackRecord instantiate2 = backStackStateArr2[i].instantiate(this);
                        this.mBackStack.add(instantiate2);
                        int i8 = instantiate2.mIndex;
                        if (i8 >= 0) {
                            setBackStackIndex(i8, instantiate2);
                        }
                        i++;
                    }
                } else {
                    this.mBackStack = null;
                }
                int i9 = fragmentManagerState.mPrimaryNavActiveIndex;
                if (i9 >= 0) {
                    this.mPrimaryNav = this.mActive.get(i9);
                }
                this.mNextFragmentIndex = fragmentManagerState.mNextFragmentIndex;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public FragmentManagerNonConfig retainNonConfig() {
        setRetaining(this.mSavedNonConfig);
        return this.mSavedNonConfig;
    }

    /* access modifiers changed from: package-private */
    public Parcelable saveAllState() {
        int i;
        int i2;
        BackStackState[] backStackStateArr;
        int[] iArr;
        int size;
        forcePostponedTransactions();
        SparseArray<Fragment> sparseArray = this.mActive;
        if (sparseArray == null) {
            i2 = 0;
            i = 0;
        } else {
            i = sparseArray.size();
            i2 = 0;
        }
        while (true) {
            backStackStateArr = null;
            if (i2 >= i) {
                break;
            }
            Fragment valueAt = this.mActive.valueAt(i2);
            if (valueAt != null) {
                if (valueAt.getAnimatingAway() != null) {
                    int stateAfterAnimating = valueAt.getStateAfterAnimating();
                    View animatingAway = valueAt.getAnimatingAway();
                    Animation animation = animatingAway.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        animatingAway.clearAnimation();
                    }
                    valueAt.setAnimatingAway((View) null);
                    moveToState(valueAt, stateAfterAnimating, 0, 0, false);
                } else if (valueAt.getAnimator() != null) {
                    valueAt.getAnimator().end();
                }
            }
            i2++;
        }
        execPendingActions();
        this.mStateSaved = true;
        this.mSavedNonConfig = null;
        SparseArray<Fragment> sparseArray2 = this.mActive;
        if (sparseArray2 == null || sparseArray2.size() <= 0) {
            return null;
        }
        int size2 = this.mActive.size();
        FragmentState[] fragmentStateArr = new FragmentState[size2];
        boolean z = false;
        for (int i3 = 0; i3 < size2; i3++) {
            Fragment valueAt2 = this.mActive.valueAt(i3);
            if (valueAt2 != null) {
                if (valueAt2.mIndex >= 0) {
                    FragmentState fragmentState = new FragmentState(valueAt2);
                    fragmentStateArr[i3] = fragmentState;
                    if (valueAt2.mState <= 0 || fragmentState.mSavedFragmentState != null) {
                        fragmentState.mSavedFragmentState = valueAt2.mSavedFragmentState;
                    } else {
                        fragmentState.mSavedFragmentState = saveFragmentBasicState(valueAt2);
                        Fragment fragment = valueAt2.mTarget;
                        if (fragment != null) {
                            if (fragment.mIndex >= 0) {
                                if (fragmentState.mSavedFragmentState == null) {
                                    fragmentState.mSavedFragmentState = new Bundle();
                                }
                                putFragment(fragmentState.mSavedFragmentState, "android:target_state", valueAt2.mTarget);
                                int i4 = valueAt2.mTargetRequestCode;
                                if (i4 != 0) {
                                    fragmentState.mSavedFragmentState.putInt("android:target_req_state", i4);
                                }
                            } else {
                                throwException(new IllegalStateException("Failure saving state: " + valueAt2 + " has target not in fragment manager: " + valueAt2.mTarget));
                                throw null;
                            }
                        }
                    }
                    z = true;
                } else {
                    throwException(new IllegalStateException("Failure saving state: active " + valueAt2 + " has cleared index: " + valueAt2.mIndex));
                    throw null;
                }
            }
        }
        if (!z) {
            return null;
        }
        int size3 = this.mAdded.size();
        if (size3 > 0) {
            iArr = new int[size3];
            int i5 = 0;
            while (i5 < size3) {
                iArr[i5] = this.mAdded.get(i5).mIndex;
                if (iArr[i5] >= 0) {
                    i5++;
                } else {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("Failure saving state: active ");
                    outline13.append(this.mAdded.get(i5));
                    outline13.append(" has cleared index: ");
                    outline13.append(iArr[i5]);
                    throwException(new IllegalStateException(outline13.toString()));
                    throw null;
                }
            }
        } else {
            iArr = null;
        }
        ArrayList<BackStackRecord> arrayList = this.mBackStack;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i6 = 0; i6 < size; i6++) {
                backStackStateArr[i6] = new BackStackState(this.mBackStack.get(i6));
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.mActive = fragmentStateArr;
        fragmentManagerState.mAdded = iArr;
        fragmentManagerState.mBackStack = backStackStateArr;
        Fragment fragment2 = this.mPrimaryNav;
        if (fragment2 != null) {
            fragmentManagerState.mPrimaryNavActiveIndex = fragment2.mIndex;
        }
        fragmentManagerState.mNextFragmentIndex = this.mNextFragmentIndex;
        saveNonConfig();
        return fragmentManagerState;
    }

    /* access modifiers changed from: package-private */
    public Bundle saveFragmentBasicState(Fragment fragment) {
        Bundle bundle;
        if (this.mStateBundle == null) {
            this.mStateBundle = new Bundle();
        }
        fragment.performSaveInstanceState(this.mStateBundle);
        dispatchOnFragmentSaveInstanceState(fragment, this.mStateBundle, false);
        if (!this.mStateBundle.isEmpty()) {
            bundle = this.mStateBundle;
            this.mStateBundle = null;
        } else {
            bundle = null;
        }
        if (fragment.mView != null) {
            saveFragmentViewState(fragment);
        }
        if (fragment.mSavedViewState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", fragment.mSavedViewState);
        }
        if (!fragment.mUserVisibleHint) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", fragment.mUserVisibleHint);
        }
        return bundle;
    }

    public Fragment.SavedState saveFragmentInstanceState(Fragment fragment) {
        Bundle saveFragmentBasicState;
        if (fragment.mIndex < 0) {
            throwException(new IllegalStateException(GeneratedOutlineSupport.outline7("Fragment ", fragment, " is not currently in the FragmentManager")));
            throw null;
        } else if (fragment.mState <= 0 || (saveFragmentBasicState = saveFragmentBasicState(fragment)) == null) {
            return null;
        } else {
            return new Fragment.SavedState(saveFragmentBasicState);
        }
    }

    /* access modifiers changed from: package-private */
    public void saveFragmentViewState(Fragment fragment) {
        if (fragment.mInnerView != null) {
            SparseArray<Parcelable> sparseArray = this.mStateArray;
            if (sparseArray == null) {
                this.mStateArray = new SparseArray<>();
            } else {
                sparseArray.clear();
            }
            fragment.mInnerView.saveHierarchyState(this.mStateArray);
            if (this.mStateArray.size() > 0) {
                fragment.mSavedViewState = this.mStateArray;
                this.mStateArray = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void saveNonConfig() {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        FragmentManagerNonConfig fragmentManagerNonConfig;
        if (this.mActive != null) {
            arrayList3 = null;
            arrayList2 = null;
            arrayList = null;
            for (int i = 0; i < this.mActive.size(); i++) {
                Fragment valueAt = this.mActive.valueAt(i);
                if (valueAt != null) {
                    if (valueAt.mRetainInstance) {
                        if (arrayList3 == null) {
                            arrayList3 = new ArrayList();
                        }
                        arrayList3.add(valueAt);
                        Fragment fragment = valueAt.mTarget;
                        valueAt.mTargetIndex = fragment != null ? fragment.mIndex : -1;
                    }
                    FragmentManagerImpl fragmentManagerImpl = valueAt.mChildFragmentManager;
                    if (fragmentManagerImpl != null) {
                        fragmentManagerImpl.saveNonConfig();
                        fragmentManagerNonConfig = valueAt.mChildFragmentManager.mSavedNonConfig;
                    } else {
                        fragmentManagerNonConfig = valueAt.mChildNonConfig;
                    }
                    if (arrayList2 == null && fragmentManagerNonConfig != null) {
                        arrayList2 = new ArrayList(this.mActive.size());
                        for (int i2 = 0; i2 < i; i2++) {
                            arrayList2.add((Object) null);
                        }
                    }
                    if (arrayList2 != null) {
                        arrayList2.add(fragmentManagerNonConfig);
                    }
                    if (arrayList == null && valueAt.mViewModelStore != null) {
                        arrayList = new ArrayList(this.mActive.size());
                        for (int i3 = 0; i3 < i; i3++) {
                            arrayList.add((Object) null);
                        }
                    }
                    if (arrayList != null) {
                        arrayList.add(valueAt.mViewModelStore);
                    }
                }
            }
        } else {
            arrayList3 = null;
            arrayList2 = null;
            arrayList = null;
        }
        if (arrayList3 == null && arrayList2 == null && arrayList == null) {
            this.mSavedNonConfig = null;
        } else {
            this.mSavedNonConfig = new FragmentManagerNonConfig(arrayList3, arrayList2, arrayList);
        }
    }

    public void setBackStackIndex(int i, BackStackRecord backStackRecord) {
        synchronized (this) {
            if (this.mBackStackIndices == null) {
                this.mBackStackIndices = new ArrayList<>();
            }
            int size = this.mBackStackIndices.size();
            if (i < size) {
                this.mBackStackIndices.set(i, backStackRecord);
            } else {
                while (size < i) {
                    this.mBackStackIndices.add((Object) null);
                    if (this.mAvailBackStackIndices == null) {
                        this.mAvailBackStackIndices = new ArrayList<>();
                    }
                    this.mAvailBackStackIndices.add(Integer.valueOf(size));
                    size++;
                }
                this.mBackStackIndices.add(backStackRecord);
            }
        }
    }

    public void setPrimaryNavigationFragment(Fragment fragment) {
        if (fragment == null || (this.mActive.get(fragment.mIndex) == fragment && (fragment.mHost == null || fragment.getFragmentManager() == this))) {
            this.mPrimaryNav = fragment;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public void showFragment(Fragment fragment) {
        if (fragment.mHidden) {
            fragment.mHidden = false;
            fragment.mHiddenChanged = !fragment.mHiddenChanged;
        }
    }

    /* access modifiers changed from: package-private */
    public void startPendingDeferredFragments() {
        if (this.mActive != null) {
            for (int i = 0; i < this.mActive.size(); i++) {
                Fragment valueAt = this.mActive.valueAt(i);
                if (valueAt != null) {
                    performPendingDeferredStart(valueAt);
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.mParent;
        if (fragment != null) {
            R$dimen.buildShortClassTag(fragment, sb);
        } else {
            R$dimen.buildShortClassTag(this.mHost, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator */
    /* compiled from: FragmentManager */
    private static class AnimationOrAnimator {
        public final Animation animation;
        public final Animator animator;

        /* synthetic */ AnimationOrAnimator(Animation animation2, C00871 r2) {
            this.animation = animation2;
            this.animator = null;
            if (animation2 == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        /* synthetic */ AnimationOrAnimator(Animator animator2, C00871 r2) {
            this.animation = null;
            this.animator = animator2;
            if (animator2 == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$EndViewTransitionAnimator */
    /* compiled from: FragmentManager */
    private static class EndViewTransitionAnimator extends AnimationSet implements Runnable {
        private final View mChild;
        private boolean mEnded;
        private final ViewGroup mParent;
        private boolean mTransitionEnded;

        EndViewTransitionAnimator(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.mParent = viewGroup;
            this.mChild = view;
            addAnimation(animation);
        }

        public boolean getTransformation(long j, Transformation transformation) {
            if (this.mEnded) {
                return !this.mTransitionEnded;
            }
            if (!super.getTransformation(j, transformation)) {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }

        public void run() {
            this.mParent.endViewTransition(this.mChild);
            this.mTransitionEnded = true;
        }

        public boolean getTransformation(long j, Transformation transformation, float f) {
            if (this.mEnded) {
                return !this.mTransitionEnded;
            }
            if (!super.getTransformation(j, transformation, f)) {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView((View) null, str, context, attributeSet);
    }

    /* access modifiers changed from: package-private */
    public void moveToState(int i, boolean z) {
        FragmentHostCallback fragmentHostCallback;
        if (this.mHost == null && i != 0) {
            throw new IllegalStateException("No activity");
        } else if (z || i != this.mCurState) {
            this.mCurState = i;
            if (this.mActive != null) {
                int size = this.mAdded.size();
                for (int i2 = 0; i2 < size; i2++) {
                    moveFragmentToExpectedState(this.mAdded.get(i2));
                }
                int size2 = this.mActive.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    Fragment valueAt = this.mActive.valueAt(i3);
                    if (valueAt != null && ((valueAt.mRemoving || valueAt.mDetached) && !valueAt.mIsNewlyAdded)) {
                        moveFragmentToExpectedState(valueAt);
                    }
                }
                startPendingDeferredFragments();
                if (this.mNeedMenuInvalidate && (fragmentHostCallback = this.mHost) != null && this.mCurState == 5) {
                    FragmentActivity.this.supportInvalidateOptionsMenu();
                    this.mNeedMenuInvalidate = false;
                }
            }
        }
    }
}
