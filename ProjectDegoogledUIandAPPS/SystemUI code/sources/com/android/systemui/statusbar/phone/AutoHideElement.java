package com.android.systemui.statusbar.phone;

public interface AutoHideElement {
    boolean isSemiTransparent();

    void synchronizeState();
}
