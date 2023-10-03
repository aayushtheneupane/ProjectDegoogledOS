package com.android.settings.search;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.SearchIndexableResource;
import android.provider.SearchIndexablesContract;
import android.provider.SearchIndexablesProvider;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import androidx.slice.SliceViewManager;
import com.android.settings.dashboard.DashboardFragmentRegistry;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.search.Indexable;
import com.android.settingslib.drawer.DashboardCategory;
import com.android.settingslib.drawer.Tile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SettingsSearchIndexablesProvider extends SearchIndexablesProvider {
    private static final Collection<String> INVALID_KEYS = new ArraySet();

    public boolean onCreate() {
        return true;
    }

    static {
        INVALID_KEYS.add((Object) null);
        INVALID_KEYS.add("");
    }

    public Cursor queryXmlResources(String[] strArr) {
        MatrixCursor matrixCursor = new MatrixCursor(SearchIndexablesContract.INDEXABLES_XML_RES_COLUMNS);
        for (SearchIndexableResource next : getSearchIndexableResourcesFromProvider(getContext())) {
            Object[] objArr = new Object[SearchIndexablesContract.INDEXABLES_XML_RES_COLUMNS.length];
            objArr[0] = Integer.valueOf(next.rank);
            objArr[1] = Integer.valueOf(next.xmlResId);
            objArr[2] = next.className;
            objArr[3] = Integer.valueOf(next.iconResId);
            objArr[4] = next.intentAction;
            objArr[5] = next.intentTargetPackage;
            objArr[6] = null;
            matrixCursor.addRow(objArr);
        }
        return matrixCursor;
    }

    public Cursor queryRawData(String[] strArr) {
        MatrixCursor matrixCursor = new MatrixCursor(SearchIndexablesContract.INDEXABLES_RAW_COLUMNS);
        for (SearchIndexableRaw next : getSearchIndexableRawFromProvider(getContext())) {
            Object[] objArr = new Object[SearchIndexablesContract.INDEXABLES_RAW_COLUMNS.length];
            objArr[1] = next.title;
            objArr[2] = next.summaryOn;
            objArr[3] = next.summaryOff;
            objArr[4] = next.entries;
            objArr[5] = next.keywords;
            objArr[6] = next.screenTitle;
            objArr[7] = next.className;
            objArr[8] = Integer.valueOf(next.iconResId);
            objArr[9] = next.intentAction;
            objArr[10] = next.intentTargetPackage;
            objArr[11] = next.intentTargetClass;
            objArr[12] = next.key;
            objArr[13] = Integer.valueOf(next.userId);
            matrixCursor.addRow(objArr);
        }
        return matrixCursor;
    }

    public Cursor queryNonIndexableKeys(String[] strArr) {
        MatrixCursor matrixCursor = new MatrixCursor(SearchIndexablesContract.NON_INDEXABLES_KEYS_COLUMNS);
        for (String str : getNonIndexableKeysFromProvider(getContext())) {
            Object[] objArr = new Object[SearchIndexablesContract.NON_INDEXABLES_KEYS_COLUMNS.length];
            objArr[0] = str;
            matrixCursor.addRow(objArr);
        }
        return matrixCursor;
    }

    public Cursor querySiteMapPairs() {
        MatrixCursor matrixCursor = new MatrixCursor(SearchIndexablesContract.SITE_MAP_COLUMNS);
        Context context = getContext();
        for (DashboardCategory next : FeatureFactory.getFactory(context).getDashboardFeatureProvider(context).getAllCategories()) {
            String str = DashboardFragmentRegistry.CATEGORY_KEY_TO_PARENT_MAP.get(next.key);
            if (str != null) {
                for (Tile next2 : next.getTiles()) {
                    String str2 = null;
                    if (next2.getMetaData() != null) {
                        str2 = next2.getMetaData().getString("com.android.settings.FRAGMENT_CLASS");
                    }
                    if (str2 != null) {
                        matrixCursor.newRow().add("parent_class", str).add("child_class", str2);
                    }
                }
            }
        }
        return matrixCursor;
    }

    public Cursor querySliceUriPairs() {
        SliceViewManager instance = SliceViewManager.getInstance(getContext());
        MatrixCursor matrixCursor = new MatrixCursor(SearchIndexablesContract.SLICE_URI_PAIRS_COLUMNS);
        Uri build = new Uri.Builder().scheme("content").authority("com.android.settings.slices").build();
        Uri build2 = new Uri.Builder().scheme("content").authority("android.settings.slices").build();
        Collection<Uri> sliceDescendants = instance.getSliceDescendants(build);
        sliceDescendants.addAll(instance.getSliceDescendants(build2));
        for (Uri next : sliceDescendants) {
            matrixCursor.newRow().add("key", next.getLastPathSegment()).add("slice_uri", next);
        }
        return matrixCursor;
    }

    private List<String> getNonIndexableKeysFromProvider(Context context) {
        Collection<Class> providerValues = FeatureFactory.getFactory(context).getSearchFeatureProvider().getSearchIndexableResources().getProviderValues();
        ArrayList arrayList = new ArrayList();
        for (Class next : providerValues) {
            System.currentTimeMillis();
            Indexable.SearchIndexProvider searchIndexProvider = DatabaseIndexingUtils.getSearchIndexProvider(next);
            try {
                List<String> nonIndexableKeys = searchIndexProvider.getNonIndexableKeys(context);
                if (nonIndexableKeys != null && !nonIndexableKeys.isEmpty()) {
                    if (nonIndexableKeys.removeAll(INVALID_KEYS)) {
                        Log.v("SettingsSearchProvider", searchIndexProvider + " tried to add an empty non-indexable key");
                    }
                    arrayList.addAll(nonIndexableKeys);
                }
            } catch (Exception e) {
                if (System.getProperty("debug.com.android.settings.search.crash_on_error") == null) {
                    Log.e("SettingsSearchProvider", "Error trying to get non-indexable keys from: " + next.getName(), e);
                } else {
                    throw new RuntimeException(e);
                }
            }
        }
        return arrayList;
    }

    private List<SearchIndexableResource> getSearchIndexableResourcesFromProvider(Context context) {
        String str;
        Collection<Class> providerValues = FeatureFactory.getFactory(context).getSearchFeatureProvider().getSearchIndexableResources().getProviderValues();
        ArrayList arrayList = new ArrayList();
        for (Class next : providerValues) {
            List<SearchIndexableResource> xmlResourcesToIndex = DatabaseIndexingUtils.getSearchIndexProvider(next).getXmlResourcesToIndex(context, true);
            if (xmlResourcesToIndex != null) {
                for (SearchIndexableResource next2 : xmlResourcesToIndex) {
                    if (TextUtils.isEmpty(next2.className)) {
                        str = next.getName();
                    } else {
                        str = next2.className;
                    }
                    next2.className = str;
                }
                arrayList.addAll(xmlResourcesToIndex);
            }
        }
        return arrayList;
    }

    private List<SearchIndexableRaw> getSearchIndexableRawFromProvider(Context context) {
        Collection<Class> providerValues = FeatureFactory.getFactory(context).getSearchFeatureProvider().getSearchIndexableResources().getProviderValues();
        ArrayList arrayList = new ArrayList();
        for (Class next : providerValues) {
            List<SearchIndexableRaw> rawDataToIndex = DatabaseIndexingUtils.getSearchIndexProvider(next).getRawDataToIndex(context, true);
            if (rawDataToIndex != null) {
                for (SearchIndexableRaw searchIndexableRaw : rawDataToIndex) {
                    searchIndexableRaw.className = next.getName();
                }
                arrayList.addAll(rawDataToIndex);
            }
        }
        return arrayList;
    }
}
