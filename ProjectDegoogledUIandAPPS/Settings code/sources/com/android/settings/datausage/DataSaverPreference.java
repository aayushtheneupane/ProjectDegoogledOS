package com.android.settings.datausage;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.Preference;
import com.android.settings.datausage.DataSaverBackend;
import com.havoc.config.center.C1715R;

public class DataSaverPreference extends Preference implements DataSaverBackend.Listener {
    private final DataSaverBackend mDataSaverBackend;

    public void onBlacklistStatusChanged(int i, boolean z) {
    }

    public void onWhitelistStatusChanged(int i, boolean z) {
    }

    public DataSaverPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDataSaverBackend = new DataSaverBackend(context);
    }

    public void onAttached() {
        super.onAttached();
        this.mDataSaverBackend.addListener(this);
    }

    public void onDetached() {
        super.onDetached();
        this.mDataSaverBackend.remListener(this);
    }

    public void onDataSaverChanged(boolean z) {
        setSummary(z ? C1715R.string.data_saver_on : C1715R.string.data_saver_off);
    }
}
