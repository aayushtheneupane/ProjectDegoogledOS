package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: wf */
/* compiled from: PG */
public final class C0606wf implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0605we();

    /* renamed from: a */
    public int f16218a;

    /* renamed from: b */
    public int f16219b;

    /* renamed from: c */
    public boolean f16220c;

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo10458a() {
        return this.f16218a >= 0;
    }

    public final int describeContents() {
        return 0;
    }

    public C0606wf() {
    }

    public C0606wf(Parcel parcel) {
        this.f16218a = parcel.readInt();
        this.f16219b = parcel.readInt();
        this.f16220c = parcel.readInt() != 1 ? false : true;
    }

    public C0606wf(C0606wf wfVar) {
        this.f16218a = wfVar.f16218a;
        this.f16219b = wfVar.f16219b;
        this.f16220c = wfVar.f16220c;
    }

    /* renamed from: b */
    public final void mo10459b() {
        this.f16218a = -1;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f16218a);
        parcel.writeInt(this.f16219b);
        parcel.writeInt(this.f16220c ? 1 : 0);
    }
}
