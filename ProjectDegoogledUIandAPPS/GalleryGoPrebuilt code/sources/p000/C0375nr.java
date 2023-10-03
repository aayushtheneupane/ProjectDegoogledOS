package p000;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/* renamed from: nr */
/* compiled from: PG */
public final class C0375nr extends View.BaseSavedState {
    public static final Parcelable.Creator CREATOR = new C0374nq();

    /* renamed from: a */
    public int f15306a;

    public C0375nr(Parcel parcel) {
        super(parcel);
        this.f15306a = parcel.readInt();
    }

    public C0375nr(Parcelable parcelable) {
        super(parcelable);
    }

    public final String toString() {
        return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.f15306a + "}";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f15306a);
    }
}
