package com.android.settings.search;

import android.util.Log;
import com.android.settings.search.Indexable;

public class DatabaseIndexingUtils {
    public static Indexable.SearchIndexProvider getSearchIndexProvider(Class<?> cls) {
        try {
            return (Indexable.SearchIndexProvider) cls.getField("SEARCH_INDEX_DATA_PROVIDER").get((Object) null);
        } catch (NoSuchFieldException unused) {
            Log.d("IndexingUtil", "Cannot find field 'SEARCH_INDEX_DATA_PROVIDER'");
            return null;
        } catch (SecurityException unused2) {
            Log.d("IndexingUtil", "Security exception for field 'SEARCH_INDEX_DATA_PROVIDER'");
            return null;
        } catch (IllegalAccessException unused3) {
            Log.d("IndexingUtil", "Illegal access to field 'SEARCH_INDEX_DATA_PROVIDER'");
            return null;
        } catch (IllegalArgumentException unused4) {
            Log.d("IndexingUtil", "Illegal argument when accessing field 'SEARCH_INDEX_DATA_PROVIDER'");
            return null;
        }
    }
}
