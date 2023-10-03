package p000;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import java.util.List;

/* renamed from: ewk */
/* compiled from: PG */
public final class ewk extends eqv implements ela {
    public static final Parcelable.Creator CREATOR = new ewl();

    /* renamed from: a */
    private final List f9132a;

    /* renamed from: b */
    private final String f9133b;

    /* renamed from: a */
    public final Status mo3498a() {
        return this.f9133b != null ? Status.f4972a : Status.f4976e;
    }

    public ewk(List list, String str) {
        this.f9132a = list;
        this.f9133b = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m99a(parcel, 1, this.f9132a);
        abj.m98a(parcel, 2, this.f9133b);
        abj.m92a(parcel, a);
    }
}
