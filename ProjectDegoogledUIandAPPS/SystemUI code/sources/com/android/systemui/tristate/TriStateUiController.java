package com.android.systemui.tristate;

import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.VolumeDialog;
import com.android.systemui.plugins.annotations.DependsOn;
import com.android.systemui.plugins.annotations.ProvidesInterface;

@DependsOn(target = VolumeDialog.Callback.class)
@ProvidesInterface(action = "com.android.systemui.action.PLUGIN_TRI_STATE_UI", version = 1)
public interface TriStateUiController extends Plugin {

    public interface UserActivityListener {
        void onTriStateUserActivity();
    }
}
