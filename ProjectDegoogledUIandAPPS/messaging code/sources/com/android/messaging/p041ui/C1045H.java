package com.android.messaging.p041ui;

import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.view.ViewGroup;
import androidx.recyclerview.widget.C0543P;
import androidx.recyclerview.widget.C0586qa;

/* renamed from: com.android.messaging.ui.H */
public abstract class C1045H extends C0543P {
    protected boolean mAutoRequery;
    protected C1041F mChangeObserver;
    protected Context mContext;
    protected Cursor mCursor;
    protected DataSetObserver mDataSetObserver;
    protected boolean mDataValid;
    protected int mRowIDColumn;

    public C1045H(Context context, Cursor cursor, int i) {
        boolean z = false;
        if ((i & 1) == 1) {
            i |= 2;
            this.mAutoRequery = true;
        } else {
            this.mAutoRequery = false;
        }
        z = cursor != null ? true : z;
        this.mCursor = cursor;
        this.mDataValid = z;
        this.mContext = context;
        this.mRowIDColumn = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i & 2) == 2) {
            this.mChangeObserver = new C1041F(this);
            this.mDataSetObserver = new C1043G(this, (C1039E) null);
        } else {
            this.mChangeObserver = null;
            this.mDataSetObserver = null;
        }
        if (z) {
            C1041F f = this.mChangeObserver;
            if (f != null) {
                cursor.registerContentObserver(f);
            }
            DataSetObserver dataSetObserver = this.mDataSetObserver;
            if (dataSetObserver != null) {
                cursor.registerDataSetObserver(dataSetObserver);
            }
        }
    }

    /* renamed from: a */
    public abstract C0586qa mo7005a(Context context, ViewGroup viewGroup, int i);

    /* renamed from: a */
    public abstract void mo7006a(C0586qa qaVar, Context context, Cursor cursor);

    /* renamed from: b */
    public void mo4800b(C0586qa qaVar, int i) {
        if (!this.mDataValid) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        } else if (this.mCursor.moveToPosition(i)) {
            mo7006a(qaVar, this.mContext, this.mCursor);
        } else {
            throw new IllegalStateException("couldn't move cursor to position " + i);
        }
    }

    public int getItemCount() {
        Cursor cursor;
        if (!this.mDataValid || (cursor = this.mCursor) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    public long getItemId(int i) {
        Cursor cursor;
        if (!this.mDataValid || (cursor = this.mCursor) == null || !cursor.moveToPosition(i)) {
            return 0;
        }
        return this.mCursor.getLong(this.mRowIDColumn);
    }

    public C0586qa onCreateViewHolder(ViewGroup viewGroup, int i) {
        return mo7005a(this.mContext, viewGroup, i);
    }

    public Cursor swapCursor(Cursor cursor) {
        Cursor cursor2 = this.mCursor;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            C1041F f = this.mChangeObserver;
            if (f != null) {
                cursor2.unregisterContentObserver(f);
            }
            DataSetObserver dataSetObserver = this.mDataSetObserver;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.mCursor = cursor;
        if (cursor != null) {
            C1041F f2 = this.mChangeObserver;
            if (f2 != null) {
                cursor.registerContentObserver(f2);
            }
            DataSetObserver dataSetObserver2 = this.mDataSetObserver;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.mRowIDColumn = cursor.getColumnIndexOrThrow("_id");
            this.mDataValid = true;
            notifyDataSetChanged();
        } else {
            this.mRowIDColumn = -1;
            this.mDataValid = false;
            notifyDataSetChanged();
        }
        return cursor2;
    }
}
