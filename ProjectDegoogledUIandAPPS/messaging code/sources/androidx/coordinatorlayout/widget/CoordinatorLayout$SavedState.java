package androidx.coordinatorlayout.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import androidx.customview.view.AbsSavedState;

public class CoordinatorLayout$SavedState extends AbsSavedState {
    public static final Parcelable.Creator CREATOR = new C0296a();

    /* renamed from: ho */
    SparseArray f304ho;

    public CoordinatorLayout$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        int readInt = parcel.readInt();
        int[] iArr = new int[readInt];
        parcel.readIntArray(iArr);
        Parcelable[] readParcelableArray = parcel.readParcelableArray(classLoader);
        this.f304ho = new SparseArray(readInt);
        for (int i = 0; i < readInt; i++) {
            this.f304ho.append(iArr[i], readParcelableArray[i]);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        SparseArray sparseArray = this.f304ho;
        int size = sparseArray != null ? sparseArray.size() : 0;
        parcel.writeInt(size);
        int[] iArr = new int[size];
        Parcelable[] parcelableArr = new Parcelable[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = this.f304ho.keyAt(i2);
            parcelableArr[i2] = (Parcelable) this.f304ho.valueAt(i2);
        }
        parcel.writeIntArray(iArr);
        parcel.writeParcelableArray(parcelableArr, i);
    }
}
