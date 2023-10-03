package com.android.dialer.app.alert;

import android.view.View;

public interface AlertManager {
    void add(View view);

    void clear();

    View inflate(int i);
}
