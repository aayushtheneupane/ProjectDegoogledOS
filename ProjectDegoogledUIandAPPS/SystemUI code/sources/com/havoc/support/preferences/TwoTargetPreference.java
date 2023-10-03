package com.havoc.support.preferences;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.havoc.support.R$id;
import com.havoc.support.R$layout;

public class TwoTargetPreference extends Preference {
    /* access modifiers changed from: protected */
    public int getSecondTargetResId() {
        return 0;
    }

    public TwoTargetPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    public TwoTargetPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        setLayoutResource(R$layout.preference_two_target);
        int secondTargetResId = getSecondTargetResId();
        if (secondTargetResId != 0) {
            setWidgetLayoutResource(secondTargetResId);
        }
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View findViewById = preferenceViewHolder.findViewById(R$id.two_target_divider);
        View findViewById2 = preferenceViewHolder.findViewById(16908312);
        boolean shouldHideSecondTarget = shouldHideSecondTarget();
        int i = 8;
        if (findViewById != null) {
            findViewById.setVisibility(shouldHideSecondTarget ? 8 : 0);
        }
        if (findViewById2 != null) {
            if (!shouldHideSecondTarget) {
                i = 0;
            }
            findViewById2.setVisibility(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldHideSecondTarget() {
        return getSecondTargetResId() == 0;
    }
}
