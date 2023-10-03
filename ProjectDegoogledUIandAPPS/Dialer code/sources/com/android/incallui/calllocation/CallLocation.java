package com.android.incallui.calllocation;

import android.content.Context;

public interface CallLocation {
    boolean canGetLocation(Context context);

    void close();
}
