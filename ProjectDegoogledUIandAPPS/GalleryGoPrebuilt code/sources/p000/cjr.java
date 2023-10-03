package p000;

import com.google.android.apps.photosgo.environment.BuildType;
import p003j$.util.Optional;

/* renamed from: cjr */
/* compiled from: PG */
public final class cjr {

    /* renamed from: a */
    private final boolean f4513a;

    /* renamed from: b */
    private final Optional f4514b = Optional.empty();

    public /* synthetic */ cjr(cjq cjq) {
        this.f4513a = !cjq.m4405a(cjq.f4511c, cjq.f4509a) ? cjq.m4405a(cjq.f4510b, cjq.f4509a) ? cjq.f4512d : false : true;
    }

    /* renamed from: a */
    public final boolean mo3175a() {
        return ((Boolean) this.f4514b.orElse(Boolean.valueOf(this.f4513a))).booleanValue();
    }

    /* renamed from: a */
    public static cjq m4407a(BuildType buildType) {
        return new cjq(buildType);
    }
}
