package com.android.dialer.glidephotomanager;

import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.inject.HasRootComponent;

public abstract class GlidePhotoManagerComponent {

    public interface HasComponent {
    }

    public static GlidePhotoManagerComponent get(Context context) {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).glidePhotoManagerComponent();
    }

    public abstract GlidePhotoManager glidePhotoManager();
}
