package com.android.contacts.widget;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

public class CompositeListAdapter extends BaseAdapter {
    private static final int INITIAL_CAPACITY = 2;
    private ListAdapter[] mAdapters;
    private boolean mAllItemsEnabled;
    private boolean mCacheValid;
    private int mCount;
    private int[] mCounts;
    private DataSetObserver mDataSetObserver;
    private int mSize;
    private int mViewTypeCount;
    private int[] mViewTypeCounts;

    public CompositeListAdapter() {
        this(2);
    }

    public CompositeListAdapter(int i) {
        this.mSize = 0;
        this.mCount = 0;
        this.mViewTypeCount = 0;
        this.mAllItemsEnabled = true;
        this.mCacheValid = true;
        this.mDataSetObserver = new DataSetObserver() {
            public void onChanged() {
                CompositeListAdapter.this.invalidate();
                CompositeListAdapter.this.notifyDataChanged();
            }

            public void onInvalidated() {
                CompositeListAdapter.this.invalidate();
                CompositeListAdapter.this.notifyDataChanged();
            }
        };
        this.mAdapters = new ListAdapter[2];
        this.mCounts = new int[2];
        this.mViewTypeCounts = new int[2];
    }

    /* access modifiers changed from: package-private */
    public void addAdapter(ListAdapter listAdapter) {
        int i = this.mSize;
        ListAdapter[] listAdapterArr = this.mAdapters;
        if (i >= listAdapterArr.length) {
            int i2 = i + 2;
            ListAdapter[] listAdapterArr2 = new ListAdapter[i2];
            System.arraycopy(listAdapterArr, 0, listAdapterArr2, 0, i);
            this.mAdapters = listAdapterArr2;
            int[] iArr = new int[i2];
            System.arraycopy(this.mCounts, 0, iArr, 0, this.mSize);
            this.mCounts = iArr;
            int[] iArr2 = new int[i2];
            System.arraycopy(this.mViewTypeCounts, 0, iArr2, 0, this.mSize);
            this.mViewTypeCounts = iArr2;
        }
        listAdapter.registerDataSetObserver(this.mDataSetObserver);
        int count = listAdapter.getCount();
        int viewTypeCount = listAdapter.getViewTypeCount();
        ListAdapter[] listAdapterArr3 = this.mAdapters;
        int i3 = this.mSize;
        listAdapterArr3[i3] = listAdapter;
        this.mCounts[i3] = count;
        this.mCount += count;
        this.mAllItemsEnabled = listAdapter.areAllItemsEnabled() & this.mAllItemsEnabled;
        int[] iArr3 = this.mViewTypeCounts;
        int i4 = this.mSize;
        iArr3[i4] = viewTypeCount;
        this.mViewTypeCount += viewTypeCount;
        this.mSize = i4 + 1;
        notifyDataChanged();
    }

    /* access modifiers changed from: protected */
    public void notifyDataChanged() {
        if (getCount() > 0) {
            notifyDataSetChanged();
        } else {
            notifyDataSetInvalidated();
        }
    }

    /* access modifiers changed from: protected */
    public void invalidate() {
        this.mCacheValid = false;
    }

    /* access modifiers changed from: protected */
    public void ensureCacheValid() {
        if (!this.mCacheValid) {
            this.mCount = 0;
            this.mAllItemsEnabled = true;
            this.mViewTypeCount = 0;
            for (int i = 0; i < this.mSize; i++) {
                int count = this.mAdapters[i].getCount();
                int viewTypeCount = this.mAdapters[i].getViewTypeCount();
                this.mCounts[i] = count;
                this.mCount += count;
                this.mAllItemsEnabled &= this.mAdapters[i].areAllItemsEnabled();
                this.mViewTypeCount += viewTypeCount;
            }
            this.mCacheValid = true;
        }
    }

    public int getCount() {
        ensureCacheValid();
        return this.mCount;
    }

    public Object getItem(int i) {
        ensureCacheValid();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.mCounts;
            if (i2 < iArr.length) {
                int i4 = iArr[i2] + i3;
                if (i >= i3 && i < i4) {
                    return this.mAdapters[i2].getItem(i - i3);
                }
                i2++;
                i3 = i4;
            } else {
                throw new ArrayIndexOutOfBoundsException(i);
            }
        }
    }

    public long getItemId(int i) {
        ensureCacheValid();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.mCounts;
            if (i2 < iArr.length) {
                int i4 = iArr[i2] + i3;
                if (i >= i3 && i < i4) {
                    return this.mAdapters[i2].getItemId(i - i3);
                }
                i2++;
                i3 = i4;
            } else {
                throw new ArrayIndexOutOfBoundsException(i);
            }
        }
    }

    public int getViewTypeCount() {
        ensureCacheValid();
        return this.mViewTypeCount;
    }

    public int getItemViewType(int i) {
        ensureCacheValid();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int[] iArr = this.mCounts;
            if (i2 < iArr.length) {
                int i5 = iArr[i2] + i3;
                if (i >= i3 && i < i5) {
                    return i4 + this.mAdapters[i2].getItemViewType(i - i3);
                }
                i4 += this.mViewTypeCounts[i2];
                i2++;
                i3 = i5;
            } else {
                throw new ArrayIndexOutOfBoundsException(i);
            }
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ensureCacheValid();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.mCounts;
            if (i2 < iArr.length) {
                int i4 = iArr[i2] + i3;
                if (i >= i3 && i < i4) {
                    return this.mAdapters[i2].getView(i - i3, view, viewGroup);
                }
                i2++;
                i3 = i4;
            } else {
                throw new ArrayIndexOutOfBoundsException(i);
            }
        }
    }

    public boolean areAllItemsEnabled() {
        ensureCacheValid();
        return this.mAllItemsEnabled;
    }

    public boolean isEnabled(int i) {
        ensureCacheValid();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.mCounts;
            if (i2 < iArr.length) {
                int i4 = iArr[i2] + i3;
                if (i < i3 || i >= i4) {
                    i2++;
                    i3 = i4;
                } else if (this.mAdapters[i2].areAllItemsEnabled() || this.mAdapters[i2].isEnabled(i - i3)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                throw new ArrayIndexOutOfBoundsException(i);
            }
        }
    }
}
