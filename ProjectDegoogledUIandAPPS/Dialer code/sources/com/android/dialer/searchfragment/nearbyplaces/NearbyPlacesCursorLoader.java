package com.android.dialer.searchfragment.nearbyplaces;

import android.content.CursorLoader;
import android.database.Cursor;
import com.android.dialer.common.LogUtil;

public final class NearbyPlacesCursorLoader extends CursorLoader {
    private final long directoryId;

    public Cursor loadInBackground() {
        if (this.directoryId != Long.MAX_VALUE) {
            return NearbyPlacesCursor.newInstance(getContext(), super.loadInBackground(), this.directoryId);
        }
        LogUtil.m9i("NearbyPlacesCursorLoader.loadInBackground", "directory id not set.", new Object[0]);
        return null;
    }

    /* renamed from: loadInBackground  reason: collision with other method in class */
    public Object m119loadInBackground() {
        if (this.directoryId != Long.MAX_VALUE) {
            return NearbyPlacesCursor.newInstance(getContext(), super.loadInBackground(), this.directoryId);
        }
        LogUtil.m9i("NearbyPlacesCursorLoader.loadInBackground", "directory id not set.", new Object[0]);
        return null;
    }
}
