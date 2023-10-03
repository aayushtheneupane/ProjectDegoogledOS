package com.havoc.support.preferences;

import android.content.Context;
import android.util.AttributeSet;
import com.havoc.support.R$layout;

public class PreferenceCategory extends androidx.preference.PreferenceCategory {
    public PreferenceCategory(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutResource(R$layout.preference_category_material_settings);
    }
}
