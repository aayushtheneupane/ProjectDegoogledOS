package androidx.fragment.app;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.core.app.ActivityCompat;
import androidx.core.app.SharedElementCallback;
import androidx.core.util.DebugUtils;
import androidx.lifecycle.C0442A;
import androidx.lifecycle.C0443B;
import androidx.lifecycle.C0450g;
import androidx.lifecycle.C0452i;
import androidx.lifecycle.C0453j;
import androidx.lifecycle.C0455l;
import androidx.lifecycle.C0460q;
import androidx.lifecycle.C0462s;
import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.Lifecycle$State;
import androidx.savedstate.C0607c;
import androidx.savedstate.C0608d;
import androidx.savedstate.C0609e;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import p000a.p008d.p009a.C0036b;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.fragment.app.j */
public class C0424j implements ComponentCallbacks, View.OnCreateContextMenuListener, C0453j, C0443B, C0609e {
    static final int ACTIVITY_CREATED = 2;
    static final int CREATED = 1;
    static final int INITIALIZING = 0;
    static final int RESUMED = 4;
    static final int STARTED = 3;
    static final Object USE_DEFAULT_TRANSITION = new Object();
    boolean mAdded;
    C0419g mAnimationInfo;
    Bundle mArguments;
    int mBackStackNesting;
    private boolean mCalled;
    C0389H mChildFragmentManager;
    ViewGroup mContainer;
    int mContainerId;
    private int mContentLayoutId;
    boolean mDeferStart;
    boolean mDetached;
    int mFragmentId;
    C0389H mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mHiddenChanged;
    C0429o mHost;
    boolean mInLayout;
    View mInnerView;
    boolean mIsCreated;
    boolean mIsNewlyAdded;
    LayoutInflater mLayoutInflater;
    C0455l mLifecycleRegistry;
    Lifecycle$State mMaxState;
    boolean mMenuVisible;
    C0424j mParentFragment;
    boolean mPerformedCreateView;
    float mPostponedAlpha;
    boolean mRemoving;
    boolean mRestored;
    boolean mRetainInstance;
    boolean mRetainInstanceChangedWhileDetached;
    Bundle mSavedFragmentState;
    C0608d mSavedStateRegistryController;
    Boolean mSavedUserVisibleHint;
    SparseArray mSavedViewState;
    int mState;
    String mTag;
    C0424j mTarget;
    int mTargetRequestCode;
    String mTargetWho;
    boolean mUserVisibleHint;
    View mView;
    C0418fa mViewLifecycleOwner;
    C0462s mViewLifecycleOwnerLiveData;
    String mWho;

    public C0424j() {
        this.mState = 0;
        this.mWho = UUID.randomUUID().toString();
        this.mTargetWho = null;
        this.mMenuVisible = true;
        this.mUserVisibleHint = true;
        this.mMaxState = Lifecycle$State.RESUMED;
        this.mViewLifecycleOwnerLiveData = new C0462s();
        initLifecycle();
    }

    private C0419g ensureAnimationInfo() {
        if (this.mAnimationInfo == null) {
            this.mAnimationInfo = new C0419g();
        }
        return this.mAnimationInfo;
    }

    private void initLifecycle() {
        this.mLifecycleRegistry = new C0455l(this);
        this.mSavedStateRegistryController = C0608d.m958b(this);
        int i = Build.VERSION.SDK_INT;
        this.mLifecycleRegistry.mo4460a((C0452i) new Fragment$1(this));
    }

    @Deprecated
    public static C0424j instantiate(Context context, String str) {
        return instantiate(context, str, (Bundle) null);
    }

    /* access modifiers changed from: package-private */
    public void callStartTransitionListener() {
        C0419g gVar = this.mAnimationInfo;
        Object obj = null;
        if (gVar != null) {
            gVar.mEnterTransitionPostponed = false;
            Object obj2 = gVar.mStartEnterTransitionListener;
            gVar.mStartEnterTransitionListener = null;
            obj = obj2;
        }
        if (obj != null) {
            ((C0388G) obj).onStartEnterTransition();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.mFragmentId));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.mContainerId));
        printWriter.print(" mTag=");
        printWriter.println(this.mTag);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.mState);
        printWriter.print(" mWho=");
        printWriter.print(this.mWho);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.mBackStackNesting);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.mAdded);
        printWriter.print(" mRemoving=");
        printWriter.print(this.mRemoving);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.mFromLayout);
        printWriter.print(" mInLayout=");
        printWriter.println(this.mInLayout);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.mHidden);
        printWriter.print(" mDetached=");
        printWriter.print(this.mDetached);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.mMenuVisible);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.mHasMenu);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.mRetainInstance);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.mUserVisibleHint);
        if (this.mFragmentManager != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.mFragmentManager);
        }
        if (this.mHost != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.mHost);
        }
        if (this.mParentFragment != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.mParentFragment);
        }
        if (this.mArguments != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.mArguments);
        }
        if (this.mSavedFragmentState != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.mSavedFragmentState);
        }
        if (this.mSavedViewState != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.mSavedViewState);
        }
        C0424j targetFragment = getTargetFragment();
        if (targetFragment != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(targetFragment);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.mTargetRequestCode);
        }
        if (getNextAnim() != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(getNextAnim());
        }
        if (this.mContainer != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.mContainer);
        }
        if (this.mView != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.mView);
        }
        if (this.mInnerView != null) {
            printWriter.print(str);
            printWriter.print("mInnerView=");
            printWriter.println(this.mView);
        }
        if (getAnimatingAway() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(getAnimatingAway());
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(getStateAfterAnimating());
        }
        if (getContext() != null) {
            C0036b.m33h(this).dump(str, fileDescriptor, printWriter, strArr);
        }
        if (this.mChildFragmentManager != null) {
            printWriter.print(str);
            printWriter.println("Child " + this.mChildFragmentManager + ":");
            this.mChildFragmentManager.dump(C0632a.m1025k(str, "  "), fileDescriptor, printWriter, strArr);
        }
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    /* access modifiers changed from: package-private */
    public C0424j findFragmentByWho(String str) {
        if (str.equals(this.mWho)) {
            return this;
        }
        C0389H h = this.mChildFragmentManager;
        if (h != null) {
            return h.findFragmentByWho(str);
        }
        return null;
    }

    public final FragmentActivity getActivity() {
        C0429o oVar = this.mHost;
        if (oVar == null) {
            return null;
        }
        return (FragmentActivity) oVar.getActivity();
    }

    public boolean getAllowEnterTransitionOverlap() {
        Boolean bool;
        C0419g gVar = this.mAnimationInfo;
        if (gVar == null || (bool = gVar.f415zo) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public boolean getAllowReturnTransitionOverlap() {
        Boolean bool;
        C0419g gVar = this.mAnimationInfo;
        if (gVar == null || (bool = gVar.f414yo) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public View getAnimatingAway() {
        C0419g gVar = this.mAnimationInfo;
        if (gVar == null) {
            return null;
        }
        return gVar.mAnimatingAway;
    }

    /* access modifiers changed from: package-private */
    public Animator getAnimator() {
        C0419g gVar = this.mAnimationInfo;
        if (gVar == null) {
            return null;
        }
        return gVar.mAnimator;
    }

    public final Bundle getArguments() {
        return this.mArguments;
    }

    public final C0433s getChildFragmentManager() {
        if (this.mChildFragmentManager == null) {
            instantiateChildFragmentManager();
            int i = this.mState;
            if (i >= 4) {
                this.mChildFragmentManager.dispatchResume();
            } else if (i >= 3) {
                this.mChildFragmentManager.dispatchStart();
            } else if (i >= 2) {
                this.mChildFragmentManager.dispatchActivityCreated();
            } else if (i >= 1) {
                this.mChildFragmentManager.dispatchCreate();
            }
        }
        return this.mChildFragmentManager;
    }

    public Context getContext() {
        C0429o oVar = this.mHost;
        if (oVar == null) {
            return null;
        }
        return oVar.getContext();
    }

    public Object getEnterTransition() {
        C0419g gVar = this.mAnimationInfo;
        if (gVar == null) {
            return null;
        }
        return gVar.f408ro;
    }

    /* access modifiers changed from: package-private */
    public SharedElementCallback getEnterTransitionCallback() {
        C0419g gVar = this.mAnimationInfo;
        if (gVar == null) {
            return null;
        }
        return gVar.mEnterTransitionCallback;
    }

    public Object getExitTransition() {
        C0419g gVar = this.mAnimationInfo;
        if (gVar == null) {
            return null;
        }
        return gVar.f410uo;
    }

    /* access modifiers changed from: package-private */
    public SharedElementCallback getExitTransitionCallback() {
        C0419g gVar = this.mAnimationInfo;
        if (gVar == null) {
            return null;
        }
        return gVar.mExitTransitionCallback;
    }

    public final C0433s getFragmentManager() {
        return this.mFragmentManager;
    }

    public final Object getHost() {
        C0429o oVar = this.mHost;
        if (oVar == null) {
            return null;
        }
        return ((C0425k) oVar).this$0;
    }

    public final int getId() {
        return this.mFragmentId;
    }

    public final LayoutInflater getLayoutInflater() {
        LayoutInflater layoutInflater = this.mLayoutInflater;
        return layoutInflater == null ? performGetLayoutInflater((Bundle) null) : layoutInflater;
    }

    public C0450g getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Deprecated
    public C0036b getLoaderManager() {
        return C0036b.m33h(this);
    }

    /* access modifiers changed from: package-private */
    public int getNextAnim() {
        C0419g gVar = this.mAnimationInfo;
        if (gVar == null) {
            return 0;
        }
        return gVar.mNextAnim;
    }

    /* access modifiers changed from: package-private */
    public int getNextTransition() {
        C0419g gVar = this.mAnimationInfo;
        if (gVar == null) {
            return 0;
        }
        return gVar.mNextTransition;
    }

    /* access modifiers changed from: package-private */
    public int getNextTransitionStyle() {
        C0419g gVar = this.mAnimationInfo;
        if (gVar == null) {
            return 0;
        }
        return gVar.mNextTransitionStyle;
    }

    public final C0424j getParentFragment() {
        return this.mParentFragment;
    }

    public Object getReenterTransition() {
        C0419g gVar = this.mAnimationInfo;
        if (gVar == null) {
            return null;
        }
        Object obj = gVar.f411vo;
        return obj == USE_DEFAULT_TRANSITION ? getExitTransition() : obj;
    }

    public final Resources getResources() {
        return requireContext().getResources();
    }

    public final boolean getRetainInstance() {
        return this.mRetainInstance;
    }

    public Object getReturnTransition() {
        C0419g gVar = this.mAnimationInfo;
        if (gVar == null) {
            return null;
        }
        Object obj = gVar.f409so;
        return obj == USE_DEFAULT_TRANSITION ? getEnterTransition() : obj;
    }

    public final C0607c getSavedStateRegistry() {
        return this.mSavedStateRegistryController.getSavedStateRegistry();
    }

    public Object getSharedElementEnterTransition() {
        C0419g gVar = this.mAnimationInfo;
        if (gVar == null) {
            return null;
        }
        return gVar.f412wo;
    }

    public Object getSharedElementReturnTransition() {
        C0419g gVar = this.mAnimationInfo;
        if (gVar == null) {
            return null;
        }
        Object obj = gVar.f413xo;
        return obj == USE_DEFAULT_TRANSITION ? getSharedElementEnterTransition() : obj;
    }

    /* access modifiers changed from: package-private */
    public int getStateAfterAnimating() {
        C0419g gVar = this.mAnimationInfo;
        if (gVar == null) {
            return 0;
        }
        return gVar.mStateAfterAnimating;
    }

    public final String getString(int i) {
        return getResources().getString(i);
    }

    public final String getTag() {
        return this.mTag;
    }

    public final C0424j getTargetFragment() {
        String str;
        C0424j jVar = this.mTarget;
        if (jVar != null) {
            return jVar;
        }
        C0389H h = this.mFragmentManager;
        if (h == null || (str = this.mTargetWho) == null) {
            return null;
        }
        return (C0424j) h.mActive.get(str);
    }

    public final int getTargetRequestCode() {
        return this.mTargetRequestCode;
    }

    public final CharSequence getText(int i) {
        return getResources().getText(i);
    }

    @Deprecated
    public boolean getUserVisibleHint() {
        return this.mUserVisibleHint;
    }

    public View getView() {
        return this.mView;
    }

    public C0453j getViewLifecycleOwner() {
        C0418fa faVar = this.mViewLifecycleOwner;
        if (faVar != null) {
            return faVar;
        }
        throw new IllegalStateException("Can't access the Fragment View's LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()");
    }

    public C0460q getViewLifecycleOwnerLiveData() {
        return this.mViewLifecycleOwnerLiveData;
    }

    public C0442A getViewModelStore() {
        C0389H h = this.mFragmentManager;
        if (h != null) {
            return h.mo4117e(this);
        }
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    public final boolean hasOptionsMenu() {
        return this.mHasMenu;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    /* access modifiers changed from: package-private */
    public void initState() {
        initLifecycle();
        this.mWho = UUID.randomUUID().toString();
        this.mAdded = false;
        this.mRemoving = false;
        this.mFromLayout = false;
        this.mInLayout = false;
        this.mRestored = false;
        this.mBackStackNesting = 0;
        this.mFragmentManager = null;
        this.mChildFragmentManager = null;
        this.mHost = null;
        this.mFragmentId = 0;
        this.mContainerId = 0;
        this.mTag = null;
        this.mHidden = false;
        this.mDetached = false;
    }

    /* access modifiers changed from: package-private */
    public void instantiateChildFragmentManager() {
        if (this.mHost != null) {
            this.mChildFragmentManager = new C0389H();
            this.mChildFragmentManager.mo4085a(this.mHost, (C0426l) new C0417f(this), this);
            return;
        }
        throw new IllegalStateException(C0632a.m1015a("Fragment ", (Object) this, " has not been attached yet."));
    }

    public final boolean isAdded() {
        return this.mHost != null && this.mAdded;
    }

    public final boolean isDetached() {
        return this.mDetached;
    }

    public final boolean isHidden() {
        return this.mHidden;
    }

    /* access modifiers changed from: package-private */
    public boolean isHideReplaced() {
        C0419g gVar = this.mAnimationInfo;
        if (gVar == null) {
            return false;
        }
        return gVar.mIsHideReplaced;
    }

    /* access modifiers changed from: package-private */
    public final boolean isInBackStack() {
        return this.mBackStackNesting > 0;
    }

    public final boolean isInLayout() {
        return this.mInLayout;
    }

    public final boolean isMenuVisible() {
        return this.mMenuVisible;
    }

    /* access modifiers changed from: package-private */
    public boolean isPostponed() {
        C0419g gVar = this.mAnimationInfo;
        if (gVar == null) {
            return false;
        }
        return gVar.mEnterTransitionPostponed;
    }

    public final boolean isRemoving() {
        return this.mRemoving;
    }

    public final boolean isResumed() {
        return this.mState >= 4;
    }

    public final boolean isStateSaved() {
        C0389H h = this.mFragmentManager;
        if (h == null) {
            return false;
        }
        return h.isStateSaved();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
        r0 = r1.mView;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isVisible() {
        /*
            r1 = this;
            boolean r0 = r1.isAdded()
            if (r0 == 0) goto L_0x0020
            boolean r0 = r1.isHidden()
            if (r0 != 0) goto L_0x0020
            android.view.View r0 = r1.mView
            if (r0 == 0) goto L_0x0020
            android.os.IBinder r0 = r0.getWindowToken()
            if (r0 == 0) goto L_0x0020
            android.view.View r1 = r1.mView
            int r1 = r1.getVisibility()
            if (r1 != 0) goto L_0x0020
            r1 = 1
            goto L_0x0021
        L_0x0020:
            r1 = 0
        L_0x0021:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0424j.isVisible():boolean");
    }

    /* access modifiers changed from: package-private */
    public void noteStateNotSaved() {
        C0389H h = this.mChildFragmentManager;
        if (h != null) {
            h.noteStateNotSaved();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        this.mCalled = true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onAttach(Context context) {
        this.mCalled = true;
        C0429o oVar = this.mHost;
        Activity activity = oVar == null ? null : oVar.getActivity();
        if (activity != null) {
            this.mCalled = false;
            onAttach(activity);
        }
    }

    public void onAttachFragment(C0424j jVar) {
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.mCalled = true;
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        return false;
    }

    public void onCreate(Bundle bundle) {
        boolean z = true;
        this.mCalled = true;
        restoreChildFragmentState(bundle);
        C0389H h = this.mChildFragmentManager;
        if (h != null) {
            if (h.mCurState < 1) {
                z = false;
            }
            if (!z) {
                this.mChildFragmentManager.dispatchCreate();
            }
        }
    }

    public Animation onCreateAnimation(int i, boolean z, int i2) {
        return null;
    }

    public Animator onCreateAnimator(int i, boolean z, int i2) {
        return null;
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        requireActivity().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i = this.mContentLayoutId;
        if (i != 0) {
            return layoutInflater.inflate(i, viewGroup, false);
        }
        return null;
    }

    public void onDestroy() {
        this.mCalled = true;
    }

    public void onDestroyOptionsMenu() {
    }

    public void onDestroyView() {
        this.mCalled = true;
    }

    public void onDetach() {
        this.mCalled = true;
    }

    public LayoutInflater onGetLayoutInflater(Bundle bundle) {
        return getLayoutInflater(bundle);
    }

    public void onHiddenChanged(boolean z) {
    }

    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
        C0429o oVar = this.mHost;
        Activity activity = oVar == null ? null : oVar.getActivity();
        if (activity != null) {
            this.mCalled = false;
            onInflate(activity, attributeSet, bundle);
        }
    }

    public void onLowMemory() {
        this.mCalled = true;
    }

    public void onMultiWindowModeChanged(boolean z) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    public void onOptionsMenuClosed(Menu menu) {
    }

    public void onPause() {
        this.mCalled = true;
    }

    public void onPictureInPictureModeChanged(boolean z) {
    }

    public void onPrepareOptionsMenu(Menu menu) {
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    public void onResume() {
        this.mCalled = true;
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public void onStart() {
        this.mCalled = true;
    }

    public void onStop() {
        this.mCalled = true;
    }

    public void onViewCreated(View view, Bundle bundle) {
    }

    public void onViewStateRestored(Bundle bundle) {
        this.mCalled = true;
    }

    /* access modifiers changed from: package-private */
    public C0433s peekChildFragmentManager() {
        return this.mChildFragmentManager;
    }

    /* access modifiers changed from: package-private */
    public void performActivityCreated(Bundle bundle) {
        C0389H h = this.mChildFragmentManager;
        if (h != null) {
            h.noteStateNotSaved();
        }
        this.mState = 2;
        this.mCalled = false;
        onActivityCreated(bundle);
        if (this.mCalled) {
            C0389H h2 = this.mChildFragmentManager;
            if (h2 != null) {
                h2.dispatchActivityCreated();
                return;
            }
            return;
        }
        throw new SuperNotCalledException(C0632a.m1015a("Fragment ", (Object) this, " did not call through to super.onActivityCreated()"));
    }

    /* access modifiers changed from: package-private */
    public void performAttach() {
        this.mCalled = false;
        onAttach(this.mHost.getContext());
        if (!this.mCalled) {
            throw new SuperNotCalledException(C0632a.m1015a("Fragment ", (Object) this, " did not call through to super.onAttach()"));
        }
    }

    /* access modifiers changed from: package-private */
    public void performConfigurationChanged(Configuration configuration) {
        onConfigurationChanged(configuration);
        C0389H h = this.mChildFragmentManager;
        if (h != null) {
            h.dispatchConfigurationChanged(configuration);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean performContextItemSelected(MenuItem menuItem) {
        if (this.mHidden) {
            return false;
        }
        if (onContextItemSelected(menuItem)) {
            return true;
        }
        C0389H h = this.mChildFragmentManager;
        if (h == null || !h.dispatchContextItemSelected(menuItem)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void performCreate(Bundle bundle) {
        C0389H h = this.mChildFragmentManager;
        if (h != null) {
            h.noteStateNotSaved();
        }
        this.mState = 1;
        this.mCalled = false;
        this.mSavedStateRegistryController.mo5286g(bundle);
        onCreate(bundle);
        this.mIsCreated = true;
        if (this.mCalled) {
            this.mLifecycleRegistry.mo4464a(Lifecycle$Event.ON_CREATE);
            return;
        }
        throw new SuperNotCalledException(C0632a.m1015a("Fragment ", (Object) this, " did not call through to super.onCreate()"));
    }

    /* access modifiers changed from: package-private */
    public boolean performCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        boolean z = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onCreateOptionsMenu(menu, menuInflater);
            z = true;
        }
        C0389H h = this.mChildFragmentManager;
        return h != null ? z | h.dispatchCreateOptionsMenu(menu, menuInflater) : z;
    }

    /* access modifiers changed from: package-private */
    public void performCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        C0389H h = this.mChildFragmentManager;
        if (h != null) {
            h.noteStateNotSaved();
        }
        this.mPerformedCreateView = true;
        this.mViewLifecycleOwner = new C0418fa();
        this.mView = onCreateView(layoutInflater, viewGroup, bundle);
        if (this.mView != null) {
            this.mViewLifecycleOwner.initialize();
            this.mViewLifecycleOwnerLiveData.setValue(this.mViewLifecycleOwner);
        } else if (!this.mViewLifecycleOwner.isInitialized()) {
            this.mViewLifecycleOwner = null;
        } else {
            throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
        }
    }

    /* access modifiers changed from: package-private */
    public void performDestroy() {
        this.mLifecycleRegistry.mo4464a(Lifecycle$Event.ON_DESTROY);
        C0389H h = this.mChildFragmentManager;
        if (h != null) {
            h.dispatchDestroy();
        }
        this.mState = 0;
        this.mCalled = false;
        this.mIsCreated = false;
        onDestroy();
        if (this.mCalled) {
            this.mChildFragmentManager = null;
            return;
        }
        throw new SuperNotCalledException(C0632a.m1015a("Fragment ", (Object) this, " did not call through to super.onDestroy()"));
    }

    /* access modifiers changed from: package-private */
    public void performDestroyView() {
        if (this.mView != null) {
            this.mViewLifecycleOwner.mo4238a(Lifecycle$Event.ON_DESTROY);
        }
        C0389H h = this.mChildFragmentManager;
        if (h != null) {
            h.dispatchDestroyView();
        }
        this.mState = 1;
        this.mCalled = false;
        onDestroyView();
        if (this.mCalled) {
            C0036b.m33h(this).mo223Cc();
            this.mPerformedCreateView = false;
            return;
        }
        throw new SuperNotCalledException(C0632a.m1015a("Fragment ", (Object) this, " did not call through to super.onDestroyView()"));
    }

    /* access modifiers changed from: package-private */
    public void performDetach() {
        this.mCalled = false;
        onDetach();
        this.mLayoutInflater = null;
        if (this.mCalled) {
            C0389H h = this.mChildFragmentManager;
            if (h != null) {
                h.dispatchDestroy();
                this.mChildFragmentManager = null;
                return;
            }
            return;
        }
        throw new SuperNotCalledException(C0632a.m1015a("Fragment ", (Object) this, " did not call through to super.onDetach()"));
    }

    /* access modifiers changed from: package-private */
    public LayoutInflater performGetLayoutInflater(Bundle bundle) {
        this.mLayoutInflater = onGetLayoutInflater(bundle);
        return this.mLayoutInflater;
    }

    /* access modifiers changed from: package-private */
    public void performLowMemory() {
        onLowMemory();
        C0389H h = this.mChildFragmentManager;
        if (h != null) {
            h.dispatchLowMemory();
        }
    }

    /* access modifiers changed from: package-private */
    public void performMultiWindowModeChanged(boolean z) {
        onMultiWindowModeChanged(z);
        C0389H h = this.mChildFragmentManager;
        if (h != null) {
            h.dispatchMultiWindowModeChanged(z);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean performOptionsItemSelected(MenuItem menuItem) {
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible && onOptionsItemSelected(menuItem)) {
            return true;
        }
        C0389H h = this.mChildFragmentManager;
        if (h == null || !h.dispatchOptionsItemSelected(menuItem)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void performOptionsMenuClosed(Menu menu) {
        if (!this.mHidden) {
            if (this.mHasMenu && this.mMenuVisible) {
                onOptionsMenuClosed(menu);
            }
            C0389H h = this.mChildFragmentManager;
            if (h != null) {
                h.dispatchOptionsMenuClosed(menu);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void performPause() {
        if (this.mView != null) {
            this.mViewLifecycleOwner.mo4238a(Lifecycle$Event.ON_PAUSE);
        }
        this.mLifecycleRegistry.mo4464a(Lifecycle$Event.ON_PAUSE);
        C0389H h = this.mChildFragmentManager;
        if (h != null) {
            h.dispatchPause();
        }
        this.mState = 3;
        this.mCalled = false;
        onPause();
        if (!this.mCalled) {
            throw new SuperNotCalledException(C0632a.m1015a("Fragment ", (Object) this, " did not call through to super.onPause()"));
        }
    }

    /* access modifiers changed from: package-private */
    public void performPictureInPictureModeChanged(boolean z) {
        onPictureInPictureModeChanged(z);
        C0389H h = this.mChildFragmentManager;
        if (h != null) {
            h.dispatchPictureInPictureModeChanged(z);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean performPrepareOptionsMenu(Menu menu) {
        boolean z = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onPrepareOptionsMenu(menu);
            z = true;
        }
        C0389H h = this.mChildFragmentManager;
        return h != null ? z | h.dispatchPrepareOptionsMenu(menu) : z;
    }

    /* access modifiers changed from: package-private */
    public void performResume() {
        C0389H h = this.mChildFragmentManager;
        if (h != null) {
            h.noteStateNotSaved();
            this.mChildFragmentManager.execPendingActions();
        }
        this.mState = 4;
        this.mCalled = false;
        onResume();
        if (this.mCalled) {
            C0389H h2 = this.mChildFragmentManager;
            if (h2 != null) {
                h2.dispatchResume();
                this.mChildFragmentManager.execPendingActions();
            }
            this.mLifecycleRegistry.mo4464a(Lifecycle$Event.ON_RESUME);
            if (this.mView != null) {
                this.mViewLifecycleOwner.mo4238a(Lifecycle$Event.ON_RESUME);
                return;
            }
            return;
        }
        throw new SuperNotCalledException(C0632a.m1015a("Fragment ", (Object) this, " did not call through to super.onResume()"));
    }

    /* access modifiers changed from: package-private */
    public void performSaveInstanceState(Bundle bundle) {
        Parcelable saveAllState;
        onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.mo5285f(bundle);
        C0389H h = this.mChildFragmentManager;
        if (h != null && (saveAllState = h.saveAllState()) != null) {
            bundle.putParcelable("android:support:fragments", saveAllState);
        }
    }

    /* access modifiers changed from: package-private */
    public void performStart() {
        C0389H h = this.mChildFragmentManager;
        if (h != null) {
            h.noteStateNotSaved();
            this.mChildFragmentManager.execPendingActions();
        }
        this.mState = 3;
        this.mCalled = false;
        onStart();
        if (this.mCalled) {
            C0389H h2 = this.mChildFragmentManager;
            if (h2 != null) {
                h2.dispatchStart();
            }
            this.mLifecycleRegistry.mo4464a(Lifecycle$Event.ON_START);
            if (this.mView != null) {
                this.mViewLifecycleOwner.mo4238a(Lifecycle$Event.ON_START);
                return;
            }
            return;
        }
        throw new SuperNotCalledException(C0632a.m1015a("Fragment ", (Object) this, " did not call through to super.onStart()"));
    }

    /* access modifiers changed from: package-private */
    public void performStop() {
        if (this.mView != null) {
            this.mViewLifecycleOwner.mo4238a(Lifecycle$Event.ON_STOP);
        }
        this.mLifecycleRegistry.mo4464a(Lifecycle$Event.ON_STOP);
        C0389H h = this.mChildFragmentManager;
        if (h != null) {
            h.dispatchStop();
        }
        this.mState = 2;
        this.mCalled = false;
        onStop();
        if (!this.mCalled) {
            throw new SuperNotCalledException(C0632a.m1015a("Fragment ", (Object) this, " did not call through to super.onStop()"));
        }
    }

    public void postponeEnterTransition() {
        ensureAnimationInfo().mEnterTransitionPostponed = true;
    }

    public void registerForContextMenu(View view) {
        view.setOnCreateContextMenuListener(this);
    }

    public final void requestPermissions(String[] strArr, int i) {
        C0429o oVar = this.mHost;
        if (oVar != null) {
            ((C0425k) oVar).this$0.requestPermissionsFromFragment(this, strArr, i);
            return;
        }
        throw new IllegalStateException(C0632a.m1015a("Fragment ", (Object) this, " not attached to Activity"));
    }

    public final FragmentActivity requireActivity() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            return activity;
        }
        throw new IllegalStateException(C0632a.m1015a("Fragment ", (Object) this, " not attached to an activity."));
    }

    public final Bundle requireArguments() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            return arguments;
        }
        throw new IllegalStateException(C0632a.m1015a("Fragment ", (Object) this, " does not have any arguments."));
    }

    public final Context requireContext() {
        Context context = getContext();
        if (context != null) {
            return context;
        }
        throw new IllegalStateException(C0632a.m1015a("Fragment ", (Object) this, " not attached to a context."));
    }

    public final C0433s requireFragmentManager() {
        C0433s fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            return fragmentManager;
        }
        throw new IllegalStateException(C0632a.m1015a("Fragment ", (Object) this, " not associated with a fragment manager."));
    }

    public final Object requireHost() {
        Object host = getHost();
        if (host != null) {
            return host;
        }
        throw new IllegalStateException(C0632a.m1015a("Fragment ", (Object) this, " not attached to a host."));
    }

    public final C0424j requireParentFragment() {
        C0424j parentFragment = getParentFragment();
        if (parentFragment != null) {
            return parentFragment;
        }
        if (getContext() == null) {
            throw new IllegalStateException(C0632a.m1015a("Fragment ", (Object) this, " is not attached to any Fragment or host"));
        }
        throw new IllegalStateException("Fragment " + this + " is not a child Fragment, it is directly attached to " + getContext());
    }

    public final View requireView() {
        View view = getView();
        if (view != null) {
            return view;
        }
        throw new IllegalStateException(C0632a.m1015a("Fragment ", (Object) this, " did not return a View from onCreateView() or this was called before onCreateView()."));
    }

    /* access modifiers changed from: package-private */
    public void restoreChildFragmentState(Bundle bundle) {
        Parcelable parcelable;
        if (bundle != null && (parcelable = bundle.getParcelable("android:support:fragments")) != null) {
            if (this.mChildFragmentManager == null) {
                instantiateChildFragmentManager();
            }
            this.mChildFragmentManager.mo4076a(parcelable);
            this.mChildFragmentManager.dispatchCreate();
        }
    }

    /* access modifiers changed from: package-private */
    public final void restoreViewState(Bundle bundle) {
        SparseArray sparseArray = this.mSavedViewState;
        if (sparseArray != null) {
            this.mInnerView.restoreHierarchyState(sparseArray);
            this.mSavedViewState = null;
        }
        this.mCalled = false;
        onViewStateRestored(bundle);
        if (!this.mCalled) {
            throw new SuperNotCalledException(C0632a.m1015a("Fragment ", (Object) this, " did not call through to super.onViewStateRestored()"));
        } else if (this.mView != null) {
            this.mViewLifecycleOwner.mo4238a(Lifecycle$Event.ON_CREATE);
        }
    }

    public void setAllowEnterTransitionOverlap(boolean z) {
        ensureAnimationInfo().f415zo = Boolean.valueOf(z);
    }

    public void setAllowReturnTransitionOverlap(boolean z) {
        ensureAnimationInfo().f414yo = Boolean.valueOf(z);
    }

    /* access modifiers changed from: package-private */
    public void setAnimatingAway(View view) {
        ensureAnimationInfo().mAnimatingAway = view;
    }

    /* access modifiers changed from: package-private */
    public void setAnimator(Animator animator) {
        ensureAnimationInfo().mAnimator = animator;
    }

    public void setArguments(Bundle bundle) {
        if (this.mFragmentManager == null || !isStateSaved()) {
            this.mArguments = bundle;
            return;
        }
        throw new IllegalStateException("Fragment already added and state has been saved");
    }

    public void setEnterSharedElementCallback(SharedElementCallback sharedElementCallback) {
        ensureAnimationInfo().mEnterTransitionCallback = sharedElementCallback;
    }

    public void setEnterTransition(Object obj) {
        ensureAnimationInfo().f408ro = obj;
    }

    public void setExitSharedElementCallback(SharedElementCallback sharedElementCallback) {
        ensureAnimationInfo().mExitTransitionCallback = sharedElementCallback;
    }

    public void setExitTransition(Object obj) {
        ensureAnimationInfo().f410uo = obj;
    }

    public void setHasOptionsMenu(boolean z) {
        if (this.mHasMenu != z) {
            this.mHasMenu = z;
            if (isAdded() && !isHidden()) {
                ((C0425k) this.mHost).this$0.supportInvalidateOptionsMenu();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setHideReplaced(boolean z) {
        ensureAnimationInfo().mIsHideReplaced = z;
    }

    public void setInitialSavedState(Fragment$SavedState fragment$SavedState) {
        Bundle bundle;
        if (this.mFragmentManager == null) {
            if (fragment$SavedState == null || (bundle = fragment$SavedState.mState) == null) {
                bundle = null;
            }
            this.mSavedFragmentState = bundle;
            return;
        }
        throw new IllegalStateException("Fragment already added");
    }

    public void setMenuVisibility(boolean z) {
        if (this.mMenuVisible != z) {
            this.mMenuVisible = z;
            if (this.mHasMenu && isAdded() && !isHidden()) {
                ((C0425k) this.mHost).this$0.supportInvalidateOptionsMenu();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setNextAnim(int i) {
        if (this.mAnimationInfo != null || i != 0) {
            ensureAnimationInfo().mNextAnim = i;
        }
    }

    /* access modifiers changed from: package-private */
    public void setNextTransition(int i, int i2) {
        if (this.mAnimationInfo != null || i != 0 || i2 != 0) {
            ensureAnimationInfo();
            C0419g gVar = this.mAnimationInfo;
            gVar.mNextTransition = i;
            gVar.mNextTransitionStyle = i2;
        }
    }

    /* access modifiers changed from: package-private */
    public void setOnStartEnterTransitionListener(C0421h hVar) {
        ensureAnimationInfo();
        C0421h hVar2 = this.mAnimationInfo.mStartEnterTransitionListener;
        if (hVar != hVar2) {
            if (hVar == null || hVar2 == null) {
                C0419g gVar = this.mAnimationInfo;
                if (gVar.mEnterTransitionPostponed) {
                    gVar.mStartEnterTransitionListener = hVar;
                }
                if (hVar != null) {
                    ((C0388G) hVar).startListening();
                    return;
                }
                return;
            }
            throw new IllegalStateException(C0632a.m1014a("Trying to set a replacement startPostponedEnterTransition on ", this));
        }
    }

    public void setReenterTransition(Object obj) {
        ensureAnimationInfo().f411vo = obj;
    }

    public void setRetainInstance(boolean z) {
        this.mRetainInstance = z;
        C0389H h = this.mFragmentManager;
        if (h == null) {
            this.mRetainInstanceChangedWhileDetached = true;
        } else if (z) {
            h.mo4087b(this);
        } else {
            h.mo4138k(this);
        }
    }

    public void setReturnTransition(Object obj) {
        ensureAnimationInfo().f409so = obj;
    }

    public void setSharedElementEnterTransition(Object obj) {
        ensureAnimationInfo().f412wo = obj;
    }

    public void setSharedElementReturnTransition(Object obj) {
        ensureAnimationInfo().f413xo = obj;
    }

    /* access modifiers changed from: package-private */
    public void setStateAfterAnimating(int i) {
        ensureAnimationInfo().mStateAfterAnimating = i;
    }

    public void setTargetFragment(C0424j jVar, int i) {
        C0433s fragmentManager = getFragmentManager();
        C0433s fragmentManager2 = jVar != null ? jVar.getFragmentManager() : null;
        if (fragmentManager == null || fragmentManager2 == null || fragmentManager == fragmentManager2) {
            C0424j jVar2 = jVar;
            while (jVar2 != null) {
                if (jVar2 != this) {
                    jVar2 = jVar2.getTargetFragment();
                } else {
                    throw new IllegalArgumentException("Setting " + jVar + " as the target of " + this + " would create a target cycle");
                }
            }
            if (jVar == null) {
                this.mTargetWho = null;
                this.mTarget = null;
            } else if (this.mFragmentManager == null || jVar.mFragmentManager == null) {
                this.mTargetWho = null;
                this.mTarget = jVar;
            } else {
                this.mTargetWho = jVar.mWho;
                this.mTarget = null;
            }
            this.mTargetRequestCode = i;
            return;
        }
        throw new IllegalArgumentException(C0632a.m1015a("Fragment ", (Object) jVar, " must share the same FragmentManager to be set as a target fragment"));
    }

    @Deprecated
    public void setUserVisibleHint(boolean z) {
        if (!this.mUserVisibleHint && z && this.mState < 3 && this.mFragmentManager != null && isAdded() && this.mIsCreated) {
            this.mFragmentManager.mo4134i(this);
        }
        this.mUserVisibleHint = z;
        this.mDeferStart = this.mState < 3 && !z;
        if (this.mSavedFragmentState != null) {
            this.mSavedUserVisibleHint = Boolean.valueOf(z);
        }
    }

    public boolean shouldShowRequestPermissionRationale(String str) {
        C0429o oVar = this.mHost;
        if (oVar != null) {
            return ActivityCompat.shouldShowRequestPermissionRationale(((C0425k) oVar).this$0, str);
        }
        return false;
    }

    public void startActivity(@SuppressLint({"UnknownNullness"}) Intent intent) {
        startActivity(intent, (Bundle) null);
    }

    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i) {
        startActivityForResult(intent, i, (Bundle) null);
    }

    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        C0429o oVar = this.mHost;
        if (oVar != null) {
            ((C0425k) oVar).this$0.startIntentSenderFromFragment(this, intentSender, i, intent, i2, i3, i4, bundle);
            return;
        }
        throw new IllegalStateException(C0632a.m1015a("Fragment ", (Object) this, " not attached to Activity"));
    }

    public void startPostponedEnterTransition() {
        C0389H h = this.mFragmentManager;
        if (h == null || h.mHost == null) {
            ensureAnimationInfo().mEnterTransitionPostponed = false;
        } else if (Looper.myLooper() != this.mFragmentManager.mHost.getHandler().getLooper()) {
            this.mFragmentManager.mHost.getHandler().postAtFrontOfQueue(new C0415e(this));
        } else {
            callStartTransitionListener();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        DebugUtils.buildShortClassTag(this, sb);
        sb.append(" (");
        sb.append(this.mWho);
        sb.append(")");
        if (this.mFragmentId != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.mFragmentId));
        }
        if (this.mTag != null) {
            sb.append(" ");
            sb.append(this.mTag);
        }
        sb.append('}');
        return sb.toString();
    }

    public void unregisterForContextMenu(View view) {
        view.setOnCreateContextMenuListener((View.OnCreateContextMenuListener) null);
    }

    @Deprecated
    public static C0424j instantiate(Context context, String str, Bundle bundle) {
        try {
            C0424j jVar = (C0424j) C0428n.m424c(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(jVar.getClass().getClassLoader());
                jVar.setArguments(bundle);
            }
            return jVar;
        } catch (InstantiationException e) {
            throw new Fragment$InstantiationException(C0632a.m1023d("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e);
        } catch (IllegalAccessException e2) {
            throw new Fragment$InstantiationException(C0632a.m1023d("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e2);
        } catch (NoSuchMethodException e3) {
            throw new Fragment$InstantiationException(C0632a.m1023d("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e3);
        } catch (InvocationTargetException e4) {
            throw new Fragment$InstantiationException(C0632a.m1023d("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e4);
        }
    }

    public final String getString(int i, Object... objArr) {
        return getResources().getString(i, objArr);
    }

    public final void postponeEnterTransition(long j, TimeUnit timeUnit) {
        Handler handler;
        ensureAnimationInfo().mEnterTransitionPostponed = true;
        C0389H h = this.mFragmentManager;
        if (h != null) {
            handler = h.mHost.getHandler();
        } else {
            handler = new Handler(Looper.getMainLooper());
        }
        handler.postDelayed(new C0413d(this), timeUnit.toMillis(j));
    }

    public void startActivity(@SuppressLint({"UnknownNullness"}) Intent intent, Bundle bundle) {
        C0429o oVar = this.mHost;
        if (oVar != null) {
            ((C0425k) oVar).this$0.startActivityFromFragment(this, intent, -1, bundle);
            return;
        }
        throw new IllegalStateException(C0632a.m1015a("Fragment ", (Object) this, " not attached to Activity"));
    }

    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i, Bundle bundle) {
        C0429o oVar = this.mHost;
        if (oVar != null) {
            ((C0425k) oVar).this$0.startActivityFromFragment(this, intent, i, bundle);
            return;
        }
        throw new IllegalStateException(C0632a.m1015a("Fragment ", (Object) this, " not attached to Activity"));
    }

    @Deprecated
    public LayoutInflater getLayoutInflater(Bundle bundle) {
        C0429o oVar = this.mHost;
        if (oVar != null) {
            C0425k kVar = (C0425k) oVar;
            LayoutInflater cloneInContext = kVar.this$0.getLayoutInflater().cloneInContext(kVar.this$0);
            getChildFragmentManager();
            C0389H h = this.mChildFragmentManager;
            h.getLayoutInflaterFactory();
            cloneInContext.setFactory2(h);
            int i = Build.VERSION.SDK_INT;
            return cloneInContext;
        }
        throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
    }

    @Deprecated
    public void onAttach(Activity activity) {
        this.mCalled = true;
    }

    @Deprecated
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
    }

    public C0424j(int i) {
        this();
        this.mContentLayoutId = i;
    }
}
