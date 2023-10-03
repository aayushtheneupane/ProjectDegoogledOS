package androidx.recyclerview.widget;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: androidx.recyclerview.widget.ya */
final class C0602ya implements Parcelable.Creator {
    C0602ya() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem(parcel);
    }

    public Object[] newArray(int i) {
        return new StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem[i];
    }
}
