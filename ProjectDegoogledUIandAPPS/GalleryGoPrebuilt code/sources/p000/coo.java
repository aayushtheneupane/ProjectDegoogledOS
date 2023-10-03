package p000;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import java.io.IOException;

/* renamed from: coo */
/* compiled from: PG */
final class coo implements ari {

    /* renamed from: a */
    private final ebh f5316a;

    /* renamed from: b */
    private final Context f5317b;

    /* renamed from: c */
    private ParcelFileDescriptor f5318c;

    public coo(Context context, ebh ebh) {
        this.f5317b = context;
        this.f5316a = ebh;
    }

    /* renamed from: a */
    public final Class mo1510a() {
        return ParcelFileDescriptor.class;
    }

    /* renamed from: c */
    public final void mo1517c() {
    }

    /* renamed from: d */
    public final int mo1518d() {
        return 1;
    }

    /* renamed from: b */
    public final void mo1516b() {
        ParcelFileDescriptor parcelFileDescriptor = this.f5318c;
        if (parcelFileDescriptor != null) {
            try {
                parcelFileDescriptor.close();
            } catch (IOException e) {
            }
        }
    }

    /* renamed from: a */
    public final void mo1514a(apb apb, arh arh) {
        try {
            ParcelFileDescriptor a = this.f5316a.mo4660a(this.f5317b);
            this.f5318c = a;
            arh.mo1525a((Object) a);
        } catch (IOException e) {
            arh.mo1524a((Exception) e);
        }
    }
}
