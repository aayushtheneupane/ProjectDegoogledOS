package com.android.systemui.shortcut;

import android.graphics.Rect;
import android.os.RemoteException;
import android.util.Log;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;
import com.android.internal.policy.DividerSnapAlgorithm;
import com.android.systemui.SystemUI;
import com.android.systemui.recents.Recents;
import com.android.systemui.shortcut.ShortcutKeyServiceProxy;
import com.android.systemui.stackdivider.Divider;
import com.android.systemui.stackdivider.DividerView;

public class ShortcutKeyDispatcher extends SystemUI implements ShortcutKeyServiceProxy.Callbacks {
    protected final long ALT_MASK = 8589934592L;
    protected final long CTRL_MASK = 17592186044416L;
    protected final long META_MASK = 281474976710656L;
    protected final long SC_DOCK_LEFT = 281474976710727L;
    protected final long SC_DOCK_RIGHT = 281474976710728L;
    protected final long SHIFT_MASK = 4294967296L;
    private ShortcutKeyServiceProxy mShortcutKeyServiceProxy = new ShortcutKeyServiceProxy(this);
    private IWindowManager mWindowManagerService = WindowManagerGlobal.getWindowManagerService();

    public void registerShortcutKey(long j) {
        try {
            this.mWindowManagerService.registerShortcutKey(j, this.mShortcutKeyServiceProxy);
        } catch (RemoteException unused) {
        }
    }

    public void onShortcutKeyPressed(long j) {
        int i = this.mContext.getResources().getConfiguration().orientation;
        if ((j == 281474976710727L || j == 281474976710728L) && i == 2) {
            handleDockKey(j);
        }
    }

    public void start() {
        registerShortcutKey(281474976710727L);
        registerShortcutKey(281474976710728L);
    }

    private void handleDockKey(long j) {
        DividerSnapAlgorithm.SnapTarget snapTarget;
        try {
            int i = 0;
            if (this.mWindowManagerService.getDockedStackSide() == -1) {
                Recents recents = (Recents) getComponent(Recents.class);
                if (j != 281474976710727L) {
                    i = 1;
                }
                recents.splitPrimaryTask(i, (Rect) null, -1);
                return;
            }
            DividerView view = ((Divider) getComponent(Divider.class)).getView();
            DividerSnapAlgorithm snapAlgorithm = view.getSnapAlgorithm();
            DividerSnapAlgorithm.SnapTarget calculateNonDismissingSnapTarget = snapAlgorithm.calculateNonDismissingSnapTarget(view.getCurrentPosition());
            if (j == 281474976710727L) {
                snapTarget = snapAlgorithm.getPreviousTarget(calculateNonDismissingSnapTarget);
            } else {
                snapTarget = snapAlgorithm.getNextTarget(calculateNonDismissingSnapTarget);
            }
            view.startDragging(true, false);
            view.stopDragging(snapTarget.position, 0.0f, false, true);
        } catch (RemoteException unused) {
            Log.e("ShortcutKeyDispatcher", "handleDockKey() failed.");
        }
    }
}
