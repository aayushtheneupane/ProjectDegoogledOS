package p000;

import android.content.res.Resources;
import android.net.Uri;
import java.io.InputStream;

/* renamed from: aya */
/* compiled from: PG */
public final class aya implements axp {

    /* renamed from: a */
    private final Resources f1855a;

    public aya(Resources resources) {
        this.f1855a = resources;
    }

    /* renamed from: a */
    public final axo mo1696a(axx axx) {
        return new ayc(this.f1855a, axx.mo1722a(Uri.class, InputStream.class));
    }
}
