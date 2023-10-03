package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import p026b.p027a.p030b.p031a.C0632a;

@SuppressLint({"BanParcelableUsage"})
class StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0602ya();
    int mPosition;

    /* renamed from: rt */
    int f621rt;

    /* renamed from: st */
    int[] f622st;

    /* renamed from: tt */
    boolean f623tt;

    StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem(Parcel parcel) {
        this.mPosition = parcel.readInt();
        this.f621rt = parcel.readInt();
        this.f623tt = parcel.readInt() != 1 ? false : true;
        int readInt = parcel.readInt();
        if (readInt > 0) {
            this.f622st = new int[readInt];
            parcel.readIntArray(this.f622st);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: S */
    public int mo4977S(int i) {
        int[] iArr = this.f622st;
        if (iArr == null) {
            return 0;
        }
        return iArr[i];
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("FullSpanItem{mPosition=");
        Pa.append(this.mPosition);
        Pa.append(", mGapDir=");
        Pa.append(this.f621rt);
        Pa.append(", mHasUnwantedGapAfter=");
        Pa.append(this.f623tt);
        Pa.append(", mGapPerSpan=");
        Pa.append(Arrays.toString(this.f622st));
        Pa.append('}');
        return Pa.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mPosition);
        parcel.writeInt(this.f621rt);
        parcel.writeInt(this.f623tt ? 1 : 0);
        int[] iArr = this.f622st;
        if (iArr == null || iArr.length <= 0) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(iArr.length);
        parcel.writeIntArray(this.f622st);
    }

    StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem() {
    }
}
