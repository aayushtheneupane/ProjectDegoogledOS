package p000;

import android.content.Context;

/* renamed from: eri */
/* compiled from: PG */
final class eri implements erp {
    /* renamed from: a */
    public final ero mo5180a(Context context, String str, ern ern) {
        int i;
        ero ero = new ero();
        int a = ern.mo5178a(context, str);
        ero.f8871a = a;
        if (a != 0) {
            i = ern.mo5179a(context, str, false);
            ero.f8872b = i;
        } else {
            i = ern.mo5179a(context, str, true);
            ero.f8872b = i;
        }
        int i2 = ero.f8871a;
        if (i2 == 0 && i == 0) {
            ero.f8873c = 0;
        } else if (i2 >= i) {
            ero.f8873c = -1;
        } else {
            ero.f8873c = 1;
        }
        return ero;
    }
}
