package com.android.messaging.p041ui.photoviewer;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import androidx.fragment.app.C0433s;
import com.android.p032ex.photo.fragments.PhotoViewFragment;
import com.android.p032ex.photo.p034a.C0712c;

/* renamed from: com.android.messaging.ui.photoviewer.b */
public class C1373b extends C0712c {
    public C1373b(Context context, C0433s sVar, Cursor cursor, float f, boolean z) {
        super(context, sVar, cursor, f, z);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public PhotoViewFragment mo5713a(Intent intent, int i, boolean z) {
        BuglePhotoViewFragment buglePhotoViewFragment = new BuglePhotoViewFragment();
        PhotoViewFragment.m1132a(intent, i, z, buglePhotoViewFragment);
        return buglePhotoViewFragment;
    }
}
