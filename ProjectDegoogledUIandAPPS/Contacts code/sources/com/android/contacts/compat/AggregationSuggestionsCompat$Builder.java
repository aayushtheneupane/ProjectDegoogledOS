package com.android.contacts.compat;

import android.net.Uri;
import android.provider.ContactsContract;
import java.util.ArrayList;

public final class AggregationSuggestionsCompat$Builder {
    private long mContactId;
    private int mLimit;
    private final ArrayList<String> mValues = new ArrayList<>();

    public AggregationSuggestionsCompat$Builder setContactId(long j) {
        this.mContactId = j;
        return this;
    }

    public AggregationSuggestionsCompat$Builder addNameParameter(String str) {
        this.mValues.add(str);
        return this;
    }

    public AggregationSuggestionsCompat$Builder setLimit(int i) {
        this.mLimit = i;
        return this;
    }

    public Uri build() {
        Uri.Builder buildUpon = ContactsContract.Contacts.CONTENT_URI.buildUpon();
        buildUpon.appendEncodedPath(String.valueOf(this.mContactId));
        buildUpon.appendPath("suggestions");
        int i = this.mLimit;
        if (i != 0) {
            buildUpon.appendQueryParameter("limit", String.valueOf(i));
        }
        int size = this.mValues.size();
        for (int i2 = 0; i2 < size; i2++) {
            buildUpon.appendQueryParameter("query", "name:" + this.mValues.get(i2));
        }
        return buildUpon.build();
    }
}
