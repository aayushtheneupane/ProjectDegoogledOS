package com.android.contacts.model;

import android.content.ContentProviderOperation;

public class CPOWrapper {
    private ContentProviderOperation mOperation;
    private int mType;

    public CPOWrapper(ContentProviderOperation contentProviderOperation, int i) {
        this.mOperation = contentProviderOperation;
        this.mType = i;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public ContentProviderOperation getOperation() {
        return this.mOperation;
    }

    public void setOperation(ContentProviderOperation contentProviderOperation) {
        this.mOperation = contentProviderOperation;
    }
}
