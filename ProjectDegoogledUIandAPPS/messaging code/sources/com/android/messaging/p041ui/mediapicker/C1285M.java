package com.android.messaging.p041ui.mediapicker;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import com.android.messaging.R;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.ui.mediapicker.M */
public class C1285M extends CursorAdapter {

    /* renamed from: sl */
    private C1290S f2030sl;

    public C1285M(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    /* renamed from: a */
    public void mo7727a(C1290S s) {
        this.f2030sl = s;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        C1424b.m3592ia(view instanceof GalleryGridItemView);
        ((GalleryGridItemView) view).mo7703a(cursor, this.f2030sl);
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.gallery_grid_item_view, viewGroup, false);
    }
}
