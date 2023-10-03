package p000;

import android.graphics.PointF;
import android.graphics.RectF;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;
import java.util.Iterator;
import java.util.Set;

/* renamed from: cam */
/* compiled from: PG */
public final class cam {

    /* renamed from: a */
    public static final hto f3965a = hto.m12121a((Object) bzl.f3937b, (Object) bzl.f3936a);

    /* renamed from: b */
    public static final hto f3966b = hto.m12124a(bzt.f3940b, bzt.f3941c, bzt.f3942d, bzt.f3943e, bzt.f3944f, bzt.f3945g, byo.f3909b, byo.f3910c, byo.f3911d, byo.f3912e, byo.f3913f, bzh.f3927b, bzh.f3929d, bzh.f3930e);

    /* renamed from: c */
    public static final hto f3967c = hto.m12124a(cas.f3991a, cas.f3992b, bzt.f3939a, bzt.f3940b, bzt.f3941c, bzt.f3942d, bzt.f3943e, bzt.f3944f, bzt.f3945g, byo.f3908a, byo.f3909b, byo.f3910c, byo.f3911d, byo.f3912e, byo.f3913f, cao.f3972a, bzh.f3926a, bzh.f3927b, bzh.f3929d, bzh.f3930e, bzh.f3931f, bzh.f3928c, cay.f3997a);

    /* renamed from: d */
    public static final hto f3968d;

    /* renamed from: e */
    public static final hto f3969e;

    /* renamed from: f */
    private static final hto f3970f;

    /* renamed from: g */
    private static final hsu f3971g;

    static {
        hto.m12123a(bzt.f3939a, byo.f3908a, bzh.f3926a, bzh.f3928c);
        hto.m12123a(bzt.f3939a, byo.f3908a, cao.f3972a, bzh.f3926a);
        htm htm = new htm();
        htm.mo7995b((Iterable) f3967c);
        htm.mo7874b(bzx.f3947a);
        f3968d = htm.mo7993a();
        htm htm2 = new htm();
        htm2.mo7995b((Iterable) f3968d);
        htm2.mo7994a(byu.f3916b, byu.f3917c, byu.f3918d, cac.f3959b, cac.f3960c);
        f3970f = htm2.mo7993a();
        htm htm3 = new htm();
        htm3.mo7995b((Iterable) f3970f);
        htm3.mo7995b((Iterable) f3965a);
        htm3.mo7994a(byu.f3915a, byu.f3919e, bzh.f3932g, bzh.f3933h, bzl.f3938c, byy.f3920a, byy.f3921b, byy.f3922c, bzv.f3946a, cac.f3961d, cay.f3998b);
        f3969e = htm3.mo7993a();
        hsq hsq = new hsq();
        hsq.mo7932a(Integer.class, new cag((byte[]) null));
        hsq.mo7932a(Boolean.class, new cad((byte[]) null));
        hsq.mo7932a(Float.class, new caf((byte[]) null));
        hsq.mo7932a(car.class, new cae());
        hsq.mo7932a(PointF.class, new caj((byte[]) null));
        hsq.mo7932a(RectF.class, new cal((byte[]) null));
        hsq.mo7932a(cau.class, new cak((byte[]) null));
        hsq.mo7932a(byh.class, new cai());
        f3971g = hsq.mo7930a();
    }

    /* renamed from: a */
    public static void m3950a(PipelineParams pipelineParams, PipelineParams pipelineParams2) {
        m3951a(pipelineParams, pipelineParams2, f3969e);
    }

    /* renamed from: a */
    public static void m3951a(PipelineParams pipelineParams, PipelineParams pipelineParams2, Set set) {
        Iterator it = set.iterator();
        boolean z = false;
        while (it.hasNext()) {
            bzy bzy = (bzy) it.next();
            z |= bzy.mo2915a(pipelineParams2, bzy.mo2916b(pipelineParams, ((cah) ife.m12898e((Object) (cah) f3971g.get(bzy.mo2913a().getClass()))).mo2957a()));
        }
    }

    /* renamed from: a */
    public static void m3952a(PipelineParams pipelineParams, Set set) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            bzy bzy = (bzy) it.next();
            bzy.mo2915a(pipelineParams, bzy.mo2913a());
        }
    }
}
