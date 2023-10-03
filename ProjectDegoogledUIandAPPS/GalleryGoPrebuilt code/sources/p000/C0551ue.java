package p000;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/* renamed from: ue */
/* compiled from: PG */
final class C0551ue extends View.BaseSavedState {
    public static final Parcelable.Creator CREATOR = new C0550ud();

    /* renamed from: a */
    public boolean f15980a;

    public C0551ue(Parcel parcel) {
        super(parcel);
        this.f15980a = parcel.readByte() != 0;
    }

    public C0551ue(Parcelable parcelable) {
        super(parcelable);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeByte(this.f15980a ? (byte) 1 : 0);
    }
}
