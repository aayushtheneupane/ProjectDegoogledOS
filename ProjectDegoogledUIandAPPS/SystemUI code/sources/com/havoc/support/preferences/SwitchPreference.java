package com.havoc.support.preferences;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.R$attr;
import androidx.preference.R$layout;
import com.havoc.support.util.VibrationUtils;

public class SwitchPreference extends androidx.preference.SwitchPreference {
    private final Context mContext;

    public SwitchPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mContext = context;
    }

    public SwitchPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SwitchPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.getAttr(context, R$attr.switchPreferenceStyle, 16843629));
        setLayoutResource(R$layout.preference_material_settings);
    }

    public SwitchPreference(Context context) {
        this(context, (AttributeSet) null);
        setLayoutResource(R$layout.preference_material_settings);
    }

    /* access modifiers changed from: protected */
    public void performClick(View view) {
        super.performClick(view);
        VibrationUtils.doHapticFeedback(this.mContext, 0);
    }
}
