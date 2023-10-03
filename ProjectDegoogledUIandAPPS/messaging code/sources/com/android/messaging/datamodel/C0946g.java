package com.android.messaging.datamodel;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.datamodel.g */
public class C0946g {
    protected final Context mContext;
    protected final String[] mProjection;
    protected final String mSelection;
    protected final String[] mSelectionArgs;
    protected final String mSortOrder;
    protected final Uri mUri;

    public C0946g(Context context, Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        this.mContext = context;
        this.mUri = uri;
        this.mProjection = strArr;
        this.mSelection = str;
        this.mSelectionArgs = strArr2;
        this.mSortOrder = str2;
    }

    /* renamed from: Wd */
    public static C0946g m2098Wd() {
        return new C0946g((Context) null, (Uri) null, (String[]) null, (String) null, (String[]) null, (String) null);
    }

    /* renamed from: J */
    public C0837b mo6583J(String str) {
        return new C0837b(str, this.mContext, this.mUri, this.mProjection, this.mSelection, this.mSelectionArgs, this.mSortOrder);
    }

    /* renamed from: Xd */
    public Cursor mo6584Xd() {
        C1424b.m3584Gj();
        if (this.mUri == null) {
            return null;
        }
        return this.mContext.getContentResolver().query(this.mUri, this.mProjection, this.mSelection, this.mSelectionArgs, this.mSortOrder);
    }

    public Uri getUri() {
        return this.mUri;
    }
}
