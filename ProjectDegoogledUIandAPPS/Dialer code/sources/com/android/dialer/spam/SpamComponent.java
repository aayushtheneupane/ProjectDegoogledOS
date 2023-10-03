package com.android.dialer.spam;

import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.inject.HasRootComponent;

public abstract class SpamComponent {

    public interface HasComponent {
    }

    public static SpamComponent get(Context context) {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).spamComponent();
    }

    public abstract Spam spam();

    public abstract SpamSettings spamSettings();
}
