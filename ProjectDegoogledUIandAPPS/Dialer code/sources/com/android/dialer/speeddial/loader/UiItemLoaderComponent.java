package com.android.dialer.speeddial.loader;

import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.inject.HasRootComponent;

public abstract class UiItemLoaderComponent {

    public interface HasComponent {
    }

    public static UiItemLoaderComponent get(Context context) {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).uiItemLoaderComponent();
    }

    public abstract SpeedDialUiItemMutator speedDialUiItemMutator();
}
