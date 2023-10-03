package p000;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.feedback.ErrorReport;

/* renamed from: est */
/* compiled from: PG */
public final class est extends bil implements esu {
    public est(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.feedback.internal.IFeedbackService");
    }

    /* renamed from: a */
    public final void mo5218a(esi esi, Bundle bundle, long j) {
        Parcel a = mo2085a();
        bin.m2619a(a, (Parcelable) esi);
        bin.m2619a(a, (Parcelable) bundle);
        a.writeLong(j);
        mo2088b(5, a);
    }

    /* renamed from: a */
    public final void mo5214a(Bundle bundle, long j) {
        Parcel a = mo2085a();
        bin.m2619a(a, (Parcelable) bundle);
        a.writeLong(j);
        mo2088b(4, a);
    }

    /* renamed from: b */
    public final void mo5220b(esi esi) {
        Parcel a = mo2085a();
        bin.m2619a(a, (Parcelable) esi);
        Parcel a2 = mo2086a(7, a);
        bin.m2621a(a2);
        a2.recycle();
    }

    /* renamed from: a */
    public final void mo5215a(ErrorReport errorReport) {
        Parcel a = mo2085a();
        bin.m2619a(a, (Parcelable) errorReport);
        Parcel a2 = mo2086a(3, a);
        bin.m2621a(a2);
        a2.recycle();
    }

    /* renamed from: b */
    public final void mo5219b(ErrorReport errorReport) {
        Parcel a = mo2085a();
        bin.m2619a(a, (Parcelable) errorReport);
        Parcel a2 = mo2086a(1, a);
        bin.m2621a(a2);
        a2.recycle();
    }

    /* renamed from: a */
    public final void mo5217a(esi esi) {
        Parcel a = mo2085a();
        bin.m2619a(a, (Parcelable) esi);
        mo2088b(8, a);
    }

    /* renamed from: a */
    public final void mo5216a(ErrorReport errorReport, long j) {
        Parcel a = mo2085a();
        bin.m2619a(a, (Parcelable) errorReport);
        a.writeLong(j);
        mo2089c(6, a);
    }
}
