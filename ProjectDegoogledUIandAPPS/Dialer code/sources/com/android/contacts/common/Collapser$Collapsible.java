package com.android.contacts.common;

import android.content.Context;

public interface Collapser$Collapsible<T> {
    void collapseWith(T t);

    boolean shouldCollapseWith(T t, Context context);
}
