package p000;

import android.content.Context;
import android.os.Build;

/* renamed from: ajs */
/* compiled from: PG */
public final class ajs extends ajp {
    static {
        iol.m14236b("NetworkNotRoamingCtrlr");
    }

    public ajs(Context context, amz amz) {
        super(ake.m651a(context, amz).f670c);
    }

    /* renamed from: a */
    public final boolean mo554a(alg alg) {
        return alg.f721j.f482i == 4;
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ boolean mo555b(Object obj) {
        ajj ajj = (ajj) obj;
        int i = Build.VERSION.SDK_INT;
        return !ajj.f641a || !ajj.f644d;
    }
}
