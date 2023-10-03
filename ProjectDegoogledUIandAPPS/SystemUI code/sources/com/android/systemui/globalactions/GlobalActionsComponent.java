package com.android.systemui.globalactions;

import android.os.RemoteException;
import android.os.ServiceManager;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.Dependency;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.SystemUI;
import com.android.systemui.plugins.GlobalActions;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.policy.ExtensionController;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GlobalActionsComponent extends SystemUI implements CommandQueue.Callbacks, GlobalActions.GlobalActionsManager {
    private IStatusBarService mBarService;
    private ExtensionController.Extension<GlobalActions> mExtension;
    private GlobalActions mPlugin;

    public void start() {
        this.mBarService = IStatusBarService.Stub.asInterface(ServiceManager.getService("statusbar"));
        ExtensionController.ExtensionBuilder<GlobalActions> newExtension = ((ExtensionController) Dependency.get(ExtensionController.class)).newExtension(GlobalActions.class);
        newExtension.withPlugin(GlobalActions.class);
        newExtension.withDefault(new Supplier() {
            public final Object get() {
                return GlobalActionsComponent.this.lambda$start$0$GlobalActionsComponent();
            }
        });
        newExtension.withCallback(new Consumer() {
            public final void accept(Object obj) {
                GlobalActionsComponent.this.onExtensionCallback((GlobalActions) obj);
            }
        });
        this.mExtension = newExtension.build();
        this.mPlugin = this.mExtension.get();
        ((CommandQueue) SysUiServiceProvider.getComponent(this.mContext, CommandQueue.class)).addCallback((CommandQueue.Callbacks) this);
    }

    public /* synthetic */ GlobalActions lambda$start$0$GlobalActionsComponent() {
        return new GlobalActionsImpl(this.mContext);
    }

    /* access modifiers changed from: private */
    public void onExtensionCallback(GlobalActions globalActions) {
        GlobalActions globalActions2 = this.mPlugin;
        if (globalActions2 != null) {
            globalActions2.destroy();
        }
        this.mPlugin = globalActions;
    }

    public void handleShowShutdownUi(boolean z, String str) {
        this.mExtension.get().showShutdownUi(z, str);
    }

    public void handleShowGlobalActionsMenu() {
        this.mExtension.get().showGlobalActions(this);
    }

    public void onGlobalActionsShown() {
        try {
            this.mBarService.onGlobalActionsShown();
        } catch (RemoteException unused) {
        }
    }

    public void onGlobalActionsHidden() {
        try {
            this.mBarService.onGlobalActionsHidden();
        } catch (RemoteException unused) {
        }
    }

    public void shutdown() {
        try {
            this.mBarService.shutdown();
        } catch (RemoteException unused) {
        }
    }

    public void reboot(boolean z) {
        try {
            this.mBarService.reboot(z);
        } catch (RemoteException unused) {
        }
    }

    public void advancedReboot(String str) {
        try {
            this.mBarService.advancedReboot(str);
        } catch (RemoteException unused) {
        }
    }
}
