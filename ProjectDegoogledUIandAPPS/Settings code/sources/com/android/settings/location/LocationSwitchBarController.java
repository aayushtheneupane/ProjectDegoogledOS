package com.android.settings.location;

import android.content.Context;
import android.os.UserHandle;
import android.widget.Switch;
import com.android.settings.location.LocationEnabler;
import com.android.settings.widget.SwitchBar;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

public class LocationSwitchBarController implements SwitchBar.OnSwitchChangeListener, LocationEnabler.LocationModeChangeListener, LifecycleObserver, OnStart, OnStop {
    private final LocationEnabler mLocationEnabler;
    private final Switch mSwitch = this.mSwitchBar.getSwitch();
    private final SwitchBar mSwitchBar;
    private boolean mValidListener;

    public LocationSwitchBarController(Context context, SwitchBar switchBar, Lifecycle lifecycle) {
        this.mSwitchBar = switchBar;
        this.mLocationEnabler = new LocationEnabler(context, this, lifecycle);
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    public void onStart() {
        if (!this.mValidListener) {
            this.mSwitchBar.addOnSwitchChangeListener(this);
            this.mValidListener = true;
        }
    }

    public void onStop() {
        if (this.mValidListener) {
            this.mSwitchBar.removeOnSwitchChangeListener(this);
            this.mValidListener = false;
        }
    }

    public void onLocationModeChanged(int i, boolean z) {
        boolean isEnabled = this.mLocationEnabler.isEnabled(i);
        int myUserId = UserHandle.myUserId();
        RestrictedLockUtils.EnforcedAdmin shareLocationEnforcedAdmin = this.mLocationEnabler.getShareLocationEnforcedAdmin(myUserId);
        if (this.mLocationEnabler.hasShareLocationRestriction(myUserId) || shareLocationEnforcedAdmin == null) {
            this.mSwitchBar.setEnabled(!z);
        } else {
            this.mSwitchBar.setDisabledByAdmin(shareLocationEnforcedAdmin);
        }
        if (isEnabled != this.mSwitch.isChecked()) {
            if (this.mValidListener) {
                this.mSwitchBar.removeOnSwitchChangeListener(this);
            }
            this.mSwitch.setChecked(isEnabled);
            if (this.mValidListener) {
                this.mSwitchBar.addOnSwitchChangeListener(this);
            }
        }
    }

    public void onSwitchChanged(Switch switchR, boolean z) {
        this.mLocationEnabler.setLocationEnabled(z);
    }
}
