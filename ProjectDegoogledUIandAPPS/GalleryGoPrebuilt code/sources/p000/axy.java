package p000;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;

/* renamed from: axy */
/* compiled from: PG */
public final class axy implements axp {

    /* renamed from: a */
    private final Resources f1852a;

    public axy(Resources resources) {
        this.f1852a = resources;
    }

    /* renamed from: a */
    public final axo mo1696a(axx axx) {
        return new ayc(this.f1852a, axx.mo1722a(Uri.class, AssetFileDescriptor.class));
    }
}
