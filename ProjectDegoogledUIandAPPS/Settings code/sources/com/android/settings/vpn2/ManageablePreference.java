package com.android.settings.vpn2;

import android.content.Context;
import android.content.res.Resources;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.android.settings.widget.GearPreference;
import com.havoc.config.center.C1715R;

public abstract class ManageablePreference extends GearPreference {
    public static int STATE_NONE = -1;
    boolean mIsAlwaysOn = false;
    int mState = STATE_NONE;
    int mUserId;

    public ManageablePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPersistent(false);
        setOrder(0);
        setUserId(UserHandle.myUserId());
    }

    public int getUserId() {
        return this.mUserId;
    }

    public void setUserId(int i) {
        this.mUserId = i;
        checkRestrictionAndSetDisabled("no_config_vpn", i);
    }

    public int getState() {
        return this.mState;
    }

    public void setState(int i) {
        if (this.mState != i) {
            this.mState = i;
            updateSummary();
            notifyHierarchyChanged();
        }
    }

    public void setAlwaysOn(boolean z) {
        if (this.mIsAlwaysOn != z) {
            this.mIsAlwaysOn = z;
            updateSummary();
        }
    }

    /* access modifiers changed from: protected */
    public void updateSummary() {
        Resources resources = getContext().getResources();
        String[] stringArray = resources.getStringArray(C1715R.array.vpn_states);
        int i = this.mState;
        String str = i == STATE_NONE ? "" : stringArray[i];
        if (this.mIsAlwaysOn) {
            String string = resources.getString(C1715R.string.vpn_always_on_summary_active);
            str = TextUtils.isEmpty(str) ? string : resources.getString(C1715R.string.join_two_unrelated_items, new Object[]{str, string});
        }
        setSummary((CharSequence) str);
    }
}
