package p000;

import android.os.Build;

/* renamed from: fgj */
/* compiled from: PG */
final class fgj extends ThreadLocal {
    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object initialValue() {
        int i = Build.VERSION.SDK_INT;
        return new fgm();
    }
}
