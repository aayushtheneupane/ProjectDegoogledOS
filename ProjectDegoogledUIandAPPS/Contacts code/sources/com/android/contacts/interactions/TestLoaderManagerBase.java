package com.android.contacts.interactions;

import android.app.LoaderManager;

abstract class TestLoaderManagerBase extends LoaderManager {
    public abstract void setDelegate(LoaderManager loaderManager);

    TestLoaderManagerBase() {
    }
}
