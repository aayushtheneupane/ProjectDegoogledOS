package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.content.res.Configuration;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.policy.ConfigurationController;

public class RemoteInputQuickSettingsDisabler implements ConfigurationController.ConfigurationListener {
    @VisibleForTesting
    CommandQueue mCommandQueue;
    private Context mContext;
    private int mLastOrientation = this.mContext.getResources().getConfiguration().orientation;
    @VisibleForTesting
    boolean mRemoteInputActive;
    @VisibleForTesting
    boolean misLandscape;

    public RemoteInputQuickSettingsDisabler(Context context, ConfigurationController configurationController) {
        this.mContext = context;
        this.mCommandQueue = (CommandQueue) SysUiServiceProvider.getComponent(context, CommandQueue.class);
        configurationController.addCallback(this);
    }

    public int adjustDisableFlags(int i) {
        return (!this.mRemoteInputActive || !this.misLandscape) ? i : i | 1;
    }

    public void setRemoteInputActive(boolean z) {
        if (this.mRemoteInputActive != z) {
            this.mRemoteInputActive = z;
            recomputeDisableFlags();
        }
    }

    public void onConfigChanged(Configuration configuration) {
        int i = configuration.orientation;
        if (i != this.mLastOrientation) {
            this.misLandscape = i == 2;
            this.mLastOrientation = configuration.orientation;
            recomputeDisableFlags();
        }
    }

    private void recomputeDisableFlags() {
        this.mCommandQueue.recomputeDisableFlags(this.mContext.getDisplayId(), true);
    }
}
