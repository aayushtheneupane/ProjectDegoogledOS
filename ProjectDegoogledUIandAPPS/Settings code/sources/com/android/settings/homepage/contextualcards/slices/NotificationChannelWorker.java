package com.android.settings.homepage.contextualcards.slices;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.ArraySet;
import com.android.settings.slices.SliceBackgroundWorker;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NotificationChannelWorker extends SliceBackgroundWorker<Void> {
    public void close() throws IOException {
    }

    /* access modifiers changed from: protected */
    public void onSlicePinned() {
    }

    public NotificationChannelWorker(Context context, Uri uri) {
        super(context, uri);
    }

    /* access modifiers changed from: protected */
    public void onSliceUnpinned() {
        removeUninstalledPackages();
    }

    private void removeUninstalledPackages() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("notification_channel_slice_prefs", 0);
        Set<String> stringSet = sharedPreferences.getStringSet("interacted_packages", new ArraySet());
        if (!stringSet.isEmpty()) {
            List list = (List) getContext().getPackageManager().getInstalledPackages(0).stream().map($$Lambda$NotificationChannelWorker$Z92VnQE66tNhgZCNVX8Mf7x4aVE.INSTANCE).collect(Collectors.toList());
            ArraySet arraySet = new ArraySet();
            for (String next : stringSet) {
                if (list.contains(next)) {
                    arraySet.add(next);
                }
            }
            sharedPreferences.edit().putStringSet("interacted_packages", arraySet).apply();
        }
    }
}
