package com.android.settings.slices;

import android.content.Context;
import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.preference.PreferenceScreen;
import androidx.slice.Slice;
import androidx.slice.widget.SliceLiveData;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

public class SlicePreferenceController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop, Observer<Slice> {
    LiveData<Slice> mLiveData;
    SlicePreference mSlicePreference;
    private Uri mUri;

    public SlicePreferenceController(Context context, String str) {
        super(context, str);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mSlicePreference = (SlicePreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    public int getAvailabilityStatus() {
        return this.mUri != null ? 0 : 3;
    }

    public void setSliceUri(Uri uri) {
        this.mUri = uri;
        this.mLiveData = SliceLiveData.fromUri(this.mContext, this.mUri);
        this.mLiveData.removeObserver(this);
    }

    public void onStart() {
        LiveData<Slice> liveData = this.mLiveData;
        if (liveData != null) {
            liveData.observeForever(this);
        }
    }

    public void onStop() {
        LiveData<Slice> liveData = this.mLiveData;
        if (liveData != null) {
            liveData.removeObserver(this);
        }
    }

    public void onChanged(Slice slice) {
        this.mSlicePreference.onSliceUpdated(slice);
    }
}
