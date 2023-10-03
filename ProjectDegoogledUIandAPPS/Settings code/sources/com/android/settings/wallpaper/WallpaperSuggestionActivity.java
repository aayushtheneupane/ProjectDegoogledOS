package com.android.settings.wallpaper;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.android.settings.display.WallpaperPreferenceController;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.search.SearchIndexableRaw;
import java.util.ArrayList;
import java.util.List;

public class WallpaperSuggestionActivity extends StyleSuggestionActivityBase implements Indexable {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableRaw> getRawDataToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            WallpaperPreferenceController wallpaperPreferenceController = new WallpaperPreferenceController(context, "dummy key");
            SearchIndexableRaw searchIndexableRaw = new SearchIndexableRaw(context);
            searchIndexableRaw.title = wallpaperPreferenceController.getTitle();
            searchIndexableRaw.screenTitle = searchIndexableRaw.title;
            ComponentName componentName = wallpaperPreferenceController.getComponentName();
            searchIndexableRaw.intentTargetPackage = componentName.getPackageName();
            searchIndexableRaw.intentTargetClass = componentName.getClassName();
            searchIndexableRaw.intentAction = "android.intent.action.MAIN";
            searchIndexableRaw.key = "wallpaper_type";
            searchIndexableRaw.keywords = wallpaperPreferenceController.getKeywords();
            arrayList.add(searchIndexableRaw);
            return arrayList;
        }
    };

    /* access modifiers changed from: protected */
    public void addExtras(Intent intent) {
        intent.putExtra("com.android.launcher3.WALLPAPER_FLAVOR", "focus_wallpaper");
    }

    public static boolean isSuggestionComplete(Context context) {
        if (StyleSuggestionActivityBase.isWallpaperServiceEnabled(context) && ((WallpaperManager) context.getSystemService("wallpaper")).getWallpaperId(1) <= 0) {
            return false;
        }
        return true;
    }
}
