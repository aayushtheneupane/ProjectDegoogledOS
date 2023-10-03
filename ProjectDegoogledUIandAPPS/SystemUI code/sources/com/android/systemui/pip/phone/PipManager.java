package com.android.systemui.pip.phone;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.IActivityManager;
import android.app.IActivityTaskManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ParceledListSlice;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import android.view.IPinnedStackController;
import android.view.IPinnedStackListener;
import com.android.systemui.Dependency;
import com.android.systemui.UiOffloadThread;
import com.android.systemui.pip.BasePipManager;
import com.android.systemui.pip.phone.PipManager;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.shared.system.InputConsumerController;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.android.systemui.shared.system.WindowManagerWrapper;
import java.io.PrintWriter;

public class PipManager implements BasePipManager {
    private static PipManager sPipController;
    /* access modifiers changed from: private */
    public IActivityManager mActivityManager;
    private IActivityTaskManager mActivityTaskManager;
    /* access modifiers changed from: private */
    public PipAppOpsListener mAppOpsListener;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler();
    private InputConsumerController mInputConsumerController;
    /* access modifiers changed from: private */
    public PipMediaController mMediaController;
    /* access modifiers changed from: private */
    public PipMenuActivityController mMenuController;
    private final PinnedStackListener mPinnedStackListener = new PinnedStackListener();
    TaskStackChangeListener mTaskStackListener = new TaskStackChangeListener() {
        public void onActivityPinned(String str, int i, int i2, int i3) {
            PipManager.this.mTouchHandler.onActivityPinned();
            PipManager.this.mMediaController.onActivityPinned();
            PipManager.this.mMenuController.onActivityPinned();
            PipManager.this.mAppOpsListener.onActivityPinned(str);
            ((UiOffloadThread) Dependency.get(UiOffloadThread.class)).submit($$Lambda$PipManager$1$GurLWXFKpAPDop_aRGndKBjZCWU.INSTANCE);
        }

        public void onActivityUnpinned() {
            ComponentName componentName = (ComponentName) PipUtils.getTopPinnedActivity(PipManager.this.mContext, PipManager.this.mActivityManager).first;
            PipManager.this.mMenuController.onActivityUnpinned();
            PipManager.this.mTouchHandler.onActivityUnpinned(componentName);
            PipManager.this.mAppOpsListener.onActivityUnpinned();
            ((UiOffloadThread) Dependency.get(UiOffloadThread.class)).submit(new Runnable(componentName) {
                private final /* synthetic */ ComponentName f$0;

                {
                    this.f$0 = r1;
                }

                public final void run() {
                    PipManager.C08271.lambda$onActivityUnpinned$1(this.f$0);
                }
            });
        }

        static /* synthetic */ void lambda$onActivityUnpinned$1(ComponentName componentName) {
            WindowManagerWrapper.getInstance().setPipVisibility(componentName != null);
        }

        public void onPinnedStackAnimationStarted() {
            PipManager.this.mTouchHandler.setTouchEnabled(false);
        }

        public void onPinnedStackAnimationEnded() {
            PipManager.this.mTouchHandler.setTouchEnabled(true);
            PipManager.this.mTouchHandler.onPinnedStackAnimationEnded();
            PipManager.this.mMenuController.onPinnedStackAnimationEnded();
        }

        public void onPinnedActivityRestartAttempt(boolean z) {
            PipManager.this.mTouchHandler.getMotionHelper().expandPip(z);
        }
    };
    /* access modifiers changed from: private */
    public PipTouchHandler mTouchHandler;

    private class PinnedStackListener extends IPinnedStackListener.Stub {
        private PinnedStackListener() {
        }

        public void onListenerRegistered(IPinnedStackController iPinnedStackController) {
            PipManager.this.mHandler.post(new Runnable(iPinnedStackController) {
                private final /* synthetic */ IPinnedStackController f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    PipManager.PinnedStackListener.this.lambda$onListenerRegistered$0$PipManager$PinnedStackListener(this.f$1);
                }
            });
        }

        public /* synthetic */ void lambda$onListenerRegistered$0$PipManager$PinnedStackListener(IPinnedStackController iPinnedStackController) {
            PipManager.this.mTouchHandler.setPinnedStackController(iPinnedStackController);
        }

        public void onImeVisibilityChanged(boolean z, int i) {
            PipManager.this.mHandler.post(new Runnable(z, i) {
                private final /* synthetic */ boolean f$1;
                private final /* synthetic */ int f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    PipManager.PinnedStackListener.this.lambda$onImeVisibilityChanged$1$PipManager$PinnedStackListener(this.f$1, this.f$2);
                }
            });
        }

        public /* synthetic */ void lambda$onImeVisibilityChanged$1$PipManager$PinnedStackListener(boolean z, int i) {
            PipManager.this.mTouchHandler.onImeVisibilityChanged(z, i);
        }

        public void onShelfVisibilityChanged(boolean z, int i) {
            PipManager.this.mHandler.post(new Runnable(z, i) {
                private final /* synthetic */ boolean f$1;
                private final /* synthetic */ int f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    PipManager.PinnedStackListener.this.lambda$onShelfVisibilityChanged$2$PipManager$PinnedStackListener(this.f$1, this.f$2);
                }
            });
        }

        public /* synthetic */ void lambda$onShelfVisibilityChanged$2$PipManager$PinnedStackListener(boolean z, int i) {
            PipManager.this.mTouchHandler.onShelfVisibilityChanged(z, i);
        }

        public void onMinimizedStateChanged(boolean z) {
            PipManager.this.mHandler.post(new Runnable(z) {
                private final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    PipManager.PinnedStackListener.this.lambda$onMinimizedStateChanged$3$PipManager$PinnedStackListener(this.f$1);
                }
            });
        }

        public /* synthetic */ void lambda$onMinimizedStateChanged$3$PipManager$PinnedStackListener(boolean z) {
            PipManager.this.mTouchHandler.setMinimizedState(z, true);
        }

        public void onMovementBoundsChanged(Rect rect, Rect rect2, Rect rect3, boolean z, boolean z2, int i) {
            PipManager.this.mHandler.post(new Runnable(rect, rect2, rect3, z, z2, i) {
                private final /* synthetic */ Rect f$1;
                private final /* synthetic */ Rect f$2;
                private final /* synthetic */ Rect f$3;
                private final /* synthetic */ boolean f$4;
                private final /* synthetic */ boolean f$5;
                private final /* synthetic */ int f$6;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                    this.f$4 = r5;
                    this.f$5 = r6;
                    this.f$6 = r7;
                }

                public final void run() {
                    PipManager.PinnedStackListener.this.lambda$onMovementBoundsChanged$4$PipManager$PinnedStackListener(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
                }
            });
        }

        public /* synthetic */ void lambda$onMovementBoundsChanged$4$PipManager$PinnedStackListener(Rect rect, Rect rect2, Rect rect3, boolean z, boolean z2, int i) {
            PipManager.this.mTouchHandler.onMovementBoundsChanged(rect, rect2, rect3, z, z2, i);
        }

        public void onActionsChanged(ParceledListSlice parceledListSlice) {
            PipManager.this.mHandler.post(new Runnable(parceledListSlice) {
                private final /* synthetic */ ParceledListSlice f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    PipManager.PinnedStackListener.this.lambda$onActionsChanged$5$PipManager$PinnedStackListener(this.f$1);
                }
            });
        }

        public /* synthetic */ void lambda$onActionsChanged$5$PipManager$PinnedStackListener(ParceledListSlice parceledListSlice) {
            PipManager.this.mMenuController.setAppActions(parceledListSlice);
        }
    }

    private PipManager() {
    }

    public void initialize(Context context) {
        this.mContext = context;
        this.mActivityManager = ActivityManager.getService();
        this.mActivityTaskManager = ActivityTaskManager.getService();
        try {
            WindowManagerWrapper.getInstance().addPinnedStackListener(this.mPinnedStackListener);
        } catch (RemoteException e) {
            Log.e("PipManager", "Failed to register pinned stack listener", e);
        }
        ActivityManagerWrapper.getInstance().registerTaskStackListener(this.mTaskStackListener);
        this.mInputConsumerController = InputConsumerController.getPipInputConsumer();
        this.mMediaController = new PipMediaController(context, this.mActivityManager);
        this.mMenuController = new PipMenuActivityController(context, this.mActivityManager, this.mMediaController, this.mInputConsumerController);
        this.mTouchHandler = new PipTouchHandler(context, this.mActivityManager, this.mActivityTaskManager, this.mMenuController, this.mInputConsumerController);
        this.mAppOpsListener = new PipAppOpsListener(context, this.mActivityManager, this.mTouchHandler.getMotionHelper());
        try {
            if (this.mActivityTaskManager.getStackInfo(2, 0) != null) {
                this.mInputConsumerController.registerInputConsumer();
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.mTouchHandler.onConfigurationChanged();
    }

    public void showPictureInPictureMenu() {
        this.mTouchHandler.showPictureInPictureMenu();
    }

    public static PipManager getInstance() {
        if (sPipController == null) {
            sPipController = new PipManager();
        }
        return sPipController;
    }

    public void dump(PrintWriter printWriter) {
        printWriter.println("PipManager");
        this.mInputConsumerController.dump(printWriter, "  ");
        this.mMenuController.dump(printWriter, "  ");
        this.mTouchHandler.dump(printWriter, "  ");
    }
}
