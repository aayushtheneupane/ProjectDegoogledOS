package p000;

import android.os.ParcelFileDescriptor;
import java.io.File;

/* renamed from: awv */
/* compiled from: PG */
final class awv implements awx {
    /* renamed from: a */
    public final Class mo1701a() {
        return ParcelFileDescriptor.class;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo1703a(Object obj) {
        ((ParcelFileDescriptor) obj).close();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo1702a(File file) {
        return ParcelFileDescriptor.open(file, 268435456);
    }
}
