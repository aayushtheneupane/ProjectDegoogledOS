package com.android.dialer.app.calllog;

import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.support.p002v7.widget.RecyclerView;
import android.util.SparseIntArray;

abstract class GroupingListAdapter extends RecyclerView.Adapter {
    protected ContentObserver changeObserver = new ContentObserver(new Handler()) {
        public boolean deliverSelfNotifications() {
            return true;
        }

        public void onChange(boolean z) {
            GroupingListAdapter.this.onContentChanged();
        }
    };
    private Cursor cursor;
    protected DataSetObserver dataSetObserver = new DataSetObserver() {
        public void onChanged() {
            GroupingListAdapter.this.notifyDataSetChanged();
        }
    };
    private SparseIntArray groupMetadata = new SparseIntArray();
    private int itemCount = 0;

    public void addGroup(int i, int i2) {
        int size = this.groupMetadata.size() - 1;
        if (size < 0 || i <= this.groupMetadata.keyAt(size)) {
            this.groupMetadata.put(i, i2);
        } else {
            this.groupMetadata.append(i, i2);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void addGroups(Cursor cursor2);

    public void changeCursor(Cursor cursor2) {
        Cursor cursor3 = this.cursor;
        if (cursor2 != cursor3) {
            if (cursor3 != null) {
                cursor3.unregisterContentObserver(this.changeObserver);
                this.cursor.unregisterDataSetObserver(this.dataSetObserver);
                this.cursor.close();
            }
            this.itemCount = 0;
            this.groupMetadata = new SparseIntArray();
            this.cursor = cursor2;
            if (cursor2 != null) {
                addGroups(this.cursor);
                this.itemCount = this.groupMetadata.size();
                cursor2.registerContentObserver(this.changeObserver);
                cursor2.registerDataSetObserver(this.dataSetObserver);
                notifyDataSetChanged();
            }
        }
    }

    public int getGroupSize(int i) {
        if (i < 0 || i >= this.groupMetadata.size()) {
            return 0;
        }
        return this.groupMetadata.valueAt(i);
    }

    public Object getItem(int i) {
        if (this.cursor != null && i >= 0 && i < this.groupMetadata.size()) {
            if (this.cursor.moveToPosition(this.groupMetadata.keyAt(i))) {
                return this.cursor;
            }
        }
        return null;
    }

    public int getItemCount() {
        return this.itemCount;
    }

    /* access modifiers changed from: protected */
    public abstract void onContentChanged();
}
