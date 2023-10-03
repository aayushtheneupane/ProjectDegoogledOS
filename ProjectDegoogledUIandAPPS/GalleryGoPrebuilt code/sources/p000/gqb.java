package p000;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.StrictMode;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* renamed from: gqb */
/* compiled from: PG */
public class gqb {
    /* renamed from: a */
    public static ColorStateList m10615a(Context context, TypedArray typedArray, int i) {
        int resourceId;
        ColorStateList a;
        if (typedArray.hasValue(i) && (resourceId = typedArray.getResourceId(i, 0)) != 0 && (a = C0436py.m15104a(context, resourceId)) != null) {
            return a;
        }
        int i2 = Build.VERSION.SDK_INT;
        return typedArray.getColorStateList(i);
    }

    /* renamed from: a */
    public void mo6626a(ghl ghl, float f, float f2) {
    }

    /* renamed from: a */
    public static ColorStateList m10616a(Context context, C0684zc zcVar, int i) {
        int f;
        ColorStateList a;
        if (zcVar.mo10735f(i) && (f = zcVar.mo10734f(i, 0)) != 0 && (a = C0436py.m15104a(context, f)) != null) {
            return a;
        }
        int i2 = Build.VERSION.SDK_INT;
        return zcVar.mo10733e(i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000d, code lost:
        r1 = p000.C0436py.m15105b(r1, (r0 = r2.getResourceId(r3, 0)));
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.drawable.Drawable m10622b(android.content.Context r1, android.content.res.TypedArray r2, int r3) {
        /*
            boolean r0 = r2.hasValue(r3)
            if (r0 == 0) goto L_0x0015
            r0 = 0
            int r0 = r2.getResourceId(r3, r0)
            if (r0 == 0) goto L_0x0015
            android.graphics.drawable.Drawable r1 = p000.C0436py.m15105b(r1, r0)
            if (r1 != 0) goto L_0x0014
            goto L_0x0015
        L_0x0014:
            return r1
        L_0x0015:
            android.graphics.drawable.Drawable r1 = r2.getDrawable(r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.gqb.m10622b(android.content.Context, android.content.res.TypedArray, int):android.graphics.drawable.Drawable");
    }

    /* renamed from: a */
    public static iek m10619a(String str, int i, int i2, StrictMode.ThreadPolicy threadPolicy) {
        return ife.m12824a(Executors.newFixedThreadPool(i, m10620a(str, i2, hpy.m11893b(threadPolicy))));
    }

    /* renamed from: a */
    public static ThreadFactory m10620a(String str, int i, hpy hpy) {
        iex iex = new iex();
        iex.mo8475a();
        iex.mo8476a(str.concat(" #%d"));
        iex.f13979a = (ThreadFactory) ife.m12898e((Object) new goh(hpy, i));
        return iex.m12778a(iex);
    }

    /* renamed from: a */
    private static void m10621a(ieh ieh, ieh ieh2) {
        ieh2.mo53a(new gpz(ieh2, ieh), idh.f13918a);
    }

    /* renamed from: a */
    public static ieh m10618a(ieh ieh, Callable callable, Executor executor) {
        iei a = iei.m12761a(callable);
        ieh.mo53a(a, executor);
        m10621a(ieh, a);
        return a;
    }

    /* renamed from: a */
    public static ieh m10617a(ieh ieh, ice ice, Executor executor) {
        ieh a = ife.m12816a((ice) new gqa(ice, ieh), (Executor) new gpy(ieh, executor));
        m10621a(ieh, a);
        return a;
    }
}
