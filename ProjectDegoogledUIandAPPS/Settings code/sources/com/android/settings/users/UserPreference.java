package com.android.settings.users;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.preference.PreferenceViewHolder;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedPreference;
import com.havoc.config.center.C1715R;
import java.util.Comparator;

public class UserPreference extends RestrictedPreference {
    public static final Comparator<UserPreference> SERIAL_NUMBER_COMPARATOR = $$Lambda$UserPreference$UpImioqp9l2DqerpjWaO9lbHRs.INSTANCE;
    private View.OnClickListener mDeleteClickListener;
    private int mSerialNumber;
    private View.OnClickListener mSettingsClickListener;
    private int mUserId;

    static /* synthetic */ int lambda$static$0(UserPreference userPreference, UserPreference userPreference2) {
        if (userPreference == null) {
            return -1;
        }
        if (userPreference2 == null) {
            return 1;
        }
        int serialNumber = userPreference.getSerialNumber();
        int serialNumber2 = userPreference2.getSerialNumber();
        if (serialNumber < serialNumber2) {
            return -1;
        }
        if (serialNumber > serialNumber2) {
            return 1;
        }
        return 0;
    }

    public UserPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -10, (View.OnClickListener) null, (View.OnClickListener) null);
    }

    UserPreference(Context context, AttributeSet attributeSet, int i, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        super(context, attributeSet);
        this.mSerialNumber = -1;
        this.mUserId = -10;
        if (!(onClickListener2 == null && onClickListener == null)) {
            setWidgetLayoutResource(C1715R.layout.restricted_preference_user_delete_widget);
        }
        this.mDeleteClickListener = onClickListener2;
        this.mSettingsClickListener = onClickListener;
        this.mUserId = i;
        useAdminDisabledSummary(true);
    }

    private void dimIcon(boolean z) {
        Drawable icon = getIcon();
        if (icon != null) {
            icon.mutate().setAlpha(z ? 102 : 255);
            setIcon(icon);
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldHideSecondTarget() {
        if (isDisabledByAdmin()) {
            return true;
        }
        if (canDeleteUser()) {
            return false;
        }
        if (this.mSettingsClickListener == null) {
            return true;
        }
        return false;
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        boolean isDisabledByAdmin = isDisabledByAdmin();
        dimIcon(isDisabledByAdmin);
        View findViewById = preferenceViewHolder.findViewById(C1715R.C1718id.user_delete_widget);
        int i = 0;
        if (findViewById != null) {
            findViewById.setVisibility(isDisabledByAdmin ? 8 : 0);
        }
        if (!isDisabledByAdmin) {
            View findViewById2 = preferenceViewHolder.findViewById(C1715R.C1718id.divider_delete);
            View findViewById3 = preferenceViewHolder.findViewById(C1715R.C1718id.divider_manage);
            View findViewById4 = preferenceViewHolder.findViewById(C1715R.C1718id.trash_user);
            if (findViewById4 != null) {
                if (canDeleteUser()) {
                    findViewById4.setVisibility(0);
                    findViewById2.setVisibility(0);
                    findViewById4.setOnClickListener(this.mDeleteClickListener);
                    findViewById4.setTag(this);
                } else {
                    findViewById4.setVisibility(8);
                    findViewById2.setVisibility(8);
                }
            }
            ImageView imageView = (ImageView) preferenceViewHolder.findViewById(C1715R.C1718id.manage_user);
            if (imageView == null) {
                return;
            }
            if (this.mSettingsClickListener != null) {
                imageView.setVisibility(0);
                if (this.mDeleteClickListener != null) {
                    i = 8;
                }
                findViewById3.setVisibility(i);
                imageView.setOnClickListener(this.mSettingsClickListener);
                imageView.setTag(this);
                return;
            }
            imageView.setVisibility(8);
            findViewById3.setVisibility(8);
        }
    }

    private boolean canDeleteUser() {
        return this.mDeleteClickListener != null && !RestrictedLockUtilsInternal.hasBaseUserRestriction(getContext(), "no_remove_user", UserHandle.myUserId());
    }

    private int getSerialNumber() {
        if (this.mUserId == UserHandle.myUserId()) {
            return Integer.MIN_VALUE;
        }
        if (this.mSerialNumber < 0) {
            int i = this.mUserId;
            if (i == -10) {
                return Integer.MAX_VALUE;
            }
            if (i == -11) {
                return 2147483646;
            }
            this.mSerialNumber = ((UserManager) getContext().getSystemService("user")).getUserSerialNumber(this.mUserId);
            if (this.mSerialNumber < 0) {
                return this.mUserId;
            }
        }
        return this.mSerialNumber;
    }

    public int getUserId() {
        return this.mUserId;
    }
}
