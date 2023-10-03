package com.android.settings.panel;

import android.content.Context;

public interface PanelFeatureProvider {
    PanelContent getPanel(Context context, String str, String str2);
}
