package com.android.p032ex.photo.p034a;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.fragment.app.C0424j;
import androidx.fragment.app.C0433s;
import com.android.p032ex.photo.C0720d;
import com.android.p032ex.photo.C0721e;
import com.android.p032ex.photo.fragments.PhotoViewFragment;
import com.android.p032ex.photo.p036c.C0719a;
import p000a.p005b.C0027n;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.ex.photo.a.c */
public class C0712c extends C0710a {

    /* renamed from: No */
    protected boolean f839No;

    /* renamed from: Qu */
    protected C0027n f840Qu = new C0027n(C0719a.f844Wu.length);

    /* renamed from: wk */
    protected final float f841wk;

    public C0712c(Context context, C0433s sVar, Cursor cursor, float f, boolean z) {
        super(context, sVar, cursor);
        this.f841wk = f;
        this.f839No = z;
    }

    /* renamed from: c */
    private String m1107c(Cursor cursor, String str) {
        if (this.f840Qu.indexOfKey(str) >= 0) {
            return cursor.getString(((Integer) this.f840Qu.get(str)).intValue());
        }
        return null;
    }

    /* renamed from: a */
    public C0424j mo5710a(Context context, Cursor cursor, int i) {
        boolean z;
        String c = m1107c(cursor, "contentUri");
        String f = mo5716f(cursor);
        String c2 = m1107c(cursor, "_display_name");
        String c3 = m1107c(cursor, "loadingIndicator");
        boolean z2 = false;
        if (c3 == null) {
            z = false;
        } else {
            z = Boolean.parseBoolean(c3);
        }
        if (c == null && z) {
            z2 = true;
        }
        C0720d a = C0721e.m1128a(this.mContext, PhotoViewFragment.class);
        a.mo5731F(c);
        a.mo5732G(f);
        a.mo5728C(c2);
        a.mo5733G(this.f839No);
        a.mo5734b(this.f841wk);
        return mo5713a(a.build(), i, z2);
    }

    /* renamed from: d */
    public String mo5714d(Cursor cursor) {
        return m1107c(cursor, "contentType");
    }

    /* renamed from: e */
    public String mo5715e(Cursor cursor) {
        return m1107c(cursor, "contentUri");
    }

    /* renamed from: f */
    public String mo5716f(Cursor cursor) {
        return m1107c(cursor, "thumbnailUri");
    }

    public Cursor swapCursor(Cursor cursor) {
        int i;
        this.f840Qu.clear();
        if (cursor != null) {
            for (String str : C0719a.f844Wu) {
                this.f840Qu.put(str, Integer.valueOf(cursor.getColumnIndexOrThrow(str)));
            }
            for (String str2 : C0719a.f845hQ) {
                int columnIndex = cursor.getColumnIndex(str2);
                if (columnIndex != -1) {
                    this.f840Qu.put(str2, Integer.valueOf(columnIndex));
                }
            }
        }
        if (Log.isLoggable("BaseCursorPagerAdapter", 2)) {
            StringBuilder Pa = C0632a.m1011Pa("swapCursor old=");
            Cursor cursor2 = this.mCursor;
            Pa.append(cursor2 == null ? -1 : cursor2.getCount());
            Pa.append("; new=");
            if (cursor == null) {
                i = -1;
            } else {
                i = cursor.getCount();
            }
            Pa.append(i);
            Log.v("BaseCursorPagerAdapter", Pa.toString());
        }
        Cursor cursor3 = this.mCursor;
        if (cursor == cursor3) {
            return null;
        }
        this.mCursor = cursor;
        if (cursor != null) {
            this.mRowIDColumn = cursor.getColumnIndex("uri");
        } else {
            this.mRowIDColumn = -1;
        }
        Cursor cursor4 = this.mCursor;
        if (cursor4 == null || cursor4.isClosed()) {
            this.f837Ou = null;
        } else {
            SparseIntArray sparseIntArray = new SparseIntArray(this.mCursor.getCount());
            this.mCursor.moveToPosition(-1);
            while (this.mCursor.moveToNext()) {
                sparseIntArray.append(this.mCursor.getString(this.mRowIDColumn).hashCode(), this.mCursor.getPosition());
            }
            this.f837Ou = sparseIntArray;
        }
        notifyDataSetChanged();
        return cursor3;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public PhotoViewFragment mo5713a(Intent intent, int i, boolean z) {
        PhotoViewFragment photoViewFragment = new PhotoViewFragment();
        PhotoViewFragment.m1132a(intent, i, z, photoViewFragment);
        return photoViewFragment;
    }
}
