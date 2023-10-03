package com.android.dialer.theme.base;

import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.inject.HasRootComponent;

public abstract class ThemeComponent {

    public interface HasComponent {
    }

    public static ThemeComponent get(Context context) {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).themeComponent();
    }

    public abstract Theme theme();
}
