package com.android.common.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class CompositeCursorAdapter extends BaseAdapter {
    private static final int INITIAL_CAPACITY = 2;
    private boolean mCacheValid;
    private final Context mContext;
    private int mCount;
    private boolean mNotificationNeeded;
    private boolean mNotificationsEnabled;
    private ArrayList<Partition> mPartitions;

    /* access modifiers changed from: protected */
    public void bindHeaderView(View view, int i, Cursor cursor) {
    }

    /* access modifiers changed from: protected */
    public abstract void bindView(View view, int i, Cursor cursor, int i2);

    /* access modifiers changed from: protected */
    public int getItemViewType(int i, int i2) {
        return 1;
    }

    public int getItemViewTypeCount() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public boolean isEnabled(int i, int i2) {
        return true;
    }

    /* access modifiers changed from: protected */
    public View newHeaderView(Context context, int i, Cursor cursor, ViewGroup viewGroup) {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract View newView(Context context, int i, Cursor cursor, int i2, ViewGroup viewGroup);

    public static class Partition {
        int count;
        Cursor cursor;
        boolean hasHeader;
        int idColumnIndex;
        boolean showIfEmpty;

        public Partition(boolean z, boolean z2) {
            this.showIfEmpty = z;
            this.hasHeader = z2;
        }

        public boolean isEmpty() {
            return this.count == 0;
        }
    }

    public CompositeCursorAdapter(Context context) {
        this(context, 2);
    }

    public CompositeCursorAdapter(Context context, int i) {
        this.mCount = 0;
        this.mCacheValid = true;
        this.mNotificationsEnabled = true;
        this.mContext = context;
        this.mPartitions = new ArrayList<>();
    }

    public Context getContext() {
        return this.mContext;
    }

    public void addPartition(boolean z, boolean z2) {
        addPartition(new Partition(z, z2));
    }

    public void addPartition(Partition partition) {
        this.mPartitions.add(partition);
        invalidate();
        notifyDataSetChanged();
    }

    public void addPartition(int i, Partition partition) {
        this.mPartitions.add(i, partition);
        invalidate();
        notifyDataSetChanged();
    }

    public void removePartition(int i) {
        Cursor cursor = this.mPartitions.get(i).cursor;
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        this.mPartitions.remove(i);
        invalidate();
        notifyDataSetChanged();
    }

    public void clearPartitions() {
        Iterator<Partition> it = this.mPartitions.iterator();
        while (it.hasNext()) {
            it.next().cursor = null;
        }
        invalidate();
        notifyDataSetChanged();
    }

    public void close() {
        Iterator<Partition> it = this.mPartitions.iterator();
        while (it.hasNext()) {
            Cursor cursor = it.next().cursor;
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        this.mPartitions.clear();
        invalidate();
        notifyDataSetChanged();
    }

    public void setHasHeader(int i, boolean z) {
        this.mPartitions.get(i).hasHeader = z;
        invalidate();
    }

    public void setShowIfEmpty(int i, boolean z) {
        this.mPartitions.get(i).showIfEmpty = z;
        invalidate();
    }

    public Partition getPartition(int i) {
        return this.mPartitions.get(i);
    }

    /* access modifiers changed from: protected */
    public void invalidate() {
        this.mCacheValid = false;
    }

    public int getPartitionCount() {
        return this.mPartitions.size();
    }

    /* access modifiers changed from: protected */
    public void ensureCacheValid() {
        if (!this.mCacheValid) {
            this.mCount = 0;
            Iterator<Partition> it = this.mPartitions.iterator();
            while (it.hasNext()) {
                Partition next = it.next();
                Cursor cursor = next.cursor;
                int count = (cursor == null || cursor.isClosed()) ? 0 : cursor.getCount();
                if (next.hasHeader && (count != 0 || next.showIfEmpty)) {
                    count++;
                }
                next.count = count;
                this.mCount += count;
            }
            this.mCacheValid = true;
        }
    }

    public boolean hasHeader(int i) {
        return this.mPartitions.get(i).hasHeader;
    }

    public int getCount() {
        ensureCacheValid();
        return this.mCount;
    }

    public Cursor getCursor(int i) {
        return this.mPartitions.get(i).cursor;
    }

    public void changeCursor(int i, Cursor cursor) {
        Cursor cursor2 = this.mPartitions.get(i).cursor;
        if (cursor2 != cursor) {
            if (cursor2 != null && !cursor2.isClosed()) {
                cursor2.close();
            }
            this.mPartitions.get(i).cursor = cursor;
            if (cursor != null && !cursor.isClosed()) {
                this.mPartitions.get(i).idColumnIndex = cursor.getColumnIndex("_id");
            }
            invalidate();
            notifyDataSetChanged();
        }
    }

    public boolean isPartitionEmpty(int i) {
        Cursor cursor = this.mPartitions.get(i).cursor;
        return cursor == null || cursor.isClosed() || cursor.getCount() == 0;
    }

    public int getPartitionForPosition(int i) {
        ensureCacheValid();
        int size = this.mPartitions.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            int i4 = this.mPartitions.get(i2).count + i3;
            if (i >= i3 && i < i4) {
                return i2;
            }
            i2++;
            i3 = i4;
        }
        return -1;
    }

    public int getOffsetInPartition(int i) {
        ensureCacheValid();
        Iterator<Partition> it = this.mPartitions.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Partition next = it.next();
            int i3 = next.count + i2;
            if (i < i2 || i >= i3) {
                i2 = i3;
            } else {
                int i4 = i - i2;
                return next.hasHeader ? i4 - 1 : i4;
            }
        }
        return -1;
    }

    public int getPositionForPartition(int i) {
        ensureCacheValid();
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += this.mPartitions.get(i3).count;
        }
        return i2;
    }

    public int getViewTypeCount() {
        return getItemViewTypeCount() + 1;
    }

    public int getItemViewType(int i) {
        ensureCacheValid();
        int size = this.mPartitions.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            int i4 = this.mPartitions.get(i2).count + i3;
            if (i < i3 || i >= i4) {
                i2++;
                i3 = i4;
            } else {
                int i5 = i - i3;
                if (this.mPartitions.get(i2).hasHeader) {
                    i5--;
                }
                if (i5 == -1) {
                    return -1;
                }
                return getItemViewType(i2, i5);
            }
        }
        throw new ArrayIndexOutOfBoundsException(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        ensureCacheValid();
        int size = this.mPartitions.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            int i4 = this.mPartitions.get(i2).count + i3;
            if (i < i3 || i >= i4) {
                i2++;
                i3 = i4;
            } else {
                int i5 = i - i3;
                if (this.mPartitions.get(i2).hasHeader) {
                    i5--;
                }
                if (i5 == -1) {
                    view2 = getHeaderView(i2, this.mPartitions.get(i2).cursor, view, viewGroup);
                } else if (this.mPartitions.get(i2).cursor.moveToPosition(i5)) {
                    view2 = getView(i2, this.mPartitions.get(i2).cursor, i5, view, viewGroup);
                } else {
                    throw new IllegalStateException("Couldn't move cursor to position " + i5);
                }
                if (view2 != null) {
                    return view2;
                }
                throw new NullPointerException("View should not be null, partition: " + i2 + " position: " + i5);
            }
        }
        throw new ArrayIndexOutOfBoundsException(i);
    }

    /* access modifiers changed from: protected */
    public View getHeaderView(int i, Cursor cursor, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = newHeaderView(this.mContext, i, cursor, viewGroup);
        }
        bindHeaderView(view, i, cursor);
        return view;
    }

    /* access modifiers changed from: protected */
    public View getView(int i, Cursor cursor, int i2, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = newView(this.mContext, i, cursor, i2, viewGroup);
        }
        bindView(view, i, cursor, i2);
        return view;
    }

    public Object getItem(int i) {
        Cursor cursor;
        ensureCacheValid();
        Iterator<Partition> it = this.mPartitions.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Partition next = it.next();
            int i3 = next.count + i2;
            if (i < i2 || i >= i3) {
                i2 = i3;
            } else {
                int i4 = i - i2;
                if (next.hasHeader) {
                    i4--;
                }
                if (i4 != -1 && (cursor = next.cursor) != null && !cursor.isClosed() && cursor.moveToPosition(i4)) {
                    return cursor;
                }
                return null;
            }
        }
        return null;
    }

    public long getItemId(int i) {
        Cursor cursor;
        ensureCacheValid();
        Iterator<Partition> it = this.mPartitions.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Partition next = it.next();
            int i3 = next.count + i2;
            if (i < i2 || i >= i3) {
                i2 = i3;
            } else {
                int i4 = i - i2;
                if (next.hasHeader) {
                    i4--;
                }
                if (i4 == -1 || next.idColumnIndex == -1 || (cursor = next.cursor) == null || cursor.isClosed() || !cursor.moveToPosition(i4)) {
                    return 0;
                }
                return cursor.getLong(next.idColumnIndex);
            }
        }
        return 0;
    }

    public boolean areAllItemsEnabled() {
        Iterator<Partition> it = this.mPartitions.iterator();
        while (it.hasNext()) {
            if (it.next().hasHeader) {
                return false;
            }
        }
        return true;
    }

    public boolean isEnabled(int i) {
        ensureCacheValid();
        int size = this.mPartitions.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            int i4 = this.mPartitions.get(i2).count + i3;
            if (i < i3 || i >= i4) {
                i2++;
                i3 = i4;
            } else {
                int i5 = i - i3;
                if (!this.mPartitions.get(i2).hasHeader || i5 != 0) {
                    return isEnabled(i2, i5);
                }
                return false;
            }
        }
        return false;
    }

    public void setNotificationsEnabled(boolean z) {
        this.mNotificationsEnabled = z;
        if (z && this.mNotificationNeeded) {
            notifyDataSetChanged();
        }
    }

    public void notifyDataSetChanged() {
        if (this.mNotificationsEnabled) {
            this.mNotificationNeeded = false;
            super.notifyDataSetChanged();
            return;
        }
        this.mNotificationNeeded = true;
    }
}
