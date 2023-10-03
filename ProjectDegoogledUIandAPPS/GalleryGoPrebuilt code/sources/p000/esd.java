package p000;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.api.Status;

/* renamed from: esd */
/* compiled from: PG */
public final class esd {

    /* renamed from: a */
    public static final Status f8900a = new Status(13);

    /* renamed from: b */
    public static final ekn f8901b = new ekn("Feedback.API", f8903d, f8902c, (byte[]) null, (byte[]) null);

    /* renamed from: c */
    private static final eki f8902c = new eki((byte[]) null);

    /* renamed from: d */
    private static final eov f8903d = new erx();

    /* renamed from: a */
    public static ekr m8088a(Context context) {
        return new ekr(context);
    }

    /* renamed from: a */
    public static ekx m8091a(ekv ekv, esi esi, Bundle bundle, long j) {
        return ekv.mo4948a(new esb(ekv, esi, bundle, j));
    }

    /* renamed from: a */
    public static ekx m8089a(ekv ekv, Bundle bundle, long j) {
        return ekv.mo4948a(new esa(ekv, bundle, j));
    }

    @Deprecated
    /* renamed from: b */
    public static ekx m8092b(ekv ekv, esi esi) {
        return ekv.mo4948a(new erz(ekv, esi));
    }

    @Deprecated
    /* renamed from: a */
    public static ekx m8090a(ekv ekv, esi esi) {
        return ekv.mo4948a(new ery(ekv, esi, ekv.mo4947a(), System.nanoTime()));
    }
}
