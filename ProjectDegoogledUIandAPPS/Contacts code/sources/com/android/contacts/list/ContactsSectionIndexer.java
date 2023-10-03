package com.android.contacts.list;

import android.text.TextUtils;
import android.widget.SectionIndexer;
import java.util.Arrays;

public class ContactsSectionIndexer implements SectionIndexer {
    protected static final String BLANK_HEADER_STRING = "…";
    private int mCount;
    private int[] mPositions;
    private String[] mSections;

    public ContactsSectionIndexer(String[] strArr, int[] iArr) {
        if (strArr == null || iArr == null) {
            throw new NullPointerException();
        } else if (strArr.length == iArr.length) {
            this.mSections = strArr;
            this.mPositions = new int[iArr.length];
            int i = 0;
            for (int i2 = 0; i2 < iArr.length; i2++) {
                if (TextUtils.isEmpty(this.mSections[i2])) {
                    this.mSections[i2] = BLANK_HEADER_STRING;
                } else if (!this.mSections[i2].equals(BLANK_HEADER_STRING)) {
                    String[] strArr2 = this.mSections;
                    strArr2[i2] = strArr2[i2].trim();
                }
                this.mPositions[i2] = i;
                i += iArr[i2];
            }
            this.mCount = i;
        } else {
            throw new IllegalArgumentException("The sections and counts arrays must have the same length");
        }
    }

    public Object[] getSections() {
        return this.mSections;
    }

    public int[] getPositions() {
        return this.mPositions;
    }

    public int getPositionForSection(int i) {
        if (i < 0 || i >= this.mSections.length) {
            return -1;
        }
        return this.mPositions[i];
    }

    public int getSectionForPosition(int i) {
        if (i < 0 || i >= this.mCount) {
            return -1;
        }
        int binarySearch = Arrays.binarySearch(this.mPositions, i);
        return binarySearch >= 0 ? binarySearch : (-binarySearch) - 2;
    }

    public void setFavoritesHeader(int i) {
        String[] strArr = this.mSections;
        if (strArr == null) {
            return;
        }
        if (strArr.length <= 0 || !strArr[0].isEmpty()) {
            int i2 = 1;
            String[] strArr2 = new String[(this.mSections.length + 1)];
            int[] iArr = new int[(this.mPositions.length + 1)];
            strArr2[0] = "";
            iArr[0] = 0;
            while (true) {
                int[] iArr2 = this.mPositions;
                if (i2 <= iArr2.length) {
                    int i3 = i2 - 1;
                    strArr2[i2] = this.mSections[i3];
                    iArr[i2] = iArr2[i3] + i;
                    i2++;
                } else {
                    this.mSections = strArr2;
                    this.mPositions = iArr;
                    this.mCount += i;
                    return;
                }
            }
        }
    }
}
