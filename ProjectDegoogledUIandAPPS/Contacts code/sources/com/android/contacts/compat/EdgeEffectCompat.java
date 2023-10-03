package com.android.contacts.compat;

import android.widget.EdgeEffect;

public class EdgeEffectCompat {
    public static void onPull(EdgeEffect edgeEffect, float f, float f2) {
        if (CompatUtils.isLollipopCompatible()) {
            edgeEffect.onPull(f, f2);
        } else {
            edgeEffect.onPull(f);
        }
    }
}
