package p000;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.googlehelp.GoogleHelp;

/* renamed from: ete */
/* compiled from: PG */
public final class ete extends eqv {
    public static final Parcelable.Creator CREATOR = new etf();

    /* renamed from: a */
    public GoogleHelp f8978a;

    /* renamed from: b */
    public final String f8979b;

    /* renamed from: c */
    public final String f8980c;

    /* renamed from: d */
    public final int f8981d;

    /* renamed from: e */
    public final String f8982e;

    /* renamed from: f */
    public final int f8983f;

    public ete(GoogleHelp googleHelp, String str, String str2, int i, String str3, int i2) {
        this.f8978a = googleHelp;
        this.f8979b = str;
        this.f8980c = str2;
        this.f8981d = i;
        this.f8982e = str3;
        this.f8983f = i2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        etf.m8141a(this, parcel, i);
    }
}
