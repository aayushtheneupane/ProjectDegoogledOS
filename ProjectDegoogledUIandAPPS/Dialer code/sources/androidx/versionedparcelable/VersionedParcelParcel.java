package androidx.versionedparcelable;

import android.os.Parcel;
import android.util.SparseIntArray;
import com.android.tools.p006r8.GeneratedOutlineSupport;

class VersionedParcelParcel extends VersionedParcel {
    private int mCurrentField = -1;
    private final int mEnd;
    private int mNextRead = 0;
    private final int mOffset;
    private final Parcel mParcel;
    private final SparseIntArray mPositionLookup = new SparseIntArray();
    private final String mPrefix;

    VersionedParcelParcel(Parcel parcel, int i, int i2, String str) {
        this.mParcel = parcel;
        this.mOffset = i;
        this.mEnd = i2;
        this.mNextRead = this.mOffset;
        this.mPrefix = str;
    }

    public void closeField() {
        int i = this.mCurrentField;
        if (i >= 0) {
            int i2 = this.mPositionLookup.get(i);
            int dataPosition = this.mParcel.dataPosition();
            this.mParcel.setDataPosition(i2);
            this.mParcel.writeInt(dataPosition - i2);
            this.mParcel.setDataPosition(dataPosition);
        }
    }

    /* access modifiers changed from: protected */
    public VersionedParcel createSubParcel() {
        Parcel parcel = this.mParcel;
        int dataPosition = parcel.dataPosition();
        int i = this.mNextRead;
        if (i == this.mOffset) {
            i = this.mEnd;
        }
        return new VersionedParcelParcel(parcel, dataPosition, i, GeneratedOutlineSupport.outline12(new StringBuilder(), this.mPrefix, "  "));
    }

    public String readString() {
        return this.mParcel.readString();
    }

    public void writeString(String str) {
        this.mParcel.writeString(str);
    }
}
