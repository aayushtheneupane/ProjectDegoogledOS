package com.android.systemui.shared.system;

import android.graphics.Rect;
import android.os.RemoteException;
import android.util.Log;
import android.view.IPinnedStackListener;
import android.view.WindowManagerGlobal;

public class WindowManagerWrapper {
    private static final WindowManagerWrapper sInstance = new WindowManagerWrapper();
    private PinnedStackListenerForwarder mPinnedStackListenerForwarder = new PinnedStackListenerForwarder();

    public static WindowManagerWrapper getInstance() {
        return sInstance;
    }

    public void getStableInsets(Rect rect) {
        try {
            WindowManagerGlobal.getWindowManagerService().getStableInsets(0, rect);
        } catch (RemoteException e) {
            Log.e("WindowManagerWrapper", "Failed to get stable insets", e);
        }
    }

    public void setNavBarVirtualKeyHapticFeedbackEnabled(boolean z) {
        try {
            WindowManagerGlobal.getWindowManagerService().setNavBarVirtualKeyHapticFeedbackEnabled(z);
        } catch (RemoteException e) {
            Log.w("WindowManagerWrapper", "Failed to enable or disable navigation bar button haptics: ", e);
        }
    }

    public void setPipVisibility(boolean z) {
        try {
            WindowManagerGlobal.getWindowManagerService().setPipVisibility(z);
        } catch (RemoteException e) {
            Log.e("WindowManagerWrapper", "Unable to reach window manager", e);
        }
    }

    public boolean hasSoftNavigationBar(int i) {
        try {
            return WindowManagerGlobal.getWindowManagerService().hasNavigationBar(i);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public int getNavBarPosition(int i) {
        try {
            return WindowManagerGlobal.getWindowManagerService().getNavBarPosition(i);
        } catch (RemoteException unused) {
            Log.w("WindowManagerWrapper", "Failed to get nav bar position");
            return -1;
        }
    }

    public void addPinnedStackListener(IPinnedStackListener iPinnedStackListener) throws RemoteException {
        this.mPinnedStackListenerForwarder.addListener(iPinnedStackListener);
        WindowManagerGlobal.getWindowManagerService().registerPinnedStackListener(0, this.mPinnedStackListenerForwarder);
    }

    public void removePinnedStackListener(IPinnedStackListener iPinnedStackListener) {
        this.mPinnedStackListenerForwarder.removeListener(iPinnedStackListener);
    }
}
