package p000;

import android.app.ApplicationErrorReport;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.BitmapTeleporter;
import java.util.List;

/* renamed from: esi */
/* compiled from: PG */
public final class esi extends eqv {
    public static final Parcelable.Creator CREATOR = new esj();

    /* renamed from: a */
    public String f8924a;

    /* renamed from: b */
    public Bundle f8925b;

    /* renamed from: c */
    public String f8926c;

    /* renamed from: d */
    public ApplicationErrorReport f8927d;

    /* renamed from: e */
    public String f8928e;

    /* renamed from: f */
    public BitmapTeleporter f8929f;

    /* renamed from: g */
    public String f8930g;

    /* renamed from: h */
    public List f8931h;

    /* renamed from: i */
    public boolean f8932i;

    /* renamed from: j */
    public eso f8933j;

    /* renamed from: k */
    public esm f8934k;

    /* renamed from: l */
    public boolean f8935l;

    /* renamed from: m */
    public Bitmap f8936m;

    /* renamed from: n */
    public String f8937n;

    /* renamed from: o */
    public boolean f8938o;

    /* renamed from: p */
    public long f8939p;

    /* renamed from: q */
    public esv f8940q;

    private esi(ApplicationErrorReport applicationErrorReport) {
        this((String) null, (Bundle) null, (String) null, applicationErrorReport, (String) null, (BitmapTeleporter) null, (String) null, (List) null, true, (eso) null, (esm) null, false, (Bitmap) null, (String) null, false, 0);
    }

    public /* synthetic */ esi(ApplicationErrorReport applicationErrorReport, byte[] bArr) {
        this(applicationErrorReport);
    }

    public esi(String str, Bundle bundle, String str2, ApplicationErrorReport applicationErrorReport, String str3, BitmapTeleporter bitmapTeleporter, String str4, List list, boolean z, eso eso, esm esm, boolean z2, Bitmap bitmap, String str5, boolean z3, long j) {
        this.f8940q = null;
        this.f8924a = str;
        this.f8925b = bundle;
        this.f8926c = str2;
        this.f8927d = applicationErrorReport;
        this.f8928e = str3;
        this.f8929f = bitmapTeleporter;
        this.f8930g = str4;
        this.f8931h = list;
        this.f8932i = z;
        this.f8933j = eso;
        this.f8934k = esm;
        this.f8935l = z2;
        this.f8936m = bitmap;
        this.f8937n = str5;
        this.f8938o = z3;
        this.f8939p = j;
    }

    /* renamed from: a */
    public static esi m8098a(List list) {
        esi esi = new esi((ApplicationErrorReport) null);
        esi.f8931h = list;
        return esi;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        esj.m8099a(this, parcel, i);
    }
}
