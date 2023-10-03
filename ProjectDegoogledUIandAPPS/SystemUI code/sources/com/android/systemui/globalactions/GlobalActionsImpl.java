package com.android.systemui.globalactions;

import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.internal.colorextraction.drawable.ScrimDrawable;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.settingslib.Utils;
import com.android.systemui.C1772R$attr;
import com.android.systemui.C1785R$style;
import com.android.systemui.Dependency;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.plugins.GlobalActions;
import com.android.systemui.plugins.GlobalActionsPanelPlugin;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.ExtensionController;
import com.android.systemui.statusbar.policy.KeyguardMonitor;

public class GlobalActionsImpl implements GlobalActions, CommandQueue.Callbacks {
    private final Context mContext;
    private final DeviceProvisionedController mDeviceProvisionedController = ((DeviceProvisionedController) Dependency.get(DeviceProvisionedController.class));
    private boolean mDisabled;
    private GlobalActionsDialog mGlobalActions;
    private final KeyguardMonitor mKeyguardMonitor = ((KeyguardMonitor) Dependency.get(KeyguardMonitor.class));
    private final ExtensionController.Extension<GlobalActionsPanelPlugin> mPanelExtension;

    public GlobalActionsImpl(Context context) {
        this.mContext = context;
        ((CommandQueue) SysUiServiceProvider.getComponent(context, CommandQueue.class)).addCallback((CommandQueue.Callbacks) this);
        ExtensionController.ExtensionBuilder<GlobalActionsPanelPlugin> newExtension = ((ExtensionController) Dependency.get(ExtensionController.class)).newExtension(GlobalActionsPanelPlugin.class);
        newExtension.withPlugin(GlobalActionsPanelPlugin.class);
        this.mPanelExtension = newExtension.build();
    }

    public void destroy() {
        ((CommandQueue) SysUiServiceProvider.getComponent(this.mContext, CommandQueue.class)).removeCallback((CommandQueue.Callbacks) this);
        GlobalActionsDialog globalActionsDialog = this.mGlobalActions;
        if (globalActionsDialog != null) {
            globalActionsDialog.destroy();
            this.mGlobalActions = null;
        }
    }

    public void showGlobalActions(GlobalActions.GlobalActionsManager globalActionsManager) {
        if (!this.mDisabled) {
            if (this.mGlobalActions == null) {
                this.mGlobalActions = new GlobalActionsDialog(this.mContext, globalActionsManager);
            }
            this.mGlobalActions.showDialog(this.mKeyguardMonitor.isShowing(), this.mDeviceProvisionedController.isDeviceProvisioned(), this.mPanelExtension.get());
            KeyguardUpdateMonitor.getInstance(this.mContext).requestFaceAuth();
        }
    }

    public void showShutdownUi(boolean z, String str) {
        ScrimDrawable scrimDrawable = new ScrimDrawable();
        scrimDrawable.setAlpha(242);
        Dialog dialog = new Dialog(this.mContext, C1785R$style.Theme_SystemUI_Dialog_GlobalActions);
        Window window = dialog.getWindow();
        window.requestFeature(1);
        window.getAttributes().systemUiVisibility |= 1792;
        window.getDecorView();
        window.getAttributes().width = -1;
        window.getAttributes().height = -1;
        window.getAttributes().layoutInDisplayCutoutMode = 1;
        window.setType(2020);
        window.clearFlags(2);
        window.addFlags(17629472);
        window.setBackgroundDrawable(scrimDrawable);
        window.setWindowAnimations(16973828);
        dialog.setContentView(17367292);
        dialog.setCancelable(false);
        int colorAttrDefaultColor = Utils.getColorAttrDefaultColor(this.mContext, C1772R$attr.wallpaperTextColor);
        ((KeyguardManager) this.mContext.getSystemService(KeyguardManager.class)).isKeyguardLocked();
        ((ProgressBar) dialog.findViewById(16908301)).getIndeterminateDrawable().setTint(colorAttrDefaultColor);
        TextView textView = (TextView) dialog.findViewById(16908308);
        textView.setTextColor(colorAttrDefaultColor);
        if (str != null && "bootloader".equals(str)) {
            textView.setText(17040973);
        } else if (str != null && "recovery".equals(str)) {
            textView.setText(17040975);
        } else if (z) {
            textView.setText(17040969);
        }
        scrimDrawable.setColor(((SysuiColorExtractor) Dependency.get(SysuiColorExtractor.class)).getNeutralColors().getMainColor(), false);
        dialog.show();
    }

    public void disable(int i, int i2, int i3, boolean z) {
        GlobalActionsDialog globalActionsDialog;
        boolean z2 = (i3 & 8) != 0;
        if (i == this.mContext.getDisplayId() && z2 != this.mDisabled) {
            this.mDisabled = z2;
            if (z2 && (globalActionsDialog = this.mGlobalActions) != null) {
                globalActionsDialog.dismissDialog();
            }
        }
    }
}
