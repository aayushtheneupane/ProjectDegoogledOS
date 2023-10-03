package com.google.android.settings.dashboard.suggestions;

import android.content.ComponentName;
import android.content.Context;
import com.android.settings.dashboard.suggestions.SuggestionFeatureProviderImpl;

public class SuggestionFeatureProviderGoogleImpl extends SuggestionFeatureProviderImpl {
    public SuggestionFeatureProviderGoogleImpl(Context context) {
        super(context);
    }

    public ComponentName getSuggestionServiceComponent() {
        return new ComponentName("com.google.android.settings.intelligence", "com.google.android.settings.intelligence.modules.suggestions.SuggestionService");
    }
}
