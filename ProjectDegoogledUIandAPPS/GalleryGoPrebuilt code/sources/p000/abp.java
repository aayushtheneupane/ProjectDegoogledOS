package p000;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

/* renamed from: abp */
/* compiled from: PG */
public final class abp extends C0313lj {
    public static final Parcelable.Creator CREATOR = new abo();

    /* renamed from: c */
    public SparseArray f98c;

    public abp(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        int readInt = parcel.readInt();
        int[] iArr = new int[readInt];
        parcel.readIntArray(iArr);
        Parcelable[] readParcelableArray = parcel.readParcelableArray(classLoader);
        this.f98c = new SparseArray(readInt);
        for (int i = 0; i < readInt; i++) {
            this.f98c.append(iArr[i], readParcelableArray[i]);
        }
    }

    public abp(Parcelable parcelable) {
        super(parcelable);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        SparseArray sparseArray = this.f98c;
        int size = sparseArray != null ? sparseArray.size() : 0;
        parcel.writeInt(size);
        int[] iArr = new int[size];
        Parcelable[] parcelableArr = new Parcelable[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = this.f98c.keyAt(i2);
            parcelableArr[i2] = (Parcelable) this.f98c.valueAt(i2);
        }
        parcel.writeIntArray(iArr);
        parcel.writeParcelableArray(parcelableArr, i);
    }
}
