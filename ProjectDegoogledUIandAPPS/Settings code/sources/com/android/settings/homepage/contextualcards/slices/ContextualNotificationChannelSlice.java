package com.android.settings.homepage.contextualcards.slices;

import android.content.Context;
import android.net.Uri;
import android.util.ArraySet;
import com.android.settings.slices.CustomSliceRegistry;
import com.android.settings.slices.SliceBackgroundWorker;
import com.havoc.config.center.C1715R;

public class ContextualNotificationChannelSlice extends NotificationChannelSlice {
    public ContextualNotificationChannelSlice(Context context) {
        super(context);
    }

    public Uri getUri() {
        return CustomSliceRegistry.CONTEXTUAL_NOTIFICATION_CHANNEL_SLICE_URI;
    }

    /* access modifiers changed from: protected */
    public CharSequence getSubTitle(String str, int i) {
        return this.mContext.getText(C1715R.string.recently_installed_app);
    }

    /* access modifiers changed from: protected */
    public boolean isUserInteracted(String str) {
        return this.mContext.getSharedPreferences("notification_channel_slice_prefs", 0).getStringSet("interacted_packages", new ArraySet()).contains(str);
    }

    public Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return NotificationChannelWorker.class;
    }
}
