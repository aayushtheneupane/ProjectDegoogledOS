package com.android.systemui.stackdivider;

import android.content.res.Configuration;
import android.os.RemoteException;
import android.util.Log;
import android.view.IDockedStackListener;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManagerGlobal;
import com.android.systemui.C1779R$layout;
import com.android.systemui.SystemUI;
import com.android.systemui.recents.Recents;
import com.android.systemui.stackdivider.Divider;
import com.android.systemui.stackdivider.DividerView;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Divider extends SystemUI implements DividerView.DividerCallbacks {
    /* access modifiers changed from: private */
    public boolean mAdjustedForIme = false;
    private final DividerState mDividerState = new DividerState();
    private DockDividerVisibilityListener mDockDividerVisibilityListener;
    /* access modifiers changed from: private */
    public ForcedResizableInfoActivityController mForcedResizableController;
    /* access modifiers changed from: private */
    public boolean mHomeStackResizable = false;
    /* access modifiers changed from: private */
    public boolean mMinimized = false;
    /* access modifiers changed from: private */
    public DividerView mView;
    /* access modifiers changed from: private */
    public boolean mVisible = false;
    private DividerWindowManager mWindowManager;

    public void start() {
        this.mWindowManager = new DividerWindowManager(this.mContext);
        update(this.mContext.getResources().getConfiguration());
        putComponent(Divider.class, this);
        this.mDockDividerVisibilityListener = new DockDividerVisibilityListener();
        try {
            WindowManagerGlobal.getWindowManagerService().registerDockedStackListener(this.mDockDividerVisibilityListener);
        } catch (Exception e) {
            Log.e("Divider", "Failed to register docked stack listener", e);
        }
        this.mForcedResizableController = new ForcedResizableInfoActivityController(this.mContext);
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        update(configuration);
    }

    public DividerView getView() {
        return this.mView;
    }

    public boolean isMinimized() {
        return this.mMinimized;
    }

    public boolean isHomeStackResizable() {
        return this.mHomeStackResizable;
    }

    private void addDivider(Configuration configuration) {
        this.mView = (DividerView) LayoutInflater.from(this.mContext).inflate(C1779R$layout.docked_stack_divider, (ViewGroup) null);
        this.mView.injectDependencies(this.mWindowManager, this.mDividerState, this);
        boolean z = false;
        this.mView.setVisibility(this.mVisible ? 0 : 4);
        this.mView.setMinimizedDockStack(this.mMinimized, this.mHomeStackResizable);
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(17105145);
        if (configuration.orientation == 2) {
            z = true;
        }
        int i = -1;
        int i2 = z ? dimensionPixelSize : -1;
        if (!z) {
            i = dimensionPixelSize;
        }
        this.mWindowManager.add(this.mView, i2, i);
    }

    private void removeDivider() {
        DividerView dividerView = this.mView;
        if (dividerView != null) {
            dividerView.onDividerRemoved();
        }
        this.mWindowManager.remove();
    }

    private void update(Configuration configuration) {
        removeDivider();
        addDivider(configuration);
        if (this.mMinimized) {
            this.mView.setMinimizedDockStack(true, this.mHomeStackResizable);
            updateTouchable();
        }
    }

    /* access modifiers changed from: private */
    public void updateVisibility(final boolean z) {
        this.mView.post(new Runnable() {
            public void run() {
                boolean access$000 = Divider.this.mVisible;
                boolean z = z;
                if (access$000 != z) {
                    boolean unused = Divider.this.mVisible = z;
                    Divider.this.mView.setVisibility(z ? 0 : 4);
                    Divider.this.mView.setMinimizedDockStack(Divider.this.mMinimized, Divider.this.mHomeStackResizable);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateMinimizedDockedStack(boolean z, long j, boolean z2) {
        final boolean z3 = z2;
        final boolean z4 = z;
        final long j2 = j;
        this.mView.post(new Runnable() {
            public void run() {
                boolean unused = Divider.this.mHomeStackResizable = z3;
                boolean access$200 = Divider.this.mMinimized;
                boolean z = z4;
                if (access$200 != z) {
                    boolean unused2 = Divider.this.mMinimized = z;
                    Divider.this.updateTouchable();
                    if (j2 > 0) {
                        Divider.this.mView.setMinimizedDockStack(z4, j2, z3);
                    } else {
                        Divider.this.mView.setMinimizedDockStack(z4, z3);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void notifyDockedStackExistsChanged(final boolean z) {
        this.mView.post(new Runnable() {
            public void run() {
                Divider.this.mForcedResizableController.notifyDockedStackExistsChanged(z);
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateTouchable() {
        this.mWindowManager.setTouchable((this.mHomeStackResizable || !this.mMinimized) && !this.mAdjustedForIme);
    }

    public void onRecentsDrawn() {
        DividerView dividerView = this.mView;
        if (dividerView != null) {
            dividerView.onRecentsDrawn();
        }
    }

    public void onUndockingTask() {
        DividerView dividerView = this.mView;
        if (dividerView != null) {
            dividerView.onUndockingTask();
        }
    }

    public void onDockedFirstAnimationFrame() {
        DividerView dividerView = this.mView;
        if (dividerView != null) {
            dividerView.onDockedFirstAnimationFrame();
        }
    }

    public void onAppTransitionFinished() {
        this.mForcedResizableController.onAppTransitionFinished();
    }

    public void onDraggingStart() {
        this.mForcedResizableController.onDraggingStart();
    }

    public void onDraggingEnd() {
        this.mForcedResizableController.onDraggingEnd();
    }

    public void growRecents() {
        Recents recents = (Recents) getComponent(Recents.class);
        if (recents != null) {
            recents.growRecents();
        }
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print("  mVisible=");
        printWriter.println(this.mVisible);
        printWriter.print("  mMinimized=");
        printWriter.println(this.mMinimized);
        printWriter.print("  mAdjustedForIme=");
        printWriter.println(this.mAdjustedForIme);
    }

    class DockDividerVisibilityListener extends IDockedStackListener.Stub {
        DockDividerVisibilityListener() {
        }

        public void onDividerVisibilityChanged(boolean z) throws RemoteException {
            Divider.this.updateVisibility(z);
        }

        public void onDockedStackExistsChanged(boolean z) throws RemoteException {
            Divider.this.notifyDockedStackExistsChanged(z);
        }

        public void onDockedStackMinimizedChanged(boolean z, long j, boolean z2) throws RemoteException {
            boolean unused = Divider.this.mHomeStackResizable = z2;
            Divider.this.updateMinimizedDockedStack(z, j, z2);
        }

        public void onAdjustedForImeChanged(boolean z, long j) throws RemoteException {
            Divider.this.mView.post(new Runnable(z, j) {
                private final /* synthetic */ boolean f$1;
                private final /* synthetic */ long f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    Divider.DockDividerVisibilityListener.this.mo12546x1fc4d65b(this.f$1, this.f$2);
                }
            });
        }

        /* renamed from: lambda$onAdjustedForImeChanged$0$Divider$DockDividerVisibilityListener */
        public /* synthetic */ void mo12546x1fc4d65b(boolean z, long j) {
            if (Divider.this.mAdjustedForIme != z) {
                boolean unused = Divider.this.mAdjustedForIme = z;
                Divider.this.updateTouchable();
                if (Divider.this.mMinimized) {
                    return;
                }
                if (j > 0) {
                    Divider.this.mView.setAdjustedForIme(z, j);
                } else {
                    Divider.this.mView.setAdjustedForIme(z);
                }
            }
        }

        public /* synthetic */ void lambda$onDockSideChanged$1$Divider$DockDividerVisibilityListener(int i) {
            Divider.this.mView.notifyDockSideChanged(i);
        }

        public void onDockSideChanged(int i) throws RemoteException {
            Divider.this.mView.post(new Runnable(i) {
                private final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    Divider.DockDividerVisibilityListener.this.lambda$onDockSideChanged$1$Divider$DockDividerVisibilityListener(this.f$1);
                }
            });
        }
    }
}
