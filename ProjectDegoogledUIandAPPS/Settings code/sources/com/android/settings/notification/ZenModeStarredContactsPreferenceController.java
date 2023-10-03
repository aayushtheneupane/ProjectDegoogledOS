package com.android.settings.notification;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settingslib.core.lifecycle.Lifecycle;

public class ZenModeStarredContactsPreferenceController extends AbstractZenModePreferenceController implements Preference.OnPreferenceClickListener {
    private Intent mFallbackIntent = new Intent("android.intent.action.MAIN");
    private final PackageManager mPackageManager = this.mContext.getPackageManager();
    private Preference mPreference;
    private final int mPriorityCategory;
    private Intent mStarredContactsIntent = new Intent("com.android.contacts.action.LIST_STARRED");

    public ZenModeStarredContactsPreferenceController(Context context, Lifecycle lifecycle, int i, String str) {
        super(context, str, lifecycle);
        this.mPriorityCategory = i;
        this.mFallbackIntent.addCategory("android.intent.category.APP_CONTACTS");
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(this.KEY);
        Preference preference = this.mPreference;
        if (preference != null) {
            preference.setOnPreferenceClickListener(this);
        }
    }

    public String getPreferenceKey() {
        return this.KEY;
    }

    public boolean isAvailable() {
        int i = this.mPriorityCategory;
        if (i == 8) {
            if (!this.mBackend.isPriorityCategoryEnabled(8) || this.mBackend.getPriorityCallSenders() != 2 || !isIntentValid()) {
                return false;
            }
            return true;
        } else if (i != 4) {
            return false;
        } else {
            if (!this.mBackend.isPriorityCategoryEnabled(4) || this.mBackend.getPriorityMessageSenders() != 2 || !isIntentValid()) {
                return false;
            }
            return true;
        }
    }

    public CharSequence getSummary() {
        return this.mBackend.getStarredContactsSummary();
    }

    public boolean onPreferenceClick(Preference preference) {
        if (this.mStarredContactsIntent.resolveActivity(this.mPackageManager) != null) {
            this.mContext.startActivity(this.mStarredContactsIntent);
            return true;
        }
        this.mContext.startActivity(this.mFallbackIntent);
        return true;
    }

    private boolean isIntentValid() {
        return (this.mStarredContactsIntent.resolveActivity(this.mPackageManager) == null && this.mFallbackIntent.resolveActivity(this.mPackageManager) == null) ? false : true;
    }
}
