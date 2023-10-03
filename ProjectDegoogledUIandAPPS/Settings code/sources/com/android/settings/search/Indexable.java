package com.android.settings.search;

import android.content.Context;
import android.provider.SearchIndexableResource;
import androidx.annotation.Keep;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.List;

public interface Indexable {

    public interface SearchIndexProvider {
        @Keep
        List<String> getNonIndexableKeys(Context context);

        @Keep
        List<AbstractPreferenceController> getPreferenceControllers(Context context);

        @Keep
        List<SearchIndexableRaw> getRawDataToIndex(Context context, boolean z);

        @Keep
        List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z);
    }
}
