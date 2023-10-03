package p000;

import android.content.res.Resources;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

/* renamed from: axz */
/* compiled from: PG */
public final class axz implements axp {

    /* renamed from: a */
    private final Resources f1853a;

    public axz(Resources resources) {
        this.f1853a = resources;
    }

    /* renamed from: a */
    public final axo mo1696a(axx axx) {
        return new ayc(this.f1853a, axx.mo1722a(Uri.class, ParcelFileDescriptor.class));
    }
}
