package p000;

import android.graphics.drawable.BitmapDrawable;
import java.io.File;

/* renamed from: azi */
/* compiled from: PG */
public final class azi implements arc {

    /* renamed from: a */
    private final auk f1902a;

    /* renamed from: b */
    private final arc f1903b;

    public azi(auk auk, arc arc) {
        this.f1902a = auk;
        this.f1903b = arc;
    }

    /* renamed from: a */
    public final int mo1509a() {
        return 2;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo1488a(Object obj, File file, aqz aqz) {
        return this.f1903b.mo1488a(new azk(((BitmapDrawable) ((aua) obj).mo1605b()).getBitmap(), this.f1902a), file, aqz);
    }
}
