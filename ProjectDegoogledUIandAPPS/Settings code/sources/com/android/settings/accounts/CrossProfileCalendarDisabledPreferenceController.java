package com.android.settings.accounts;

import android.content.Context;
import android.os.UserHandle;
import com.android.settings.core.BasePreferenceController;

public class CrossProfileCalendarDisabledPreferenceController extends BasePreferenceController {
    private UserHandle mManagedUser;

    public void setManagedUser(UserHandle userHandle) {
        this.mManagedUser = userHandle;
    }

    public CrossProfileCalendarDisabledPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        UserHandle userHandle = this.mManagedUser;
        return (userHandle == null || !CrossProfileCalendarPreferenceController.isCrossProfileCalendarDisallowedByAdmin(this.mContext, userHandle.getIdentifier())) ? 4 : 0;
    }
}
