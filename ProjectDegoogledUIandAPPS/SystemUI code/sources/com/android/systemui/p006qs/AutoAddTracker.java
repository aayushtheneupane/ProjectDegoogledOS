package com.android.systemui.p006qs;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArraySet;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.Prefs;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/* renamed from: com.android.systemui.qs.AutoAddTracker */
public class AutoAddTracker {
    private static final String[][] CONVERT_PREFS = {new String[]{"QsHotspotAdded", "hotspot"}, new String[]{"QsDataSaverAdded", "saver"}, new String[]{"QsInvertColorsAdded", "inversion"}, new String[]{"QsWorkAdded", "work"}, new String[]{"QsNightDisplayAdded", "night"}};
    /* access modifiers changed from: private */
    public final ArraySet<String> mAutoAdded;
    private final Context mContext;
    @VisibleForTesting
    protected final ContentObserver mObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean z) {
            AutoAddTracker.this.mAutoAdded.addAll(AutoAddTracker.this.getAdded());
        }
    };

    public AutoAddTracker(Context context) {
        this.mContext = context;
        this.mAutoAdded = new ArraySet<>(getAdded());
        for (String[] strArr : CONVERT_PREFS) {
            if (Prefs.getBoolean(context, strArr[0], false)) {
                setTileAdded(strArr[1]);
                Prefs.remove(context, strArr[0]);
            }
        }
        this.mContext.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("qs_auto_tiles"), false, this.mObserver);
    }

    public boolean isAdded(String str) {
        return this.mAutoAdded.contains(str);
    }

    public void setTileAdded(String str) {
        if (this.mAutoAdded.add(str)) {
            saveTiles();
        }
    }

    public void setTileRemoved(String str) {
        if (this.mAutoAdded.remove(str)) {
            saveTiles();
        }
    }

    private void saveTiles() {
        Settings.Secure.putString(this.mContext.getContentResolver(), "qs_auto_tiles", TextUtils.join(",", this.mAutoAdded));
    }

    /* access modifiers changed from: private */
    public Collection<String> getAdded() {
        String string = Settings.Secure.getString(this.mContext.getContentResolver(), "qs_auto_tiles");
        if (string == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(string.split(","));
    }
}
