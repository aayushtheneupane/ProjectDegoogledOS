package com.android.contacts.database;

import android.database.AbstractCursor;
import android.database.CursorIndexOutOfBoundsException;

public final class EmptyCursor extends AbstractCursor {
    private String[] mColumns;

    public int getCount() {
        return 0;
    }

    public EmptyCursor(String[] strArr) {
        this.mColumns = strArr;
    }

    public String[] getColumnNames() {
        return this.mColumns;
    }

    public String getString(int i) {
        throw cursorException();
    }

    public short getShort(int i) {
        throw cursorException();
    }

    public int getInt(int i) {
        throw cursorException();
    }

    public long getLong(int i) {
        throw cursorException();
    }

    public float getFloat(int i) {
        throw cursorException();
    }

    public double getDouble(int i) {
        throw cursorException();
    }

    public boolean isNull(int i) {
        throw cursorException();
    }

    private CursorIndexOutOfBoundsException cursorException() {
        return new CursorIndexOutOfBoundsException("Operation not permitted on an empty cursor.");
    }
}
