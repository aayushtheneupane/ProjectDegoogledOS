package com.android.dialer.about;

import android.content.Context;
import android.support.p000v4.content.AsyncTaskLoader;
import java.util.List;

final class LicenseLoader extends AsyncTaskLoader<List<License>> {
    private List<License> licenses;

    LicenseLoader(Context context) {
        super(context.getApplicationContext());
    }

    public void deliverResult(Object obj) {
        List<License> list = (List) obj;
        this.licenses = list;
        super.deliverResult(list);
    }

    public Object loadInBackground() {
        return Licenses.getLicenses(getContext());
    }

    /* access modifiers changed from: protected */
    public void onStartLoading() {
        List<License> list = this.licenses;
        if (list != null) {
            this.licenses = list;
            super.deliverResult(list);
            return;
        }
        forceLoad();
    }

    /* access modifiers changed from: protected */
    public void onStopLoading() {
        cancelLoad();
    }
}
