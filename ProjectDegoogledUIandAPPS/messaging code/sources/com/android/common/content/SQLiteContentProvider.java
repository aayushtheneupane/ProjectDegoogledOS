package com.android.common.content;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;
import android.net.Uri;
import java.util.ArrayList;

public abstract class SQLiteContentProvider extends ContentProvider implements SQLiteTransactionListener {

    /* renamed from: tb */
    private SQLiteOpenHelper f744tb;

    /* renamed from: ub */
    private volatile boolean f745ub;

    /* renamed from: vb */
    protected SQLiteDatabase f746vb;

    /* renamed from: wb */
    private final ThreadLocal f747wb = new ThreadLocal();

    /* renamed from: Dm */
    private boolean m1026Dm() {
        return this.f747wb.get() != null && ((Boolean) this.f747wb.get()).booleanValue();
    }

    /* access modifiers changed from: protected */
    /* renamed from: Oa */
    public void mo5419Oa() {
    }

    /* renamed from: Pa */
    public int mo5420Pa() {
        return 500;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Qa */
    public abstract void mo5421Qa();

    /* access modifiers changed from: protected */
    /* renamed from: Ra */
    public void mo5422Ra() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: Sa */
    public void mo5423Sa() {
        if (this.f745ub) {
            this.f745ub = false;
            mo5421Qa();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract int mo5424a(Uri uri, ContentValues contentValues, String str, String[] strArr);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract int mo5425a(Uri uri, String str, String[] strArr);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Uri mo5426a(Uri uri, ContentValues contentValues);

    public ContentProviderResult[] applyBatch(ArrayList arrayList) {
        this.f746vb = this.f744tb.getWritableDatabase();
        this.f746vb.beginTransactionWithListener(this);
        try {
            this.f747wb.set(true);
            int size = arrayList.size();
            ContentProviderResult[] contentProviderResultArr = new ContentProviderResult[size];
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (i < size) {
                i2++;
                if (i2 <= mo5420Pa()) {
                    ContentProviderOperation contentProviderOperation = (ContentProviderOperation) arrayList.get(i);
                    if (i > 0 && contentProviderOperation.isYieldAllowed()) {
                        boolean z = this.f745ub;
                        if (this.f746vb.yieldIfContendedSafely(4000)) {
                            this.f746vb = this.f744tb.getWritableDatabase();
                            this.f745ub = z;
                            i3++;
                        }
                        i2 = 0;
                    }
                    contentProviderResultArr[i] = contentProviderOperation.apply(this, contentProviderResultArr, i);
                    i++;
                } else {
                    throw new OperationApplicationException("Too many content provider operations between yield points. The maximum number of operations per yield point is 500", i3);
                }
            }
            this.f746vb.setTransactionSuccessful();
            return contentProviderResultArr;
        } finally {
            this.f747wb.set(false);
            this.f746vb.endTransaction();
            mo5423Sa();
        }
    }

    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        int length = contentValuesArr.length;
        this.f746vb = this.f744tb.getWritableDatabase();
        this.f746vb.beginTransactionWithListener(this);
        int i = 0;
        while (i < length) {
            try {
                if (mo5426a(uri, contentValuesArr[i]) != null) {
                    this.f745ub = true;
                }
                boolean z = this.f745ub;
                SQLiteDatabase sQLiteDatabase = this.f746vb;
                this.f746vb.yieldIfContendedSafely();
                this.f746vb = sQLiteDatabase;
                this.f745ub = z;
                i++;
            } catch (Throwable th) {
                this.f746vb.endTransaction();
                throw th;
            }
        }
        this.f746vb.setTransactionSuccessful();
        this.f746vb.endTransaction();
        mo5423Sa();
        return length;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public abstract SQLiteOpenHelper mo5429c(Context context);

    /* JADX INFO: finally extract failed */
    public int delete(Uri uri, String str, String[] strArr) {
        int i;
        if (!m1026Dm()) {
            this.f746vb = this.f744tb.getWritableDatabase();
            this.f746vb.beginTransactionWithListener(this);
            try {
                i = mo5425a(uri, str, strArr);
                if (i > 0) {
                    this.f745ub = true;
                }
                this.f746vb.setTransactionSuccessful();
                this.f746vb.endTransaction();
                mo5423Sa();
            } catch (Throwable th) {
                this.f746vb.endTransaction();
                throw th;
            }
        } else {
            i = mo5425a(uri, str, strArr);
            if (i > 0) {
                this.f745ub = true;
            }
        }
        return i;
    }

    /* JADX INFO: finally extract failed */
    public Uri insert(Uri uri, ContentValues contentValues) {
        Uri uri2;
        if (!m1026Dm()) {
            this.f746vb = this.f744tb.getWritableDatabase();
            this.f746vb.beginTransactionWithListener(this);
            try {
                uri2 = mo5426a(uri, contentValues);
                if (uri2 != null) {
                    this.f745ub = true;
                }
                this.f746vb.setTransactionSuccessful();
                this.f746vb.endTransaction();
                mo5423Sa();
            } catch (Throwable th) {
                this.f746vb.endTransaction();
                throw th;
            }
        } else {
            uri2 = mo5426a(uri, contentValues);
            if (uri2 != null) {
                this.f745ub = true;
            }
        }
        return uri2;
    }

    public void onBegin() {
        mo5422Ra();
    }

    public void onCommit() {
        mo5419Oa();
    }

    public boolean onCreate() {
        this.f744tb = mo5429c(getContext());
        return true;
    }

    public void onRollback() {
    }

    /* JADX INFO: finally extract failed */
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int i;
        if (!m1026Dm()) {
            this.f746vb = this.f744tb.getWritableDatabase();
            this.f746vb.beginTransactionWithListener(this);
            try {
                i = mo5424a(uri, contentValues, str, strArr);
                if (i > 0) {
                    this.f745ub = true;
                }
                this.f746vb.setTransactionSuccessful();
                this.f746vb.endTransaction();
                mo5423Sa();
            } catch (Throwable th) {
                this.f746vb.endTransaction();
                throw th;
            }
        } else {
            i = mo5424a(uri, contentValues, str, strArr);
            if (i > 0) {
                this.f745ub = true;
            }
        }
        return i;
    }
}
