package com.android.settings.search;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.util.Log;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.core.PreferenceControllerListHelper;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.core.PreferenceXmlParserUtils;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

public class BaseSearchIndexProvider implements Indexable.SearchIndexProvider {
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return null;
    }

    public List<SearchIndexableRaw> getRawDataToIndex(Context context, boolean z) {
        return null;
    }

    public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isPageSearchEnabled(Context context) {
        return true;
    }

    public List<String> getNonIndexableKeys(Context context) {
        if (!isPageSearchEnabled(context)) {
            return getNonIndexableKeysFromXml(context, true);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(getNonIndexableKeysFromXml(context, false));
        List<AbstractPreferenceController> preferenceControllers = getPreferenceControllers(context);
        if (preferenceControllers != null && !preferenceControllers.isEmpty()) {
            for (AbstractPreferenceController next : preferenceControllers) {
                if (next instanceof PreferenceControllerMixin) {
                    ((PreferenceControllerMixin) next).updateNonIndexableKeys(arrayList);
                } else if (next instanceof BasePreferenceController) {
                    ((BasePreferenceController) next).updateNonIndexableKeys(arrayList);
                } else {
                    Log.e("BaseSearchIndex", next.getClass().getName() + " must implement " + PreferenceControllerMixin.class.getName() + " treating the key non-indexable");
                    arrayList.add(next.getPreferenceKey());
                }
            }
        }
        return arrayList;
    }

    public List<AbstractPreferenceController> getPreferenceControllers(Context context) {
        List<AbstractPreferenceController> createPreferenceControllers = createPreferenceControllers(context);
        List<SearchIndexableResource> xmlResourcesToIndex = getXmlResourcesToIndex(context, true);
        if (xmlResourcesToIndex == null || xmlResourcesToIndex.isEmpty()) {
            return createPreferenceControllers;
        }
        ArrayList arrayList = new ArrayList();
        for (SearchIndexableResource searchIndexableResource : xmlResourcesToIndex) {
            arrayList.addAll(PreferenceControllerListHelper.getPreferenceControllersFromXml(context, searchIndexableResource.xmlResId));
        }
        List<BasePreferenceController> filterControllers = PreferenceControllerListHelper.filterControllers(arrayList, createPreferenceControllers);
        ArrayList arrayList2 = new ArrayList();
        if (createPreferenceControllers != null) {
            arrayList2.addAll(createPreferenceControllers);
        }
        arrayList2.addAll(filterControllers);
        return arrayList2;
    }

    private List<String> getNonIndexableKeysFromXml(Context context, boolean z) {
        List<SearchIndexableResource> xmlResourcesToIndex = getXmlResourcesToIndex(context, true);
        if (xmlResourcesToIndex == null || xmlResourcesToIndex.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (SearchIndexableResource searchIndexableResource : xmlResourcesToIndex) {
            arrayList.addAll(getNonIndexableKeysFromXml(context, searchIndexableResource.xmlResId, z));
        }
        return arrayList;
    }

    public List<String> getNonIndexableKeysFromXml(Context context, int i, boolean z) {
        return getKeysFromXml(context, i, z);
    }

    private List<String> getKeysFromXml(Context context, int i, boolean z) {
        ArrayList arrayList = new ArrayList();
        try {
            for (Bundle next : PreferenceXmlParserUtils.extractMetadata(context, i, 515)) {
                if (z || !next.getBoolean("searchable", true)) {
                    arrayList.add(next.getString("key"));
                }
            }
        } catch (IOException | XmlPullParserException unused) {
            Log.w("BaseSearchIndex", "Error parsing non-indexable from xml " + i);
        }
        return arrayList;
    }
}
