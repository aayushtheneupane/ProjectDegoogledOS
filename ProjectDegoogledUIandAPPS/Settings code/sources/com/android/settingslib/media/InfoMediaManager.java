package com.android.settingslib.media;

import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import com.android.internal.annotations.VisibleForTesting;

public class InfoMediaManager extends MediaManager {
    @VisibleForTesting
    MediaRouter mMediaRouter;
    @VisibleForTesting
    final MediaRouterCallback mMediaRouterCallback;
    @VisibleForTesting
    MediaRouteSelector mSelector;

    class MediaRouterCallback extends MediaRouter.Callback {
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public String getControlCategoryByPackageName(String str) {
        return "com.google.android.gms.cast.CATEGORY_CAST/4F8B3483";
    }
}
