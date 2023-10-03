package com.android.settings.location;

import android.content.Context;
import android.os.UserManager;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.location.LocationEnabler;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;

public abstract class LocationBasePreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, LocationEnabler.LocationModeChangeListener {
    protected final LocationEnabler mLocationEnabler;
    protected final UserManager mUserManager;

    public boolean isAvailable() {
        return true;
    }

    public LocationBasePreferenceController(Context context, Lifecycle lifecycle) {
        super(context);
        this.mUserManager = (UserManager) context.getSystemService("user");
        this.mLocationEnabler = new LocationEnabler(context, this, lifecycle);
    }
}
