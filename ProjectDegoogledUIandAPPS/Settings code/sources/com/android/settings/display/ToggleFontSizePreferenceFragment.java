package com.android.settings.display;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.search.SearchIndexableRaw;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class ToggleFontSizePreferenceFragment extends PreviewSeekBarPreferenceFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableRaw> getRawDataToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableRaw searchIndexableRaw = new SearchIndexableRaw(context);
            searchIndexableRaw.title = context.getString(C1715R.string.title_font_size);
            searchIndexableRaw.screenTitle = context.getString(C1715R.string.title_font_size);
            searchIndexableRaw.key = "font_size_setting_screen";
            searchIndexableRaw.keywords = context.getString(C1715R.string.keywords_display_font_size);
            arrayList.add(searchIndexableRaw);
            return arrayList;
        }
    };
    private float[] mValues;

    /* access modifiers changed from: protected */
    public int getActivityLayoutResId() {
        return C1715R.layout.font_size_activity;
    }

    public int getHelpResource() {
        return C1715R.string.help_url_font_size;
    }

    public int getMetricsCategory() {
        return 340;
    }

    /* access modifiers changed from: protected */
    public int[] getPreviewSampleResIds() {
        return new int[]{C1715R.layout.font_size_preview};
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Resources resources = getContext().getResources();
        ContentResolver contentResolver = getContext().getContentResolver();
        this.mEntries = resources.getStringArray(C1715R.array.entries_font_size_percent);
        String[] stringArray = resources.getStringArray(C1715R.array.entryvalues_font_size);
        this.mInitialIndex = fontSizeValueToIndex(Settings.System.getFloat(contentResolver, "font_scale", 1.0f), stringArray);
        this.mValues = new float[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            this.mValues[i] = Float.parseFloat(stringArray[i]);
        }
        getActivity().setTitle(C1715R.string.title_font_size);
    }

    /* access modifiers changed from: protected */
    public Configuration createConfig(Configuration configuration, int i) {
        Configuration configuration2 = new Configuration(configuration);
        configuration2.fontScale = this.mValues[i];
        return configuration2;
    }

    /* access modifiers changed from: protected */
    public void commit() {
        if (getContext() != null) {
            Settings.System.putFloat(getContext().getContentResolver(), "font_scale", this.mValues[this.mCurrentIndex]);
        }
    }

    public static int fontSizeValueToIndex(float f, String[] strArr) {
        float parseFloat = Float.parseFloat(strArr[0]);
        int i = 1;
        while (i < strArr.length) {
            float parseFloat2 = Float.parseFloat(strArr[i]);
            if (f < parseFloat + ((parseFloat2 - parseFloat) * 0.5f)) {
                return i - 1;
            }
            i++;
            parseFloat = parseFloat2;
        }
        return strArr.length - 1;
    }
}
