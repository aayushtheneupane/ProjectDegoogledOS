package p000;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: gj */
/* compiled from: PG */
final class C0177gj implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0176gi();

    /* renamed from: a */
    public final String f11451a;

    /* renamed from: b */
    public final String f11452b;

    /* renamed from: c */
    public final boolean f11453c;

    /* renamed from: d */
    public final int f11454d;

    /* renamed from: e */
    public final int f11455e;

    /* renamed from: f */
    public final String f11456f;

    /* renamed from: g */
    public final boolean f11457g;

    /* renamed from: h */
    public final boolean f11458h;

    /* renamed from: i */
    public final boolean f11459i;

    /* renamed from: j */
    public final Bundle f11460j;

    /* renamed from: k */
    public final boolean f11461k;

    /* renamed from: l */
    public final int f11462l;

    /* renamed from: m */
    public Bundle f11463m;

    public final int describeContents() {
        return 0;
    }

    public C0177gj(Parcel parcel) {
        this.f11451a = parcel.readString();
        this.f11452b = parcel.readString();
        boolean z = true;
        this.f11453c = parcel.readInt() != 0;
        this.f11454d = parcel.readInt();
        this.f11455e = parcel.readInt();
        this.f11456f = parcel.readString();
        this.f11457g = parcel.readInt() != 0;
        this.f11458h = parcel.readInt() != 0;
        this.f11459i = parcel.readInt() != 0;
        this.f11460j = parcel.readBundle();
        this.f11461k = parcel.readInt() == 0 ? false : z;
        this.f11463m = parcel.readBundle();
        this.f11462l = parcel.readInt();
    }

    public C0177gj(C0147fh fhVar) {
        this.f11451a = fhVar.getClass().getName();
        this.f11452b = fhVar.f9591j;
        this.f11453c = fhVar.f9599r;
        this.f11454d = fhVar.f9562A;
        this.f11455e = fhVar.f9563B;
        this.f11456f = fhVar.f9564C;
        this.f11457g = fhVar.f9567F;
        this.f11458h = fhVar.f9598q;
        this.f11459i = fhVar.f9566E;
        this.f11460j = fhVar.f9592k;
        this.f11461k = fhVar.f9565D;
        this.f11462l = fhVar.f9582U.ordinal();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f11451a);
        sb.append(" (");
        sb.append(this.f11452b);
        sb.append(")}:");
        if (this.f11453c) {
            sb.append(" fromLayout");
        }
        if (this.f11455e != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.f11455e));
        }
        String str = this.f11456f;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.f11456f);
        }
        if (this.f11457g) {
            sb.append(" retainInstance");
        }
        if (this.f11458h) {
            sb.append(" removing");
        }
        if (this.f11459i) {
            sb.append(" detached");
        }
        if (this.f11461k) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f11451a);
        parcel.writeString(this.f11452b);
        parcel.writeInt(this.f11453c ? 1 : 0);
        parcel.writeInt(this.f11454d);
        parcel.writeInt(this.f11455e);
        parcel.writeString(this.f11456f);
        parcel.writeInt(this.f11457g ? 1 : 0);
        parcel.writeInt(this.f11458h ? 1 : 0);
        parcel.writeInt(this.f11459i ? 1 : 0);
        parcel.writeBundle(this.f11460j);
        parcel.writeInt(this.f11461k ? 1 : 0);
        parcel.writeBundle(this.f11463m);
        parcel.writeInt(this.f11462l);
    }
}
