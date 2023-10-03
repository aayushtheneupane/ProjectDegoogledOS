package com.android.systemui.statusbar.p007tv;

import android.os.RemoteException;
import android.os.ServiceManager;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.SystemUI;
import com.android.systemui.statusbar.CommandQueue;

/* renamed from: com.android.systemui.statusbar.tv.TvStatusBar */
public class TvStatusBar extends SystemUI implements CommandQueue.Callbacks {
    private IStatusBarService mBarService;

    public void start() {
        putComponent(TvStatusBar.class, this);
        CommandQueue commandQueue = (CommandQueue) getComponent(CommandQueue.class);
        commandQueue.addCallback((CommandQueue.Callbacks) this);
        this.mBarService = IStatusBarService.Stub.asInterface(ServiceManager.getService("statusbar"));
        try {
            this.mBarService.registerStatusBar(commandQueue);
        } catch (RemoteException unused) {
        }
    }
}
