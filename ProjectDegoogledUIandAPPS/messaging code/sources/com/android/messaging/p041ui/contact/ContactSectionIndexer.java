package com.android.messaging.p041ui.contact;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.SectionIndexer;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import java.util.ArrayList;

/* renamed from: com.android.messaging.ui.contact.ContactSectionIndexer */
public class ContactSectionIndexer implements SectionIndexer {
    private static final String BLANK_HEADER_STRING = " ";
    private ArrayList mSectionStartingPositions;
    private String[] mSections;

    public ContactSectionIndexer(Cursor cursor) {
        buildIndexer(cursor);
    }

    private void buildIndexer(Cursor cursor) {
        if (!buildIndexerFromCursorExtras(cursor)) {
            C1430e.m3630w("MessagingApp", "contact provider didn't provide contact label information, fall back to using display name!");
            buildIndexerFromDisplayNames(cursor);
        }
    }

    private boolean buildIndexerFromCursorExtras(Cursor cursor) {
        Bundle extras;
        if (cursor == null || (extras = cursor.getExtras()) == null) {
            return false;
        }
        String[] stringArray = extras.getStringArray("android.provider.extra.ADDRESS_BOOK_INDEX_TITLES");
        int[] intArray = extras.getIntArray("android.provider.extra.ADDRESS_BOOK_INDEX_COUNTS");
        if (stringArray == null || intArray == null || stringArray.length != intArray.length) {
            return false;
        }
        this.mSections = stringArray;
        this.mSectionStartingPositions = new ArrayList(intArray.length);
        int i = 0;
        for (int i2 = 0; i2 < intArray.length; i2++) {
            if (TextUtils.isEmpty(this.mSections[i2])) {
                this.mSections[i2] = BLANK_HEADER_STRING;
            } else if (!this.mSections[i2].equals(BLANK_HEADER_STRING)) {
                String[] strArr = this.mSections;
                strArr[i2] = strArr[i2].trim();
            }
            this.mSectionStartingPositions.add(Integer.valueOf(i));
            i += intArray[i2];
        }
        return true;
    }

    private void buildIndexerFromDisplayNames(Cursor cursor) {
        String str;
        ArrayList arrayList = new ArrayList();
        this.mSectionStartingPositions = new ArrayList();
        if (cursor != null) {
            cursor.moveToPosition(-1);
            int i = 0;
            while (cursor.moveToNext()) {
                String string = cursor.getString(8);
                if (TextUtils.isEmpty(string)) {
                    str = BLANK_HEADER_STRING;
                } else {
                    str = string.substring(0, 1).toUpperCase();
                }
                int size = arrayList.size() - 1;
                if (!TextUtils.equals(size >= 0 ? (String) arrayList.get(size) : null, str)) {
                    arrayList.add(str);
                    this.mSectionStartingPositions.add(Integer.valueOf(i));
                }
                i++;
            }
        }
        this.mSections = new String[arrayList.size()];
        arrayList.toArray(this.mSections);
    }

    public int getPositionForSection(int i) {
        if (this.mSectionStartingPositions.isEmpty()) {
            return 0;
        }
        ArrayList arrayList = this.mSectionStartingPositions;
        return ((Integer) arrayList.get(Math.max(Math.min(i, arrayList.size() - 1), 0))).intValue();
    }

    public int getSectionForPosition(int i) {
        int i2 = 0;
        if (this.mSectionStartingPositions.isEmpty()) {
            return 0;
        }
        int size = this.mSectionStartingPositions.size() - 1;
        if (i <= ((Integer) this.mSectionStartingPositions.get(0)).intValue()) {
            return 0;
        }
        if (i >= ((Integer) this.mSectionStartingPositions.get(size)).intValue()) {
            return size;
        }
        while (i2 <= size) {
            int i3 = (i2 + size) / 2;
            int intValue = ((Integer) this.mSectionStartingPositions.get(i3)).intValue();
            int i4 = i3 + 1;
            int intValue2 = ((Integer) this.mSectionStartingPositions.get(i4)).intValue();
            if (i >= intValue && i < intValue2) {
                return i3;
            }
            if (i < intValue) {
                size = i3 - 1;
            } else if (i >= intValue2) {
                i2 = i4;
            }
        }
        C1424b.fail("Invalid section indexer state: couldn't find section for pos " + i);
        return -1;
    }

    public Object[] getSections() {
        return this.mSections;
    }
}
