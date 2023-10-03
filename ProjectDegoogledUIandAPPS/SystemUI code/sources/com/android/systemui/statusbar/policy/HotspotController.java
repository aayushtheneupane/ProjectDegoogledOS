package com.android.systemui.statusbar.policy;

import com.android.systemui.Dumpable;

public interface HotspotController extends CallbackController<Callback>, Dumpable {

    public interface Callback {
        void onHotspotChanged(boolean z, int i);
    }

    int getNumConnectedDevices();

    boolean isHotspotEnabled();

    boolean isHotspotSupported();

    boolean isHotspotTransient();

    void setHotspotEnabled(boolean z);
}
