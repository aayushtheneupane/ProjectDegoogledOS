package com.android.dialer.callcomposer;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.List;

public class GalleryGridAdapter extends CursorAdapter {
    private final Context context;
    private final View.OnClickListener onClickListener;
    private GalleryGridItemData selectedData;
    private final List<GalleryGridItemView> views = new ArrayList();

    public GalleryGridAdapter(Context context2, Cursor cursor, View.OnClickListener onClickListener2) {
        super(context2, cursor, 0);
        Assert.isNotNull(onClickListener2);
        this.onClickListener = onClickListener2;
        Assert.isNotNull(context2);
        this.context = context2;
    }

    public void bindView(View view, Context context2, Cursor cursor) {
        GalleryGridItemView galleryGridItemView = (GalleryGridItemView) view;
        galleryGridItemView.bind(cursor);
        galleryGridItemView.setSelected(galleryGridItemView.getData().equals(this.selectedData));
    }

    public int getCount() {
        return super.getCount() + 1;
    }

    /* JADX WARNING: Failed to insert additional move for type inference */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r3, android.view.View r4, android.view.ViewGroup r5) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x001b
            android.database.Cursor r0 = r2.getCursor()
            int r1 = r3 + -1
            boolean r0 = r0.moveToPosition(r1)
            if (r0 == 0) goto L_0x000f
            goto L_0x001b
        L_0x000f:
            java.lang.String r2 = "couldn't move cursor to position "
            java.lang.String r2 = com.android.tools.p006r8.GeneratedOutlineSupport.outline5(r2, r1)
            java.lang.AssertionError r3 = new java.lang.AssertionError
            r3.<init>(r2)
            throw r3
        L_0x001b:
            if (r4 != 0) goto L_0x003a
            android.content.Context r4 = r2.context
            r2.getCursor()
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r4)
            r0 = 2131493000(0x7f0c0088, float:1.8609468E38)
            r1 = 0
            android.view.View r4 = r4.inflate(r0, r5, r1)
            com.android.dialer.callcomposer.GalleryGridItemView r4 = (com.android.dialer.callcomposer.GalleryGridItemView) r4
            android.view.View$OnClickListener r5 = r2.onClickListener
            r4.setOnClickListener(r5)
            java.util.List<com.android.dialer.callcomposer.GalleryGridItemView> r5 = r2.views
            r5.add(r4)
        L_0x003a:
            android.database.Cursor r5 = r2.getCursor()
            if (r3 != 0) goto L_0x0048
            r2 = r4
            com.android.dialer.callcomposer.GalleryGridItemView r2 = (com.android.dialer.callcomposer.GalleryGridItemView) r2
            r3 = 1
            r2.showGallery(r3)
            goto L_0x005b
        L_0x0048:
            r3 = r4
            com.android.dialer.callcomposer.GalleryGridItemView r3 = (com.android.dialer.callcomposer.GalleryGridItemView) r3
            r3.bind(r5)
            com.android.dialer.callcomposer.GalleryGridItemData r5 = r3.getData()
            com.android.dialer.callcomposer.GalleryGridItemData r2 = r2.selectedData
            boolean r2 = r5.equals(r2)
            r3.setSelected(r2)
        L_0x005b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.callcomposer.GalleryGridAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    public void insertEntries(List<GalleryGridItemData> list) {
        Assert.checkArgument(list.size() != 0);
        LogUtil.m9i("GalleryGridAdapter.insertRows", "inserting %d rows", Integer.valueOf(list.size()));
        MatrixCursor matrixCursor = new MatrixCursor(GalleryGridItemData.IMAGE_PROJECTION);
        for (GalleryGridItemData next : list) {
            matrixCursor.addRow(new Object[]{0L, next.getFilePath(), next.getMimeType(), ""});
        }
        matrixCursor.moveToFirst();
        swapCursor(new MergeCursor(new Cursor[]{matrixCursor, getCursor()}));
    }

    public GalleryGridItemData insertEntry(String str, String str2) {
        LogUtil.m9i("GalleryGridAdapter.insertRow", GeneratedOutlineSupport.outline9(str2, " ", str), new Object[0]);
        MatrixCursor matrixCursor = new MatrixCursor(GalleryGridItemData.IMAGE_PROJECTION);
        matrixCursor.addRow(new Object[]{0L, str, str2, ""});
        matrixCursor.moveToFirst();
        swapCursor(new MergeCursor(new Cursor[]{matrixCursor, getCursor()}));
        return new GalleryGridItemData((Cursor) matrixCursor);
    }

    public View newView(Context context2, Cursor cursor, ViewGroup viewGroup) {
        GalleryGridItemView galleryGridItemView = (GalleryGridItemView) LayoutInflater.from(context2).inflate(R.layout.gallery_grid_item_view, viewGroup, false);
        galleryGridItemView.setOnClickListener(this.onClickListener);
        this.views.add(galleryGridItemView);
        return galleryGridItemView;
    }

    public void setSelected(GalleryGridItemData galleryGridItemData) {
        this.selectedData = galleryGridItemData;
        for (GalleryGridItemView next : this.views) {
            next.setSelected(next.getData().equals(galleryGridItemData));
        }
    }
}
