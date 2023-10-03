package com.android.messaging.datamodel.data;

import android.database.Cursor;
import android.database.CursorWrapper;

/* renamed from: com.android.messaging.datamodel.data.k */
class C0928k extends CursorWrapper {
    final int mCount;

    public C0928k(Cursor cursor) {
        super(cursor);
        this.mCount = cursor.getCount();
    }

    public int getPosition() {
        return (this.mCount - super.getPosition()) - 1;
    }

    public boolean isAfterLast() {
        return super.isBeforeFirst();
    }

    public boolean isBeforeFirst() {
        return super.isAfterLast();
    }

    public boolean isFirst() {
        return super.isLast();
    }

    public boolean isLast() {
        return super.isFirst();
    }

    public boolean move(int i) {
        return super.move(-i);
    }

    public boolean moveToFirst() {
        return super.moveToLast();
    }

    public boolean moveToLast() {
        return super.moveToFirst();
    }

    public boolean moveToNext() {
        return super.moveToPrevious();
    }

    public boolean moveToPosition(int i) {
        return super.moveToPosition((this.mCount - i) - 1);
    }

    public boolean moveToPrevious() {
        return super.moveToNext();
    }
}
