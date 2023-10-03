package com.android.settings.display;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;
import com.android.settingslib.display.DisplayDensityUtils;
import com.havoc.config.center.C1715R;

public class ScreenZoomPreference extends Preference {
    public ScreenZoomPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, TypedArrayUtils.getAttr(context, C1715R.attr.preferenceStyle, 16842894));
        DisplayDensityUtils displayDensityUtils = new DisplayDensityUtils(context);
        if (displayDensityUtils.getCurrentIndex() < 0) {
            setVisible(false);
            setEnabled(false);
        } else if (TextUtils.isEmpty(getSummary())) {
            setSummary((CharSequence) displayDensityUtils.getEntries()[displayDensityUtils.getCurrentIndex()]);
        }
    }
}
