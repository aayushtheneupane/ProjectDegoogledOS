package com.android.systemui.statusbar;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.WindowManagerGlobal;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.statusbar.RegisterStatusBarResult;
import com.android.systemui.Dependency;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.fragments.FragmentHostManager;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.NavigationBarController;
import com.android.systemui.statusbar.phone.AutoHideController;
import com.android.systemui.statusbar.phone.LightBarController;
import com.android.systemui.statusbar.phone.NavigationBarFragment;
import com.android.systemui.statusbar.phone.NavigationBarView;
import com.android.systemui.statusbar.policy.BatteryController;

public class NavigationBarController implements CommandQueue.Callbacks {
    private static final String TAG = "NavigationBarController";
    private final Context mContext;
    private final DisplayManager mDisplayManager;
    private final Handler mHandler;
    @VisibleForTesting
    SparseArray<NavigationBarFragment> mNavigationBars = new SparseArray<>();

    public class SystemUiVisibility {
        public int displayId;
        public Rect dockedStackBounds;
        public int dockedStackVis;
        public Rect fullscreenStackBounds;
        public int fullscreenStackVis;
        public int mask;
        public boolean navbarColorManagedByIme;
        public int vis;

        public SystemUiVisibility() {
        }
    }

    public NavigationBarController(Context context, Handler handler) {
        this.mContext = context;
        this.mHandler = handler;
        this.mDisplayManager = (DisplayManager) this.mContext.getSystemService("display");
        CommandQueue commandQueue = (CommandQueue) SysUiServiceProvider.getComponent(this.mContext, CommandQueue.class);
        if (commandQueue != null) {
            commandQueue.addCallback((CommandQueue.Callbacks) this);
        }
    }

    public SystemUiVisibility createSystemUiVisibility() {
        return new SystemUiVisibility();
    }

    public void onDisplayRemoved(int i) {
        removeNavigationBar(i);
    }

    public void onDisplayReady(int i) {
        createNavigationBar(this.mDisplayManager.getDisplay(i), (RegisterStatusBarResult) null, (SystemUiVisibility) null);
    }

    public void createNavigationBars(boolean z, RegisterStatusBarResult registerStatusBarResult) {
        for (Display display : this.mDisplayManager.getDisplays()) {
            if (z || display.getDisplayId() != 0) {
                createNavigationBar(display, registerStatusBarResult, (SystemUiVisibility) null);
            }
        }
    }

    public void recreateNavigationBars(boolean z, RegisterStatusBarResult registerStatusBarResult, SystemUiVisibility systemUiVisibility) {
        for (Display display : this.mDisplayManager.getDisplays()) {
            if (z || display.getDisplayId() != 0) {
                createNavigationBar(display, registerStatusBarResult, systemUiVisibility);
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void createNavigationBar(Display display, RegisterStatusBarResult registerStatusBarResult, SystemUiVisibility systemUiVisibility) {
        Context context;
        if (display != null) {
            int displayId = display.getDisplayId();
            boolean z = displayId == 0;
            try {
                if (WindowManagerGlobal.getWindowManagerService().hasNavigationBar(displayId)) {
                    if (z) {
                        context = this.mContext;
                    } else {
                        context = this.mContext.createDisplayContext(display);
                    }
                    Context context2 = context;
                    NavigationBarFragment.create(context2, new FragmentHostManager.FragmentListener(z, context2, systemUiVisibility, displayId, registerStatusBarResult, display) {
                        private final /* synthetic */ boolean f$1;
                        private final /* synthetic */ Context f$2;
                        private final /* synthetic */ NavigationBarController.SystemUiVisibility f$3;
                        private final /* synthetic */ int f$4;
                        private final /* synthetic */ RegisterStatusBarResult f$5;
                        private final /* synthetic */ Display f$6;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                            this.f$3 = r4;
                            this.f$4 = r5;
                            this.f$5 = r6;
                            this.f$6 = r7;
                        }

                        public final void onFragmentViewCreated(String str, Fragment fragment) {
                            NavigationBarController.this.lambda$createNavigationBar$0$NavigationBarController(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, str, fragment);
                        }
                    });
                }
            } catch (RemoteException unused) {
                Log.w(TAG, "Cannot get WindowManager.");
            }
        }
    }

    public /* synthetic */ void lambda$createNavigationBar$0$NavigationBarController(boolean z, Context context, SystemUiVisibility systemUiVisibility, int i, RegisterStatusBarResult registerStatusBarResult, Display display, String str, Fragment fragment) {
        LightBarController lightBarController;
        AutoHideController autoHideController;
        Context context2 = context;
        SystemUiVisibility systemUiVisibility2 = systemUiVisibility;
        RegisterStatusBarResult registerStatusBarResult2 = registerStatusBarResult;
        NavigationBarFragment navigationBarFragment = (NavigationBarFragment) fragment;
        if (z) {
            lightBarController = (LightBarController) Dependency.get(LightBarController.class);
        } else {
            lightBarController = new LightBarController(context2, (DarkIconDispatcher) Dependency.get(DarkIconDispatcher.class), (BatteryController) Dependency.get(BatteryController.class));
        }
        navigationBarFragment.setLightBarController(lightBarController);
        if (z) {
            autoHideController = (AutoHideController) Dependency.get(AutoHideController.class);
        } else {
            autoHideController = new AutoHideController(context2, this.mHandler);
        }
        navigationBarFragment.setAutoHideController(autoHideController);
        navigationBarFragment.restoreSystemUiVisibilityState();
        if (systemUiVisibility2 != null) {
            navigationBarFragment.setSystemUiVisibility(systemUiVisibility2.displayId, systemUiVisibility2.vis, systemUiVisibility2.fullscreenStackVis, systemUiVisibility2.dockedStackVis, systemUiVisibility2.mask, systemUiVisibility2.fullscreenStackBounds, systemUiVisibility2.dockedStackBounds, systemUiVisibility2.navbarColorManagedByIme);
        }
        this.mNavigationBars.append(i, navigationBarFragment);
        if (registerStatusBarResult2 != null) {
            navigationBarFragment.setImeWindowStatus(display.getDisplayId(), registerStatusBarResult2.mImeToken, registerStatusBarResult2.mImeWindowVis, registerStatusBarResult2.mImeBackDisposition, registerStatusBarResult2.mShowImeSwitcher);
        }
    }

    public void removeNavigationBar(int i) {
        NavigationBarFragment navigationBarFragment = this.mNavigationBars.get(i);
        if (navigationBarFragment != null) {
            WindowManagerGlobal.getInstance().removeView(navigationBarFragment.getView().getRootView(), true);
            this.mNavigationBars.remove(i);
        }
    }

    public void checkNavBarModes(int i) {
        NavigationBarFragment navigationBarFragment = this.mNavigationBars.get(i);
        if (navigationBarFragment != null) {
            navigationBarFragment.checkNavBarModes();
        }
    }

    public void finishBarAnimations(int i) {
        NavigationBarFragment navigationBarFragment = this.mNavigationBars.get(i);
        if (navigationBarFragment != null) {
            navigationBarFragment.finishBarAnimations();
        }
    }

    public void touchAutoDim(int i) {
        NavigationBarFragment navigationBarFragment = this.mNavigationBars.get(i);
        if (navigationBarFragment != null) {
            navigationBarFragment.touchAutoDim();
        }
    }

    public void transitionTo(int i, int i2, boolean z) {
        NavigationBarFragment navigationBarFragment = this.mNavigationBars.get(i);
        if (navigationBarFragment != null) {
            navigationBarFragment.transitionTo(i2, z);
        }
    }

    public void disableAnimationsDuringHide(int i, long j) {
        NavigationBarFragment navigationBarFragment = this.mNavigationBars.get(i);
        if (navigationBarFragment != null) {
            navigationBarFragment.disableAnimationsDuringHide(j);
        }
    }

    public NavigationBarView getNavigationBarView(int i) {
        NavigationBarFragment navigationBarFragment = this.mNavigationBars.get(i);
        if (navigationBarFragment == null) {
            return null;
        }
        return (NavigationBarView) navigationBarFragment.getView();
    }

    public NavigationBarFragment getDefaultNavigationBarFragment() {
        return this.mNavigationBars.get(0);
    }
}
