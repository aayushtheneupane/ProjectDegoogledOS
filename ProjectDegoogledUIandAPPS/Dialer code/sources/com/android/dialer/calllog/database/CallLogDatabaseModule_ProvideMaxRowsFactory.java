package com.android.dialer.calllog.database;

import android.support.p002v7.appcompat.R$style;
import dagger.internal.Factory;

public enum CallLogDatabaseModule_ProvideMaxRowsFactory implements Factory<Integer> {
    INSTANCE;

    public Object get() {
        R$style.checkNotNull1(999, "Cannot return null from a non-@Nullable @Provides method");
        return 999;
    }
}
