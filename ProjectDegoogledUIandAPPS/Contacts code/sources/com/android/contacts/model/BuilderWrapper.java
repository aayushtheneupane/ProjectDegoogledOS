package com.android.contacts.model;

import android.content.ContentProviderOperation;

public class BuilderWrapper {
    private ContentProviderOperation.Builder mBuilder;
    private int mType;

    public BuilderWrapper(ContentProviderOperation.Builder builder, int i) {
        this.mBuilder = builder;
        this.mType = i;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public ContentProviderOperation.Builder getBuilder() {
        return this.mBuilder;
    }

    public void setBuilder(ContentProviderOperation.Builder builder) {
        this.mBuilder = builder;
    }
}
