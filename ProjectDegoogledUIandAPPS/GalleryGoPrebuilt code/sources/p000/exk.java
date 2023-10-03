package p000;

import android.content.ContentResolver;

/* renamed from: exk */
/* compiled from: PG */
final class exk extends exl {
    public exk(String str, Integer num) {
        super(str, num);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo5369a() {
        int i;
        ContentResolver contentResolver = exl.f9183a;
        String str = this.f9184b;
        int intValue = ((Integer) this.f9185c).intValue();
        Object a = exi.m8310a(contentResolver);
        Integer num = (Integer) exi.m8311a(exi.f9174e, str, (Object) Integer.valueOf(intValue));
        if (num != null) {
            i = num.intValue();
        } else {
            String a2 = exi.m8312a(contentResolver, str);
            if (a2 != null) {
                try {
                    int parseInt = Integer.parseInt(a2);
                    num = Integer.valueOf(parseInt);
                    intValue = parseInt;
                } catch (NumberFormatException e) {
                }
            }
            exi.m8314a(a, exi.f9174e, str, num);
            i = intValue;
        }
        return Integer.valueOf(i);
    }
}
