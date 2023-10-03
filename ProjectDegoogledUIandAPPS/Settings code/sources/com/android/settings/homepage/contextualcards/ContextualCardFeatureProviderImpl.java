package com.android.settings.homepage.contextualcards;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArraySet;
import androidx.slice.Slice;
import androidx.slice.SliceMetadata;
import com.android.settings.slices.CustomSliceRegistry;

public class ContextualCardFeatureProviderImpl implements ContextualCardFeatureProvider {
    private final Context mContext;

    public ContextualCardFeatureProviderImpl(Context context) {
        this.mContext = context;
    }

    public void logNotificationPackage(Slice slice) {
        if (slice != null && slice.getUri().equals(CustomSliceRegistry.CONTEXTUAL_NOTIFICATION_CHANNEL_SLICE_URI)) {
            String string = SliceMetadata.from(this.mContext, slice).getPrimaryAction().getAction().getIntent().getBundleExtra(":settings:show_fragment_args").getString("package");
            SharedPreferences sharedPreferences = this.mContext.getSharedPreferences("notification_channel_slice_prefs", 0);
            ArraySet arraySet = new ArraySet(sharedPreferences.getStringSet("interacted_packages", new ArraySet()));
            arraySet.add(string);
            sharedPreferences.edit().putStringSet("interacted_packages", arraySet).apply();
        }
    }
}
