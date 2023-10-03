package androidx.appcompat.widget;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import java.util.HashMap;
import java.util.Map;

class ActivityChooserModel extends DataSetObservable {
    private static final Map<String, ActivityChooserModel> sDataModelRegistry = new HashMap();
    private static final Object sRegistryLock = new Object();

    public interface ActivityChooserModelClient {
    }

    public Intent chooseActivity(int i) {
        throw null;
    }

    public ResolveInfo getActivity(int i) {
        throw null;
    }

    public int getActivityCount() {
        throw null;
    }

    public int getActivityIndex(ResolveInfo resolveInfo) {
        throw null;
    }

    public ResolveInfo getDefaultActivity() {
        throw null;
    }

    public int getHistorySize() {
        throw null;
    }

    public void setDefaultActivity(int i) {
        throw null;
    }
}
