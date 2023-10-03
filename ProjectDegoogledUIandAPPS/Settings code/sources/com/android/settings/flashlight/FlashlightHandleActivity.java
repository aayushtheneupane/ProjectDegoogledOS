package com.android.settings.flashlight;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.search.SearchIndexableRaw;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class FlashlightHandleActivity extends Activity implements Indexable {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableRaw> getRawDataToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableRaw searchIndexableRaw = new SearchIndexableRaw(context);
            searchIndexableRaw.title = context.getString(C1715R.string.power_flashlight);
            searchIndexableRaw.screenTitle = context.getString(C1715R.string.power_flashlight);
            searchIndexableRaw.keywords = context.getString(C1715R.string.keywords_flashlight);
            searchIndexableRaw.intentTargetPackage = context.getPackageName();
            searchIndexableRaw.intentTargetClass = FlashlightHandleActivity.class.getName();
            searchIndexableRaw.intentAction = "android.intent.action.MAIN";
            searchIndexableRaw.key = "flashlight";
            arrayList.add(searchIndexableRaw);
            return arrayList;
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent().getBooleanExtra("fallback_to_homepage", false)) {
            startActivity(new Intent("android.settings.SETTINGS"));
        }
        finish();
    }
}
