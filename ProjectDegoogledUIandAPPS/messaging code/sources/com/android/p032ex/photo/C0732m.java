package com.android.p032ex.photo;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import androidx.fragment.app.C0433s;
import p000a.p008d.p009a.C0036b;

/* renamed from: com.android.ex.photo.m */
public interface C0732m {
    /* renamed from: ea */
    C0713b mo5701ea();

    View findViewById(int i);

    void finish();

    Context getApplicationContext();

    Context getContext();

    C0734o getController();

    Intent getIntent();

    Resources getResources();

    C0433s getSupportFragmentManager();

    C0036b getSupportLoaderManager();

    void overridePendingTransition(int i, int i2);

    void setContentView(int i);
}
